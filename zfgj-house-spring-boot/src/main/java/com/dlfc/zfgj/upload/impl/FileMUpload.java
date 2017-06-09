package com.dlfc.zfgj.upload.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.zfgj.config.FTPConfig;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.upload.Upload;
import com.dlfc.zfgj.upload.UploadUtils;
import lombok.Getter;
import lombok.Setter;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by walden on 2017/4/17.
 */
@Slf4j
@Getter
@Setter
@Service(value = "fileMUpload")
@Transactional
public class FileMUpload implements Upload {

    private static final String DATE_FORMAT = "yyyyMMdd";

    @Autowired
    private FTPClient ftpClient;
    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private SysInfoAttrMService sysInfoAttrService;
    @Autowired
    private AgtService agtService;

    private String uploadPath = PropertyUtils.getSysVal("upload.file.temporary.directory");


    @Override
    public List<SysInfoAtt> upload(Object object, int imgType, String empId) throws ApplicationException {
        MultipartFile multipartFile = (MultipartFile) object;
        // 记录用
        String fileName = StringUtils.EMPTY;
        String filePath;
        // 上传用
        String fileRealname = StringUtils.EMPTY;
        String fileRealPath = StringUtils.EMPTY;
        String workingDirOriginal = StringUtils.EMPTY;
        if (null != multipartFile) {
            fileRealname = multipartFile.getOriginalFilename().toLowerCase();
            String extension = getExtension(fileRealname);
            fileName = UuidUtils.get32UUID() + extension;
            fileRealPath = "/" + DateUtils.getDate(DATE_FORMAT);
        }
        try {
            ftpClient.connect(ftpConfig.getHost());
            ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 保存原根目录
            workingDirOriginal = ftpConfig.getWorkingDirectoryForHouse();
            // 获取真实下载目录
            String workingDirCurrent = workingDirOriginal + fileRealPath;
            // 文件路径全名
            filePath = workingDirCurrent + "/" + fileName;
            // 创建文件上传路径
            makeDir(workingDirCurrent);
            // 定向文件上传路径
            ftpClient.changeWorkingDirectory(workingDirCurrent);
            InputStream inputStream = multipartFile.getInputStream();
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // 文件上传
            ftpClient.storeFile(fileName, inputStream);
            // 还原根目录
            ftpClient.changeWorkingDirectory(workingDirOriginal);
//            if (imgType == InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue()) {
//
//                //检查图片是否是二维码
//                QRCodeUtils QRCode = new QRCodeUtils();
//                //生产二维码(图片信息，图片路径，图片格式)
//                String tu = QRCode.decoderQRCode(multipartFile.getInputStream());
//                if(StringUtils.isEmpty(tu)){
//                    throw new ApplicationException("请上传二维码");
//                }
//                //判断是否为微信二维码
//                if(!tu.startsWith("http://weixin.qq.com/")){
//                    throw new ApplicationException("请上传微信二维码");
//                }
//                QRCode.encoderQRCode(tu, workingDirCurrent + filePath, "png");
//            }
        } catch (Exception e) {
            //this.log.info("upload", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        List<SysInfoAtt> list = new ArrayList<>();
        if(imgType == InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue() || imgType == InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue()){
            //保存经纪人id
            if (StringUtils.isNotBlank(empId)) {
                sysInfoAtt.setLid(empId);
            }
        }
        sysInfoAtt.setFileType(imgType);
        sysInfoAtt.setFileName(fileName);
        sysInfoAtt.setFilePath(filePath.substring(workingDirOriginal.length()+1,filePath.length()));
        sysInfoAtt.setFileRealName(fileRealname);
        if (imgType == InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue()) {//上传房间图片
            sysInfoAtt = sysInfoAttrService.saveRoomAndGet(sysInfoAtt,empId);
        }else if (imgType == InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue()){//上传经纪人头像图片
            sysInfoAttrService.deleteSysInfoAttByParams(sysInfoAtt.getLid(),imgType);
            sysInfoAtt = sysInfoAttrService.saveAndGet(sysInfoAtt);
//            AgtEmpInfo agtEmpInfo = agtService.findById(sysInfoAtt.getLid());
//            if (agtEmpInfo != null) {
//                agtEmpInfo.setAvatarId(sysInfoAtt.getId());
//            }
//            agtService.save(agtEmpInfo);
        }
        list.add(sysInfoAtt);
        return list;
    }

    @Override
    public Upload upload(File f) throws ApplicationException {
        return null;
    }

    /**
     * 图片回显示
     * @param path 回显示路径
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
        byte[] buff = new byte[1024 * 16];
        int length;
        while ((length = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, length);
        }
        inputStream.close();
        ftpClient.completePendingCommand();
    }

    private String getExtension(String fileName) {
        String extension = StringUtils.EMPTY;
        int index = fileName.indexOf(".");
        if (index > 0) {
            extension = fileName.substring(index);
        }
        return extension;
    }

    private void makeDir(String dir) throws IOException {
        UploadUtils.makeDir(ftpClient, dir);
    }
}
