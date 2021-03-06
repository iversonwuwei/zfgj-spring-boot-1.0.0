package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/3/6.
 */

@Getter
@Setter
@Component
public class HouseOwnerDTO implements Serializable {

    /**
     * 房屋所有权人姓名
     */
    private String name;

    /**
     * 房屋所有权人证件类型
     */
    private String idType;

    /**
     * 房屋所有权人证件号码
     */
    private String idNo;

    /**
     * 房屋所有权人手机号
     */
    private String phone;
}
