package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.ConOtherCostsExample;
import com.dlfc.zfgj.mapper.ConOtherCostsMapper;
import com.dlfc.zfgj.service.mobile.ConOtherCostsMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangna on 2017/6/9.
 */
@Service
@Transactional
public class ConOtherCostsMServiceImpl implements ConOtherCostsMService{

    @Autowired
    private ConOtherCostsMapper conOtherCostsMapper;

    /**
     * 根据合同ID查询
     *
     * @param cid
     * @return
     */
    @Override
    public List<ConOtherCosts> getConOtherCostsByCid(String cid) {
        ConOtherCostsExample conOtherCostsExample = new ConOtherCostsExample();
        ConOtherCostsExample.Criteria criteria = conOtherCostsExample.createCriteria();
        criteria.andCidEqualTo(cid);
        criteria.andDeleteFlgEqualTo((short) 0);
        return conOtherCostsMapper.selectByExample(conOtherCostsExample);
    }
}
