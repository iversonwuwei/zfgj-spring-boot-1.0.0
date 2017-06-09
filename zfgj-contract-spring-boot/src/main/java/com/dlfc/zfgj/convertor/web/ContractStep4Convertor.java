package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep4DTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.web.ContractStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/27.
 */

@Component
public class ContractStep4Convertor extends AbstractConvertor<ConContract, ContractStep4DTO> {

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private ContractStepService contractStepServiceImpl;
    @Autowired
    private HouseItemsConvertor houseItemsConvertor;
    @Autowired
    private ContractOtherCostConvertor contractOtherCostConvertor;

    @Override
    public ConContract toModel(ContractStep4DTO contractStep4DTO) {
        ConContract conContract = new ConContract();
        if (null != contractStep4DTO) {
            conContract.setId(contractStep4DTO.getContractId());
            conContract.setLessorIds(contractStepServiceImpl.getStrFromArray(contractStep4DTO.getLessorPapers()));
            conContract.setLessorIdsOther(contractStep4DTO.getOtherLessorPapers());
            conContract.setLesseeIds(contractStepServiceImpl.getStrFromArray(contractStep4DTO.getTenantryPapers()));
            conContract.setLesseeIdsOther(contractStep4DTO.getOtherTenantryPapers());
            conContract.setAdditionalTerms(contractStep4DTO.getOthers());
            // 房屋物品清单信息
            if (null != contractStep4DTO.getDeliverItems()) {
                conContract.setHouItemsList(houseItemsConvertor.toListModel(contractStep4DTO.getDeliverItems()));
            }
            // 合同其他相关费用
            if (null != contractStep4DTO.getOtherRelatedItems()) {
                conContract.setConOtherCostsList(
                        contractOtherCostConvertor.toListModel(contractStep4DTO.getOtherRelatedItems()));
            }
            conContract.setVersion(contractStep4DTO.getVersion());
        }
        return conContract;
    }

    @Override
    public ContractStep4DTO toDTO(ConContract model, boolean forListView) {
        ContractStep4DTO contractStep4DTO = new ContractStep4DTO();
        if (null != model) {
            contractStep4DTO.setContractId(model.getId());
        }
        return contractStep4DTO;
    }
}
