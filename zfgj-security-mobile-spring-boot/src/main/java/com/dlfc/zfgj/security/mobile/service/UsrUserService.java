package com.dlfc.zfgj.security.mobile.service;

import com.dlfc.zfgj.security.mobile.entity.UsrUser;

/**
 * Created by K on 2017/2/24.
 */
public interface UsrUserService {
//    UsrUser getUserByPerId(String perId);

    UsrUser findByUserName(String username);

//    UsrUser getUserByUserName(String username);
}
