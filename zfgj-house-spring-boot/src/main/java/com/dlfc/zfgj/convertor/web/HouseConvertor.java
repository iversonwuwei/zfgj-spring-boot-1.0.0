package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.HouseDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.web.HouseService;
import com.dlfc.zfgj.service.web.SystemPersonService;
import com.dlfc.zfgj.service.web.UserHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
@Component("houseConvertor")
public class HouseConvertor extends AbstractConvertor<HouLeaseInfo, HouseDTO> {

    @Autowired
    @Qualifier("hServiceImpl")
    private HouseService houseService;
    @Autowired
    private SystemPersonService systemPersonService;
    @Autowired
    @Qualifier("us111333")
    private UserHouseService userHouseService;

    @Override
    public HouLeaseInfo toModel(HouseDTO houseDTO) {
        return null;
    }

    @Override
    public HouseDTO toDTO(HouLeaseInfo model, boolean forListView) {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setLNo(model.getLno());
        houseDTO.setAcreage(model.getRentalArea().toString());
        houseDTO.setCreateTime(DateUtils.dateToStr(model.getCreateTime(), DateUtils.DATE_YMD_PATERN));
        houseDTO.setAuditStatus(model.getAuditStatus());
        houseDTO.setHouseId(model.getId());
        houseDTO.setHTitle(model.getTitle());
        houseDTO.setReleaseStatus(model.getReleaseStatus().intValue());
        String apartmentLayout = model.getApartmentLayout();
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotEmpty(apartmentLayout)) {
            String[] strs = apartmentLayout.split(",");
            if (strs.length == 3) {
                if (StringUtils.isNotEmpty(strs[0])
                        && StringUtils.isNotEmpty(strs[1])) {
                    stringBuffer.append(strs[0]);
                    stringBuffer.append("室");
                    stringBuffer.append(strs[1]);
                    stringBuffer.append("厅");
                    apartmentLayout = stringBuffer.toString();
                }
            }
        }
        houseDTO.setApartmentLayout(apartmentLayout);
        // 通过CREATE_USER 查找创建出租信息人的名字
        houseDTO.setEmpName(getNameByCreateUser(model));
        //收藏房源ID
        List<String> list = houseService.findByHid(model.getCreateUser());
        for (String str : list) {
            if (model.getId().equals(str)) {
                houseDTO.setColHid(model.getId());
            }
        }
        houseDTO.setRefreshable(houseService.checkFresh(model));
        return houseDTO;
    }

    /**
     * 通过CREATE_USER 查找创建出租信息人的名字
     *
     * @param model
     * @return
     */
    private String getNameByCreateUser(HouLeaseInfo model) {
        String empName = StringUtils.EMPTY;
        String createUser = model.getCreateUser();
        if (StringUtils.isNotEmpty(createUser)) {
            UsrUser usrUser = userHouseService.getUserById(createUser);
            if (null != usrUser && StringUtils.isNotEmpty(usrUser.getPerId())) {
                SysPerson sysPerson = systemPersonService.getSysPersonById(usrUser.getPerId());
                if (null != sysPerson) {
                    return sysPerson.getName();
                }
            }
        }
        return empName;
    }
}
