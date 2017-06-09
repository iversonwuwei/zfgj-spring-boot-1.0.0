package com.dlfc.zfgj.common.identify;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.housecenter.dlfc.auth.utils.HttpsClient;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by K on 2017/3/21.
 */

@Slf4j
public class UserAuthFacet {

    public UserAuthFacet() {
    }

    public static boolean authID(String name, String idNo, Map<String, String> paramMap) {
        if ("OFF".equals(paramMap.get("on-off"))) {
            log.debug("关闭身份验证开关，验证直接通过【" + name + "】【" + idNo + "】");
            return true;
        } else {
            String url = MessageFormat.format((String) paramMap.get("AuthIDURL"),
                    new Object[]{idNo, HttpsClient.getURLEncodeString(name)});
            if (log.isDebugEnabled()) {
                log.debug("查询URL:[" + url + "]");
            }

            try {
                String e = HttpsClient.connect(url, "http");
                JSONObject jo = JSONArray.parseObject(e);
                if (log.isDebugEnabled()) {
                    log.debug(e);
                }

                if (jo == null || jo.isEmpty() || jo.get("result") == null) {
                    return false;
                }

                JSONObject joj = JSONArray.parseObject(jo.get("result").toString());
                if ("1".equals(joj.getString("res"))) {
                    log.debug("true");
                    return true;
                }
            } catch (Exception var6) {
                log.error("证验证失败", var6);
            }

            return false;
        }
    }
}
