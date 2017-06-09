package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysAreaCities;
import com.dlfc.zfgj.entity.SysAreaProvinces;

import java.util.List;

/**
 * Created by K on 2017/3/4.
 */
public interface SysAreaAreasMService {
    List<SysAreaProvinces> getAllProvinces();
    List<SysAreaCities> getAllCities(String provinceId);
}
