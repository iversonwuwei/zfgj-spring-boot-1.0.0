package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.ConOtherCostsExample;
import com.dlfc.zfgj.mapper.ConOtherCostsMapper;
import com.dlfc.zfgj.service.web.ConOtherCostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/4/1.
 */

@Service
@Transactional
public class ConOtherCostsServiceImpl implements ConOtherCostsService {

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
