package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.DeliverItemMDTO;
import com.dlfc.zfgj.entity.ConHouseItems;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/5.
 */

@Component
public class HouseItemsMConvertor extends AbstractConvertor<ConHouseItems, DeliverItemMDTO> {
    @Override
    public ConHouseItems toModel(DeliverItemMDTO deliverItemDTO) {
        ConHouseItems conHouseItems = new ConHouseItems();
        if (null != deliverItemDTO) {
            conHouseItems.setItem(deliverItemDTO.getName());
            conHouseItems.setBrand(deliverItemDTO.getBrand());
            conHouseItems.setNum(deliverItemDTO.getQuantity());
            conHouseItems.setPrice(deliverItemDTO.getPrice());
            conHouseItems.setCompenAmount(deliverItemDTO.getCompensation());
        }
        return conHouseItems;
    }

    @Override
    public DeliverItemMDTO toDTO(ConHouseItems model, boolean forListView) {
        return null;
    }
}
