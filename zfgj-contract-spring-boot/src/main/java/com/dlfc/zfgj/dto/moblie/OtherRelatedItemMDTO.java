package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class OtherRelatedItemMDTO {

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
    private Date startDate;

    /**
     * 起计底数
     */
    private String minQuantity;
}
