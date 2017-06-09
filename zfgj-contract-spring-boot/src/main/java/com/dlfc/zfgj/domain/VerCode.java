package com.dlfc.zfgj.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by wanglijun on 2017/3/31.
 */
@Getter
@Setter
@Component
public class VerCode {
    /**
    * 手机号
    */
    private String mobile;

    /**
     * 业务域（1：注册，2：找回密码）
     */
    private String domain;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 客户端IP
     */
    private String phoneIp;

}
