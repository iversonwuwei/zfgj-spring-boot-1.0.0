package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.SystemPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsz on 2017/3/6.
 */
@Transactional
@Service("aServiceImpl")
public class AgtServiceImpl implements AgtService {

    private AgtEmpInfo agtEmpInfo;
    private List<AgtEmpInfo> agtEmpInfoList;

    /**
     * 经纪人信息列表Mapper
     */
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    /**
     * 用户申请公司Mapper
     */
    @Autowired
    private AgtUsrCompLogInfoMapper agtUsrCompLogInfoMapper;
    /**
     * 经纪公司信息Mapper
     */
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;

    @Autowired
    private SystemPersonService systemPersonService;


    @Override
    public AgtEmpInfo getComAll(String uid) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        example.setOrderByClause("CREATE_TIME DESC");
        agtEmpInfoList = agtEmpInfoMapper.selectByExample(example);
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            return agtEmpInfoList.get(0);
        }
        agtEmpInfo = new AgtEmpInfo();
        agtEmpInfo.setUserId(uid);
        return agtEmpInfo;
    }


//    /**
//     * 查找经纪人电话
//     *
//     * @param id
//     * @return
//     */
//    public String getPhone(String id) {
//        // 获取经纪人对象
//        AgtEmpInfo aei = agtEmpInfoMapper.getPhone(id);
//        // 经纪人对象非空
//        if (aei != null) {
//            return aei.getPhone();
//        } else {
//            return "";
//        }
//    }

    @Override
    public AgtEmpInfo findById(String id) {
        return agtEmpInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 判断是否在职
     *
     * @param uid 用户ID
     * @return true:在职，false:离职
     */
    public boolean isInCom(String uid) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        criteria.andUserIdEqualTo(uid);
        // 查询公司对象是否存在
        if (agtEmpInfoMapper.countByExample(example) > 0) {
            return true;
        } else {
            return false;
        }

    }

//    /**
//     * 根据经纪人id获取签合同房源
//     *
//     * @param eid 经纪人ID
//     * @return 签合同房源数量
//     */
//    public int conHouse(String eid) {
//        return agtEmpInfoMapper.conHouse(eid);
//    }

//    /**
//     * 根据经纪人id获取等待审核出租信息
//     *
//     * @param eid 经纪人ID
//     * @return 等待审核出租信息数量
//     */
//    public int wHouse(String eid) {
//        HouLeaseInfoExample example = new HouLeaseInfoExample();
//        HouLeaseInfoExample.Criteria criteria = example.createCriteria();
//        criteria.andEidEqualTo(eid);
//        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
//        criteria.andAuditStatusEqualTo(AuditStatusEnum.UNAUDITED_ENUM.getValue());
//        return houLeaseInfoMapper.countByExample(example);
//    }
//
//    /**
//     * 根据经纪人id获取审核未通过出租信息
//     *
//     * @param eid 经纪人ID
//     * @return 审核未通过出租信息数量
//     */
//    public int unAuHouse(String eid) {
//        HouLeaseInfoExample houLeaseInfoExample = new HouLeaseInfoExample();
//        HouLeaseInfoExample.Criteria criteria = houLeaseInfoExample.createCriteria();
//        criteria.andEidEqualTo(eid);
//        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
//        criteria.andAuditStatusEqualTo(AuditStatusEnum.REFUSE_ENUM.getValue());
//        return houLeaseInfoMapper.countByExample(houLeaseInfoExample);
//    }

//    /**
//     * 根据经纪人id获取客源
//     *
//     * @param eid 经纪人信息ID
//     * @return 客源数量
//     */
//    public int cus(String eid) {
//        return agtEmpInfoMapper.cus(eid);
//    }

//    /**
//     * 根据经纪人id获取合同
//     *
//     * @param aei 经纪人信息对象
//     * @return 合同数量
//     */
//    public int contract(AgtEmpInfo aei) {
//        return agtEmpInfoMapper.contract(aei);
//    }

//    /**
//     * 根据经纪人id获取即将到期合同
//     *
//     * @param aei 经纪人信息对象
//     * @return 即将到期合同数量
//     */
//    public int eContract(AgtEmpInfo aei) {
//        return agtEmpInfoMapper.eContract(aei);
//    }

    /**
     * 按照用户id查找 经纪人公司
     *
     * @param uid 用户申请公司记录对象ID
     * @return 用户申请公司记录对象列表
     */
    public AgtUsrCompLogInfo findAgtUsrCompLog(String uid) {
        AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
        AgtUsrCompLogInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andUserIdEqualTo(uid);
        example.setOrderByClause("PINDEX DESC");
        List<AgtUsrCompLogInfo> agtUsrCompLogInfoList = agtUsrCompLogInfoMapper.selectByExample(example);
        if (null != agtUsrCompLogInfoList && agtUsrCompLogInfoList.size() > 0) {
            return agtUsrCompLogInfoList.get(0);
        }
        return null;
    }

    /**
     * 查询公司信息通过公司id
     *
     * @param id 公司ID
     * @return 查询成功失败状态
     */
    public AgtCompInfo getCompInfoById(String id) {
        return agtCompInfoMapper.selectByPrimaryKey(id);
    }

