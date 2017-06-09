package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.SystemAttachmentDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class SystemAttachmentConvertor extends AbstractConvertor<SysInfoAtt, SystemAttachmentDTO> {

    @Override
    public SysInfoAtt toModel(SystemAttachmentDTO systemAttachmentDTO) {
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        if (null != systemAttachmentDTO) {
            sysInfoAtt.setFileType(systemAttachmentDTO.getType());
            sysInfoAtt.setFileName(systemAttachmentDTO.getName());
            sysInfoAtt.setFilePath(systemAttachmentDTO.getPath());
            sysInfoAtt.setFileRealName(systemAttachmentDTO.getRealName());
        }
        return sysInfoAtt;
    }

    @Override
    public SystemAttachmentDTO toDTO(SysInfoAtt model, boolean forListView) {
        return null;
    }
}
