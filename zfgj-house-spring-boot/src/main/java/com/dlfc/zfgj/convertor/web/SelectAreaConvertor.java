package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SelectAreaDTO;
import com.dlfc.zfgj.entity.SysAreaAreas;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/23.
 */

@Component
public class SelectAreaConvertor extends AbstractConvertor<SysAreaAreas, SelectAreaDTO> {


    /**
     * 将 DTO 转换为 Model.
     *
     * @param houseDTO 数据传输对象
     * @return
     */
    @Override
    public SysAreaAreas toModel(SelectAreaDTO houseDTO) {
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
    public SelectAreaDTO toDTO(SysAreaAreas model, boolean forListView) {
        SelectAreaDTO selectCodeDTO = new SelectAreaDTO();
        selectCodeDTO.setAreaName(model.getArea());
        selectCodeDTO.setAreaId(model.getAreaId().toString());
        return selectCodeDTO;
    }
}
