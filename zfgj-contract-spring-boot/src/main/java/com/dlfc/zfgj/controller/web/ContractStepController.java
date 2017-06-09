package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.Utils.CurrentUser;
import com.dlfc.zfgj.convertor.web.*;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.*;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.ContractStepService;
import com.dlfc.zfgj.service.web.UsrUserService;
import com.dlfc.zfgj.validator.web.ContractStep1Validator;
import com.dlfc.zfgj.validator.web.ContractStep2Validator;
import com.dlfc.zfgj.validator.web.ContractStep3Validator;
import com.dlfc.zfgj.validator.web.ContractStep4Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Slf4j
@RestController
@RequestMapping(value = "/w/step")
public class ContractStepController {

    @Autowired
    private ContractStepService contractStepServiceImpl;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private ContractStep1Validator contractStep1Validator;
    @Autowired
    private ContractStep2Validator contractStep2Validator;
    @Autowired
    private ContractStep3Validator contractStep3Validator;
    @Autowired
    private ContractStep4Validator contractStep4Validator;
    @Autowired
    private ContractStep1Convertor contractStep1Convertor;
    @Autowired
    private ContractStep2Convertor contractStep2Convertor;
    @Autowired
    private ContractStep3Convertor contractStep3Convertor;
    @Autowired
    private ContractStep4Convertor contractStep4Convertor;
    @Autowired
    private ContractStep5InitConvertor contractStep5InitConvertor;
    @Autowired
    private ContractStep4InitConvertor contractStep4InitConvertor;
    @Autowired
    private ContractStep3InitConvertor contractStep3InitConvertor;
    @Autowired
    private ContractStep2InitConvertor contractStep2InitConvertor;
    @Autowired
    private ContractStep1InitConvertor contractStep1InitConvertor;
    @Autowired
    private ResultError resultError;
    @Autowired
    private CurrentUser currentUser;

    /**
     * 合同新建第一步前初始化
     *
     * @return
     */
    @RequestMapping(value = "/step1/init", method = RequestMethod.GET)
    public ResultDTO<ContractStep1InitDTO> step1Init(
            @RequestParam(defaultValue = StringUtils.EMPTY, required = false) String contractId) {
        ConContract conContract = contractStepServiceImpl.getContractByCid(contractId);
        return contractStep1InitConvertor.toResultDTO(conContract);
    }

