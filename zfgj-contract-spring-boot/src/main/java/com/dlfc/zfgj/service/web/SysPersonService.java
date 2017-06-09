package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

public interface SysPersonService {

    List<SysPerson> getSyspersonsByParams(String id,
                                          Integer idType,
                                          String name);

    boolean checkIdNoBinded(String idNo);

    boolean checkIdentity(String id, Integer idType, String name);

    void saveIfNotExistInSysPerson(List<ConHouseUser> conHouseUsers);

    boolean checkAge(Integer idType, String perId);

    SysPerson getSyspersonsById(String id);

    String save(SysPerson sysPerson, UsrUser user);
}
