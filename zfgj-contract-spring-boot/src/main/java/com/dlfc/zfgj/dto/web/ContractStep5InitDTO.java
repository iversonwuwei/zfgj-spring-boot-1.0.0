package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/6.
 */

@Getter
@Setter
@Component
public class ContractStep5InitDTO {

    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 出租方姓名
     */
    private String lessorName;

    /**
     * 出租方证件类型
     */
    private String lessorIdType;

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
    private String tenantryIdType;

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
     * 承租方户籍所在省（value）
     */
    private String tenantryProvince;

    /**
     * 承租方户籍所在市（value）
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
     * 合同版本号
     */
    private Integer version;

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
     * 租赁总时间
     */
    private String totalMonths;

    /**
     * 租赁方式
     */
    private String rentalMode;

    /**
     * 租赁用途
     */
    private String leaseUse;

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
     * 月租金
     */
    private Integer monthlyRent;

    /**
     * 结算周期 月，季度，半年，年
     */
    private String settlement;

    /**
     * 房主承担（结果）
     */
    private String[] ownerResponsibility;

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
     * 甲乙丙三方其他约定
     */
    private String others;

    /**
     * 房屋交割清单
     */
    private List<DeliverItemDTO> deliverItems;

    /**
     * 其他相关费用
     */
    private List<OtherRelatedItemDTO> otherRelatedItems;

    /**
     * 房屋共有人列表
     */
    private List<HouseOwnerDTO> houseOwnerDTOList;

    /**
     * 房主承担的费用（总）
     */
    private String houseOwnerBearTotal;

    /**
     * 房客承担的费用（总）
     */
    private String houseUserBearTotal;

    /**
     * 签约当日甲方提供的证件原件（总）
     */
    private String lessorPaperTotal;

    /**
     * 签约当日乙方提供的证件原件（总）
     */
    private String lesseePaperTotal;

    /**
     * 审核结果
     */
    private Boolean examineResult;

    /**
     * 附件列表
     */
    private List<SystemAttachmentDTO> systemAttachmentDTOList;

    /**
     * 加密
     */
    private String accessKey;

    /**
     * 居间方公司名称
     */
    private String AgtCompName;

    /**
     * 居间方签约人
     */
    private String AgtName;

    /**
     * 居间方电话
     */
    private String AgtPhone;

    /**
     * 修改按钮是否显示
     */
    private boolean modifyButton;

    /**
     * 下载按钮是否显示
     */
    private boolean downloadButton;

    /**
     * 上传按钮是否显示
     */
    private boolean uploadButton;

    /**
     * 结算时间
     */
    private List<String> paymentExplanation;

    /**
     * 拒绝原因
     */
    private String rejectReason;
    /**
     * 随住子女
     */
    private List<ConHouseUserChildrenDTO> childrenMDTOS;
}
