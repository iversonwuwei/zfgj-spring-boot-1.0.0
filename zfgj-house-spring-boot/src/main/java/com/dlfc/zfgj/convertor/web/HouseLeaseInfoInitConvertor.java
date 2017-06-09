package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.HouseLeaseDTO;
import com.dlfc.zfgj.dto.web.SysAttDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.enums.AuditStatusEnum;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.service.web.SyCodeService;
import com.dlfc.zfgj.service.web.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
@Component("houseleaseConvertor")
public class HouseLeaseInfoInitConvertor extends AbstractConvertor<HouLeaseInfo, HouseLeaseDTO> {

    private static final String HOU_DECOR = "hou_decor";
    private static final String DECOR_TIME = "decor_time";
    private static final String HOUSE_FACILITIES = "house_facilities";
    private static final String ACCESSORY_SHOP = "accessory_shop";
    private static final String RENTAL_REQUIRE = "rental_require";
    private static final String TENANT_COST_ITEMS = "tenant_cost_items";
    private static final String HOUSE_FEATURES = "house_features";

    @Autowired
    private SysInfoAttMapper sysInfoAttMapper;
    @Autowired
    private SysAttConvertor sysAttConvertor;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private SyCodeService syCodeService;
    @Autowired
    private SysCodeConvertor sysCodeConvertor;

    @Override
    public HouLeaseInfo toModel(HouseLeaseDTO dto) {
        return null;
    }

    @Override
    public HouseLeaseDTO toDTO(HouLeaseInfo model, boolean forListView) {
        HouseLeaseDTO dto = new HouseLeaseDTO();
        if (StringUtils.isNotEmpty(model.getId())) {
            dto.setRoom(model.getRoom());
            dto.setId(model.getId());
            dto.setAcreage(model.getRentalArea().toString());
            String[] str;
            if (StringUtils.isNotBlank(model.getApartmentLayout())) {
                str = model.getApartmentLayout().split(",");
                dto.setApartmentLayoutRoom(str[0]);
                dto.setApartmentLayoutHall(str[1]);
                dto.setApartmentLayoutToilet(str[2]);
            }
            if (StringUtils.isNotBlank(model.getFloor())) {
                str = model.getFloor().split(",");
                String floor = str[0];
                if (StringUtils.isNotEmpty(floor)) {
                    dto.setFloor(Integer.valueOf(floor));
                }
                dto.setOnFloor(str[1]);
            }
            if (StringUtils.isNotBlank(model.getNo())) {
                str = model.getNo().split(",");
                dto.setNo(str[0]);
                dto.setUnit(str[1]);
                dto.setInRoom(str[2]);
            }
            if (StringUtils.isNotBlank(model.getRentType())) {
                str = model.getRentType().split(",");
                dto.setPledge(str[0]);
                dto.setPay(str[1]);
            }
            dto.setBuildTime(model.getBuildTime());
            dto.setCheckinTime(model.getCheckinTime());
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
            dto.setDescription(model.getDescription());
            if (StringUtils.isNotBlank(model.getElectric())) {
                str = model.getElectric().split(",");
                dto.setElectric(str);
            }
            if (StringUtils.isNotBlank(model.getFacilities())) {
                str = model.getFacilities().split(",");
                dto.setFacilities(str);
            }
            if (StringUtils.isNotBlank(model.getFeature())) {
                str = model.getFeature().split(",");
                dto.setFeature(str);
            }
            if (StringUtils.isNotBlank(model.getOwnerCostItems())) {
                str = model.getOwnerCostItems().split(",");
                dto.setOwnerCostItems(str);
            }
            if (StringUtils.isNotBlank(model.getRequirement())) {
                str = model.getRequirement().split(",");
                dto.setRequirement(str);
            }
            dto.setLeaseMode(model.getLeaseMode().toString());
            dto.setOrientation(model.getOrientation());
            dto.setRent((double) model.getRent().intValue());
            dto.setOwnerCost(model.getOwnerCost());
            dto.setTitle(model.getTitle());
            dto.setVillageName(model.getVillageName());
            if (StringUtils.isNotEmpty(model.getDistrict())) {
                str = model.getDistrict().split(",");
                dto.setAreaId(str[0]);
                dto.setDistrict(str[1]);
            }
            dto.setStationid(model.getStationid());
            List<SysInfoAtt> sysInfoAttList = this.findByPath(model.getId());
            if (null != sysInfoAttList) {
                List<SysAttDTO> sysAttDTOList = sysAttConvertor.toListDTO(sysInfoAttList);
                dto.setSysAttDTOList(sysAttDTOList);
                uploadService.initFileList(sysAttDTOList);
            }
            // 审核结果
            if (AuditStatusEnum.REFUSE_ENUM.getValue() == model.getAuditStatus()) {
                dto.setExamineResult(false);
            }
            // 拒绝原因
            if (StringUtils.isNotEmpty(model.getRejectReason())) {
                dto.setRejectReason("审核失败原因：" + model.getRejectReason());
            }
        }
        dto.setHouDecor(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(HOU_DECOR)));
        dto.setDecorTimes(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(DECOR_TIME)));
        dto.setHouseFacilities(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(HOUSE_FACILITIES)));
        dto.setAccessoryShop(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(ACCESSORY_SHOP)));
        dto.setRentalRequire(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(RENTAL_REQUIRE)));
        dto.setTenantCostItems(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(TENANT_COST_ITEMS)));
        dto.setHouseFeatures(sysCodeConvertor.toListDTO(syCodeService.getCodeByType(HOUSE_FEATURES)));
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
