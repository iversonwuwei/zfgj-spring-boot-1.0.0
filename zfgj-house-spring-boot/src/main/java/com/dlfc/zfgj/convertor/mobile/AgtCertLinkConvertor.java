package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.CertIdentifyDTO;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.enums.AgtCertEnum;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class AgtCertLinkConvertor extends AbstractConvertor<AgtCertLink, CertIdentifyDTO> {

    @Autowired
    private UserHouseMService userHouseMService;

    @Override
    public AgtCertLink toModel(CertIdentifyDTO dto) {
        AgtCertLink model = new AgtCertLink();
        model.setCertCardNo(dto.getCertNo());
        model.setUserId(dto.getUid());
        model.setIsCert(Integer.valueOf(AgtCertEnum.APPROVING.getValue()));
        model.setPid(userHouseMService.getUserById(dto.getUid()).getPerId());
        return model;
    }

    @Override
    public CertIdentifyDTO toDTO(AgtCertLink model, boolean forListView) {
        return null;
    }
}
