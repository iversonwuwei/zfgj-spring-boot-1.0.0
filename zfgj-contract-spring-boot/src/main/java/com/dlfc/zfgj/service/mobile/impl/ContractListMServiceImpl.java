package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.ConStatusEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.mobile.ContractListMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.actors.threadpool.Arrays;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */

@Slf4j
@Transactional
@Service
public class ContractListMServiceImpl implements ContractListMService {

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;

    /**
     * 检索个人合同
     *
     * @return
     */
    @Override
    public List<ConContract> getAllPersonalContracts(Integer beginPos,
                                                     Integer count,
                                                     UsrUser user,
                                                     Integer... conStatus) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andIdEqualTo(user.getEmpId());
        AgtEmpInfo agtEmpInfo = agtEmpInfoMapper.selectByExample(agtEmpInfoExample).get(0);
        if(agtEmpInfo.getStatus() == 1){
            return null;
        }else{
            ConContractExample example = new ConContractExample();
            ConContractExample.Criteria criteriaCon = example.createCriteria();
            criteriaCon.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            criteriaCon.andEidEqualTo(user.getEmpId());
            criteriaCon.andStatusIn(Arrays.asList(conStatus));
            String orderBy = "MODIFY_TIME DESC";
            String limit = StringUtils.EMPTY;
            if (null != beginPos && null != count) {
                limit = StringUtils.SPACE + "LIMIT" + StringUtils.SPACE + beginPos + "," + count;
            }
            example.setOrderByClause(orderBy + limit);
            return conContractMapper.selectByExample(example);
        }
    }

    /**
     * /**
     * 检索个人合同数
     *
     * @return
     */
    @Override
    public List<ConContract> getPersonalContractsCount(UsrUser user ,Integer... conStatus) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andIdEqualTo(user.getEmpId());
        AgtEmpInfo agtEmpInfo = agtEmpInfoMapper.selectByExample(agtEmpInfoExample).get(0);
        if(agtEmpInfo.getStatus() == 1){
            return null;
        }else{
            ConContractExample example = new ConContractExample();
            ConContractExample.Criteria criteriaCon = example.createCriteria();
            criteriaCon.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            if ( conStatus.length > 1){
                criteriaCon.andStatusIn(Arrays.asList(conStatus));
            }
            criteriaCon.andEidEqualTo(user.getEmpId());
            return conContractMapper.selectByExample(example);
        }
    }
}