//    /**
//     * 查询所有公司信息
//     *
//     * @return 公司信息对象列表
//     */
//    public List<AgtCompInfo> findList() {
//        return agtCompInfoMapper.findList();
//    }

    /**
     * 修改经纪人手机号码
     *
     * @param aei 经纪人信息对象
     */
    public void updatePhone(AgtEmpInfo aei) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        criteria.andUserIdEqualTo(aei.getUserId());
        agtEmpInfoMapper.updateByExampleSelective(aei, example);
    }

    /**
     * 判断公司名存在与否
     *
     * @param com 公司名称
     * @return 公司信息对象列表
     */
    private List<AgtCompInfo> isCom(String com) {
        AgtCompInfoExample example = new AgtCompInfoExample();
        AgtCompInfoExample.Criteria c = example.createCriteria();
        c.andFullNameEqualTo(com);
        return agtCompInfoMapper.selectByExample(example);
    }

    /**
     * 写入操作--加入公司
     *
     * @param compId   公司ID
     * @param officeId 部门ID
     * @param usrUser
     */
    public void insertCom(String compId, String officeId, UsrUser usrUser) {


        AgtUsrCompLogInfo aucli = new AgtUsrCompLogInfo();
        aucli.setCompanyId(compId);
        aucli.setUserId(usrUser.getId());
        aucli.setStatus(0);
        aucli.setOfficeId(officeId);
        SysPerson sysPerson = systemPersonService.getSysPersonById(usrUser.getPerId());
        if (null != sysPerson) {
            aucli.setPid(sysPerson.getId());
        }
        aucli.preInsert(usrUser);
        // 加入公司
        agtUsrCompLogInfoMapper.insertSelective(aucli);
    }

    /**
     * 经纪人加入公司
     *
     * @param comName 公司名字
     */
    @Override
    public void joinComp(String comName, UsrUser usrUser) throws ApplicationException {
        if (StringUtils.isBlank(comName)) {
            //报错的Key未定义
            throw new ApplicationException("请选择要加入的公司");
        }
        //判断公司名存在与否
        List<AgtCompInfo> list = this.isCom(comName);
        // 公司信息空校验
        if (list == null || list.size() == 0) {
            throw new ApplicationException("请正确选择公司");
        }
        //判断是否在职
        if (this.isInCom(usrUser.getId())) {
            //报错的Key未定义
            throw new ApplicationException("员工处于在职状态无法加入公司");
        }
        this.insertCom(list.get(0).getId(), list.get(0).getOfficeId(), usrUser);
    }

    /**
     * 取消申请加入公司
     *
     * @param id 用户申请公司记录对象ID
     */
    public int reJoinComp(String id) {
        AgtUsrCompLogInfo update = new AgtUsrCompLogInfo();
        update.setStatus(3);
        update.setId(id);
        // 更新用户申请公司记录对象信息
        return agtUsrCompLogInfoMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 根据uid查询经纪人的OfficeId
     *
     * @param userId
     */
    @Override
    public String getOfficeId(String userId) {
        AgtEmpInfoExample example = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andUserIdEqualTo(userId);
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(example);
        if (agtEmpInfoList != null && agtEmpInfoList.size() > 0) {
            return agtEmpInfoList.get(0).getOfficeId();
        }
        return null;
    }


    /**
     * 获取所属部门所有经纪人UID
     *
     * @return 经纪人UID列表
     */
    public List<String> getEmpIdListByOfficeId(String officeId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        criteria.andOfficeIdEqualTo(officeId);
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        List<String> empIdList = new ArrayList<>();
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            for (AgtEmpInfo agtEmpInfo : agtEmpInfoList) {
                empIdList.add(agtEmpInfo.getUserId());
            }
        }
        return empIdList;
    }

    /**
     * 根据登录用户查询经纪人信息
     *
     * @param userId
     * @return
     */
    @Override
    public AgtEmpInfo getAgtEmpInfoByUserId(String userId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
        agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        if (null != agtEmpInfoList && agtEmpInfoList.size() == 1) {
            return agtEmpInfoList.get(0);
        }
        return null;
    }

    @Override
    public void save(AgtEmpInfo agtEmpInfo) {
        agtEmpInfoMapper.updateByPrimaryKey(agtEmpInfo);
    }

    @Override
    public List<AgtCompInfo> getCompInfos() {
        AgtCompInfoExample example = new AgtCompInfoExample();
        AgtCompInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return agtCompInfoMapper.selectByExample(example);
    }
}
