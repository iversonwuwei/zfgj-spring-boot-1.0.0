package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.convertor.mobile.SystemCodeMConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/m/identification")
public class IdentificationMController {
    // 个人证件类型
    public static final String CODE_PER_ID_TYPE = "per_id_type";

    @Autowired
    private SysCodeMService sysCodeMServiceImpl;
    @Autowired
    private SystemCodeMConvertor systemCodeMConvertor;
    @Autowired
    private SysPersonMService sysPersonMServiceImpl;
    @Autowired
    private ResultError resultError;
    @Autowired
    private UsrUserMService usrUserMService;
    @Autowired
    private AgtEmpInfoMService agtEmpInfoMService;
    @Autowired
    private UserMService userMService;
    @Autowired
    @Qualifier("contractForM")
    private SysMobileCapchaService sysMobileCapchaService;

    /**
     * 获取证件类型列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/idTypeList", method = RequestMethod.GET)
    public ListResultDTO<SystemCodeMDTO> getIdTypeList() throws CustomRuntimeException {
        // 证件类型列表
        List<SysCode> idTypeList = sysCodeMServiceImpl.getCodeByType(CODE_PER_ID_TYPE);
        return systemCodeMConvertor.toResultDTO(idTypeList);
    }

    /**
     * 实名认证
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/checkId", method = RequestMethod.GET)
    public ResultDTO<Void> checkId(@RequestParam String id,
                                   @RequestParam Integer idType,
                                   @RequestParam String name) {
        if (!sysPersonMServiceImpl.checkId(id, idType, name)) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0009"),null));
        }
        return ResultDTO.success();
    }

    /**
     * 更改手机号
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/changePhone", method = RequestMethod.GET)
    public ResultDTO<Void> changePhone(@RequestHeader String token,
                                       @RequestParam String phone,
                                       @RequestParam String code,
                                       @RequestParam String msgId) {
        UsrUser user = null;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        try {
            if (!agtEmpInfoMService.changePhone(user, phone, code, msgId)) {
                return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0131"),null));
            }
        } catch (Exception e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @param domain
     * @return
     */
    @RequestMapping(value = "/verCodeSend", method = RequestMethod.GET)
    public ResultDTO<String> verCodeSend(@RequestParam String phone,
                                         @RequestParam String domain) {
        if (!sysMobileCapchaService.getCaptchaCountByMobile(phone, domain)) {
            ResultError resultError = new ResultError();
            resultError.setErrmsg(PropertyUtils.getErrorMsg("SYS-0132"));
            return ResultDTO.failure("", resultError);
        }
        String id;
        try {
            id = userMService.codeSend(phone, domain);
        } catch (Exception e) {
            return ResultDTO.failure("",new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(id);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ResultDTO<Void> changePassword(@RequestHeader String token,
                                          @RequestParam String password,
                                          @RequestParam String newPassword) {
        UsrUser user = null;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        if (!userMService.validatePassword(password, user.getPassword())) {
            return ResultDTO.failure(new ResultError("登录密码不正确",null));
        }
        if (!userMService.changePassword(newPassword, user)) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0133"),null));
        }
        return ResultDTO.success();
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ResultDTO<Void> forgetPassword(@RequestParam String phone,
                                          @RequestParam String password,
                                          @RequestParam String code,
                                          @RequestParam String msgId) {
        try {
            if (!userMService.forgetPassword(phone, password, code, msgId)) {
                return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0133"),null));
            }
        } catch (Exception e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    @RequestMapping(value = "/passwordValidate", method = RequestMethod.GET)
    public ResultDTO<Void> passwordValidate(@RequestHeader String token,
                                            @RequestParam String password) {
        UsrUser user = null;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        if (!userMService.validatePassword(password, user.getPassword())) {
            return ResultDTO.failure(new ResultError("登录密码不正确",null));
        }
        return ResultDTO.success();
    }

    /**
     * 意见反馈
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/feedBack", method = RequestMethod.GET)
    public ResultDTO<Void> feedBack(@RequestHeader String token,
                                    @RequestParam String content,
                                    HttpServletRequest request) {
        UsrUser user = null;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        if (!userMService.feedBack(content, request, user)) {
            return ResultDTO.failure(new ResultError("提交意见反馈失败",null));
        }
        return ResultDTO.success();
    }
}
