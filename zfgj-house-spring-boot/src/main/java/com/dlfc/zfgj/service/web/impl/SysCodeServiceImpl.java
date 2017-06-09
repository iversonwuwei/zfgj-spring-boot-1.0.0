package com.dlfc.zfgj.service.web.impl;


import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysCodeExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysCodeMapper;
import com.dlfc.zfgj.service.web.SyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/20.
 */

@Service("sCodeSerivice")
@Transactional
public class SysCodeServiceImpl implements SyCodeService {
    // 个人证件类型
    private static final String CODE_PER_ID_TYPE = "per_id_type";

    private List<SysCode> systemCodeList;

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
        SysCodeExample example = new SysCodeExample();
        SysCodeExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andTypeEqualTo(type);
        if (CODE_PER_ID_TYPE.equals(type)) {
            example.setOrderByClause("SORT DESC");
        } else {
            example.setOrderByClause("SORT");
        }
        systemCodeList = sysCodeMapper.selectByExample(example);
        if (null == systemCodeList) {
            return new ArrayList<>();
        }
        return systemCodeList;
    }


}
