package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysParamExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysParamMapper;
import com.dlfc.zfgj.service.mobile.SysParamMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by K on 2017/2/24.
 */

@Service
public class SysParamMServiceImpl implements SysParamMService {

    private List<SysParam> sysParamList;

    @Autowired
    private SysParamMapper sysParamMapper;

    /**
     * 判断保险开关是否打开
     *
     * @return on返回true，off返回false
     */
    public boolean isOn() {
        SysParamExample example = new SysParamExample();
        SysParamExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andScopeEqualTo("INSURANCE_FLAG");
        criteria.andSkeyEqualTo("ON-OFF");
        sysParamList = sysParamMapper.selectByExample(example);
        if (null != sysParamList && sysParamList.size() > 0) {
            String svalue = sysParamList.get(0).getSvalue();
            if (StringUtils.isNotEmpty(svalue)) {
                if ("on".equalsIgnoreCase(svalue)) {
                    return true;
                }
            }
        }
        return false;
    }
}
