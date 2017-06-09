package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysCode;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemCodeMConvertor extends AbstractConvertor<SysCode, SystemCodeMDTO> {
    @Override
    public SysCode toModel(SystemCodeMDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeMDTO toDTO(SysCode model, boolean forListView) {
        SystemCodeMDTO systemCodeDTO = new SystemCodeMDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getCode());
            systemCodeDTO.setName(model.getName());
        }
        return systemCodeDTO;
    }
}
