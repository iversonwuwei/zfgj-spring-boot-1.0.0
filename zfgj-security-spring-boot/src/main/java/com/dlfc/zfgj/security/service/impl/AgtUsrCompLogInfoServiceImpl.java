package com.dlfc.zfgj.security.service.impl;

import com.dlfc.zfgj.security.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.security.entity.AgtUsrCompLogInfoExample;
import com.dlfc.zfgj.security.enums.YesNoEnum;
import com.dlfc.zfgj.security.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.security.service.AgtUsrCompLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by K on 2017/5/25.
 */

@Service
public class AgtUsrCompLogInfoServiceImpl implements AgtUsrCompLogInfoService {

    @Autowired
    private AgtUsrCompLogInfoMapper agtUsrCompLogInfoMapper;

    /**
     * 按照用户id查找 经纪人公司
     *
     * @param uid 用户申请公司记录对象ID
     * @return 用户申请公司记录对象列表
     */
    @Override
    public AgtUsrCompLogInfo findAgtUsrCompLog(String uid) {
        AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
        AgtUsrCompLogInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("PINDEX DESC");
        List<AgtUsrCompLogInfo> agtUsrCompLogInfoList = agtUsrCompLogInfoMapper.selectByExample(example);
        if (null != agtUsrCompLogInfoList && agtUsrCompLogInfoList.size() > 0) {
            return agtUsrCompLogInfoList.get(0);
        }
        return null;
    }
}
