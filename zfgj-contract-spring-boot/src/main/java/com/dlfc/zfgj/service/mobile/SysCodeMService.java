package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysCode;

import java.util.List;

/**
 * Created by K on 2017/2/20.
 */
public interface SysCodeMService {
    List<SysCode> getCodeByType(String type);
    String getNameByType(String code, String type);
}
