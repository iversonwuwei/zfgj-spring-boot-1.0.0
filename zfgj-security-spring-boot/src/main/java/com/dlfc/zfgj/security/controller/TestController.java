package com.dlfc.zfgj.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlfc.zfgj.security.common.json.JSONService;
import com.dlfc.zfgj.security.entity.UsernamePassword;
import com.dlfc.zfgj.security.exception.CustomRuntimeException;
import com.dlfc.zfgj.security.http.actions.DoPost;
import com.housecenter.dlfc.framework.common.web.AjaxResult;
import com.housecenter.dlfc.framework.common.web.AjaxResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by walden on 2017/3/28.
 */
@RestController
@RequestMapping("/login")
public class TestController {

    @Autowired
    private DoPost doPost;
    @Autowired
    private JSONService jsonService;


    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestBody UsernamePassword usernamePassword) throws CustomRuntimeException {

        JSONObject jsonObject = jsonService.toJsonObject(doPost.post(usernamePassword));
        String elementValue = (String) jsonService.getOjbectFromJsonObject(jsonObject, "token");
        return (String) doPost.post(usernamePassword);
    }

    @RequestMapping(value = "/testResults", method = RequestMethod.POST)
    public Object testAjaxResults(){
        String name = "Walden";
        AjaxResult ajaxResult = AjaxResultBuilder.build(name);
        String test = ajaxResult.getMessage();
        return ajaxResult;
    }
}
