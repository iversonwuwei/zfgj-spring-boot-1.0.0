package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysAreaProvinces;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemProvinceMConvertor extends AbstractConvertor<SysAreaProvinces, SystemCodeMDTO> {
    @Override
    public SysAreaProvinces toModel(SystemCodeMDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeMDTO toDTO(SysAreaProvinces model, boolean forListView) {
        SystemCodeMDTO systemCodeDTO = new SystemCodeMDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getProvinceId());
            systemCodeDTO.setName(model.getProvince());
        }
        return systemCodeDTO;
    }
}
