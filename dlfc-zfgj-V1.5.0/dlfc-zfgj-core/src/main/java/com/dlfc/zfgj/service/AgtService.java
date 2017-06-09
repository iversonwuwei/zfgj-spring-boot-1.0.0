/**
* @name: AgtService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 经纪人相关操作,查询公司详情，经纪人登记审核等
*
* @version: 1.0
* @date : 2015年11月10日 
* @author: daiym 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月10日       daiym        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtCompInfoExample;
import com.dlfc.zfgj.entity.AgtCusInfo;
import com.dlfc.zfgj.entity.AgtCusInfoExample;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.entity.AgtEmpInfoExample.Criteria;
import com.dlfc.zfgj.entity.AgtEmpInfoList;
import com.dlfc.zfgj.entity.AgtEmpInfoLog;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfoExample;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouHouseInfoExample;
import com.dlfc.zfgj.entity.SysUserRole;
import com.dlfc.zfgj.entity.SysUserRoleExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoLogMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.AgtExamineHouInfoMapper;
import com.dlfc.zfgj.mapper.AgtUsrCompLogInfoMapper;
import com.dlfc.zfgj.mapper.HouEntrustInfoMapper;
import com.dlfc.zfgj.mapper.SysUserRoleMapper;
import com.housecenter.dlfc.zfgj.common.enums.AgtGradeEnum;
import com.housecenter.dlfc.zfgj.common.enums.AgtOptEventEnum;
import com.housecenter.dlfc.zfgj.common.enums.MsgTypeEnum;

/**
 * @name: AgtService
 * @description:经纪人相关操作,查询公司详情，经纪人登记审核等
 * 
 * @version 1.0
 * @author daiym
 *
 */
@Service("agtService")
public class AgtService extends BaseService {
	/** 经纪人信息列表Mapper */
	@Autowired
	private AgtEmpInfoMapper agtEmpInfoMapper;
	/** 经纪人操作记录Mapper */
	@Autowired
	private AgtEmpInfoLogMapper agtEmpInfoLogMapper;
	/** 经纪公司信息 */
	@Autowired
	private AgtCompInfoMapper agtCompInfoMapper;
	/** 用户申请公司Mapper */
	@Autowired
	private AgtUsrCompLogInfoMapper agtUsrCompLogInfoMapper;
	/** 角色信息Mapper */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	/** 消息Service */
	@Autowired
	private MessageService messageService;
	/** 短信操作Service */
	@Autowired
	private SMSService smsService;
	/** 用户信息Service */
	@Autowired
	private UsrUserService usrUserService;
	/** 经纪人预约验房信息Mapper */
	@Autowired
	private AgtExamineHouInfoMapper agtExamineHouInfoMapper;
	/** 房屋委托信息Mapper */
	@Autowired
	private HouEntrustInfoMapper houEntrustInfoMapper;
	/** 房屋信息Mapper */
	@Autowired
	private HouHouseInfoService houHouseInfoService;
	/** 客户信息Service */
	@Autowired
	private AgtCusInfoService agtCusInfoService;
	/** 入职成功 */
	private static String MSG_JOIN_COMPANY_AGREE_TITLE = PropertyUtils.getMessage("msg.join.company.agree.title");

	/**
	 * 查询经纪人列表
	 * 
	 * @param page
	 *            经纪人分页对象
	 * @param empInfo
	 *            经纪人对象
	 * @return 经纪人分页对象
	 */
	public BasePage<AgtEmpInfoList> find(BasePage<AgtEmpInfoList> page, AgtEmpInfoList empInfo) {
		empInfo.setPage(page);
		page.setList(agtEmpInfoMapper.selectEmpInfoList(empInfo));
		return page;
	}

	/**
	 * 查找与离职人员同一公司经纪人列表
	 * 
	 * @param page
	 *            经纪人分页对象
	 * @param empInfo
	 *            经纪人信息对象
	 * @return 经纪人分页对象
	 * @author HAN.JIAQI 2016/07/22
	 */
	public BasePage<AgtEmpInfoList> findTransfer(BasePage<AgtEmpInfoList> page, AgtEmpInfoList empInfo) {
		empInfo.setPage(page);
		page.setList(agtEmpInfoMapper.selectTransferList(empInfo));
		return page;
	}

