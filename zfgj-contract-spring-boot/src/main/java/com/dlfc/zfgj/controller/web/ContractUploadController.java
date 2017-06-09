package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.Utils.CurrentUser;
import com.dlfc.zfgj.convertor.web.ContractShowUpConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.ContractShowUpDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.ContractStepService;
import com.dlfc.zfgj.service.web.UploadService;
import com.dlfc.zfgj.service.web.UsrUserService;
import com.dlfc.zfgj.validator.web.ContractUploadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by K on 2017/3/13.
 */

@RestController
@RequestMapping(value = "/w/upload")
public class ContractUploadController {

    @Autowired
    private UploadService uploadServiceImpl;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private ContractUploadValidator contractUploadValidator;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private ContractShowUpConvertor contractShowUpConvertor;
    @Autowired
    private ContractStepService contractStepService;

    /**
     * 查找图片
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/uploadInit", method = RequestMethod.GET)
    public ListResultDTO<ContractShowUpDTO> uploadInit(@RequestParam String contractId,
                                                       @RequestHeader String token)
            throws CustomRuntimeException {
        try {
            currentUser.getCurrentUser(token);
            contractStepService.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ListResultDTO.failure(new ArrayList(), new ResultError(e.getMessage(),null));
        }
        return contractShowUpConvertor.toResultDTO(uploadServiceImpl.getMapForView(contractId));
    }

//    /**
//     * 多个图片上传
//     *
//     * @param files
//     * @param contractId
//     * @param types
//     * @return
//     * @throws CustomRuntimeException
//     */
//    @RequestMapping(value = "/picture", method = RequestMethod.POST)
//    ResultDTO<Map> upload(@RequestParam MultipartFile[] files,
//                          @RequestParam String contractId,
//                          @RequestParam Integer[] types,
//                          @RequestHeader String token) throws CustomRuntimeException {
//        Map hashMap;
//        try {
//            UsrUser user = usrUserService.getUser(token);
////            contractUploadValidator.validate(files, types);
//            hashMap = uploadServiceImpl.doUpload(files, types, contractId, user);
//        } catch (ApplicationException e) {
//            this.resultError.setErrmsg(e.getMessage());
//            return ResultDTO.failure(new HashMap(), this.resultError);
//        }
//        return ResultDTO.success(hashMap);
//    }

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping(value = "/href", method = RequestMethod.GET)
    ResultDTO<Void> showUp(@RequestParam String path,
                           HttpServletResponse response) {
        try {
            uploadServiceImpl.showUp(path, response);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

//    /**
//     * 上传单个图片时验证
//     *
//     * @param file
//     * @return
//     */
//    @RequestMapping(value = "/singlePicture", method = RequestMethod.POST)
//    ResultDTO<Void> uploadSingle(@RequestParam MultipartFile file
//            ,
//                                 @RequestParam String contractId,
//                                 @RequestParam Integer type,
//                                 @RequestParam String token
//    ) {
//        try {
//            UsrUser user = usrUserService.getUser(token);
//            contractUploadValidator.validate(file);
//            String fileName = uploadServiceImpl.upload(file);
//            uploadServiceImpl.operateData(contractId, type, fileName, file, user);
//        } catch (ApplicationException e) {
//            this.resultError.setErrmsg(e.getMessage());
//            return ResultDTO.failure(this.resultError);
//        }
//        return ResultDTO.success();
//    }

    /**
     * 添加/替换图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    ResultDTO<Void> addFile(@RequestParam Integer type,
                            @RequestParam MultipartFile file) {
        try {
            contractUploadValidator.validate(file);
            uploadServiceImpl.addFile(type, file);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 上传图片（从内存获取）
     *
     * @param contractId
     * @param token
     * @return
     */
    @RequestMapping(value = "/uploadMap", method = RequestMethod.POST)
    ResultDTO<Void> uploadMap(@RequestParam String contractId,
                              @RequestHeader String token) {
        try {
            UsrUser user = usrUserService.getUser(token);
            uploadServiceImpl.uploadMap(contractId, user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }
}
