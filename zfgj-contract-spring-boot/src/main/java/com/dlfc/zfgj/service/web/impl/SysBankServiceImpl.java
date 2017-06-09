package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysBank;
import com.dlfc.zfgj.entity.SysBankExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysBankMapper;
import com.dlfc.zfgj.service.web.SysBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K on 2017/2/20.
 */

@Service
@Transactional
public class SysBankServiceImpl implements SysBankService {

    @Autowired
    private SysBankMapper sysBankMapper;

    /**
     * 获取银行信息列表
     *
     * @return
     */
    public List<SysBank> getList() {
        SysBankExample example = new SysBankExample();
        SysBankExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return sysBankMapper.selectByExample(example);
    }
}
