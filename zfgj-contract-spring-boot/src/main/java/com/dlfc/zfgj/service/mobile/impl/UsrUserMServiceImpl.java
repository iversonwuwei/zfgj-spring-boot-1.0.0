package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.Utils.CurrentUser;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.UsrUserMapper;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/2/24.
 */

@Transactional
@Service
public class UsrUserMServiceImpl implements UsrUserMService {

    private List<UsrUser> usrUserList;

    @Autowired
    private UsrUserMapper usrUserMapper;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    AgtEmpInfoMapper agtEmpInfoMapper;

    /**
     * 通过用户信息ID查询用户对象
     *
     * @param perId 用户信息ID
     * @return 用户对象
     */
    public UsrUser getUserByPerId(String perId) {
        UsrUserExample example = new UsrUserExample();
        UsrUserExample.Criteria criteria = example.createCriteria();
        criteria.andPerIdEqualTo(perId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
         usrUserList = usrUserMapper.selectByExample(criteria);
        if (usrUserList != null && usrUserList.size() == 1) {
            return usrUserList.get(0);
        }
        return null;
    }

    @Override
    public UsrUser getUser(String token) throws ApplicationException{
        String username = currentUser.getCurrentUser(token);
        UsrUserExample usrUserExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = usrUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria = usrUserExample.createCriteria();
        criteria.andMobileEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        usrUserExample.or(criteria);
        List<UsrUser> usrUserList = usrUserMapper.selectByExample(usrUserExample);
        if (null != usrUserList && usrUserList.size() == 1) {
            UsrUser usrUser = usrUserList.get(0);
            AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
            AgtEmpInfoExample.Criteria criteria1 = agtEmpInfoExample.createCriteria();
            criteria1.andUserIdEqualTo(usrUser.getId());
            List<AgtEmpInfo> agtEmpInfo = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
            usrUser.setEmpId(agtEmpInfo.get(0).getId());
            return usrUser;
        }
        return null;
    }
}
