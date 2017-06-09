package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.RegisterDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.PasswordLevelEnum;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class UsrUserConvertor extends AbstractConvertor<UsrUser, RegisterDTO> {

    @Autowired
    private UserHouseMService userHouseMService;

    @Override
    public UsrUser toModel(RegisterDTO dto) {
        UsrUser model = new UsrUser();
        if (null != dto) {
            model.setMobile(dto.getMobile());
            model.setPassword(userHouseMService.generatePassword(dto.getPassword()));
            model.setUsername(userHouseMService.generateUsername(dto.getMobile()));
            model.setPasswordLevel(PasswordLevelEnum.LEVEL_LOW.getValue());
        }
        return model;
    }

    @Override
    public RegisterDTO toDTO(UsrUser model, boolean forListView) {
        return null;
    }
}
