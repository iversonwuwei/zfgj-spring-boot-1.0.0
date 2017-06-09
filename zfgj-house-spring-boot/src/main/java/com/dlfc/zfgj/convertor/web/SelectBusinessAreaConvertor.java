package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SelectBusinessAreaDTO;
import com.dlfc.zfgj.entity.SysTradeAreas;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/23.
 */

@Component
public class SelectBusinessAreaConvertor extends AbstractConvertor<SysTradeAreas, SelectBusinessAreaDTO> {


    /**
     * 将 DTO 转换为 Model.
     *
     * @param houseDTO 数据传输对象
     * @return
     */
    @Override
    public SysTradeAreas toModel(SelectBusinessAreaDTO houseDTO) {
        return null;
    }

    /**
     * 将 Model 转换为 DTO.
     *
     * @param model       数据Model
     * @param forListView 是否为列表视图做转换 true：列表视图 false：详细视图
     * @return
     */
    @Override
    public SelectBusinessAreaDTO toDTO(SysTradeAreas model, boolean forListView) {
        SelectBusinessAreaDTO selectCodeDTO = new SelectBusinessAreaDTO();
        selectCodeDTO.setName(model.getName());
        selectCodeDTO.setSpell(model.getsSpell());
        selectCodeDTO.setPanentId(model.getParentId());
        selectCodeDTO.setId(model.getId());
        selectCodeDTO.setTradeId(model.getTradeId());
        return selectCodeDTO;
    }
}
