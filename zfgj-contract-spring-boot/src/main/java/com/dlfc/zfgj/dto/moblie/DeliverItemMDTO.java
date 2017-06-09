package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class DeliverItemMDTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 损赔金额
     */
    private BigDecimal compensation;
}
