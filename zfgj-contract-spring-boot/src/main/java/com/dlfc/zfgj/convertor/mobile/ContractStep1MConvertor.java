package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.NewContractDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.service.mobile.ContractStepMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep1MConvertor extends AbstractConvertor<ConContract, NewContractDTO> {

    @Autowired
    private HouseUserMConvertor houseUserMConvertor;
    @Autowired
    private ContractStepMService contractStepMServiceImpl;
    @Autowired
    private HouseItemsMConvertor houseItemsMConvertor;
    @Autowired
    private ContractOtherCostMConvertor contractOtherCostMConvertor;
    @Autowired
    private ConHouseUserChildrenMConvertor conHouseUserChildrenMConvertor;

    @Override
    public ConContract toModel(NewContractDTO contractStep1DTO) {
        ConContract conContractV2 = new ConContract();
        if (contractStep1DTO != null) {
            conContractV2.setLessorName(contractStep1DTO.getLessorName());
            conContractV2.setLessorIdType(contractStep1DTO.getLessorIdType());
            conContractV2.setLessorIdNo(contractStep1DTO.getLessorId());
            conContractV2.setLessorMobile(contractStep1DTO.getLessorPhoneNumber());
            conContractV2.setLessorEmail(contractStep1DTO.getLessorEmail());
            conContractV2.setLessorAddress(contractStep1DTO.getLessorDeliverAddress());
            conContractV2.setLessorBackupName(contractStep1DTO.getLessorContactName());
            conContractV2.setLesseeName(contractStep1DTO.getTenantryName());
            conContractV2.setLesseeIdType(contractStep1DTO.getTenantryIdType());
            conContractV2.setLesseeIdNo(contractStep1DTO.getTenantryId());
            conContractV2.setLesseeMobile(contractStep1DTO.getTenantryPhoneNumber());
            conContractV2.setLesseeEmail(contractStep1DTO.getTenantryEmail());
            conContractV2.setLesseeAddress(contractStep1DTO.getTenantryDeliverAddress());
            conContractV2.setLesseeBackupName(contractStep1DTO.getTenantryContactName());
            conContractV2.setLesseeBackupTel(contractStep1DTO.getTenantryContactPhoneNumber());
            conContractV2.setLesseeNation(contractStep1DTO.getTenantryNation());
            conContractV2.setLesseeHouRegProvince(contractStep1DTO.getTenantryProvince());
            conContractV2.setLesseeHouRegCity(contractStep1DTO.getTenantryCity());
            conContractV2.setHouseAddr(contractStep1DTO.getHouseAddress());
            conContractV2.setBuildingArea(BigDecimal.valueOf(contractStep1DTO.getHouseArea()));
            conContractV2.setHouseStructure(contractStep1DTO.getHouseStructure());
            conContractV2.setPropertyType(contractStep1DTO.getHousePropertyIdType());
            conContractV2.setLeaseTermYear(contractStep1DTO.getYears());
            conContractV2.setLeaseTermMonth(contractStep1DTO.getMonths());
            conContractV2.setStartTime(contractStep1DTO.getContractStartDate());
            conContractV2.setEndTime(contractStep1DTO.getContractEndDate());
            conContractV2.setRentalMode(Short.valueOf(contractStep1DTO.getRentalMode().toString()));
            if(contractStep1DTO.getLeaseUse() != null){
                conContractV2.setLeasePurpose(Short.valueOf(String.valueOf(contractStep1DTO.getLeaseUse())));
            }
            conContractV2.setLeaseDomain(contractStep1DTO.getLeaseDomain());
            conContractV2.setLeaseArea(contractStep1DTO.getLeaseArea());
            // 实际使用人列表
            conContractV2.setHouseUsers(houseUserMConvertor.toListModel(contractStep1DTO.getHouUserList()));
            conContractV2.setHouseUserChildrens(conHouseUserChildrenMConvertor.toListModel(contractStep1DTO.getChildrenMDTOS()));
            conContractV2.setMonthlyRent(contractStep1DTO.getMonthlyRent());
            conContractV2.setSettlementCycle(contractStep1DTO.getSettlement());
            conContractV2.setOwnerBear(contractStepMServiceImpl.getStrFromList(contractStep1DTO.getOwnerResponsibility()));
            conContractV2.setAgencyFee(contractStep1DTO.getAgentFee());
            conContractV2.setDepositAmt(contractStep1DTO.getDeposit());
            String ownerBearOther = contractStep1DTO.getOwnerBearOther();
            conContractV2.setOwnerBearOther(ownerBearOther);
            conContractV2.setPaymentMethod(contractStep1DTO.isDepositSuperviseFlg() ? (short) 1 : 0);
            conContractV2.setLessorIds(contractStepMServiceImpl.getStrFromList(contractStep1DTO.getLessorPapers()));
            conContractV2.setLessorIdsOther(contractStep1DTO.getOtherLessorPapers());
            conContractV2.setLesseeIds(contractStepMServiceImpl.getStrFromList(contractStep1DTO.getTenantryPapers()));
            conContractV2.setLesseeIdsOther(contractStep1DTO.getOtherTenantryPapers());
            conContractV2.setAdditionalTerms(contractStep1DTO.getOthers());
            // 房屋物品清单信息
            if (null != contractStep1DTO.getDeliverItems()){
                conContractV2.setHouItemsList(houseItemsMConvertor.toListModel(contractStep1DTO.getDeliverItems()));
            }
            // 合同其他相关费用
            conContractV2.setConOtherCostsList(contractOtherCostMConvertor.toListModel(contractStep1DTO.getOtherRelatedItems()));
        }
        return conContractV2;
    }

    @Override
    public NewContractDTO toDTO(ConContract model, boolean forListView) {
        NewContractDTO contractStep1DTO = new NewContractDTO();
        if(model != null){

            contractStep1DTO.setContractId(model.getId());

        }
        return contractStep1DTO;
    }
}