    /**
     * 合同新建第一步
     *
     * @param contractStep1DTO
     * @return
     */
    @RequestMapping(value = "/step1", method = RequestMethod.POST)
    public ResultDTO<String> step1(@RequestBody @Valid ContractStep1DTO contractStep1DTO,
                                   @RequestHeader String token)
            throws CustomRuntimeException {
        ConContract conContract;
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStep1Validator.validate(contractStep1DTO);
            conContract = contractStep1Convertor.toModel(contractStep1DTO);
            contractStepServiceImpl.saveStep1(conContract, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController step1", e);
            return ResultDTO.failure(StringUtils.EMPTY, new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(conContract.getId());
    }

    /**
     * 合同新建第二步前初始化
     *
     * @return
     */
    @RequestMapping(value = "/step2/init", method = RequestMethod.GET)
    public ResultDTO<ContractStep2InitDTO> step2Init(@RequestParam String contractId) {
        try {
            contractStepServiceImpl.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ContractStep2InitDTO(), new ResultError(e.getMessage(),null));
        }
        ConContract conContract = contractStepServiceImpl.getContractByCid(contractId);
        return contractStep2InitConvertor.toResultDTO(conContract);
    }

    /**
     * 获取合同结束日期
     *
     * @param startDateValue
     * @param years
     * @param months
     * @return
     */
    @RequestMapping(value = "/step2/init/getEndDate", method = RequestMethod.GET)
    public Date getEndDateByParams(@RequestParam Long startDateValue,
                                   @RequestParam String years,
                                   @RequestParam String months) {
        return contractStepServiceImpl.getContractEndDate(startDateValue, years, months);
    }

    /**
     * 合同新建第二步
     *
     * @param contractStep2DTO
     * @return
     */
    @RequestMapping(value = "/step2/{contractId}", method = RequestMethod.POST)
    public ResultDTO<String> step2(@PathVariable String contractId,
                                   @RequestBody ContractStep2DTO contractStep2DTO,
                                   @RequestHeader String token)
            throws CustomRuntimeException {
        ConContract conContract;
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStep2DTO.setContractId(contractId);
            contractStep2Validator.validate(contractStep2DTO);
            conContract = contractStep2Convertor.toModel(contractStep2DTO);
            contractStepServiceImpl.saveStep2(conContract, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController step2", e);
            return ResultDTO.failure(StringUtils.EMPTY, new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(contractId);
    }

    /**
     * 合同新建第三步前初始化
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/step3/init", method = RequestMethod.GET)
    public ResultDTO<ContractStep3InitDTO> step3Init(@RequestParam String contractId,
                                                     @RequestHeader String token) {
        try {
            currentUser.getCurrentUser(token);
            contractStepServiceImpl.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ContractStep3InitDTO(), new ResultError(e.getMessage(),null));
        }
        return contractStep3InitConvertor.toResultDTO(contractStepServiceImpl.getContractByCid(contractId));
    }

    /**
     * 获取支付方式说明直接返回结果到页面
     *
     * @param contractId
     * @param monthlyRent
     * @param settlement
     * @return
     */
    @RequestMapping(value = "/step3/init/paymentExplanation", method = RequestMethod.GET)
    public List<String> paymentExplanation(@RequestParam String contractId,
                                           @RequestParam Integer monthlyRent,
                                           @RequestParam(required = false, defaultValue = "1") Integer settlement) {
        return contractStepServiceImpl.getPaymentExplanationForView(contractId, monthlyRent, settlement);
    }

    /**
     * 合同新建第三步
     *
     * @param contractStep3DTO
     * @return
     */
    @RequestMapping(value = "/step3/{contractId}", method = RequestMethod.POST)
    public ResultDTO<String> step3(@PathVariable String contractId,
                                   @RequestBody @Valid ContractStep3DTO contractStep3DTO,
                                   @RequestHeader String token) {
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStep3Validator.validate(contractStep3DTO, contractId);
            contractStep3DTO.setContractId(contractId);
            ConContract conContract = contractStep3Convertor.toModel(contractStep3DTO);
            contractStepServiceImpl.saveStep3(conContract, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController step3", e);
            return ResultDTO.failure(StringUtils.EMPTY, new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(contractId);
    }

    /**
     * 合同新建第四步前初始化
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/step4/init", method = RequestMethod.GET)
    public ResultDTO<ContractStep4InitDTO> step4Init(@RequestParam String contractId,
                                                     @RequestHeader String token)
            throws CustomRuntimeException {
        try {
            currentUser.getCurrentUser(token);
            contractStepServiceImpl.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ContractStep4InitDTO(), new ResultError(e.getMessage(),null));
        }
        return contractStep4InitConvertor.toResultDTO(contractStepServiceImpl.getContractByCid(contractId));
    }

    /**
     * 合同新建第四步
     *
     * @param contractStep4DTO
     * @return
     */
    @RequestMapping(value = "/step4/{contractId}", method = RequestMethod.POST)
    public ResultDTO<String> step4(@PathVariable String contractId,
                                   @RequestBody @Valid ContractStep4DTO contractStep4DTO,
                                   @RequestHeader String token)
            throws CustomRuntimeException {
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStep4Validator.validate(contractStep4DTO);
            contractStep4DTO.setContractId(contractId);
            ConContract conContract = contractStep4Convertor.toModel(contractStep4DTO);
            contractStepServiceImpl.saveStep4(conContract, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController step4", e);
            return ResultDTO.failure(StringUtils.EMPTY, new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success(contractId);
    }

    /**
     * 合同新建第五步前初始化/合同详情
     *
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/step5/init", method = RequestMethod.GET)
    public ResultDTO<ContractStep5InitDTO> step5Init(@RequestParam String contractId,
                                                     @RequestHeader String token) {
        try {
            contractStepServiceImpl.checkContractId(contractId);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ContractStep5InitDTO(), new ResultError(e.getMessage(),null));
        }
        return contractStep5InitConvertor.toResultDTO(contractStepServiceImpl.getContractByCid(contractId));
    }

    /**
     * 合同新建第五步（点击下载按钮）
     *
     * @param contractId
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/step/step5", method = RequestMethod.GET)
    public ResultDTO<Void> step5(@RequestParam String contractId,
                                 @RequestHeader String token) throws CustomRuntimeException {
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStepServiceImpl.saveStep5(contractId, user);
        } catch (ApplicationException e) {
            this.log.info("ConContractController step5", e);
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }
}
