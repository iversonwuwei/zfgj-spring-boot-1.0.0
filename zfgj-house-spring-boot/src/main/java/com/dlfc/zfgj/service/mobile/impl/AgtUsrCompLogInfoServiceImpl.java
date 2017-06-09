package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfoExample;
import com.dlfc.zfgj.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.service.mobile.AgtUsrCompLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/6/8.
 */

@Service("hmAgtUsrCompLogInfoService")
@Transactional
public class AgtUsrCompLogInfoServiceImpl implements AgtUsrCompLogInfoService {

    @Autowired
    private AgtUsrCompLogInfoMapper agtUsrCompLogInfoMapper;

    @Override
    public List<AgtUsrCompLogInfo> findByUid(String uid) {
        AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
        AgtUsrCompLogInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("PINDEX DESC");
        return agtUsrCompLogInfoMapper.selectByExample(example);
    }
}
