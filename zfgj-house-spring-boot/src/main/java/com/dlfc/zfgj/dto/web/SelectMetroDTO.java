package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wsz on 2017/3/16.
 */
@Getter
@Setter
public class SelectMetroDTO {


    /**
     * 地铁站名字
     */
    private String name;


    /**
     * 地铁站Id
     */
    private String id;

    /**
     * 地铁站缩写
     */
    private String spelling;
}
