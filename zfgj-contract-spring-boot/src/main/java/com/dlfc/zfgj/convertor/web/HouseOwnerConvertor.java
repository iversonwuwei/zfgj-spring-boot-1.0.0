package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.HouseOwnerDTO;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/6.
 */

@Component
public class HouseOwnerConvertor extends AbstractConvertor<SysPerson, HouseOwnerDTO> {
    // 证件类型
    private static final String PER_ID_TYPE = "per_id_type";
    @Autowired
    private SysCodeService sysCodeService;

    @Override
    public SysPerson toModel(HouseOwnerDTO houseOwnerDTO) {
        return null;
    }

    @Override
    public HouseOwnerDTO toDTO(SysPerson model, boolean forListView) {
        HouseOwnerDTO houseOwnerDTO = new HouseOwnerDTO();
        if (null != model) {
            houseOwnerDTO.setName(model.getName());
            if (null != model.getIdType()) {
                houseOwnerDTO.setIdType(sysCodeService.getNameByType(model.getIdType().toString(), PER_ID_TYPE));
            }
            houseOwnerDTO.setIdNo(model.getIdNo());
            houseOwnerDTO.setPhone(model.getContactWay());
        }
        return houseOwnerDTO;
    }
}
