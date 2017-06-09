package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.mobile.SysAttMConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.mobile.SysAttMDTO;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.UploadMService;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.validator.mobile.UploadMValidator;
import com.housecenter.dlfc.framework.starter.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangna on 2017/3/27.
 */

@Slf4j
@RestController
@RequestMapping(value = "/m/upload")
public class UploadMController extends BaseController {
    //房间图片
    private final static int HOUSE_PIC_ENUM = InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue();
    //经纪人图片
    private final static int EMP_AVATAR_ENUM = InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue();

    @Autowired
    private UploadMService uploadMServiceImpl;


    @Autowired
    private ResultError resultError;

    @Autowired
    private UploadMValidator uploadMValidator;

    @Autowired
    private SysAttMConvertor sysAttMConvertor;

    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;

    /**
     * 房间图片上传
     *
     * @param files
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/roompicture", method = RequestMethod.POST)
    ListResultDTO<SysAttMDTO> upload(@RequestHeader String token,
                                     @RequestParam MultipartFile[] files)
            throws CustomRuntimeException {
        List<SysInfoAtt> sysInfoAttList;
        UsrUser usrUser;
        try {
            usrUser = userHouseMService.getUserName(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ListResultDTO.failure(new ArrayList<SysAttMDTO>(), this.resultError);
        }
        try {
            uploadMValidator.validate(files, HOUSE_PIC_ENUM);
            if (files.length <= 12) {
                sysInfoAttList = uploadMServiceImpl.upload(files, HOUSE_PIC_ENUM, usrUser.getId());
                return sysAttMConvertor.toResultDTO(sysInfoAttList);
            }
        } catch (ApplicationException e) {
            return ListResultDTO.failure(new ArrayList<SysAttMDTO>(), new ResultError(e.getCode(), e.getMessage(), null));
        }
        this.resultError.setErrmsg("上传图片不能大于12张");
        return ListResultDTO.failure(new ArrayList<SysAttMDTO>(), this.resultError);
    }


    /**
     * 经纪人图片上传
     *
     * @param files
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/empicture", method = RequestMethod.POST)
    ListResultDTO<SysAttMDTO> empicture(@RequestHeader String token,
                                        @RequestParam MultipartFile[] files)
            throws CustomRuntimeException {
        List<SysInfoAtt> sysInfoAttList;
        UsrUser usrUser;
        try {
            usrUser = userHouseMService.getUserName(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ListResultDTO.failure(new ArrayList<SysAttMDTO>(), this.resultError);
        }
        try {
            uploadMValidator.validate(files, EMP_AVATAR_ENUM);
            if (files.length <= 1) {
                sysInfoAttList = uploadMServiceImpl.upload(files, EMP_AVATAR_ENUM, usrUser.getId());
                return sysAttMConvertor.toResultDTO(sysInfoAttList);
            }

        } catch (ApplicationException e) {
            return ListResultDTO.failure(new ArrayList<SysAttMDTO>(), new ResultError(e.getCode(), e.getMessage(), null));
        }
        this.resultError.setErrmsg("上传图片不能大于1张");
        return new ListResultDTO().failure(new ArrayList<SysAttMDTO>(), this.resultError);
    }

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping(value = "/href", method = RequestMethod.GET)
    ResultDTO<Void> showUp(@RequestHeader String token,
                           @RequestParam String path,
                           HttpServletResponse response) {
        try {
            uploadMServiceImpl.showUp(path, response);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getCode(), e.getMessage(), null));
        }
        return ResultDTO.success();
    }

    /**
     * 上传资格证书图片
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/uploadCert", method = RequestMethod.POST)
    public ResultDTO<List<String>> uploadCert(@RequestBody MultipartFile[] files) {
        List<String> pathList = new ArrayList<>();
        try {
            uploadMValidator.validate(files, InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue());
            pathList = uploadMServiceImpl.uploadFiles(files, InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue());
        } catch (ApplicationException e) {
            return ResultDTO.failure(pathList, new ResultError(e.getMessage(), null));
        }
        return ResultDTO.success(pathList);
    }
}
