package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/16.
 */
@Getter
@Setter
@Component
public class ContractStep4DTO extends ContractStep3DTO {

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
    private List<DeliverItemDTO> deliverItems;

    /**
     * 其他相关费用
     */
    private List<OtherRelatedItemDTO> otherRelatedItems;
}
