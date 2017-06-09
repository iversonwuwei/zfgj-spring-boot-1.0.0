package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.FileDownload;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.SysInfoAttrService;
import com.dlfc.zfgj.service.web.UploadService;
import com.dlfc.zfgj.service.web.UserHouseService;
import com.dlfc.zfgj.validator.web.UploadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by K on 2017/3/13.
 */

@RestController
@RequestMapping(value = "/w/upload")
public class UploadController {

    @Autowired
    @Qualifier(value = "house")
    private UploadService uploadServiceImpl;
    @Autowired
    private UploadValidator uploadValidator;
    @Autowired
    private ResultError resultError;
    @Autowired
    @Qualifier("us111333")
    private UserHouseService userHouseService;
    @Autowired
    private SysInfoAttrService sysInfoAttrService;

    /**
     * 经纪人图片上传
     *
     * @param file
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/empicture", method = RequestMethod.POST)
    public ResultDTO<Void> empicture(@RequestParam MultipartFile file,
                                     @RequestParam String token)
            throws CustomRuntimeException {
        try {
            UsrUser usrUser = userHouseService.getUser(token);
            uploadValidator.validate(file);
            String filepath = uploadServiceImpl.upload(file);
            uploadServiceImpl.saveEmpicture(file, filepath, usrUser);
            return ResultDTO.success();
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
    }

    /**
     * 上传二维码
     */
    @RequestMapping(value = "/erwei", method = RequestMethod.POST)
    public ResultDTO<Void> erwei(@RequestParam MultipartFile file,
                                 @RequestHeader String token)
            throws CustomRuntimeException {
        try {
            UsrUser usrUser = userHouseService.getUser(token);
            uploadValidator.validate(file);
            uploadValidator.validateQR(file);
            String filepath = uploadServiceImpl.upload(file);
            uploadServiceImpl.saveQR(file, filepath, usrUser);
            return ResultDTO.success();
        } catch (Exception e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
    }

    /**
     * 房间图片上传验证
     *
     * @param file
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/roompicture", method = RequestMethod.POST)
    public ResultDTO<Void> beforeUpload(@RequestParam MultipartFile file)
            throws CustomRuntimeException {
        try {
            uploadValidator.validate(file);
            uploadServiceImpl.addFile(file);
            return ResultDTO.success();
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
    }

    /**
     * 删除图片
     *
     * @param index
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public ResultDTO<Void> del(@RequestParam Integer index) throws CustomRuntimeException {
        try {
            uploadServiceImpl.deleteFile(index);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

//    /**
//     * 房间图片批量上传
//     *
//     * @param id
//     * @param token
//     * @return
//     */
//    @RequestMapping(value = "/files", method = RequestMethod.POST)
//    public ResultDTO<Void> upload(@RequestParam String id,
//                                  @RequestHeader String token) {
//        try {
//            UsrUser user = userHouseService.getUserName(token);
//            List<SysInfoAtt> sysInfoAttList = new ArrayList<>();
//            for (MultipartFile file : files) {
////                MultipartFile file = (MultipartFile) object;
//                String filePath = uploadServiceImpl.upload(file);
//                SysInfoAtt sysInfoAtt = uploadServiceImpl.generateSysInfoAtt(
//                        file, filePath, InfoAttFileTypeEnum.ROOM_PIC_ENUM.getValue());
//                sysInfoAttList.add(sysInfoAtt);
//            }
//            sysInfoAttrService.saveSysInfoAttsForRoom(sysInfoAttList, id, usrUser);
//        } catch (ApplicationException e) {
//            this.resultError.setErrmsg(e.getMessage());
//            return ResultDTO.failure(this.resultError);
//        }
//        return ResultDTO.success();
//    }

    /**
     * 图片回显
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping(value = "/href/{imgId}", method = RequestMethod.GET)
    public ResultDTO<Void> showUp(@RequestParam String path,
                                  HttpServletResponse response, @PathVariable String imgId) throws InterruptedException {
        try {
            Thread.sleep(1000L);
//           uploadServiceImpl.showUp(path, response);
            FileDownload.download(path, response);
        } catch (Exception e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 让它停一会儿
     *
     * @return
     */
    @RequestMapping(value = "/anranya", method = RequestMethod.GET)
    public ResultDTO<Void> anranya() {
        return ResultDTO.success();
    }
}
