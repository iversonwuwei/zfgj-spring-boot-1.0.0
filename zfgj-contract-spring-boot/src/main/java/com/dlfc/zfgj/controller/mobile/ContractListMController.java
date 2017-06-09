package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.mobile.ContractCountMConvertor;
import com.dlfc.zfgj.convertor.mobile.ContractMConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.moblie.ContractCountMDTO;
import com.dlfc.zfgj.dto.moblie.ContractMDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.ConStatusEnum;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.ContractListMService;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import com.housecenter.dlfc.framework.starter.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walden on 2017/2/15.
 */
@Slf4j
@RestController
@RequestMapping(value = "/m/contracts")
public class ContractListMController extends BaseController {

    @Autowired
    private ContractListMService contractListMServiceImpl;
    @Autowired
    private ContractMConvertor contractMConvertor;
    @Autowired
    private UsrUserMService usrUserMService;
    @Autowired
    private ContractCountMConvertor contractCountMConvertor;
    @Autowired
    private ResultError resultError;

    /**
     * /**
     * 检索个人未上传合同列表
     *
     * @param beginPos
     * @param count
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ListResultDTO<ContractMDTO> personalContracts(@RequestHeader String token,
                                                         @RequestParam Integer beginPos,
                                                         @RequestParam Integer count) throws CustomRuntimeException {
        UsrUser usrUser;
        try {
            usrUser = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ListResultDTO.failure(new ArrayList<ContractMDTO>(), this.resultError);
        }
        List<ConContract> conContractList =
                contractListMServiceImpl.getAllPersonalContracts(
                        beginPos,
                        count,
                        usrUser ,
                        ConStatusEnum.WAIT_UPLOAD_ENUM.getValue(),
                        ConStatusEnum.WAIT_CONFIRM_ENUM.getValue(),
                        ConStatusEnum.CREATING_ENUM.getValue(),
                        ConStatusEnum.REJECT_ENUM.getValue());
        return contractMConvertor.toResultDTO(conContractList);
    }

    /**
     * /**
     * 个人未上传合同数
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/personCount", method = RequestMethod.GET)
    public ResultDTO<ContractCountMDTO> personalContracts(@RequestHeader String token) throws CustomRuntimeException {
        UsrUser usrUser;
        try {
            usrUser = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new ContractCountMDTO(), this.resultError);
        }
        List<ConContract> conContractList = contractListMServiceImpl.getPersonalContractsCount(usrUser,
                ConStatusEnum.WAIT_UPLOAD_ENUM.getValue(),
                ConStatusEnum.WAIT_CONFIRM_ENUM.getValue(),
                ConStatusEnum.CREATING_ENUM.getValue(),
                ConStatusEnum.REJECT_ENUM.getValue());
        int count = conContractList.size();
        ConContract conContract = new ConContract();
        conContract.setListCount(count);
        return contractCountMConvertor.toResultDTO(conContract);
    }

    /**
     *
     * 检索个人已上传合同列表
     *
     * @param beginPos
     * @param count
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/finishcontract", method = RequestMethod.GET)
    public ListResultDTO<ContractMDTO> personContracts(@RequestHeader String token,
                                                         @RequestParam Integer beginPos,
                                                         @RequestParam Integer count) throws CustomRuntimeException {
        UsrUser usrUser;
        try {
            usrUser = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ListResultDTO.failure(new ArrayList<ContractMDTO>(), this.resultError);
        }
        List<ConContract> conContractList =
                contractListMServiceImpl.getAllPersonalContracts(
                        beginPos,
                        count,
                        usrUser,
                        ConStatusEnum.ACTIVE_ENUM.getValue(),
                        ConStatusEnum.WAIT_PAY_ENUM.getValue(),
                        ConStatusEnum.EXPIRE_ENUM.getValue(),
                        ConStatusEnum.DISS_AGR_ENUM.getValue(),
                        ConStatusEnum.FINISH_ENUM.getValue(),
                        ConStatusEnum.APPROVING_ENUM.getValue(),
                        ConStatusEnum.APPROVE_REJECT_ENUM.getValue(),
                        ConStatusEnum.CANCEL_ENUM.getValue(),
                        ConStatusEnum.DEPOSIT_CLAIM_ENUM.getValue()
                );
        return contractMConvertor.toResultDTO(conContractList);
    }

    /**
     *
     * 个人已上传合同数
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/finishcount", method = RequestMethod.GET)
    public ResultDTO<ContractCountMDTO> personContracts(@RequestHeader String token) throws CustomRuntimeException {
        UsrUser usrUser;
        try {
            usrUser = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new ContractCountMDTO(), this.resultError);
        }
        List<ConContract> conContractList = contractListMServiceImpl.getPersonalContractsCount(
                usrUser,
                ConStatusEnum.ACTIVE_ENUM.getValue(),
                ConStatusEnum.WAIT_PAY_ENUM.getValue(),
                ConStatusEnum.EXPIRE_ENUM.getValue(),
                ConStatusEnum.DISS_AGR_ENUM.getValue(),
                ConStatusEnum.FINISH_ENUM.getValue(),
                ConStatusEnum.APPROVING_ENUM.getValue(),
                ConStatusEnum.APPROVE_REJECT_ENUM.getValue(),
                ConStatusEnum.CANCEL_ENUM.getValue(),
                ConStatusEnum.DEPOSIT_CLAIM_ENUM.getValue());
        int count = conContractList.size();
        ConContract conContract = new ConContract();
        conContract.setListCount(count);
        return contractCountMConvertor.toResultDTO(conContract);
    }


    /**
     *
     * 个人合同总数
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResultDTO<ContractCountMDTO> count(@RequestHeader String token) throws CustomRuntimeException {
        UsrUser usrUser;
        try {
            usrUser = usrUserMService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            response.setStatus(getResponse().getStatus());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new ContractCountMDTO(), this.resultError);
        }
        List<ConContract> conContractList = contractListMServiceImpl.getPersonalContractsCount(usrUser,0);
        int count = conContractList.size();
        ConContract conContract = new ConContract();
        conContract.setListCount(count);
        return contractCountMConvertor.toResultDTO(conContract);
    }
}
