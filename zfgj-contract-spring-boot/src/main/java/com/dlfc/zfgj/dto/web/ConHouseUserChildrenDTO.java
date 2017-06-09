package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangna on 2017/6/9.
 */
@Getter
@Setter
@Component
public class ConHouseUserChildrenDTO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 生日日期
     */
    private Date birthday;
}
