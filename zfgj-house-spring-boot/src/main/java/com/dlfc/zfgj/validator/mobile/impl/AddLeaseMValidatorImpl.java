package com.dlfc.zfgj.validator.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.mobile.AddHouseLeaseMDTO;
import com.dlfc.zfgj.validator.mobile.AddLeaseMValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by wangna on 2017/3/22.
 */
@Component
public class AddLeaseMValidatorImpl implements AddLeaseMValidator{
    // 租金分期 是否分期 00：否 01 ：是
    private static final String BY_STAGES = "01";
    Object[] arguments;
    @Override
    public void checkHouse(AddHouseLeaseMDTO addHouseMDTO) throws ApplicationException {

        Pattern pattern = Pattern.compile("[0-9]{1,10}");

        //单元验证
        if (StringUtils.isEmpty(addHouseMDTO.getUnit())){
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0117"));
        }
        //室验证
        if (StringUtils.isEmpty(addHouseMDTO.getDoor())){
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0118"));
        }

        //楼层验证
        if (StringUtils.isNotEmpty(addHouseMDTO.getFloor())) {
            String[] str = addHouseMDTO.getFloor().split(",");
            for (int i = 0; i < str.length; i++) {
                if(StringUtils.isEmpty(str[i])){
                arguments = new Object[]{"楼层"};
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
                }
            }
        }

        // 户型验证
        if (StringUtils.isNotEmpty(addHouseMDTO.getApartmentLayout())) {
            String[] str = addHouseMDTO.getApartmentLayout().split(",");
            for (int i = 0; i < str.length; i++) {
                if (!pattern.matcher(str[i]).matches() && str.length!=3) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0033"));
                }
            }
        }
        //出租方式验证
        if (addHouseMDTO == null || (addHouseMDTO != null && ("0").equals(addHouseMDTO.getLeaseMode()))) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0034"));
        }
        //商圈验证
        if (StringUtils.isEmpty(addHouseMDTO.getDistrict())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0035"));
        }
        //建筑时间验证
        if (StringUtils.isEmpty(addHouseMDTO.getBuildTime())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0036"));
        }
        //房屋朝向
        if (StringUtils.isEmpty(addHouseMDTO.getOrientation())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0037"));
        }
        //装修验证
        if (StringUtils.isEmpty(addHouseMDTO.getDecor())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0038"));
        }
        //装修时间
        if (StringUtils.isEmpty(addHouseMDTO.getDecorTime())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0039"));
        }
        //地铁验证
//        if (StringUtils.isEmpty(addHouseMDTO.getStationId())) {
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0040"));
//        }
        //入住时间验证
        if (StringUtils.isEmpty(addHouseMDTO.getCheckinTime())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0042"));
        }
        //租金验证
        if (addHouseMDTO.getRent() == null ) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0043"));
        }
        //详细地址验证（房源标题）
        if (StringUtils.isEmpty(addHouseMDTO.getHouseAddress())){
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0041"));
        }
        //房屋面积验证
        if (StringUtils.isEmpty(addHouseMDTO.getBuildingArea())){
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0062"));
        }else {
            BigDecimal area = new BigDecimal(addHouseMDTO.getBuildingArea());
            if (area.compareTo(new BigDecimal("7")) == -1) {
                arguments = new Object[]{"7"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0063",arguments));
            }
        }

        //所在小区验证
        if (StringUtils.isEmpty(addHouseMDTO.getVillageName())){
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0119"));
        }
    }
}
