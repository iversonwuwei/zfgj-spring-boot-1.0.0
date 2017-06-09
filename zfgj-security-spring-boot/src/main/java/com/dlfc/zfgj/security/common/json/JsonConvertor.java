package com.dlfc.zfgj.security.common.json;

import com.alibaba.fastjson.*;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/3/29.
 */
@Component
public class JsonConvertor implements Convertor {

    private String jsonString;

    @Override
    public Object convert(Object object) {
        jsonString = (String) object;
        JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(jsonString);
        return jsonObject;
    }
}
