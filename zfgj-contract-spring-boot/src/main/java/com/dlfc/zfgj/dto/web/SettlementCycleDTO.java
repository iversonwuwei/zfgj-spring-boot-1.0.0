package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/3/13.
 */

@Getter
@Setter
@Component
public class SettlementCycleDTO implements Serializable {
    //支付时间年
    private String afterPayDateYYYY;
    //支付时间月
    private String afterPayDateMM;
    //支付时间日
    private String afterPayDateDD;
    //支付起始有效日期年
    private String fromDateYYYY;
    //支付起始有效日期月
    private String fromDateMM;
    //支付起始有效日期日
    private String fromDateDD;
    //支付结束有效日期年
    private String toDateYYYY;
    //支付结束有效日期月
    private String toDateMM;
    //支付结束有效日期日
    private String toDateDD;
    //支付金额
    private Double money;
}
