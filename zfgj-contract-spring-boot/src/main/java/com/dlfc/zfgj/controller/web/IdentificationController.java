package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.convertor.web.SystemCodeConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.SysCodeService;
import com.dlfc.zfgj.service.web.SysPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/w/identification")
public class IdentificationController {
    // 个人证件类型
    public static final String CODE_PER_ID_TYPE = "per_id_type";

    @Autowired
    private SysCodeService sysCodeServiceImpl;
    @Autowired
    private SysPersonService sysPersonServiceImpl;
    @Autowired
    private SystemCodeConvertor systemCodeConvertor;
    @Autowired
    private ResultError resultError;

    /**
     * 获取证件类型列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/idTypeList", method = RequestMethod.GET)
    public ListResultDTO<SystemCodeDTO> getIdTypeList() throws CustomRuntimeException {
        // 证件类型列表
        List<SysCode> idTypeList = sysCodeServiceImpl.getCodeByType(CODE_PER_ID_TYPE);
        return systemCodeConvertor.toResultDTO(idTypeList);
    }

    /**
     * 出租双方身份验证
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    @RequestMapping(value = "/checkForRent", method = RequestMethod.GET)
    public ResultDTO<Void> checkIdentityForRent(@RequestParam String id,
                                                @RequestParam Integer idType,
                                                @RequestParam String name) {
        // 实名认证+系统身份验证
        if (!sysPersonServiceImpl.checkIdentity(id, idType, name)) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0009"),null));
        }
        // 未成年人验证
        else if (sysPersonServiceImpl.checkAge(idType, id)) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0111"),null));
        }
        return ResultDTO.success();
    }

    /**
     * 实际使用者身份验证
     *
     * @param id
     * @param idType
     * @param name
     * @return
     */
    @RequestMapping(value = "/checkForUser", method = RequestMethod.GET)
    public ResultDTO<Void> checkIdentityForUser(@RequestParam String id,
                                                @RequestParam Integer idType,
                                                @RequestParam String name) {
        // 实名认证+系统身份验证
        if (!sysPersonServiceImpl.checkIdentity(id, idType, name)) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0009"),null));
        }
        return ResultDTO.success();
    }
}
