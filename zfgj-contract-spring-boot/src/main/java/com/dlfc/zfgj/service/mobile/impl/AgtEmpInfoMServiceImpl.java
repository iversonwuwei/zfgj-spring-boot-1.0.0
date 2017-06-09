package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.MsgEnums;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.service.mobile.AgtEmpInfoMService;
import com.dlfc.zfgj.service.mobile.SysMobileCapchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/2/16.
 */
@Service
@Transactional
public class AgtEmpInfoMServiceImpl implements AgtEmpInfoMService {
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;
    @Autowired
    private AgtEmpInfoExample agtEmpInfoExample;
    @Autowired
    @Qualifier("contractForM")
    private SysMobileCapchaService sysMobileCapchaService;

    /**
     * 获取所属部门所有经纪人ID
     *
     * @return 经纪人ID列表
     */
    public List<String> getEmpIdListByOfficeId(String officeId) {
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
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
        agtEmpInfoCriteria.andUserIdEqualTo(user.getId());//currentUser.getId());
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

    @Override
    public AgtEmpInfo getAgtEmpInfoByUserId(String userId) {
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(0);
        criteria.andDeleteFlgEqualTo((short) 0);
        List<AgtEmpInfo> agtEmpInfos = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        AgtEmpInfo agtEmpInfo = new AgtEmpInfo();
        if (null != agtEmpInfos && agtEmpInfos.size() == 1) {
            agtEmpInfo = agtEmpInfos.get(0);
        }
        return agtEmpInfo;
    }

    /**
     * 更改经纪人手机号
     *
     * @return
     */
    @Override
    public boolean changePhone(UsrUser usrUser,String phone,String code,String msgId) throws Exception{
        boolean flag = false;
        int verCodeLimitSecond = Integer.valueOf(1800);
        SysMobileCapcha param = new SysMobileCapcha();
        param.setVerCode(code);
        param.setMobile(phone);
        // 重置登录密码
        param.setDomain(String.valueOf(MsgEnums.FINDPWDCHECKPHONE.getValue()));
        param.setDeviceId(msgId);
        SysMobileCapcha sysMobileCapcha = sysMobileCapchaService.selectByVerCode(param);
        if (sysMobileCapcha == null) {
            throw new ApplicationException("验证码不正确");
        }
        // 验证码是否超时
        int subSecond = DateUtils.getSecondBetweenDate(sysMobileCapcha.getCreateTime(), new Date());
        if (subSecond > verCodeLimitSecond) {
            throw new ApplicationException("验证码无效");
        }
        AgtEmpInfo agtEmpInfo = new AgtEmpInfo();
        agtEmpInfo.preUpdate(usrUser);
        agtEmpInfo.setId(usrUser.getEmpId());
        agtEmpInfo.setPhone(phone);
        int success = agtEmpInfoMapper.updateByPrimaryKeySelective(agtEmpInfo);
        if(success > 0){
            flag = true;
        }
        return flag;
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

}
