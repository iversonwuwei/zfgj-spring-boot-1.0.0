package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 实际使用者
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class HouseUserMDTO {

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
    private Integer identityMode;

    /**
     * 证件号码
     */
    private String idNumber;

    /**
     * 验证结果
     */
    private boolean validateResult = true;
}
