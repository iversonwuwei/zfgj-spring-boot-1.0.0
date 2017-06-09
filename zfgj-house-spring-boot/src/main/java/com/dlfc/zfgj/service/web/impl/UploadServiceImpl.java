package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.zfgj.dto.web.SysAttDTO;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.ImageService;
import com.dlfc.zfgj.service.web.SysInfoAttrService;
import com.dlfc.zfgj.service.web.UploadService;
import com.dlfc.zfgj.upload.UploadUtils;
import com.dlfc.zfgj.upload.impl.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
@Service("house")
@Transactional
@Slf4j
public class UploadServiceImpl implements UploadService<MultipartFile> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static LinkedList<SysAttDTO> fileList;

    @Autowired
    @Qualifier(value = "fileUpload")
    private FileUpload upload;
    @Autowired
    @Qualifier(value = "sysInfoServiceImpl")
    private SysInfoAttrService sysInfoAttrService;
    @Autowired
    @Qualifier(value = "aServiceImpl")
    private AgtService agtService;
    @Autowired
    @Qualifier(value = "houseForW")
    private ImageService imageService;

    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws ApplicationException
     */
    @Override
    public String upload(MultipartFile file) throws ApplicationException {
        String path = StringUtils.EMPTY;
        if (null != file) {
            String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
            String date = DateUtils.getSysDateStr(DATE_FORMAT);
            String fileName = UuidUtils.get32UUID() + ".png";
            String perDir = PropertyUtils.getSysVal("upload.file.real.per.directory");
            // 生成png图片
            path = perDir + date + "/" + fileName;
            // 新生成的图片
            File destFile = new File(realDir + perDir + date);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            destFile = new File(destFile.getAbsolutePath() + "/" + fileName);
            try {
//                fileName = upload.upload(file).getFileName();//上传到FTP服务器
                file.transferTo(destFile);
            } catch (IOException e) {
                log.error("文件上传时出错", e);
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
            }
        }
        return path;
    }

    /**
     * 保存经纪人头像信息
     *
     * @param file
     * @param filepath
     * @param user
     */
    @Override
    public void saveEmpicture(MultipartFile file,
                              String filepath,
                              UsrUser user) {
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(user.getId());
        if (null != agtEmpInfo) {
            doSysInfoAtt(agtEmpInfo.getId(), filepath, file, InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue(), user);
        }
    }

    /**
     * 保存二维码图片信息
     *
     * @param file
     * @param filepath
     * @param user
     */
    @Override
    public void saveQR(MultipartFile file,
                       String filepath,
                       UsrUser user) {
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(user.getId());
        if (null != agtEmpInfo) {
            doSysInfoAtt(agtEmpInfo.getId(), filepath, file, InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue(), user);
        }
    }

    /**
     * 组装附件表数据
     *
     * @param file
     * @param path
     * @return
     */
    @Override
    public SysInfoAtt generateSysInfoAtt(MultipartFile file,
                                         String path,
                                         Integer type) {
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.setFileType(type);
        sysInfoAtt.setFilePath(path);
        sysInfoAtt.setFileName(getFileNameFromPath(path));
//        sysInfoAtt.setFileRealName(file.getOriginalFilename());//不再保存原名
        return sysInfoAtt;
    }

    /**
     * 添加房源图片
     *
     * @param file
     */
    @Override
    public void addFile(MultipartFile file) throws ApplicationException {
        if (null == fileList) {
            fileList = new LinkedList<>();
        }
        SysAttDTO sysAttDTO = new SysAttDTO();
        sysAttDTO.setFile(file);
        // 存储文件到本地
        String path = uploadTemp(file);
        sysAttDTO.setPath(path);
        String fileName = getFileNameFromPath(path);
        if (StringUtils.isNotEmpty(fileName)) {
            sysAttDTO.setFileName(fileName);
        }
        fileList.add(sysAttDTO);
    }

    private String getFileNameFromPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            path = path.replace("\\", "/");
            int index = path.lastIndexOf("/");
            if (index > 0) {
                return path.substring(index + 1);
            }
        }
        return null;
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
     * 删除房源图片
     *
     * @param index
     */
    @Override
    public void deleteFile(Integer index) throws ApplicationException {
        if (null == index || fileList.size() < index) {
            log.error("房源图片列表下标异常");
            throw new ApplicationException("找不到指定图片");
        }
        fileList.remove(index.intValue());
    }

    /**
     * 上传房间图片（从内存中获取）
     *
     * @param id
     * @param user
     */
    @Override
    public void uploadFileList(String id,
                               UsrUser user) throws ApplicationException {
        Iterator<SysAttDTO> iterator = fileList.iterator();
        SysAttDTO dto;
        String path;
        SysInfoAtt sysInfoAtt;
        List<String> idList = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                dto = iterator.next();
                // ID为空时是新建或者替换界面 不为空时是编辑界面
                if (StringUtils.isEmpty(dto.getId())) {
                    path = imageService.generateLeaseImage(
                            dto.getFileName(), ImageServiceImpl.WATER_MARK_TYPE_LOGO);
//                    fileName = upload(new File(path));
                    if (StringUtils.isNotEmpty(path)) {
                        sysInfoAtt = generateSysInfoAtt(
                                dto.getFile(), path, InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue());
                        sysInfoAtt.setLid(id);
                        idList.add(sysInfoAttrService.save(sysInfoAtt, user));
                    }
                } else {
                    idList.add(dto.getId());
                }
            }
        } catch (IOException e) {
            log.error("房源图片转换异常", e);
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0132"));
        }
        sysInfoAttrService.deleteSysInfoAttByParams(id, InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue(), idList, user);
        fileList.clear();
    }

    /**
     * 初始化房间图片集合
     *
     * @param sysAttDTOList
     */
    @Override
    public void initFileList(List<SysAttDTO> sysAttDTOList) {
        if (null == sysAttDTOList) {
            fileList = new LinkedList<>();
        } else {
            fileList = new LinkedList<>(sysAttDTOList);
        }
    }

    /**
     * 操作附件表数据
     *
     * @param lid
     * @param filepath
     * @param file
     * @param type
     * @param user
     */
    @Override
    public void doSysInfoAtt(String lid,
                             String filepath,
                             MultipartFile file,
                             Integer type,
                             UsrUser user) {
        List<String> idList = new ArrayList<>();
        SysInfoAtt sysInfoAtt = generateSysInfoAtt(file, filepath, type);
        sysInfoAtt.setLid(lid);
        idList.add(sysInfoAttrService.save(sysInfoAtt, user));
        sysInfoAttrService.deleteSysInfoAttByParams(lid, type, idList, user);
    }

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @throws ApplicationException
     */
    @Override
    public void showUp(String path,
                       HttpServletResponse response) throws ApplicationException {
        try {
            upload.showUp(path, response);
        } catch (Exception e) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0129"));
        }
    }
}
