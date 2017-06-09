package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.HouseUserDTO;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/6.
 */

@Component
public class HouseUserConvertor extends AbstractConvertor<ConHouseUser, HouseUserDTO> {

    // 个人证件类型
    private static final String CODE_PER_ID_TYPE = "per_id_type";

    @Autowired
    private SysCodeService sysCodeService;

    @Override
    public ConHouseUser toModel(HouseUserDTO houseUserDTO) {
        ConHouseUser conHouseUser = new ConHouseUser();
        conHouseUser.setName(houseUserDTO.getHouseUserName());
        conHouseUser.setMobile(houseUserDTO.getPhoneNumber());
        conHouseUser.setIdNo(houseUserDTO.getIdNumber());
        if (StringUtils.isNotEmpty(houseUserDTO.getIdentityMode())) {
            conHouseUser.setIdType(Integer.valueOf(houseUserDTO.getIdentityMode()));
        }
        return conHouseUser;
    }

    @Override
    public HouseUserDTO toDTO(ConHouseUser model, boolean forListView) {
        HouseUserDTO houseUserDTO = new HouseUserDTO();
        if (null != model) {
            houseUserDTO.setHouseUserName(model.getName());
            houseUserDTO.setPhoneNumber(model.getMobile());
            houseUserDTO.setIdNumber(model.getIdNo());
            if (null != model.getIdType()) {
                houseUserDTO.setIdentityMode(model.getIdType().toString());
                houseUserDTO.setIdentityModeName(
                        sysCodeService.getNameByType(model.getIdType().toString(), CODE_PER_ID_TYPE));
            }
        }
        return houseUserDTO;
    }
}
