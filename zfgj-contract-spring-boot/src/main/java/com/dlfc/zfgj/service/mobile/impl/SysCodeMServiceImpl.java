package com.dlfc.zfgj.service.mobile.impl;


import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysCodeExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysCodeMapper;
import com.dlfc.zfgj.service.mobile.SysCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/20.
 */

@Service
@Transactional
public class SysCodeMServiceImpl implements SysCodeMService {
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

    /**
     * 根据code类型和code值取得code名
     *
     * @param code
     * @param type
     * @return
     */
    public String getNameByType(String code, String type) {
        SysCodeExample example = new SysCodeExample();
        SysCodeExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andTypeEqualTo(type);
        criteria.andCodeEqualTo(code);
        List<SysCode> sysCodeList = sysCodeMapper.selectByExample(example);
        if (null != sysCodeList && sysCodeList.size() == 1) {
            return sysCodeList.get(0).getName();
        }
        return null;
    }
}
