package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.RegisterDTO;
import com.dlfc.zfgj.entity.SysMobileCapcha;
import com.dlfc.zfgj.enums.MsgEnums;
import com.dlfc.zfgj.enums.TemplateNoEnum;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class SysMobileCapchaConvertor extends AbstractConvertor<SysMobileCapcha, RegisterDTO> {
    @Override
    public SysMobileCapcha toModel(RegisterDTO dto) {
        SysMobileCapcha model = new SysMobileCapcha();
        if (null != dto) {
            model.setMobile(dto.getMobile());
            model.setDeviceId(dto.getDeviceId());
            model.setVerCode(dto.getValidateCode());
            model.setDomain(MsgEnums.REGESIT.getValue());
            model.setTemplateId(TemplateNoEnum.REGESIT_NO.getValue());
        }
        return model;
    }

    @Override
    public RegisterDTO toDTO(SysMobileCapcha model, boolean forListView) {
        return null;
    }
}
