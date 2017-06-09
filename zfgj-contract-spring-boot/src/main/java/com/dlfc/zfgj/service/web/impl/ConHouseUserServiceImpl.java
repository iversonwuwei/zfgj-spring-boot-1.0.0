package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.ConHouseUserExample;
import com.dlfc.zfgj.mapper.ConHouseUserMapper;
import com.dlfc.zfgj.service.web.ConHouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/3/14.
 */

@Service
@Transactional
public class ConHouseUserServiceImpl implements ConHouseUserService {

    @Autowired
    ConHouseUserMapper conHouseUserMapper;

    @Override
    public List<ConHouseUser> getHouseUserByCId(String cid) {
        ConHouseUserExample conHouseUserExample = new ConHouseUserExample();
        ConHouseUserExample.Criteria criteria = conHouseUserExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andCidEqualTo(cid);
        return conHouseUserMapper.selectByExample(conHouseUserExample);
    }
}
