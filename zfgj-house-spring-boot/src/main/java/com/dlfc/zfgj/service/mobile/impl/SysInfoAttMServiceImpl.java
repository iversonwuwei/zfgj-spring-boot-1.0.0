package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.mapper.UsrUserMapper;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangna on 2017/3/8.
 */
@Service("sysInfoMServiceImpl")
@Transactional
public class SysInfoAttMServiceImpl implements SysInfoAttrMService {

    private SysInfoAtt entity;

    /**
     * 附件mapper
     */
    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;

    @Autowired
    private UsrUserMapper usrUserMapper;

    /**
     * 根据lid查询
     *
     * @param lid  图片外键ID
     * @param type 图片类型
     * @return 图片对象
     */
    @Override
    public SysInfoAtt selectByLidAndType(String lid, int type) {
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria crit = sysInfoAttExample.createCriteria();
        crit.andLidEqualTo(lid);
        crit.andFileTypeEqualTo(type);
        crit.andDeleteFlgEqualTo((short) 0);
        sysInfoAttExample.setOrderByClause(" PINDEX ASC ");
        List<SysInfoAtt> list = sysInfoAttMapper.selectByExample(sysInfoAttExample);
        if (list != null && list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public SysInfoAtt saveAndGet(SysInfoAtt sysInfoAtt) {//经纪人
        UsrUser user = usrUserMapper.selectByPrimaryKey(sysInfoAtt.getLid());
        sysInfoAtt.preInsert(user);
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAttMapper.selectByPrimaryKey(sysInfoAtt.getId());
    }

    /**
     * 更新附件表并获取
     *
     * @param sysInfoAtt
     * @return
     */
    @Override
    public SysInfoAtt saveRoomAndGet(SysInfoAtt sysInfoAtt, String empId) {//房间信息
        UsrUser user = usrUserMapper.selectByPrimaryKey(empId);
        sysInfoAtt.preInsert(user);
        sysInfoAttMapper.insertSelective(sysInfoAtt);
        return sysInfoAttMapper.selectByPrimaryKey(sysInfoAtt.getId());
    }

    /**
     * 插入附件表
     *
     * @param sysInfoAtt 附件表对象
     * @return
     */
    @Override
    public int save(SysInfoAtt sysInfoAtt) {
        sysInfoAtt.setId(IdGen.uuid());
        sysInfoAtt.setCreateTime(DateUtils.getSynchTime());
        sysInfoAtt.setModifyTime(DateUtils.getSynchTime());
        sysInfoAtt.setDeleteFlg((short) 0);
        return sysInfoAttMapper.insertSelective(sysInfoAtt);
    }

    /**
     * 根据Lid删除原数据
     *
     * @param lid
     * @param type
     */
    @Override
    public void deleteSysInfoAttByParams(String lid,
                                         Integer type) {
        UsrUser user = usrUserMapper.selectByPrimaryKey(lid);
        SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = sysInfoAttExample.createCriteria();
        criteria.andLidEqualTo(lid);
        criteria.andFileTypeEqualTo(type);
        criteria.andDeleteFlgEqualTo((short) 0);
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.preUpdate(user);
        sysInfoAtt.setDeleteFlg((short) 1);
        sysInfoAttMapper.updateByExampleSelective(sysInfoAtt, sysInfoAttExample);
    }

    @Override
    public void saveWithLidAndPaths(String id,
                                    List<String> paths,
                                    int type,
                                    UsrUser user) {
        for (String path : paths) {
            entity = new SysInfoAtt();
            entity.setFilePath(path);
            entity.setLid(id);
            entity.setFileType(type);
            save(entity, user);
        }
    }

    @Override
    public String save(SysInfoAtt sysInfoAtt,
                       UsrUser user) {
        if (null != sysInfoAtt) {
            sysInfoAtt.preInsert(user);
            sysInfoAttMapper.insertSelective(sysInfoAtt);
            return sysInfoAtt.getId();
        }
        return null;
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
}
