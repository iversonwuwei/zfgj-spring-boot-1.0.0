package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.web.SysInfoAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Service
@Transactional
public class SysInfoAttServiceImpl implements SysInfoAttService {

    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;

    @Override
    public List<SysInfoAtt> findList(String lid, Integer... fileTypes) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria;
        if (fileTypes != null) {
            for (Integer fileType : fileTypes) {
                criteria = sysInfoAttExample.or();
                criteria.andLidEqualTo(lid);
                criteria.andDeleteFlgEqualTo((short) 0);
                criteria.andFileTypeEqualTo(fileType);
            }
        } else {
            criteria = sysInfoAttExample.createCriteria();
            criteria.andLidEqualTo(lid);
            criteria.andDeleteFlgEqualTo((short) 0);
        }
        sysInfoAttExample.setOrderByClause("PINDEX");
        return sysInfoAttMapper.selectByExample(sysInfoAttExample);
    }

    /**
     * 上传多个文件时删除旧数据
     *
     * @param lid
     * @param ids
     * @param user
     */
    @Override
    public void deleteOldRecord(String lid,
                                List<String> ids,
                                UsrUser user) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andIdNotIn(ids);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.preUpdate(user);
        sysInfoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        sysInfoAttMapper.updateByExampleSelective(sysInfoAtt, sysInfoAttExample);
    }

    /**
     * 保存文件记录
     *
     * @param sysInfoAtt
     * @param user
     * @return
     */
    @Override
    public String save(SysInfoAtt sysInfoAtt, UsrUser user) {
        sysInfoAtt.preInsert(user);
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAtt.getId();
    }

    /**
     * 上传单个文件时删除旧数据
     *
     * @param contractId
     * @param type
     * @param user
     */
    @Override
    public void deleteOldSingleRecord(String contractId,
                                      Integer type,
                                      UsrUser user) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(contractId);
        criteria.andFileTypeEqualTo(type);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.preUpdate(user);
        sysInfoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        sysInfoAttMapper.updateByExampleSelective(sysInfoAtt, sysInfoAttExample);
    }
}
