package com.dlfc.zfgj.service.mobile;

import java.util.Map;

/**
 * Created by wangna on 2017/6/1.
 */
public interface SysParamMService {
    boolean isOn(String scope);

    Map<String, String> getSysParamsForAuth();
}
