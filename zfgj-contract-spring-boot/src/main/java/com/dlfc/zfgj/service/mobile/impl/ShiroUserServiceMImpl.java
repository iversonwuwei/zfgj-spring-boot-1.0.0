package com.dlfc.zfgj.service.mobile.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by walden on 2017/3/14.
 */
@Service("shiroUserServiceMImpl")
@Transactional
public class ShiroUserServiceMImpl { /*implements ShiroUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserExample sysUserExample;
    @Autowired
    @Qualifier("shiroUseronvertor")
    private ShiroUserConvertor shiroUserConvertor;


    @Override
    public ShiroUser selectShiroUser(String s) {

        SysUser sysUser = sysUserMapper.findByLoginName(s);
        if (sysUser != null){
            ShiroUser shiroUser = shiroUserConvertor.toModel(sysUser);
            return  shiroUser;
        }
        return null;
    }*/
}
