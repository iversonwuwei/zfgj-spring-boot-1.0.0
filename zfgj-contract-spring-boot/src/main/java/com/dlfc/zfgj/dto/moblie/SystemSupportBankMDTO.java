package com.dlfc.zfgj.dto.moblie;

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
public class SystemSupportBankMDTO implements Serializable {

    /**
     * 借记卡列表
     */
    private List<SystemCodeMDTO> debitCardList;

    /**
     * 信用卡列表
     */
    private List<SystemCodeMDTO> creditCardList;
}
