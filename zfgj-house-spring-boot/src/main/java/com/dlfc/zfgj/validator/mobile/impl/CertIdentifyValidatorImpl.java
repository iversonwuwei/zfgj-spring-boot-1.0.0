package com.dlfc.zfgj.validator.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.CertIdentifyDTO;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.enums.AgtCertEnum;
import com.dlfc.zfgj.service.web.AgtCertLinkService;
import com.dlfc.zfgj.validator.mobile.CertIdentifyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/6/5.
 */

@Component
public class CertIdentifyValidatorImpl implements CertIdentifyValidator {

    private int count;
    private List<AgtCertLink> agtCertLinkList;

    @Autowired
    @Qualifier("hwAgtCertLinkService")
    private AgtCertLinkService agtCertLinkService;

    @Override
    public void validate(CertIdentifyDTO certIdentifyDTO) throws ApplicationException {
        agtCertLinkList = agtCertLinkService.isEmpCerted(certIdentifyDTO.getUid());
        if (null != agtCertLinkList
                && agtCertLinkList.size() > 1) {
            throw new ApplicationException("从业资格证数据错误");
        } else if (agtCertLinkList.size() > 0
                && Integer.valueOf(AgtCertEnum.REFUSED.getValue()) != agtCertLinkList.get(0).getIsCert()) {
            throw new ApplicationException("已上传从业资格证信息");
        }
        count = agtCertLinkService.isAgtCardBinded(certIdentifyDTO.getCertNo());
        if (count > 0) {
            throw new ApplicationException("此从业资格证号已被使用");
        }
    }
}
