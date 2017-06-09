package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysAreaProvinces;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemProvinceConvertor extends AbstractConvertor<SysAreaProvinces, SystemCodeDTO> {
    @Override
    public SysAreaProvinces toModel(SystemCodeDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeDTO toDTO(SysAreaProvinces model, boolean forListView) {
        SystemCodeDTO systemCodeDTO = new SystemCodeDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getProvinceId());
            systemCodeDTO.setName(model.getProvince());
        }
        return systemCodeDTO;
    }
}
