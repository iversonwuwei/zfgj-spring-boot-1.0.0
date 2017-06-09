package com.dlfc.zfgj.validator.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.convertor.mobile.SysMobileCapchaConvertor;
import com.dlfc.zfgj.dto.mobile.RegisterDTO;
import com.dlfc.zfgj.entity.SysMobileCapcha;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.MsgEnums;
import com.dlfc.zfgj.service.mobile.SysMobileCapchaMSerivce;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.validator.mobile.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class RegisterValidatorImpl implements RegisterValidator {

    private static final int TIME_SECONDS = 1800;

    private SysMobileCapcha sysMobileCapcha;
    private List<SysMobileCapcha> sysMobileCapchaList;

    private List<UsrUser> usrUserList;

    @Autowired
    private SysMobileCapchaConvertor sysMobileCapchaConvertor;
    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;
    @Autowired
    @Qualifier("SysMobileCapchaMSerivce")
    private SysMobileCapchaMSerivce sysMobileCapchaMSerivce;

    @Override
    public void validate(RegisterDTO registerDTO) throws ApplicationException {
        sysMobileCapcha = sysMobileCapchaConvertor.toModel(registerDTO);
        sysMobileCapcha.setDomain(MsgEnums.REGESIT.getValue());
        sysMobileCapchaList = sysMobileCapchaMSerivce.findByParams(sysMobileCapcha);
        if (null == sysMobileCapchaList || sysMobileCapchaList.size() == 0) {
            throw new ApplicationException("验证码错误");
        }
        sysMobileCapcha = sysMobileCapchaList.get(0);
        int seconds = DateUtils.getSecondBetweenDate(sysMobileCapcha.getCreateTime(), new Date());
        if (seconds > TIME_SECONDS) {
            throw new ApplicationException("验证码已失效，请重新发送");
        }
        usrUserList = userHouseMService.findByMobile(registerDTO.getMobile());
        if (null != usrUserList && usrUserList.size() > 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0011"));
        }
    }
}
