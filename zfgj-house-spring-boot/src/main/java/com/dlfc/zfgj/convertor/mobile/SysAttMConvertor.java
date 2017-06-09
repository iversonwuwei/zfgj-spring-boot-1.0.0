package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.SysAttMDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/3/27.
 */

@Component
public class SysAttMConvertor extends AbstractConvertor<SysInfoAtt, SysAttMDTO> {

    @Override
    public SysInfoAtt toModel(SysAttMDTO sysAttMDTO) {
        return null;
    }

    @Override
    public SysAttMDTO toDTO(SysInfoAtt model, boolean forListView) {
        SysAttMDTO sysAttMDTO = new SysAttMDTO();
        if (null != model) {
            sysAttMDTO.setInfoId(model.getId());
            sysAttMDTO.setType(model.getFileType());
            sysAttMDTO.setFileName(model.getFileName());
            sysAttMDTO.setPath(model.getFilePath());
            sysAttMDTO.setRealName(model.getFileRealName());
        }
        return sysAttMDTO;
    }
}
