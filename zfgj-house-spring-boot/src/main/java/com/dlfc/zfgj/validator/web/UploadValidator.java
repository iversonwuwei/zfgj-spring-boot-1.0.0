package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.utils.QRCodeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by K on 2017/3/13.
 */

@Component
public class UploadValidator {

    public void validate(MultipartFile file) throws ApplicationException {
        boolean validateResult = false;
        String errorMessage = StringUtils.EMPTY;
        if (null != file && file.getSize() > 0) {
            String fileRealName;
            // 上传文件大小限制
            long maxSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
            // 上传文件扩展名限制
            String extAllowed = PropertyUtils.getSysVal("upload.file.ext.allowed");
            String[] extAllowedArray = extAllowed.split(",");
            // 文件大小不合法
            if (file.getSize() > maxSize) {
                validateResult = true;
                errorMessage = PropertyUtils.getErrorMsg("SYS-0113", new Object[]{maxSize / 1048576});
            }
            fileRealName = file.getOriginalFilename();
//            if (fileRealName.length() > 25) {
//                validateResult = true;
//                errorMessage = PropertyUtils.getErrorMsg("SYS-0130");
//            }//不再保存原名
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
        } else {
            // 文件为空
            validateResult = true;
            errorMessage = PropertyUtils.getErrorMsg("SYS-0115");
        }
        if (validateResult) {
            throw new ApplicationException(errorMessage);
        }
    }

    /**
     * 验证图片是否为二维码
     *
     * @param file
     */
    public void validateQR(MultipartFile file) throws ApplicationException, IOException {
        QRCodeUtils QRCode = new QRCodeUtils();
        //生产二维码(图片信息，图片路径，图片格式)
        String tu = QRCode.decoderQRCode(file.getInputStream());
        if(StringUtils.isEmpty(tu)){
            throw new ApplicationException("请上传二维码");
        }
        //判断是否为微信二维码
        if(!tu.startsWith("http://weixin.qq.com/")){
            throw new ApplicationException("请上传微信二维码");
        }
    }
}
