package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.web.AgtCompConvertor;
import com.dlfc.zfgj.convertor.web.AgtConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.AgtCompDTO;
import com.dlfc.zfgj.dto.web.AgtEmpDTO;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.SMSService;
import com.dlfc.zfgj.service.web.UserHouseService;
import com.dlfc.zfgj.validator.web.HouseValidator;
import com.housecenter.dlfc.framework.starter.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by wsz on 2017/3/7.
 */
@Slf4j
@RestController
@RequestMapping("/w/login")
public class LoginController extends BaseController {
    @Autowired
    @Qualifier("aServiceImpl")
    private AgtService agtService;
    @Autowired
    private AgtConvertor agtConvertor;
    @Autowired
    private HouseValidator agtLogIndexValidator;
    @Autowired
    @Qualifier("smsServiceImpl")
    private SMSService smsService;
    @Autowired
    @Qualifier("us111333")
    private UserHouseService userHouseService;
    @Autowired
    private AgtCompConvertor agtCompConvertor;


    /**
     * 经纪人首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResultDTO<AgtEmpDTO> agtCom(@RequestHeader String token) {
        UsrUser user;
        try {
            user = userHouseService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            return ResultDTO.failure(new AgtEmpDTO(),new ResultError(e.getMessage(),null));
        }
        AgtEmpInfo agtEmpInfo = agtService.getComAll(user.getId());
        return agtConvertor.toResultDTO(agtEmpInfo);
    }

    /**
     * 发送手机验证码
     */
    @RequestMapping(value = "/sendcode", method = RequestMethod.POST)
    public ResultDTO<Void> sendCode(@RequestParam String phone,
                                    HttpServletRequest request) {
        if (StringUtils.isBlank(phone)) {
            return ResultDTO.failure(new ResultError("请输入新手机号",null));
        }
        // 操作状态
        String res = smsService.sendCapcha(phone, "Z0011", "7", request);
        // 操作状态不为空，获取验证码成功
        if (StringUtils.isNotBlank(res)) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("获取验证码失败",null));
        }


    }


    /**
     * 修改经纪人手机号
     */
    @RequestMapping(value = "/ephone", method = RequestMethod.POST)
    public ResultDTO<AgtEmpDTO> updatePhone(@RequestParam String phone,
                                            @RequestParam String code,
                                            @RequestHeader String token) {
        UsrUser usrUser;
        try {
            usrUser = userHouseService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            return ResultDTO.failure(new AgtEmpDTO(),new ResultError(e.getMessage(),null));
        }
        try {
            agtLogIndexValidator.validate(phone, code);
            // 查询验证码不为空
            if (smsService.selectVerCodeCapcha(phone, code) != null) {
                AgtEmpInfo aei = new AgtEmpInfo();
                aei.setUserId(usrUser.getId());
                aei.setPhone(phone);
                // 更新手机信息
                agtService.updatePhone(aei);
                return agtConvertor.toResultDTO(aei);
            } else {
                return ResultDTO.failure(new AgtEmpDTO(),new ResultError("手机号修改失败",null));
            }
        } catch (ApplicationException e) {
            log.info("LoginController updatePhone", e);
            return ResultDTO.failure(new AgtEmpDTO(),new ResultError(e.getMessage(),null));
        }

    }

    /**
     * 经纪人加入公司
     */
    @RequestMapping(value = "/joincom", method = RequestMethod.POST)
    public ResultDTO<Void> joinCom(@RequestParam String comName,
                                   @RequestHeader String token) {

        UsrUser usrUser;
        try {
            usrUser = userHouseService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        try {
            agtService.joinComp(comName, usrUser);
        } catch (ApplicationException e) {
            log.info("LoginController joinCom", e);
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 经纪人取消加入公司
     */
    @RequestMapping(value = "/leaveCom", method = RequestMethod.POST)
    public ResultDTO<Void> leaveCom(@RequestParam String comId) {
        int i = agtService.reJoinComp(comId);
        if (i != 0) {
            return ResultDTO.success();
        }
        return ResultDTO.failure(new ResultError("操作失败",null));
    }

    /**
     * 查询经纪公司
     */
    @RequestMapping(value = "/searchComp", method = RequestMethod.GET)
    public ListResultDTO<AgtCompDTO> searchComp() throws CustomRuntimeException {
        List<AgtCompInfo> compNameList = agtService.getCompInfos();
        return agtCompConvertor.toResultDTO(compNameList);
    }
}
