package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.ConStatusEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import com.dlfc.zfgj.service.web.ContractListService;
import com.dlfc.zfgj.service.web.UsrDelInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */

@Slf4j
@Transactional
@Service
public class ContractListServiceImpl implements ContractListService {

    private static final int DAYS_FOR_EXPIRED = 90;
    // 排序根据的时间类型:→1：创建时间
    private static final String TYPE_CREATE_TIME = "1";
    // 排序根据的时间类型:→2：生效时间
    private static final String TYPE_START_TIME = "2";
    // 排序根据的时间类型:→3：终止时间
    private static final String TYPE_END_TIME = "3";

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    @Autowired
    private SysPersonMapper sysPersonMapper;
    @Autowired
    private AgtEmpInfoService agtEmpInfoServiceImpl;
    @Autowired
    private SysOfficeServiceImpl sysOfficeServiceImpl;
    @Autowired
    private UsrDelInfoService usrDelInfoServiceImpl;

    /**
     * 检索个人合同
     *
     * @return
     */
    @Override
    public List<ConContract> getAllPersonalContracts(String timeType,
                                                     String orderBy,
                                                     UsrUser user) {
        AgtEmpInfo agtEmpInfo = agtEmpInfoServiceImpl.getAgtEmpInfoByUserId(user.getId());
        if (null == agtEmpInfo) {
            return new ArrayList<>();
        }
        ConContractExample conContractExample = new ConContractExample();
        ConContractExample.Criteria criteria = conContractExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andStatusNotEqualTo((short) ConStatusEnum.CANCEL_ENUM.getValue());
        criteria.andEidEqualTo(agtEmpInfo.getId());
        // 获取排序方式
        setOrderByIfNecessary(conContractExample, timeType, orderBy);
        // 过滤无效合同
        List<String> delIdList = usrDelInfoServiceImpl.getDelIdListByUser(user);
        if (null != delIdList && delIdList.size() > 0) {
            criteria.andIdNotIn(delIdList);
        }
        return conContractMapper.selectByExample(conContractExample);
    }

    /**
     * 检索部门合同
     *
     * @return
     */
    @Override
    public List<ConContract> getAllDepartmentalContracts(String timeType,
                                                         String orderBy,
                                                         UsrUser user) {
        // 查询经纪人所在的部门ID
        String officeId = sysOfficeServiceImpl.getOfficeIdByUser(user);
        List<ConContract> conContractList = new ArrayList<>();
        if (StringUtils.isNotEmpty(officeId)) {
            // 查询部门合同列表
            ConContractExample conContractExample = new ConContractExample();
            ConContractExample.Criteria conContractCriteria = conContractExample.createCriteria();
            conContractCriteria.andDeleteFlgEqualTo((short) 0);
            conContractCriteria.andStatusNotEqualTo((short) ConStatusEnum.CANCEL_ENUM.getValue());
            // 查询所属部门下所有经纪人ID
            List<String> empIdList = agtEmpInfoServiceImpl.getEmpIdListByOfficeId(officeId);
            if (null != empIdList && empIdList.size() > 0) {
                conContractCriteria.andEidIn(empIdList);
            }
            // 获取排序方式
            setOrderByIfNecessary(conContractExample, timeType, orderBy);
            // 过滤无效合同
            List<String> delIdList = usrDelInfoServiceImpl.getDelIdListByEmpIdList(empIdList);
            if (null != delIdList && delIdList.size() > 0) {
                conContractCriteria.andIdNotIn(delIdList);
            }
            conContractList = conContractMapper.selectByExample(conContractExample);
        }
        return conContractList;
    }

    /**
     * 根据条件检索个人合同列表
     *
     * @param searchInfo
     * @param user
     * @return
     */
    @Override
    public List<ConContract> getAllPersonalContractsBy(String searchInfo,
                                                       String timeType,
                                                       String orderBy,
                                                       UsrUser user) {
        return getListBySearchParam(searchInfo, getAllPersonalContracts(timeType, orderBy, user));
    }

    /**
     * 根据条件检索部门合同列表
     *
     * @param searchInfo
     * @param user
     * @return
     */
    @Override
    public List<ConContract> getAllDepartmentalContractsBy(String searchInfo,
                                                           String timeType,
                                                           String orderBy,
                                                           UsrUser user) {
        return getListBySearchParam(searchInfo, getAllDepartmentalContracts(timeType, orderBy, user));
    }

    /**
     * 检索个人有效合同
     *
     * @return
     */
    @Override
    public List<ConContract> getPersonalEffectiveContracts(String timeType,
                                                           String orderBy,
                                                           UsrUser user) {
        List<ConContract> conContractList = getAllPersonalContracts(timeType, orderBy, user);
        return getActiveContracts(conContractList);
    }

    /**
     * 检索部门有效合同
     *
     * @return
     */
    @Override
    public List<ConContract> getDepartmentalEffectiveContracts(String timeType,
                                                               String orderBy,
                                                               UsrUser user) {
        List<ConContract> conContractList = getAllDepartmentalContracts(timeType, orderBy, user);
        return getActiveContracts(conContractList);
    }

