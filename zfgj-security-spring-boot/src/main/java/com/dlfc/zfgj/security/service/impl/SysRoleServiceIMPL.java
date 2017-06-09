package com.dlfc.zfgj.security.service.impl;

import com.dlfc.zfgj.security.entity.SysRole;
import com.dlfc.zfgj.security.mapper.SysRoleMapper;
import com.dlfc.zfgj.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by K on 2017/3/29.
 */

@Service
public class SysRoleServiceIMPL implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询角色表
     * @param id
     * @return
     */
    @Override
    public SysRole getSysRoleById(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }
}
