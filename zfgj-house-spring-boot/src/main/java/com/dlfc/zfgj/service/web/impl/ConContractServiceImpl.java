package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.ConContractExample;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.web.ConContractService;
import com.dlfc.zfgj.service.web.UsrDelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/5/11.
 */

@Service("hwConContractService")
@Transactional
public class ConContractServiceImpl implements ConContractService {

    @Autowired
    @Qualifier("hwUsrDelInfoService")
    private UsrDelInfoService usrDelInfoService;
    @Autowired
    private ConContractMapper conContractMapper;

    /**
     * 根据经纪人id获取合同
     *
     * @param agtEmpInfo 经纪人信息对象
     * @return 合同数量
     */
    @Override
    public int contract(AgtEmpInfo agtEmpInfo) {
        ConContractExample example = new ConContractExample();
        ConContractExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andBeidEqualTo(agtEmpInfo.getId());
        List<String> delIdList = usrDelInfoService.getDelIdList(agtEmpInfo.getUserId());
        if (null != delIdList && delIdList.size() > 0) {
            criteria.andIdNotIn(delIdList);
        }
        return conContractMapper.countByExample(example);
    }

    /**
     * 根据经纪人id获取即将到期合同
     *
     * @param agtEmpInfo 经纪人信息对象
     * @return 即将到期合同数量
     */
    @Override
    public int eContract(AgtEmpInfo agtEmpInfo) {
        ConContractExample example = new ConContractExample();
        ConContractExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andBeidEqualTo(agtEmpInfo.getId());
        List<String> delIdList = usrDelInfoService.getDelIdList(agtEmpInfo.getUserId());
        if (null != delIdList && delIdList.size() > 0) {
            criteria.andIdNotIn(delIdList);
        }
        Date comingDate = DateUtils.addMonths(DateUtils.getSynchTime(), 1);
        criteria.andEndTimeLessThanOrEqualTo(comingDate);
        return conContractMapper.countByExample(example);
    }

}
