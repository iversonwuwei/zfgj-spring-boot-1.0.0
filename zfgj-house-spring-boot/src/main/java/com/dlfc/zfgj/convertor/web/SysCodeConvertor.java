package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SysCodeDTO;
import com.dlfc.zfgj.entity.SysCode;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/5/11.
 */

@Component
public class SysCodeConvertor extends AbstractConvertor<SysCode, SysCodeDTO> {
    @Override
    public SysCode toModel(SysCodeDTO sysCodeDTO) {
        return null;
    }

    @Override
    public SysCodeDTO toDTO(SysCode model, boolean forListView) {
        SysCodeDTO dto = new SysCodeDTO();
        if (null != model) {
            dto.setId(model.getId());
            dto.setCode(model.getCode());
            dto.setName(model.getName());
        }
        return dto;
    }
}
