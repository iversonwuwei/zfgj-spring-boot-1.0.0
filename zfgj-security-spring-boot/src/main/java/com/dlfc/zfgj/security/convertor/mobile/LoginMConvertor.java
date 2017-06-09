package com.dlfc.zfgj.security.convertor.mobile;

import com.dlfc.zfgj.security.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.security.dto.mobile.LoginMDTO;
import com.dlfc.zfgj.security.entity.UsrUser;
import org.springframework.stereotype.Component;

/**
 * Created by wanglijun on 2017/3/29.
 */
@Component
public class LoginMConvertor extends AbstractConvertor<UsrUser,LoginMDTO> {

    @Override
    public UsrUser toModel(LoginMDTO loginMDTO) {
        return null;
    }

    @Override
    public LoginMDTO toDTO(UsrUser model, boolean forListView) {

        LoginMDTO loginMDTO = new LoginMDTO();
        loginMDTO.setToken("");
        loginMDTO.setUId(model.getId());
        loginMDTO.setEmpId(model.getEmpId());
        loginMDTO.setName(model.getPersonName());
        loginMDTO.setUsername(model.getUsername());
        loginMDTO.setPhone(model.getPhone());
        loginMDTO.setUrl(model.getAvatar());
        loginMDTO.setCompany(model.getCompanyName());
        loginMDTO.setFlag(model.getFlag());
        loginMDTO.setGrade(model.getScore());
        loginMDTO.setUserIdentity(model.getUserIdentity());
        return loginMDTO;
    }
}
