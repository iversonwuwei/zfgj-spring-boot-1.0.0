package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.AgtCompDTO;
import com.dlfc.zfgj.entity.AgtCompInfo;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/5/8.
 */

@Component
public class AgtCompConvertor extends AbstractConvertor<AgtCompInfo, AgtCompDTO> {
    @Override
    public AgtCompInfo toModel(AgtCompDTO agtCompDTO) {
        return null;
    }

    @Override
    public AgtCompDTO toDTO(AgtCompInfo model, boolean forListView) {
        AgtCompDTO dto = new AgtCompDTO();
        if (null != model) {
            dto.setId(model.getId());
            dto.setCompName(model.getFullName());
        }
        return dto;
    }
}
