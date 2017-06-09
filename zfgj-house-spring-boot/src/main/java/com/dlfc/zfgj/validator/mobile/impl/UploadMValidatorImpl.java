package com.dlfc.zfgj.validator.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.utils.QRCodeUtils;
import com.dlfc.zfgj.validator.mobile.UploadMValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by wangna on 2017/3/27.
 */
@Component
public class UploadMValidatorImpl implements UploadMValidator {
    @Override
    public void validate(MultipartFile[] files, int imgType) throws ApplicationException {
        boolean validateResult = false;
        String errorMessage = StringUtils.EMPTY;
        if (null != files && files.length > 0) {
            if (imgType == InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue()
                    && files.length != 2) {
                throw new ApplicationException("请上传证件图片（正反面2张）");
            }
            String fileRealName;
            // 上传文件大小限制
            long maxSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
            // 上传文件扩展名限制
            String extAllowed = PropertyUtils.getSysVal("upload.file.ext.allowed");
            String[] extAllowedArray = extAllowed.split(",");
            for (MultipartFile file : files) {
                // 文件大小不合法
                if (file.getSize() > maxSize) {
                    validateResult = true;
                    errorMessage = PropertyUtils.getErrorMsg("SYS-0113", new Object[]{maxSize / 1048576});
                    break;
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
                //判断上传的照片是否是二维码图片
                if (imgType == InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue()) {

                    //检查图片是否是二维码
                    QRCodeUtils QRCode = new QRCodeUtils();
                    //生产二维码(图片信息，图片路径，图片格式)
                    String tu = null;
                    try {
                        tu = QRCode.decoderQRCode(file.getInputStream());
                        if (StringUtils.isEmpty(tu)) {
                            throw new ApplicationException("请上传二维码");
                        }
                        //判断是否为微信二维码
                        if (!tu.startsWith("http://weixin.qq.com/")) {
                            throw new ApplicationException("请上传微信二维码");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
}
