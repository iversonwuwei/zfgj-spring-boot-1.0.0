package com.dlfc.zfgj.controller.mobile;

import com.dlfc.zfgj.convertor.mobile.SystemSupportBanksMConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.moblie.SystemSupportBankMDTO;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.SupportBankMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/m/supportBank")
public class SupportBankMController {
    // 银行卡类型：借记卡
    private static final String CARD_TYPE_DEBIT = "01";
    // 银行卡类型：信用卡
    private static final String CARD_TYPE_CREDIT = "02";

    @Autowired
    private SupportBankMService supportBankMServiceImpl;
    @Autowired
    private SystemSupportBanksMConvertor systemSupportBanksMConvertor;

    /**
     * 获取通联支持的银行卡列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/supportBankList", method = RequestMethod.GET)
    public ResultDTO<SystemSupportBankMDTO> getSupportBankList() throws CustomRuntimeException {
        SystemSupportBankMDTO systemSupportBankDTO = new SystemSupportBankMDTO();
        // 通联支持的银行卡列表（借记卡）
        List<SysPaychannelBanks> debitCardList = supportBankMServiceImpl.getPaySupportBankList(CARD_TYPE_DEBIT);
        // 通联支持的银行卡列表（信用卡）
        List<SysPaychannelBanks> creditCardList = supportBankMServiceImpl.getPaySupportBankList(CARD_TYPE_CREDIT);
        systemSupportBankDTO.setDebitCardList(systemSupportBanksMConvertor.toListDTO(debitCardList));
        systemSupportBankDTO.setCreditCardList(systemSupportBanksMConvertor.toListDTO(creditCardList));
        return new ResultDTO().success(systemSupportBankDTO);
    }
}
