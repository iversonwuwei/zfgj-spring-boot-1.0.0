package com.dlfc.zfgj.security.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlfc.zfgj.security.mobile.common.json.JSONService;
import com.dlfc.zfgj.security.mobile.dto.base.ResultDTO;
import com.dlfc.zfgj.security.mobile.http.actions.DoGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by walden on 2017/4/7.
 */
@RestController
@RequestMapping("/s/tokens")
public class TokenController {

    @Autowired
    @Qualifier(value = "doGetsImpl")
    private DoGet doGet;
    @Autowired
    private JSONService jsonService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultDTO<String> tokenFresh(@RequestHeader String refreshToken){
        Object object = doGet.get(refreshToken);
        JSONObject jsonObject = jsonService.toJsonObject(object);
        String newToken = (String) jsonService.getOjbectFromJsonObject(jsonObject, "data");
        return  ResultDTO.success(newToken);
    }
}
