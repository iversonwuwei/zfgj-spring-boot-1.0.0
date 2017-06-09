package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wsz on 2017/3/16.
 */
@Getter
@Setter
public class SelectBusinessAreaDTO {


    /**
     * 城市区域
     */
    private String Name;

    /**
     * 上级城市区域Id
     */
    private String panentId;

    /**
     * 商圈名称简写
     */
    private String spell;

    /**
     * 主键id
     */
    private String id;

    /**
     * 商圈id
     */
    private String tradeId;

}
