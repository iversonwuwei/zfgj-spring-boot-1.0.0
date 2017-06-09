package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.*;
import com.dlfc.zfgj.service.web.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/12.
 */

@Slf4j
@Transactional
@Service
public class ContractStepServiceImpl implements ContractStepService {

    private static final String CREATE_CONTRACT_STEP1 = "新建合同step1";
    private static final String CREATE_CONTRACT_STEP2 = "新建合同step2";
    private static final String CREATE_CONTRACT_STEP3 = "新建合同step3";
    private static final String CREATE_CONTRACT_STEP4 = "新建合同step4";
    private static final String CREATE_CONTRACT_DOWNLOAD = "下载合同";
    private static final String CREATE_CONTRACT_UPLOAD = "上传合同";
    private static final String CANCEL_CONTRACT = "取消合同";

    private SysPerson sysPerson;
    private List<SysPerson> sysPersonList;

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private ConContractLogMapper conContractLogMapper;
    @Autowired
    private ConHouseUserMapper conHouseUserMapper;
    @Autowired
    private ConHouseUserLogMapper conHouseUserLogMapper;
    @Autowired
    private ConHouseItemsMapper houseItemsMapper;
    @Autowired
    private ConHouseItemsLogMapper houseItemsLogMapper;
    @Autowired
    private ConOtherCostsMapper conOtherCostsMapper;
    @Autowired
    private SysPersonService sysPersonServiceImpl;
    @Autowired
    private DownloadService downloadServiceImpl;
    @Autowired
    private AgtEmpInfoService agtEmpInfoService;
    @Autowired
    private HouHouseInfoService houHouseInfoService;
    @Autowired
    private AgtCompInfoService agtCompInfoService;
    @Autowired
    private HouCoOwnerTempService houCoOwnerTempService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private ConHouseUserChildrenMapper conHouseUserChildrenMapper;
    @Autowired
    private ConHouseUserChildrenLogMapper conHouseUserChildrenLogMapper;

    /**
     * 判断合同ID是否为空
     *
     * @param contractId
     * @return
     */
    @Override
    public void checkContractId(String contractId) throws ApplicationException {
        if (StringUtils.isEmpty(contractId)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0005"));
        }
    }

    /**
     * 逻辑取消合同
     *
     * @param contractId
     * @param user
     */
    @Override
    public void cancelContract(String contractId,
                               UsrUser user) throws ApplicationException {
        ConContract conContract = conContractMapper.selectByPrimaryKey(contractId);
        if (null != conContract
                && conContract.getStatus() == ConStatusEnum.CREATING_ENUM.getValue()) {
            ConContract conContractForUpdate = new ConContract();
            conContractForUpdate.setId(contractId);
            conContractForUpdate.setVersion(conContract.getVersion() + 1);
//            conContractForUpdate.setStatus((short) ConStatusEnum.CANCEL_ENUM.getValue());
            conContractForUpdate.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
            conContractForUpdate.preUpdate(user);
            conContractMapper.updateByPrimaryKeySelective(conContractForUpdate);
            saveLog(contractId,
                    conContract.getStatus().intValue(),
                    ConOptEventEnum.MODIFY_ENUM.getValue(),
                    CANCEL_CONTRACT,
                    user);
        } else {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0007"));
        }
    }

    /**
     * 根据ID获取合同详细信息
     *
     * @param cid
     * @return
     */
    @Override
    public ConContract getContractByCid(String cid) {
        ConContract conContract = new ConContract();
        if (StringUtils.isNotEmpty(cid)) {
            conContract = conContractMapper.selectByPrimaryKey(cid);
        }
        return conContract;
    }

