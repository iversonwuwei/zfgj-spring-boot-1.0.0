package com.dlfc.zfgj.security.service.impl;

import com.dlfc.zfgj.security.entity.AgtEmpInfo;
import com.dlfc.zfgj.security.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.security.enums.YesNoEnum;
import com.dlfc.zfgj.security.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.security.service.AgtEmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by K on 2017/3/29.
 */

@Service
public class AgtEmpInfoServiceImpl implements AgtEmpInfoService {

    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;

    /**
     * 通过userId查询经纪人信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<AgtEmpInfo> getAgtEmpInfoByUserId(String userId) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        example.setOrderByClause("CREATE_TIME DESC");
        return agtEmpInfoMapper.selectByExample(example);
    }
}
