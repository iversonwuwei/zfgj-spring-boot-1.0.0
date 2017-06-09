package com.dlfc.zfgj.service.mobile.impl;


import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysCodeExample;
import com.dlfc.zfgj.mapper.SysCodeMapper;
import com.dlfc.zfgj.service.mobile.SyCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/20.
 */

@Service("sCodeMSerivice")
@Transactional
public class SysCodeMServiceImpl implements SyCodeMService {
    // 个人证件类型
    private static final String CODE_PER_ID_TYPE = "per_id_type";

    @Autowired
    private SysCodeMapper sysCodeMapper;

    /**
     * 通过code类型获取系统code列表
     *
     * @param type
     * @return
     */
    @Override
    public List<SysCode> getCodeByType(String type) {
        SysCodeExample sysCodeExample = new SysCodeExample();
        SysCodeExample.Criteria criteria = sysCodeExample.createCriteria();
        criteria.andTypeEqualTo(type);
        if (CODE_PER_ID_TYPE.equals(type)) {
            sysCodeExample.setOrderByClause("SORT DESC");
        } else {
            sysCodeExample.setOrderByClause("SORT");
        }
        List<SysCode> systemCodeList = sysCodeMapper.selectByExample(sysCodeExample);
        if (null == systemCodeList) {
            return new ArrayList<>();
        }
        return systemCodeList;
    }




}
