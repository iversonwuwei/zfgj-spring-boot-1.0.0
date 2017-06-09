package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class ContractStep1DTO {

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
}
