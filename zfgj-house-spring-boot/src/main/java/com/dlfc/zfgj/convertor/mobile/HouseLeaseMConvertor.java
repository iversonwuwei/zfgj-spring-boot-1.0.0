package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.HouseLeaseMDTO;
import com.dlfc.zfgj.dto.mobile.SysAttMDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.mobile.SyCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.util.parsing.combinator.testing.Str;

import java.util.List;

/**
 * Created by wangna on 2017/6/1.
 */
@Component("houseLeaseMConvertor")
public class HouseLeaseMConvertor extends AbstractConvertor<HouLeaseInfo,HouseLeaseMDTO> {
    private static final String HOU_DECOR = "hou_decor";
    private static final String DECOR_TIME = "decor_time";
    private static final String HOUSE_FACILITIES = "house_facilities";
    private static final String ACCESSORY_SHOP = "accessory_shop";
    private static final String RENTAL_REQUIRE = "rental_require";
    private static final String TENANT_COST_ITEMS = "tenant_cost_items";
    private static final String HOUSE_FEATURES = "house_features";
    private static final String HOU_ORIENTATION = "hou_orientation";


    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;
    @Autowired
    private SysAttMConvertor sysAttMConvertor;
    @Autowired
    private SyCodeMService sysCodeMService;
    @Autowired
    private SysCodeMConvertor sysCodeMConvertor;
    @Override
    public HouLeaseInfo toModel(HouseLeaseMDTO houseLeaseMDTO) {
        return null;
    }

    @Override
    public HouseLeaseMDTO toDTO(HouLeaseInfo model, boolean forListView) {
        HouseLeaseMDTO dto = new HouseLeaseMDTO();
        dto.setHouDecor(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(HOU_DECOR)));
        dto.setDecorTimes(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(DECOR_TIME)));
        dto.setHouseFacilities(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(HOUSE_FACILITIES)));
        dto.setAccessoryShop(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(ACCESSORY_SHOP)));
        dto.setRentalRequire(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(RENTAL_REQUIRE)));
        dto.setTenantCostItems(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(TENANT_COST_ITEMS)));
        dto.setHouseFeatures(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(HOUSE_FEATURES)));
        dto.setHouOrientation(sysCodeMConvertor.toListDTO(sysCodeMService.getCodeByType(HOU_ORIENTATION)));
        if (null != model && StringUtils.isNotEmpty(model.getId())) {
            dto.setRoom(model.getRoom());
            dto.setHouseId(model.getId());
            dto.setBuildingArea(model.getRentalArea().toString());
            dto.setApartmentLayout(model.getApartmentLayout());
            dto.setFloor(model.getFloor());
            dto.setRoom(model.getRoom());
            String[] address = model.getNo().split(",");
            if (address.length == 3){
                dto.setNo(address[0]);
                dto.setUnit(address[1]);
                dto.setDoor(address[2]);
            }
            dto.setRentType(model.getRentType());
            dto.setBuildTime(model.getBuildTime());
            dto.setCheckinTime(DateUtils.dateToStr(model.getCheckinTime(), DateUtils.DATE_YMD_PATERN));
            dto.setDecor(model.getDecor());
            String decorTime = model.getDecorTime();
            if (StringUtils.isNotEmpty(decorTime)) {
                // 兼容旧数据
                if (decorTime.compareTo("4") > 0) {
                    dto.setDecorTime("3");
                } else {
                    dto.setDecorTime(decorTime);
                }
            }
            dto.setDescription(model.getDcrpTxt());
            dto.setElectric(model.getElectric());
            dto.setFeature(model.getFeature());
            dto.setFacilities(model.getFacilities());
            dto.setOwnerCostItems(model.getOwnerCostItems());
            dto.setOwnerCost(model.getOwnerCost());
            dto.setRequirement(model.getRequirement());
            dto.setLeaseMode(model.getLeaseMode().toString());
            dto.setOrientation(model.getOrientation());
            dto.setRent(String.valueOf(model.getRent()));
            dto.setOwnerCost(model.getOwnerCost());
            dto.setHouseAddress(model.getTitle());
            dto.setVillageName(model.getVillageName());
            dto.setDistrict(model.getDistrict());

            dto.setStationId(model.getStationid());
            List<SysInfoAtt> sysInfoAttList = this.findByPath(model.getId());
            if (null != sysInfoAttList) {
                List<SysAttMDTO> sysAttDTOList = sysAttMConvertor.toListDTO(sysInfoAttList);
                dto.setSysAttMDTOList(sysAttDTOList);
            }
            // 审核状态
            if (null != model.getAuditStatus()) {
                dto.setAuditStatus(String.valueOf(model.getAuditStatus()));
            }
            // 拒绝原因
            if (StringUtils.isNotEmpty(model.getRejectReason())) {
                dto.setRejectReason("审核失败原因：" + model.getRejectReason());
            }
        }
         return dto;
    }

    /**
     * 根据id查询附件表图片信息
     */
    private List<SysInfoAtt> findByPath(String id) {
        SysInfoAttExample example = new SysInfoAttExample();
        SysInfoAttExample.Criteria criteria = example.createCriteria();
        criteria.andLidEqualTo(id);
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andFileTypeEqualTo(InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue());
        example.setOrderByClause("SORT ASC,PINDEX ASC");
        return sysInfoAttMapper.selectByExample(example);
    }
}
