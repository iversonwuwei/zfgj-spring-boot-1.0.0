package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.*;
import com.dlfc.zfgj.service.mobile.*;
import com.housecenter.dlfc.auth.AuthFacet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/12.
 */

@Slf4j
@Transactional
@Service
public class ContractStepMServiceImpl implements ContractStepMService {

    private static final String CREATE_CONTRACT_STEP1 = "APP新建合同";
    private static final String CANCEL_CONTRACT = "取消合同";
    private static final String UPDATE_CONTRACT = "APP更新合同";
    private static final String CREATE_LOG_DRCP = "APP上传个人信息";

    private SysPerson sysPerson;
    private List<SysPerson> sysPersonList;

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private SysPersonMapper sysPersonMapper;
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
    private UsrUserMService usrUserMServiceImpl;
    @Autowired
    private SysPersonMService sysPersonMServiceImpl;
    @Autowired
    private DownloadMService downloadMServiceImpl;
    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;
    @Autowired
    private SysPersonLogMapper sysPersonLogMapper;
    @Autowired
    private HouHouseInfoMService houHouseInfoMService;
    @Autowired
    private HouCoOwnerTempMService houCoOwnerTempMService;
    @Autowired
    private SysParamMService sysParamMService;
    @Autowired
    private AgtCompInfoMService agtCompInfoMService;
    @Autowired
    private AgtEmpInfoMService agtEmpInfoMService;
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
    public void checkContractId(String contractId) throws ApplicationException {
        if (StringUtils.isEmpty(contractId)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0005"));
        }
    }

//    /**
//     * 逻辑删除合同
//     *
//     * @param contractId
//     */
//    public void cancelContract(String contractId) throws ApplicationException {
//        ConContractV2 conContract = conContractMapper.selectByPrimaryKey(contractId);
//        if (null != conContract
//                && conContract.getStatus() == ConStatusEnum.CREATING_ENUM.getValue()) {
//            ConContractV2 conContractForUpdate = new ConContractV2();
//            conContractForUpdate.setId(contractId);
//            conContractForUpdate.setVersion(conContract.getVersion() + 1);
//            conContractForUpdate.setStatus(ConStatusEnum.CANCEL_ENUM.getValue());
//            //conContractForUpdate.preUpdate();
//            conContractForUpdate.setModifyTime(DateUtils.getSynchTime());//TODO 第一条恢复时删除这条
//            conContractMapper.updateByPrimaryKeySelective(conContractForUpdate);
//            saveLog(contractId, conContract.getStatus(), ConOptEventEnum.CANCEL_ENUM.getValue(), CANCEL_CONTRACT,);
//        } else {
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0007"));
//        }
//    }

    /**
     * 更新合同状态
     *
     * @param contractId
     */
    public void upateContractStatus(String contractId, UsrUser usrUser) throws ApplicationException {
        ConContract conContract = conContractMapper.selectByPrimaryKey(contractId);
        if (null != conContract) {
            ConContract conContractForUpdate = new ConContract();
            conContractForUpdate.preUpdate(usrUser);
            conContractForUpdate.setId(contractId);
            conContractForUpdate.setVersion(conContract.getVersion() + 1);
            conContractForUpdate.setStatus((short) ConStatusEnum.APPROVING_ENUM.getValue());
            conContractMapper.updateByPrimaryKeySelective(conContractForUpdate);
            saveLog(contractId, conContract.getStatus(), ConOptEventEnum.MODIFY_ENUM.getValue(), UPDATE_CONTRACT, usrUser);
        } else {
            throw new ApplicationException("上传合同失败");
        }
    }

    /**
     * 根据ID获取合同详细信息
     *
     * @param cid
     * @return
     */
    public ConContract getContractByCid(String cid) {
        ConContract conContract = new ConContract();
        if (StringUtils.isNotEmpty(cid)) {
            conContract = conContractMapper.selectByPrimaryKey(cid);
        }
        return conContract;
    }

