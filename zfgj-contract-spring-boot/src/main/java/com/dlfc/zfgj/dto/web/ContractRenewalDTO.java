package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/2/27.
 */

@Getter
@Setter
@Component
public class ContractRenewalDTO extends ContractStep4DTO {

    /**
     * 原合同ID
     */
    private String renewalCid;

    /**
     * 签约当日甲方提供的证件原件列表（条件）
     */
    private List<SystemCodeDTO> lessorLicenseList;

    /**
     * 签约当日乙方提供的证件原件列表（条件）
     */
    private List<SystemCodeDTO> lesseeLicenseList;

    /**
     * 房主承担项目列表（条件）
     */
    private List<SystemCodeDTO> lessorBearList;
}
