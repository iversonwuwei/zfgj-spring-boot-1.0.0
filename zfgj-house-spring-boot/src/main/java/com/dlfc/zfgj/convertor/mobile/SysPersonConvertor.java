package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.NameIdentifyDTO;
import com.dlfc.zfgj.entity.SysPerson;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class SysPersonConvertor extends AbstractConvertor<SysPerson, NameIdentifyDTO> {
    @Override
    public SysPerson toModel(NameIdentifyDTO dto) {
        SysPerson model = new SysPerson();
        model.setName(dto.getName());
        model.setIdNo(dto.getIdNo());
        return model;
    }

    @Override
    public NameIdentifyDTO toDTO(SysPerson model, boolean forListView) {
        return null;
    }
}
