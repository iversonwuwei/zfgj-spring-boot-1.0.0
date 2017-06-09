package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysCode;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemCodeConvertor extends AbstractConvertor<SysCode, SystemCodeDTO> {
    @Override
    public SysCode toModel(SystemCodeDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeDTO toDTO(SysCode model, boolean forListView) {
        SystemCodeDTO systemCodeDTO = new SystemCodeDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getCode());
            systemCodeDTO.setName(model.getName());
        }
        return systemCodeDTO;
    }
}
