package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wsz on 2017/3/16.
 */
@Getter
@Setter
public class SelectCompCodeDTO {


    /**
     * 公司名字
     */
    private String compFullName;


    /**
     * 名称简拼
     */
    private String sspelling;

    /**
     * 名称全拼
     */
    private String aspelling;
    /**
     * 公司主键id
     */
    private String id;
}
