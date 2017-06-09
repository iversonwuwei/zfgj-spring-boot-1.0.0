package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.common.identify.ContractAuthFacet;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysPersonExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.PersonIdTypeEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.service.web.SysParamService;
import com.dlfc.zfgj.service.web.SysPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

@Slf4j
@Transactional
@Service
public class SysPersonServiceImpl implements SysPersonService {

    @Autowired
    protected SysPersonMapper sysPersonMapper;

    @Autowired
    protected SysParamService sysParamServiceImpl;

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
        boolean searchFlg = false;
        if (StringUtils.isNotEmpty(name)) {
            cri.andNameEqualTo(name);
            searchFlg = true;
        }
        if (null != idType) {
            cri.andIdTypeEqualTo(idType);
            searchFlg = true;
        }
        if (StringUtils.isNotEmpty(id)) {
            cri.andIdNoEqualTo(id);
            searchFlg = true;
        }
        if (searchFlg) {
            cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            return sysPersonMapper.selectByExample(example);
        }
        return null;
    }

    /**
     * 判断证件号在person表是否使用过
     *
     * @param idNo 身份证号
     * @return true:已经被使用，false:未被使用
     */
    public boolean checkIdNoBinded(String idNo) {
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
    @Override
    public boolean checkIdentity(String id,
                                 Integer idType,
                                 String name) {
        return checkId(id, idType, name);
//                && checkSysPerson(id, idType, name);
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

    /**
     * 验证是否成年
     *
     * @param idType
     * @param idNo
     * @return
     */
    @Override
    public boolean checkAge(Integer idType,
                            String idNo) {
        if (PersonIdTypeEnum.ID_CARD_ENUM.getValue() != idType) {
            return false;
        }
        int birthday, idLength = idNo.length();
        if (idLength == 18) {
            birthday = Integer.valueOf(idNo.substring(6, 14));
        } else if (idLength == 15) {
            birthday = Integer.valueOf("19" + idNo.substring(6, 12));
        } else {
            return true;
        }
        int systemDay = Integer.valueOf(DateUtils.format.format(DateUtils.getSynchTime()));
        if (systemDay - birthday < 180000) {
            return true;
        }
        return false;
    }

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
     * 身份证验证
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    private boolean checkId(String id,
                            Integer idType,
                            String name) {
        if (PersonIdTypeEnum.ID_CARD_ENUM.getValue() == idType) {
            return ContractAuthFacet.authID(name, id, sysParamServiceImpl.getSysParamsForAuth());
        }
        return true;
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
                && personList.size() > 0;
    }
}
