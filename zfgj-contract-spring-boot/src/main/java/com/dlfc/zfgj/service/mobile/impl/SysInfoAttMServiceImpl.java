package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.mobile.SysInfoAttMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Service
@Transactional
public class SysInfoAttMServiceImpl implements SysInfoAttMService {

    // 合同
    private static final String CONTRACT = "合同";

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

    @Override
    public void deleteOldRecord(String lid, List<Integer> ids,UsrUser user) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andFileTypeIn(ids);
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.setVersion(1);
        sysInfoAtt.preUpdate(user);
        sysInfoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
        sysInfoAttMapper.updateByExampleSelective(sysInfoAtt, sysInfoAttExample);
    }

    @Override
    public String save(SysInfoAtt sysInfoAtt, UsrUser user) {
        sysInfoAtt.preInsert(user);
//        sysInfoAtt.setId(IdGen.uuid());//TODO
//        sysInfoAtt.setModifyTime(DateUtils.getSynchTime());//TODO
//        sysInfoAtt.setCreateTime(sysInfoAtt.getModifyTime());//TODO
//        sysInfoAtt.setVersion(Integer.valueOf(0));//TODO
//        sysInfoAtt.setDeleteFlg((short) 0);//TODO
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAtt.getId();
    }
}
