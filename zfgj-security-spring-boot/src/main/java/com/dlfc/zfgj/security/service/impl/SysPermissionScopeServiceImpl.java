package com.dlfc.zfgj.security.service.impl;

import com.dlfc.zfgj.security.entity.SysPermissionScope;
import com.dlfc.zfgj.security.entity.SysPermissionScopeExample;
import com.dlfc.zfgj.security.enums.YesNoEnum;
import com.dlfc.zfgj.security.mapper.SysPermissionScopeMapper;
import com.dlfc.zfgj.security.service.SysPermissionScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/4/12.
 */

@Service
@Transactional
public class SysPermissionScopeServiceImpl implements SysPermissionScopeService {

    @Autowired
    private SysPermissionScopeMapper sysPermissionScopeMapper;

    /**
     * 根据角色ID和许可查询权限信息
     *
     * @param id
     * @param permission
     * @return
     */
    @Override
    public SysPermissionScope getScopeByParams(String id,
                                               String permission) {
        SysPermissionScopeExample sysPermissionScopeExample = new SysPermissionScopeExample();
        SysPermissionScopeExample.Criteria criteria = sysPermissionScopeExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andRoleIdEqualTo(id);
        criteria.andPermissionEqualTo(permission);
        List<SysPermissionScope> scopeList = sysPermissionScopeMapper.selectByExample(sysPermissionScopeExample);
        if (null != scopeList && scopeList.size() == 1) {
            return scopeList.get(0);
        }
        return null;
    }

    /**
     * 比较scope
     *
     * @param dataScope
     * @param houseEditable
     * @return
     */
    @Override
    public boolean scopeComparator(String dataScope,
                                   String id,
                                   String houseEditable) {
        SysPermissionScope scope = getScopeByParams(id, houseEditable);
        if (null != scope && scope.getScope().compareTo(dataScope) >= 0) {
            return true;
        }
        return false;
    }
}
