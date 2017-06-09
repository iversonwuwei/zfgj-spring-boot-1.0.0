package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep2DTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.enums.RentalModeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by walden on 2017/2/27.
 */

@Component
public class ContractStep2Convertor extends AbstractConvertor<ConContract, ContractStep2DTO> {

    @Autowired
    private HouseUserConvertor houseUserConvertor;
    @Autowired
    private ConHouseUserChildrenConvertor conHouseUserChildrenConvertor;

    @Override
    public ConContract toModel(ContractStep2DTO dto) {
        ConContract model = new ConContract();
        if (null != dto) {
            model.setId(dto.getContractId());
            model.setHouseAddr(dto.getHouseAddress());
            model.setHouseStructure(dto.getHouseStructure());
            model.setBuildingArea(BigDecimal.valueOf(dto.getHouseArea()));
            model.setHouseType(dto.getHouseType());
            model.setPropertyType(dto.getHousePropertyIdType());
            model.setPropertyIdNo(dto.getHousePropertyId());
            model.setStages("0" + dto.getStages());
            model.setLeaseTermYear(dto.getYears());
            model.setLeaseTermMonth(dto.getMonths());
            model.setStartTime(dto.getContractStartDate());
            model.setEndTime(dto.getContractEndDate());
            model.setRentalMode(Short.valueOf(dto.getRentalMode().toString()));
            model.setLeasePurpose(Short.valueOf(dto.getLeaseUse().toString()));
            model.setLeaseDomain(dto.getLeaseDomain());
            if (RentalModeEnum.PART_RENT_ENUM.getValue() == dto.getRentalMode()) {
                model.setLeaseArea(dto.getLeaseArea());
            } else {
                // 整租时 出租面积为建筑面积
                model.setLeaseArea(BigDecimal.valueOf(dto.getHouseArea()));
            }
            // 实际使用人列表
            model.setHouseUsers(houseUserConvertor.toListModel(dto.getHouUserList()));
            //随行子女列表
            model.setHouseUserChildrens(conHouseUserChildrenConvertor.toListModel(dto.getConHouseUserChildrenDTOS()));
            model.setVersion(dto.getVersion());
        }
        return model;
    }

    @Override
    public ContractStep2DTO toDTO(ConContract model, boolean forListView) {
        ContractStep2DTO dto = new ContractStep2DTO();
        if (null != model) {
            dto.setContractId(model.getId());
        }
        return dto;
    }
}