	/**
	 * 移交离职人员房源或客源
	 * 
	 * @param eid
	 *            将要移交的经纪人ID
	 * @param oldEid
	 *            离职经纪人ID
	 * @param type
	 *            类型（0:房源1:客源）
	 */
	public void updateHouseOrCus(String eid, String oldEid, Integer type) {
		switch (type) {
		case 0:
			// 更新内容
			HouHouseInfo houseInfo = new HouHouseInfo();
			houseInfo.setBeid(eid);
			// 更新条件
			HouHouseInfoExample houExample = new HouHouseInfoExample();
			HouHouseInfoExample.Criteria cri = houExample.or();
			cri.andBeidEqualTo(oldEid);

			houHouseInfoService.updateHouse(houseInfo, houExample);
			break;
		case 1:
			AgtCusInfo agtCusInfo = new AgtCusInfo();
			agtCusInfo.setEid(eid);

			AgtCusInfoExample agtExample = new AgtCusInfoExample();
			AgtCusInfoExample.Criteria agtCri = agtExample.or();
			agtCri.andEidEqualTo(oldEid);

			agtCusInfoService.updateStatus(agtCusInfo, agtExample);
			break;
		}

	}

	/**
	 * 查询详情
	 * 
	 * @param id
	 *            经纪人ID
	 * @return 经纪人对象
	 */
	public AgtEmpInfo getAgtEmpInfo(String id) {

		AgtEmpInfoExample example = new AgtEmpInfoExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(id);
		// 查询经纪人对象列表
		List<AgtEmpInfo> infoList = agtEmpInfoMapper.selectByExampleAll(example);
		// 经纪人对象列表非空
		if (infoList != null && infoList.size() > 0) {
			return infoList.get(0);
		}

		return null;
	}

	/**
	 * 更新操作
	 * 
	 * @param empInfo
	 *            经纪人对象
	 * @param event
	 *            经纪人操作事件类型枚举
	 * @return 操作成功失败状态
	 */
	public int updateAgtEmpInfo(AgtEmpInfo empInfo, AgtOptEventEnum event) {
		// 获取经纪人对象
		AgtEmpInfo preEmpInfoRes = agtEmpInfoMapper.selectByPrimaryKey(empInfo.getId());

		// 设置更新版本
		empInfo.setVersion(preEmpInfoRes.getVersion());

		int res = agtEmpInfoMapper.updateByPrimaryKeySelective(empInfo);

		// 更新sys_user_role表 删除所有对应关系
		sysUserRoleMapper.deleteByUserId(preEmpInfoRes.getUserId());
		// 插入对应关系
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(preEmpInfoRes.getUserId());
		if (empInfo.getRoleCode() != null && empInfo.getRoleCode() != "") {
			sysUserRole.setRoleId(empInfo.getRoleCode());
		} else {
			// roleCode空的时候是准经纪人roleCode
			sysUserRole.setRoleId("f73686b7e74d4a81997f15c09ca80b9d");
		}
		sysUserRoleMapper.insertSelective(sysUserRole);
		// 更新了一条
		if (res == 1) {

			AgtEmpInfo empInfoRes = agtEmpInfoMapper.selectByPrimaryKey(empInfo.getId());

			AgtEmpInfoLog agtEmpInfoLog = new AgtEmpInfoLog();
			agtEmpInfoLog.preUpdate();
			agtEmpInfoLog.setId(UuidUtils.get32UUID());
			agtEmpInfoLog.setAvatarId(empInfoRes.getAvatarId());
			agtEmpInfoLog.setCompanyId(empInfoRes.getCompanyId());
			agtEmpInfoLog.setDcrp(event.getName());
			agtEmpInfoLog.setOfficeId(empInfoRes.getOfficeId());
			agtEmpInfoLog.setOperator(empInfoRes.getUserId());
			agtEmpInfoLog.setOptEvent(event.getValue());
			agtEmpInfoLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
			agtEmpInfoLog.setStatusTime(empInfoRes.getStatusTime());
			agtEmpInfoLog.setRoleCode(empInfoRes.getRoleCode());
			agtEmpInfoLog.setStatus(empInfoRes.getStatus().shortValue());
			agtEmpInfoLog.setUserId(empInfoRes.getUserId());
			agtEmpInfoLog.setPid(empInfoRes.getPid());

			agtEmpInfoLogMapper.insertSelective(agtEmpInfoLog);

		} else {
			return -1;
		}

		return res;
	}

