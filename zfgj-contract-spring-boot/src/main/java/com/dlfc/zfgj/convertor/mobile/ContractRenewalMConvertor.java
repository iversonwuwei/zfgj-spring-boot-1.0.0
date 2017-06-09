package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.ContractRenewalMDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.mobile.ContractStepMService;
import com.dlfc.zfgj.service.mobile.SysCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/5.
 */

@Component
public class ContractRenewalMConvertor extends AbstractConvertor<ConContract, ContractRenewalMDTO> {
    // 房主承担
    private static final String LESSOR_BEAR = "lessor_bear";
    // 签约当日甲方提供的证件原件
    private static final String LESSOR_ORIGI_LICENSE = "lessor_origi_license";
    // 签约当日乙方提供的证件原件
    private static final String LESSEE_ORIGI_LICENSE = "lessee_origi_license";
    @Autowired
    private SysCodeMService sysCodeMServiceImpl;
    @Autowired
    private ContractStepMService contractStepMServiceImpl;
    @Autowired
    private SystemCodeMConvertor systemCodeMConvertor;
    @Autowired
    private ConContractMapper conContractMapper;

    @Override
    public ConContract toModel(ContractRenewalMDTO contractRenewalDTO) {
        ConContract conContract = conContractMapper.selectByPrimaryKey(contractRenewalDTO.getContractId());
        if (null != contractRenewalDTO) {
            conContract.setRenewalCid(contractRenewalDTO.getRenewalCid());
            conContract.setLeaseTermYear(contractRenewalDTO.getYears());
            conContract.setLeaseTermMonth(contractRenewalDTO.getMonths());
            conContract.setMonthlyRent(contractRenewalDTO.getMonthlyRent());
            conContract.setOwnerBear(contractStepMServiceImpl.getStrFromList(contractRenewalDTO.getOwnerResponsibility()));
            conContract.setOwnerBearOther(contractRenewalDTO.getOwnerBearOther());
            conContract.setLessorIds(contractStepMServiceImpl.getStrFromList(contractRenewalDTO.getLessorPapers()));
            conContract.setLessorIdsOther(contractRenewalDTO.getOtherLessorPapers());
            conContract.setLesseeIds(contractStepMServiceImpl.getStrFromList(contractRenewalDTO.getTenantryPapers()));
            conContract.setLesseeIdsOther(contractRenewalDTO.getOtherTenantryPapers());
            conContract.setVersion(contractRenewalDTO.getVersion());
        }
        return conContract;
    }

    @Override
    public ContractRenewalMDTO toDTO(ConContract model, boolean forListView) {
        ContractRenewalMDTO contractRenewalDTO = new ContractRenewalMDTO();
        if (null != model) {
            // 续约时间设定
            ConContract tempContract = new ConContract();
            //contractStepMServiceImpl.timeSetForRenewal(model, tempContract);
            contractRenewalDTO.setContractStartDate(tempContract.getStartTime());
            contractRenewalDTO.setContractEndDate(tempContract.getEndTime());
            // 房主承担项目
            List<SysCode> lessorBearList = sysCodeMServiceImpl.getCodeByType(LESSOR_BEAR);
            contractRenewalDTO.setLessorBearList(systemCodeMConvertor.toListDTO(lessorBearList));
            // 房主实际承担项目
            contractRenewalDTO.setOwnerResponsibility(contractStepMServiceImpl.getListFromStr(model.getOwnerBear(), lessorBearList));
            contractRenewalDTO.setOwnerBearOther(model.getOwnerBearOther());
            // 甲方可能提供的证件原件
            List<SysCode> lessorLicenseList = sysCodeMServiceImpl.getCodeByType(LESSOR_ORIGI_LICENSE);
            contractRenewalDTO.setLessorLicenseList(systemCodeMConvertor.toListDTO(lessorLicenseList));
            // 甲方实际提供的证件原件
            contractRenewalDTO.setLessorPapers(contractStepMServiceImpl.getListFromStr(model.getLessorIds(), lessorLicenseList));
            contractRenewalDTO.setOtherLessorPapers(model.getLessorIdsOther());
            // 乙方可能提供的证件原件
            List<SysCode> lesseeLicenseList = sysCodeMServiceImpl.getCodeByType(LESSEE_ORIGI_LICENSE);
            contractRenewalDTO.setLesseeLicenseList(systemCodeMConvertor.toListDTO(lesseeLicenseList));
            // 乙方实际提供的证件原件
            contractRenewalDTO.setTenantryPapers(contractStepMServiceImpl.getListFromStr(model.getLesseeIds(), lesseeLicenseList));
            contractRenewalDTO.setOtherTenantryPapers(model.getLesseeIdsOther());
            // 合同版本号（校验用）
            contractRenewalDTO.setVersion(model.getVersion());
        }
        return contractRenewalDTO;
    }
}
