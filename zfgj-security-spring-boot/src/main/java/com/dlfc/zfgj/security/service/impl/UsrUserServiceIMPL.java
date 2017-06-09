package com.dlfc.zfgj.security.service.impl;


import com.dlfc.zfgj.security.entity.UsrUser;
import com.dlfc.zfgj.security.entity.UsrUserExample;
import com.dlfc.zfgj.security.enums.YesNoEnum;
import com.dlfc.zfgj.security.mapper.UsrUserMapper;
import com.dlfc.zfgj.security.service.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/2/24.
 */

@Transactional
@Service
public class UsrUserServiceIMPL implements UsrUserService {

    @Autowired
    private UsrUserMapper usrUserMapper;

//    /**
//     * 通过用户信息ID查询用户对象
//     *
//     * @param perId 用户信息ID
//     * @return 用户对象
//     */
//    public UsrUser getUserByPerId(String perId) {
//        UsrUserExample usrUserExample = new UsrUserExample();
//        UsrUser user = new UsrUser();
//        UsrUserExample.Criteria cri = usrUserExample.createCriteria();
//        cri.andPerIdEqualTo(perId);
//        cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
//        List<UsrUser> list = usrUserMapper.selectByExample(usrUserExample);
//        if (list != null && list.size() > 0) {
//            user = list.get(0);
//        }
//        return user;
//    }

//    /**
//     * Mobile端查询用户信息
//     *
//     * @param username
//     * @return
//     */
//    @Override
//    public UsrUser findByUserName(String username) {
//        UsrUser usrUser = usrUserMapper.findByUserName(username);
//        return usrUser;
//    }

    /**
     * Web端查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UsrUser getUserByUserName(String username) {
        UsrUserExample usrUserExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = usrUserExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andUsernameEqualTo(username);
        criteria = usrUserExample.or();
        criteria.andMobileEqualTo(username);
        List<UsrUser> usrUsers = usrUserMapper.selectByExample(usrUserExample);
        if (null != usrUsers && usrUsers.size() == 1) {
            return usrUsers.get(0);
        }
        return null;
    }
}
