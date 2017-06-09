package com.dlfc.zfgj.dto.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/5.
 */
@Setter
@Getter
@Component
public class HouseFreshMDTO {
    /**
     * 刷新时间
     */
    private String freshTime;

    /**
     * 发布时间
     */
    private String releaseTime;
}
