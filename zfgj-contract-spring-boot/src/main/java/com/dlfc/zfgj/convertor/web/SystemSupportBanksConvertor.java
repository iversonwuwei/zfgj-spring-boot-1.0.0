package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemSupportBanksConvertor extends AbstractConvertor<SysPaychannelBanks, SystemCodeDTO> {
    @Override
    public SysPaychannelBanks toModel(SystemCodeDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeDTO toDTO(SysPaychannelBanks model, boolean forListView) {
        SystemCodeDTO systemCodeDTO = new SystemCodeDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getPindex().toString());
            systemCodeDTO.setName(model.getBankName());
        }
        return systemCodeDTO;
    }
}
