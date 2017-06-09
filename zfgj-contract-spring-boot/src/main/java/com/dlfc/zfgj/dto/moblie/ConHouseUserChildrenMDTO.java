package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 随行子女
 * Created by wangna on 2017/6/8.
 */
@Getter
@Setter
@Component
public class ConHouseUserChildrenMDTO {

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
