package com.dlfc.zfgj.dto.moblie;

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
public class ContractRenewalMDTO extends ContractStep4MDTO {

    /**
     * 原合同ID
     */
    private String renewalCid;

    /**
     * 签约当日甲方提供的证件原件列表（条件）
     */
    private List<SystemCodeMDTO> lessorLicenseList;

    /**
     * 签约当日乙方提供的证件原件列表（条件）
     */
    private List<SystemCodeMDTO> lesseeLicenseList;

    /**
     * 房主承担项目列表（条件）
     */
    private List<SystemCodeMDTO> lessorBearList;
}
