package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.DeliverItemDTO;
import com.dlfc.zfgj.entity.ConHouseItems;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/5.
 */

@Component
public class HouseItemsConvertor extends AbstractConvertor<ConHouseItems, DeliverItemDTO> {
    @Override
    public ConHouseItems toModel(DeliverItemDTO deliverItemDTO) {
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
    public DeliverItemDTO toDTO(ConHouseItems model, boolean forListView) {
        DeliverItemDTO deliverItemDTO = new DeliverItemDTO();
        if (null != model) {
            deliverItemDTO.setName(model.getItem());
            deliverItemDTO.setBrand(model.getBrand());
            deliverItemDTO.setQuantity(model.getNum());
            deliverItemDTO.setPrice(model.getPrice());
            deliverItemDTO.setCompensation(model.getCompenAmount());
        }
        return deliverItemDTO;
    }
}
