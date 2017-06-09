package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wanglijun on 2017/3/21.
 */
@Getter
@Setter
@Component
public class PaymentCycleMDTO {

    /**
     * 合同开始时间
     */
    private Date startDate;

    /**
     * 合同结束时间
     */
    private Date endDate;

    /**
     * 合同结算周期
     */
    private Integer settlementCycle;

    /**
     * 合同月租金
     */
    private Integer monthlyRent;

    /**
     * 一共租多少个月
     */
    private Integer totalMonth;
}
