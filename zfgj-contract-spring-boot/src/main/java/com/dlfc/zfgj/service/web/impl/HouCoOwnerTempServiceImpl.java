package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.HouCoOwnerTemp;
import com.dlfc.zfgj.entity.HouCoOwnerTempExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.HouCoOwnerTempMapper;
import com.dlfc.zfgj.service.web.HouCoOwnerTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Service
@Transactional
public class HouCoOwnerTempServiceImpl implements HouCoOwnerTempService {

    @Autowired
    private HouCoOwnerTempMapper houCoOwnerMapper;

    @Override
    public List<HouCoOwnerTemp> getHouCoOwnersByHid(String hid) {
        HouCoOwnerTempExample houCoOwnerExample = new HouCoOwnerTempExample();
        List<HouCoOwnerTemp> houCoOwnerList = new ArrayList<>();
        if (StringUtils.isNotEmpty(hid)) {
            HouCoOwnerTempExample.Criteria criteria = houCoOwnerExample.createCriteria();
            criteria.andHidEqualTo(hid);
            criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            houCoOwnerList = houCoOwnerMapper.selectByExample(houCoOwnerExample);
        }
        return houCoOwnerList;
    }

    /**
     * 删除房源原来共有人
     *
     * @param hid
     */
    @Override
    public void deleteOldOwners(String hid) {
        HouCoOwnerTempExample houCoOwnerExample = new HouCoOwnerTempExample();
        HouCoOwnerTempExample.Criteria criteria = houCoOwnerExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andHidEqualTo(hid);
        HouCoOwnerTemp houCoOwnerTemp = new HouCoOwnerTemp();
        houCoOwnerTemp.setDeleteFlg((short) 1);
        houCoOwnerMapper.updateByExampleSelective(houCoOwnerTemp, houCoOwnerExample);
    }

    /**
     * 保存房源共有人
     *
     * @param houCoOwner
     */
    @Override
    public void saveHouseOwner(HouCoOwnerTemp houCoOwner) {
        houCoOwnerMapper.insertSelective(houCoOwner);
    }
}
