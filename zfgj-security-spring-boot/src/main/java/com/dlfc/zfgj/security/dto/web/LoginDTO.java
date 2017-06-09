package com.dlfc.zfgj.security.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/29.
 */

@Getter
@Setter
@Component
public class LoginDTO {
    /**
     * 登录用Token
     */
    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 可否编辑房源
     */
    private boolean houseEditable;

    /**
     * 可否编辑合同
     */
    private boolean contractEditable;

    /**
     * 可否查看房源
     */
    private boolean houseViewable;

    /**
     * 可否查看合同
     */
    private boolean contractViewable;

    /**
     * 可否删除房源
     */
    private boolean houseDeletable;

    /**
     * 可否删除合同
     */
    private boolean contractDeletable;

    /**
     * 登录错误信息
     */
    private String message;

    /**
     * 登录结果
     */
    private boolean result;

    /**
     * 经纪人是否在职
     */
    private int agtOnJob;

    /**
     * 经纪人是否有资格证
     */
    private int agtJoin;

    /**
     * 经纪人审核状态
     */
    private int agtAuditStatus;
}
