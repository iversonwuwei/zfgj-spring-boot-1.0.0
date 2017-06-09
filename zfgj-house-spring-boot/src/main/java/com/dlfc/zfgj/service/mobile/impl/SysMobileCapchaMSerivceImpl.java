package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.SysMobileCapcha;
import com.dlfc.zfgj.entity.SysMobileCapchaExample;
import com.dlfc.zfgj.mapper.SysMobileCapchaMapper;
import com.dlfc.zfgj.service.mobile.SysMobileCapchaMSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/6/5.
 */

@Service("SysMobileCapchaMSerivce")
@Transactional
public class SysMobileCapchaMSerivceImpl implements SysMobileCapchaMSerivce {

    @Autowired
    private SysMobileCapchaMapper sysMobileCapchaMapper;

    @Override
    public List<SysMobileCapcha> findByParams(SysMobileCapcha sysMobileCapcha) {
        if (null != sysMobileCapcha) {
            SysMobileCapchaExample example = new SysMobileCapchaExample();
            SysMobileCapchaExample.Criteria criteria = example.createCriteria();
            criteria.andDomainEqualTo(sysMobileCapcha.getDomain());
            criteria.andMobileEqualTo(sysMobileCapcha.getMobile());
            criteria.andVerCodeEqualTo(sysMobileCapcha.getVerCode());
            criteria.andDomainEqualTo(sysMobileCapcha.getDomain());
            return sysMobileCapchaMapper.selectByExample(example);
        }
        return null;
    }
}
