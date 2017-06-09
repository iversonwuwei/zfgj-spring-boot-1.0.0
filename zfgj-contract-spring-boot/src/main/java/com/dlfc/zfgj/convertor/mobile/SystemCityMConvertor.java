package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysAreaCities;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemCityMConvertor extends AbstractConvertor<SysAreaCities, SystemCodeMDTO> {
    @Override
    public SysAreaCities toModel(SystemCodeMDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeMDTO toDTO(SysAreaCities model, boolean forListView) {
        SystemCodeMDTO systemCodeDTO = new SystemCodeMDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getCityId());
            systemCodeDTO.setName(model.getCity());
        }
        return systemCodeDTO;
    }
}
