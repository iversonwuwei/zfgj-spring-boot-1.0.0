package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.ConHouseUserChildren;
import com.dlfc.zfgj.entity.ConHouseUserChildrenExample;
import com.dlfc.zfgj.mapper.ConHouseUserChildrenMapper;
import com.dlfc.zfgj.service.mobile.ConHouseUserChildrenMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangna on 2017/6/9.
 */
@Slf4j
@Transactional
@Service
public class ConHouseUserChildrenMServiceImpl implements ConHouseUserChildrenMService {

    @Autowired
    private ConHouseUserChildrenMapper conHouseUserChildrenMapper;

    @Override
    public List<ConHouseUserChildren> getByCid(String cid) {
        ConHouseUserChildrenExample childrenExample = new ConHouseUserChildrenExample();
        ConHouseUserChildrenExample.Criteria criteria = childrenExample.createCriteria();
        criteria.andCidEqualTo(cid);
        criteria.andDeleteFlgEqualTo((short)0);
        return conHouseUserChildrenMapper.selectByExample(childrenExample);
    }
}
