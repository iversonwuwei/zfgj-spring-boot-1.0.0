package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/4/9.
 */

@Component
@Getter
@Setter
public class ContractShowUpDTO {

    /**
     * 图片类型
     */
    private String type;

    /**
     * 图片地址
     */
    private String path;
}