    /**
     * 合同新建第一步
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void saveStep1(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoByUserId(user.getId());
        String agtId = StringUtils.EMPTY;
        if (null != agtEmpInfo) {
            agtId = agtEmpInfo.getId();
        }
        if (conContract.getIsNewRecord()) {
            conContract.setEid(agtId);
        }
        conContract.setBeid(agtId);
        AgtCompInfo agtCompInfo = agtCompInfoService.getComInfoByComId(agtEmpInfo.getCompanyId());
        if (null != agtCompInfo) {
            conContract.setAgtComName(agtCompInfo.getName());
            conContract.setAgtAddress(agtCompInfo.getAddress());
            conContract.setAgtComTel(agtCompInfo.getTel());
        }
        saveLessor(conContract, user);
        saveLessee(conContract, user);
        saveElse(conContract, user);
    }

    /**
     * 如果房东在平台签过合同，则系统默认把信息带过来（在此中介上一次签合同中的产权信息）
     *
     * @param contract
     * @return
     */
    @Override
    public ConContract getLatestContract(ConContract contract) {
        ConContractExample ConContractExample = new ConContractExample();
        ConContractExample.Criteria criteria = ConContractExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        List<String> eIdList = new ArrayList<>();
        String eid = contract.getEid();
        if (StringUtils.isNotEmpty(eid)) {
            AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoById(eid);
            if (null != agtEmpInfo) {
                eIdList = agtEmpInfoService.getEmpIdListByCompanyId(agtEmpInfo.getCompanyId());
            }
        }
        if (null != eIdList) {
            criteria.andEidIn(eIdList);
        }
        criteria.andStatusEqualTo((short) ConStatusEnum.ACTIVE_ENUM.getValue());
        criteria.andLessorIdNoEqualTo(contract.getLessorIdNo());
        criteria.andLessorIdTypeEqualTo(contract.getLessorIdType());
        ConContractExample.setOrderByClause("MODIFY_TIME DESC");
        List<ConContract> conContractList = conContractMapper.selectByExample(ConContractExample);
        ConContract conContract = new ConContract();
        if (null != conContractList && conContractList.size() > 0) {
            conContract = conContractList.get(0);
        }
        return conContract;
    }

    /**
     * 合同新建第二步
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void saveStep2(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        if (conContract.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue()) {
            conContract.setLeaseDomain("整租");
        }
        // 保存实际使用者信息到系统表
        sysPersonServiceImpl.saveIfNotExistInSysPerson(conContract.getHouseUsers());
        // 保存房屋信息
        saveHouseInfo(conContract, user);
//        HouHouseInfo houHouseInfo = houHouseInfoService.getHouHouseInfoById(conContract.getHid());
//        //判断是否可以赠送保险
//        boolean insuranceFlag = sysParamService.isOn();
//        if (null != houHouseInfo && insuranceFlag) {
//            if (conContract.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue()
//                    && conContract.getLeaseTermYear() >= 1
//                    && (houHouseInfo.getStructure() == null
//                    || houHouseInfo.getStructure() == StructureEnum.ZHUAN_HUN_ENUM.getValue()
//                    || houHouseInfo.getStructure() == StructureEnum.KUANG_JIYA_ENUM.getValue())) {
//                conContract.setHasInsurance(YesNoEnum.YES_ENUM.getValue());
//            } else {
//                conContract.setHasInsurance(YesNoEnum.NO_ENUM.getValue());
//            }
//        } else {
        conContract.setHasInsurance(YesNoEnum.NO_ENUM.getValue());
//        }
        String conLogId = updateCon(conContract, ConOptEventEnum.MODIFY_ENUM.getValue(), CREATE_CONTRACT_STEP2, user);
        String contractId = conContract.getId();
        // 删除旧使用者
        deleteHouseUser(contractId);
        // 保存新使用者
        saveHouseUser(conContract.getHouseUsers(), contractId, conLogId, user);
        //删除旧随行子女
        deleteHouseChildren(contractId);
        //保存随行子女
        saveHouseChildren(conContract.getHouseUserChildrens(), contractId, conLogId, user);
    }




    /**
     * 保存房屋信息
     *
     * @param conContract
     * @param user
     * @return
     */
    private void saveHouseInfo(ConContract conContract,
                               UsrUser user) throws ApplicationException {
        String hid;
        HouHouseInfo houHouseInfo;
        String propertyIdNo = conContract.getPropertyIdNo();
        ConContract contractTemp = new ConContract();
        if (StringUtils.isNotEmpty(propertyIdNo)) {
            contractTemp.setPropertyIdNo(propertyIdNo);
            houHouseInfo = houHouseInfoService.getHouseInfoByContractInput(contractTemp);
        } else {
            contractTemp.setHouseAddr(conContract.getHouseAddr());
            houHouseInfo = houHouseInfoService.getHouseInfoByContractInput(contractTemp);
        }
        if (null != houHouseInfo && StringUtils.isNotEmpty(houHouseInfo.getId())) {
            hid = houHouseInfo.getId();
            Integer version = houHouseInfo.getVersion();
            // 产权编号或者房源地址相符时，更新房源信息
            houHouseInfo = getHouseFromContract(conContract);
            houHouseInfo.setId(hid);
            houHouseInfo.setVersion(version);
            houHouseInfoService.updateHouseInfo(houHouseInfo, user);
            // 删除房源原房主
            houCoOwnerTempService.deleteOldOwners(hid);
        } else {
            // 保存房源信息
            houHouseInfo = getHouseFromContract(conContract);
            hid = houHouseInfoService.saveHouseInfo(houHouseInfo, user);
        }
        //保存房东信息为房源房主信息
        ConContract contract = getContractByCid(conContract.getId());
        HouCoOwnerTemp houCoOwner = new HouCoOwnerTemp();
        houCoOwner.preInsert(user);
        List<SysPerson> sysPersonList = sysPersonServiceImpl.getSyspersonsByParams(
                contract.getLessorIdNo(), contract.getLessorIdType(), contract.getLessorName());
        if (null != sysPersonList && sysPersonList.size() == 1) {
            SysPerson sysPerson = sysPersonList.get(0);
            houCoOwner.setCoPid(sysPerson.getId());
            conContract.setHouseOwnerPid(sysPerson.getId());
        }
        houCoOwner.setHid(hid);
        houCoOwnerTempService.saveHouseOwner(houCoOwner);
        //保存房主信息到合同表
        conContract.setHid(hid);
        conContract.setHouseOwnerName(contract.getLessorName());
        conContract.setHouseOwnerIdType(contract.getLessorIdType());
        conContract.setHouseOwnerIdNo(contract.getLessorIdNo());
        conContract.setHouseOwnerMobile(contract.getLessorMobile());
    }

