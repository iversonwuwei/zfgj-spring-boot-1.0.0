package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.AgtCompInfo;
import com.dlfc.zfgj.security.mobile.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.security.mobile.service.AgtCompInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/5/13.
 */

@Service("smAgtCompInfoService")
@Transactional
public class AgtCompInfoServiceImpl implements AgtCompInfoService {

    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;

    @Override
    public AgtCompInfo getAgtCompInfoById(String id) {
        return agtCompInfoMapper.selectByPrimaryKey(id);
    }
}
