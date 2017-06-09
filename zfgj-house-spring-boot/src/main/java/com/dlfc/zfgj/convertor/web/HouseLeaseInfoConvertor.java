package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.HouseLeaseDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysTradeAreas;
import com.dlfc.zfgj.enums.LeaseInfoSysSourceEnum;
import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.service.web.SelectCodeService;
import com.dlfc.zfgj.utils.GetAddressLatAndLonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
@Component("addHouseLeaseIfoConvertor")
public class HouseLeaseInfoConvertor extends AbstractConvertor<HouLeaseInfo, HouseLeaseDTO> {

    @Autowired
    private SelectCodeService selectCodeService;

    @Override
    public HouLeaseInfo toModel(HouseLeaseDTO dto) {
        HouLeaseInfo model = new HouLeaseInfo();
        if (StringUtils.isNotBlank(dto.getId())) {
            model.setId(dto.getId());
        }
        model.setLeaseMode(Integer.parseInt(dto.getLeaseMode()));
        if (model.getLeaseMode().intValue() == RentalModeEnum.PART_RENT_ENUM.getValue()) {
            model.setRoom(dto.getRoom());
        }
        //租金类型拼接
        if (StringUtils.isNotBlank(dto.getPledge()) &&
                StringUtils.isNotBlank(dto.getPay())) {
            model.setRentType(dto.getPledge() + "," + dto.getPay());
        }

        model.setRentalArea(new BigDecimal(dto.getAcreage()));
        //户型字符串拼接
        if (StringUtils.isNotBlank(dto.getApartmentLayoutRoom())
                && StringUtils.isNotBlank(dto.getApartmentLayoutHall())
                && StringUtils.isNotBlank(dto.getApartmentLayoutToilet())) {
            model.setApartmentLayout(dto.getApartmentLayoutRoom() + "," +
                    dto.getApartmentLayoutHall() + "," + dto.getApartmentLayoutToilet());
        }
        //楼层字符串拼接
//        if (StringUtils.isNotBlank(dto.getFloor()) &&
        if (null != dto.getFloor() &&
                StringUtils.isNotBlank(dto.getOnFloor())) {

            model.setFloor(dto.getFloor() + "," + dto.getOnFloor());
        }
        //楼号单元室拼接
        if (StringUtils.isNotBlank(dto.getNo()) &&
                StringUtils.isNotBlank(dto.getUnit())
                && StringUtils.isNotBlank(dto.getInRoom())) {
            model.setNo(dto.getNo() + "," + dto.getUnit() + "," + dto.getInRoom());
        }
        //商圈区域拼接
        String area = StringUtils.EMPTY;
        String district = StringUtils.EMPTY;
        List<SysAreaAreas> areas = selectCodeService.getByArea();
        if (null != areas) {
            for (SysAreaAreas sysAreaAreas : areas) {
                if (sysAreaAreas.getAreaId().equals(dto.getAreaId())) {
                    area = sysAreaAreas.getArea();
                    break;
                }
            }
        }
        List<SysTradeAreas> districts = selectCodeService.getByBusinessArea(dto.getAreaId());
        if (null != districts) {
            for (SysTradeAreas sysTradeAreas : districts) {
                if (sysTradeAreas.getTradeId().equals(dto.getDistrict())) {
                    district = sysTradeAreas.getName();
                    break;
                }
            }
        }
        if (StringUtils.isNotEmpty(area) && StringUtils.isNotEmpty(district)) {
            model.setDistrictName(area + StringUtils.SPACE + district);
        }
        if (StringUtils.isNotEmpty(dto.getAreaId()) &&
                StringUtils.isNotEmpty(dto.getDistrict())) {
            model.setDistrict(dto.getAreaId() + "," + dto.getDistrict());
        }
        model.setBuildTime(dto.getBuildTime());
        model.setCheckinTime(dto.getCheckinTime());
        model.setDecor(dto.getDecor());
        model.setDecorTime(dto.getDecorTime());
        String description = dto.getDescription();
        String dcrpTxt = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(description)) {
            description = description.replaceAll("\r\n", StringUtils.EMPTY);
            dcrpTxt = description.replaceAll("</?[^>]+>", "").replaceAll("<a>\\s*|\t|\r|\n</a>", "")
                    .replace("&nbsp;", StringUtils.SPACE);
        }
        model.setDescription(description);
        model.setDcrpTxt(dcrpTxt);
        Integer len;
        if (dto.getElectric() != null && dto.getElectric().length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dto.getElectric().length; i++) {
                stringBuffer.append(dto.getElectric()[i]);
                stringBuffer.append(",");
            }
            if (stringBuffer.length() > 0) {
                len = stringBuffer.length() - 1;
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                model.setElectric(stringBuffer.toString());
            }
        }

        if (dto.getFacilities() != null && dto.getFacilities().length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dto.getFacilities().length; i++) {
                stringBuffer.append(dto.getFacilities()[i]);
                stringBuffer.append(",");
            }
            if (stringBuffer.length() > 0) {
                len = stringBuffer.length() - 1;
                stringBuffer.deleteCharAt(len);
                model.setFacilities(stringBuffer.toString());
            }
        }

        if (dto.getFeature() != null && dto.getFeature().length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dto.getFeature().length; i++) {
                stringBuffer.append(dto.getFeature()[i]);
                stringBuffer.append(",");
            }
            if (stringBuffer.length() > 0) {
                len = stringBuffer.length() - 1;
                stringBuffer.deleteCharAt(len);
                model.setFeature(stringBuffer.toString());
            }
        }

        if (dto.getOwnerCostItems() != null && dto.getOwnerCostItems().length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dto.getOwnerCostItems().length; i++) {
                stringBuffer.append(dto.getOwnerCostItems()[i]);
                stringBuffer.append(",");
            }
            if (stringBuffer.length() > 0) {
                len = stringBuffer.length() - 1;
                stringBuffer.deleteCharAt(len);
                model.setOwnerCostItems(stringBuffer.toString());
            }
        }
        if (dto.getRequirement() != null && dto.getRequirement().length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dto.getRequirement().length; i++) {
                stringBuffer.append(dto.getRequirement()[i]);
                stringBuffer.append(",");
            }
            if (stringBuffer.length() > 0) {
                len = stringBuffer.length() - 1;
                stringBuffer.deleteCharAt(len);
                model.setRequirement(stringBuffer.toString());
            }
        }
        model.setLeaseMode(Integer.parseInt(dto.getLeaseMode()));
        model.setOrientation(dto.getOrientation());
        model.setRent(new BigDecimal(dto.getRent()));
        model.setOwnerCost(dto.getOwnerCost());
        model.setTitle(dto.getTitle());
        model.setStationid(dto.getStationid());
        model.setVillageName(dto.getVillageName());
        // 房源来源：租房管家
        model.setSysSource(LeaseInfoSysSourceEnum.ZFGJ.getValue());
        try {
            String location = GetAddressLatAndLonUtils.getAddressLatAndLon("大连市" + model.getDistrictName() + model.getVillageName());
            String a[] = location.split(",");
            if (a.length == 1) {
                model.setLongitude(a[0]);
            } else {
                model.setLongitude(a[0]);
                model.setLatitude(a[1]);
            }
        } catch (Exception e) {
            model.setLongitude(null);
            model.setLatitude(null);
        }
        return model;
    }

    @Override
    public HouseLeaseDTO toDTO(HouLeaseInfo model, boolean forListView) {

        return null;
    }


}
