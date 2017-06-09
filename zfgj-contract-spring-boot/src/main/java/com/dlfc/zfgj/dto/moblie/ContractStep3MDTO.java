package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by walden on 2017/2/16.
 */

@Getter
@Setter
@Component
public class ContractStep3MDTO extends ContractStep2MDTO {

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
}
