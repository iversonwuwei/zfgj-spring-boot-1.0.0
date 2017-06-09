package com.dlfc.zfgj.security.mobile.service.impl;

import com.dlfc.zfgj.security.mobile.entity.AgtCertLink;
import com.dlfc.zfgj.security.mobile.entity.AgtCertLinkExample;
import com.dlfc.zfgj.security.mobile.mapper.AgtCertLinkMapper;
import com.dlfc.zfgj.security.mobile.service.AgtCertLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/5/13.
 */

@Service("smAgtCertLinkService")
@Transactional
public class AgtCertLinkServiceImpl implements AgtCertLinkService {

    @Autowired
    private AgtCertLinkMapper agtCertLinkMapper;

    @Override
    public List<AgtCertLink> getAgtCertLinkByUid(String uid) {
        AgtCertLinkExample example = new AgtCertLinkExample();
        AgtCertLinkExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andUserIdEqualTo(uid);
        return agtCertLinkMapper.selectByExample(example);
    }
}
