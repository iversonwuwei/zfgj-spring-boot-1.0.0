package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.ContractCountMDTO;
import com.dlfc.zfgj.entity.ConContract;
import org.springframework.stereotype.Component;

/**
 * Created by wanglijun on 2017/3/30.
 */
@Component
public class ContractCountMConvertor extends AbstractConvertor<ConContract, ContractCountMDTO> {
    @Override
    public ConContract toModel(ContractCountMDTO contractMDTO) {
        return null;
    }

    @Override
    public ContractCountMDTO toDTO(ConContract model, boolean forListView) {
        ContractCountMDTO contractDTO = new ContractCountMDTO();
        contractDTO.setCount(model.getListCount());
        return contractDTO;
    }
}
