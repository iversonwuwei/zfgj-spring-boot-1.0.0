package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.SysInfoAtt;
import com.dlfc.zfgj.security.mobile.entity.SysInfoAttExample;
import com.dlfc.zfgj.security.mobile.enums.YesNoEnum;
import com.dlfc.zfgj.security.mobile.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.security.mobile.service.SysInfoAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/5/13.
 */

@Service("smSysInfoAttService")
@Transactional
public class SysInfoAttServiceImpl implements SysInfoAttService {

    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;

    @Override
    public List<SysInfoAtt> getSysInfoAttByParams(Integer type,
                                                  String lid) {
        SysInfoAttExample example = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andFileTypeEqualTo(type);
        criteria.andLidEqualTo(lid);
        return sysInfoAttMapper.selectByExample(example);
    }
}
