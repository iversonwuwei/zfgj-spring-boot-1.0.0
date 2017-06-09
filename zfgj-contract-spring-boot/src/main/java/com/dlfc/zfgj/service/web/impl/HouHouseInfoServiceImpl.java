package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.HouOptEventEnum;
import com.dlfc.zfgj.enums.HouseActiveStatusEnum;
import com.dlfc.zfgj.enums.HouseAuditStatusEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.HouHouseInfoLogMapper;
import com.dlfc.zfgj.mapper.HouHouseInfoMapper;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import com.dlfc.zfgj.service.web.HouHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/3/3.
 */

@Service
@Transactional
public class HouHouseInfoServiceImpl implements HouHouseInfoService {

    @Autowired
    private HouHouseInfoMapper houHouseInfoMapper;
    @Autowired
    private AgtEmpInfoService agtEmpInfoService;
    @Autowired
    private HouHouseInfoLogMapper houHouseInfoLogMapper;

    /**
     * 保存房屋信息
     *
     * @param houHouseInfo
     * @param user
     * @return
     */
    @Override
    public String saveHouseInfo(HouHouseInfo houHouseInfo,
                                UsrUser user) {
        houHouseInfo.preInsert(user);
        AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoByUserId(user.getId());
        if (null != agtEmpInfo) {
            houHouseInfo.setEid(agtEmpInfo.getId());
            houHouseInfo.setBeid(agtEmpInfo.getId());
        }
        houHouseInfo.setAuditStatus(HouseAuditStatusEnum.UNAUDITED_ENUM.getValue());
        houHouseInfo.setNo(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_HOUSE));
        houHouseInfo.setActiveStatus(HouseActiveStatusEnum.YES_ENUM.getValue());
        houHouseInfo.setLockStatus(YesNoEnum.NO_ENUM.getValue());
        houHouseInfo.setDeleteFlg((short) YesNoEnum.NO_ENUM.getValue());
        houHouseInfo.setCollectType(YesNoEnum.NO_ENUM.getValue());
        houHouseInfoMapper.insert(houHouseInfo);
        saveHouseInfoLog(houHouseInfo, HouOptEventEnum.CREATE_ENUM.getValue(), user);
        return houHouseInfo.getId();
    }

    /**
     * 更新房屋信息
     *
     * @param houHouseInfo
     * @param user
     */
    @Override
    public void updateHouseInfo(HouHouseInfo houHouseInfo,
                                UsrUser user) {
        houHouseInfo.preUpdate(user);
        houHouseInfo.setVersion(houHouseInfo.getVersion() + 1);
        houHouseInfoMapper.updateByPrimaryKeySelective(houHouseInfo);
        saveHouseInfoLog(houHouseInfo, HouOptEventEnum.MODIFY_ENUM.getValue(), user);
    }

    /**
     * 通过合同创建时的信息查询房源
     *
     * @param conContract
     * @return
     */
    @Override
    public HouHouseInfo getHouseInfoByContractInput(ConContract conContract) {
        HouHouseInfoExample houHouseInfoExample = new HouHouseInfoExample();
        HouHouseInfoExample.Criteria criteria = houHouseInfoExample.createCriteria();
        boolean searchFlg = false;
        if (StringUtils.isNotEmpty(conContract.getHouseAddr())) {
            criteria.andHouseAddrEqualTo(conContract.getHouseAddr());
            searchFlg = true;
        }
        if (null != conContract.getHouseStructure()) {
            criteria.andStructureEqualTo(conContract.getHouseStructure());
            searchFlg = true;
        }
        if (null != conContract.getBuildingArea()) {
            criteria.andBuildingAreaEqualTo(conContract.getBuildingArea());
            searchFlg = true;
        }
        if (null != conContract.getPropertyType()) {
            criteria.andPropertyIdTypeEqualTo(conContract.getPropertyType());
            searchFlg = true;
        }
        if (StringUtils.isNotEmpty(conContract.getPropertyIdNo())) {
            criteria.andPropertyIdNoEqualTo(conContract.getPropertyIdNo());
            searchFlg = true;
        }
        List<HouHouseInfo> houHouseInfoList = new ArrayList<>();
        if (searchFlg) {
            criteria.andDeleteFlgEqualTo((short) 0);
            houHouseInfoList = houHouseInfoMapper.selectByExample(houHouseInfoExample);
        }
        if (null != houHouseInfoList && houHouseInfoList.size() == 1) {
            return houHouseInfoList.get(0);
        }
        return null;
    }

    /**
     * 根据主键ID查询房源信息
     *
     * @param hid
     * @return
     */
    @Override
    public HouHouseInfo getHouHouseInfoById(String hid) {
        return houHouseInfoMapper.selectByPrimaryKey(hid);
    }

    /**
     * 保存房屋信息log
     *
     * @param houHouseInfo
     * @param user
     */
    private void saveHouseInfoLog(HouHouseInfo houHouseInfo,
                                  Integer optEvent,
                                  UsrUser user) {
        HouHouseInfoLog houHouseInfoLog = new HouHouseInfoLog();
        ConversionUtils.reflectionAttr(houHouseInfo, houHouseInfoLog);
        houHouseInfoLog.setOperator(user.getId());
        houHouseInfoLog.setOptEvent(optEvent);
        houHouseInfoLog.setOptTime(DateUtils.getSynchTime());
        houHouseInfoLog.setDcrp("新建");
        houHouseInfoLog.preInsert(user);
        houHouseInfoLogMapper.insert(houHouseInfoLog);
    }
}
