package com.dlfc.zfgj.security.dto.mobile;

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

    private String uId;

    private String empId;

    private String username;

    private String phone;

    private String name;

    private String company;

    private String grade;

    private int flag;

    private String url;

    private Short userIdentity;
}
