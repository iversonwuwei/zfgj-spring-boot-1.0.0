package com.dlfc.zfgj.security.common.json;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by walden on 2017/3/29.
 */
@Service
@Transactional
public class JSONServiceImpl implements JSONService {

    @Autowired
    private Convertor convertor;
    @Autowired
    private SearchAble searchAble;

    @Override
    public JSONObject toJsonObject(Object obj) {
        return (JSONObject) convertor.convert(obj);
    }

    @Override
    public Object getOjbectFromJsonObject(JSONObject parent, String key) {
        return searchAble.search(parent, key);
    }

}
