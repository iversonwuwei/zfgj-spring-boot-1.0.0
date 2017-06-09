package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangna on 2017/6/1.
 */
@Getter
@Setter
@Component
public class MyHouseMDTO {
    /**
     * 已发布房源
     */
    private List<HouseMDTO> releaseHouse;
    /**
     * 未发布房源
     */
    private List<HouseMDTO> unReleaseHouse;

    /**
     * 未发布房源数量
     */
    private String unReleaseCount;
    /**
     * 发布房源数量
     */
    private String releaseCount;

}
