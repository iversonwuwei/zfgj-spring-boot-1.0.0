package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.ContractMDTO;
import com.dlfc.zfgj.entity.ConContract;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/16.
 */
@Component
public class ContractMConvertor extends AbstractConvertor<ConContract, ContractMDTO> {

    @Override
    public ConContract toModel(ContractMDTO ContractMDTO) {
        return null;
    }

    @Override
    public ContractMDTO toDTO(ConContract model, boolean forListView) {
        ContractMDTO contractDTO = new ContractMDTO();
        if (null != model) {
            contractDTO.setContractId(model.getId());
            contractDTO.setCreatedDate(DateUtils.formatDateTime(model.getCreateTime()));
            contractDTO.setAddress(model.getHouseAddr());
            contractDTO.setFeeOfMonth(model.getMonthlyRent());
            contractDTO.setAgentFee(model.getAgencyFee());
            contractDTO.setLessorName(model.getLessorName());
            contractDTO.setTenantryName(model.getLesseeName());
        }
        return contractDTO;
    }
}
