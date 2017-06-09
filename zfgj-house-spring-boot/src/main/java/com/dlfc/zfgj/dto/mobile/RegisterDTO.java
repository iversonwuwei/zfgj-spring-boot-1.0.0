package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by K on 2017/6/1.
 */

@Getter
@Setter
public class RegisterDTO {

    @NotNull(message = "手机号不能为空")
    @Min(value = 11, message = "手机号码位数不正确")
    @Max(value = 11, message = "手机号码位数不正确")
    private String mobile;

    @NotNull(message = "密码不能为空")
    @Min(value = 11, message = "密码不得少于8位")
    @Max(value = 11, message = "密码不得多于18位")
    private String password;

    @NotNull(message = "验证码不能为空")
    private String validateCode;

    private String deviceId;
}
