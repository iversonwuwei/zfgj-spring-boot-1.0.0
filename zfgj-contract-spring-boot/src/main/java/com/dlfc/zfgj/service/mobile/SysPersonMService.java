package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

public interface SysPersonMService {

    String selectIdByCard(String name, String idNo);

    List<SysPerson> getSyspersonsByParams(String id,
                                          Integer idType,
                                          String name);

    boolean isIdNoBinded(String idNo);

    boolean checkId(String id, Integer idType, String name);

    void saveIfNotExistInSysPerson(List<ConHouseUser> conHouseUsers);

    String save(SysPerson sysPerson, UsrUser user);

    SysPerson getSyspersonsById(String id);
}
