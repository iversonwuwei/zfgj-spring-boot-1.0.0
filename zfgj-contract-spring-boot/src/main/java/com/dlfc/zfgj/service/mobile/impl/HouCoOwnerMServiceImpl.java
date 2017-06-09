package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.HouCoOwner;
import com.dlfc.zfgj.entity.HouCoOwnerExample;
import com.dlfc.zfgj.mapper.HouCoOwnerMapper;
import com.dlfc.zfgj.service.mobile.HouCoOwnerMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Service
@Transactional
public class HouCoOwnerMServiceImpl implements HouCoOwnerMService {

    @Autowired
    private HouCoOwnerMapper houCoOwnerMapper;

    @Override
    public List<HouCoOwner> getHouCoOwnersByHid(String hid) {
        List<HouCoOwner> houCoOwnerList = new ArrayList<>();
        if (StringUtils.isEmpty(hid)) {
            HouCoOwnerExample houCoOwnerExample = new HouCoOwnerExample();
            HouCoOwnerExample.Criteria criteria = houCoOwnerExample.createCriteria();
            criteria.andHidEqualTo(hid);
            houCoOwnerList = houCoOwnerMapper.selectByExample(houCoOwnerExample);
        }
        return houCoOwnerList;
    }
}
