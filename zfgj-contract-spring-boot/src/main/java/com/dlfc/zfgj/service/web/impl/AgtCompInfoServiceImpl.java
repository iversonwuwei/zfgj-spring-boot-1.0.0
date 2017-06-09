package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.service.web.AgtCompInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/3/31.
 */

@Service
@Transactional
public class AgtCompInfoServiceImpl implements AgtCompInfoService {

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
