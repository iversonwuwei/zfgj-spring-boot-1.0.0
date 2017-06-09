package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.SysAreaCities;
import com.dlfc.zfgj.entity.SysAreaCitiesExample;
import com.dlfc.zfgj.entity.SysAreaProvinces;
import com.dlfc.zfgj.entity.SysAreaProvincesExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysAreaCitiesMapper;
import com.dlfc.zfgj.mapper.SysAreaProvincesMapper;
import com.dlfc.zfgj.service.mobile.SysAreaAreasMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/3/4.
 */

@Service
@Transactional
public class SysAreaAreasMServiceImpl implements SysAreaAreasMService {

    @Autowired
    private SysAreaCitiesMapper sysAreaCitiesMapper;
    @Autowired
    private SysAreaProvincesMapper sysAreaProvincesMapper;

    /**
     * 取得所有省
     *
     * @return
     */
    @Override
    public List<SysAreaProvinces> getAllProvinces() {
        SysAreaProvincesExample example = new SysAreaProvincesExample();
        SysAreaProvincesExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return sysAreaProvincesMapper.selectByExample(example);
    }

    /**
     * 根据省取得下属市
     *
     * @return
     */
    @Override
    public List<SysAreaCities> getAllCities(String provinceId) {
        SysAreaCitiesExample example = new SysAreaCitiesExample();
        SysAreaCitiesExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andProvinceIdEqualTo(provinceId);
        return sysAreaCitiesMapper.selectByExample(example);
    }
}
