package com.dlfc.zfgj.security.mobile.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by walden on 2017/3/28.
 */
@Getter
@Setter
public class UsernamePassword {

    private String username;

    private String password;

    private String validateCode;
}
