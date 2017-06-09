package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class ContractStep4InitMDTO implements Serializable{

    /**
     * 签约当日甲方提供的证件原件列表（条件）
     */
    private List<SystemCodeMDTO> lessorLicenseList;

    /**
     * 签约当日乙方提供的证件原件列表（条件）
     */
    private List<SystemCodeMDTO> lesseeLicenseList;

    /**
     * 签约当日甲方提供的证件原件（结果）
     */
    private String[] lessorPapers;
    /**
     * 签约当日甲方提供的其他证件
     */
    private String otherLessorPapers;

    /**
     * 签约当日乙方提供的证件原件（结果）
     */
    private String[] tenantryPapers;
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
     * 合同版本号
     */
    private Integer version;
}
