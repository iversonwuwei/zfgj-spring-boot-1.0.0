package com.dlfc.zfgj.common.upload.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.zfgj.common.upload.Upload;
import com.dlfc.zfgj.common.upload.UploadUtils;
import com.dlfc.zfgj.config.FTPConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by walden on 2017/2/17.
 */
@Slf4j
@Getter
@Service(value = "fileUpload")
@Transactional
public class FileUpload implements Upload<File> {

    private static final String TIME_FORMAT = "yyyyMMdd";

    @Autowired
    private FTPClient ftpClient;
    @Autowired
    private FTPConfig ftpConfig;

    private String fileName;

    public FileUpload() {
    }

    @Override
    public FileUpload upload(File file) {
        this.fileName = generateFileName(file);
        FileInputStream fileInputStream;
        String fileRealPath = "/" + DateUtils.getDate(TIME_FORMAT);
        try {
            ftpClient.connect(ftpConfig.getHost());
            ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 保存根目录
            String workingDirOriginal = ftpConfig.getWorkingDirectoryForContract();
            // 获取真实下载目录
            String workingDirCurrent = workingDirOriginal + fileRealPath;
            // 创建文件上传路径
            makeDir(workingDirCurrent);
            // 定向文件上传路径
            ftpClient.changeWorkingDirectory(workingDirCurrent);
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // 获取输入流
            fileInputStream = new FileInputStream(file);
            // 文件上传
            ftpClient.storeFile(this.fileName, fileInputStream);
            // 还原根目录
            ftpClient.changeWorkingDirectory(workingDirOriginal);
            fileInputStream.close();
        } catch (IOException e) {
            log.error("文件上传时发生错误", e);
            return null;
        }
        return this;
    }

    @Override
    public void upload(File file, String fileName) throws ApplicationException {

    }

    private void makeDir(String dir) throws IOException {
        UploadUtils.makeDir(ftpClient, dir);
    }

    private String generateFileName(File file) {
        String fileRealname = file.getName().toLowerCase();
        String extension = UploadUtils.getExtension(fileRealname);
        String fileName = UuidUtils.get32UUID() + extension;
        return fileName;
    }
}
