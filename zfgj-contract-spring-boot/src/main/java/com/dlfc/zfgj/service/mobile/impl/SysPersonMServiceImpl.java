package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.zfgj.common.identify.ContractAuthFacet;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysPersonExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.PersonIdTypeEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.service.mobile.SysPersonMService;
import com.dlfc.zfgj.service.web.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

@Transactional
@Service
public class SysPersonMServiceImpl implements SysPersonMService {

    @Autowired
    protected SysPersonMapper sysPersonMapper;
    @Autowired
    protected SysParamService sysParamServiceImpl;

    /**
     * 根据主键查询系统用户信息
     *
     * @param id
     * @return
     */
    @Override
    public SysPerson getSyspersonsById(String id) {
        return sysPersonMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据身份证号和姓名查用户信息的ID
     *
     * @param name 姓名
     * @param idNo 身份证号
     * @return 用户信息ID
     */
    public String selectIdByCard(String name, String idNo) {
        SysPersonExample sysPersonExample = new SysPersonExample();
        SysPersonExample.Criteria criteria = sysPersonExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIdNoEqualTo(idNo);
        List<SysPerson> sysPersonList = sysPersonMapper.selectByExample(sysPersonExample);
        if (sysPersonList != null && sysPersonList.size() > 0) {
            return sysPersonList.get(0).getId();
        } else {
            return null;
        }
    }

    /**
     * 验证是否注册
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    @Override
    public List<SysPerson> getSyspersonsByParams(String id,
                                                 Integer idType,
                                                 String name) {
        SysPersonExample example = new SysPersonExample();
        SysPersonExample.Criteria cri = example.createCriteria();
        cri.andNameEqualTo(name);
        cri.andIdTypeEqualTo(idType);
        cri.andIdNoEqualTo(id);
        cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return sysPersonMapper.selectByExample(example);
    }

    /**
     * 判断证件号在person表是否使用过
     *
     * @param idNo 身份证号
     * @return true:已经被使用，false:未被使用
     */
    public boolean isIdNoBinded(String idNo) {
        SysPersonExample sysPersonExample = new SysPersonExample();
        SysPersonExample.Criteria criteria = sysPersonExample.createCriteria();
        criteria.andIdNoEqualTo(idNo);
        criteria.andDeleteFlgEqualTo((short) 0);
        if (sysPersonMapper.countByExample(sysPersonExample) > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 身份验证
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    public boolean checkIdentity(String id,
                                 Integer idType,
                                 String name) {
        return checkId(id, idType, name)
                && checkSysPerson(id, idType, name);
    }
    /**
     * 系统身份验证（查重）
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    private boolean checkSysPerson(String id,
                                   Integer idType,
                                   String name) {
        List<SysPerson> personList = getSyspersonsByParams(id, idType, name);
        return null != personList
                && personList.size() >= 0
                && !isIdNoBinded(id);
    }
    /**
     * 身份证验证
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    public boolean checkId(String id,
                            Integer idType,
                            String name) {
        return PersonIdTypeEnum.ID_CARD_ENUM.getValue() == idType
                && ContractAuthFacet.authID(name, id, sysParamServiceImpl.getSysParamsForAuth());
    }

    /**
     * 如果系统身份不存在则保存身份信息
     *
     * @param conHouseUsers
     */
    @Override
    public void saveIfNotExistInSysPerson(List<ConHouseUser> conHouseUsers) {
        String id;
        Integer idType;
        String name;
        for (ConHouseUser conHouseUser : conHouseUsers) {
            id = conHouseUser.getIdNo();
            idType = conHouseUser.getIdType();
            name = conHouseUser.getName();
            List<SysPerson> personList = getSyspersonsByParams(id, idType, name);
            if (null == personList && personList.size() == 0) {
                SysPerson person = new SysPerson();
                person.setName(name);
                person.setIdType(idType);
                person.setIdNo(id);
                person.setStatus(1);
                person.preInsert();
                person.setCertTime(DateUtils.getSynchTime());
                sysPersonMapper.insertSelective(person);
            }
        }
    }

    @Override
    public String save(SysPerson sysPerson,
                       UsrUser user) {
        if(null!=sysPerson){
            sysPerson.preInsert(user);
            sysPersonMapper.insertSelective(sysPerson);
            return sysPerson.getId();
        }
        return null;
    }
}
