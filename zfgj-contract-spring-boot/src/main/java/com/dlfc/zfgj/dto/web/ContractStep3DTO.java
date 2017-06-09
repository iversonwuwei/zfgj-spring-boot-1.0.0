package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.enums.SettlementCycleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by walden on 2017/2/16.
 */

@Getter
@Setter
@Component
public class ContractStep3DTO extends ContractStep2DTO {

    /**
     * 月租金
     */
    private Integer monthlyRent;

    /**
     * 结算周期 月，季度，半年，年
     */
    private Integer settlement = SettlementCycleEnum.MONTHLY_ENUM.getValue();

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
}
