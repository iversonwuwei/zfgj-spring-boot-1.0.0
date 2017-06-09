package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.AgtCusInfoExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtCusInfoMapper;
import com.dlfc.zfgj.service.web.AgtCusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/5/11.
 */

@Service("hwAgtCusInfoService")
@Transactional
public class AgtCusInfoServiceImpl implements AgtCusInfoService {

    @Autowired
    private AgtCusInfoMapper agtCusInfoMapper;

    /**
     * 根据经纪人id获取客源
     *
     * @param eid 经纪人信息ID
     * @return 客源数量
     */
    @Override
    public int cus(String eid) {
        AgtCusInfoExample example = new AgtCusInfoExample();
        AgtCusInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andEidEqualTo(eid);
        return agtCusInfoMapper.countByExample(example);
    }
}
