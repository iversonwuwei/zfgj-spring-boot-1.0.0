package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysPersonExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.PersonIdTypeEnum;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.service.web.SystemPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/4/5.
 */

@Service
@Transactional
public class SystemPersonServiceImpl implements SystemPersonService {

    @Autowired
    private SysPersonMapper sysPersonMapper;

    /**
     * 根据主键查询系统用户信息
     *
     * @param id
     * @return
     */
    @Override
    public SysPerson getSysPersonById(String id) {
        return sysPersonMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存人物信息
     *
     * @param sysPerson
     * @param user
     * @return
     */
    @Override
    public String save(SysPerson sysPerson,
                       UsrUser user) {
        if (null != sysPerson) {
            sysPerson.preInsert(user);
            sysPersonMapper.insertSelective(sysPerson);
            return sysPerson.getId();
        }
        return null;
    }

    /**
     * 通过名字和身份证号码查询人物信息
     *
     * @param name
     * @param idNo
     * @return
     */
    @Override
    public List<SysPerson> findByNameAndIdNo(String name,
                                             String idNo) {
        SysPersonExample example = new SysPersonExample();
        SysPersonExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andNameEqualTo(name);
        criteria.andIdNoEqualTo(idNo);
        criteria.andIdTypeEqualTo(PersonIdTypeEnum.ID_CARD_ENUM.getValue());
        return sysPersonMapper.selectByExample(example);
    }
}
