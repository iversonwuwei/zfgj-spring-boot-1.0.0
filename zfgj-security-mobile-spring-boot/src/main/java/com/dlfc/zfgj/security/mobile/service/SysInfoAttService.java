package com.dlfc.zfgj.security.mobile.service;

import com.dlfc.zfgj.security.mobile.entity.SysInfoAtt;

import java.util.List;

/**
 * Created by K on 2017/5/13.
 */
public interface SysInfoAttService {
    List<SysInfoAtt> getSysInfoAttByParams(Integer type,
                                           String lid);
}
