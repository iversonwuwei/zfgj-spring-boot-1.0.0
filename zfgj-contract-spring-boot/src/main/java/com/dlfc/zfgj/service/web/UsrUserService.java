package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by K on 2017/2/24.
 */
public interface UsrUserService {
    UsrUser getUserByPerId(String perId);

    UsrUser getUser(String token) throws ApplicationException;

    boolean feedBack(String title, String content, HttpServletRequest request, UsrUser user);
}
