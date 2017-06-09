package com.dlfc.zfgj.security.mobile.common.json;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by walden on 2017/3/29.
 */
public interface JSONService {

    JSONObject toJsonObject(Object obj);

    Object getOjbectFromJsonObject(JSONObject parent, String key);
}
