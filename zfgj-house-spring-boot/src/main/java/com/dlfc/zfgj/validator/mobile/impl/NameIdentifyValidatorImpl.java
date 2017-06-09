package com.dlfc.zfgj.validator.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.common.identify.UserAuthFacet;
import com.dlfc.zfgj.dto.mobile.NameIdentifyDTO;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.mobile.SysParamMService;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.service.web.SystemPersonService;
import com.dlfc.zfgj.validator.mobile.NameIdentifyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class NameIdentifyValidatorImpl implements NameIdentifyValidator {

    @Autowired
    @Qualifier("sysParamService")
    private SysParamMService sysParamMService;
    @Autowired
    private SystemPersonService systemPersonService;
    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;

    @Override
    public void validate(NameIdentifyDTO nameIdentifyDTO) throws ApplicationException {
        String name = nameIdentifyDTO.getName();
        String idNo = nameIdentifyDTO.getIdNo();
        List<SysPerson> sysPersonList = systemPersonService.findByNameAndIdNo(name, idNo);
        if (null != sysPersonList && sysPersonList.size() > 0) {
            List<UsrUser> usrUserList = userHouseMService.findByPerId(sysPersonList.get(0).getId());
            if (null != usrUserList && usrUserList.size() > 0) {
                throw new ApplicationException("该身份证已经注册过别的账户");
            }
        }
        Map<String, String> map = sysParamMService.getSysParamsForAuth();
        if (!UserAuthFacet.authID(name, idNo, map)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0009"));
        }
    }
}
