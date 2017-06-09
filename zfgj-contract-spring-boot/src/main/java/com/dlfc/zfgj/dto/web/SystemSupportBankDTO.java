package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */
@Getter
@Setter
@Component
public class SystemSupportBankDTO implements Serializable {

    /**
     * 借记卡列表
     */
    private List<SystemCodeDTO> debitCardList;

    /**
     * 信用卡列表
     */
    private List<SystemCodeDTO> creditCardList;
}
