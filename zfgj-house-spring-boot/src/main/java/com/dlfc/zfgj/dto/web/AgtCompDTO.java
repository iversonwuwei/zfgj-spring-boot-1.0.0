package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/5/8.
 */

@Getter
@Setter
@Component
public class AgtCompDTO implements Serializable{

    /**
     * 主键ID
     */
    private String id;
    /**
     * 公司名
     */
    private String compName;
}
