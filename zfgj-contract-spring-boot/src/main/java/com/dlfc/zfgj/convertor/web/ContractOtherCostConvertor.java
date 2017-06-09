package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.OtherRelatedItemDTO;
import com.dlfc.zfgj.entity.ConOtherCosts;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/5.
 */

@Component
public class ContractOtherCostConvertor extends AbstractConvertor<ConOtherCosts, OtherRelatedItemDTO> {
    @Override
    public ConOtherCosts toModel(OtherRelatedItemDTO otherRelatedItemDTO) {
        ConOtherCosts conOtherCosts = new ConOtherCosts();
        if (null != otherRelatedItemDTO) {
            conOtherCosts.setItem(otherRelatedItemDTO.getItem());
            conOtherCosts.setUnit(otherRelatedItemDTO.getUnit());
            conOtherCosts.setPrice(otherRelatedItemDTO.getPrice());
            conOtherCosts.setStime(otherRelatedItemDTO.getStartDate());
            conOtherCosts.setScount(otherRelatedItemDTO.getMinQuantity());
        }
        return conOtherCosts;
    }

    @Override
    public OtherRelatedItemDTO toDTO(ConOtherCosts model, boolean forListView) {
        OtherRelatedItemDTO otherRelatedItemDTO = new OtherRelatedItemDTO();
        if (null != model) {
            otherRelatedItemDTO.setItem(model.getItem());
            otherRelatedItemDTO.setUnit(model.getUnit());
            otherRelatedItemDTO.setPrice(model.getPrice());
            otherRelatedItemDTO.setStartDate(model.getStime());
            otherRelatedItemDTO.setMinQuantity(model.getScount());
        }
        return otherRelatedItemDTO;
    }
}
