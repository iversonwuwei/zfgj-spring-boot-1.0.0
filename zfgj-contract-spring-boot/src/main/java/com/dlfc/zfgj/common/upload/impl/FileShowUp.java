package com.dlfc.zfgj.common.upload.impl;

import com.dlfc.zfgj.common.upload.ShowUp;
import com.dlfc.zfgj.config.FTPConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by K on 2017/3/27.
 */

@Service(value = "fileShowUp")
public class FileShowUp implements ShowUp<String> {

    @Autowired
    private FTPClient ftpClient;
    @Autowired
    private FTPConfig ftpConfig;

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @throws IOException
     */
    @Override
    public void showUp(String path, HttpServletResponse response) throws IOException {
        ftpClient.connect(ftpConfig.getHost());
        ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
        ftpClient.changeWorkingDirectory(ftpConfig.getWorkingDirectoryForContract());
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ServletOutputStream outputStream = response.getOutputStream();
        InputStream inputStream = ftpClient.retrieveFileStream(path);
        byte[] buff = new byte[1024 * 16];
        int length;
        while ((length = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, length);
        }
        inputStream.close();
        ftpClient.completePendingCommand();
    }
}
