package com.dlfc.zfgj.security.mobile.common.json;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/3/29.
 */
@Slf4j
@Component
public class JsonElementSearch implements SearchAble {

    private JSONObject jsonObject;

    @Override
    public Object search(Object json, Object param) {
        jsonObject = (JSONObject) json;
        String elementValue = "";
        try{
            elementValue = jsonObject.getString((String) param);
        }catch (NullPointerException n){
            n.fillInStackTrace();
        }
        return elementValue;
    }
}
