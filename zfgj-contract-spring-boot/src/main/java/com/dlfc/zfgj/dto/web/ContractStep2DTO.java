package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.enums.RentalPurposeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by walden on 2017/2/16.
 */

@Getter
@Setter
@Component
public class ContractStep2DTO extends ContractStep1DTO {

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
    private Double houseArea;

    /**
     * 房屋户型
     */
    private String houseType;

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
    private Integer stages = 0;

    /**
     * 租赁时间（年）
     */
    private Integer years;

    /**
     * 租赁时间（月）
     */
    private Integer months = 1;

    /**
     * 租赁起始日期
     */
    private Date contractStartDate;

    /**
     * 租赁结束日期
     */
    private Date contractEndDate;

    /**
     * 租赁方式
     */
    private Integer rentalMode = RentalModeEnum.WHOLE_RENT_ENUM.getValue();

    /**
     * 租赁用途
     */
    private Integer leaseUse = RentalPurposeEnum.CIVIL_USE_ENUM.getValue();

    /**
     * 出租区域
     */
    private String leaseDomain;

    /**
     * 出租面积
     */
    private BigDecimal leaseArea;

    /**
     * 使用人数
     */
    private Integer userQuantity = 1;

    /**
     * 房屋使用者
     */
    private List<HouseUserDTO> houUserList;

    /**
     * 随行子女
     */
    private List<ConHouseUserChildrenDTO> conHouseUserChildrenDTOS;
}
