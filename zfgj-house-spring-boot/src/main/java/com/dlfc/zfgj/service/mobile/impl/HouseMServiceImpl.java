package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.dto.mobile.AddHouseLeaseMDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.*;
import com.dlfc.zfgj.service.mobile.AgtMService;
import com.dlfc.zfgj.service.mobile.HouseMService;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import com.dlfc.zfgj.service.mobile.SysParamMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
@Transactional
@Service("hMServiceImpl")
public class HouseMServiceImpl implements HouseMService {

    @Autowired
    @Qualifier("aMServiceImpl")
    private AgtMService agtService;
    @Autowired
    private UsrHouCollectionMapper usrHouCollectionMapper;

    @Autowired
    private HouLeaseInfoMapper houLeaseInfoV2Mapper;

    @Autowired
    private SysInfoAttrMService sysInfoAttrMService;

    @Autowired
    private HouLeaseInfoLogMapper houLeaseInfoLogMapper;

    @Autowired
    @Qualifier("sysParamService")
    private SysParamMService sysParamMService;

    /**
     * 附件mapper
     */
    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;

    /**
     * 查询所有房源
     *
     * @return
     */
    @Override
    public List<HouLeaseInfo> findAll(String releaseStatus,
                                      String collect,
                                      String CreateTime,
                                      UsrUser usrUser,
                                      int beginPos,
                                      int count) {
        HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = houLeaseInfoExample.createCriteria();
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            criteria.andEidEqualTo(agtEmpInfo.getId());
        } else {
            return null;
        }
        searchParameter(houLeaseInfoExample, criteria, releaseStatus, collect, CreateTime, usrUser, beginPos, count);
        return houLeaseInfoV2Mapper.selectByExample(houLeaseInfoExample);
    }

    /**
     * 查询房源数量
     * @param releaseStatus
     *              发布状态
     * @param collect
     *              收藏状态
     * @param CreateTime
     *              创建时间
     * @param usrUser
     *              创建人
     * @return
     */
    @Override
    public String findCount(String releaseStatus, String collect, String CreateTime, UsrUser usrUser) {
        int count;
        HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = houLeaseInfoExample.createCriteria();
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            criteria.andEidEqualTo(agtEmpInfo.getId());
        } else {
            return "0";
        }
        searchParameter(houLeaseInfoExample, criteria, releaseStatus, collect, CreateTime, usrUser, 0, 0);
        count = houLeaseInfoV2Mapper.countByExample(houLeaseInfoExample);
        return String.valueOf(count);
    }
