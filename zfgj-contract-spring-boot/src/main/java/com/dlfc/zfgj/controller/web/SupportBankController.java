package com.dlfc.zfgj.controller.web;

import com.dlfc.zfgj.convertor.web.SystemSupportBanksConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.SystemSupportBankDTO;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.SupportBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/w/supportBank")
public class SupportBankController {
    // 银行卡类型：借记卡
    private static final String CARD_TYPE_DEBIT = "01";
    // 银行卡类型：信用卡
    private static final String CARD_TYPE_CREDIT = "02";

    @Autowired
    private SupportBankService supportBankServiceImpl;
    @Autowired
    private SystemSupportBanksConvertor systemSupportBanksConvertor;

    /**
     * 获取通联支持的银行卡列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/supportBankList", method = RequestMethod.GET)
    public ResultDTO<SystemSupportBankDTO> getSupportBankList() throws CustomRuntimeException {
        SystemSupportBankDTO systemSupportBankDTO = new SystemSupportBankDTO();
        // 通联支持的银行卡列表（借记卡）
        List<SysPaychannelBanks> debitCardList = supportBankServiceImpl.getPaySupportBankList(CARD_TYPE_DEBIT);
        // 通联支持的银行卡列表（信用卡）
        List<SysPaychannelBanks> creditCardList = supportBankServiceImpl.getPaySupportBankList(CARD_TYPE_CREDIT);
        systemSupportBankDTO.setDebitCardList(systemSupportBanksConvertor.toListDTO(debitCardList));
        systemSupportBankDTO.setCreditCardList(systemSupportBanksConvertor.toListDTO(creditCardList));
        return new ResultDTO().success(systemSupportBankDTO);
    }
}
