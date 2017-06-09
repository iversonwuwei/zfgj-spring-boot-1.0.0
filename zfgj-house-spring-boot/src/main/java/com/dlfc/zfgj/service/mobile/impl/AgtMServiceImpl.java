package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.mapper.SysOfficeMapper;
import com.dlfc.zfgj.service.mobile.AgtMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsz on 2017/3/6.
 */
@Transactional
@Service("aMServiceImpl")
public class AgtMServiceImpl implements AgtMService {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;
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


//    @Override
//    public AgtEmpInfo getComAll(String uid) {
//        AgtEmpInfoExample example = new AgtEmpInfoExample();
//        AgtEmpInfoExample.Criteria c = example.createCriteria();
//        c.andUserIdEqualTo(uid);
//        c.andStatusEqualTo(0);
//        c.andDeleteFlgEqualTo((short) 0);
//        // 公司对象列表
//        List<AgtEmpInfo> infoList = agtEmpInfoMapper.selectByExampleAll(example);
//        // 公司对象列表非空
//        if (infoList != null && infoList.size() > 0) {
//            return infoList.get(0);
//        }
//        return null;
//    }


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

//    /**
//     * 判断是否在职
//     *
//     * @param uid 用户ID
//     * @return true:在职，false:离职
//     */
//    public boolean isInCom(String uid) {
//
//        // 查询公司对象是否存在
//        if ((int) agtEmpInfoMapper.isInCom(uid) > 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

//    /**
//     * 根据经纪人id获取有效房源
//     *
//     * @param eid 经纪人ID
//     * @return 有效房源数量
//     */
//    public int actHouse(String eid) {
//        return agtEmpInfoMapper.actHouse(eid);
//    }

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
//     * 根据经纪人id获取等待审核房源
//     *
//     * @param eid 经纪人ID
//     * @return 等待审核房源数量
//     */
//    public int wHouse(String eid) {
//        return agtEmpInfoMapper.wHouse(eid);
//    }

//    /**
//     * 根据经纪人id获取审核未通过房源
//     *
//     * @param eid 经纪人ID
//     * @return 审核未通过房源数量
//     */
//    public int unAuHouse(String eid) {
//        return agtEmpInfoMapper.unAuHouse(eid);
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

//    /**
//     * 按照用户id查找 经纪人公司
//     *
//     * @param uid 用户申请公司记录对象ID
//     * @return 用户申请公司记录对象列表
//     */
//    public List<AgtUsrCompLogInfo> findAgtUsrCompLog(String uid) {
//        return agtUsrCompLogInfoMapper.findAgtUsrCompLog(uid);
//    }

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

//    /**
//     * 修改经纪人手机号码
//     *
//     * @param aei 经纪人信息对象
//     */
//    public void updatePhone(AgtEmpInfo aei) {
//        agtEmpInfoMapper.updatePhone(aei);
//    }

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
     * @param compId
     *            公司ID
     * @param officeId
     *            部门ID
     */
    public void insertCom(String compId, String officeId) {


        AgtUsrCompLogInfo aucli = new AgtUsrCompLogInfo();
        aucli.setCompanyId(compId);
        aucli.setUserId("b3236c95ae924aec975e8f60b8fba9d8");
        aucli.setStatus(0);
        aucli.setOfficeId(officeId);
        aucli.setPid("b39ab075141545668eee5dc12867193a");
        //aucli.preInsert();
        //以后需要删除
        aucli.setId(IdGen.uuid());
        aucli.setModifyUser("b3236c95ae924aec975e8f60b8fba9d8");
        aucli.setCreateUser("b3236c95ae924aec975e8f60b8fba9d8");
        /*aucli.setModifyUserIdentity("b3236c95ae924aec975e8f60b8fba9d8");
        aucli.setCreateUserIdentity("b3236c95ae924aec975e8f60b8fba9d8");*/
        aucli.setModifyTime(DateUtils.getSynchTime());
        aucli.setCreateTime(aucli.getModifyTime());
        aucli.setVersion(Integer.valueOf(0));
        aucli.setDeleteFlg((short)0);
        // 加入公司
        agtUsrCompLogInfoMapper.insertSelective(aucli);
    }

//    /**
//     * 经纪人加入公司
//     *
//     * @param comName 公司名字
//     */
//    @Override
//    public void joinComp(String comName) throws ApplicationException {
//        if (StringUtils.isBlank(comName)) {
//            //报错的Key未定义
//            throw new ApplicationException(PropertyUtils.getErrorMsg("请选择要加入的公司"));
//        }
//        //判断公司名存在与否
//        List<AgtCompInfo> list = this.isCom(comName);
//        // 公司信息空校验
//        if (!(list != null && list.size() != 0)) {
//            throw new ApplicationException();
//        }
//        //判断是否在职
//        if (this.isInCom("b3236c95ae924aec975e8f60b8fba9d8")) {
//            //报错的Key未定义
//            throw new ApplicationException(PropertyUtils.getErrorMsg("员工处于在职状态无法加入公司"));
//        }
//        this.insertCom(list.get(0).getId(), list.get(0).getOfficeId());
//    }

