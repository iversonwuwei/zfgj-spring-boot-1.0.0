/**
* @name: ConContractService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月9日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月9日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.ConversionUtils;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.admin.common.utils.OrderUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.service.SystemService;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConContractExample;
import com.dlfc.zfgj.entity.ConContractExample.Criteria;
import com.dlfc.zfgj.entity.ConContractLog;
import com.dlfc.zfgj.entity.ConHouseItems;
import com.dlfc.zfgj.entity.ConHouseItemsExample;
import com.dlfc.zfgj.entity.ConHouseItemsLog;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.ConHouseUserExample;
import com.dlfc.zfgj.entity.ConHouseUserLog;
import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.ConOtherCostsExample;
import com.dlfc.zfgj.entity.DateTimeBean;
import com.dlfc.zfgj.entity.HouHouseBaseinfo;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysPaychannelBanks;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysPersonExample;
import com.dlfc.zfgj.entity.UsrAccount;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserBankcard;
import com.dlfc.zfgj.entity.UsrUserBankcardExample;
import com.dlfc.zfgj.mapper.ConContractLogMapper;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.mapper.ConHouseItemsLogMapper;
import com.dlfc.zfgj.mapper.ConHouseItemsMapper;
import com.dlfc.zfgj.mapper.ConHouseUserLogMapper;
import com.dlfc.zfgj.mapper.ConHouseUserMapper;
import com.dlfc.zfgj.mapper.ConOtherCostsMapper;
import com.dlfc.zfgj.mapper.HouHouseBaseinfoMapper;
import com.dlfc.zfgj.mapper.HouHouseInfoMapper;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.dlfc.zfgj.mapper.SysPaychannelBanksMapper;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.mapper.UsrAccountMapper;
import com.dlfc.zfgj.mapper.UsrUserBankcardMapper;
import com.housecenter.dlfc.zfgj.common.enums.ConOptEventEnum;
import com.housecenter.dlfc.zfgj.common.enums.ConSourceEnum;
import com.housecenter.dlfc.zfgj.common.enums.ConStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.RentalModeEnum;
import com.housecenter.dlfc.zfgj.common.enums.StructureEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;

import tk.mybatis.mapper.util.StringUtil;

/**
 * @name: ConContractService
 * @description:
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
@Service
public class ConContractService extends BaseService {
	
	public static final int TYPE_STEP1 = 1;
	public static final int TYPE_STEP2 = 2;
	public static final int TYPE_STEP3 = 3;
	public static final int TYPE_STEP4 = 4;
	public static final int TYPE_STEP5 = 5;

	@Autowired
	private UsrUserService userService;
	@Autowired
	private HouHouseInfoMapper houDao;
	@Autowired
	private HouHouseBaseinfoMapper houHouseBaseinfoMapper;
	@Autowired
	private ConContractMapper conDao;
	@Autowired
	private ConContractLogMapper conLogDao;
	@Autowired
	private ConHouseUserMapper houUserDao;
	@Autowired
	private ConHouseUserLogMapper houUserLogDao;
	@Autowired
	private ConHouseItemsMapper houItemsDao;
	@Autowired
	private ConHouseItemsLogMapper houItemsLogDao;
	@Autowired
	private SysPersonMapper personDao;
	@Autowired
	private SysInfoAttMapper infoAttDao;
	@Autowired
	private UsrUserBankcardMapper userBankcardDao;
	@Autowired
	private SysPersonService sysPersonService;
	@Autowired
	private AllinpayService allinpayService;
	@Autowired
	private UsrAccountMapper UsrAccountnDao;
	@Autowired
	private ConOtherCostsMapper conOtherCostsMapper;
	@Autowired
	private SMSService smsService;
	@Autowired
	private  SysPaychannelBanksMapper sysPaychannelBanksMapper;
	@Autowired
	private  SysParamService paramService;
	
	

	public ConContract getById(String cid)  {
		return conDao.findById(cid);
	}

	/**
	 * 分页
	 * 
	 * @throws Exception
	 */
	public BasePage<ConContract> selectPage(BasePage<ConContract> page, ConContract con) throws Exception {
		con.setPage(page);
		List<ConContract> list = conDao.findByList(con);
		page.setList(list);
		return page;

	}

	/**
	 * 关联解除合同表的关联查询
	 */
	public ConContract findDissById(String cid) throws Exception {
		return conDao.findDissById(cid);
	}

	/**
	 * 查询全部合同
	 * 
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ConContract getByNo(String no) throws Exception {
		ConContractExample emp = new ConContractExample();
		Criteria cri = emp.createCriteria();
		cri.andDeleteFlgEqualTo((short) 0);
		cri.andNoEqualTo(no);
		List<ConContract> list = conDao.selectByExample(emp);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存合同信息
	 * 
	 * @param con
	 * @param type
	 * @throws Exception
	 */
	public void save(ConContract con, int type) throws Exception {
		if (type == TYPE_STEP2) {
			HouHouseInfo houseInfo = houDao.selectByPrimaryKey(con.getHid());
			if (houseInfo.getDeleteFlg() == YesNoEnum.YES_ENUM.getValue()) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0006"));
			}
			con.setHouseAddr(houseInfo.getHouseAddr());
			con.setPropertyType(houseInfo.getPropertyIdType());
			con.setBuildingArea(houseInfo.getBuildingArea());
			//判断是否是租金分期
			if (con.getStages() == null) {
				con.setStages("00");
			}
			// 如果是整租,出租面积是房屋建筑面积乘以0.7
			if (con.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue()) {
				con.setLeaseArea(new BigDecimal((int) (houseInfo.getBuildingArea().doubleValue())));
				con.setLeaseDomain("整租");
			}
			SysParam sysParam = new SysParam();
			sysParam.setScope("INSURANCE_FLAG");
			sysParam.setSkey("ON-OFF");
			boolean b =paramService.isOn(sysParam);
			if(b){
				//判断是否可以赠送保险
				if(con.getRentalMode() == RentalModeEnum.WHOLE_RENT_ENUM.getValue() && con.getLeaseTermYear() >=1 && 
						(houseInfo.getStructure() == null || houseInfo.getStructure() == StructureEnum.ZHUAN_HUN_ENUM.getValue() || 
								houseInfo.getStructure() ==StructureEnum.KUANG_JIYA_ENUM.getValue())){
					con.setHasInsurance(1);
				}else{
					con.setHasInsurance(0);
				}
			}
			
		}
		String cid = con.getId();
		String dcrp = getDcrp(type);
		if (type == TYPE_STEP3) {
			if (con.getPaymentMethod() == null) {
				con.setPaymentMethod((short) 0);
			}
		}
		String conLogId = updateCon(con, ConOptEventEnum.MODIFY_ENUM.getValue(), dcrp, type);
		if (type == TYPE_STEP2) {
			deleteHouseUser(cid);
			saveHouseUser(con.getHouUserList(), cid, conLogId);
		} else if (type == TYPE_STEP4) {
			deleteHouseItems(cid);
			saveHouseItems(con.getHouItemsList(), cid, conLogId);
			deleteOtherItems(cid);
			saveOtherItems(con.getConOtherCostsList(), cid);
		}
	}
	
	/**
	 * 非事物处理合同保存(出租方)
	 * 
	 * @param con
	 * @param type
	 * @throws Exception
	 */
	public void saveLessor(ConContract con) throws Exception {
		/*UsrUserBankcard lessorBankcard = con.getUserBankcardList().get(0);
		if(lessorBankcard.getBankNum().contains("******")){
			String perId = sysPersonService.selectIdByCard(con.getLessorName(),con.getLessorIdNo());
			if (StringUtil.isNotEmpty(perId)) {
				UsrUser user = userService.getUserByPerId(perId);
				if (user != null) {
					List<UsrUserBankcard> list = user.getUserBankcardList();
					if (list != null && list.size() > 0) {
						lessorBankcard.setBankNum(list.get(0).getBankNum());
					}else{
						lessorBankcard.setBankNum(null);
					}
				}else{
					lessorBankcard.setBankNum(null);
				}
			}else{
				lessorBankcard.setBankNum(null);
			}
		}
		if(com.dlfc.common.utils.StringUtils.isBlank(lessorBankcard.getBankNum())){
			throw new ApplicationException(PropertyUtil.getErrorMsg("SYS-0094"));
		}*/
		//在身份验证时数据已经插入库，如果查询还是空就是数据有问题
		String lessorPid = sysPersonService.selectIdByCard(con.getLessorName(),con.getLessorIdNo());
		if (StringUtil.isNotEmpty(lessorPid)){
		con.setLessorPid(lessorPid);
		}else{
			throw new ApplicationException("出租方" + PropertyUtils.getErrorMsg("SYS-0009"));
		}
	}
	
	/**
	 * 非事物处理合同保存(承租方)
	 * 
	 * @param con
	 * @param type
	 * @throws Exception
	 */
	public void saveLessee(ConContract con) throws Exception {
		/*UsrUserBankcard lesseeBankcard = con.getUserBankcardList().get(1);
		if(lesseeBankcard.getBankNum().contains("******")){
			String perId = sysPersonService.selectIdByCard(con.getLesseeName(),con.getLesseeIdNo());
			if (StringUtil.isNotEmpty(perId)) {
				UsrUser user = userService.getUserByPerId(perId);
				if (user != null) {
					List<UsrUserBankcard> list = user.getUserBankcardList();
					if (list != null && list.size() > 0) {
						lesseeBankcard.setBankNum(list.get(0).getBankNum());
					}else{
						lesseeBankcard.setBankNum(null);
					}
				}else{
					lesseeBankcard.setBankNum(null);
				}
			}else{
				lesseeBankcard.setBankNum(null);
			}
		}
		if(com.dlfc.common.utils.StringUtils.isBlank(lesseeBankcard.getBankNum())){
			throw new ApplicationException(PropertyUtil.getErrorMsg("SYS-0095"));
		}*/
		//在身份验证时数据已经插入库，如果查询还是空就是数据有问题
		String lesseePid = sysPersonService.selectIdByCard(con.getLesseeName(),con.getLesseeIdNo());
		if (StringUtil.isNotEmpty(lesseePid)){
			con.setLesseePid(lesseePid);
			}else{
				throw new ApplicationException("承租方" + PropertyUtils.getErrorMsg("SYS-0009"));
			}
	}

	/**
	 * 非事物处理合同保存(第一步的其他信息)
	 * 
	 * @param con
	 * @param type
	 * @throws Exception
	 */
	public void saveElse(ConContract con) throws Exception {
		
		// 添加
		if (con.getIsNewRecord()) {
			con.setLessorIdType(1);
			con.setLesseeIdType(1);
			con.setRenewalFlg((short) YesNoEnum.NO_ENUM.getValue());
			con.preInsert();
			con.setNo(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_CONTRACT));
			con.setStatus((short) ConStatusEnum.CREATING_ENUM.getValue());
			con.setSource(ConSourceEnum.ZFGJ_ENUM.getValue());
			conDao.insertSelective(con);
			saveLog(con.getId(), (short) 0, ConOptEventEnum.CREATE_ENUM.getValue(), "新建合同step1");
		} else {
			//修改
			updateCon(con, ConOptEventEnum.MODIFY_ENUM.getValue(), "新建合同step1", 1);
		}
	}
	
	/**
	 * 修改合同
	 * 
	 * @param con
	 * @param event
	 * @param derp
	 * @return
	 * @throws Exception
	 */
	public String updateCon(ConContract con, int event, String derp, int type) throws Exception {
		String cid = con.getId();
		/*
		 * 校验version
		 */
		ConContract conDB = checkVersion(cid, con.getVersion());
		con.preUpdate();
		if (type == TYPE_STEP3) {
			if (con.getOwnerBear() == null) {
				con.setOwnerBear("");
			}
			if (con.getPaymentMethod() == null) {
				con.setPaymentMethod((short) 0);
			}
		}else if(type == TYPE_STEP4){
			if(con.getLesseeIdsArray() == null || con.getLesseeIdsArray().length == 0){
				con.setLesseeIds("");
			}
			if(con.getLessorIdsArray() == null || con.getLessorIdsArray().length == 0){
				con.setLessorIds("");
			}
		}
		con.setVersion(con.getVersion() + 1);
		conDao.updateByPrimaryKeySelective(con);
		return saveLog(cid, conDB.getStatus(), event, derp);
	}

	/**
	 * 逻辑删除合同
	 * 
	 * @param cid
	 * @throws Exception
	 */
	public void deleteCon(String cid) throws Exception {
		ConContract conDB = conDao.selectByPrimaryKey(cid);
		if (conDB.getStatus().intValue() == ConStatusEnum.CREATING_ENUM.getValue()) {
			ConContract con = new ConContract();
			con.setId(cid);
			con.setVersion(conDB.getVersion() + 1);
			con.setStatus((short) ConStatusEnum.CANCEL_ENUM.getValue());
			con.preUpdate();
			conDao.updateByPrimaryKeySelective(con);
			saveLog(cid, conDB.getStatus(), ConOptEventEnum.CANCEL_ENUM.getValue(), "取消合同");
		} else {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0007"));
		}
	}

	/**
	 * 添加或修改续约合同
	 * 
	 * @param con
	 * @param type
	 * @throws Exception
	 */
	public void saveForRenewal(ConContract con) throws Exception {
		// 添加续约合同
		if (con.getIsNewRecord()) {
			// 校验是否已经存在续约合同
			checkRenewalExist(con.getRenewalCid());
			ConContract conDB = conDao.selectByPrimaryKey(con.getRenewalCid());
			String cidDB = conDB.getId();
			short oldStatus = conDB.getStatus();
			Calendar cal = Calendar.getInstance();
			cal.setTime(conDB.getEndTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 续约开始时间为之前的合同结束时间+1天
			conDB.setStartTime(cal.getTime());
			int totalMonth = con.getLeaseTermYear() * 12 + con.getLeaseTermMonth();
			Calendar newEndCal = (Calendar) cal.clone();
			newEndCal.add(Calendar.MONTH, totalMonth);
			newEndCal.add(Calendar.DAY_OF_MONTH, -1);
			// 续约结束时间为续约开始时间+总月数
			conDB.setEndTime(newEndCal.getTime());
			conDB.setRenewalFlg((short) YesNoEnum.NO_ENUM.getValue());
			conDB.setRenewalCid(con.getRenewalCid());
			conDB.setLeaseTermYear(con.getLeaseTermYear());
			conDB.setLeaseTermMonth(con.getLeaseTermMonth());
			conDB.setStatus((short) ConStatusEnum.WAIT_UPLOAD_ENUM.getValue());
			conDB.setNo("C" + DateUtils.dateToStr(com.dlfc.admin.common.utils.DateUtils.getSynchTime(), DateUtils.DATE_MS_PATERN));
			conDB.setMonthlyRent(con.getMonthlyRent());
			conDB.setOwnerBear(con.getOwnerBear());
			conDB.setOwnerBearOther(con.getOwnerBearOther());
			conDB.setSendTime(null);
			conDB.setApprovalTime(null);
			conDB.setCancelTime(null);
			conDB.setApproveRejectTime(null);
			conDB.setConfirmTime(null);
			conDB.setDissAgrTime(null);
			conDB.setPindex(null);
			conDB.preInsert();
			conDao.insertSelective(conDB);
			String cid = conDB.getId();
			String conLogId = saveLog(cid, (short) 0, ConOptEventEnum.CREATE_ENUM.getValue(), "新建续约合同");
			saveHouseUser(conDB.getHouUserList(), cid, conLogId);
			saveHouseItems(conDB.getHouItemsList(), cid, conLogId);
			// 将来源合同renewalFlg设置为1（已有续约的合同）
			ConContract parentCon = new ConContract();
			parentCon.setId(cidDB);
			parentCon.setRenewalFlg((short) YesNoEnum.YES_ENUM.getValue());
			parentCon.preUpdate();
			conDao.updateByPrimaryKeySelective(parentCon);
			saveLog(cidDB, oldStatus, ConOptEventEnum.MODIFY_ENUM.getValue(), "修改合同续约标识");
			// 修改续约合同
		} else {
			String cid = con.getId();
			ConContract conDB = checkVersion(cid, con.getVersion());
			int totalMonth = con.getLeaseTermYear() * 12 + con.getLeaseTermMonth();
			Calendar cal = Calendar.getInstance();
			cal.setTime(conDB.getStartTime());
			cal.add(Calendar.MONTH, totalMonth);
			// 续约结束时间为续约开始时间+总月数
			con.setEndTime(cal.getTime());
			con.setVersion(con.getVersion() + 1);
			conDao.updateByPrimaryKeySelective(con);
			saveLog(cid, conDB.getStatus(), ConOptEventEnum.MODIFY_ENUM.getValue(), "修改续约合同");
		}
	}

	/**
	 * 处理代理协议附件
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void saveProxyAtt(ConContract con) throws Exception {
		String cid = con.getId();
		/*
		 * 删除已有的合同附件
		 */
		SysInfoAttExample example = new SysInfoAttExample();
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri = example.createCriteria();
		cri.andLidEqualTo(cid);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri.andFileTypeEqualTo(InfoAttFileTypeEnum.PROXY_AGR_ENUM.getValue());
		SysInfoAtt infoAtt = new SysInfoAtt();
		infoAtt.setVersion(1);
		infoAtt.preUpdate();
		infoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		infoAttDao.updateByExampleSelective(infoAtt, example);
		if (StringUtil.isNotEmpty(con.getProxyName())) {
			/*
			 * 添加代理协议附件
			 */
			SysInfoAtt proxyInfoAtt = con.getInfoAttList().get(0);
			proxyInfoAtt.setLid(cid);
			proxyInfoAtt.setFileType(InfoAttFileTypeEnum.PROXY_AGR_ENUM.getValue());
			proxyInfoAtt.preInsert();
			infoAttDao.insertSelective(proxyInfoAtt);
			/*
			 * 移动图片
			 */
			String tempDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
			String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
			String proxyInfoAttFilePath = realDir + proxyInfoAtt.getFilePath();
			File file = new File(proxyInfoAttFilePath);
			if (!file.exists()) {
				copyFile(tempDir + proxyInfoAtt.getFilePath(), proxyInfoAttFilePath);
			}
		}
	}

	/**
	 * 处理合同附件
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void saveConAtt(ConContract con) throws Exception {
		String cid = con.getId();
		ConContract conDB = conDao.selectByPrimaryKey(cid);
		/*
		 * 删除已有的合同附件
		 */
		SysInfoAttExample example = new SysInfoAttExample();
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri = example.createCriteria();
		cri.andLidEqualTo(cid);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri.andFileTypeEqualTo(InfoAttFileTypeEnum.ONE_SIDE_ENUM.getValue());
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri2 = example.or();
		cri2.andLidEqualTo(cid);
		cri2.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri2.andFileTypeEqualTo(InfoAttFileTypeEnum.OTHER_SIDE_ENUM.getValue());
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri3 = example.or();
		cri3.andLidEqualTo(cid);
		cri3.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri3.andFileTypeEqualTo(InfoAttFileTypeEnum.SECURITY_BOOK_ENUM.getValue());
		if ("01".equals(conDB.getStages())) {
			com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri4 = example.or();
			cri4.andLidEqualTo(cid);
			cri4.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
			cri4.andFileTypeEqualTo(InfoAttFileTypeEnum.STAGES_LESSOR_ENUM.getValue());
			com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri5 = example.or();
			cri5.andLidEqualTo(cid);
			cri5.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
			cri5.andFileTypeEqualTo(InfoAttFileTypeEnum.STAGES_LESSEE_ENUM.getValue());
		}
		SysInfoAtt infoAtt = new SysInfoAtt();
		infoAtt.setVersion(1);
		infoAtt.preUpdate();
		infoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		infoAttDao.updateByExampleSelective(infoAtt, example);

		/*
		 * 添加合同正面图片
		 */
		SysInfoAtt oneSide = con.getInfoAttList().get(0);
		oneSide.setLid(cid);
		oneSide.setFileType(InfoAttFileTypeEnum.ONE_SIDE_ENUM.getValue());
		oneSide.preInsert();
		infoAttDao.insertSelective(oneSide);
		/*
		 * 添加合同背面图片
		 */
		SysInfoAtt otherSide = con.getInfoAttList().get(2);
		otherSide.setLid(cid);
		otherSide.setFileType(InfoAttFileTypeEnum.OTHER_SIDE_ENUM.getValue());
		otherSide.preInsert();
		infoAttDao.insertSelective(otherSide);
		/*
		 * 将合同状态置为等待审核
		 */
		con.setStatus((short) ConStatusEnum.APPROVING_ENUM.getValue());
		updateCon(con, ConOptEventEnum.SUBMIT_ENUM.getValue(), "上传图片，提交合同等待审批", 0);
		/*
		 * 移动图片
		 */
		String tempDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
		String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
		String oneSideFilePath = realDir + oneSide.getFilePath();
		String otherSideFilePath = realDir + otherSide.getFilePath();
		if ("01".equals(conDB.getStages())) {
			/*
			 * 添加租金分期房主协议图片
			 */
			SysInfoAtt lessorSide = con.getInfoAttList().get(3);
			lessorSide.setLid(cid);
			lessorSide.setFileType(InfoAttFileTypeEnum.STAGES_LESSOR_ENUM.getValue());
			lessorSide.preInsert();
			infoAttDao.insertSelective(lessorSide);
			/*
			 * 添加租金分期房客协议图片
			 */
			SysInfoAtt lesseeSide = con.getInfoAttList().get(4);
			lesseeSide.setLid(cid);
			lesseeSide.setFileType(InfoAttFileTypeEnum.STAGES_LESSEE_ENUM.getValue());
			lesseeSide.preInsert();
			infoAttDao.insertSelective(lesseeSide);
			String lessorFilePath = realDir + lessorSide.getFilePath();
			String lesseeFilePath = realDir + lesseeSide.getFilePath();
			File file = new File(lessorFilePath);
			if (!file.exists()) {
				copyFile(tempDir + lessorSide.getFilePath(), lessorFilePath);
			}
			file = null;
			file = new File(lesseeFilePath);
			if (!file.exists()) {
				copyFile(tempDir + lesseeSide.getFilePath(), lesseeFilePath);
			}
		}
		
		File file = new File(oneSideFilePath);
		if (!file.exists()) {
			copyFile(tempDir + oneSide.getFilePath(), oneSideFilePath);
		}
		file = null;
		file = new File(otherSideFilePath);
		if (!file.exists()) {
			copyFile(tempDir + otherSide.getFilePath(), otherSideFilePath);
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws Exception
	 */
	private void copyFile(String srcFilePath, String destFilePath) throws Exception {
		boolean result = FileUtils.copyFileCover(srcFilePath, destFilePath, true);
		if (!result) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
		}
	}

	/**
	 * 删除实际使用者
	 * 
	 * @param cid
	 * @throws Exception
	 */
	private void deleteHouseUser(String cid) throws Exception {
		ConHouseUser houUser = new ConHouseUser();
		houUser.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		ConHouseUserExample example = new ConHouseUserExample();
		com.dlfc.zfgj.entity.ConHouseUserExample.Criteria cri = example.createCriteria();
		cri.andCidEqualTo(cid);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		houUserDao.updateByExampleSelective(houUser, example);
	}

	/**
	 * 保存实际使用者
	 * 
	 * @param houUserList
	 * @throws Exception
	 */
	private void saveHouseUser(List<ConHouseUser> houUserList, String cid, String conLogId) throws Exception {
		if (houUserList != null) {
			for (ConHouseUser houUser : houUserList) {
				houUser.setCid(cid);
				houUser.preInsert();
				houUserDao.insertSelective(houUser);
				saveHouseUserLog(houUser, conLogId);
			}
		}
	}

	/**
	 * 保存实际使用者log
	 * 
	 * @param houUser
	 * @param conLogId
	 * @throws Exception
	 */
	private void saveHouseUserLog(ConHouseUser houUser, String conLogId) throws Exception {
		ConHouseUserLog houUserLog = new ConHouseUserLog();
		houUserLog.preInsert();
		houUserLog.setCid(houUser.getCid());
		houUserLog.setConLogId(conLogId);
		houUserLog.setCoPid(houUser.getPid());
		houUserLog.setIdNo(houUser.getIdNo());
		houUserLog.setIdType(houUser.getIdType());
		houUserLog.setMobile(houUser.getMobile());
		houUserLogDao.insertSelective(houUserLog);
	}

	/**
	 * 删除房屋清单列表
	 * 
	 * @param cid
	 * @throws Exception
	 */
	private void deleteHouseItems(String cid) throws Exception {
		ConHouseItems houItems = new ConHouseItems();
		houItems.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		ConHouseItemsExample example = new ConHouseItemsExample();
		com.dlfc.zfgj.entity.ConHouseItemsExample.Criteria cri = example.createCriteria();
		cri.andCidEqualTo(cid);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		houItemsDao.updateByExampleSelective(houItems, example);
	}

	/**
	 * 保存房屋清单列表
	 * 
	 * @param houItemsList
	 * @param cid
	 * @throws Exception
	 */
	private void saveHouseItems(List<ConHouseItems> houItemsList, String cid, String conLogId) throws Exception {
		if (houItemsList != null) {
			for (ConHouseItems houItems : houItemsList) {
				if (StringUtils.isNotEmpty(houItems.getItem()) && StringUtils.isNotEmpty(houItems.getBrand())
						&& houItems.getPrice().doubleValue() != 0 && houItems.getPrice() != null
						&& houItems.getNum() != null && houItems.getNum() != 0 && houItems.getCompenAmount() != null
						&& houItems.getCompenAmount().doubleValue() != 0) {
					houItems.setCid(cid);
					houItems.preInsert();
					houItemsDao.insertSelective(houItems);
					saveHouseItemsLog(houItems, conLogId);
				}
			}
		}
	}

	/**
	 * 保存房屋清单列表log
	 * 
	 * @param houItems
	 * @param conLogId
	 * @throws Exception
	 */
	private void saveHouseItemsLog(ConHouseItems houItems, String conLogId) throws Exception {
		ConHouseItemsLog houItemsLog = new ConHouseItemsLog();
		houItemsLog.preInsert();
		houItemsLog.setBrand(houItems.getBrand());
		houItemsLog.setCid(houItems.getCid());
		houItemsLog.setComment(houItems.getComment());
		houItemsLog.setCompenAmount(houItems.getCompenAmount());
		houItemsLog.setConLogId(conLogId);
		houItemsLog.setItem(houItems.getItem());
		houItemsLogDao.insertSelective(houItemsLog);
	}
	
	/**
	 * 删除其他费用列表
	 * 
	 * @param cid
	 * @throws Exception
	 * @author HAN.JIAQI 2016.05.12
	 */
	private void deleteOtherItems(String cid) throws Exception {
		ConOtherCosts costs = new ConOtherCosts();
		costs.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		ConOtherCostsExample example = new ConOtherCostsExample();
		com.dlfc.zfgj.entity.ConOtherCostsExample.Criteria cri = example.createCriteria();
		cri.andCidEqualTo(cid);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		conOtherCostsMapper.updateByExampleSelective(costs, example);
	}

	/**
	 * 保存其他费用列表
	 * 
	 * @param houItemsList
	 * @param cid
	 * @throws Exception
	 * @author HAN.JIAQI 2016.05.12
	 */
	private void saveOtherItems(List<ConOtherCosts> conOtherCostsList, String cid) throws Exception {
		if (conOtherCostsList != null) {
			for (ConOtherCosts costs : conOtherCostsList) {
				if (StringUtils.isNotEmpty(costs.getItem()) && StringUtils.isNotEmpty(costs.getUnit())
						&& costs.getPrice().doubleValue() != 0 && costs.getPrice() != null
						&& costs.getStime() != null && costs.getScount() != null) {
					costs.setCid(cid);
					costs.preInsert();
					conOtherCostsMapper.insertSelective(costs);
				}
			}
		}
	}

	/**
	 * 检验版本
	 * 
	 * @param cid
	 * @param oldVersion
	 * @param conDB
	 * @return
	 * @throws Exception
	 */
	public ConContract checkVersion(String cid, Integer oldVersion) throws Exception {
		ConContract con = null;
		if (oldVersion != null) {
			con = conDao.selectByPrimaryKey(cid);
			if (oldVersion.intValue() != con.getVersion()) {
				con = null;
			}
		}
		if (con == null) {
			// 数据已被更新，请刷新页面再进行操作！
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
		}
		return con;
	}

	/**
	 * 保存合同log
	 * 
	 * @param cid
	 * @param oldStatus
	 * @param optEvent
	 * @param dcrp
	 * @return
	 * @throws Exception
	 */
	public String saveLog(String cid, short oldStatus, int optEvent, String dcrp) throws Exception {
		ConContract conDB = conDao.selectByPrimaryKey(cid);
		ConContractLog conLog = new ConContractLog();
		ConversionUtils.reflectionAttr(conDB, conLog);
		if (conDB.getAgentStatus() != null) {
			conLog.setAgentStatus((int) conDB.getAgentStatus());
		}
		conLog.setConStatusBefore(oldStatus);
		conLog.setConStatusCurr(conDB.getStatus());
		conLog.setOperator(UserUtils.getUser().getId());
		conLog.setOptEvent(optEvent);
		conLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		conLog.setDcrp(dcrp);
		conLog.setPindex(null);
		conLog.setId(IdGen.uuid());
		conLog.setCid(cid);
		conLogDao.insertSelective(conLog);
		return conLog.getId();
	}

	/**
	 * 获取一个周期内的相关合同 当前时间段中是否可以租赁特定面积使用
	 * 
	 * @param con
	 *            配置开始时间结束时间
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public double getConInPeriod(ConContract con) throws Exception {
		List<Double> l = new ArrayList<Double>();

		List cons = (List<ConContract>) conDao.getConInPeriod(con);
		if (cons != null) {

			for (int i = 0; i < cons.size(); i++) {
				ConContract c = (ConContract) cons.get(i);

				Double a = getAreaInPeriod(c);
				l.add(a);
			}
			// Collections.sort(l);
			Collections.sort(l, new Comparator<Double>() {
				@Override
				public int compare(Double o1, Double o2) {
					return o2.compareTo(o1);
				}
			});

		}
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return (double) 0;
		}

	}

	public Double getAreaInPeriod(ConContract con) throws Exception {
		Double cons = (Double) conDao.getAreaInPeriod(con);
		return cons;

	}

	/**
	 * 获取可出租的房源
	 * 
	 * @param beid
	 *            归属人ID
	 * @return
	 * @throws Exception
	 */
	public List<HouHouseInfo> getCanRentHouList(String beid, String hid, String pId) throws Exception {
//		List<HouHouseInfo> resultList = new ArrayList<HouHouseInfo>();
		List<HouHouseInfo> houinfoList = houDao.findList(beid);
//		注释掉了房主是出租人的这部分逻辑，未来可能还需要加上
//		SysPerson sysPerson = personDao.selectByPrimaryKey(pId);
//		if (houinfoList != null) {
//			for (HouHouseInfo houseInfo : houinfoList) {
//				// 如果房主已认证，房源共有人pID等于出租人pID，则显示
//				if (sysPerson.getStatus() == 1) {
//					if (pId.equals(houseInfo.getCopId())) {
//						resultList.add(houseInfo);
//					}
//				}
//			}
//		}
		return houinfoList;
	}

	/**
	 * 判断输入的合同开始时间是否合法 如果合法，获取可租面积和是否可以整租
	 * 
	 * @param startTime
	 * @param endTime
	 * @param hid
	 * @param cid
	 * @param resultMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getCanRentalArea(String startTime, String endTime, String hid, String cid)
			throws Exception {
		double leasedArea = 0.0;
		//TODO 根据baseId 搜索出所有的房源id
		String baseId = houDao.selectByPrimaryKey(hid).getBaseId();
		List<String> hidList = houDao.findByBaseId(baseId);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		Date startDate = DateUtils.strToDate(startTime, DateUtils.DATE_Y_M_D_PATERN);
		Date endDate = DateUtils.strToDate(endTime, DateUtils.DATE_Y_M_D_PATERN);
		ConContract con = new ConContract();
		con.setStartTime(startDate);
		con.setEndTime(endDate);
		if (StringUtils.isNotEmpty(cid)) {
			con.setId(cid);
		}
		
		// 计算该房源一共出租的面积
		for(int i=0; i < hidList.size() ; i++){
			con.setHid(hidList.get(i));
			leasedArea = leasedArea + getConInPeriod(con);
		}
		
		HouHouseBaseinfo houInfo = houHouseBaseinfoMapper.selectByPrimaryKey(baseId);
		int totalArea = (int) (houInfo.getBuildingArea().doubleValue());
		if (leasedArea == 0) {
			resultMap.put("overplusArea", totalArea + "");
			resultMap.put("wholeRentFlg",RentalModeEnum.WHOLE_RENT_ENUM.getValue() + "");
		} else {
			int overplusArea = (int) (totalArea - leasedArea);
			resultMap.put("overplusArea", overplusArea + "");
			resultMap.put("wholeRentFlg",RentalModeEnum.PART_RENT_ENUM.getValue() + "");
		}
		resultMap.put("contractFlag", houInfo.getContractFlag() + "");
		return resultMap;
	}
	/**
	 * 验证年龄是否大于等于18岁
	 * @param perId 人员信息id
	 * @return ajax对象
	 */
	public AjaxResult checkAge(String perId){
		SysPerson sysPerson = personDao.selectByPrimaryKey(perId);
		if (sysPerson != null && StringUtils.isNotEmpty(sysPerson.getIdNo())) {
			boolean age = false;
			try {
				age = this.getAge(sysPerson.getIdNo());
			} catch (ApplicationException e) {
				return new AjaxResult(AjaxResult.EXECUTE_FAILURE, e.getMessage());
			}
			if (age) {
				return new AjaxResult(AjaxResult.EXECUTE_SUCCESS, null);
			} else {
				return new AjaxResult(AjaxResult.EXECUTE_FAILURE, PropertyUtils.getErrorMsg("SYS-0111"));
			}
		} else {
			return new AjaxResult(AjaxResult.EXECUTE_FAILURE, PropertyUtils.getErrorMsg("SYS-0110"));
		}
	}

	/**
	 * 根据身份证号计算年龄
	 * 计算方式：18周岁生日的下一天零点之后才算满18周岁
	 * 
	 * @param IDCardNum
	 *            身份证号
	 * @return 满18岁true 未满false
	 * @throws ApplicationException
	 *             日期转换失败
	 */
	private boolean getAge(String IDCardNum) throws ApplicationException {
		int year, month, day, idLength = IDCardNum.length();
		if (idLength == 18) {
			year = Integer.parseInt(IDCardNum.substring(6, 10)) + 18;
			month = Integer.parseInt(IDCardNum.substring(10, 12));
			day = Integer.parseInt(IDCardNum.substring(12, 14)) + 1;
		} else if (idLength == 15) {
			year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900 + 18;
			month = Integer.parseInt(IDCardNum.substring(8, 10));
			day = Integer.parseInt(IDCardNum.substring(10, 12)) + 1;
		} else {
			return false;
		}
		try {
			return getYearDiff(year, month, day);
		} catch (ApplicationException e) {
			throw new ApplicationException("解析文件失败");
		}
	}

	/**
	 * 
	 * 判断日期大小
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 布尔值
	 * @throws ApplicationException
	 *             日期转换失败
	 */
	private boolean getYearDiff(int year, int month, int day) throws ApplicationException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sMonth = "";
		String sDay = "";
		if (String.valueOf(month).length() == 1) {
			sMonth = "0" + String.valueOf(month);
		} else {
			sMonth = month + "";
		}

		if (String.valueOf(day).length() == 1) {
			sDay = "0" + String.valueOf(day);
		} else {
			sDay = day + "";
		}
		Date update = null;
		try {
			update = sdf.parse(String.valueOf(year) + sMonth + sDay);
		} catch (ParseException e) {
			logger.error("ContractController getYearDiff 日期转换失败", e);
			throw new ApplicationException("日期转换失败");
		}
		Date today = new Date();
		return today.after(update);
	}
	
	public String checkIdentity(String name, int idType, String idNo, boolean able) throws Exception {
		String perId = null;
		SysPersonExample example = new SysPersonExample();
		com.dlfc.zfgj.entity.SysPersonExample.Criteria cri = example.createCriteria();
		cri.andNameEqualTo(name);
		cri.andIdTypeEqualTo(idType);
		cri.andIdNoEqualTo(idNo);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		List<SysPerson> personList = personDao.selectByExample(example);
		if (personList != null && personList.size() != 0) {
			perId = personList.get(0).getId();
		}
		if (StringUtil.isEmpty(perId) && able) {
			//验证人员表是否有此身份证号
			if (sysPersonService.isIdNoBinded(idNo)) {
				return null;
			}
			if(allinpayService.checkId(idNo, name)){
				SysPerson person = new SysPerson();
				person.setName(name);
				person.setIdType(1);
				person.setIdNo(idNo);
				person.setStatus(1);
				person.preInsert();
				person.setCertTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
				personDao.insertSelective(person);
				perId = person.getId();
			}
		}
		return perId;
	}

	private String getDcrp(int type) {
		String dcrp = "";
		switch (type) {
		case TYPE_STEP1:
			dcrp = "新建合同step1";
			break;
		case TYPE_STEP2:
			dcrp = "新建合同step2";
			break;
		case TYPE_STEP3:
			dcrp = "新建合同step3";
			break;
		case TYPE_STEP4:
			dcrp = "新建合同step4";
			break;
		case TYPE_STEP5:
			dcrp = "下载合同";
			break;
		default:
			break;
		}
		return dcrp;
	}

	/**
	 * 添加用户
	 * 
	 * @param name
	 * @param idType
	 * @param idNo
	 * @param mobile
	 * @param bankType
	 * @param bankNum
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String addUser(String name, int idType, String idNo, String mobile, boolean type, String bankNum)
			throws Exception {

		String iName = "";
		// 根据type判断是出租方还是承租方用于给失败提示
		if (type) {
			iName = "出租方";
		} else {
			iName = "承租方";
		}

		String perId = checkIdentity(name, idType, idNo, true);
		if (StringUtil.isEmpty(perId)) {
			throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0009"));
		}
		// 先判断库中有无此电话
		if (userService.isMobileBinded(mobile)) {
			UsrUser u = userService.getUserByMobile(mobile);
			// 判断此电话与身份证号+名字是否是一条用户信息如果不是需要提示电话被绑定
			if (StringUtils.isBlank(u.getPerId())) {
				throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0010"));
			} else if (!u.getPerId().equals(perId)) {
				throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0011"));
			}

		}

		UsrUser user = userService.getUserByPerId(perId);
		// 如果user为空证明之前没有绑定过账户
		if (user == null) {
			if (userService.isCardBinded(bankNum)) {
				throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0012"));
			}
			Map<String, String> map = allinpayService.getBankByAlipay(bankNum);
			if (map != null) {
				// 通联接口type
				String allinType = "";
				// syspaychannelbanks表中type
				String cardType = "";
				if ("CC".equalsIgnoreCase(map.get("cardType").toString())) {
					allinType = "02";
					cardType = "02";
				} else if ("DC".equalsIgnoreCase(map.get("cardType").toString())) {
					allinType = "00";
					cardType = "01";
				}
				String pcId = userService.selectIdByCode(Global.getConfig("bankCardWay"));
				SysPaychannelBanks sysPaychannelBanks = userService.selectBankCodeByPayway(map.get("bank"), pcId,
						cardType);
				if (sysPaychannelBanks == null) {
					throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0013"));
				}
				boolean f = allinpayService.checkBankInfo(sysPaychannelBanks.getBankCode(), allinType, bankNum, name,
						"0", idNo, mobile);
				if (f) {

					user = new UsrUser();
					user.preInsert();
					user.setMobile(mobile);
					String username = "";
					while (true) {
						username = mobile + Const.UNDERLINE + StringUtils.getRandomCapital(3);
						if (!userService.isUserNameBinded(username)) {
							break;
						}
					}
					StringBuilder str=new StringBuilder();
					Random random=new Random();
					for(int i=0;i<8;i++){
						str.append(random.nextInt(10));
					}
					user.setUsername(username);
					user.setPassword(SystemService.entryptPassword(str.toString()));
					user.setPerId(perId);
					userService.save(user);
					UsrAccount ua = new UsrAccount();
					ua.preInsert();
					ua.setUserId(user.getId());
					UsrAccountnDao.insertSelective(ua);
					addUserBankcard(user.getId(), mobile, map.get("bank"), bankNum, cardType);
					Map<String,String> maps = new HashMap<String, String>();
					maps.put("tel",mobile.substring(7, 11));
					maps.put("code",str.toString());
					smsService.sendSMSByTemplate(mobile, "Z0017",maps);
				} else {
					throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0014"));
				}
			} else {
				throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0013"));
			}

		} else {
			UsrUserBankcardExample example = new UsrUserBankcardExample();
			com.dlfc.zfgj.entity.UsrUserBankcardExample.Criteria cri = example.createCriteria();
			cri.andBankNumEqualTo(bankNum);
			cri.andUserIdEqualTo(user.getId());
			cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
			List<UsrUserBankcard> userBankcardList = userBankcardDao.selectByExample(example);
			/*
			 * 如果此银行卡存在，但是不是默认的，将卡设置成默认的
			 */
			if (userBankcardList != null && userBankcardList.size() > 0) {
				UsrUserBankcard userBankcard = userBankcardList.get(0);
				if (userBankcard.getIsDefault() != YesNoEnum.YES_ENUM.getValue()) {
					/*
					 * 将之前的银行卡设置为不是默认的
					 */
					updateUserBankcard(user.getId());
					/*
					 * 将此银行卡设置为默认的
					 */
					UsrUserBankcard bankcard = new UsrUserBankcard();
					bankcard.setIsDefault((short) YesNoEnum.YES_ENUM.getValue());
					bankcard.setId(userBankcard.getId());
					bankcard.preUpdate();
					userBankcardDao.updateByPrimaryKeySelective(bankcard);
				}
			} else {
				Map<String, String> map = allinpayService.getBankByAlipay(bankNum);
				if (map != null) {
					// 通联接口type
					String allinType = "";
					// syspaychannelbanks表中type
					String cardType = "";
					if ("CC".equalsIgnoreCase(map.get("cardType").toString())) {
						allinType = "02";
						cardType = "02";
					} else if ("DC".equalsIgnoreCase(map.get("cardType").toString())) {
						allinType = "00";
						cardType = "01";
					}
					String pcId = userService.selectIdByCode(Global.getConfig("bankCardWay"));
					SysPaychannelBanks sysPaychannelBanks = userService.selectBankCodeByPayway(map.get("bank"), pcId,
							cardType);
					if (sysPaychannelBanks == null) {
						throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0013"));
					}
					boolean f = allinpayService.checkBankInfo(sysPaychannelBanks.getBankCode(), allinType, bankNum,
							name, "0", idNo, mobile);
					if (f) {
						/*
						 * 将之前的银行卡设置为不是默认的
						 */
						updateUserBankcard(user.getId());
						addUserBankcard(user.getId(), mobile, map.get("bank"), bankNum, cardType);
					} else {
						throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0014"));
					}
				} else {
					throw new ApplicationException(iName + PropertyUtils.getErrorMsg("SYS-0013"));
				}
			}
			
		}
		return perId;
	}

	/**
	 * 添加银行卡
	 * 
	 * @param userId
	 * @param mobile
	 * @param bankType
	 * @param bankNum
	 * @throws Exception
	 */
	private void addUserBankcard(String userId, String mobile, String baseCode, String bankNum, String cardType)
			throws Exception {
		UsrUserBankcard userBankcard = new UsrUserBankcard();
		userBankcard.preInsert();
		userBankcard.setBankMobile(mobile);
		userBankcard.setBankNum(bankNum);
		userBankcard.setUserId(userId);
		userBankcard.setBaseCode(baseCode);
		userBankcard.setCardType(cardType);
		userBankcard.setIsDefault((short) YesNoEnum.YES_ENUM.getValue());
		userBankcardDao.insertSelective(userBankcard);
	}

	/**
	 * 将其余银行卡设置为非默认
	 * 
	 * @param userId
	 * @throws Exception
	 */
	private void updateUserBankcard(String userId) throws Exception {
		UsrUserBankcard userBankcard = new UsrUserBankcard();
		userBankcard.preUpdate();
		userBankcard.setIsDefault((short) YesNoEnum.NO_ENUM.getValue());
		UsrUserBankcardExample example = new UsrUserBankcardExample();
		com.dlfc.zfgj.entity.UsrUserBankcardExample.Criteria cri = example.createCriteria();
		cri.andUserIdEqualTo(userId);
		cri.andIsDefaultEqualTo((short) YesNoEnum.YES_ENUM.getValue());
		userBankcardDao.updateByExampleSelective(userBankcard, example);
	}

	/**
	 * 移交合同
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void saveTransfer(String id, String beid) throws Exception {
		ConContract con = new ConContract();
		con.preUpdate();
		con.setId(id);
		con.setBeid(beid);
		conDao.updateByPrimaryKeySelective(con);
		saveLog(id, (short) 0, 8, "移交合同");
	}

	/**
	 * 校验此合同是否已经存在续约合同
	 * 
	 * @throws Exception
	 */
	private void checkRenewalExist(String cid) throws Exception {
		List<Short> statusList = new ArrayList<Short>();
		statusList.add(Short.valueOf(ConStatusEnum.CREATING_ENUM.getValue() + ""));
		statusList.add(Short.valueOf(ConStatusEnum.ACTIVE_ENUM.getValue() + ""));
		statusList.add(Short.valueOf(ConStatusEnum.APPROVE_REJECT_ENUM.getValue() + ""));
		statusList.add(Short.valueOf(ConStatusEnum.APPROVING_ENUM.getValue() + ""));
		statusList.add(Short.valueOf(ConStatusEnum.WAIT_UPLOAD_ENUM.getValue() + ""));
		ConContractExample example = new ConContractExample();
		Criteria cri = example.createCriteria();
		cri.andRenewalCidEqualTo(cid);
		cri.andRenewalFlgEqualTo((short) YesNoEnum.YES_ENUM.getValue());
		cri.andStatusIn(statusList);
		int count = conDao.countByExample(example);
		if (count > 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0015"));
		}
	}

	public ConContract find(ConContract condition) throws Exception {
		return conDao.findOne(condition);

	}

	/**
	 * 根据房源ID查有效合同的到期时间
	 * 
	 * @param hid
	 * @return
	 */
	public List<ConContract> getEndTime(String hid) {
		ConContract example = new ConContract();
		example.setHid(hid);

		return conDao.getEndTime(example);
	}

	public List<DateTimeBean> dateList(Date startTime, Date endTime, int type, double money, int year, int month) {
		List<DateTimeBean> list = new ArrayList<DateTimeBean>();
		SimpleDateFormat formatYYYY = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatMM = new SimpleDateFormat("MM");
		SimpleDateFormat formatDD = new SimpleDateFormat("dd");
		// 总月数
		int totalMonth = year * 12 + month;
		// 声明循环轮回
		int cycle = 1;
		if (type == 2) {// 季付
			cycle = 3;
		} else if (type == 3) {// 半年付
			cycle = 6;
		} else if (type == 4) {// 年付
			cycle = 12;
		}
		Calendar clStartTime = Calendar.getInstance();
		clStartTime.setTime(startTime);
		int payStartDay = clStartTime.get(Calendar.DATE);
		// 支付时间
		Date afterPayDate;
		// 支付起始有效日期
		Date fromDate;
		// 支付结束有效日期
		Date toDate;
		// 支付金额
		double rent = 0;
		for (int i = 0; i < totalMonth; i = i + cycle) {
			int surplus = totalMonth - i;
			if (i == 0) {
				afterPayDate = startTime;
				fromDate = startTime;
				Calendar cl = Calendar.getInstance();
				cl.setTime(startTime);
				cl.add(Calendar.MONTH, cycle);
				int days = cl.getActualMaximum(Calendar.DATE);
				if (payStartDay <= days) {
					cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), payStartDay - 1);
				} else {
					cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), days - 1);
				}
				toDate = cl.getTime();
				rent = money * cycle;
			} else {
				if (cycle == 1) {
					Calendar cl = Calendar.getInstance();
					cl.setTime(startTime);
					cl.add(Calendar.MONTH, i);
					int firstDays = cl.getActualMaximum(Calendar.DATE);
					if (payStartDay > firstDays) {
						cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), firstDays);
					}
					afterPayDate = cl.getTime();
					fromDate = afterPayDate;
					Calendar cls = Calendar.getInstance();
					cls.setTime(startTime);
					cls.add(Calendar.MONTH, cycle + i);
					int secondDays = cls.getActualMaximum(Calendar.DATE);
					if (payStartDay <= secondDays) {
						cls.set(cls.get(Calendar.YEAR), cls.get(Calendar.MONTH), payStartDay - 1);
					} else {
						cls.set(cl.get(Calendar.YEAR), cls.get(Calendar.MONTH), secondDays - 1);
					}
					toDate = cls.getTime();
					rent = money * cycle;
				} else {
					Calendar cl = Calendar.getInstance();
					cl.setTime(startTime);
					cl.add(Calendar.MONTH, i - 1);
					int firstDays = cl.getActualMaximum(Calendar.DATE);
					if (payStartDay > firstDays) {
						cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), firstDays);
					}
					afterPayDate = cl.getTime();
					Calendar cls = Calendar.getInstance();
					cls.setTime(startTime);
					cls.add(Calendar.MONTH, i);
					int secondDays = cls.getActualMaximum(Calendar.DATE);
					if (payStartDay > secondDays) {
						cls.set(cls.get(Calendar.YEAR), cls.get(Calendar.MONTH), secondDays);
					}
					fromDate = cls.getTime();
					if (surplus >= cycle) {
						Calendar clss = Calendar.getInstance();
						clss.setTime(startTime);
						clss.add(Calendar.MONTH, i + cycle);
						int thirdDays = clss.getActualMaximum(Calendar.DATE);
						if (payStartDay <= thirdDays) {
							clss.set(clss.get(Calendar.YEAR), clss.get(Calendar.MONTH), payStartDay - 1);
						} else {
							clss.set(clss.get(Calendar.YEAR), clss.get(Calendar.MONTH), thirdDays - 1);
						}
						toDate = clss.getTime();
						rent = money * cycle;
					} else {
						toDate = endTime;
						rent = money * surplus;
					}
				}
			}
			DateTimeBean dtb = new DateTimeBean();
			dtb.setAfterPayDateYYYY(formatYYYY.format(afterPayDate));
			dtb.setAfterPayDateMM(formatMM.format(afterPayDate));
			dtb.setAfterPayDateDD(formatDD.format(afterPayDate));
			dtb.setFromDateYYYY(formatYYYY.format(fromDate));
			dtb.setFromDateMM(formatMM.format(fromDate));
			dtb.setFromDateDD(formatDD.format(fromDate));
			dtb.setToDateYYYY(formatYYYY.format(toDate));
			dtb.setToDateMM(formatMM.format(toDate));
			dtb.setToDateDD(formatDD.format(toDate));
			dtb.setMoney(rent);
			list.add(dtb);
		}
		return list;
	}
	
	/**
	 * 查询通联支持的银行列表,并组合成HTML
	 * @return
	 */
	public String selectAllpaySupportBankList()
	{
		List<HashMap<String, Object>> records=sysPaychannelBanksMapper.selectBankList();
		String html="";
		if(records!=null&&!records.isEmpty())
		{
			html="<table class=\"table table-border table-bordered table-hover table-bg\">";
			html+="<thead>";
			html+="<tr>";
			html+="<th width=\"100\">银行名称</th>";
			html+="<th width=\"20\">卡类型</th>";
			html+="</tr>";
			html+="</thead>";
			html+="<tbody>";
			for(HashMap<String, Object> record:records)
			{
				String itemNo=record.get("BANK_NAME").toString();
				String itemName=record.get("cardTypeMsg").toString();
				html+="<tr style=\"color:gray;\">";
				html+="<td>"+itemNo+"</td>";
				html+="<td>"+itemName+"</td>";
				html+="</tr>";
			}
			html+="</tbody>";
			html+="</table>";
		}
		return html;
	}
}
