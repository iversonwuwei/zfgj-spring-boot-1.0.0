package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.common.upload.ShowUp;
import com.dlfc.zfgj.common.upload.UploadUtils;
import com.dlfc.zfgj.common.upload.impl.FileUpload;
import com.dlfc.zfgj.dto.web.SystemAttachmentDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.ConStatusEnum;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.web.ContractStepService;
import com.dlfc.zfgj.service.web.SysInfoAttService;
import com.dlfc.zfgj.service.web.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by walden on 2017/2/17.
 */

@Slf4j
@Service
@Transactional
public class UploadServiceImpl implements UploadService<File> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static Map<Integer, SystemAttachmentDTO> fileMap;

    @Autowired
    @Qualifier(value = "fileUpload")
    private FileUpload upload;

    @Autowired
    @Qualifier(value = "fileShowUp")
    private ShowUp fileShowUp;

    @Autowired
    private SysInfoAttService sysInfoAttServiceImpl;
    @Autowired
    private ContractStepService contractStepServiceImpl;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws ApplicationException
     */
    @Override
    public String upload(File file) throws ApplicationException {
        String path = StringUtils.EMPTY;
        if (null != file) {
//                fileName = upload.upload(file).getFileName();//FTP服务器上传
            String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
            String fileName = getFileNameFromPath(file.getPath());
            path = DateUtils.getDate(DATE_FORMAT) + "/" + fileName;
            File destFile = new File(realDir + DateUtils.getDate(DATE_FORMAT));
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            destFile = new File(destFile.getAbsolutePath() + "/" + fileName);
            boolean result = FileUtils.copyFileCover(file.getAbsolutePath(), destFile.getAbsolutePath(), true);
            if (!result) {
                log.error("文件上传时出错");
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
            }
        }
        return path;
    }

    /**
     * 组装文件记录数据
     *
     * @param path
     * @param type
     * @param file
     * @return
     */
    private SysInfoAtt getSysInfoAtt(String path,
                                     Integer type,
                                     MultipartFile file) {
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.setFileName(getFileNameFromPath(path));
        sysInfoAtt.setFileType(type);
//        sysInfoAtt.setFileRealName(file.getName());//不再保存原名
        sysInfoAtt.setFilePath(path);
        return sysInfoAtt;
    }

    /**
     * 查询图片
     *
     * @param contractId
     * @return
     * @throws ApplicationException
     */
    @Override
    public List<SysInfoAtt> getMapForView(String contractId) {
        List<SysInfoAtt> sysInfoAttsResult = new ArrayList<>();
        // 文件上传限制类型
        sysInfoAttsResult.add(getSysInfoAttByType(contractId, InfoAttFileTypeEnum.ONE_SIDE_ENUM.getValue()));
        sysInfoAttsResult.add(getSysInfoAttByType(contractId, InfoAttFileTypeEnum.OTHER_SIDE_ENUM.getValue()));
        sysInfoAttsResult.add(getSysInfoAttByType(contractId, InfoAttFileTypeEnum.SECURITY_BOOK_ENUM.getValue()));
        return sysInfoAttsResult;
    }

    private SysInfoAtt getSysInfoAttByType(String contractId,
                                           Integer type) {
        List<SysInfoAtt> sysInfoAttList = sysInfoAttServiceImpl.findList(contractId, type);
        if (null != sysInfoAttList && sysInfoAttList.size() > 0) {
            return sysInfoAttList.get(0);
        }
        SysInfoAtt sysInfoAtt = new SysInfoAtt();
        sysInfoAtt.setFileType(type);
        sysInfoAtt.setFilePath(PropertyUtils.getSysVal("defaultLogo"));
        return sysInfoAtt;
    }

//    /**
//     * 上传多个文件并更新文件记录
//     *
//     * @param files
//     * @param types
//     * @param contractId
//     * @param user
//     * @return
//     * @throws ApplicationException
//     */
//    @Override
//    public Map doUpload(MultipartFile[] files,
//                        Integer[] types,
//                        String contractId,
//                        UsrUser user) throws ApplicationException {
//        List<String> ids = new ArrayList<>();
//        SysInfoAtt sysInfoAtt;
//        Map hashMap = new HashMap<String, String>();
//        int length = files.length;
//        String[] fileNames = new String[length];
//        for (int i = 0; i < files.length; i++) {
//            fileNames[i] = upload(files[i]);
//            sysInfoAtt = getSysInfoAtt(fileNames[i], types[i], files[i]);
//            sysInfoAtt.setLid(contractId);
//            ids.add(sysInfoAttServiceImpl.save(sysInfoAtt, user));
//            hashMap.put(sysInfoAtt.getFileType(), sysInfoAtt.getFilePath());
//        }
//        sysInfoAttServiceImpl.deleteOldRecord(contractId, ids, user);
//        contractStepServiceImpl.setStatus(ConStatusEnum.APPROVING_ENUM.getValue(), contractId, user);
//        return hashMap;
//    }

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
            fileShowUp.showUp(path, response);
        } catch (Exception e) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0129"));
        }
    }

