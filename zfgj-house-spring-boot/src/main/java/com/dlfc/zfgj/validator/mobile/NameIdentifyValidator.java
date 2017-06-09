package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.NameIdentifyDTO;

/**
 * Created by K on 2017/6/5.
 */
public interface NameIdentifyValidator {

    void validate(NameIdentifyDTO nameIdentifyDTO) throws ApplicationException;
}
