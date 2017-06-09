package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SelectMetroDTO;
import com.dlfc.zfgj.entity.SysTransportStation;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/23.
 */

@Component
public class SelectMetroConvertor extends AbstractConvertor<SysTransportStation, SelectMetroDTO> {


    /**
     * 将 DTO 转换为 Model.
     *
     * @param houseDTO 数据传输对象
     * @return
     */
    @Override
    public SysTransportStation toModel(SelectMetroDTO houseDTO) {
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
    public SelectMetroDTO toDTO(SysTransportStation model, boolean forListView) {
        SelectMetroDTO selectCodeDTO = new SelectMetroDTO();
        selectCodeDTO.setName(model.getName());
        selectCodeDTO.setSpelling(model.getSpelling());
        selectCodeDTO.setId(model.getId());
        return selectCodeDTO;
    }
}
