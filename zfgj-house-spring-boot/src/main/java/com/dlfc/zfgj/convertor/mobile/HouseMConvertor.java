package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.HouseMDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.mobile.HouseMService;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by wangna on 2017/6/1.
 */
@Component("houseMConvertor")
public class HouseMConvertor extends AbstractConvertor<HouLeaseInfo,HouseMDTO> {


    @Autowired
    @Qualifier("sysInfoMServiceImpl")
    private SysInfoAttrMService sysInfoAttrMService;

    @Autowired
    @Qualifier("hMServiceImpl")
    private HouseMService houseMService;

    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;
    @Override
    public HouLeaseInfo toModel(HouseMDTO houseMDTO) {
        return null;
    }

    @Override
    public HouseMDTO toDTO(HouLeaseInfo model, boolean forListView) {
        HouseMDTO houseDTO = new HouseMDTO();
        houseDTO.setLno(model.getLno());
        houseDTO.setAcreage(model.getRentalArea().toString());
        houseDTO.setCreateTime(DateUtils.dateToStr(model.getCreateTime(), DateUtils.MODIFIED_PATERN));
        houseDTO.setAuditStatus(model.getAuditStatus());
        houseDTO.setHouseId(model.getId());
        houseDTO.setHtitle(model.getTitle());
        houseDTO.setReleaseTime(DateUtils.dateToStr(model.getReleaseTime(), DateUtils.MODIFIED_PATERN));
        houseDTO.setReleaseStatus(model.getReleaseStatus().intValue());
        houseDTO.setRejectReason(model.getRejectReason());
        String apartmentLayout = model.getApartmentLayout();
//        StringBuffer stringBuffer = new StringBuffer();
//        if (StringUtils.isNotEmpty(apartmentLayout)) {
//            String[] strs = apartmentLayout.split(",");
//            if (strs.length == 3) {
//                if (StringUtils.isNotEmpty(strs[0])
//                        && StringUtils.isNotEmpty(strs[1])) {
//                    stringBuffer.append(strs[0]);
//                    stringBuffer.append("厅");
//                    stringBuffer.append(strs[1]);
//                    stringBuffer.append("室");
//                    apartmentLayout = stringBuffer.toString();
//                }
//            }
//        }
        houseDTO.setApartmentLayout(apartmentLayout);
        // 通过CREATE_USER 查找创建出租信息人的名字
        //houseDTO.setEmpName(getNameByCreateUser(model));
        //收藏房源ID
//        List<String> list = houseMService.findByHid(model.getCreateUser());
//        for (String str : list) {
//            if (model.getId().equals(str)) {
//                houseDTO.setColHid(model.getId());
//            }
//        }
        houseDTO.setRefreshable(houseMService.checkFresh(model));
        SysInfoAtt sysInfoAtt = sysInfoAttrMService.selectByLidAndType(model.getId(), InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue());
        if (null != sysInfoAtt){
            houseDTO.setUrl(sysInfoAtt.getFilePath());
        }
        return houseDTO;
    }
//    /**
//     * 通过CREATE_USER 查找创建出租信息人的名字
//     *
//     * @param model
//     * @return
//     */
//    private String getNameByCreateUser(HouLeaseInfo model) {
//        String empName = StringUtils.EMPTY;
//        String createUser = model.getCreateUser();
//        if (StringUtils.isNotEmpty(createUser)) {
//            UsrUser usrUser = userHouseMService.getUserById(createUser);
//            if (null != usrUser && StringUtils.isNotEmpty(usrUser.getPerId())) {
//                SysPerson sysPerson = systemPersonService.getSysPersonById(usrUser.getPerId());
//                if (null != sysPerson) {
//                    return sysPerson.getName();
//                }
//            }
//        }
//        return empName;
//    }
}