    /**
     * 根据CID获取合同签名地址
     *
     * @param cid
     * @return
     */
    public List<SysInfoAtt> getContractSign(String cid) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(cid);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        sysInfoAttExample.setOrderByClause("PINDEX ASC");
        List<SysInfoAtt> sysInfoAtts = sysInfoAttMapper.selectByExample(sysInfoAttExample);
        return sysInfoAtts;
    }

    /**
     * 身份验证
     *
     * @param id
     * @param idType
     * @param name
     * @param user
     * @return
     */
    public void checkIdentity(String id,
                              Integer idType,
                              String name,
                              String phoneNo,
                              UsrUser user) throws ApplicationException {
        // 验证是否注册
        String perId = StringUtils.EMPTY;
        List<SysPerson> personList = sysPersonMServiceImpl.getSyspersonsByParams(id, idType, name);
        if (personList != null && personList.size() != 0) {
            perId = personList.get(0).getId();
        }
        if (StringUtils.isEmpty(perId)) {
            // 验证ID是否被使用过
            if (sysPersonMServiceImpl.isIdNoBinded(id)) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0009"));
            }
            // 未注册且合法时，先注册
            else if (AuthFacet.authID(name, id)) {
                SysPerson person = new SysPerson();
                person.setName(name);
                person.setIdType(idType);
                person.setIdNo(id);
                person.setStatus(1);
                person.preInsert(user);
                person.setCertTime(DateUtils.getSynchTime());
                sysPersonMapper.insertSelective(person);
                perId = person.getId();
            }
        }
        if (StringUtils.isNotEmpty(perId)) {
            // 手机号码验证
            UsrUser usr = usrUserMServiceImpl.getUserByPerId(perId);
            if (usr != null
                    && !StringUtils.equals(usr.getMobile(), phoneNo)) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0010"));
            }
            // 证件为身份证时 验证是否成年
            if (idType == PersonIdTypeEnum.ID_CARD_ENUM.getValue()) {
                checkAge(perId);
            }
        } else {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0009"));
        }
    }

    /**
     * 验证是否成年
     *
     * @param perId
     * @return
     */
    private void checkAge(String perId) throws ApplicationException {
        SysPerson sysPerson = sysPersonMapper.selectByPrimaryKey(perId);
        if (sysPerson != null
                && StringUtils.isNotEmpty(sysPerson.getIdNo())) {
            boolean ageFlg;
            ageFlg = valuateAge(sysPerson.getIdNo());
            if (!ageFlg) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0111"));
            }
        } else {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0110"));
        }
    }

    /**
     * 合同新建
     *
     * @param conContract
     * @throws ApplicationException
     */
    public void saveStep1(ConContract conContract,
                          UsrUser user) throws ApplicationException {
        if (conContract.getIsNewRecord()) {
            conContract.setEid(user.getEmpId());
        }
        if (conContract.getStages() == null) {
            conContract.setStages(String.valueOf(0));
        }
        if (conContract.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue()) {
            conContract.setLeaseArea(conContract.getBuildingArea());
            conContract.setLeaseDomain("整租");
        }
        conContract.setBeid(user.getEmpId());
        AgtEmpInfo agtEmpInfo = agtEmpInfoMService.getAgtEmpInfoByUserId(user.getId());
        AgtCompInfo agtCompInfo = agtCompInfoMService.getComInfoByComId(agtEmpInfo.getCompanyId());
        if (null != agtCompInfo) {
            conContract.setAgtComName(agtCompInfo.getName());
            conContract.setAgtAddress(agtCompInfo.getAddress());
            conContract.setAgtComTel(agtCompInfo.getTel());
        }
        conContract.setStatus((short) ConStatusEnum.WAIT_UPLOAD_ENUM.getValue());
        saveLessor(conContract, user);
        saveLessee(conContract, user);
        String conLogId = saveElse(conContract, user);
        // 保存实际使用者信息到系统表
        sysPersonMServiceImpl.saveIfNotExistInSysPerson(conContract.getHouseUsers());
        // 保存房屋信息
        saveHouseInfo(conContract, user);
        //判断是否可以赠送保险
//        HouHouseInfo houHouseInfo = houHouseInfoMService.getHouHouseInfoById(conContract.getHid());
//        boolean insuranceFlag = sysParamMService.isOn();
//        if (null != houHouseInfo && insuranceFlag) {
//            if (conContract.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue()
//                    && conContract.getLeaseTermYear() >= 1
//                    && (houHouseInfo.getStructure() == null
//                    || houHouseInfo.getStructure() == StructureEnum.ZHUAN_HUN_ENUM.getValue()
//                    || houHouseInfo.getStructure() == StructureEnum.KUANG_JIYA_ENUM.getValue())) {
//                conContract.setHasInsurance(1);
//            } else {
//                conContract.setHasInsurance(0);
//            }
//        }else {
        conContract.setHasInsurance(0);
//        }
        conContractMapper.updateByPrimaryKeySelective(conContract);
        String contractId = conContract.getId();
        saveHouseUser(conContract.getHouseUsers(), contractId, conLogId, user);
        saveHouseChildren(conContract.getHouseUserChildrens(), contractId,conLogId, user);
        saveHouseItems(conContract.getHouItemsList(), contractId, conLogId, user);
        saveOtherItems(conContract.getConOtherCostsList(), contractId, user);
    }



    /**
     * 讲List转成String
     *
     * @param list
     * @return
     */
    @Override
    public String getStrFromList(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (null != list && list.size() > 0) {
            for (String str : list) {
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
    public List<String> getListFromStr(String str, List<SysCode> sysCodeList) {
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
        Integer cycle = downloadMServiceImpl.getCycleFromType(settlementCycle);
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
    public List<String> getPaymentExplanationForView(String contractId, Integer monthlyRent, Integer settlement) {
        ConContract conContract = getContractByCid(contractId);
        Date startDate = conContract.getStartTime();
        Date endDate = conContract.getEndTime();
        Integer totalMonth = conContract.getLeaseTermYear() * 12 + conContract.getLeaseTermMonth();
        return getPaymentExplanation(startDate, endDate, settlement, monthlyRent, totalMonth);
    }

    /**
     * 非事物处理合同保存(出租方)
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    private void saveLessor(ConContract conContract,
                            UsrUser user) {
        sysPersonList = sysPersonMServiceImpl.getSyspersonsByParams(
                conContract.getLessorIdNo(), conContract.getLessorIdType(), conContract.getLessorName());
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
            conContract.setLessorPid(sysPersonMServiceImpl.save(sysPerson, user));
        }
    }

    /**
     * 非事物处理合同保存(承租方)
     *
     * @param conContract
     * @param user
     * @throws ApplicationException
     */
    private void saveLessee(ConContract conContract,
                            UsrUser user) {
        sysPersonList = sysPersonMServiceImpl
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
            conContract.setLessorPid(sysPersonMServiceImpl.save(sysPerson, user));
        }
    }

    /**
     * @param sysPerson 人员信息的基本数据
     * @param usrUser
     * @return
     */
    private Boolean createsysPersonLog(SysPerson sysPerson,
                                       UsrUser usrUser) {
        Boolean success = false;
        SysPersonLog sysPersonLog = new SysPersonLog();
        sysPersonLog.preInsert(usrUser);
        sysPersonLog.setName(sysPerson.getName());
        sysPersonLog.setIdNo(sysPerson.getIdNo());
        sysPersonLog.setIdType(sysPerson.getIdType());
        sysPersonLog.setStatus(sysPerson.getStatus());
        sysPersonLog.setOptTime(new Date());
        sysPersonLog.setOperator(sysPerson.getUUID());
        sysPersonLog.setOptEvent(0);
        sysPersonLog.setDcrp(CREATE_LOG_DRCP);
        int result = sysPersonLogMapper.insertSelective(sysPersonLog);
        if (result > 0) {
            success = true;
        }
        return success;
    }


    /**
     * 非事物处理合同保存(第一步的其他信息)
     *
     * @param conContract
     * @throws ApplicationException
     */
    private String saveElse(ConContract conContract,
                            UsrUser usrUser) throws ApplicationException {
        conContract.preInsert(usrUser);
        conContract.setNo(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_CONTRACT));
        conContract.setSource(ConSourceEnum.ZFGJ_APP_ENUM.getValue());
        conContractMapper.insertSelective(conContract);
        return saveLog(conContract.getId(), (short) 0, ConOptEventEnum.CREATE_ENUM.getValue(), CREATE_CONTRACT_STEP1, usrUser);
    }

    /**
     * 保存合同log
     *
     * @param cid
     * @param oldStatus
     * @param optEvent
     * @param dcrp
     * @return
     * @throws ApplicationException
     */
    private String saveLog(String cid,
                           short oldStatus,
                           int optEvent,
                           String dcrp,
                           UsrUser usrUser) throws ApplicationException {
        ConContract conContract = conContractMapper.selectByPrimaryKey(cid);
        ConContractLog conContractLog = new ConContractLog();
        ConversionUtils.reflectionAttr(conContract, conContractLog);
        conContractLog.preInsert(usrUser);
        conContractLog.setConStatusBefore(oldStatus);
        conContractLog.setConStatusCurr(conContract.getStatus().shortValue());
        conContractLog.setOperator(usrUser.getId());
        conContractLog.setOptEvent(optEvent);
        conContractLog.setOptTime(DateUtils.getSynchTime());
        conContractLog.setDcrp(dcrp);
        conContractLog.setPindex(null);
        conContractLog.setCid(cid);
        conContractLogMapper.insertSelective(conContractLog);
        return conContractLog.getId();
    }

    /**
     * 保存随行子女信息
     * @param houseUserChildrens
     * @param contractId
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
    private void savesHouseChildrenLog(ConHouseUserChildren conHouseUserChildren , String conLogId, UsrUser user) {
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

    /**
     * 根据身份证号计算年龄
     * 计算方式：18周岁生日的下一天零点之后才算满18周岁
     *
     * @param IDCardNum
     * @return
     * @throws ApplicationException
     */
    private boolean valuateAge(String IDCardNum) throws ApplicationException {
        int year, month, day, idLength = IDCardNum.length();
        if (idLength == 18) {
            year = Integer.parseInt(IDCardNum.substring(6, 10)) + 18;
            month = Integer.parseInt(IDCardNum.substring(10, 12));
            day = Integer.parseInt(IDCardNum.substring(12, 14)) + 1;
        } else if (idLength == 15) {
            year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900 + 18;
            month = Integer.parseInt(IDCardNum.substring(8, 10));
            day = Integer.parseInt(IDCardNum.substring(10, 12)) + 1;
        } else {
            return false;
        }
        try {
            return getYearDiff(year, month, day);
        } catch (ApplicationException e) {
            throw new ApplicationException("解析文件失败");
        }
    }

    /**
     * 判断日期大小
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 布尔值
     * @throws ApplicationException 日期转换失败
     */
    private boolean getYearDiff(int year,
                                int month,
                                int day) throws ApplicationException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sMonth;
        String sDay;
        if (String.valueOf(month).length() == 1) {
            sMonth = "0" + String.valueOf(month);
        } else {
            sMonth = month + "";
        }

        if (String.valueOf(day).length() == 1) {
            sDay = "0" + String.valueOf(day);
        } else {
            sDay = day + "";
        }
        Date update;
        try {
            update = sdf.parse(String.valueOf(year) + sMonth + sDay);
        } catch (ParseException e) {
            this.log.error("ContractController getYearDiff 日期转换失败", e);
            throw new ApplicationException("日期转换失败");
        }
        Date today = new Date();
        return today.after(update);
    }


    /**
     * 保存房屋信息
     *
     * @param conContract
     * @param user
     * @return
     */
    private void saveHouseInfo(ConContract conContract,
                               UsrUser user) {
        String hid;
        HouHouseInfo houHouseInfo;
        String propertyIdNo = conContract.getPropertyIdNo();
        ConContract contractTemp = new ConContract();
        if (StringUtils.isNotEmpty(propertyIdNo)) {
            contractTemp.setPropertyIdNo(propertyIdNo);
            houHouseInfo = houHouseInfoMService.getHouseInfoByContractInput(contractTemp);
        } else {
            contractTemp.setHouseAddr(conContract.getHouseAddr());
            houHouseInfo = houHouseInfoMService.getHouseInfoByContractInput(contractTemp);
        }
        if (null != houHouseInfo && StringUtils.isNotEmpty(houHouseInfo.getId())) {
            hid = houHouseInfo.getId();
            Integer version = houHouseInfo.getVersion();
            // 产权编号或者房源地址相符时，更新房源信息
            houHouseInfo = getHouseFromContract(conContract);
            houHouseInfo.setId(hid);
            houHouseInfo.setVersion(version);
            houHouseInfoMService.updateHouseInfo(houHouseInfo, user);
            // 删除房源原房主
            houCoOwnerTempMService.deleteOldOwners(hid);
        } else {
            // 保存房源信息
            houHouseInfo = getHouseFromContract(conContract);
            hid = houHouseInfoMService.saveHouseInfo(houHouseInfo, user);
        }
        //保存房东信息为房源房主信息
        ConContract contract = getContractByCid(conContract.getId());
        HouCoOwnerTemp houCoOwner = new HouCoOwnerTemp();
        houCoOwner.preInsert(user);
        List<SysPerson> sysPersonList = sysPersonMServiceImpl.getSyspersonsByParams(
                contract.getLessorIdNo(), contract.getLessorIdType(), contract.getLessorName());
        if (null != sysPersonList && sysPersonList.size() == 1) {
            SysPerson sysPerson = sysPersonList.get(0);
            houCoOwner.setCoPid(sysPerson.getId());
            conContract.setHouseOwnerPid(sysPerson.getId());
        }
        houCoOwner.setHid(hid);
        houCoOwnerTempMService.saveHouseOwner(houCoOwner);
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
}
