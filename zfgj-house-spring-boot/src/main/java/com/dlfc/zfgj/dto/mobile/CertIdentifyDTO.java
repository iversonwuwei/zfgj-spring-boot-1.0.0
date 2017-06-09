package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by K on 2017/6/5.
 */

@Getter
@Setter
public class CertIdentifyDTO {

    private List<String> paths;

    @NotNull(message = "从业资格证编号不能为空")
    private String certNo;

    private String uid;
}