    /**
     * 从新建合同页面获取房屋信息
     *
     * @param conContract
     */
    private HouHouseInfo getHouseFromContract(ConContract conContract) {
        HouHouseInfo houHouseInfo = new HouHouseInfo();
        houHouseInfo.setHouseAddr(conContract.getHouseAddr());
        houHouseInfo.setStructure(conContract.getHouseStructure());
        houHouseInfo.setBuildingArea(conContract.getBuildingArea().doubleValue());
        houHouseInfo.setPropertyIdType(conContract.getPropertyType());
        houHouseInfo.setPropertyIdNo(conContract.getPropertyIdNo());
        // 房屋来源为租房管家建立
        houHouseInfo.setSource(HouseSourceEnum.ZFGJ_ENUM.getValue());
        return houHouseInfo;
    }

    /**
     * 合同新建第三步
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void saveStep3(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        updateCon(conContract, ConOptEventEnum.MODIFY_ENUM.getValue(), CREATE_CONTRACT_STEP3, user);
    }

    /**
     * 合同新建第四步
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void saveStep4(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        String contractId = conContract.getId();
        String conLogId = updateCon(conContract, ConOptEventEnum.MODIFY_ENUM.getValue(), CREATE_CONTRACT_STEP4, user);
        deleteHouseItems(contractId);
        saveHouseItems(conContract.getHouItemsList(), contractId, conLogId, user);
        deleteOtherItems(contractId);
        saveOtherItems(conContract.getConOtherCostsList(), contractId, user);
    }

    /**
     * 将array转成String
     *
     * @param array
     * @return
     */
    @Override
    public String getStrFromArray(String[] array) {
        StringBuffer stringBuffer = new StringBuffer();
        if (null != array && array.length > 0) {
            for (String str : array) {
                if (StringUtils.isNotEmpty(str)) {
                    stringBuffer.append(str);
                    stringBuffer.append(",");
                }
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            return stringBuffer.toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 将符合条件的代码取出组成list
     *
     * @param str
     * @param sysCodeList
     * @return
     */
    @Override
    public List<String> getListFromStr(String str,
                                       List<SysCode> sysCodeList) {
        List<String> stringList = new ArrayList<>();
        String[] stringArray = str.split(",");
        for (String item : stringArray) {
            if (StringUtils.isNotEmpty(item)) {
                for (SysCode sysCode : sysCodeList) {
                    if (item.equals(sysCode.getCode())) {
                        stringList.add(item);
                    }
                }
            }
        }
        return stringList;
    }

    /**
     * 合同新建第五步
     *
     * @param contractId
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void saveStep5(String contractId,
                          UsrUser user) throws ApplicationException {
        ConContract conContract = getContractByCid(contractId);
        conContract.setStatus((short) ConStatusEnum.WAIT_UPLOAD_ENUM.getValue());
        updateCon(conContract, ConOptEventEnum.MODIFY_ENUM.getValue(), CREATE_CONTRACT_DOWNLOAD, user);
    }

    /**
     * 获取支付方式说明
     *
     * @param startDate
     * @param settlementCycle
     * @param monthlyRent
     * @return
     */
    @Override
    public List<String> getPaymentExplanation(Date startDate,
                                              Date endDate,
                                              Integer settlementCycle,
                                              Integer monthlyRent,
                                              Integer totalMonth) {
        Date start;
        Date end;
        String startDateStr;
        String endDateStr;
        Object[] arguments;
        List<String> paymentExplanation = new ArrayList<>();
        Integer cycle = downloadServiceImpl.getCycleFromType(settlementCycle);
        Integer cycleRent = null;
        Integer leftRent = null;
        int row = 1;
        int left = 0;
        if (null != cycle && 0 != cycle) {
            row = totalMonth / cycle;
            left = totalMonth % cycle;
            cycleRent = monthlyRent * cycle;
            leftRent = monthlyRent * left;
        }
        start = startDate;
        for (int i = 0; i < row; i++) {
            end = DateUtils.addDays(DateUtils.addMonths(start, cycle), -1);
            startDateStr = DateUtils.formatDate(start);
            endDateStr = DateUtils.formatDate(end);
            arguments = new Object[]{startDateStr, startDateStr, endDateStr, cycleRent};
            paymentExplanation.add(PropertyUtils.getSysVal("text.payment.explanation", arguments));
            start = DateUtils.addDays(end, 1);
        }
        if (left != 0) {
            startDateStr = DateUtils.formatDate(start);
            endDateStr = DateUtils.formatDate(endDate);
            arguments = new Object[]{startDateStr, startDateStr, endDateStr, leftRent};
            paymentExplanation.add(PropertyUtils.getSysVal("text.payment.explanation", arguments));
        }
        return paymentExplanation;
    }

    /**
     * 获取支付方式说明直接返回结果到页面
     *
     * @param contractId
     * @param monthlyRent
     * @param settlement
     * @return
     */
    @Override
    public List<String> getPaymentExplanationForView(String contractId,
                                                     Integer monthlyRent,
                                                     Integer settlement) {
        ConContract conContract = getContractByCid(contractId);
        Date startDate = conContract.getStartTime();
        Date endDate = conContract.getEndTime();
        Integer totalMonth = conContract.getLeaseTermYear() * 12 + conContract.getLeaseTermMonth();
        return getPaymentExplanation(startDate, endDate, settlement, monthlyRent, totalMonth);
    }

    /**
     * 获取合同结束日期
     *
     * @param startDateValue
     * @param years
     * @param months
     * @return
     */
    @Override
    public Date getContractEndDate(Long startDateValue,
                                   String years,
                                   String months) {
        int totalMonths = (Integer.valueOf(years)) * 12 + Integer.valueOf(months);
        Date startDate = new Date(startDateValue);
        // 起始日期（号）
        String dayStart = DateUtils.formatDate(startDate).substring(8);
        Date endDate = DateUtils.addMonths(startDate, totalMonths);
        // 结束日期（号）
        String dayEnd = DateUtils.formatDate(endDate).substring(8);
        if (StringUtils.isNotEmpty(dayStart)
                && StringUtils.isNotEmpty(dayEnd)
                && dayStart.compareTo(dayEnd) > 0) {
            // 结束日期为小月月底的时候 取最后一天为结束日期
            return endDate;
        }
        return DateUtils.addDays(endDate, -1);
    }

    /**
     * 设定合同状态
     *
     * @param status
     * @param user
     */
    @Override
    public void setStatus(int status,
                          String contractId,
                          UsrUser user) {
        ConContract conContract = getContractByCid(contractId);
        Integer version = conContract.getVersion();
        conContract = new ConContract();
        conContract.setStatus((short) status);
        conContract.setId(contractId);
        conContract.setVersion(version + 1);
        conContract.preUpdate(user);
        conContractMapper.updateByPrimaryKeySelective(conContract);
        saveLog(contractId,
                conContract.getStatus().intValue(),
                ConOptEventEnum.MODIFY_ENUM.getValue(),
                CREATE_CONTRACT_UPLOAD, user);
    }

    /**
     * 非事物处理合同保存(出租方)
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    private void saveLessor(ConContract conContract,
                            UsrUser user) {//throws ApplicationException {
        sysPersonList = sysPersonServiceImpl
                .getSyspersonsByParams(conContract.getLessorIdNo(), conContract.getLessorIdType(), conContract.getLessorName());
        if (null != sysPersonList && sysPersonList.size() == 1) {
            conContract.setLessorPid(sysPersonList.get(0).getId());
        } else {
            // 保存证件数据，使用户登录时可以看到签过的合同
            sysPerson = new SysPerson();
            sysPerson.setName(conContract.getLessorName());
            sysPerson.setIdType(conContract.getLessorIdType());
            sysPerson.setIdNo(conContract.getLessorIdNo());
            sysPerson.setAddress(conContract.getLessorAddress());
            sysPerson.setContactWay(conContract.getLessorMobile());
            sysPerson.setStatus(YesNoEnum.YES_ENUM.getValue());
            sysPerson.setCertTime(DateUtils.getSynchTime());
            conContract.setLessorPid(sysPersonServiceImpl.save(sysPerson, user));
        }
//        else {
//            Object[] arguments = {"出租方"};
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0134", arguments));
//        }
    }

    /**
     * 非事物处理合同保存(承租方)
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    private void saveLessee(ConContract conContract,
                            UsrUser user) {//throws ApplicationException {
        sysPersonList = sysPersonServiceImpl
                .getSyspersonsByParams(conContract.getLesseeIdNo(), conContract.getLesseeIdType(), conContract.getLesseeName());
        if (null != sysPersonList && sysPersonList.size() == 1) {
            conContract.setLesseePid(sysPersonList.get(0).getId());
        } else {
            // 保存证件数据，使用户登录时可以看到签过的合同
            sysPerson = new SysPerson();
            sysPerson.setName(conContract.getLesseeName());
            sysPerson.setIdType(conContract.getLesseeIdType());
            sysPerson.setIdNo(conContract.getLesseeIdNo());
            sysPerson.setAddress(conContract.getLesseeAddress());
            sysPerson.setContactWay(conContract.getLesseeMobile());
            sysPerson.setStatus(YesNoEnum.YES_ENUM.getValue());
            sysPerson.setCertTime(DateUtils.getSynchTime());
            conContract.setLesseePid(sysPersonServiceImpl.save(sysPerson, user));
        }
//        else {
//            Object[] arguments = {"承租方"};
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0134", arguments));
//        }
    }

    /**
     * 非事物处理合同保存(第一步的其他信息)
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    private void saveElse(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        // 添加
        if (conContract.getIsNewRecord()) {
            conContract.setNo(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_CONTRACT));
            conContract.setStatus((short) ConStatusEnum.CREATING_ENUM.getValue());
            conContract.setSource(ConSourceEnum.ZFGJ_ENUM.getValue());
            conContract.preInsert(user);
            conContractMapper.insertSelective(conContract);
            saveLog(conContract.getId(), 0, ConOptEventEnum.CREATE_ENUM.getValue(), CREATE_CONTRACT_STEP1, user);
        } else {
            //修改
            updateCon(conContract, ConOptEventEnum.MODIFY_ENUM.getValue(), CREATE_CONTRACT_STEP1, user);
        }
    }

    /**
     * 保存合同log
     *
     * @param cid
     * @param oldStatus
     * @param optEvent
     * @param dcrp
     * @param user
     * @return
     * @throws ApplicationException
     */
    private String saveLog(String cid,
                           Integer oldStatus,
                           Integer optEvent,
                           String dcrp,
                           UsrUser user) {
        ConContract conContract = conContractMapper.selectByPrimaryKey(cid);
        ConContractLog conContractLog = new ConContractLog();
        ConversionUtils.reflectionAttr(conContract, conContractLog);
        conContractLog.setConStatusBefore(oldStatus.shortValue());
        conContractLog.setConStatusCurr(conContract.getStatus().shortValue());
        conContractLog.setOperator(user.getId());
        conContractLog.setOptEvent(optEvent);
        conContractLog.setOptTime(DateUtils.getSynchTime());
        conContractLog.setDcrp(dcrp);
        conContractLog.setPindex(null);
        conContractLog.setCid(cid);
        conContractLog.preInsert(user);
        conContractLogMapper.insertSelective(conContractLog);
        return conContractLog.getId();
    }

    /**
     * 修改合同
     *
     * @param conContract
     * @param event
     * @param dcrp
     * @param user
     * @return
     * @throws ApplicationException
     */
    private String updateCon(ConContract conContract,
                             int event,
                             String dcrp,
                             UsrUser user) throws ApplicationException {
        String contractId = conContract.getId();
        // 校验version
        ConContract conDB = checkVersion(contractId, conContract.getVersion());
        conContract.setVersion(conDB.getVersion() + 1);
        conContract.preUpdate(user);
        conContractMapper.updateByPrimaryKeySelective(conContract);
        return saveLog(contractId, conDB.getStatus().intValue(), event, dcrp, user);
    }

    /**
     * 检验版本
     *
     * @param contractId
     * @param oldVersion
     * @return
     * @throws ApplicationException
     */
    private ConContract checkVersion(String contractId,
                                     Integer oldVersion) throws ApplicationException {
        ConContract conContract = null;
        if (oldVersion != null) {
            conContract = conContractMapper.selectByPrimaryKey(contractId);
            if (oldVersion.intValue() != conContract.getVersion()) {
                conContract = null;
            }
        }
        if (conContract == null) {
            // 数据已被更新，请刷新页面再进行操作！
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
        }
        return conContract;
    }

    /**
     * 添加随行子女
     * @param houseUserChildrens
     * @param contractId
     * @param conLogId
     * @param user
     */
    private void saveHouseChildren(List<ConHouseUserChildren> houseUserChildrens, String contractId, String conLogId, UsrUser user) {
        for (ConHouseUserChildren conHouseUserChildren : houseUserChildrens){
            conHouseUserChildren.preInsert(user);
            conHouseUserChildren.setCid(contractId);
            conHouseUserChildrenMapper.insertSelective(conHouseUserChildren);
            savesHouseChildrenLog(conHouseUserChildren,conLogId,user);
        }
    }
    /**
     * 保存随行子女信息log
     * @param conHouseUserChildren
     * @param conLogId
     * @param user
     */
    private void savesHouseChildrenLog(ConHouseUserChildren conHouseUserChildren, String conLogId, UsrUser user) {
        ConHouseUserChildrenLog childrenLog = new ConHouseUserChildrenLog();
        childrenLog.preInsert(user);
        childrenLog.setCid(conHouseUserChildren.getCid());
        childrenLog.setConLogId(conLogId);
        childrenLog.setChildId(conHouseUserChildren.getId());
        childrenLog.setName(conHouseUserChildren.getName());
        childrenLog.setGender(conHouseUserChildren.getGender());
        childrenLog.setIdNo(conHouseUserChildren.getIdNo());
        childrenLog.setBirthday(conHouseUserChildren.getBirthday());
        conHouseUserChildrenLogMapper.insertSelective(childrenLog);
    }

    /**
     * 删除随行子女
     * @param contractId
     */
    private void deleteHouseChildren(String contractId) {
        ConHouseUserChildren conHouseUserChildren = new ConHouseUserChildren();
        conHouseUserChildren.setDeleteFlg((short)YesNoEnum.YES_ENUM.getValue());
        ConHouseUserChildrenExample example = new ConHouseUserChildrenExample();
        ConHouseUserChildrenExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short)YesNoEnum.NO_ENUM.getValue());
        criteria.andCidEqualTo(contractId);
        conHouseUserChildrenMapper.updateByExampleSelective(conHouseUserChildren,example);

    }
    /**
     * 删除实际使用者
     *
     * @param cid
     */
    private void deleteHouseUser(String cid) {
        ConHouseUser houUser = new ConHouseUser();
        houUser.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        ConHouseUserExample example = new ConHouseUserExample();
        ConHouseUserExample.Criteria cri = example.createCriteria();
        cri.andCidEqualTo(cid);
        cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        conHouseUserMapper.updateByExampleSelective(houUser, example);
    }

    /**
     * 保存实际使用者
     *
     * @param houUserList
     * @param cid
     * @param conLogId
     * @param user
     * @throws ApplicationException
     */
    private void saveHouseUser(List<ConHouseUser> houUserList,
                               String cid,
                               String conLogId,
                               UsrUser user) throws ApplicationException {
        if (houUserList != null) {
            for (ConHouseUser houUser : houUserList) {
                houUser.setCid(cid);
                houUser.preInsert(user);
                conHouseUserMapper.insertSelective(houUser);
                saveHouseUserLog(houUser, conLogId, user);
            }
        }
    }

    /**
     * 保存实际使用者log
     *
     * @param houUser
     * @param conLogId
     * @param user
     * @throws ApplicationException
     */
    private void saveHouseUserLog(ConHouseUser houUser,
                                  String conLogId,
                                  UsrUser user) throws ApplicationException {
        ConHouseUserLog houUserLog = new ConHouseUserLog();
        houUserLog.preInsert(user);
        houUserLog.setCid(houUser.getCid());
        houUserLog.setConLogId(conLogId);
        houUserLog.setCoPid(houUser.getPid());
        houUserLog.setIdNo(houUser.getIdNo());
        houUserLog.setIdType(houUser.getIdType());
        houUserLog.setMobile(houUser.getMobile());
        conHouseUserLogMapper.insertSelective(houUserLog);
    }

    /**
     * 删除房屋清单列表
     *
     * @param cid
     * @throws ApplicationException
     */
    private void deleteHouseItems(String cid) throws ApplicationException {
        ConHouseItems houItems = new ConHouseItems();
        houItems.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        ConHouseItemsExample example = new ConHouseItemsExample();
        ConHouseItemsExample.Criteria cri = example.createCriteria();
        cri.andCidEqualTo(cid);
        cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        houseItemsMapper.updateByExampleSelective(houItems, example);
    }

    /**
     * 保存房屋清单列表
     *
     * @param houItemsList
     * @param cid
     * @param user
     * @throws ApplicationException
     */
    private void saveHouseItems(List<ConHouseItems> houItemsList,
                                String cid,
                                String conLogId,
                                UsrUser user) throws ApplicationException {
        if (houItemsList != null) {
            for (ConHouseItems houItems : houItemsList) {
                if (StringUtils.isNotEmpty(houItems.getItem())
                        && StringUtils.isNotEmpty(houItems.getBrand())
                        && houItems.getPrice().doubleValue() != 0
                        && houItems.getPrice() != null
                        && houItems.getNum() != null
                        && houItems.getNum() != 0
                        && houItems.getCompenAmount() != null
                        && houItems.getCompenAmount().doubleValue() != 0) {
                    houItems.setCid(cid);
                    houItems.preInsert(user);
                    houseItemsMapper.insertSelective(houItems);
                    saveHouseItemsLog(houItems, conLogId, user);
                }
            }
        }
    }

    /**
     * 保存房屋清单列表log
     *
     * @param houItems
     * @param conLogId
     * @param user
     * @throws ApplicationException
     */
    private void saveHouseItemsLog(ConHouseItems houItems,
                                   String conLogId,
                                   UsrUser user) throws ApplicationException {
        ConHouseItemsLog houItemsLog = new ConHouseItemsLog();
        houItemsLog.preInsert(user);
        houItemsLog.setBrand(houItems.getBrand());
        houItemsLog.setCid(houItems.getCid());
        houItemsLog.setComment(houItems.getComment());
        houItemsLog.setCompenAmount(houItems.getCompenAmount());
        houItemsLog.setConLogId(conLogId);
        houItemsLog.setItem(houItems.getItem());
        houseItemsLogMapper.insertSelective(houItemsLog);
    }

    /**
     * 删除其他费用列表
     *
     * @param cid
     * @throws ApplicationException
     */
    private void deleteOtherItems(String cid) throws ApplicationException {
        ConOtherCosts costs = new ConOtherCosts();
        costs.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        ConOtherCostsExample example = new ConOtherCostsExample();
        ConOtherCostsExample.Criteria cri = example.createCriteria();
        cri.andCidEqualTo(cid);
        cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        conOtherCostsMapper.updateByExampleSelective(costs, example);
    }

    /**
     * 保存其他费用列表
     *
     * @param conOtherCostsList
     * @param cid
     * @param user
     * @throws ApplicationException
     */
    private void saveOtherItems(List<ConOtherCosts> conOtherCostsList,
                                String cid,
                                UsrUser user) throws ApplicationException {
        if (conOtherCostsList != null) {
            for (ConOtherCosts costs : conOtherCostsList) {
                if (StringUtils.isNotEmpty(costs.getItem())
                        && StringUtils.isNotEmpty(costs.getUnit())
                        && costs.getPrice().doubleValue() != 0
                        && costs.getPrice() != null
                        && costs.getStime() != null
                        && costs.getScount() != null) {
                    costs.setCid(cid);
                    costs.preInsert(user);
                    conOtherCostsMapper.insertSelective(costs);
                }
            }
        }
    }
}
