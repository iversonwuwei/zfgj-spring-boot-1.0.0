package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.HouLeaseInfoLogMapper;
import com.dlfc.zfgj.mapper.HouLeaseInfoMapper;
import com.dlfc.zfgj.mapper.UsrHouCollectionMapper;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.HouseService;
import com.dlfc.zfgj.service.web.SysParamService;
import com.dlfc.zfgj.utils.GetAddressLatAndLonUtils;
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
@Service("hServiceImpl")
public class HouseServiceImpl implements HouseService {

    private List<HouLeaseInfo> houLeaseInfoList;

    @Autowired
    @Qualifier("aServiceImpl")
    private AgtService agtService;
    @Autowired
    private UsrHouCollectionMapper usrHouCollectionMapper;
    @Autowired
    private HouLeaseInfoMapper houLeaseInfoMapper;
    @Autowired
    private HouLeaseInfoLogMapper houLeaseInfoLogMapper;
    @Autowired
    private SysParamService sysParamService;

    /**
     * 查询所有房源
     *
     * @return
     */
    @Override
    public List<HouLeaseInfo> findAll(String releaseStatus,
                                      String collect,
                                      String CreateTime,
                                      UsrUser usrUser) {
        HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = houLeaseInfoExample.createCriteria();
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            criteria.andEidEqualTo(agtEmpInfo.getId());
        } else {
            return null;
        }
        searchParameter(houLeaseInfoExample, criteria, releaseStatus, collect, CreateTime, usrUser);
        return houLeaseInfoMapper.selectByExample(houLeaseInfoExample);
    }


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
        List<HouLeaseInfo> houHouseInfos = houLeaseInfoMapper.selectByExample(houHouseInfoExample);
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
    @Override
    public List<HouLeaseInfo> getAllDepartmentalHouses(String releaseStatus,
                                                       String collect,
                                                       String CreateTime,
                                                       UsrUser usrUser) {
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
            searchParameter(houLeaseInfoExample, criteria, releaseStatus, collect, CreateTime, usrUser);
            criteria.andDeleteFlgEqualTo((short) 0);
            criteria.andUidIsNull();
            // 查询所属部门下所有经纪人UID
            List<String> empUidList = agtService.getEmpIdListByOfficeId(officeId);
            if (null != empUidList && empUidList.size() > 0) {
                criteria.andCreateUserIn(empUidList);
            }
            houseInfos = houLeaseInfoMapper.selectByExample(houLeaseInfoExample);
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
        int i = houLeaseInfoMapper.updateByPrimaryKeySelective(houseInfo);
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
                                UsrUser user) {
        criteria.andDeleteFlgEqualTo((short) 0);
        if (StringUtils.isNotBlank(releaseStatus)) {
            //查询出租信息发布状态 0代表 未上架 ; 1代表 上架
            if ("0".equals(releaseStatus)) {
                criteria.andReleaseStatusEqualTo(HouseReleaseStatusEnum.NO_ENUM.getValue());
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
        if (StringUtils.isNotBlank(createTime)) {
            if ("0".equals(createTime)) {
                houLeaseInfoExample.setOrderByClause(" CREATE_TIME ASC ");
            } else {
                houLeaseInfoExample.setOrderByClause(" CREATE_TIME DESC ");
            }
        } else {
            houLeaseInfoExample.setOrderByClause(" FRESH_TIME DESC ");
        }
    }

    /**
     * 查找收藏里的出租信息的ID(HID)
     *
     * @param uid userId
     * @return 收藏的房源Id
     */
    public List<String> findByHid(String uid) {
        UsrHouCollectionExample collectionExample = new UsrHouCollectionExample();
        UsrHouCollectionExample.Criteria criteria = collectionExample.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andDeleteFlgEqualTo((short)0);
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
    public int saveCollection(String hid, UsrUser usrUser) throws ApplicationException {
        UsrHouCollection collection = new UsrHouCollection();
        collection.preInsert(usrUser);
        collection.setHid(hid);
        collection.setUid(usrUser.getId());
        collection.setCtime(DateUtils.getSynchTime());
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
        UsrHouCollection usrHouCollection = new UsrHouCollection();
        usrHouCollection.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        UsrHouCollectionExample example = new UsrHouCollectionExample();
        UsrHouCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andHidEqualTo(hid);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return usrHouCollectionMapper.updateByExampleSelective(usrHouCollection, example);
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
        return houLeaseInfoMapper.updateByPrimaryKeySelective(HouHouseInfo);
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
        return houLeaseInfoMapper.updateByPrimaryKeySelective(HouHouseInfo);
    }

    /**
     * 保存出租信息
     *
     * @param houLeaseInfo
     * @return
     */
    @Override
    public String saveHouse(HouLeaseInfo houLeaseInfo,
                            UsrUser usrUser) {
        houLeaseInfo.setId(IdGen.uuid());
        houLeaseInfo.setLno(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_LEASE));
        //新发布的出租信息直接进入审核中
        houLeaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
        houLeaseInfo.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
        //出租信息从哪个系统创建的来源
        houLeaseInfo.setSourceType(HouseSourceTypeEnum.AGENT_ENUM.getValue());
        houLeaseInfo.setFreshTime(DateUtils.getSynchTime());
        houLeaseInfo.setReleaseTime(DateUtils.getSynchTime());
        try {
            String location = GetAddressLatAndLonUtils.getAddressLatAndLon("大连市" + houLeaseInfo.getDistrictName() + houLeaseInfo.getVillageName());
            String a[] = location.split(",");
            if (a.length == 1) {
                houLeaseInfo.setLongitude(a[0]);
            } else {
                houLeaseInfo.setLongitude(a[0]);
                houLeaseInfo.setLatitude(a[1]);
            }
        } catch (Exception e) {
            houLeaseInfo.setLongitude(null);
            houLeaseInfo.setLatitude(null);
        }
        houLeaseInfo.preInsert(usrUser);
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(usrUser.getId());
        if (agtEmpInfo != null) {
            houLeaseInfo.setEid(agtEmpInfo.getId());
        }
        houLeaseInfoMapper.insertSelective(houLeaseInfo);
        this.saveLog(houLeaseInfo, usrUser, "新建出租信息", HouLeaseOptEventEnum.NEW_ENUM.getValue());
        return houLeaseInfo.getId();
    }


    /**
     * 编辑出租查询页面
     */
    @Override
    public HouLeaseInfo findById(String id) {
        HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria conContractCriteria = houLeaseInfoExample.createCriteria();
        conContractCriteria.andIdEqualTo(id);
        conContractCriteria.andDeleteFlgEqualTo((short) 0);
        List<HouLeaseInfo> list = houLeaseInfoMapper.selectByExample(houLeaseInfoExample);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新出租信息并插入附件表
     *
     * @param houLeaseInfo 出租信息对象
     * @return
     */
    @Override
    public String updateHouse(HouLeaseInfo houLeaseInfo,
                              UsrUser usrUser) {
        houLeaseInfo.preUpdate(usrUser);
        houLeaseInfo.setFreshTime(DateUtils.getSynchTime());
        houLeaseInfo.setReleaseTime(DateUtils.getSynchTime());
        houLeaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
        houLeaseInfo.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
        houLeaseInfoMapper.updateByPrimaryKeySelective(houLeaseInfo);
        this.saveLog(houLeaseInfo, usrUser, "编辑出租信息", HouLeaseOptEventEnum.EDIT_ENUM.getValue());
        return houLeaseInfo.getId();
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
            HouLeaseInfo houLeaseInfo = houLeaseInfoMapper.selectByPrimaryKey(id);
            if (checkFresh(houLeaseInfo)) {
                houLeaseInfo = new HouLeaseInfo();
                houLeaseInfo.setId(id);
                houLeaseInfo.preUpdate(usrUser);
                houLeaseInfo.setFreshTime(DateUtils.getSynchTime());
                houLeaseInfo.setReleaseTime(DateUtils.getSynchTime());
                return houLeaseInfoMapper.updateByPrimaryKeySelective(houLeaseInfo);
            }
        }
        return 0;
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
        } else if (sysParamService.isOn("fresh")) {
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

    private void saveLog(HouLeaseInfo houLeaseInfo, UsrUser usrUser, String dcrp, Integer oent) {
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
        houLeaseInfoLogMapper.insertSelective(houLeaseInfoLog);
    }

    /**
     * 根据经纪人id获取有效房源
     *
     * @param eid 经纪人ID
     * @return 有效房源数量
     */
    @Override
    public int actHouse(String eid) {
        HouLeaseInfoExample example = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andReleaseStatusEqualTo(HouseReleaseStatusEnum.YES_ENUM.getValue());
        criteria.andEidEqualTo(eid);
        return houLeaseInfoMapper.countByExample(example);
    }

    /**
     * 等待审核
     *
     * @param eid
     * @param status
     * @return
     */
    @Override
    public int countHouByStatus(String eid,
                                int status) {
        HouLeaseInfoExample example = new HouLeaseInfoExample();
        HouLeaseInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEidEqualTo(eid);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andAuditStatusEqualTo(status);
        return houLeaseInfoMapper.countByExample(example);
    }

}
