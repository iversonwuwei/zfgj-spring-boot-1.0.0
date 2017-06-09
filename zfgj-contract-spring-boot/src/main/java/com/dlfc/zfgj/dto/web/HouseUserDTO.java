package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 实际使用者
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class HouseUserDTO {

    /**
     * 姓名
     */
    private String houseUserName;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 证件类型
     */
    private String identityMode;

    /**
     * 证件类型名
     */
    private String identityModeName;

    /**
     * 证件号码
     */
    private String idNumber;
}
