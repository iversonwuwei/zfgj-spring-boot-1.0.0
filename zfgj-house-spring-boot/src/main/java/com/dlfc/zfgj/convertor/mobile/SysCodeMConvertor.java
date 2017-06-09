package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.SysCodeMDTO;
import com.dlfc.zfgj.entity.SysCode;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/1.
 */
@Component("sysCodeMConvertor")
public class SysCodeMConvertor extends AbstractConvertor<SysCode, SysCodeMDTO> {
    @Override
    public SysCode toModel(SysCodeMDTO sysCodeMDTO) {
        return null;
    }

    @Override
    public SysCodeMDTO toDTO(SysCode model, boolean forListView) {
        SysCodeMDTO dto = new SysCodeMDTO();
        if (null != model) {
            dto.setId(model.getId());
            dto.setCode(model.getCode());
            dto.setName(model.getName());
        }
        return dto;

    }
}