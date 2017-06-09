package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserExample;
import com.dlfc.zfgj.mapper.UsrUserMapper;
import com.dlfc.zfgj.service.web.UserHouseService;
import com.dlfc.zfgj.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
@Transactional
@Service("us111333")
public class UserServiceImpl implements UserHouseService {

    @Autowired
    private UsrUserMapper usrUserMapper;

    @Autowired
    private CurrentUser currentUser;

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UsrUser getUser(String token) throws ApplicationException {
        String username = currentUser.getCurrentUser(token);
        UsrUserExample userExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        userExample.or(criteria);
        List<UsrUser> usrUsers = usrUserMapper.selectByExample(userExample);
        UsrUser usrUser = new UsrUser();
        if (null != usrUsers && usrUsers.size() == 1) {
            usrUser = usrUsers.get(0);
        }
        return usrUser;
    }

    /**
     * 根据主键查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UsrUser getUserById(String id) {
        return usrUserMapper.selectByPrimaryKey(id);
    }
}
