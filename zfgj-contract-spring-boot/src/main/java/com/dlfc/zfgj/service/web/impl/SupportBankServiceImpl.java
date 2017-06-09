package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysPaychannelBanks;
import com.dlfc.zfgj.entity.SysPaychannelBanksExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysPaychannelBanksMapper;
import com.dlfc.zfgj.service.web.SupportBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/3/12.
 */

@Transactional
@Service
public class SupportBankServiceImpl implements SupportBankService {
    // 银行卡类型：借记卡
    private static final String CARD_TYPE_DEBIT = "01";
    // 银行卡类型：信用卡
    private static final String CARD_TYPE_CREDIT = "02";

    @Autowired
    private SysPaychannelBanksMapper sysPaychannelBanksMapper;

    /**
     * 查询通联支持的银行列表
     *
     * @param cardType
     * @return
     */
    public List<SysPaychannelBanks> getPaySupportBankList(String cardType) {
        if (CARD_TYPE_DEBIT.equals(cardType)
                || CARD_TYPE_CREDIT.equals(cardType)) {
            SysPaychannelBanksExample example = new SysPaychannelBanksExample();
            SysPaychannelBanksExample.Criteria criteria = example.createCriteria();
            criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            criteria.andCardTypeEqualTo(cardType);
            example.setOrderByClause("BANK_CODE");
            return sysPaychannelBanksMapper.selectByExample(example);
        } else {
            return null;
        }
    }
}
