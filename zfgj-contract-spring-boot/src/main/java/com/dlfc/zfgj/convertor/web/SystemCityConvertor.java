package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysAreaCities;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemCityConvertor extends AbstractConvertor<SysAreaCities, SystemCodeDTO> {
    @Override
    public SysAreaCities toModel(SystemCodeDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeDTO toDTO(SysAreaCities model, boolean forListView) {
        SystemCodeDTO systemCodeDTO = new SystemCodeDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getCityId());
            systemCodeDTO.setName(model.getCity());
        }
        return systemCodeDTO;
    }
}
