package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.enums.SettlementCycleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class ContractStep3InitDTO implements Serializable {

    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 月租金
     */
    private String monthlyRent;

    /**
     * 结算周期 月，季度，半年，年
     */
    private String settlement = String.valueOf(SettlementCycleEnum.MONTHLY_ENUM.getValue());

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
    private String agentFee;

    /**
     * 押金
     */
    private String deposit;

    /**
     * 申请押金监管标识
     */
    private boolean depositSuperviseFlg;
    /**
     * 合同起始时间
     */
    private Date contractStartDate;

    /**
     * 房主承担项目列表（条件）
     */
    private List<SystemCodeDTO> lessorBearList;

    /**
     * 支付方式相关说明
     */
    private List<String> paymentExplanation;

    /**
     * 合同版本号
     */
    private Integer version;
}
