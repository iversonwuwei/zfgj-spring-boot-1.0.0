package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.common.page.web.CutomizedPage;
import com.dlfc.zfgj.convertor.web.ContractConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.ContractDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.ContractListService;
import com.dlfc.zfgj.service.web.ContractStepService;
import com.dlfc.zfgj.service.web.UsrUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by walden on 2017/2/15.
 */
@Slf4j
@RestController
@RequestMapping(value = "/w/contracts")
public class ContractListController {

    @Autowired
    private ContractListService contractListServiceImpl;
    @Autowired
    private ContractStepService contractStepServiceImpl;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private ContractConvertor contractConvertor;
    @Autowired
    private CutomizedPage cutomizedPage;
    @Autowired
    private ResultError resultError;

    /**
     * 检索个人合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public PageInfo<ContractDTO> personalContracts(@RequestParam(required = false, defaultValue = "1")
                                                           String timeType,
                                                   @RequestParam(required = false, defaultValue = "0")
                                                           String orderBy,
                                                   @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getAllPersonalContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 检索部门合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public PageInfo<ContractDTO> departmentalContracts(@RequestParam(required = false, defaultValue = "1")
                                                               String timeType,
                                                       @RequestParam(required = false, defaultValue = "0")
                                                               String orderBy,
                                                       @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getAllDepartmentalContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 检索生效中的个人合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/person/effective", method = RequestMethod.GET)
    public PageInfo<ContractDTO> personalEffectiveContracts(@RequestParam(required = false, defaultValue = "2")
                                                                    String timeType,
                                                            @RequestParam(required = false, defaultValue = "0")
                                                                    String orderBy,
                                                            @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getPersonalEffectiveContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 检索生效中的部门合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/department/effective", method = RequestMethod.GET)
    public PageInfo<ContractDTO> departmentalEffectiveContracts(@RequestParam(required = false, defaultValue = "2")
                                                                        String timeType,
                                                                @RequestParam(required = false, defaultValue = "0")
                                                                        String orderBy,
                                                                @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getDepartmentalEffectiveContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 根据条件检索个人合同列表
     * 搜索条件：房源地址/状态/维护人
     *
     * @param searchParam 搜索参数
     * @param timeType    排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy     排序方式→1：正序；0：倒序
     * @return
     */
    @RequestMapping(value = "/person/search", method = RequestMethod.GET)
    public PageInfo<ContractDTO> personalSearchByParam(@RequestParam String searchParam,
                                                       @RequestParam(required = false, defaultValue = "1")
                                                               String timeType,
                                                       @RequestParam(required = false, defaultValue = "0")
                                                               String orderBy,
                                                       @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getAllPersonalContractsBy(searchParam, timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 根据条件检索部门合同列表
     * 搜索条件：房源地址/状态/维护人
     *
     * @param searchParam 搜索参数
     * @param timeType    排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy     排序方式→1：正序；0：倒序
     * @return
     */
    @RequestMapping(value = "/department/search", method = RequestMethod.GET)
    public PageInfo<ContractDTO> departmentalSearchByParam(@RequestParam String searchParam,
                                                           @RequestParam(required = false, defaultValue = "1")
                                                                   String timeType,
                                                           @RequestParam(required = false, defaultValue = "0")
                                                                   String orderBy,
                                                           @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getAllDepartmentalContractsBy(searchParam, timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 即将到期的个人合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     */
    @RequestMapping(value = "/person/nearing", method = RequestMethod.GET)
    public PageInfo<ContractDTO> personalNearingContracts(@RequestParam(required = false, defaultValue = "3")
                                                                  String timeType,
                                                          @RequestParam(required = false, defaultValue = "0")
                                                                  String orderBy,
                                                          @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getPersonalNearingContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 即将到期的部门合同列表
     *
     * @param timeType 排序根据的时间类型:→1：创建时间；2：生效时间；3：终止时间
     * @param orderBy  排序方式→1：正序；0：倒序
     * @return
     */
    @RequestMapping(value = "/department/nearing", method = RequestMethod.GET)
    public PageInfo<ContractDTO> departmentalNearingContracts(@RequestParam(required = false, defaultValue = "3")
                                                                      String timeType,
                                                              @RequestParam(required = false, defaultValue = "0")
                                                                      String orderBy,
                                                              @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = usrUserService.getUser(token);
        List<ConContract> conContractList = contractListServiceImpl
                .getDepartmentalNearingContracts(timeType, orderBy, user);
        cutomizedPage.setSource(contractConvertor.toListDTO(conContractList));
        return cutomizedPage.first();
    }

    /**
     * 合同取消
     *
     * @param contractId
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ResultDTO<Void> cancelContract(@RequestParam String contractId,
                                          @RequestHeader String token)
            throws CustomRuntimeException {
        try {
            UsrUser user = usrUserService.getUser(token);
            contractStepServiceImpl.checkContractId(contractId);
            contractStepServiceImpl.cancelContract(contractId, user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }
}
