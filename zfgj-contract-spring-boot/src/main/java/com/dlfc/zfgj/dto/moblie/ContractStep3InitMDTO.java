package com.dlfc.zfgj.dto.moblie;

import com.dlfc.zfgj.enums.SettlementCycleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class ContractStep3InitMDTO implements Serializable {

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
    /**
     * 合同起始时间
     */
    private Date contractStartDate;

    /**
     * 房主承担项目列表（条件）
     */
    private List<SystemCodeMDTO> lessorBearList;

    /**
     * 支付方式相关说明
     */
    private List<String> paymentExplanation;

    /**
     * 合同版本号
     */
    private Integer version;
}
