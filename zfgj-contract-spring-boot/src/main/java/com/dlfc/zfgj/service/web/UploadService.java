package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
public interface UploadService<T> {

    String upload(T file) throws ApplicationException;

    List<SysInfoAtt> getMapForView(String contractId);

//    Map doUpload(MultipartFile[] files,
//                 Integer[] types,
//                 String contractId,
//                 UsrUser user) throws ApplicationException;

    void showUp(String path, HttpServletResponse response) throws ApplicationException;

//    void operateData(String contractId,
//                     Integer type,
//                     String fileName,
//                     T file,
//                     UsrUser user) throws ApplicationException;

    void addFile(Integer type, MultipartFile file) throws ApplicationException;

    String uploadTemp(MultipartFile file) throws ApplicationException;

    void uploadMap(String contractId, UsrUser user) throws ApplicationException;

    void initFileMap(SysInfoAtt model);
}
