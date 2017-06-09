package com.dlfc.zfgj.security.service;

import com.dlfc.zfgj.security.entity.SysPermissionScope;

/**
 * Created by K on 2017/4/12.
 */
public interface SysPermissionScopeService {
    SysPermissionScope getScopeByParams(String id, String permission);

    boolean scopeComparator(String dataScope, String id, String houseEditable);
}
