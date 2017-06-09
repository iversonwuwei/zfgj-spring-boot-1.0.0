package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SysAttDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class SysAttConvertor extends AbstractConvertor<SysInfoAtt, SysAttDTO> {
    @Override
    public SysInfoAtt toModel(SysAttDTO systemAttachmentDTO) {
        return null;
    }

    @Override
    public SysAttDTO toDTO(SysInfoAtt model, boolean forListView) {
        SysAttDTO systemAttachmentDTO = new SysAttDTO();
        if (null != model) {
            systemAttachmentDTO.setId(model.getId());
            if (null != model.getFileType()) {
                systemAttachmentDTO.setType(String.valueOf(model.getFileType().intValue()));
            }
            systemAttachmentDTO.setFileName(model.getFileName());
            systemAttachmentDTO.setPath(model.getFilePath());
            systemAttachmentDTO.setRealName(model.getFileRealName());
        }
        return systemAttachmentDTO;
    }
}
