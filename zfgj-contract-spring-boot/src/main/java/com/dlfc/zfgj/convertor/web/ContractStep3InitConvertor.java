package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep3InitDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.enums.SettlementCycleEnum;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class ContractStep3InitConvertor extends AbstractConvertor<ConContract, ContractStep3InitDTO> {
    // 租金分期
    private static final String BY_STAGES = "01";
    // 房主承担
    private static final String LESSOR_BEAR = "lessor_bear";

    @Autowired
    private SysCodeService sysCodeServiceImpl;
    @Autowired
    private SystemCodeConvertor systemCodeConvertor;

    @Override
    public ConContract toModel(ContractStep3InitDTO contractStep3InitDTO) {
        return null;
    }

    @Override
    public ContractStep3InitDTO toDTO(ConContract model, boolean forListView) {
        ContractStep3InitDTO contractStep3InitDTO = new ContractStep3InitDTO();
        if (null != model) {
            contractStep3InitDTO.setContractId(model.getId());
            // 修改合同时，默认信息为上次填写信息
            if (null != model.getMonthlyRent()) {
                if (null != model.getMonthlyRent()) {
                    contractStep3InitDTO.setMonthlyRent(model.getMonthlyRent().toString());
                }
                Integer settlementCycle = model.getSettlementCycle();
                if (null != settlementCycle) {
                    contractStep3InitDTO.setSettlement(settlementCycle.toString());
                }
                if (StringUtils.isNotEmpty(model.getOwnerBear())) {
                    contractStep3InitDTO.setOwnerResponsibility(model.getOwnerBear().split(","));
                }
                contractStep3InitDTO.setOwnerBearOther(model.getOwnerBearOther());
                if (null != model.getAgencyFee()) {
                    contractStep3InitDTO.setAgentFee(model.getAgencyFee().toString());
                }
                if (null != model.getDepositAmt()) {
                    contractStep3InitDTO.setDeposit(model.getDepositAmt().toString());
                }
                contractStep3InitDTO.setDepositSuperviseFlg(model.getPaymentMethod() == 1);
            }
            // 新建合同时
            else {
                // 分期支付时，结算周期默认为年付
                if (BY_STAGES == model.getStages()) {
                    contractStep3InitDTO.setSettlement(String.valueOf(SettlementCycleEnum.YEAR_ENUM.getValue()));
                }
            }
            // 房主承担列表
            List<SysCode> lessorBearList = sysCodeServiceImpl.getCodeByType(LESSOR_BEAR);
            contractStep3InitDTO.setLessorBearList(systemCodeConvertor.toListDTO(lessorBearList));
            // 合同版本号（验证用）
            contractStep3InitDTO.setVersion(model.getVersion());
        }
        return contractStep3InitDTO;
    }
}
