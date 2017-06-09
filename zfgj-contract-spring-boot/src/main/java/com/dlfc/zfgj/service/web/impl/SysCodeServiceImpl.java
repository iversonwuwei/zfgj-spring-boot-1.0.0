package com.dlfc.zfgj.service.web.impl;


import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysCodeExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysCodeMapper;
import com.dlfc.zfgj.service.web.SysCodeService;
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
public class SysCodeServiceImpl implements SysCodeService {
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
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
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
    public String getNameByType(String code,
                                String type) {
        SysCodeExample sysCodeExample = new SysCodeExample();
        SysCodeExample.Criteria criteria = sysCodeExample.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andCodeEqualTo(code);
        List<SysCode> sysCodeList = sysCodeMapper.selectByExample(sysCodeExample);
        if (null != sysCodeList && sysCodeList.size() == 1) {
            return sysCodeList.get(0).getName();
        }
        return null;
    }

    /**
     * 过滤旧数据
     *
     * @param array
     * @param sysCodeList
     * @return
     */
    @Override
    public String[] checkOldSysCodes(String[] array,
                                     List<SysCode> sysCodeList) {
        List<String> codeList = new ArrayList<>();
        if (null != sysCodeList && sysCodeList.size() > 0) {
            for (SysCode sysCode : sysCodeList) {
                codeList.add(sysCode.getCode());
            }
        }
        List<String> newCodeList = new ArrayList<>();
        if (null != array && array.length > 0) {
            for (String str : array) {
                if (codeList.contains(str)) {
                    newCodeList.add(str);
                }
            }
        }
        if (null != newCodeList) {
            return newCodeList.toArray(new String[newCodeList.size()]);
        }
        return null;
    }
}
