/*
package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.entity.ContractShiroUser;
import com.dlfc.zfgj.entity.UsrUser;
import com.housecenter.dlfc.framework.shiro.core.model.ShiroUser;
import org.springframework.stereotype.Service;

*/
/**
 * Created by walden on 2017/3/14.
 *//*

@Service("shiroUserMConvertor")
public class ShiroUserMConvertor  extends AbstractConvertor<ShiroUser, UsrUser> {

    @Override
    public ShiroUser toModel(UsrUser sysUser) {
        ContractShiroUser shiroUser = new ContractShiroUser();
        shiroUser.setId(sysUser.getId());
        shiroUser.setEmpId(sysUser.getEmpId());
        shiroUser.setName(sysUser.getPersonName());
        shiroUser.setUsername(sysUser.getUsername());
        shiroUser.setPhone(sysUser.getPhone());
        shiroUser.setUrl(sysUser.getAvatar());
        shiroUser.setCompany(sysUser.getCompanyName());
        shiroUser.setFlag(sysUser.getFlag());
        shiroUser.setGrade(sysUser.getScore());
        return shiroUser;
    }

    @Override
    public UsrUser toDTO(ShiroUser model, boolean forListView) {
        return null;
    }
}
*/
