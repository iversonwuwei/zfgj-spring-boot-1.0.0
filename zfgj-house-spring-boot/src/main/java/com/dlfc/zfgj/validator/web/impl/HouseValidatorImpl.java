package com.dlfc.zfgj.validator.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.web.HouseLeaseDTO;
import com.dlfc.zfgj.validator.web.HouseValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/8.
 */
@Component
public class HouseValidatorImpl implements HouseValidator {
    @Override
    public void validate(String phone, String code) throws ApplicationException {
        String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        Pattern pattern = Pattern.compile(regEx);
        // 手机号空校验
        if (StringUtils.isBlank(phone)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0120"));
        }/*else{
            if (!pattern.matcher(phone).matches()) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0122"));
            }
        }*/
        // 验证码空校验
        if (StringUtils.isBlank(code)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0121"));
        }
    }

    @Override
    public void checkHouse(HouseLeaseDTO HouseLeaseDTO) throws ApplicationException {

       /* Pattern pattern = Pattern.compile("[-3-9]{1,10}");
        // 楼层验证
        if (StringUtils.isNotEmpty(addHouseLeaseDTO.getFloor())) {
            String[] str = addHouseLeaseDTO.getFloor().split(",");
            for (int i = 0; i < str.length; i++) {
                if (!pattern.matcher(str[i].toString()).matches()) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0032"));
                }
            }
        }*/
        // 户型验证
//        if (StringUtils.isNotEmpty(addHouseLeaseDTO.getApartmentLayout())) {
//            String[] str = addHouseLeaseDTO.getApartmentLayout().split(",");
//            for (int i = 0; i < str.length; i++) {
//                if (!pattern.matcher(str[i]).matches()) {
//                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0033"));
//                }
//            }
//        }
        //出租方式验证
        if (HouseLeaseDTO == null || (HouseLeaseDTO != null && ("0").equals(HouseLeaseDTO.getLeaseMode()))) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0034"));
        }
        //商圈验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getDistrict())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0035"));
        }
//        //建筑时间验证
//        if (StringUtils.isEmpty(addHouseLeaseDTO.getBuildTime())) {
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0036"));
//        }
        //房屋朝向
        if (StringUtils.isEmpty(HouseLeaseDTO.getOrientation())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0037"));
        }
        //装修验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getDecor())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0038"));
        }
        //装修时间
        if (StringUtils.isEmpty(HouseLeaseDTO.getDecorTime())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0039"));
        }
        /*//地铁验证
        if (StringUtils.isEmpty(addHouseLeaseDTO.getStationid())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0040"));
        }*/
        //标题验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getTitle())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0041"));
        }
        //租金验证
        if (HouseLeaseDTO.getRent() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0043"));
        }
        Object[] arguments;
//        // 入住时间验证
//        if (null == addHouseLeaseDTO.getCheckinTime()) {
//            arguments = new Object[]{"入住时间"};
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
//        }
        // 楼层验证
//        if (StringUtils.isEmpty(HouseLeaseDTO.getFloor())) {
            if (null==HouseLeaseDTO.getFloor()) {
            arguments = new Object[]{"楼层"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 总楼层数验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getOnFloor())) {
            arguments = new Object[]{"总楼数层"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 户型（室）验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getApartmentLayoutRoom())) {
            arguments = new Object[]{"户型（室）"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 户型（厅）验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getApartmentLayoutHall())) {
            arguments = new Object[]{"户型（厅）"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 户型（卫）验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getApartmentLayoutToilet())) {
            arguments = new Object[]{"户型（卫）"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 户型（面积）验证
        if (StringUtils.isEmpty(HouseLeaseDTO.getAcreage())) {
            arguments = new Object[]{"户型（面积）"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0131", arguments));
        }
        // 出租面积要大于等于7平米
        double acreage = Double.valueOf(HouseLeaseDTO.getAcreage());
        if (acreage < 7) {
            arguments = new Object[]{"7"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0063", arguments));
        }
    }
}
