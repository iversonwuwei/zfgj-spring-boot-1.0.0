package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by walden on 2017/2/15.
 */
@Getter
@Setter
@Component
public class ContractDTO implements Serializable {

    /**
     * 合同表主键
     */
    private String id;

    /**
     * 合同编号（不是合同表主键）
     */
    private String contractId;

    /**
     * 签约步骤
     */
    private Integer step;

    /**
     * 创建日期
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String editString;

    /**
     * 房租
     */
    private Integer feeOfMonth;

    /**
     * 合同生效日期
     */
    private Date startDate;

    /**
     * 合同结束日期
     */
    private Date endDate;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 合同状态
     */
    private String contractStatus;

    /**
     * 出租方式
     */
    private Short rentalMode;

    /**
     * 出租区域
     */
    private String leaseDomain;

    /**
     * 房屋详细地址
     */
    private String houseAddress;

    /**
     * 维护人
     */
    private String agentName;
}
