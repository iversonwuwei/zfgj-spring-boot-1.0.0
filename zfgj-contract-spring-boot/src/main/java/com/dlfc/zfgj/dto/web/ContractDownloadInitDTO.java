package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.HouCoOwnerTemp;
import com.dlfc.zfgj.entity.SysInfoAtt;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Getter
@Setter
@Component
public class ContractDownloadInitDTO implements Serializable {

    /**
     * 当前合同
     */
    private ConContract conContract;

    /**
     * 服务地址
     */
    private String serverPath;

    /**
     * 附件
     */
    private List<SysInfoAtt> sysInfoAttList;

    /**
     * 共有人
     */
    private List<HouCoOwnerTemp> houCoOwnerList;

    /**
     * 支付列表
     */
    private List<SettlementCycleDTO> settlementCycleList;
}
