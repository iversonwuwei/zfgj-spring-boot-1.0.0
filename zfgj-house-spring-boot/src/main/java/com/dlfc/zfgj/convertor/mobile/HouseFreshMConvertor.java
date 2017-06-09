package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.HouseFreshMDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/5.
 */
@Component("houseFreshMConvertor")
public class HouseFreshMConvertor extends AbstractConvertor<HouLeaseInfo , HouseFreshMDTO> {
    @Override
    public HouLeaseInfo toModel(HouseFreshMDTO houseFreshMDTO) {
        return null;
    }

    @Override
    public HouseFreshMDTO toDTO(HouLeaseInfo model, boolean forListView) {
        HouseFreshMDTO houseFreshMDTO = new HouseFreshMDTO();
        if (null != model){
            houseFreshMDTO.setFreshTime(DateUtils.dateToStr(model.getFreshTime(), DateUtils.MODIFIED_PATERN));
            houseFreshMDTO.setReleaseTime( DateUtils.dateToStr(model.getReleaseTime(), DateUtils.MODIFIED_PATERN));
        }
        return houseFreshMDTO;
    }
}
