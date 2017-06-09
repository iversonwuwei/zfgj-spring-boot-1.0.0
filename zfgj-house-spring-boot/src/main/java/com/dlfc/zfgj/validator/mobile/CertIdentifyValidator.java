package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.CertIdentifyDTO;

/**
 * Created by K on 2017/6/5.
 */
public interface CertIdentifyValidator {

    void validate(CertIdentifyDTO certIdentifyDTO) throws ApplicationException;
}
