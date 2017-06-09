package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.common.upload.ShowUp;
import com.dlfc.zfgj.common.upload.UploadUtils;
import com.dlfc.zfgj.common.upload.impl.FileUpload;
import com.dlfc.zfgj.dto.web.SystemAttachmentDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.mobile.SysInfoAttMService;
import com.dlfc.zfgj.service.mobile.UploadMService;
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
public class UploadMServiceImpl implements UploadMService {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static Map<Integer, SystemAttachmentDTO> fileMap;

    @Autowired
    @Qualifier(value = "fileUpload")
    private FileUpload upload;

    @Autowired
    @Qualifier(value = "fileShowUp")
    private ShowUp fileShowUp;

    @Autowired
    private SysInfoAttMService sysInfoAttServiceImpl;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws ApplicationException
     */
    private String upload(MultipartFile file) throws ApplicationException {
        String fileName = null;
        if (null != file) {
            String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
            fileName = UploadUtils.generateName(file);
            File destFile = new File(realDir + DateUtils.getDate(DATE_FORMAT));
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            destFile = new File(destFile.getAbsolutePath() + "/" + fileName);
            try {
                file.transferTo(destFile);
            } catch (IOException e) {
                log.error("文件上传时出错");
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"));
            }
        }
        return fileName;
    }

    /**
     * 组装文件记录数据
     *
     * @param fileNames
     * @param types
     * @param files
     * @return
     */
    private SysInfoAtt[] getSysInfoAttList(String[] fileNames,
                                           Integer[] types,
                                           MultipartFile[] files) {
        int length = files.length;
        String fileName;
        SysInfoAtt[] sysInfoAtts = new SysInfoAtt[length];
        SysInfoAtt sysInfoAtt;
        for (int i = 0; i < length; i++) {
            sysInfoAtt = new SysInfoAtt();
            fileName = fileNames[i];
            sysInfoAtt.setFileName(fileNames[i]);
            sysInfoAtt.setFileType(types[i]);
            sysInfoAtt.setFilePath(DateUtils.getDate(DATE_FORMAT) + "/" + fileName);
            sysInfoAtts[i] = sysInfoAtt;
        }
        return sysInfoAtts;
    }

    /**
     * 查看图片
     *
     * @param contractId
     * @return
     * @throws ApplicationException
     */
    @Override
    public Map getMapForView(String contractId) throws ApplicationException {
        if (StringUtils.isEmpty(contractId)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0005"));
        }
        List<SysInfoAtt> sysInfoAttList = sysInfoAttServiceImpl.findList(contractId, null);
        if (null == sysInfoAttList || sysInfoAttList.size() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0128"));
        }
        Map hashMap = new HashMap<String, String>();
        Integer type;
        String path;
        List<Integer> typeRecord = new ArrayList<>();
        for (SysInfoAtt sysInfoAtt : sysInfoAttList) {
            type = sysInfoAtt.getFileType();
            // 每个类型的图片只显示一张
            if (typeRecord.contains(type)) {
                continue;
            }
            path = sysInfoAtt.getFilePath();
            if (null != type && StringUtils.isNotEmpty(path)) {
                hashMap.put("p" + type, path);
            }
            typeRecord.add(type);
        }
        return hashMap;
    }

    /**
     * 上传文件并更新文件记录
     *
     * @param files
     * @param types
     * @param contractId
     * @return
     * @throws ApplicationException
     */
    @Override
    public Map DoUpload(MultipartFile[] files,
                        Integer[] types,
                        String contractId,
                        UsrUser user) throws ApplicationException {
        SysInfoAtt[] sysInfoAtts;
        List<String> ids = new ArrayList<>();
        Map hashMap = new HashMap<String, String>();
        int length = files.length;
        String[] fileNames = new String[length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = upload(files[i]);
        }
        sysInfoAttServiceImpl.deleteOldRecord(contractId, Arrays.asList(types), user);
        sysInfoAtts = getSysInfoAttList(fileNames, types, files);
        for (SysInfoAtt sysInfoAtt : sysInfoAtts) {
            sysInfoAtt.setLid(contractId);
            ids.add(sysInfoAttServiceImpl.save(sysInfoAtt, user));
            hashMap.put(sysInfoAtt.getFileType(), sysInfoAtt.getFilePath());
        }
        return hashMap;
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
            fileShowUp.showUp(path, response);
        } catch (IOException e) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0129"));
        }
    }
}
