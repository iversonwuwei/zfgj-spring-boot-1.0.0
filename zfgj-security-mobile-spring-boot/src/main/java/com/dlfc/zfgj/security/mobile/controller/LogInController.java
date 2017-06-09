package com.dlfc.zfgj.security.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlfc.zfgj.security.mobile.common.json.JSONService;
import com.dlfc.zfgj.security.mobile.convertor.mobile.LoginMConvertor;
import com.dlfc.zfgj.security.mobile.dto.base.ResultDTO;
import com.dlfc.zfgj.security.mobile.dto.mobile.LoginMDTO;
import com.dlfc.zfgj.security.mobile.entity.UsernamePassword;
import com.dlfc.zfgj.security.mobile.error.ResultError;
import com.dlfc.zfgj.security.mobile.exception.CustomRuntimeException;
import com.dlfc.zfgj.security.mobile.http.actions.DoPost;
import com.dlfc.zfgj.security.mobile.service.UsrUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by walden on 2017/3/28.
 */
@Api(tags = "web login", description = "web 登录页面")
@com.housecenter.dlfc.framework.starter.web.annotation.RestController
@RequestMapping(value = "/login")
public class LogInController {

    @Autowired
    private DoPost doPost;
    @Autowired
    private JSONService jsonService;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private LoginMConvertor loginMConvertor;

    /**
     * Mobile端登录接口
     *
     * @param usernamePassword
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/mobile", method = RequestMethod.POST)
    public ResultDTO<LoginMDTO> login(@RequestBody UsernamePassword usernamePassword) throws CustomRuntimeException{
        LoginMDTO loginMDTO = null;
        JSONObject jsonObject = jsonService.toJsonObject(doPost.post(usernamePassword));
        String code = (String) jsonService.getOjbectFromJsonObject(jsonObject, "code");
        String message = (String) jsonService.getOjbectFromJsonObject(jsonObject, "message");
        String userName = (String) jsonService.getOjbectFromJsonObject(jsonObject, "data");
        String token = (String) jsonService.getOjbectFromJsonObject(jsonObject, "token");
        String status = (String) jsonService.getOjbectFromJsonObject(jsonObject, "success");
        String refreshToken = (String) jsonService.getOjbectFromJsonObject(jsonObject, "refreshToken");
        if(status.equals("0")){
            loginMDTO = new LoginMDTO();
            loginMDTO.setMessage(message);
            loginMDTO.setCode(code);
            ResultError resultError = new ResultError();
            resultError.setErrcode(code);
            resultError.setErrmsg(message);
            return ResultDTO.failure(loginMDTO,resultError);
        }else {
            loginMDTO = loginMConvertor.toDTO(usrUserService.findByUserName(userName));
            loginMDTO.setToken(token);
            loginMDTO.setRefreshToken(refreshToken);
            loginMDTO.setMessage(message);
            loginMDTO.setCode(code);
            return ResultDTO.success(loginMDTO);
        }
    }
}
