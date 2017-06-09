package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.web.SysAttDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
public interface UploadService<T> {

    String upload(T t) throws ApplicationException ;

    void saveEmpicture(MultipartFile file,
                       String fileName,
                       UsrUser user);

    void doSysInfoAtt(String lid,
                      String filePath,
                      MultipartFile file,
                      Integer type,
                      UsrUser user);

    void showUp(String path, HttpServletResponse response) throws ApplicationException;

    void saveQR(MultipartFile file, String fileName, UsrUser usrUser);

    SysInfoAtt generateSysInfoAtt(MultipartFile file, String filePath, Integer type);

    void addFile(MultipartFile file) throws ApplicationException;

    String uploadTemp(MultipartFile file) throws ApplicationException;

    void deleteFile(Integer index) throws ApplicationException;

    void uploadFileList(String id, UsrUser user) throws ApplicationException;

    void initFileList(List<SysAttDTO> sysAttDTOList);
}
