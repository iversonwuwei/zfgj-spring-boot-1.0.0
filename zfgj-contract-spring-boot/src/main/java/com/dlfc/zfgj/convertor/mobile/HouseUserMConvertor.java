package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.HouseUserMDTO;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/6.
 */

@Component
public class HouseUserMConvertor extends AbstractConvertor<ConHouseUser, HouseUserMDTO> {

    @Autowired
    private SysCodeService sysCodeService;
    @Override
    public ConHouseUser toModel(HouseUserMDTO houseUserDTO) {
        ConHouseUser conHouseUser = new ConHouseUser();
        conHouseUser.setName(houseUserDTO.getHouseUserName());
        conHouseUser.setMobile(houseUserDTO.getPhoneNumber());
        conHouseUser.setIdNo(houseUserDTO.getIdNumber());
        conHouseUser.setIdType(houseUserDTO.getIdentityMode());
        return conHouseUser;
    }

    @Override
    public HouseUserMDTO toDTO(ConHouseUser model, boolean forListView) {
        HouseUserMDTO houseUserDTO = new HouseUserMDTO();
        if (null != model) {
            houseUserDTO.setHouseUserName(model.getName());
            houseUserDTO.setPhoneNumber(model.getMobile());
            houseUserDTO.setIdNumber(model.getIdNo());
            houseUserDTO.setIdentityMode(model.getIdType());
        }
        return houseUserDTO;
    }
}
