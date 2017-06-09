package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.security.mobile.entity.AgtUsrCompLogInfoExample;
import com.dlfc.zfgj.security.mobile.enums.YesNoEnum;
import com.dlfc.zfgj.security.mobile.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.security.mobile.service.AgtUsrCompLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/6/6.
 */

@Service("smAgtUsrCompLogInfoService")
@Transactional
public class AgtUsrCompLogInfoServiceImpl implements AgtUsrCompLogInfoService {

    @Autowired
    private AgtUsrCompLogInfoMapper agtUsrCompLogInfoMapper;

    @Override
    public List<AgtUsrCompLogInfo> findAgtUsrCompLogInfoByUid(String uid) {
        AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
        AgtUsrCompLogInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("PINDEX DESC");
        return agtUsrCompLogInfoMapper.selectByExample(example);
    }
}
