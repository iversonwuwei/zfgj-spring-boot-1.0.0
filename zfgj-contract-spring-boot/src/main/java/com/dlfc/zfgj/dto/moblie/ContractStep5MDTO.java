package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/6.
 */

@Getter
@Setter
@Component
public class ContractStep5MDTO extends ContractStep4MDTO {

    /**
     * 户型
     */
    private String houseType;

    /**
     * 房屋共有人列表
     */
    private List<HouseOwnerMDTO> houseOwnerMDTOList;

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
    private List<SystemAttachmentMDTO> systemAttachmentMDTOList;

    /**
     * 加密
     */
    private String accessKey;
}
