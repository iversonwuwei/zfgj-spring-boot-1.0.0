package com.dlfc.zfgj.upload.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.zfgj.config.FTPConfig;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.SysInfoAttrService;
import com.dlfc.zfgj.upload.Upload;
import com.dlfc.zfgj.upload.UploadUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
@Slf4j
@Getter
@Service(value = "fileUpload")
@Transactional
public class FileUpload implements Upload<MultipartFile> {

    private static final String DATE_FORMAT = "yyyyMMdd";

    @Autowired
    private FTPClient ftpClient;
    @Autowired
    private FTPConfig ftpConfig;
    @Autowired
    private SysInfoAttrService sysInfoAttrService;
    @Autowired
    private AgtService agtService;

    private String fileName;

    @Override
    public FileUpload upload(File file) throws ApplicationException {
        this.fileName = generateFileName(file);
        String workingDirOriginal;
        String fileRealPath = "/" + DateUtils.getDate(DATE_FORMAT);
        try {
            ftpClient.connect(ftpConfig.getHost());
            ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 保存原根目录
            workingDirOriginal = ftpConfig.getWorkingDirectoryForHouse();
            // 获取真实下载目录
            String workingDirCurrent = workingDirOriginal + fileRealPath;
            // 创建文件上传路径
            makeDir(workingDirCurrent);
            // 定向文件上传路径
            ftpClient.changeWorkingDirectory(workingDirCurrent);
            // 获取输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // 文件上传
            ftpClient.storeFile(this.fileName, fileInputStream);
            // 还原根目录
            ftpClient.changeWorkingDirectory(workingDirOriginal);
        } catch (Exception e) {
            this.log.info("upload", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        return this;
    }

    /**
     * 图片回显示
     *
     * @param path     回显示路径
     * @param response 返回客户端
     * @throws IOException
     */
    @Override
    public void showUp(String path, HttpServletResponse response) throws IOException {
        ftpClient.connect(ftpConfig.getHost());
        ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
        ftpClient.changeWorkingDirectory(ftpConfig.getWorkingDirectoryForHouse());
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ServletOutputStream outputStream = response.getOutputStream();
        InputStream inputStream = ftpClient.retrieveFileStream(path);
        byte[] buff = new byte[1024 * 1024 * 24];
        int length;
        while ((length = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, length);
        }
        inputStream.close();
        ftpClient.completePendingCommand();
    }

    @Override
    public List<SysInfoAtt> upload(MultipartFile multipartFile, int imgType, String empId) throws ApplicationException {
        return null;
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
