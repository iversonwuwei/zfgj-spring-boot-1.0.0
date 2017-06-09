package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysPaychannelBanks;

import java.util.List;

/**
 * Created by K on 2017/3/12.
 */
public interface SupportBankMService {

    List<SysPaychannelBanks> getPaySupportBankList(String cardType);
}
