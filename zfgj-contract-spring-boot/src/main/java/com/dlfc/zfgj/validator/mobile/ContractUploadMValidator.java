package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by K on 2017/3/13.
 */

@Component
public class ContractUploadMValidator {

    public void validate(MultipartFile[] files,
                         Integer[] types) throws ApplicationException {
        boolean validateResult = false;
        String errorMessage = StringUtils.EMPTY;
        String fileRealName;
        if (null != files) {
            // 类型数必须与文件数相同
            if (null == types || types.length != files.length) {
                validateResult = true;
                errorMessage = PropertyUtils.getErrorMsg("SYS-0093");
            } else {
                // 类型不可以为空
                for (Integer type : types) {
                    if (null == type) {
                        validateResult = true;
                        errorMessage = PropertyUtils.getErrorMsg("SYS-0093");
                        break;
                    }
                }
            }
            for (MultipartFile file : files) {
                if (null != file) {
                    // 上传文件大小限制
                    long maxSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
                    // 上传文件扩展名限制
                    String extAllowed = PropertyUtils.getSysVal("upload.file.ext.allowed");
                    String[] extAllowedArray = extAllowed.split(",");
                    // 文件大小不合法
                    if (file.getSize() > maxSize) {
                        errorMessage = PropertyUtils.getErrorMsg("SYS-0113", new Object[]{maxSize / 1048576});
                    }
                    fileRealName = file.getOriginalFilename();
                    // 获得文件后缀名
                    String ext = fileRealName.substring(fileRealName.lastIndexOf(".")).toLowerCase();
                    boolean extFlag = false;
                    for (String str : extAllowedArray) {
                        if (str.equals(ext)) {
                            extFlag = true;
                            break;
                        }
                    }
                    //文件后缀名不合法
                    if (!extFlag) {
                        validateResult = true;
                        String str = extAllowed.replace(",.", "/");
                        errorMessage = PropertyUtils.getErrorMsg
                                ("SYS-0114", new Object[]{str.substring(1, str.length())});
                    }
                }
            }
        } else {
            validateResult = true;
            errorMessage = PropertyUtils.getErrorMsg("SYS-0115");
        }
        if (validateResult) {
            throw new ApplicationException(errorMessage);
        }
    }
}
