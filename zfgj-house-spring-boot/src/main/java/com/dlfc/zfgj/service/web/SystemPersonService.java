package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/4/5.
 */
public interface SystemPersonService {
    SysPerson getSysPersonById(String id);

    String save(SysPerson sysPerson, UsrUser user);

    List<SysPerson> findByNameAndIdNo(String name, String idNo);
}
