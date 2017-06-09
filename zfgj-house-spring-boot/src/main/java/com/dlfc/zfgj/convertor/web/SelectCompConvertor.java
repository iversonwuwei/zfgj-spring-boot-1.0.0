package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SelectCompCodeDTO;
import com.dlfc.zfgj.entity.AgtCompInfo;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/23.
 */

@Component
public class SelectCompConvertor extends AbstractConvertor<AgtCompInfo, SelectCompCodeDTO> {


    /**
     * 将 DTO 转换为 Model.
     *
     * @param houseDTO 数据传输对象
     * @return
     */
    @Override
    public AgtCompInfo toModel(SelectCompCodeDTO houseDTO) {
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
    public SelectCompCodeDTO toDTO(AgtCompInfo model, boolean forListView) {
        SelectCompCodeDTO selectCodeDTO = new SelectCompCodeDTO();
        selectCodeDTO.setCompFullName(model.getFullName());
        selectCodeDTO.setAspelling(model.getaSpelling());
        selectCodeDTO.setSspelling(model.getsSpelling());
        selectCodeDTO.setId(model.getId());
        return selectCodeDTO;
    }
}