//    /**
//     * 单个文件上传后的数据处理
//     *
//     * @param contractId
//     * @param type
//     * @param fileName
//     * @param file
//     * @param user
//     */
//    @Override
//    public void operateData(String contractId,
//                            Integer type,
//                            String fileName,
//                            File file,
//                            UsrUser user) throws ApplicationException {
//        sysInfoAttServiceImpl.deleteOldSingleRecord(contractId, type, user);
//        SysInfoAtt sysInfoAtt = getSysInfoAtt(fileName, type, file);
//        sysInfoAtt.setLid(contractId);
//        sysInfoAttServiceImpl.save(sysInfoAtt, user);
//        contractStepServiceImpl.setStatus(ConStatusEnum.APPROVING_ENUM.getValue(), contractId, user);
//    }

    /**
     * 添加/替换文件
     *
     * @param type
     * @param file
     */
    @Override
    public void addFile(Integer type,
                        MultipartFile file) throws ApplicationException {
        if (null == fileMap) {
            fileMap = new HashMap<>();
        }
        SystemAttachmentDTO attachmentDTO = new SystemAttachmentDTO();
        attachmentDTO.setRealName(file.getOriginalFilename());
        attachmentDTO.setFile(file);
        attachmentDTO.setType(type);
        // 存储文件到本地
        String path = uploadTemp(file);
        attachmentDTO.setPath(path);
        if (fileMap.containsKey(type)) {
            this.replace(type, attachmentDTO);
        } else {
            fileMap.put(type, attachmentDTO);
        }
    }

    private void replace(Integer type, Object object) {
        SystemAttachmentDTO attachmentDTO = fileMap.get(type);
        if (attachmentDTO != null) {
            attachmentDTO = (SystemAttachmentDTO) object;
        }
        fileMap.put(type, attachmentDTO);
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
        String parentDirName = "contract/" + DateUtils.getDate(DATE_FORMAT);
        String fileName = UploadUtils.generateName(file);
        File tempFile = new File(destDir + "/" + parentDirName);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        String path = tempFile.getAbsolutePath() + "/" + fileName;
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
     * 从内存获取文件并上传
     *
     * @param contractId
     * @param user
     */
    @Override
    public void uploadMap(String contractId,
                          UsrUser user) throws ApplicationException {
        List<String> ids = new ArrayList<>();
        boolean result = true;
        Iterator<SystemAttachmentDTO> iterator = fileMap.values().iterator();
        SystemAttachmentDTO dto;
        SysInfoAtt sysInfoAtt;
        String path;
        while (iterator.hasNext()) {
            dto = iterator.next();
            // ID为空时是新建或者替换界面 不为空时是编辑界面
            if (StringUtils.isEmpty(dto.getId())) {
                path = upload(new File(dto.getPath()));
                if (StringUtils.isNotEmpty(path)) {
                    sysInfoAtt = getSysInfoAtt(path, dto.getType(), dto.getFile());
                    sysInfoAtt.setLid(contractId);
                    ids.add(sysInfoAttServiceImpl.save(sysInfoAtt, user));
                } else {
                    result = false;
                    break;
                }
            } else {
                ids.add(dto.getId());
            }
        }
        if (!result) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
        }
        sysInfoAttServiceImpl.deleteOldRecord(contractId, ids, user);
        contractStepServiceImpl.setStatus(ConStatusEnum.APPROVING_ENUM.getValue(), contractId, user);
        fileMap.clear();
    }

    /**
     * 上传界面初始化时组装文件内存
     *
     * @param sysInfoAtt
     */
    @Override
    public void initFileMap(SysInfoAtt sysInfoAtt) {
        if (null != sysInfoAtt) {
            if (null == fileMap) {
                fileMap = new HashMap<>();
            }
            Integer type = sysInfoAtt.getFileType();
            SystemAttachmentDTO attachmentDTO = new SystemAttachmentDTO();
            attachmentDTO.setId(sysInfoAtt.getId());
            fileMap.put(type, attachmentDTO);
        }
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
}
