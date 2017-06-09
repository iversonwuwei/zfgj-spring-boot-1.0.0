package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by K on 2017/6/5.
 */

@Getter
@Setter
public class NameIdentifyDTO {

    @NotNull(message = "名字不能为空")
    private String name;

    @NotNull(message = "身份证号码不能为空")
    @Size(min = 18, max = 18, message = "请输入正确位数的身份证号码")
    private String idNo;
}
