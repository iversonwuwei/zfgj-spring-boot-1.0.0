package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.mapper.SysOfficeMapper;
import com.dlfc.zfgj.service.web.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/4/10.
 */

@Service
@Transactional
public class SysOfficeServiceImpl implements SysOfficeService {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;

    @Override
    public SysOffice getSysOfficeById(String officeId) {
        return sysOfficeMapper.selectByPrimaryKey(officeId);
    }
}
