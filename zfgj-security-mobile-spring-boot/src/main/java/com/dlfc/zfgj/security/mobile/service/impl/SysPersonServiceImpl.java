package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.SysPerson;
import com.dlfc.zfgj.security.mobile.mapper.SysPersonMapper;
import com.dlfc.zfgj.security.mobile.service.SysPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/5/13.
 */

@Service("smSysPersonService")
@Transactional
public class SysPersonServiceImpl implements SysPersonService {

    @Autowired
    private SysPersonMapper sysPersonMapper;

    @Override
    public SysPerson getSysPersonById(String id) {
        return sysPersonMapper.selectByPrimaryKey(id);
    }
}
