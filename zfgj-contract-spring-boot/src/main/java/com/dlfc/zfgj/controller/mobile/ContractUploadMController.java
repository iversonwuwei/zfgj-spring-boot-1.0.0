package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.UploadMService;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import com.dlfc.zfgj.validator.mobile.ContractUploadMValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by K on 2017/3/13.
 */

@RestController
@RequestMapping(value = "/m/upload")
public class ContractUploadMController {

    @Autowired
    private ContractUploadMValidator contractUploadMValidator;
    @Autowired
    private UploadMService uploadMServiceImpl;
    @Autowired
    private ResultError resultError;
    @Autowired
    private UsrUserMService usrUserMService;


    /**
     * 查看图片
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/uploadInit", method = RequestMethod.GET)
    public ResultDTO<?> uploadInit(@RequestParam String contractId) {
        Map<String, String> hashMap;
        try {
            hashMap = uploadMServiceImpl.getMapForView(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new HashMap<String, String>(),new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(hashMap);
    }

    /**
     * 图片上传
     *
     * @param files
     * @param contractId
     * @param types
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    ResultDTO<?> upload(@RequestHeader String token,
                          @RequestParam MultipartFile[] files,
                          @RequestParam String contractId,
                          @RequestParam Integer[] types) throws CustomRuntimeException{
        Map<String, String> hashMap;
        UsrUser user;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new HashMap<String, String>(), resultError);
        }
        try {
            contractUploadMValidator.validate(files, types);
            hashMap = uploadMServiceImpl.DoUpload(files, types, contractId,user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new HashMap<String, String>(),new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(hashMap);
    }

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
            uploadMServiceImpl.showUp(path, response);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }
}