    /**
     * 取消申请加入公司
     *
     * @param id
     *            用户申请公司记录对象ID
     */
    public int reJoinComp(String id)  {
        AgtUsrCompLogInfo update = new AgtUsrCompLogInfo();
        update.setStatus(3);
        update.setId(id);
        // 更新用户申请公司记录对象信息
       return agtUsrCompLogInfoMapper.updateByPrimaryKeySelective(update);
    }

//    /**
//     * 根据uid查询经纪人的Eid
//     *
//     * @param userId
//     */
//    @Override
//    public String getEid(String userId) {
//        return agtEmpInfoMapper.getEid(userId);
//    }


    /**
     * 获取所属部门所有经纪人ID
     *
     * @return 经纪人ID列表
     */
    public List<String> getEmpIdListByOfficeId(String officeId) {
        AgtEmpInfoExample agtEmpInfoExample = new AgtEmpInfoExample();
        AgtEmpInfoExample.Criteria criteria = agtEmpInfoExample.createCriteria();
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria.andOfficeIdEqualTo(officeId);
        List<AgtEmpInfo> agtEmpInfoList = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        List<String> empIdList = new ArrayList<>();
        if (null != agtEmpInfoList && agtEmpInfoList.size() > 0) {
            for (AgtEmpInfo agtEmpInfo : agtEmpInfoList) {
                empIdList.add(agtEmpInfo.getId());
            }
        }
        return empIdList;
    }


    public String getOfficeIdByUser() {
//        String officeId = UserUtils.getUser().getOffice().getId();
        String officeId = "aa65f09a4a7e44b6aa90272205284896";//TODO 暂时没用过
        SysOfficeExample sysOfficeExample = new SysOfficeExample();
        SysOfficeExample.Criteria sysOfficeCriteria1 = sysOfficeExample.createCriteria();
        sysOfficeCriteria1.andIdEqualTo(officeId);
        SysOfficeExample.Criteria sysOfficeCriteria2 = sysOfficeExample.createCriteria();
        sysOfficeCriteria2.andParentIdsLike(officeId);
        SysOffice sysOffice = sysOfficeMapper.selectByPrimaryKey(officeId);
        String resultOfficeId = StringUtils.EMPTY;
        if (null != sysOffice && StringUtils.isNotEmpty(sysOffice.getId())) {
            resultOfficeId = sysOffice.getId();
        }
        return resultOfficeId;
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
        criteria.andStatusEqualTo(0);
        criteria.andDeleteFlgEqualTo((short) 0);
        List<AgtEmpInfo> agtEmpInfos = agtEmpInfoMapper.selectByExample(agtEmpInfoExample);
        AgtEmpInfo agtEmpInfo = new AgtEmpInfo();
        if (null != agtEmpInfos && agtEmpInfos.size() == 1) {
            agtEmpInfo = agtEmpInfos.get(0);
        }
        return agtEmpInfo;
    }
}
