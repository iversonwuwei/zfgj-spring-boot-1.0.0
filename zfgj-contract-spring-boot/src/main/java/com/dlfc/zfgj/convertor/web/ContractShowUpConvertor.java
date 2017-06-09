package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractShowUpDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.service.web.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/4/9.
 */

@Component
public class ContractShowUpConvertor extends AbstractConvertor<SysInfoAtt, ContractShowUpDTO> {

    @Autowired
    private UploadService uploadService;

    @Override
    public SysInfoAtt toModel(ContractShowUpDTO contractShowUpDTO) {
        return null;
    }

    @Override
    public ContractShowUpDTO toDTO(SysInfoAtt model, boolean forListView) {
        ContractShowUpDTO contractShowUpDTO = new ContractShowUpDTO();
        if (null != model) {
            contractShowUpDTO.setType(model.getFileType().toString());
            contractShowUpDTO.setPath(model.getFilePath());
            uploadService.initFileMap(model);
        }
        return contractShowUpDTO;
    }
}
