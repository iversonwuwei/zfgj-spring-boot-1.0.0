package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by wanglijun on 2017/3/30.
 */
@Getter
@Setter
@Component
public class ContractCountMDTO implements Serializable {
    /**
     * 未上传合同
     */
    private Integer count;
}
