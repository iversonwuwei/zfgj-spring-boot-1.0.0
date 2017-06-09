package com.dlfc.zfgj.security.mobile.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by wanglijun on 2017/3/29.
 */
@Getter
@Setter
@Component
public class LoginMDTO implements Serializable {

    private String token;

    private String refreshToken;

    private String code;

    private String message;

    private String uId;

    private String empId;

    private String username;

    private String phone;

    private String name;

    private String company;

    private String grade;

    private String url;

    private Short userIdentity;

    private String nameResult;

    private String certResult;

    private String joinResult;
}
