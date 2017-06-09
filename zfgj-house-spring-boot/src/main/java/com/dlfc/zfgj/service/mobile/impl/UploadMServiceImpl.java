package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.mobile.AgtMService;
import com.dlfc.zfgj.service.mobile.ImageMService;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import com.dlfc.zfgj.service.mobile.UploadMService;
import com.dlfc.zfgj.upload.UploadUtils;
import com.dlfc.zfgj.upload.impl.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangna on 2017/3/27.
 */
@Service
@Slf4j
public class UploadMServiceImpl implements UploadMService<MultipartFile> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private File entityFile;

    @Autowired
    @Qualifier(value = "fileUpload")
    private FileUpload upload;

    @Autowired
    @Qualifier(value = "sysInfoMServiceImpl")
    private SysInfoAttrMService sysInfoAttrService;
    @Autowired
    @Qualifier(value = "aMServiceImpl")
    private AgtMService agtService;
    @Autowired
    @Qualifier(value = "houseForWM")
    private ImageMService imageMService;

    @Override
    public List<SysInfoAtt> upload(MultipartFile[] multipartFiles, int imgType, String empId) throws ApplicationException {
        List<SysInfoAtt> sysInfoAtts = new ArrayList<>();
        if (null != multipartFiles) {
            for (MultipartFile file : multipartFiles) {
                SysInfoAtt sysInfoAtt;
                String path = StringUtils.EMPTY;
                String fileName = StringUtils.EMPTY;
                File newFile;
                if (imgType == InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue()) {//经纪人
                    String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
                    String date = DateUtils.getSysDateStr(DATE_FORMAT);
                    fileName = UploadUtils.generateName(file);
                    String perDir = PropertyUtils.getSysVal("upload.file.real.per.directory");
                    // 生成png图片
                    path = perDir + date + "/" + fileName;
                    newFile = new File(realDir + perDir + date);
                    //上传图片
                    if (!newFile.exists()) {
                        newFile.mkdirs();
                    }
                    newFile = new File(newFile.getAbsoluteFile() + "/" + fileName);
                    try {
                        file.transferTo(newFile);
                    } catch (IOException e) {
                        log.error("文件上传时出错", e);
                        throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
                    }
                } else {//房源图片
                    String destDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
                    String parentDirName = "house/" + DateUtils.getDate(DATE_FORMAT);
                    File tempFile = new File(destDir + parentDirName);
                    if (!tempFile.exists()) {
                        tempFile.mkdirs();
                    }
                    fileName = UploadUtils.generateName(file);
                    path = tempFile.getAbsoluteFile() + "/" + fileName;
                    tempFile = new File(path);
                    try {
                        // MultipartFile类型转成File类型
                        file.transferTo(tempFile);
                    } catch (IOException e) {
                        log.error("文件转存时失败", e);
                        throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
                    }
                    try {
                        path = imageMService.generateLeaseImage(
                                fileName, ImageMServiceImpl.WATER_MARK_TYPE_LOGO);
                    } catch (IOException e) {
                        log.error("文件上传时出错", e);
                        throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
                    }
                    newFile = new File(destDir + parentDirName);
                }

                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                try {
                    //插入数据库
                    sysInfoAtt = insertSysInfoAtt(imgType, empId, fileName, file.getOriginalFilename(), path);
                    sysInfoAtts.add(sysInfoAtt);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    throw new ApplicationException(PropertyUtils.getErrorMsg("上传文件失败"));
                }
            }
        } else {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0115"));
        }
        return sysInfoAtts;
    }

    /**
     * 上传文件到临时文件夹
     *
     * @param file
     * @return
     */
    @Override
    public String uploadTemp(MultipartFile file) throws ApplicationException {
        String destDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
        String parentDirName = "house/" + DateUtils.getDate(DATE_FORMAT);
        File tempFile = new File(destDir + parentDirName);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        String fileName = UploadUtils.generateName(file);
        String path = tempFile.getAbsoluteFile() + "/" + fileName;
        tempFile = new File(path);
        try {
            // MultipartFile类型转成File类型
            file.transferTo(tempFile);
        } catch (IOException e) {
            log.error("文件转存时失败", e);
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
        }
        return path;
    }

    /**
     * 上传单个文件
     *
     * @param file
     * @param realDir
     * @return
     */
    @Override
    public String uploadSingleFile(MultipartFile file,
                                   String realDir) throws ApplicationException {
        String fileName = UploadUtils.generateName(file);
        entityFile = new File(realDir + "/" + fileName);
        try {
            file.transferTo(entityFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
        }
        return fileName;
    }

    /**
     * 上传多个文件
     *
     * @param files
     * @param type
     * @return
     */
    @Override
    public List<String> uploadFiles(MultipartFile[] files,
                                    int type) throws ApplicationException {
        String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
        String plusDir = StringUtils.EMPTY;
        if (InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue() == type) {
            plusDir = PropertyUtils.getSysVal("upload.file.real.per.directory");
        }
        String dateFix = "/" + DateUtils.getSysDateStr(DATE_FORMAT);
        realDir += plusDir;
        realDir += dateFix;
        entityFile = new File(realDir);
        if (!entityFile.exists()) {
            entityFile.mkdirs();
        }
        List<String> pathList = new ArrayList<>();
        for (MultipartFile file : files) {
            pathList.add(plusDir + dateFix + uploadSingleFile(file, realDir));
        }
        return pathList;
    }

    @Override
    public SysInfoAtt insertSysInfoAtt(int imgType, String empId, String fileName, String fileRealname, String path) {
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        if (imgType == InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue() || imgType == InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue()) {
            //保存经纪人id
            if (StringUtils.isNotBlank(empId)) {
                sysInfoAtt.setLid(empId);
            }
        }
        sysInfoAtt.setFileType(imgType);
        sysInfoAtt.setFileName(fileName);
        sysInfoAtt.setFilePath(path);
        //sysInfoAtt.setFileRealName(fileRealname);
        if (imgType == InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue()) {//上传房间图片
            sysInfoAtt = sysInfoAttrService.saveRoomAndGet(sysInfoAtt, empId);
        } else if (imgType == InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue()) {//上传经纪人头像图片
            sysInfoAttrService.deleteSysInfoAttByParams(sysInfoAtt.getLid(), imgType);
            sysInfoAtt = sysInfoAttrService.saveAndGet(sysInfoAtt);
        }
        return sysInfoAtt;
    }

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @throws ApplicationException
     */
    @Override
    public void showUp(String path, HttpServletResponse response) throws ApplicationException {
        try {
            upload.showUp(path, response);
        } catch (Exception e) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0129"));
        }
    }

    private String getExtension(String fileName) {
        String extension = StringUtils.EMPTY;
        int index = fileName.indexOf(".");
        if (index > 0) {
            extension = fileName.substring(index);
        }
        return extension;
    }
}
