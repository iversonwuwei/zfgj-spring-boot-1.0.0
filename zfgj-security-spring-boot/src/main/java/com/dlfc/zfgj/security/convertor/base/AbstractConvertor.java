package com.dlfc.zfgj.security.convertor.base;


import com.dlfc.zfgj.security.dto.base.ListResultDTO;
import com.dlfc.zfgj.security.dto.base.ResultDTO;
import com.dlfc.zfgj.security.exception.CustomRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Model与DTO转换类
 */
public abstract class AbstractConvertor<ENTITY, DTO> {

    /**
     * 将 DTO 转换为 Model.
     *
     * @param dto 数据传输对象
     * @return
     */
    public abstract ENTITY toModel(final DTO dto);

    /**
     * 将 Model 转换为 DTO.
     *
     * @param model 数据Model
     * @return
     */
    public DTO toDTO(final ENTITY model) {
        return this.toDTO(model, false);
    }

    /**
     * 将 Model 转换为 DTO.
     *
     * @param model 数据Model
     * @param forListView 是否为列表视图做转换 true：列表视图 false：详细视图
     * @return
     */
    public abstract DTO toDTO(final ENTITY model, boolean forListView);

    public final List<ENTITY> toListModel(final List<DTO> dtoList) {
        final List<ENTITY> modelList = this.toModelList(dtoList);//dtoList.stream().map(dto -> this.toModel(dto)).collect(Collectors.toList());
        return modelList;
    }

    private List<ENTITY> toModelList(List<DTO> dtoList){
        List<ENTITY> modelList = new ArrayList<>();
        for (DTO dto : dtoList){
            modelList.add(this.toModel(dto));
        }
        return modelList;
    }

    public final List<DTO> toListDTO(final List<ENTITY> modelList) {
        final List<DTO> dtoList = this.toDtoList(modelList);//modelList.stream().map(model -> this.toDTO(model, true)).collect(Collectors.toList());
        return dtoList;
    }

    private final List<DTO> toDtoList(List<ENTITY> modelList){
        List<DTO> dtoList = new ArrayList<>();
        for (ENTITY entity : modelList){
            dtoList.add(this.toDTO(entity, true));
        }
        return dtoList;
    }

    /*public final List<DTO> toListDTO(final List<ENTITY> modelList){
        final List<DTO> dtoList = this.doListConvert(modelList);

        return dtoList;
    }

    private List<DTO> doListConvert(List<ENTITY> models){
        List<DTO> dtoList = new ArrayList<>();
        for (ENTITY model : models){
            dtoList.add(this.toDTO(model, true));
        }

        return dtoList;
    }*/

    public final ResultDTO<DTO> toResultDTO(final ENTITY model) {
        final DTO dto = (model == null) ? null : this.toDTO(model);
        final ResultDTO<DTO> resultDTO = ResultDTO.success(dto);
        return resultDTO;
    }

    public final ListResultDTO<DTO> toResultDTO(final List<ENTITY> modelList) throws CustomRuntimeException {
        final List<DTO> dtoList = this.toListDTO(modelList);
        final ListResultDTO<DTO> resultDTO = ListResultDTO.success(dtoList);
        return resultDTO;
    }
}