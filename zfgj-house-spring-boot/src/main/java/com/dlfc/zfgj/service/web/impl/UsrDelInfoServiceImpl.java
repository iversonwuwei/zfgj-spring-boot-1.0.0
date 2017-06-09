package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.UsrDelInfo;
import com.dlfc.zfgj.entity.UsrDelInfoExample;
import com.dlfc.zfgj.mapper.UsrDelInfoMapper;
import com.dlfc.zfgj.service.web.UsrDelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/5/11.
 */

@Service("hwUsrDelInfoService")
@Transactional
public class UsrDelInfoServiceImpl implements UsrDelInfoService {

    @Autowired
    private UsrDelInfoMapper usrDelInfoMapper;

    /**
     * @param uid
     * @return
     */
    @Override
    public List<String> getDelIdList(String uid) {
        UsrDelInfoExample example = new UsrDelInfoExample();
        UsrDelInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUepIdEqualTo(uid);
        List<UsrDelInfo> usrDelInfos = usrDelInfoMapper.selectByExample(example);
        List<String> delIdList = new ArrayList<>();
        for (UsrDelInfo usrDelInfo : usrDelInfos) {
            delIdList.add(usrDelInfo.getDelId());
        }
        return delIdList;
    }
}
