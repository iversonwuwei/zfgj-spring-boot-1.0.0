package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.convertor.mobile.ContractDetailMConvertor;
import com.dlfc.zfgj.convertor.mobile.ContractStep1MConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.moblie.ContractDetailMDTO;
import com.dlfc.zfgj.dto.moblie.NewContractDTO;
import com.dlfc.zfgj.dto.moblie.PaymentCycleMDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.AgtEmpInfoMService;
import com.dlfc.zfgj.service.mobile.ContractStepMService;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import com.dlfc.zfgj.validator.mobile.ContractStep1MValidator;
import com.housecenter.dlfc.framework.starter.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Slf4j
@RestController
@RequestMapping(value = "/m/step")
public class ContractStepMController extends BaseController {

    @Autowired
    private ContractStepMService contractStepMServiceImpl;
    @Autowired
    private AgtEmpInfoMService agtEmpInfoMServiceImpl;
    @Autowired
    private ContractStep1MValidator contractStep1MValidator;
    @Autowired
    private ContractStep1MConvertor contractStep1MConvertor;
    @Autowired
    private ResultError resultError;
    @Autowired
    private UsrUserMService usrUserMService;
    @Autowired
    private ContractDetailMConvertor contractDetailMConvertor;

    /**
     * 新建合同初始化
     *
     * @return
     */
    @RequestMapping(value = "/step1/init", method = RequestMethod.GET)
    public ResultDTO<Void> step1Init(@RequestHeader String token) {
        UsrUser user;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        // 判断经纪人是否锁定
        if (agtEmpInfoMServiceImpl.isCompLockedByUserId(user)) {
            this.resultError.setErrmsg(PropertyUtils.getErrorMsg("SYS-0016"));
            return ResultDTO.failure(this.resultError);
        }
        return ResultDTO.success();
    }

    /**
     * 新建合同
     *
     * @param newContractDTO
     * @return
     */
    @RequestMapping(value = "/newContract", method = RequestMethod.POST)
    public ResultDTO<NewContractDTO> step1(@RequestHeader String token,
                                           @RequestBody @Valid NewContractDTO newContractDTO)
            throws CustomRuntimeException {
        ConContract conContract;
        UsrUser user;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new NewContractDTO(), this.resultError);
        }
        try {
            contractStep1MValidator.validate(newContractDTO);
            conContract = contractStep1MConvertor.toModel(newContractDTO);
            contractStepMServiceImpl.saveStep1(conContract, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController newContractRequired", e);
            return ResultDTO.failure(new NewContractDTO(),new ResultError(e.getMessage(),null));
        }
        return contractStep1MConvertor.toResultDTO(conContract);
    }

    /**
     * 获取支付方式说明直接返回结果到页面
     *
     * @param paymentCycleMDTO
     * @return
     */
    @RequestMapping(value = "/paymentExplanation", method = RequestMethod.POST)
    public ResultDTO<List> paymentExplanation(@RequestHeader String token,
                                              @RequestBody PaymentCycleMDTO paymentCycleMDTO) {

        List list = contractStepMServiceImpl.getPaymentExplanation(paymentCycleMDTO.getStartDate(),
                paymentCycleMDTO.getEndDate(),
                paymentCycleMDTO.getSettlementCycle(),
                paymentCycleMDTO.getMonthlyRent(),
                paymentCycleMDTO.getTotalMonth());
        return ResultDTO.success(list);
    }

    /**
     * 上传合同
     *
     * @param newContractDTO
     * @return
     */
    @RequestMapping(value = "/uploadContract", method = RequestMethod.POST)
    public ResultDTO<Void> uploadContract(@RequestHeader String token,
                                          @RequestBody NewContractDTO newContractDTO) {
        UsrUser user;
        try {
            user = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(this.resultError);
        }
        try {
            contractStepMServiceImpl.upateContractStatus(newContractDTO.getContractId(), user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController uploadContractRequired", e);
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }


    /**
     * 合同详情
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResultDTO<ContractDetailMDTO> step5Init(@RequestParam String contractId,
                                                   @RequestHeader String token) {
        try {
            contractStepMServiceImpl.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ContractDetailMDTO(), new ResultError(e.getMessage(),null));
        }
        return contractDetailMConvertor.toResultDTO(contractStepMServiceImpl.getContractByCid(contractId));
    }
}
