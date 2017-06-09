package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.ConHouseItems;
import com.dlfc.zfgj.entity.ConHouseItemsExample;
import com.dlfc.zfgj.mapper.ConHouseItemsMapper;
import com.dlfc.zfgj.service.web.ConHouseItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/4/1.
 */

@Service
@Transactional
public class ConHouseItemsServiceImpl implements ConHouseItemsService {

    @Autowired
    private ConHouseItemsMapper conHouseItemsMapper;

    /**
     * 根据合同ID查询
     *
     * @param cid
     * @return
     */
    @Override
    public List<ConHouseItems> getConHouseItemsByCid(String cid) {
        ConHouseItemsExample conHouseItemsExample = new ConHouseItemsExample();
        ConHouseItemsExample.Criteria criteria = conHouseItemsExample.createCriteria();
        criteria.andCidEqualTo(cid);
        criteria.andDeleteFlgEqualTo((short) 0);
        return conHouseItemsMapper.selectByExample(conHouseItemsExample);
    }
}
