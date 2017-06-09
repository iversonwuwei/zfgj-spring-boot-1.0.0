package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by wsz on 2017/3/8.
 */
public interface SysInfoAttrService {
    SysInfoAtt selectByLidAndType(String lid, Integer type);

    String save(SysInfoAtt sysInfoAtt, UsrUser user);

    int update(SysInfoAtt sysInfoAtt);

    SysInfoAtt saveAndGet(SysInfoAtt sysInfoAtt);

//    SysInfoAtt findByLid(String lid);

    SysInfoAtt findById(String id);

    void deleteSysInfoAttByParams(String lid, Integer type, List<String> idList, UsrUser user);

    List<SysInfoAtt> findByParams(int type, String lid);
}
