package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.SystemAttachmentMDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class SystemAttachmentMConvertor extends AbstractConvertor<SysInfoAtt, SystemAttachmentMDTO> {
    @Override
    public SysInfoAtt toModel(SystemAttachmentMDTO systemAttachmentDTO) {
        return null;
    }

    @Override
    public SystemAttachmentMDTO toDTO(SysInfoAtt model, boolean forListView) {
        SystemAttachmentMDTO systemAttachmentDTO = new SystemAttachmentMDTO();
        if (null != model) {
            systemAttachmentDTO.setType(model.getFileType());
            systemAttachmentDTO.setName(model.getFileName());
            systemAttachmentDTO.setPath(model.getFilePath());
            systemAttachmentDTO.setRealName(model.getFileRealName());
        }
        return systemAttachmentDTO;
    }
}
