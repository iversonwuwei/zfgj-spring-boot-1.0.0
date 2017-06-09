package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/16.
 */
@Service
@Transactional
public class AgtEmpInfoServiceImpl implements AgtEmpInfoService {

    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;

    /**
     * 获取所属部门所有经纪人ID
     *
     * @return 经纪人ID列表
     */
    public List<String> getEmpIdListByOfficeId(String officeId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        criteria.andOfficeIdEqualTo(officeId);
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        List<String> empIdList = new ArrayList<>();
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            for (AgtEmpInfo agtEmpInfo : agtEmpInfoList) {
                empIdList.add(agtEmpInfo.getId());
            }
        }
        return empIdList;
    }

    /**
     * 判断用户（经纪人）是否锁定
     *
     * @return
     */
    public boolean isCompLockedByUserId(UsrUser user) {
        /**
         * this is an example for gettting the shiroUser;
         * Based On the way to get the shiro user and their properties;
         * 如果属性值不足以满足需求，可以扩展ShiroUser 然后添加convertor以获得足够的属性。
         */
        boolean lockFlag = false;
        // 查询经纪人
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria agtEmpInfoCriteria = agtEmpInfoExample.createCriteria();
        agtEmpInfoCriteria.andUserIdEqualTo(user.getId());
        agtEmpInfoCriteria.andDeleteFlgEqualTo((short) 0);
        agtEmpInfoCriteria.andStatusEqualTo(0);
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        // 验证经纪人是否被锁住
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            AgtCompInfo agtCompInfo;
            for (AgtEmpInfo agtEmpInfo : agtEmpInfoList) {
                agtCompInfo = agtCompInfoMapper.selectByPrimaryKey(agtEmpInfo.getCompanyId());
                if (null != agtCompInfo && agtCompInfo.getLockFlag() == 1) {
                    lockFlag = true;
                    break;
                }
            }
        }
        return lockFlag;
    }

    /**
     * 根据登录用户查询经纪人信息
     *
     * @param userId
     * @return
     */
    @Override
    public AgtEmpInfo getAgtEmpInfoByUserId(String userId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        List<AgtEmpInfo> agtEmpInfos = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        if (null != agtEmpInfos && agtEmpInfos.size() == 1) {
            return agtEmpInfos.get(0);
        }
        return null;
    }

    /**
     * 根据主键ID查询经纪人信息
     *
     * @param eid
     * @return
     */
    @Override
    public AgtEmpInfo getAgtEmpInfoById(String eid) {
        return agtEmpInfoMapper.selectByPrimaryKey(eid);
    }

    /**
     * 根据公司ID查询公司下属所有经纪人ID
     *
     * @param companyId
     * @return
     */
    @Override
    public List<String> getEmpIdListByCompanyId(String companyId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            List<String> eidList = new ArrayList<>();
            for (AgtEmpInfo agtEmpInfo : agtEmpInfoList) {
                eidList.add(agtEmpInfo.getId());
            }
            return eidList;
        }
        return null;
    }
}
