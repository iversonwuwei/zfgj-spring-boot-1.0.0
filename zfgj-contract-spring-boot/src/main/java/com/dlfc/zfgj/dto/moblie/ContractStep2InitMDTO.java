package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class ContractStep2InitMDTO implements Serializable{

    /**
     * 详细地址
     */
    private String houseAddress;

    /**
     * 房屋结构
     */
    private Integer houseStructure;

    /**
     * 建筑面积
     */
    private double houseArea;

    /**
     * 产权证件类型
     */
    private Integer housePropertyIdType;

    /**
     * 产权证件编号
     */
    private String housePropertyId;

    /**
     * 租金分期
     */
    private Integer stages;

    /**
     * 租赁时间（年）
     */
    private Integer years;

    /**
     * 租赁时间（月）
     */
    private Integer months;

    /**
     * 租赁起始日期
     */
    private Date contractStartDate;

    /**
     * 租赁结束日期
     */
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
    private Integer rentalMode;

    /**
     * 租赁用途
     */
    private Integer leaseUse;

    /**
     * 使用人数
     */
    private Integer userQuantity;

    /**
     * 房屋使用者
     */
    private List<HouseUserMDTO> houUserList;

    /**
     * 合同版本号
     */
    private Integer version;
}
