package com.dlfc.zfgj.security.service.impl;

import com.dlfc.zfgj.security.entity.AgtCertLink;
import com.dlfc.zfgj.security.entity.AgtCertLinkExample;
import com.dlfc.zfgj.security.mapper.AgtCertLinkMapper;
import com.dlfc.zfgj.security.service.AgtCertLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/5/11.
 */

@Service("swAgtCertLinkService")
@Transactional
public class AgtCertLinkServiceImpl implements AgtCertLinkService {

    @Autowired
    private AgtCertLinkMapper agtCertLinkMapper;

    /**
     * 是否申请过经纪人
     *
     * @param uid userId
     * @return true:申请过，false:未申请过
     */
    @Override
    public List<AgtCertLink> isEmpCerted(String uid) {
        AgtCertLinkExample example = new AgtCertLinkExample();
        AgtCertLinkExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("CREATE_TIME DESC");
        return agtCertLinkMapper.selectByExample(example);
    }
}
