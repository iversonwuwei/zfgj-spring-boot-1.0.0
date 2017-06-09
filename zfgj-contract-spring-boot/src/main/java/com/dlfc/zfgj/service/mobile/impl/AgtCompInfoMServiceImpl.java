package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.service.mobile.AgtCompInfoMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanglijun on 2017/4/14.
 */
@Service
@Transactional
public class AgtCompInfoMServiceImpl  implements AgtCompInfoMService {
    @Autowired
    AgtCompInfoMapper agtCompInfoMapper;

    /**
     * 根据主键查公司信息
     *
     * @param agtComId
     * @return
     */
    @Override
    public AgtCompInfo getComInfoByComId(String agtComId) {
        return agtCompInfoMapper.selectByPrimaryKey(agtComId);
    }
}
