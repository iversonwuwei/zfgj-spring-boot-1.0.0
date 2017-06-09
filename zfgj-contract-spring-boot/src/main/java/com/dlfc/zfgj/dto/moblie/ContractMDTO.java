package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by walden on 2017/2/15.
 */
@Getter
@Setter
@Component
public class ContractMDTO implements Serializable {

    /**
     * 合同编号
     */
    private String contractId;

    /**
     * 创建日期
     */
    private String createdDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 房租
     */
    private Integer feeOfMonth;

    /**
     * 出租方姓名
     */
    private String lessorName;

    /**
     * 承租方姓名
     */
    private String tenantryName;

    /**
     * 中介费
     */
    private BigDecimal agentFee;
}
