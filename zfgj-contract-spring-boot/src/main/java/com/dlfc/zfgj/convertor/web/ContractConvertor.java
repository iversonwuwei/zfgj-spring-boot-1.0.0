package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractDTO;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.enums.ConStatusEnum;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import com.dlfc.zfgj.service.web.SysPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/16.
 */
@Component
public class ContractConvertor extends AbstractConvertor<ConContract, ContractDTO> {

    @Autowired
    private SysPersonService sysPersonService;
    @Autowired
    private AgtEmpInfoService agtEmpInfoService;

    @Override
    public ConContract toModel(ContractDTO ContractDTO) {
        return null;
    }

    @Override
    public ContractDTO toDTO(ConContract model, boolean forListView) {
        ContractDTO contractDTO = new ContractDTO();
        if (null != model) {
            contractDTO.setId(model.getId());
            // 合同编号（不是合同表主键）
            contractDTO.setContractId(model.getNo());
            contractDTO.setCreatedDate(model.getCreateTime());
            contractDTO.setAddress(model.getHouseAddr());
            contractDTO.setFeeOfMonth(model.getMonthlyRent());
            contractDTO.setStartDate(model.getStartTime());
            contractDTO.setEndDate(model.getEndTime());
            contractDTO.setDeposit(model.getDepositAmt());
            if (null != model.getStatus()) {
                contractDTO.setContractStatus(ConStatusEnum.getName(model.getStatus()));
            }
            contractDTO.setRentalMode(model.getRentalMode());
            contractDTO.setLeaseDomain(model.getLeaseDomain());
            contractDTO.setHouseAddress(model.getHouseAddr());
            String eid = model.getEid();
            if (StringUtils.isNotEmpty(eid)) {
                AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoById(eid);
                if (null != agtEmpInfo) {
                    SysPerson sysPerson = sysPersonService.getSyspersonsById(agtEmpInfo.getPid());
                    if (null != sysPerson) {
                        contractDTO.setAgentName(sysPerson.getName());
                    }
                }
            }
        }
        return contractDTO;
    }
}
