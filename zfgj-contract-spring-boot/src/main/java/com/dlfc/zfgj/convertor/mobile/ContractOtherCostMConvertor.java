package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.OtherRelatedItemMDTO;
import com.dlfc.zfgj.entity.ConOtherCosts;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/5.
 */

@Component
public class ContractOtherCostMConvertor extends AbstractConvertor<ConOtherCosts, OtherRelatedItemMDTO> {
    @Override
    public ConOtherCosts toModel(OtherRelatedItemMDTO otherRelatedItemDTO) {
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
    public OtherRelatedItemMDTO toDTO(ConOtherCosts model, boolean forListView) {
        return null;
    }
}
