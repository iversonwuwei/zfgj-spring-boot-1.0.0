package com.dlfc.zfgj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.http.actions.DoGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/3/28.
 */
@Component
public class CurrentUser {

    @Autowired
    private DoGet doGet;

    public String getCurrentUser(String token) throws ApplicationException {
        String[] array = (String[]) doGet.get(token);
        String status = array[0];
        String jsonString = array[1];
        JSONObject json = JSON.parseObject(jsonString);
        String data = json.getString("data");
        if (null != data) {
            return data;
        }
        String message = json.getString("message");
        String code = json.getString("code");
        if (!"0".equals(code)) {
            throw new ApplicationException(status, message);
        } else {
            // 以防万一 TODO:
            throw new ApplicationException("", "");
        }
    }
}
