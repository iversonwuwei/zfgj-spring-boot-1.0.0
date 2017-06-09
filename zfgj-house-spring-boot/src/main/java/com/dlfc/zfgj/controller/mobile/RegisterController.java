package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.mobile.UsrUserConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.mobile.RegisterDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.validator.mobile.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by K on 2017/6/1.
 */

@RestController
@RequestMapping("/m/register")
public class RegisterController {

    @Autowired
    private RegisterValidator validator;
    @Autowired
    private UsrUserConvertor usrUserConvertor;
    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultDTO<Void> register(@RequestBody RegisterDTO registerDTO) {
        try {
            validator.validate(registerDTO);
            UsrUser user = usrUserConvertor.toModel(registerDTO);
            userHouseMService.save(user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(), null));
        }
        return ResultDTO.success();
    }
}