//    @Override
//    public HouHouseInfo findOneByPrimaryKey(String key) throws CustomRuntimeException {
//        HouHouseInfo houHouseInfo = houHouseInfoMapper.findHouOne(key);
//        if (houHouseInfo == null) {
//            throw new CustomRuntimeException("", "");
//        }
//        return houHouseInfo;
//    }

    /**
     * 根据出租信息编号查找
     *
     * @param no 出租信息编号
     * @return
     */

    @Override
    public List<HouLeaseInfo> findByNo(String no) {
        HouLeaseInfoExample houHouseInfoExample = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = houHouseInfoExample.createCriteria();
        criteria.andLnoEqualTo(no);
        criteria.andDeleteFlgEqualTo((short) 0);
        List<HouLeaseInfo> houHouseInfos = houLeaseInfoV2Mapper.selectByExample(houHouseInfoExample);
        if (houHouseInfos != null && houHouseInfos.size() > 0) {
            return houHouseInfos;
        }
        return null;

    }


    /**
     * 检索部门房源
     *
     * @return
     */
    //TODO 查询条件可能会需要重改
    @Override
    public List<HouLeaseInfo> getAllDepartmentalHouses(String releaseStatus,
                                                       String collect,
                                                       String CreateTime,
                                                       UsrUser usrUser,
                                                       int beginPos,
                                                       int count) {
        // 查询经纪人所在的部门ID
        String officeId;
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            officeId = agtEmpInfo.getOfficeId();
        } else {
            return null;
        }
        List<HouLeaseInfo> houseInfos = new ArrayList<>();
        if (StringUtils.isNotEmpty(officeId)) {
            // 查询部门合同列表
            HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
            HouLeaseInfoExample.Criteria criteria = houLeaseInfoExample.createCriteria();
            searchParameter(houLeaseInfoExample, criteria, releaseStatus, collect, CreateTime, usrUser,beginPos,count);
            criteria.andDeleteFlgEqualTo((short) 0);
            // 查询所属部门下所有经纪人UID
            List<String> empUidList = agtService.getEmpIdListByOfficeId(officeId);
            if (null != empUidList && empUidList.size() > 0) {
                criteria.andCreateUserIn(empUidList);
            }
            houseInfos = houLeaseInfoV2Mapper.selectByExample(houLeaseInfoExample);
        }
        return houseInfos;
    }

    /**
     * 删除出租信息
     *
     * @param hid 房源信息id
     */
    @Override
    public int delHouse(String hid) throws ApplicationException {
        if (StringUtils.isEmpty(hid)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0005"));
        }
        HouLeaseInfo houseInfo = new HouLeaseInfo();
        houseInfo.setId(hid);
        houseInfo.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        int i = houLeaseInfoV2Mapper.updateByPrimaryKeySelective(houseInfo);
        // 删除（更新）收藏表里的数据
        calCollection(hid);
        return i;

    }

    /**
     * 搜索条件
     *
     * @param houLeaseInfoExample
     * @param releaseStatus
     * @param collect
     * @param createTime
     * @param user
     * @return
     */
    public void searchParameter(HouLeaseInfoExample houLeaseInfoExample,
                                HouLeaseInfoExample.Criteria criteria,
                                String releaseStatus,
                                String collect,
                                String createTime,
                                UsrUser user,
                                int beginPos,
                                int count) {
        criteria.andDeleteFlgEqualTo((short) 0);
        if (StringUtils.isNotBlank(releaseStatus)) {
            //查询出租信息发布状态 0代表 未上架 ; 1代表 上架
            if ("0".equals(releaseStatus)) {
                //criteria.andReleaseStatusEqualTo(HouseReleaseStatusEnum.NO_ENUM.getValue());
               //TODO 包含未发布及强制下架的出租信息
                criteria.andReleaseStatusNotEqualTo(HouseReleaseStatusEnum.YES_ENUM.getValue());
            } else if ("1".equals(releaseStatus)) {
                criteria.andReleaseStatusEqualTo(HouseReleaseStatusEnum.YES_ENUM.getValue());
            }
        }
        if (StringUtils.isNotBlank(collect)) {
            List<String> list = this.findAllByUsrHouCollection(user);
            //查询出租信息收藏状态 0代表 有收藏；1代表 未收藏
            if ("0".equals(collect)) {
                criteria.andIdIn(list);
            } else if ("1".equals(collect)) {
                criteria.andIdNotIn(list);
            }
        }
        if(count != 0){
            if (StringUtils.isNotBlank(createTime)) {
                if ("0".equals(createTime)) {
                    houLeaseInfoExample.setOrderByClause(" CREATE_TIME ASC limit " + beginPos +"," + count );
                } else {
                    houLeaseInfoExample.setOrderByClause(" CREATE_TIME DESC limit "+ beginPos +"," + count);
                }
            } else {
                houLeaseInfoExample.setOrderByClause(" FRESH_TIME DESC limit "+ beginPos +"," + count);
            }
        }else{

            if (StringUtils.isNotBlank(createTime)) {
                if ("0".equals(createTime)) {
                    houLeaseInfoExample.setOrderByClause(" CREATE_TIME ASC " );
                } else {
                    houLeaseInfoExample.setOrderByClause(" CREATE_TIME DESC ");
                }
            } else {
                houLeaseInfoExample.setOrderByClause(" FRESH_TIME DESC ");
            }
        }
    }
    /**
     * 查找所有收藏信息的hid
     *
     * @param user
     */
    private List<String> findAllByUsrHouCollection(UsrUser user) {
        UsrHouCollectionExample example = new UsrHouCollectionExample();
        UsrHouCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andUidEqualTo(user.getId());
        List<String> array = new ArrayList<>();
        List<UsrHouCollection> list = usrHouCollectionMapper.selectByExample(example);
        for (UsrHouCollection collection1 : list) {
            array.add(collection1.getHid());
        }
        return array;
    }
    /**
     * 收藏出租信息
     *
     * @param uid userId
     * @return 收藏的房源Id
     */
    public List<String> findByHid(String uid) {
        UsrHouCollectionExample collectionExample = new UsrHouCollectionExample();
        UsrHouCollectionExample.Criteria criteria = collectionExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<UsrHouCollection> list = usrHouCollectionMapper.selectByExample(collectionExample);
        List<String> slist = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (UsrHouCollection collection : list) {
                String hid = collection.getHid();
                slist.add(hid);
            }
        }
        return slist;
    }

    /**
     * 添加收藏
     *
     * @param hid 出租信息Hid
     * @return 1成功0失败
     */
    @Override
    public int saveCollection(String hid) {
        UsrHouCollection collection = new UsrHouCollection();
        collection.setId(IdGen.uuid());
        collection.setUid("");
        collection.setHid(hid);
        collection.setCreateTime(DateUtils.getSynchTime());
        return usrHouCollectionMapper.insertSelective(collection);
    }

    /**
     * 取消收藏
     *
     * @param hid
     * @return
     */
    @Override
    public int calCollection(String hid) {
        UsrHouCollectionExample collection = new UsrHouCollectionExample();
        UsrHouCollectionExample.Criteria criteria = collection.createCriteria();
        criteria.andHidEqualTo(hid);
        return usrHouCollectionMapper.deleteByExample(collection);
    }

    /**
     * 更新出租信息并插入附件表
     *
     * @param houLeaseInfo 出租信息对象
     * @return
     */
    @Override
    public int updateHouse(HouLeaseInfo houLeaseInfo, AddHouseLeaseMDTO addHouseLeaseMDTO,
                              UsrUser usrUser) {
        int success = 0;
        houLeaseInfo.preUpdate(usrUser);
        houLeaseInfo.setFreshTime(DateUtils.getSynchTime());
        houLeaseInfo.setReleaseTime(DateUtils.getSynchTime());
        houLeaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
        houLeaseInfo.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
        houLeaseInfoV2Mapper.updateByPrimaryKeySelective(houLeaseInfo);
        if ( this.deleteByLid(houLeaseInfo.getId(),usrUser) == 0 && this.updateByLid(addHouseLeaseMDTO,houLeaseInfo) == 0){
            success = this.saveLog(houLeaseInfo, usrUser, "编辑出租信息", HouLeaseOptEventEnum.EDIT_ENUM.getValue());
        }
        return success;
    }

    /**
     *  编辑出租信息之后更新附件表
     * @param lid 出租信息id
     * @param
     * @return 成功=0   失败=1
     */
    private int deleteByLid(String lid,UsrUser user) {
        int success = 0;
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andDeleteFlgEqualTo((short) 0);
        List<SysInfoAtt> sysInfoAtts =sysInfoAttMapper.selectByExample(sysInfoAttExample);
        for (SysInfoAtt sysInfoAtt : sysInfoAtts){
            sysInfoAtt.preUpdate(user);
            sysInfoAtt.setDeleteFlg((short)1);
            if (sysInfoAttMapper.updateByPrimaryKeySelective(sysInfoAtt) == 0){
               return 1;
            }
        }
      return success;

    }


    /**
     * 出租信息上架
     */
    @Override
    public int putaway(String id, UsrUser usrUser) {
        HouLeaseInfo HouHouseInfo = new HouLeaseInfo();
        HouHouseInfo.setId(id);
        HouHouseInfo.preUpdate(usrUser);
        HouHouseInfo.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
        HouHouseInfo.setFreshTime(DateUtils.getSynchTime());
        HouHouseInfo.setReleaseTime(DateUtils.getSynchTime());
        return houLeaseInfoV2Mapper.updateByPrimaryKeySelective(HouHouseInfo);
    }

    /**
     * 出租信息下架
     */
    @Override
    public int soldOut(String id, UsrUser usrUser) {
        HouLeaseInfo HouHouseInfo = new HouLeaseInfo();
        HouHouseInfo.setId(id);
        HouHouseInfo.preUpdate(usrUser);
        HouHouseInfo.setReleaseStatus(HouseReleaseStatusEnum.NO_ENUM.getValue());
        return houLeaseInfoV2Mapper.updateByPrimaryKeySelective(HouHouseInfo);
    }

    /**
     * 保存出租信息
     *
     * @param houLeaseInfoV2
     * @param addHouseLeaseMDTO
     * @return
     */
    @Override
    public int saveHouse(HouLeaseInfo houLeaseInfoV2, AddHouseLeaseMDTO addHouseLeaseMDTO,UsrUser usrUser) {
        int success = 0;
        houLeaseInfoV2.setId(IdGen.uuid());
        houLeaseInfoV2.setLno(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_LEASE));
        //新发布的出租信息直接进入审核中
        houLeaseInfoV2.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
        houLeaseInfoV2.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
        //出租信息从哪个系统创建的来源
        houLeaseInfoV2.setSourceType(HouseSourceTypeEnum.AGENT_ENUM.getValue());
        houLeaseInfoV2.setFreshTime(DateUtils.getSynchTime());
        houLeaseInfoV2.setReleaseTime(DateUtils.getSynchTime());

        houLeaseInfoV2.preInsert(usrUser);
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            houLeaseInfoV2.setEid(agtEmpInfo.getId());
        }
        this.updateByLid(addHouseLeaseMDTO,houLeaseInfoV2);//更新附件表
        if (houLeaseInfoV2Mapper.insertSelective(houLeaseInfoV2) > 0){
           success = this.saveLog(houLeaseInfoV2, usrUser, "新建出租信息", HouLeaseOptEventEnum.NEW_ENUM.getValue());
        }
        return success;
    }
    /**
     * 查询lid是否为空
     *
     * @param sid 附件表主键id
     * @return lid为空的附件表集合
     */
    private List<SysInfoAtt> findByLid(String sid) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andIdEqualTo(sid);
        criteria.andLidIsNull();
        criteria.andDeleteFlgEqualTo((short) 0);
        return sysInfoAttMapper.selectByExample(sysInfoAttExample);
    }
    /**
     * 更新刷新时间
     *
     * @param id 出租信息主键id
     * @return
     */
    @Override
    public int updateHouseFreshTime(String id, UsrUser usrUser) {
        if (StringUtils.isNotBlank(id)) {
            HouLeaseInfo houLeaseInfo = houLeaseInfoV2Mapper.selectByPrimaryKey(id);
            if (checkFresh(houLeaseInfo)) {
                houLeaseInfo = new HouLeaseInfo();
                houLeaseInfo.setId(id);
                houLeaseInfo.preUpdate(usrUser);
                houLeaseInfo.setFreshTime(DateUtils.getSynchTime());
                houLeaseInfo.setReleaseTime(DateUtils.getSynchTime());
                return houLeaseInfoV2Mapper.updateByPrimaryKeySelective(houLeaseInfo);
            }
        }
        return 0;
    }



    /**
     * 新建或编辑出租信息之后跟新附件表
     *
     * @param addHouseLeaseMDTO DTO对象
     * @param houLeaseInfoV2   出租信息对象
     * @return 0失败 !=0 成功
     */
    private int updateByLid(AddHouseLeaseMDTO addHouseLeaseMDTO, HouLeaseInfo houLeaseInfoV2) {
        if (addHouseLeaseMDTO.getSysInfoId() != null && addHouseLeaseMDTO.getSysInfoId().length > 0) {
            //把图片传入附件表
            for (int i= 0;i < addHouseLeaseMDTO.getSysInfoId().length;i++) {
                //查询附件表LID是否为空，如果是空证明是新插入的图片，然后给绑定出租信息的主键ID
                List<SysInfoAtt> list = this.findByLid(addHouseLeaseMDTO.getSysInfoId()[i]);
                if (list != null && list.size() > 0) {
                    for (SysInfoAtt SysInfoAtt : list) {
                        if (houLeaseInfoV2 != null) {
                            SysInfoAtt.setLid(houLeaseInfoV2.getId());
                            sysInfoAttrMService.update(SysInfoAtt);
                        }
                    }
                }

            }
        }
        return 0;
    }
    /**
     * 编辑列表页面
     */
    @Override
    public HouLeaseInfo findById(String id){
        HouLeaseInfoExample houLeaseInfoV2Example = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria conContractCriteria = houLeaseInfoV2Example.createCriteria();
        conContractCriteria.andIdEqualTo(id);
        conContractCriteria.andDeleteFlgEqualTo((short)0);
       List<HouLeaseInfo> list = houLeaseInfoV2Mapper.selectByExample(houLeaseInfoV2Example);
       if(list != null && list.size() >0){
           return list.get(0);
       }
        return null;
    }
    /**
     * 判断当前出租信息是否可以刷新
     *
     * @param houLeaseInfo
     * @return
     */
    @Override
    public boolean checkFresh(HouLeaseInfo houLeaseInfo) {
        Integer releaseStatus = houLeaseInfo.getReleaseStatus();
        if (null == releaseStatus
                || HouseReleaseStatusEnum.YES_ENUM.getValue() != releaseStatus.intValue()) {
            return false;
        } else if (sysParamMService.isOn("fresh")) {
            Date freshTime = houLeaseInfo.getFreshTime();
            Date startTime = DateUtils.getStartDate(DateUtils.getSynchTime());
            if (null != freshTime) {
                return freshTime.before(startTime);
            }
            return false;
        }
        return true;
    }
    /**
     * 记录log表
     */

    private int saveLog(HouLeaseInfo houLeaseInfo, UsrUser usrUser, String dcrp, Integer oent) {
        HouLeaseInfoLog houLeaseInfoLog = new HouLeaseInfoLog();
        houLeaseInfoLog.setRentalArea(houLeaseInfo.getRentalArea());
        houLeaseInfoLog.setRent(houLeaseInfo.getRent());
        houLeaseInfoLog.setEid(houLeaseInfo.getEid());
        houLeaseInfoLog.setApartmentLayout(houLeaseInfo.getApartmentLayout());
        houLeaseInfoLog.setAuditStatus(houLeaseInfo.getAuditStatus());
        houLeaseInfoLog.setBuildTime(houLeaseInfo.getBuildTime());
        houLeaseInfoLog.setCheckinTime(houLeaseInfo.getCheckinTime());
        houLeaseInfoLog.setContact(houLeaseInfo.getContact());
        houLeaseInfoLog.setDcrp(dcrp);
        houLeaseInfoLog.setDescription(houLeaseInfo.getDescription());
        houLeaseInfoLog.setDistrict(houLeaseInfo.getDistrict());
        houLeaseInfoLog.setDistrictName(houLeaseInfo.getDistrictName());
        houLeaseInfoLog.setElectric(houLeaseInfo.getElectric());
        houLeaseInfoLog.setFacilities(houLeaseInfo.getFacilities());
        houLeaseInfoLog.setFloor(houLeaseInfo.getFloor());
        houLeaseInfoLog.setFeature(houLeaseInfo.getFeature());
        houLeaseInfoLog.setEnvironment(houLeaseInfo.getEnvironment());
        houLeaseInfoLog.setFreshTime(houLeaseInfo.getFreshTime());
        houLeaseInfoLog.setHouseType(houLeaseInfo.getHouseType());
        houLeaseInfoLog.setLatitude(houLeaseInfo.getLatitude());
        houLeaseInfoLog.setLeaseId(houLeaseInfo.getId());
        houLeaseInfoLog.setLeaseMode(houLeaseInfo.getLeaseMode());
        houLeaseInfoLog.setLno(houLeaseInfo.getLno());
        houLeaseInfoLog.setNo(houLeaseInfo.getNo());
        houLeaseInfoLog.setOnlinePayment(houLeaseInfo.getOnlinePayment());
        houLeaseInfoLog.setOperator(usrUser.getId());
        houLeaseInfoLog.setOptEvent(oent);
        houLeaseInfoLog.setOptTime(DateUtils.getSynchTime());
        houLeaseInfoLog.setOrientation(houLeaseInfo.getOrientation());
        houLeaseInfoLog.setOwnerCost(houLeaseInfo.getOwnerCost());
        houLeaseInfoLog.setOwnerCostItems(houLeaseInfo.getOwnerCostItems());
        houLeaseInfoLog.setPreferential(houLeaseInfo.getPreferential());
        houLeaseInfoLog.setRejectReason(houLeaseInfo.getRejectReason());
        houLeaseInfoLog.setReleaseStatus(houLeaseInfo.getReleaseStatus());
        houLeaseInfoLog.setRejectType(houLeaseInfo.getRejectType());
        houLeaseInfoLog.setRequirement(houLeaseInfo.getRequirement());
        houLeaseInfoLog.setShelfReason(houLeaseInfo.getShelfReason());
        houLeaseInfoLog.setStationid(houLeaseInfo.getStationid());
        houLeaseInfoLog.setRoom(houLeaseInfo.getRoom());
        houLeaseInfoLog.setSourceType(houLeaseInfo.getSourceType());
        houLeaseInfoLog.setTel(houLeaseInfo.getTel());
        houLeaseInfoLog.setTitle(houLeaseInfo.getTitle());
        houLeaseInfoLog.setUid(houLeaseInfo.getUid());
        houLeaseInfoLog.setVillageName(houLeaseInfo.getVillageName());
        houLeaseInfoLog.preInsert(usrUser);
       return houLeaseInfoLogMapper.insertSelective(houLeaseInfoLog);
    }
}
