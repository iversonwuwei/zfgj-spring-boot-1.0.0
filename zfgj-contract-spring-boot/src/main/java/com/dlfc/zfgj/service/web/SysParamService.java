package com.dlfc.zfgj.service.web;

import java.util.Map;

/**
 * Created by K on 2017/2/24.
 */

public interface SysParamService {

    boolean isOn();

    Map<String,String> getSysParamsForAuth();
}
