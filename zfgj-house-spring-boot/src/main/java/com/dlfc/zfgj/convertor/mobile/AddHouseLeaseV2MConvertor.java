package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.AddHouseLeaseMDTO;
import com.dlfc.zfgj.dto.mobile.SysAttMDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.enums.LeaseInfoSysSourceEnum;
import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.mobile.SelectCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangna on 2017/3/21.
 */
@Component("addHouseLeaseV2MConvertor")
public class AddHouseLeaseV2MConvertor extends AbstractConvertor<HouLeaseInfo,AddHouseLeaseMDTO> {

    @Autowired
    @Qualifier("selectCodeM")
    private SelectCodeMService selectCodeMService;

    @Autowired
    private SysAttMConvertor sysAttMConvertor;

    @Override
    public HouLeaseInfo toModel(AddHouseLeaseMDTO addHouseMDTO) {
        HouLeaseInfo houLeaseInfoV2 = new HouLeaseInfo();
        houLeaseInfoV2.setId(addHouseMDTO.getHouseId());
        if (addHouseMDTO != null){
           // houLeaseInfoV2.setHouseAdd(addHouseMDTO.getHouseAddress());
            //houLeaseInfoV2.setStructure(addHouseMDTO.getHouseStructure());//房屋结构
            houLeaseInfoV2.setDistrict(addHouseMDTO.getDistrict());
            houLeaseInfoV2.setStationid(addHouseMDTO.getStationId());
            //商圈区域拼接
            if (null != addHouseMDTO.getDistrict()){
                String[] districtss = addHouseMDTO.getDistrict().split(",");
                String area = StringUtils.EMPTY;
                String district = StringUtils.EMPTY;
                if (null != districtss && districtss.length == 2){
                    List<SysAreaAreas> areas = selectCodeMService.getByArea();
                    if (null != areas) {
                        for (SysAreaAreas sysAreaAreas : areas) {
                            if (sysAreaAreas.getAreaId().equals(districtss[0])) {
                                area = sysAreaAreas.getArea();
                                break;
                            }
                        }
                    }
                    List<SysTradeAreas> districts = selectCodeMService.getByBusinessArea(districtss[0]);
                    if (null != districts) {
                        for (SysTradeAreas sysTradeAreas : districts) {
                            if (sysTradeAreas.getTradeId().equals(districtss[1])) {
                                district = sysTradeAreas.getName();
                                break;
                            }
                        }
                    }
                    if (StringUtils.isNotEmpty(area) && StringUtils.isNotEmpty(district)) {
                        houLeaseInfoV2.setDistrictName(area + StringUtils.SPACE + district);
                    }
                }
            }


            houLeaseInfoV2.setVillageName(addHouseMDTO.getVillageName());
            houLeaseInfoV2.setNo(addHouseMDTO.getNo() + "," + addHouseMDTO.getUnit() + "," + addHouseMDTO.getDoor());
            houLeaseInfoV2.setRentalArea(new BigDecimal(addHouseMDTO.getBuildingArea()));
            houLeaseInfoV2.setLeaseMode(Integer.parseInt(addHouseMDTO.getLeaseMode()));
            houLeaseInfoV2.setRent(new BigDecimal(addHouseMDTO.getRent()));
            houLeaseInfoV2.setRentType(addHouseMDTO.getRentType());
            houLeaseInfoV2.setCheckinTime(DateUtils.parseDate(addHouseMDTO.getCheckinTime()));
            if (houLeaseInfoV2.getLeaseMode().intValue() == RentalModeEnum.PART_RENT_ENUM.getValue()){
                //分租
                houLeaseInfoV2.setRoom(addHouseMDTO.getRoom());
            }
            houLeaseInfoV2.setFloor(addHouseMDTO.getFloor());
            houLeaseInfoV2.setApartmentLayout(addHouseMDTO.getApartmentLayout());
            houLeaseInfoV2.setOrientation(addHouseMDTO.getOrientation());
            houLeaseInfoV2.setBuildTime(addHouseMDTO.getBuildTime());
            houLeaseInfoV2.setDecorTime(addHouseMDTO.getDecorTime());
            houLeaseInfoV2.setDecor(addHouseMDTO.getDecor());
            houLeaseInfoV2.setTitle(addHouseMDTO.getHouseAddress());
            String description = addHouseMDTO.getDescription();
            if (description != null && !"".equals(description)){
                description = description.replaceAll("\r\n","");
            }
            houLeaseInfoV2.setDescription(description);
            houLeaseInfoV2.setDcrpTxt(description);
            houLeaseInfoV2.setRequirement(addHouseMDTO.getRequirement());
            houLeaseInfoV2.setOwnerCost(addHouseMDTO.getOwnerCost());
            houLeaseInfoV2.setOwnerCostItems(addHouseMDTO.getOwnerCostItems());
            houLeaseInfoV2.setFacilities(addHouseMDTO.getFacilities());
            houLeaseInfoV2.setFeature(addHouseMDTO.getFeature());
            houLeaseInfoV2.setElectric(addHouseMDTO.getElectric());
            //房源来源：租房管家APP
            houLeaseInfoV2.setSysSource(LeaseInfoSysSourceEnum.APP_ZFGJ.getValue());
        }
        return houLeaseInfoV2;
    }

    @Override
    public AddHouseLeaseMDTO toDTO(HouLeaseInfo model, boolean forListView) {
        return null;
    }

}