    /**
     * 检索即将到期的个人合同
     *
     * @return
     */
    @Override
    public List<ConContract> getPersonalNearingContracts(String timeType,
                                                         String orderBy,
                                                         UsrUser user) {
        List<ConContract> conContractList = getAllPersonalContracts(timeType, orderBy, user);
        return getNearingContracts(conContractList);
    }

    /**
     * 检索即将到期的部门合同
     *
     * @return
     */
    @Override
    public List<ConContract> getDepartmentalNearingContracts(String timeType,
                                                             String orderBy,
                                                             UsrUser user) {
        List<ConContract> conContractList = getAllDepartmentalContracts(timeType, orderBy, user);
        return getNearingContracts(conContractList);
    }

    /**
     * 模糊检索过滤
     *
     * @param searchParam
     * @param param
     * @return
     */
    private boolean checkSearchParam(String searchParam, String param) {
        return StringUtils.isNotEmpty(param) && param.contains(searchParam);
    }

    /**
     * 通过综合检索条件过滤检索结果
     *
     * @param searchParam
     * @param list
     * @return
     */
    private List<ConContract> getListBySearchParam(String searchParam, List<ConContract> list) {
        if (StringUtils.isNotEmpty(searchParam)) {
            List<ConContract> newList = new ArrayList<>();
            for (ConContract contract : list) {
                // 维护人
                String beid = contract.getBeid();
                boolean nameLikeFlg = false;
                AgtEmpInfo agtEmpInfo;
                SysPerson sysPerson;
                String sysPersonName = StringUtils.EMPTY;
                if (StringUtils.isNotEmpty(beid)) {
                    agtEmpInfo = agtEmpInfoMapper.selectByPrimaryKey(beid);
                    if (null != agtEmpInfo && StringUtils.isNotEmpty(agtEmpInfo.getId())) {
                        sysPerson = sysPersonMapper.selectByPrimaryKey(agtEmpInfo.getId());
                        if (null != sysPerson && StringUtils.isNotEmpty(sysPerson.getId())) {
                            sysPersonName = sysPerson.getName();
                        }
                    }
                    if (checkSearchParam(searchParam, sysPersonName)) {
                        nameLikeFlg = true;
                    }
                }
                if (nameLikeFlg
                        // 合同状态
                        || checkSearchParam(searchParam, ConStatusEnum.getName(contract.getStatus()))
                        // 房源地址
                        || checkSearchParam(searchParam, contract.getHouseAddr())) {
                    newList.add(contract);
                }
            }
            return newList;
        }
        return list;
    }

    /**
     * 取得生效合同
     *
     * @param conContracts
     */
    private List<ConContract> getActiveContracts(List<ConContract> conContracts) {
        List<ConContract> newContractList = new ArrayList<>();
        if (null != conContracts && conContracts.size() > 0) {
            for (ConContract conContract : conContracts) {
                if (ConStatusEnum.ACTIVE_ENUM.getValue() == conContract.getStatus()) {
                    newContractList.add(conContract);
                }
            }
        }
        return newContractList;
    }

    /**
     * 取得即将到期合同
     *
     * @param conContractList
     * @return
     */
    private List<ConContract> getNearingContracts(List<ConContract> conContractList) {
        List<ConContract> newContractList = new ArrayList<>();
        if (null != conContractList && conContractList.size() > 0) {
            Date systemDate = DateUtils.getSynchTime();
            Date endDate;
            for (ConContract conContract : conContractList) {
                endDate = conContract.getEndTime();
                if (null != endDate) {
                    if (DateUtils.getDaysBetweenDate(systemDate, endDate) <= DAYS_FOR_EXPIRED) {
                        newContractList.add(conContract);
                    }
                }
            }
        }
        return newContractList;
    }

    /**
     * orderBy相关操作
     *
     * @param conContractExample
     * @param timeType
     * @param orderBy
     */
    private void setOrderByIfNecessary(ConContractExample conContractExample,
                                       String timeType,
                                       String orderBy) {
        if (null != conContractExample
                && null != timeType
                && null != orderBy) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getTimeType(timeType));
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(getOrderBy(orderBy));
            conContractExample.setOrderByClause(stringBuffer.toString());
        }
    }

    /**
     * 获取时间类型
     *
     * @param timeType
     * @return
     */
    private String getTimeType(String timeType) {
        if (TYPE_CREATE_TIME.equals(timeType)) {
            return "CREATE_TIME";
        } else if (TYPE_START_TIME.equals(timeType)) {
            return "START_TIME";
        } else if (TYPE_END_TIME.equals(timeType)) {
            return "END_TIME";
        }
        return null;
    }

    /**
     * 获取排序类型
     *
     * @param orderBy
     * @return
     */
    private String getOrderBy(String orderBy) {
        if (String.valueOf(YesNoEnum.YES_ENUM.getValue()).equals(orderBy)) {
            return "ASC";
        } else if (String.valueOf(YesNoEnum.NO_ENUM.getValue()).equals(orderBy)) {
            return "DESC";
        }
        return null;
    }
}
