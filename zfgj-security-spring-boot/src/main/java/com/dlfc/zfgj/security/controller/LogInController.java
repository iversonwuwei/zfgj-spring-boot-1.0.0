package com.dlfc.zfgj.security.controller;

import com.dlfc.zfgj.security.convertor.web.LoginConvertor;
import com.dlfc.zfgj.security.dto.base.ResultDTO;
import com.dlfc.zfgj.security.dto.web.LoginDTO;
import com.dlfc.zfgj.security.entity.UsernamePassword;
import com.dlfc.zfgj.security.exception.CustomRuntimeException;
import com.dlfc.zfgj.security.http.actions.DoPost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by walden on 2017/3/28.
 */
@Api(tags = "web login", description = "web 登录页面")
@RestController
@RequestMapping(value = "/login")
public class LogInController {

    @Autowired
    private DoPost doPost;
    @Autowired
    private LoginConvertor loginConvertor;

    /**
     * Web端登录接口
     *
     * @param usernamePassword
     * @return
     * @throws CustomRuntimeException
     */
    @ApiOperation("插入用户信息")
    @ApiImplicitParam(name = "usernamePassword", value = "用户详细实体User",
            required = true, dataType = "UsernamePassword")
    @RequestMapping(value = "/web", method = RequestMethod.POST)
    public ResultDTO<LoginDTO> loginForWeb(@RequestBody UsernamePassword usernamePassword)
            throws CustomRuntimeException {
        Object object = doPost.post(usernamePassword);
        LoginDTO loginDTO = loginConvertor.toDTO(object);
        if (loginDTO.isResult()){
            return ResultDTO.success(loginDTO);
        }
        return ResultDTO.failure(loginDTO);
    }
}
