package com.dlfc.zfgj.upload;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by K on 2017/3/22.
 */

@Component
public class UploadUtils {
    /**
     * 逐级创建FTP路径
     *
     * @param ftpClient
     * @param dir
     * @throws IOException
     */
    public static void makeDir(FTPClient ftpClient, String dir) throws IOException {
        if (StringUtils.isNotEmpty(dir)) {
            String[] dirArray = dir.split("/");
            String dirDes = StringUtils.EMPTY;
            String dirOrg;
            for (int i = 0; i < dirArray.length; i++) {
                dirOrg = dirArray[i];
                if (StringUtils.isNotEmpty(dirOrg)) {
                    dirDes += "/" + dirOrg;
                    if (!ftpClient.makeDirectory(dirDes)) {
                        ftpClient.mkd(dirDes);
                    }
                }
            }
        }
    }

    public static String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        String extension = "." + fileName.substring((lastIndex + 1), fileName.length());
        return extension;
    }

    public static String generateName(MultipartFile file) {
        String fileRealName = file.getOriginalFilename().toLowerCase();
        String extension = getExtension(fileRealName);
        return UuidUtils.get32UUID() + extension;
    }
}
