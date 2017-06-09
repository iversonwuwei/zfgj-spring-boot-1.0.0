package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.web.SysInfoAttrService;
import com.dlfc.zfgj.service.web.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wsz on 2017/3/8.
 */
@Transactional
@Service("sysInfoServiceImpl")
public class SysInfoAttServiceImpl implements SysInfoAttrService {

    /**
     * 附件mapper
     */
    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;
    @Autowired
    @Qualifier("house")
    private UploadService uploadService;

    /**
     * 根据lid查询
     *
     * @param lid  图片外键ID
     * @param type 图片类型
     * @return 图片对象
     */
    @Override
    public SysInfoAtt selectByLidAndType(String lid, Integer type) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andFileTypeEqualTo(type);
        criteria.andDeleteFlgEqualTo((short) 0);
        List<SysInfoAtt> list = sysInfoAttMapper.selectByExample(sysInfoAttExample);
        if (list != null && list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 插入附件表
     *
     * @return
     */
    @Override
    public String save(SysInfoAtt sysInfoAtt,
                       UsrUser user) {
        sysInfoAtt.preInsert(user);
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAtt.getId();
    }

    /**
     * 更新附件表
     *
     * @param sysInfoAtt 附件表对象
     * @return
     */
    @Override
    public int update(SysInfoAtt sysInfoAtt) {
        return sysInfoAttMapper.updateByPrimaryKeySelective(sysInfoAtt);
    }

    @Override
    public SysInfoAtt saveAndGet(SysInfoAtt sysInfoAtt) {
        sysInfoAtt.setId(IdGen.uuid());
        sysInfoAtt.setCreateTime(DateUtils.getSynchTime());
        sysInfoAtt.setModifyTime(DateUtils.getSynchTime());
        sysInfoAtt.setDeleteFlg((short) 0);
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAttMapper.selectByPrimaryKey(sysInfoAtt.getId());
    }

//    @Override
//    public SysInfoAtt findByLid(String lid) {
//        SysInfoAtt sysInfoAtt = sysInfoAttMapper.selectByLid(lid);
//        return sysInfoAtt;
//    }

    @Override
    public SysInfoAtt findById(String id) {
        SysInfoAtt sysInfoAtt = sysInfoAttMapper.selectByPrimaryKey(id);
        return sysInfoAtt;
    }

    /**
     * 删除原数据
     *
     * @param lid
     * @param type
     * @param idList
     * @param user
     */
    @Override
    public void deleteSysInfoAttByParams(String lid,
                                         Integer type,
                                         List<String> idList,
                                         UsrUser user) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andFileTypeEqualTo(type);
        criteria.andIdNotIn(idList);
        criteria.andDeleteFlgEqualTo((short) 0);
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.preUpdate(user);
        sysInfoAtt.setDeleteFlg((short) 1);
        sysInfoAttMapper.updateByExampleSelective(sysInfoAtt, sysInfoAttExample);
    }

    @Override
    public List<SysInfoAtt> findByParams(int type,
                                         String lid) {
        SysInfoAttExample example = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andFileTypeEqualTo(type);
        criteria.andLidEqualTo(lid);
        return sysInfoAttMapper.selectByExample(example);
    }
}
