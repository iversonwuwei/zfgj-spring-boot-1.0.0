package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.HouseOwnerMDTO;
import com.dlfc.zfgj.entity.SysPerson;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/6.
 */

@Component
public class HouseOwnerMConvertor extends AbstractConvertor<SysPerson, HouseOwnerMDTO> {
    @Override
    public SysPerson toModel(HouseOwnerMDTO houseOwnerDTO) {
        return null;
    }

    @Override
    public HouseOwnerMDTO toDTO(SysPerson model, boolean forListView) {
        HouseOwnerMDTO houseOwnerDTO = new HouseOwnerMDTO();
        if (null != model) {
            houseOwnerDTO.setName(model.getName());
            houseOwnerDTO.setIdType(model.getIdType());
            houseOwnerDTO.setIdNo(model.getIdNo());
            houseOwnerDTO.setPhone(model.getContactWay());
        }
        return houseOwnerDTO;
    }
}
