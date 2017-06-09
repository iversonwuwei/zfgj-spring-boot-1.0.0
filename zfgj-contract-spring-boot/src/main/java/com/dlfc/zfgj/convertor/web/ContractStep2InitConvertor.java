package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ConHouseUserChildrenDTO;
import com.dlfc.zfgj.dto.web.ContractStep2InitDTO;
import com.dlfc.zfgj.dto.web.HouseUserDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.ConHouseUserChildren;
import com.dlfc.zfgj.service.web.ConHouseUserChildrenService;
import com.dlfc.zfgj.service.web.ConHouseUserService;
import com.dlfc.zfgj.service.web.ContractStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class ContractStep2InitConvertor extends AbstractConvertor<ConContract, ContractStep2InitDTO> {

    @Autowired
    private ContractStepService contractStepServiceImpl;
    @Autowired
    private ConHouseUserService conHouseUserServiceImpl;
    @Autowired
    private HouseUserConvertor houseUserConvertor;
    @Autowired
    private ConHouseUserChildrenService conHouseUserChildrenService;
    @Autowired
    private ConHouseUserChildrenConvertor conHouseUserChildrenConvertor;

    @Override
    public ConContract toModel(ContractStep2InitDTO contractStep2InitDTO) {
        return null;
    }

    @Override
    public ContractStep2InitDTO toDTO(ConContract model, boolean forListView) {
        ContractStep2InitDTO contractStep2InitDTO = new ContractStep2InitDTO();
        if (null != model) {
            // 修改合同时，默认信息为上次填写信息
            if (StringUtils.isNotEmpty(model.getHouseAddr())) {
                contractStep2InitDTO.setContractId(model.getId());
                //房屋信息
                contractStep2InitDTO.setHouseAddress(model.getHouseAddr());
                Integer houseStructure = model.getHouseStructure();
                if (null != houseStructure) {
                    contractStep2InitDTO.setHouseStructure(houseStructure.toString());
                }
                if (null != model.getBuildingArea()) {
                    contractStep2InitDTO.setHouseArea(model.getBuildingArea().doubleValue());
                }
                contractStep2InitDTO.setHouseType(model.getHouseType());
                Integer propertyType = model.getPropertyType();
                if (null != propertyType) {
                    contractStep2InitDTO.setHousePropertyIdType(propertyType.toString());
                }
                contractStep2InitDTO.setHousePropertyId(model.getPropertyIdNo());
                // 租金分期
                contractStep2InitDTO.setStages(Integer.valueOf(model.getStages()));
                // 租赁期限
                if (null != model.getLeaseTermYear()) {
                    contractStep2InitDTO.setYears(model.getLeaseTermYear().toString());
                }
                if (null != model.getLeaseTermMonth()) {
                    contractStep2InitDTO.setMonths(model.getLeaseTermMonth().toString());
                }
                contractStep2InitDTO.setContractStartDate(model.getStartTime());
                contractStep2InitDTO.setContractEndDate(model.getEndTime());
                // 租赁标的
                Short rentalMode = model.getRentalMode();
                if (null != rentalMode) {
                    contractStep2InitDTO.setRentalMode(rentalMode.toString());
                }
                Short leasePurpose = model.getLeasePurpose();
                if (null != leasePurpose) {
                    contractStep2InitDTO.setLeaseUse(leasePurpose.toString());
                }
                contractStep2InitDTO.setLeaseDomain(model.getLeaseDomain());
                contractStep2InitDTO.setLeaseArea(model.getLeaseArea());
                //随行子女列表
                List<ConHouseUserChildren> childrens = conHouseUserChildrenService.getByCid(model.getId());
                if (null != childrens){
                    List<ConHouseUserChildrenDTO> childrenDTOS = conHouseUserChildrenConvertor.toListDTO(childrens);
                    if (null != childrenDTOS){
                        ConHouseUserChildrenDTO[] houseUserChildrens = new ConHouseUserChildrenDTO[childrenDTOS.size()];
                        for(int i = 0 ; i < childrenDTOS.size();i++){
                            houseUserChildrens[i] = childrenDTOS.get(i);
                        }
                        contractStep2InitDTO.setConHouseUserChildrenList(houseUserChildrens);
                    }
                }
                // 已保存的实际使用人列表
                List<ConHouseUser> conHouseUserList = conHouseUserServiceImpl.getHouseUserByCId(model.getId());
                if (null != conHouseUserList) {
                    List<HouseUserDTO> houseUserDTOList = houseUserConvertor.toListDTO(conHouseUserList);
                    if (null != houseUserDTOList) {
                        HouseUserDTO[] houseUsers = new HouseUserDTO[houseUserDTOList.size()];
                        for (int i = 0; i < houseUserDTOList.size(); i++) {
                            houseUsers[i] = houseUserDTOList.get(i);
                        }
                        contractStep2InitDTO.setHouUserList(houseUsers);
                    }
                    contractStep2InitDTO.setUserQuantity(conHouseUserList.size());
                }
            }
            // 新建合同时
            // 如果房东在平台签过合同，则系统默认把信息带过来（在此中介上一次签合同中的产权信息）
            else {
                ConContract latestContract = contractStepServiceImpl.getLatestContract(model);
                if (null != latestContract) {
                    contractStep2InitDTO.setHouseAddress(latestContract.getHouseAddr());
                    if (null != latestContract.getHouseStructure()) {
                        contractStep2InitDTO.setHouseStructure(latestContract.getHouseStructure().toString());
                    }
                    if (null != latestContract.getBuildingArea()) {
                        contractStep2InitDTO.setHouseArea(latestContract.getBuildingArea().doubleValue());
                    }
                    if (null != latestContract.getPropertyType()) {
                        contractStep2InitDTO.setHousePropertyIdType(latestContract.getPropertyType().toString());
                    }
                    contractStep2InitDTO.setHousePropertyId(latestContract.getPropertyIdNo());
                    HouseUserDTO houseUserDTO = new HouseUserDTO();
                    houseUserDTO.setHouseUserName(StringUtils.EMPTY);
                    houseUserDTO.setIdentityMode(StringUtils.EMPTY);
                    houseUserDTO.setIdentityModeName(StringUtils.EMPTY);
                    houseUserDTO.setIdNumber(StringUtils.EMPTY);
                    houseUserDTO.setPhoneNumber(StringUtils.EMPTY);
                    contractStep2InitDTO.setHouUserList(new HouseUserDTO[0]);
                }
            }
            // 签约上一步合同版本号（验证用）
            contractStep2InitDTO.setVersion(model.getVersion());
        }
        return contractStep2InitDTO;
    }
}
