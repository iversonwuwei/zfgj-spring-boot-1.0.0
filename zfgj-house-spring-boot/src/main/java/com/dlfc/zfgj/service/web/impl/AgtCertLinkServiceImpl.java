package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.AgtCertLinkExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.mapper.AgtCertLinkMapper;
import com.dlfc.zfgj.service.web.AgtCertLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/5/11.
 */

@Service("hwAgtCertLinkService")
@Transactional
public class AgtCertLinkServiceImpl implements AgtCertLinkService {

    private AgtCertLinkExample example;
    private AgtCertLinkExample.Criteria criteria;

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
        example = new AgtCertLinkExample();
        criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("CREATE_TIME DESC");
        return agtCertLinkMapper.selectByExample(example);
    }

    /**
     * 职业资格证是否使用过
     *
     * @param certNo
     * @return
     */
    @Override
    public int isAgtCardBinded(String certNo) {
        example = new AgtCertLinkExample();
        criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andCertCardNoEqualTo(certNo);
        return agtCertLinkMapper.countByExample(example);
    }

    /**
     * 保存
     *
     * @param agtCertLink
     * @param user
     * @return
     */
    @Override
    public String save(AgtCertLink agtCertLink,
                       UsrUser user) {
        if (null != agtCertLink) {
            agtCertLink.preInsert(user);
            agtCertLinkMapper.insertSelective(agtCertLink);
            return agtCertLink.getId();
        }
        return null;
    }
}
