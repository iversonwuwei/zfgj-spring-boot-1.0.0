package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysParamExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysParamMapper;
import com.dlfc.zfgj.service.mobile.SysParamMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangna on 2017/6/1.
 */
@Service("sysParamService")
@Transactional
public class SysParamMServiceImpl implements SysParamMService {

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

    /**
     * 实名认证参数
     *
     * @return
     */
    @Override
    public Map<String, String> getSysParamsForAuth() {
        Map<String, String> paramMap = new HashMap<>();
        SysParamExample sysParamExample = new SysParamExample();
        SysParamExample.Criteria criteria = sysParamExample.createCriteria();
        criteria.andScopeEqualTo("auth");
        criteria.andDeleteFlgEqualTo((short) 0);
        List<SysParam> sysParams = sysParamMapper.selectByExample(sysParamExample);
        if (null != sysParams) {
            for (SysParam sysParam : sysParams) {
                paramMap.put(sysParam.getSkey(), sysParam.getSvalue());
            }
        }
        return paramMap;
    }
}
