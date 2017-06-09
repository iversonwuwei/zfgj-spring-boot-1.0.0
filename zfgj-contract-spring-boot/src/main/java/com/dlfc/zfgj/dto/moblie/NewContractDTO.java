package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 2017/3/20.
 */
@Getter
@Setter
@Component
public class NewContractDTO {
    /**
     * 出租方姓名
     */
    private String lessorName;

    /**
     * 出租方证件类型
     */
    private Integer lessorIdType;

    /**
     * 出租方证件号码
     */
    private String lessorId;

    /**
     * 出租方电话号码
     */
    private String lessorPhoneNumber;

    /**
     * 出租方邮件地址
     */
    @Email
    private String lessorEmail;

    /**
     * 出租方送达地址
     */
    private String lessorDeliverAddress;

    /**
     * 出租方备用联系人姓名
     */
    private String lessorContactName;

    /**
     * 出租方备用联系号码
     */
    private String lessorContactPhoneNumber;

    /**
     * 承租方姓名
     */
    private String tenantryName;

    /**
     * 承租方身份证类型
     */
    private Integer tenantryIdType;

    /**
     * 承租方身份证号码
     */
    private String tenantryId;

    /**
     * 承租方电话号码
     */
    private String tenantryPhoneNumber;

    /**
     * 承租方民族
     */
    private Integer tenantryNation;

    /**
     * 承租方户籍所在省
     */
    private String tenantryProvince;

    /**
     * 承租方户籍所在市
     */
    private String tenantryCity;

    /**
     * 承租方邮件地址
     */
    @Email
    private String tenantryEmail;

    /**
     * 承租方送达地址
     */
    private String tenantryDeliverAddress;

    /**
     * 承租方备用联系人姓名
     */
    private String tenantryContactName;

    /**
     * 承租方备用联系号码
     */
    private String tenantryContactPhoneNumber;

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
     * 租赁方式
     */
    private Integer rentalMode;

    /**
     * 租赁用途
     */
    private Integer leaseUse;

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
    private Integer userQuantity;

    /**
     * 房屋使用者
     */
    private List<HouseUserMDTO> houUserList;

    /**
     * 房屋使用者验证结果
     */
    private boolean houUserListFlg;

    /**
     * 合同版本号
     */
    private Integer version;
    /**
     * 月租金
     */
    private Integer monthlyRent;

    /**
     * 结算周期 月，季度，半年，年
     */
    private Integer settlement;

    /**
     * 房主承担（结果）
     */
    private List<String> ownerResponsibility;

    /**
     * 房主承担其他
     */
    private String ownerBearOther;

    /**
     * 中介费
     */
    private BigDecimal agentFee;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 申请押金监管标识
     */
    private boolean depositSuperviseFlg;
    /**
     * 签约当日甲方提供的证件原件（结果）
     */
    private List<String> lessorPapers;
    /**
     * 签约当日甲方提供的其他证件
     */
    private String otherLessorPapers;

    /**
     * 签约当日乙方提供的证件原件（结果）
     */
    private List<String> tenantryPapers;
    /**
     * 签约当日乙方提供的其他证件
     */
    private String otherTenantryPapers;

    /**
     * 甲乙丙三方其他约定
     */
    private String others;

    /**
     * 房屋交割清单
     */
    private List<DeliverItemMDTO> deliverItems;

    /**
     * 其他相关费用
     */
    private List<OtherRelatedItemMDTO> otherRelatedItems;

    /**
     * 签名图片
     */
    private List<String> imgUrls;

    /**
     * 随行子女
     */
    private List<ConHouseUserChildrenMDTO> childrenMDTOS;
}
