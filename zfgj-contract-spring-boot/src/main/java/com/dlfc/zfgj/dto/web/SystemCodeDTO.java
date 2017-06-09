package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/3/4.
 */
@Setter
@Getter
@Component
public class SystemCodeDTO implements Serializable {

    /**
     * 代码
     */
    private String code;

    /**
     * 名字
     */
    private String name;
}
