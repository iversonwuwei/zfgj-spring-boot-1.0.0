package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class OtherRelatedItemDTO {

    /**
     * 项目
     */
    private String item;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 起计时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    /**
     * 起计底数
     */
    private String minQuantity;
}
