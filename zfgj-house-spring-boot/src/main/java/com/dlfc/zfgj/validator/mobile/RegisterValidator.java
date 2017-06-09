package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.RegisterDTO;

/**
 * Created by K on 2017/6/5.
 */
public interface RegisterValidator {

    void validate(RegisterDTO registerDTO) throws ApplicationException;
}
