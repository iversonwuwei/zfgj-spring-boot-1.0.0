package com.dlfc.zfgj.security.mobile.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/3/4.
 */

@Getter
@Setter
@Component
public class SysAreaCities implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * CITY_ID
     */
    private String cityId;

    /**
     * CITY
     */
    private String city;

    /**
     * PROVINCE_ID
     */
    private String provinceId;
}
