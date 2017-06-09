package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.AgtEmpInfo;
import com.dlfc.zfgj.security.mobile.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.security.mobile.enums.YesNoEnum;
import com.dlfc.zfgj.security.mobile.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.security.mobile.service.AgtEmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by K on 2017/3/29.
 */

@Service("smAgtEmpInfoService")
@Primary
public class AgtEmpInfoServiceIMPL implements AgtEmpInfoService {

    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;

    /**
     * 通过userId查询经纪人信息
     *
     * @param userId
     * @return
     */
    @Override
    public AgtEmpInfo getAgtEmpInfoByUserId(String userId) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        List<AgtEmpInfo> agtEmpInfos = agtEmpInfoMapper.selectByExample(example);
        example.setOrderByClause("CREATE_TIME DESC");
        if (null != agtEmpInfos && agtEmpInfos.size() > 0) {
            return agtEmpInfos.get(0);
        }
        return null;
    }
}