	/**
	 * 有无合同
	 * 
	 * @param userId
	 *            用户ID
	 * @return true:有合同，false:无合同
	 */
	public boolean hasDoingCon(String userId) {
		int res = agtEmpInfoMapper.hasDoingCon(userId);
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有公司信息
	 * 
	 * @return 公司信息对象列表
	 */
	@Transactional(readOnly = false)
	public List<AgtCompInfo> findList() {
		return agtCompInfoMapper.findList();
	}

	/**
	 * 判断公司名存在与否
	 * 
	 * @param com
	 *            公司名称
	 * @return 公司信息对象列表
	 */
	public List<AgtCompInfo> isCom(String com) {
		AgtCompInfoExample example = new AgtCompInfoExample();
		com.dlfc.zfgj.entity.AgtCompInfoExample.Criteria c = example.createCriteria();
		c.andFullNameEqualTo(com);
		return agtCompInfoMapper.selectByExample(example);
	}

	/**
	 * 判断是否在职
	 * 
	 * @param uid
	 *            用户ID
	 * @return true:在职，false:离职
	 */
	public boolean isInCom(String uid) {
		// 查询公司对象是否存在
		if ((int) agtEmpInfoMapper.isInCom(uid) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询公司详情
	 * 
	 * @param uid
	 *            用户ID
	 * @return 公司信息对象
	 */
	public AgtEmpInfo getComAll(String uid) {
		AgtEmpInfoExample example = new AgtEmpInfoExample();
		Criteria c = example.createCriteria();
		c.andUserIdEqualTo(uid);
		c.andStatusEqualTo(0);
		c.andDeleteFlgEqualTo((short) 0);
		// 公司对象列表
		List<AgtEmpInfo> infoList = agtEmpInfoMapper.selectByExampleAll(example);
		// 公司对象列表非空
		if (infoList != null && infoList.size() > 0) {
			return infoList.get(0);
		}
		return null;
	}

	/**
	 * 写入操作--加入公司
	 * 
	 * @param comId
	 *            公司ID
	 * @param officeId
	 *            部门ID
	 */
	public void insertCom(String compId, String officeId) {
		User userInfo = UserUtils.getUser();
		AgtUsrCompLogInfo aucli = new AgtUsrCompLogInfo();
		aucli.setCompanyId(compId);
		aucli.setUserId(userInfo.getId());
		aucli.setStatus(0);
		aucli.setOfficeId(officeId);
		aucli.setPid(userInfo.getPerId());
		aucli.preInsert();
		// 加入公司
		agtUsrCompLogInfoMapper.insertSelective(aucli);
	}

	/**
	 * 经纪人登记操作
	 * 
	 * @param AgtEmpInfo
	 *            经纪人信息对象
	 * @param event
	 *            经纪人操作事件类型枚举
	 * @return 更新成功失败状态
	 */
	@Transactional(readOnly = false)
	public int vUpdate(AgtEmpInfo empInfo, AgtOptEventEnum event) {
		// 获取经纪人对象
		AgtEmpInfo preEmpInfoRes = agtEmpInfoMapper.selectByPrimaryKey(empInfo.getId());

		// 设置更新版本
		empInfo.setVersion(preEmpInfoRes.getVersion() + 1);
		// 更新经纪人
		int res = agtEmpInfoMapper.updateByPrimaryKeySelective(empInfo);
		// 更新经纪人成功
		if (res == 1) {

			AgtEmpInfo empInfoRes = agtEmpInfoMapper.selectByPrimaryKey(empInfo.getId());

			AgtEmpInfoLog agtEmpInfoLog = new AgtEmpInfoLog();
			agtEmpInfoLog.preInsert();
			agtEmpInfoLog.setId(UuidUtils.get32UUID());
			agtEmpInfoLog.setAvatarId(empInfoRes.getAvatarId());
			agtEmpInfoLog.setCompanyId(empInfoRes.getCompanyId());
			agtEmpInfoLog.setDcrp(event.getName());
			agtEmpInfoLog.setOfficeId(empInfoRes.getOfficeId());
			agtEmpInfoLog.setOperator(empInfoRes.getUserId());
			agtEmpInfoLog.setOptEvent(event.getValue());
			agtEmpInfoLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
			agtEmpInfoLog.setRoleCode(empInfoRes.getRoleCode());
			agtEmpInfoLog.setStatus(empInfoRes.getStatus().shortValue());
			agtEmpInfoLog.setUserId(empInfoRes.getUserId());
			agtEmpInfoLog.setPid(empInfoRes.getPid());

			agtEmpInfoLogMapper.insertSelective(agtEmpInfoLog);

		} else {
			return -1;
		}

		return res;
	}

	/**
	 * 用户申请公司查询
	 * 
	 * @param example
	 *            用户申请公司记录查询对象
	 * @return 用户申请公司记录对象列表
	 */
	public List<AgtUsrCompLogInfo> getAgtUsrCompLogInfo(AgtUsrCompLogInfoExample example) {
		// 获取用户申请公司记录对象列表
		List<AgtUsrCompLogInfo> infoList = agtUsrCompLogInfoMapper.selectByExampleAll(example);
		return infoList;
	}

	/**
	 * 经纪人审核列表
	 * 
	 * @param page
	 *            用户申请公司记录分页对象
	 * @param agtUsrCompLogInfo
	 *            用户申请公司记录对象
	 * @return 用户申请公司记录分页对象
	 */
	public BasePage<AgtUsrCompLogInfo> find(BasePage<AgtUsrCompLogInfo> page, AgtUsrCompLogInfo agtUsrCompLogInfo) {
		agtUsrCompLogInfo.setPage(page);
		page.setList(agtUsrCompLogInfoMapper.selectAgtUsrCompLogList(agtUsrCompLogInfo));
		return page;
	}

	/**
	 * 
	 * @param aei
	 *            经纪人信息对象
	 * @param aucli
	 *            用户申请公司记录对象
	 * @param isId
	 *            是否入职过此公司
	 * @return 插入成功失败状态
	 */
	@Transactional(readOnly = false)
	public int InserAgtEmpInfo(AgtEmpInfo aei, AgtUsrCompLogInfo aucli, String isId) {

		String eid = "";
		aei.setStatus(0);
		aei.setStatusTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		eid = aei.getUUID();
		aei.preInsert();
		aei.setId(eid);
		aei.setGrade(AgtGradeEnum.AGT_THREE_STARS_ENUM.getValue());
		// 插入经纪人
		agtEmpInfoMapper.insertSelective(aei);

		aucli.setEid(eid);
		aucli.setStatus(1);

		AgtEmpInfoLog agtEmpInfoLog = new AgtEmpInfoLog();
		agtEmpInfoLog.preInsert();
		agtEmpInfoLog.setId(agtEmpInfoLog.getUUID());
		agtEmpInfoLog.setCompanyId(aei.getCompanyId());
		agtEmpInfoLog.setOperator(aei.getUserId());
		agtEmpInfoLog.setOptEvent(1);
		agtEmpInfoLog.setDcrp("申请加入公司");
		agtEmpInfoLog.setDeleteFlg((short) 0);
		agtEmpInfoLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		// 更新用户申请公司记录
		agtUsrCompLogInfoMapper.updateByPrimaryKeySelective(aucli);

		SysUserRole sur = new SysUserRole();
		sur.setUserId(aei.getUserId());
		sur.setRoleId(aei.getRoleCode());
		SysUserRoleExample example = new SysUserRoleExample();
		com.dlfc.zfgj.entity.SysUserRoleExample.Criteria c = example.createCriteria();
		c.andUserIdEqualTo(aei.getUserId());
		// 更新角色信息
		sysUserRoleMapper.updateByExampleSelective(sur, example);
		UsrUser u = usrUserService.findById(aei.getUserId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("company", agtCompInfoMapper.selectByPrimaryKey(aei.getCompanyId()).getFullName());
		// 发送短信
		smsService.sendSMSByTemplate(u.getMobile(), "Z0015", map);
		// 站内信
		try {
			Object[] arguments = { map.get("company").toString() };
			String receiver = eid;
			String content = PropertyUtils.getMessage("msg.join.company.agree.content", arguments);
			messageService.sendMessage(UserUtils.getUser().getId(), UserUtils.getUser().getName(), receiver,
					MSG_JOIN_COMPANY_AGREE_TITLE, content, (short) MsgTypeEnum.ACC_ENUM.getValue());
		} catch (Exception e) {
			logger.error("AgtService InserAgtEmpInfo", e);
		}
		return agtEmpInfoLogMapper.insertSelective(agtEmpInfoLog);
	}

	/**
	 * 用户申请公司表更新
	 * 
	 * @param aucli
	 *            用户申请公司记录对象
	 * @param aei
	 *            经纪人信息对象
	 * @return 更新成功失败状态
	 */
	@Transactional(readOnly = false)
	public int updateAgtUser(AgtUsrCompLogInfo aucli, AgtEmpInfo aei) {

		UsrUser u = usrUserService.findById(aei.getUserId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("company", agtCompInfoMapper.selectByPrimaryKey(aei.getCompanyId()).getFullName());
		// 发送短信
		smsService.sendSMSByTemplate(u.getMobile(), "Z0016", map);
		// 站内信
		try {
			String receiver = aei.getUserId();
			Object[] arguments = { map.get("company").toString() };
			String content = PropertyUtils.getMessage("msg.join.company.refuse.content", arguments);
			messageService.sendMessage(UserUtils.getUser().getId(), UserUtils.getUser().getName(), receiver,
					PropertyUtils.getMessage("msg.join.company.refuse.title"), content,
					(short) MsgTypeEnum.ACC_ENUM.getValue());

		} catch (Exception e) {
			logger.error("AgtService updateAgtUser", e);
		}
		return agtUsrCompLogInfoMapper.updateByPrimaryKeySelective(aucli);
	}

	/**
	 * 按照用户id查找 经纪人公司
	 * 
	 * @param uid
	 *            用户申请公司记录对象ID
	 * @return 用户申请公司记录对象列表
	 */
	public List<AgtUsrCompLogInfo> findAgtUsrCompLog(String uid) {
		return agtUsrCompLogInfoMapper.findAgtUsrCompLog(uid);
	}

	/**
	 * 查询是否入职过此公司
	 * 
	 * @param agtEmpInfo
	 *            经纪人信息
	 * @return 经纪人ID
	 */
	public String getIsEmp(AgtEmpInfo agtEmpInfo) {

		AgtEmpInfoExample example = new AgtEmpInfoExample();
		Criteria c = example.createCriteria();
		c.andUserIdEqualTo(agtEmpInfo.getUserId());
		c.andPidEqualTo(agtEmpInfo.getPid());
		c.andCompanyIdEqualTo(agtEmpInfo.getCompanyId());
		// 获取经纪人对象列表
		List<AgtEmpInfo> infoList = agtEmpInfoMapper.selectByExample(example);
		// 经纪人对象列表非空
		if (infoList != null && infoList.size() > 0) {
			return infoList.get(0).getId();
		}
		return null;
	}

	/**
	 * 取消申请加入公司
	 * 
	 * @param id
	 *            用户申请公司记录对象ID
	 */
	public void reJoinComp(String id) {
		AgtUsrCompLogInfo update = new AgtUsrCompLogInfo();
		update.setStatus(3);
		update.setId(id);
		// 更新用户申请公司记录对象信息
		agtUsrCompLogInfoMapper.updateByPrimaryKeySelective(update);
	}

	/**
	 * 根据经纪人id获取有效房源
	 * 
	 * @param eid
	 *            经纪人ID
	 * @return 有效房源数量
	 */
	public int actHouse(String eid) {
		return agtEmpInfoMapper.actHouse(eid);
	}

	/**
	 * 根据经纪人id获取签合同房源
	 * 
	 * @param eid
	 *            经纪人ID
	 * @return 签合同房源数量
	 */
	public int conHouse(String eid) {
		return agtEmpInfoMapper.conHouse(eid);
	}

	/**
	 * 根据经纪人id获取等待审核房源
	 * 
	 * @param eid
	 *            经纪人ID
	 * @return 等待审核房源数量
	 */
	public int wHouse(String eid) {
		return agtEmpInfoMapper.wHouse(eid);
	}

	/**
	 * 根据经纪人id获取审核未通过房源
	 * 
	 * @param eid
	 *            经纪人ID
	 * @return 审核未通过房源数量
	 */
	public int unAuHouse(String eid) {
		return agtEmpInfoMapper.unAuHouse(eid);
	}

	/**
	 * 根据经纪人id获取客源
	 * 
	 * @param eid
	 *            经纪人信息ID
	 * @return 客源数量
	 */
	public int cus(String eid) {
		return agtEmpInfoMapper.cus(eid);
	}

	/**
	 * 根据经纪人id获取合同
	 * 
	 * @param aei
	 *            经纪人信息对象
	 * @return 合同数量
	 */
	public int contract(AgtEmpInfo aei) {
		return agtEmpInfoMapper.contract(aei);
	}

	/**
	 * 根据经纪人id获取即将到期合同
	 * 
	 * @param aei
	 *            经纪人信息对象
	 * @return 即将到期合同数量
	 */
	public int eContract(AgtEmpInfo aei) {
		return agtEmpInfoMapper.eContract(aei);
	}

	/**
	 * 
	 * @param officeId
	 *            部门ID
	 * @return 公司ID
	 */
	public String selectCompIdByOfficeId(String officeId) {
		return agtEmpInfoMapper.selectCompIdByOfficeId(officeId);
	}

	/**
	 * 查询经纪人委托发布记录数
	 * 
	 * @param id
	 *            房源委托ID
	 * @return 经纪人委托发布记录数
	 */
	public int selectEntrustCount(String id) {
		return houEntrustInfoMapper.selectEntrustInfoObjectCount(id);
	}

	/**
	 * 查询经纪人验房预约记录数
	 * 
	 * @param id
	 *            经纪人验房预约ID
	 * @return 经纪人验房预约记录数
	 */
	public int selectExamineCount(String id) {
		return agtExamineHouInfoMapper.selectExamineeCount(id);
	}

	/**
	 * 查询经纪人手机号码
	 * 
	 * @param id
	 *            经纪人ID
	 * @return 手机号码
	 */
	public String getPhone(String id) {
		// 获取经纪人对象
		AgtEmpInfo aei = agtEmpInfoMapper.getPhone(id);
		// 经纪人对象非空
		if (aei != null) {
			return aei.getPhone();
		} else {
			return "";
		}
	}

	/**
	 * 查询经纪人手机号码是否存在
	 * 
	 * @param phone
	 *            电话
	 * @return true:存在，false:不存在
	 */
	public boolean isPhoneBinded(String phone) {
		// 查询手机号数量
		int count = agtEmpInfoMapper.isPhoneBinded(phone);
		// 手机号数量大于零
		if (count > 0) {
			return true;// 存在
		}
		return false;
	}

	/**
	 * 修改经纪人手机号码
	 * 
	 * @param aei 经纪人信息对象
	 */
	public void updatePhone(AgtEmpInfo aei) {
		agtEmpInfoMapper.updatePhone(aei);
	}

}
