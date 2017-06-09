package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by wsz on 2017/3/8.
 */
public interface SysInfoAttrMService {
    SysInfoAtt selectByLidAndType(String lid, int type);

    int save(SysInfoAtt sysInfoAtt);

    String save(SysInfoAtt entity, UsrUser user);

    int update(SysInfoAtt sysInfoAtt);

    SysInfoAtt saveAndGet(SysInfoAtt sysInfoAtt);

    SysInfoAtt saveRoomAndGet(SysInfoAtt sysInfoAtt, String empId);

    void deleteSysInfoAttByParams(String lid,
                                  Integer type);

    void saveWithLidAndPaths(String id, List<String> paths, int type, UsrUser user);
}
