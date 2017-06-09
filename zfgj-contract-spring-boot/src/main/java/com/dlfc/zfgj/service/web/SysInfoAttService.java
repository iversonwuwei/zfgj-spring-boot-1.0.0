package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */
public interface SysInfoAttService {
    List<SysInfoAtt> findList(String lid, Integer... fileTypes);

    void deleteOldRecord(String lid, List<String> ids, UsrUser user);

    String save(SysInfoAtt sysInfoAtt, UsrUser user);

    void deleteOldSingleRecord(String contractId, Integer type, UsrUser user);
}
