package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangna on 2017/3/27.
 */
@Component
public interface UploadMValidator {

    void validate(MultipartFile[] files,int imgType) throws ApplicationException;
}
