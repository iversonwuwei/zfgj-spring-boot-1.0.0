package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep3DTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.web.ContractStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/27.
 */

@Component
public class ContractStep3Convertor extends AbstractConvertor<ConContract, ContractStep3DTO> {
    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private ContractStepService contractStepServiceImpl;

    @Override
    public ConContract toModel(ContractStep3DTO contractStep3DTO) {
        ConContract conContract = new ConContract();
        if (null != contractStep3DTO) {
            conContract.setId(contractStep3DTO.getContractId());
            conContract.setMonthlyRent(contractStep3DTO.getMonthlyRent());
            conContract.setSettlementCycle(contractStep3DTO.getSettlement());
            conContract.setOwnerBear(
                    contractStepServiceImpl.getStrFromArray(contractStep3DTO.getOwnerResponsibility()));
            conContract.setOwnerBearOther(contractStep3DTO.getOwnerBearOther());
            conContract.setAgencyFee(contractStep3DTO.getAgentFee());
            conContract.setDepositAmt(contractStep3DTO.getDeposit());
            conContract.setPaymentMethod(contractStep3DTO.isDepositSuperviseFlg() ? (short) 1 : 0);
            conContract.setVersion(contractStep3DTO.getVersion());
        }
        return conContract;
    }

    @Override
    public ContractStep3DTO toDTO(ConContract model, boolean forListView) {
        ContractStep3DTO contractStep3DTO = new ContractStep3DTO();
        contractStep3DTO.setContractId(model.getId());
        return contractStep3DTO;
    }
}
