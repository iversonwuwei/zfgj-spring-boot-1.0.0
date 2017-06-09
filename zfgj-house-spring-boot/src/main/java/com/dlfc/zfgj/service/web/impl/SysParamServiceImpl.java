package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysParamExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysParamMapper;
import com.dlfc.zfgj.service.web.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/5/16.
 */

@Service("hwSysParamService")
@Transactional
public class SysParamServiceImpl implements SysParamService {

    private List<SysParam> sysParams;

    @Autowired
    private SysParamMapper sysParamMapper;

    @Override
    public boolean isOn(String scope) {
        SysParamExample example = new SysParamExample();
        SysParamExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andScopeEqualTo(scope);
        criteria.andSkeyEqualTo("ON-OFF");
        sysParams = sysParamMapper.selectByExample(example);
        if (null != sysParams && sysParams.size() == 1) {
            String svalue = sysParams.get(0).getSvalue();
            if (StringUtils.isNotEmpty(svalue) && "on".equalsIgnoreCase(svalue)) {
                return true;
            }
        }
        return false;
    }
}
