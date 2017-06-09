package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.UsrUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by walden on 2017/2/22.
 */
public interface UserMService {

    UsrUser findOne(String username);

    String codeSend(String phone, String domain) throws Exception;

    boolean changePassword(String password,
                           UsrUser usrUser);

    boolean forgetPassword(String phone,
                           String password,
                           String code,
                           String msgId) throws Exception;

    boolean validatePassword(String plainPassword, String password);

    boolean feedBack(String count, HttpServletRequest request, UsrUser usrUser);
}
