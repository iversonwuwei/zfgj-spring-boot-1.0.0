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
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class ContractStep2InitDTO implements Serializable {

    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 详细地址
     */
    private String houseAddress;

    /**
     * 房屋结构
     */
    private String houseStructure;

    /**
     * 建筑面积
     */
    private Double houseArea;

    /**
     * 房屋户型
     */
    private String houseType;

    /**
     * 产权证件类型
     */
    private String housePropertyIdType;

    /**
     * 产权证件编号
     */
    private String housePropertyId;

    /**
     * 租金分期
     */
    private Integer stages = 0;

    /**
     * 租赁时间（年）
     */
    private String years;

    /**
     * 租赁时间（月）
     */
    private String months;

    /**
     * 租赁起始日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractStartDate;

    /**
     * 租赁结束日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractEndDate;

    /**
     * 出租区域
     */
    private String leaseDomain;

    /**
     * 出租面积
     */
    private BigDecimal leaseArea;

    /**
     * 租赁方式
     */
    private String rentalMode;

    /**
     * 租赁用途
     */
    private String leaseUse;

    /**
     * 使用人数
     */
    private Integer userQuantity;

    /**
     * 房屋使用者
     */
    private HouseUserDTO[] houUserList;

    /**
     * 随行子女
     */
    private ConHouseUserChildrenDTO[] conHouseUserChildrenList;

    /**
     * 合同版本号
     */
    private Integer version;
}
