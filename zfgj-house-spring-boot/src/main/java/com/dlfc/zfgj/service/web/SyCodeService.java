package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.SysCode;

import java.util.List;

/**
 * Created by K on 2017/2/20.
 */
public interface SyCodeService {
    List<SysCode> getCodeByType(String type);

}
