package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.security.Digests;
import com.dlfc.admin.common.utils.Encodes;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserExample;
import com.dlfc.zfgj.mapper.UsrUserMapper;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
@Transactional
@Service("us111333M")
public class UserMServiceImpl implements UserHouseMService {

    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UsrUserMapper usrUserMapper;

    @Autowired
    private CurrentUser currentUser;

    @Override
    public UsrUser getUserName(String token) throws ApplicationException {
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
     * 通过手机号查找用户
     *
     * @param mobile
     * @return
     */
    @Override
    public List<UsrUser> findByMobile(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            UsrUserExample userExample = new UsrUserExample();
            UsrUserExample.Criteria criteria = userExample.createCriteria();
            criteria.andDeleteFlgEqualTo((short) 0);
            criteria.andMobileEqualTo(mobile);
            return usrUserMapper.selectByExample(userExample);
        }
        return null;
    }

    /**
     * 注册用户信息
     *
     * @param user
     * @return
     */
    @Override
    public String save(UsrUser user) {
        user.preInsert(user);
        usrUserMapper.insertSelective(user);
        return user.getId();
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

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    @Override
    public String generatePassword(String password) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 通过手机生成用户名
     *
     * @param mobile
     * @return
     */
    @Override
    public String generateUsername(String mobile) {
        mobile += "_";
        for (int i = 0; i < 3; i++) {
            mobile += (char) (Math.random() * 26 + 'A');
        }
        return mobile;
    }

    /**
     * 通过人物信息查询注册用户
     *
     * @param id
     * @return
     */
    @Override
    public List<UsrUser> findByPerId(String id) {
        UsrUserExample example = new UsrUserExample();
        UsrUserExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andPerIdEqualTo(id);
        return usrUserMapper.selectByExample(example);
    }

    @Override
    public String updateById(UsrUser user) {
        if (null != user) {
            user.preUpdate(user);
            usrUserMapper.updateByPrimaryKey(user);
            return user.getId();
        }
        return null;
    }
}
