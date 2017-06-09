package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.zfgj.entity.UsrDelInfo;
import com.dlfc.zfgj.entity.UsrDelInfoExample;
import com.dlfc.zfgj.mapper.UsrDelInfoMapper;
import com.dlfc.zfgj.service.mobile.UsrDelInfoMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/3/10.
 */

@Service
@Transactional
public class UsrDelInfoMServiceImpl implements UsrDelInfoMService {

    @Autowired
    private UsrDelInfoMapper usrDelInfoMapper;

    /**
     * 通过User查询无效合同ID列表
     *
     * @return
     */
    @Override
    public List<String> getDelIdListByUser() {
        UsrDelInfoExample usrDelInfoExample = new UsrDelInfoExample();
        UsrDelInfoExample.Criteria criteria = usrDelInfoExample.createCriteria();
//TODO        criteria.andUepIdEqualTo(UserUtils.getUser().getId());
        criteria.andUepIdEqualTo("12");//TODO
        List<UsrDelInfo> usrDelInfoList = usrDelInfoMapper.selectByExample(usrDelInfoExample);
        return getStringsFromList(usrDelInfoList);
    }

    /**
     * 通过经纪人列表查询所属的无效合同ID列表
     *
     * @return
     */
    @Override
    public List<String> getDelIdListByEmpIdList(List<String> emlIdList) {
        UsrDelInfoExample usrDelInfoExample = new UsrDelInfoExample();
        UsrDelInfoExample.Criteria criteria = usrDelInfoExample.createCriteria();
        criteria.andUepIdIn(emlIdList);
        List<UsrDelInfo> usrDelInfoList = usrDelInfoMapper.selectByExample(usrDelInfoExample);
        return getStringsFromList(usrDelInfoList);
    }

    private List<String> getStringsFromList(List<UsrDelInfo> list) {
        List<String> strings = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (UsrDelInfo usrDelInfo : list) {
                strings.add(usrDelInfo.getDelId());
            }
        }
        return strings;
    }
}
