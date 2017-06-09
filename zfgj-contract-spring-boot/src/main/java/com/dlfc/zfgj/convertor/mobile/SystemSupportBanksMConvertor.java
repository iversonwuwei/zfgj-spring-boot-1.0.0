package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/3/4.
 */

@Component
public class SystemSupportBanksMConvertor extends AbstractConvertor<SysPaychannelBanks, SystemCodeMDTO> {
    @Override
    public SysPaychannelBanks toModel(SystemCodeMDTO systemCodeDTO) {
        return null;
    }

    @Override
    public SystemCodeMDTO toDTO(SysPaychannelBanks model, boolean forListView) {
        SystemCodeMDTO systemCodeDTO = new SystemCodeMDTO();
        if (null != model) {
            systemCodeDTO.setCode(model.getPindex().toString());
            systemCodeDTO.setName(model.getBankName());
        }
        return systemCodeDTO;
    }
}
