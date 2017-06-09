/**
* @name: HouHouseInfoService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 房源增删改查操作
*
* @version: 1.0
* @date : 2016年8月16日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月16日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.ConversionUtils;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.OrderUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.HouCoOwnerTemp;
import com.dlfc.zfgj.entity.HouCoOwnerTempLog;
import com.dlfc.zfgj.entity.HouEntrustInfo;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouHouseInfoAtt;
import com.dlfc.zfgj.entity.HouHouseInfoExample;
import com.dlfc.zfgj.entity.HouHouseInfoLog;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.HouCoOwnerTempLogMapper;
import com.dlfc.zfgj.mapper.HouHouseInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.EntrustStateEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseActiveStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseAuditStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseCertStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseSourceEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;

/**
 * @name: HouHouseInfoService
 * @description: 房源增删改查操作
 * @version 1.0
 * @author yuanjw
 *
 */
@Service
public class HouHouseInfoService extends BaseService {
	/** 个人信息service */
	@Autowired
	private SysPersonService personService;
	/** 房源信息mapper */
	@Autowired
	private HouHouseInfoMapper houHouseInfoMapper;
	/** 房源图片service */
	@Autowired
	public HouHouseInfoAttService houHouseInfoAttService;
	/** 房源基础信息service */
	@Autowired
	public HouHouseBaseinfoService houHouseBaseinfoService;
	/** 房源共有人service */
	@Autowired
	HouCoOwnerTempService houCoOwnerTempService;
	/** 房源共有人日志mapper */
	@Autowired
	HouCoOwnerTempLogMapper coOwnerTempLogDao;
	/** 房源委托service */
	@Autowired
	private HouEntrustInfoService houEntrustInfoService;
	/** 经纪人信息mapper */
	@Autowired
	private AgtEmpInfoMapper agtEmpInfoMapper;

	/** 房源图片存放目录 */
	private static final String HOU_PIC_DIR = "hou";

	/**
	 * 根据id取得房源
	 * 
	 * @param id
	 *            房源ID
	 * @return 房源对象
	 */
	public HouHouseInfo findById(String id) {
		return houHouseInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询分页数据-显示房源
	 * 
	 * @param page
	 *            分页对象
	 * @param entity
	 *            房源对象
	 * @return 房源信息页面对象
	 */
	public BasePage<HouHouseInfo> selectPage(BasePage<HouHouseInfo> page, HouHouseInfo entity) {
		// 根据uid查询eid
		String eid = agtEmpInfoMapper.getEid(UserUtils.getUser().getId());
		entity.setUid(eid);
		// 用户ID非空
		if (StringUtils.isEmpty(entity.getUid())) {
			entity.setUid(UserUtils.getUser().getId());
		}
		entity.setPage(page);

		List<HouHouseInfo> list = houHouseInfoMapper.selectHouseInfoList(entity);

		page.setList(list);
		return page;
	}

	/**
	 * 收藏房源
	 * 
	 * @param id
	 *            房源ID
	 * @param collectType
	 *            收藏类型
	 */
	public void updateCollectHouse(String id, String collectType) {
		HouHouseInfo record = new HouHouseInfo();
		record.setId(id);
		// 收藏类型非空
		if (collectType != null && !"".equals(collectType) && !"1".equals(collectType)) {
			record.setCollectType(YesNoEnum.YES_ENUM.getValue());
		} else {
			record.setCollectType(YesNoEnum.NO_ENUM.getValue());
		}
		record.preUpdate();
		// 更新房源信息
		houHouseInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 是否可以删除房源-检测房源是否在合同表存在
	 * 
	 * @param hid
	 *            房源ID
	 * @return 房源信息对象列表
	 */
	public List<HouHouseInfo> delCheck(String hid) {
		return this.houHouseInfoMapper.delCheck(hid);
	}

	/**
	 * 禁用房源
	 * 
	 * @param hisArr
	 *            房源ID数组
	 */
	public void deleteHouse(String[] hisArr) {
		for (int i = 0; i < hisArr.length; i++) {
			String hid = hisArr[i].split(",")[0];
			// 保存房源信息到记录表
			this.saveHouseLog(hid, 3, "删除");

			HouHouseInfo record = new HouHouseInfo();
			record.preUpdate();
			record.setId(hid);
			record.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());

			houHouseInfoMapper.updateByPrimaryKeySelective(record);
		}
	}

	/**
	 * 编辑房源-修改房源信息
	 * 
	 * @param houseInfo
	 *            房源对象
	 */
	public void updateHouse(HouHouseInfo houseInfo) {
		//添加房源来源
		houseInfo.setSource(HouseSourceEnum.ZFGJ_ENUM.getValue());
		houseInfo.preUpdate();
		houHouseInfoMapper.updateByPrimaryKeySelective(houseInfo);
	}

	/**
	 * 根据特定条件修改房源信息
	 * 
	 * @param houseInfo
	 *            房源对象
	 * @param example
	 *            条件对象
	 * @author HAN.JIAQI 2016/07/22
	 */
	public void updateHouse(HouHouseInfo houseInfo, HouHouseInfoExample example) {
		houseInfo.preUpdate();
		houHouseInfoMapper.updateByExampleSelective(houseInfo, example);
	}

	/**
	 * 编辑房源-保存房源信息到记录表
	 * 
	 * @param hid
	 *            房源ID
	 * @param event
	 *            操作事件
	 * @param desc
	 *            描述
	 * @return 房源信息操作记录ID
	 */
	public String saveHouseLog(String hid, Integer event, String desc) {
		HouHouseInfoLog houseInfoLog = new HouHouseInfoLog();
		houseInfoLog.setHid(hid);
		houseInfoLog.setOptEvent(event);
		houseInfoLog.setDcrp(desc);
		houseInfoLog.preInsert();
		houHouseInfoMapper.saveHouseLog(houseInfoLog);
		return houseInfoLog.getId();
	}

	/**
	 * 编辑房源
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param houseInfo
	 *            房源对象
	 * @param mulImage_path
	 *            房源图片数组
	 * @throws ApplicationException
	 *             创建文件失败,上传文件失败
	 */
	public void updateHouse(HttpServletRequest request, HouHouseInfo houseInfo, String[] mulImage_path)
			throws ApplicationException {
		// 房源ID
		String houseId = houseInfo.getId();
		// 房屋共有人
		String houseOwnerName = houseInfo.getHouseOwnerName();
		// 房主证件类型
		Integer houseOwnerIdType = houseInfo.getIdType();
		// 房主身份证号
		String houseOwnerIdNo = houseInfo.getHouseOwnerIdNo();
		if (houseInfo.getOwners() != null && !houseInfo.getOwners().isEmpty() && houseInfo.getOwners().get(0) != null) {
			// 房源ID非空
			if (StringUtils.isEmpty(houseId)) {
				houseId = houseInfo.getOwners().get(0).getId();
			}
			// 房源共有人非空
			if (StringUtils.isEmpty(houseOwnerName)) {
				houseOwnerName = houseInfo.getOwners().get(0).getName();
			}
			// 房主证件类型非空
			if (houseOwnerIdType == null) {
				houseOwnerIdType = houseInfo.getOwners().get(0).getIdType();
			}
			// 房主身份证号非空
			if (StringUtils.isEmpty(houseOwnerIdNo)) {
				houseOwnerIdNo = houseInfo.getOwners().get(0).getIdNo();
			}
		}

		// 创建共有人map
		Map<String, String> ownerMap = new HashMap<String, String>();
		ownerMap.put("name", houseOwnerName);
		ownerMap.put("type", String.valueOf(houseOwnerIdType));
		ownerMap.put("code", houseOwnerIdNo);
		// 检测共有人是否存在
		List<String> personList = checkPerson(ownerMap);

		String ownerId = "";
		// 共有人非空
		if (personList == null || personList.size() == 0) {
			SysPerson person = new SysPerson();
			person.setIdType(houseOwnerIdType);
			person.setIdNo(houseOwnerIdNo);
			person.setName(houseOwnerName);
			person.setCertStatus(Short.valueOf((short) YesNoEnum.YES_ENUM.getValue()));
			person.setStatus(YesNoEnum.YES_ENUM.getValue());
			ownerId = person.getUUID();
			person.setId(ownerId);
			personService.insertUser(person);
		} else {
			ownerId = personList.get(0);
		}

		houseInfo.setVersion(houseInfo.getVersion() + 1);// 每次加1

		// 暂时先注释 若产权类型是产权或者使用权，先调用房产接口认证,认证状态默认未认证
		// if (houseInfo.getPropertyIdType() ==
		// PropertyIdTypeEnum.PROPERTY_ENUM.getValue()
		// || houseInfo.getPropertyIdType() ==
		// PropertyIdTypeEnum.USUS_ENUM.getValue()) {
		// houseInfo.setCertStatus(HouseCertStatusEnum.UNCERT_ENUM.getValue());
		// } else {//是其他，默认认证状态默认已认证，审核状态默认待审核，走审核接口
		// houseInfo.setCertStatus(HouseCertStatusEnum.YES_ENUM.getValue());
		houseInfo.setAuditStatus(HouseAuditStatusEnum.UNAUDITED_ENUM.getValue());
		// }

		// 保存房源信息到记录表
		String houseLogId = this.saveHouseLog(houseId, 2, "修改");

		HouCoOwnerTemp coOwner = new HouCoOwnerTemp();
		coOwner.setHid(houseId);
		houCoOwnerTempService.deleteCoOwnerTemp(coOwner);

		// 房主信息保存到共有人临时表中
		saveCoOwnerTemp(houseId, ownerId, houseLogId, true);

		String copersonid = null;
		// 姓名数组
		String[] conameArr = request.getParameterValues("coname");
		// 证件类型数组
		String[] coidtypeArr = request.getParameterValues("coidtype");
		// 证件号数组
		String[] coidcodeArr = request.getParameterValues("coidcode");
		// 姓名非空
		if (conameArr != null && conameArr.length > 0) {
			for (int i = 0; i < conameArr.length; i++) {
				String coname = conameArr[i];
				String coidtype = coidtypeArr[i];
				String coidcode = coidcodeArr[i];
				if (coname != null && !"".equals(coname)) {
					// 如果person不存在则插入person表
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", coname);
					map.put("type", coidtype);
					map.put("code", coidcode);
					// 共有人ID数组
					List<String> isPersonList = checkPerson(map);
					// 共有人ID数组为空
					if (isPersonList == null || isPersonList.size() == 0) {
						SysPerson person = new SysPerson();
						person.setIdType(Integer.parseInt(coidtype));
						person.setIdNo(coidcode);
						person.setName(coname);
						person.setCreateTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
						person.setCertStatus(Short.valueOf((short) YesNoEnum.YES_ENUM.getValue()));
						person.setStatus(YesNoEnum.YES_ENUM.getValue());
						copersonid = person.getUUID();
						person.setId(copersonid);
						personService.insertUser(person);
					} else {
						copersonid = isPersonList.get(0);
					}

					coOwner = new HouCoOwnerTemp();
					coOwner.setCoPid(copersonid);
					coOwner.setHid(houseId);
					houCoOwnerTempService.insertSelective(coOwner);

					// 保存共有人信息到记录表
					saveCoOwnerTempLog(houseLogId, coOwner, 2, "修改");
				}
			}
		}

		// 修改房源信息
		updateHouse(houseInfo);

		HouHouseInfoAtt houseAtt = new HouHouseInfoAtt();
		houseAtt.setHid(houseId);
		houHouseInfoAttService.deleteHouseAtt(houseAtt);
		// 房源图片
		if (mulImage_path != null && mulImage_path.length > 0) {
			String[] mulImage_path_arr = mulImage_path;
			String descFileDir =PropertyUtils.getSysVal("upload.file.real.directory");

			for (int i = 0; i < mulImage_path_arr.length; i++) {
				String mulImage_path_temp = mulImage_path_arr[i];
				//房屋图片是空字符串
				if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
				}
				String path = "";
				if (mulImage_path_temp.contains("_already")) {
					path = mulImage_path_temp.split("_already")[0];
				} else {
					String srcFileName = PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp;
					String descFilePath = descFileDir + HOU_PIC_DIR + File.separator + mulImage_path_temp;
					File descFile = new File(descFilePath);
					//目标文件的文件夹是否存在，不存在则创建
					if (!descFile.getParentFile().exists()) {
						descFile.getParentFile().mkdirs();
					}
					// 复制文件
					boolean flag = FileUtils.copyFileCover(srcFileName, descFilePath, true);
					if (flag == false) {
						logger.error("HouHouseInfoService updateHouse", PropertyUtils.getErrorMsg("SYS-0008"));
						throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
					}
					
					path = mulImage_path_temp.replaceAll("\\" + File.separator, "/");

					path = HOU_PIC_DIR + "/" + path;
				}
				houseAtt = new HouHouseInfoAtt();
				houseAtt.setFilePath(path);
				houseAtt.setHid(houseId);
				houHouseInfoAttService.saveHouseAtt(houseAtt);
			}
		}

	}

	/**
	 * 检测证件编号是否存在
	 * 
	 * @param houseInfo
	 *            房源对象
	 * @return 操作状态
	 */
	public String selectCheckIdNo(HouHouseInfo houseInfo) {
		return this.houHouseInfoMapper.selectCheckIdNo(houseInfo);
	}

	/**
	 * 检测证件编号是否存在
	 * 
	 * @param houseInfo
	 * @return 操作状态
	 */
	public String selectCheckAddr(HouHouseInfo houseInfo) {
		return this.houHouseInfoMapper.selectCheckAddr(houseInfo);
	}

	/**
	 * 显示房源
	 * 
	 * @param houseInfo
	 *            房源对象
	 * @return 房源对象列表
	 */
	public List<HouHouseInfo> selectHouseInfo(HouHouseInfo houseInfo) {
		return this.houHouseInfoMapper.selectHouseInfo(houseInfo);
	}

	/**
	 * 检测共有人是否存在
	 * 
	 * @param map
	 * @return ID列表
	 */
	public List<String> checkPerson(Map<String, String> map) {
		return this.houHouseInfoMapper.checkPerson(map);
	}

	/**
	 * 添加房源 1，产权证接口认证通过的才插入房源表 2，如果房源不在房源基础表存在，插入房源基础表，否则存已经存在基础表的ID
	 * 3，产权类型选其他时，插入房源基础表，默认为新房源
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param houseInfo
	 *            房源对象
	 * @param mulImage_path
	 *            房源图片数组
	 * @return 房源ID
	 * @throws ApplicationException
	 *             添加共有人log信息失败,上传附件失败
	 */
	public String insertHouse(HttpServletRequest request, HouHouseInfo houseInfo, String[] mulImage_path)
			throws ApplicationException {

		houseInfo.setEid(UserUtils.getUser().getEmpId());
		houseInfo.setBeid(UserUtils.getUser().getEmpId());

		// 暂时先注释 若产权类型是产权或者使用权，先调用房产接口认证,认证状态默认未认证
		// if (houseInfo.getPropertyIdType() ==
		// PropertyIdTypeEnum.PROPERTY_ENUM.getValue()
		// || houseInfo.getPropertyIdType() ==
		// PropertyIdTypeEnum.USUS_ENUM.getValue()) {
		// houseInfo.setCertStatus(HouseCertStatusEnum.UNCERT_ENUM.getValue());
		// } else {//是其他，默认认证状态默认已认证，审核状态默认待审核，走审核接口
		// houseInfo.setCertStatus(HouseCertStatusEnum.YES_ENUM.getValue());
		houseInfo.setAuditStatus(HouseAuditStatusEnum.UNAUDITED_ENUM.getValue());
		// }
		// 房源来源租房管家
		houseInfo.setSource(HouseSourceEnum.ZFGJ_ENUM.getValue());
		String no = OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_HOUSE);
		houseInfo.setNo(no);
		// houseInfo.setProvince(PropertyUtil.getSysVal("system.province.code"));
		// houseInfo.setCity(PropertyUtil.getSysVal("system.city.code"));
		houseInfo.setActiveStatus(HouseActiveStatusEnum.YES_ENUM.getValue());// 默认有效
		houseInfo.setLockStatus(YesNoEnum.NO_ENUM.getValue());// 默认不锁定
		houseInfo.setDeleteFlg((short) YesNoEnum.NO_ENUM.getValue());
		houseInfo.setCollectType(YesNoEnum.NO_ENUM.getValue());// 默认未收藏
		houseInfo.preInsert();
		String houseId = houseInfo.getId();

		// 保存房源信息
		houHouseInfoMapper.insertSelective(houseInfo);
		// 保存房源信息到记录表
		String houseLogId = this.saveHouseLog(houseId, 1, "新建");
		// 房主姓名
		String houseOwnerName = houseInfo.getHouseOwnerName();
		// 房主证件类型
		Integer houseOwnerIdType = houseInfo.getHouseOwnerIdType();
		// 房主身份证号
		String houseOwnerIdNo = houseInfo.getHouseOwnerIdNo();
		/*
		 * 查询房主信息PID，如果没有则向Person表中添加一条数据
		 */
		Map<String, String> ownerMap = new HashMap<String, String>();
		ownerMap.put("name", houseOwnerName);
		ownerMap.put("type", String.valueOf(houseOwnerIdType));
		ownerMap.put("code", houseOwnerIdNo);
		// 获取共有人ID列表
		List<String> personList = checkPerson(ownerMap);
		String ownerId = "";
		// 共有人ID为空
		if (personList == null || personList.size() == 0) {
			SysPerson person = new SysPerson();
			person.setIdType(houseOwnerIdType);
			person.setIdNo(houseOwnerIdNo);
			person.setName(houseOwnerName);
			person.setCertStatus(Short.valueOf((short) YesNoEnum.YES_ENUM.getValue()));
			person.setStatus(YesNoEnum.YES_ENUM.getValue());
			person.preInsert();
			personService.insertUser(person);
			ownerId = person.getId();
		} else {
			ownerId = personList.get(0);
		}
		// 房主信息保存到共有人临时表中
		saveCoOwnerTemp(houseId, ownerId, houseLogId, true);

		// 姓名数组
		String[] conameArr = request.getParameterValues("coname");
		// 证件类型数组
		String[] coidtypeArr = request.getParameterValues("coidtype");
		// 证件号数组
		String[] coidcodeArr = request.getParameterValues("coidcode");
		// 姓名数组非空
		if (conameArr != null && conameArr.length > 0) {
			for (int i = 0; i < conameArr.length; i++) {
				String coname = conameArr[i];
				String coidtype = coidtypeArr[i];
				String coidcode = coidcodeArr[i];
				// 姓名非空
				if (coname != null && !"".equals(coname)) {
					// 如果person不存在则插入person表
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", coname);
					map.put("type", coidtype);
					map.put("code", coidcode);
					// 获取共有人ID列表
					List<String> isPersonList = checkPerson(map);
					String copersonid = "";
					// 共有人为空
					if (isPersonList == null || isPersonList.size() == 0) {
						SysPerson person = new SysPerson();
						person.setIdType(Integer.parseInt(coidtype));
						person.setIdNo(coidcode);
						person.setName(coname);
						person.setCertStatus(Short.valueOf((short) YesNoEnum.YES_ENUM.getValue()));
						person.setStatus(YesNoEnum.YES_ENUM.getValue());
						person.preInsert();
						personService.insertUser(person);
						copersonid = person.getId();
					} else {
						copersonid = isPersonList.get(0);
					}
					// 保存到共有人临时表中
					saveCoOwnerTemp(houseId, copersonid, houseLogId, true);

				}
			}
		}

		// 房源图片
		if (mulImage_path != null && mulImage_path.length > 0) {
			String[] mulImage_path_arr = mulImage_path;
			String descFileDir = PropertyUtils.getSysVal("upload.file.real.directory");

			for (int i = 0; i < mulImage_path_arr.length; i++) {
				String mulImage_path_temp = mulImage_path_arr[i];
				//房屋图片是空字符串
				if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
				}
				String srcFileName = PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp;
				String descFilePath = descFileDir + HOU_PIC_DIR + File.separator + mulImage_path_temp;
				File descFile = new File(descFilePath);
				// 目标文件的文件夹是否存在，不存在则创建
				if (!descFile.getParentFile().exists()) {
					descFile.getParentFile().mkdirs();
				}
				// 复制图片
				boolean flag = FileUtils.copyFileCover(srcFileName, descFilePath, true);
				if (flag == false) {
					logger.error("HouHouseInfoService insertHouse", "上传附件失败");
					throw new ApplicationException("上传附件失败");
				} else {

				}

				mulImage_path_temp = mulImage_path_temp.replaceAll("\\" + File.separator, "/");
				HouHouseInfoAtt houseAtt = new HouHouseInfoAtt();
				houseAtt.setFilePath(HOU_PIC_DIR + "/" + mulImage_path_temp);
				houseAtt.setHid(houseId);
				houHouseInfoAttService.saveHouseAtt(houseAtt);
			}
		}

		String entId = request.getParameter("ent_id");
		if (entId != null && !"".equals(entId)) {
			// 通过验房预约发布的房源
			HouEntrustInfo record = new HouEntrustInfo();
			record.setId(entId);
			record.setStatus(EntrustStateEnum.PUBLISH_ENUM.getValue());
			houEntrustInfoService.updateByPrimaryKeySelective(record);
		}

		return houseId;
	}

	/**
	 * 编辑房源房产证
	 * 
	 * @param houseInfo
	 *            房源对象
	 * @param mulImage_path
	 *            房源图片数组
	 * @throws ApplicationException
	 *             创建文件失败，上传附件失败
	 */
	public void updateHouseFile(HouHouseInfo houseInfo, String[] mulImage_path) throws ApplicationException {
		String houseId = houseInfo.getId();

		houseInfo.setVersion(houseInfo.getVersion() + 1);// 每次加1
		houseInfo.setAuditStatus(HouseAuditStatusEnum.UNAUDITED_ENUM.getValue());

		// 保存房源信息到记录表
		this.saveHouseLog(houseId, 2, "修改");

		// 修改房源信息
		updateHouse(houseInfo);

		HouHouseInfoAtt houseAtt = new HouHouseInfoAtt();
		houseAtt.setHid(houseId);
		// 删除图片
		houHouseInfoAttService.deleteHouseAtt(houseAtt);
		// 房源图片
		if (mulImage_path != null && mulImage_path.length > 0) {
			String[] mulImage_path_arr = mulImage_path;
			StringBuilder descFileNameBulider = new StringBuilder(PropertyUtils.getSysVal("upload.file.real.directory"));
			int fileLength = descFileNameBulider.length();

			for (int i = 0; i < mulImage_path_arr.length; i++) {
				descFileNameBulider.setLength(fileLength);
				String mulImage_path_temp = mulImage_path_arr[i];
				//房屋图片是空字符串
				if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
				}
				String path = "";
				if (mulImage_path_temp.contains("_already")) {
					path = mulImage_path_temp.split("_already")[0];
				} else {
					String srcFileName = PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp;
					descFileNameBulider.append(HOU_PIC_DIR).append(File.separator);
					int indexOf = mulImage_path_temp.lastIndexOf(File.separator);
					String fileName = null;
					if (indexOf > 0) {
						// 如果文件夹不存在 创建文件夹
						String filePath = mulImage_path_temp.substring(0, indexOf + 1);
						fileName = mulImage_path_temp.substring(indexOf);
						descFileNameBulider.append(filePath);
						File rootDirFile = new File(descFileNameBulider.toString());
						if (!rootDirFile.getParentFile().exists()) {
							rootDirFile.getParentFile().mkdirs();
						}
					} else {
						// 路径中不包含文件夹
						fileName = mulImage_path_temp;
					}
					descFileNameBulider.append(fileName);
					File rootDirFile = new File(descFileNameBulider.toString());
					if (!rootDirFile.getParentFile().exists()) {
						try {
							rootDirFile.getParentFile().createNewFile();
						} catch (IOException e) {
							logger.error("HouHouseInfoService updateHouseFile", e.getMessage());
							throw new ApplicationException("创建文件失败");
						}
					}

					boolean flag = FileUtils.copyFileCover(srcFileName, descFileNameBulider.toString(), true);
					if (flag == false) {
						throw new ApplicationException("上传附件失败");
					}

					path = mulImage_path_temp.replaceAll("\\" + File.separator, "/");
				}
				houseAtt = new HouHouseInfoAtt();
				houseAtt.setFilePath(path);
				houseAtt.setHid(houseId);
				houHouseInfoAttService.saveHouseAtt(houseAtt);
			}
		}
	}

	/**
	 * 检测版本，房源是否被别人修改
	 * 
	 * @param houseInfo
	 *            房源对象
	 * @return true:被修改过，false:未被修改过
	 */
	public boolean checkVersion(HouHouseInfo houseInfo) {
		// 查询房源信息数量
		String co = this.houHouseInfoMapper.checkVersion(houseInfo);
		// 数量等于1
		if ("1".equals(co)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取房源列表
	 * 
	 * @param beid
	 *            归属人IDSS
	 * @return 房源对象列表
	 */
	public List<HouHouseInfo> getList(String beid) {
		return houHouseInfoMapper.findList(beid);
	}

	/**
	 * 添加共有人
	 * 
	 * @param hid
	 *            房源ID
	 * @param pid
	 *            共有人ID
	 * @param houLogId
	 *            房源日志ID
	 * @throws ApplicationException
	 *             添加共有人log信息失败
	 */
	private void saveCoOwnerTemp(String hid, String pid, String houLogId, boolean addAble) throws ApplicationException {
		HouCoOwnerTemp coOwner = new HouCoOwnerTemp();
		coOwner.setCoPid(pid);
		coOwner.setHid(hid);

		houCoOwnerTempService.insertSelective(coOwner);
		// 保存共有人信息到记录表
		if (addAble) {
			saveCoOwnerTempLog(houLogId, coOwner, 1, "新建");
		}

	}

	/**
	 * 添加共有人log信息
	 * 
	 * @param houLogId
	 *            房源日志ID
	 * @param coOwnerTemp
	 *            共有人对象
	 * @param optEvent
	 *            操作事件
	 * @param dcrp
	 *            描述
	 * @throws ApplicationException
	 *             添加共有人log信息失败
	 */
	private void saveCoOwnerTempLog(String houLogId, HouCoOwnerTemp coOwnerTemp, int optEvent, String dcrp)
			throws ApplicationException {
		HouCoOwnerTempLog coOwnerTempLog = new HouCoOwnerTempLog();
		try {
			ConversionUtils.reflectionAttr(coOwnerTemp, coOwnerTempLog);
		} catch (IllegalArgumentException e) {
			logger.error("HouHouseInfoService saveCoOwnerTempLog", e.getMessage());
			throw new ApplicationException("添加共有人log信息失败");
		}
		coOwnerTempLog.setHouInfoLogId(houLogId);
		coOwnerTempLog.preInsert();
		User user = UserUtils.getUser();
		coOwnerTempLog.setOperator(user.getId());
		coOwnerTempLog.setOptEvent(optEvent);
		coOwnerTempLog.setDcrp(dcrp);
		coOwnerTempLog.setVersion(0);
		coOwnerTempLog.setDeleteFlg((short) YesNoEnum.NO_ENUM.getValue());
		coOwnerTempLog.setId(coOwnerTempLog.getUUID());
		coOwnerTempLogDao.insertSelective(coOwnerTempLog);
	}

	/**
	 * 校验persion是否存在
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param houseInfo
	 *            房源对象
	 * @param per_id
	 *            用户ID
	 * @return ture:不存在，false:存在
	 */
	public boolean checkPerson(HttpServletRequest request, HouHouseInfo houseInfo, String per_id) {
		boolean flg = true;
		// 共有人姓名
		String houseOwnerName = houseInfo.getHouseOwnerName();
		// 房主证件类型
		Integer houseOwnerIdType = houseInfo.getHouseOwnerIdType();
		// 房主身份证号
		String houseOwnerIdNo = houseInfo.getHouseOwnerIdNo();

		Map<String, String> ownerMap = new HashMap<String, String>();
		ownerMap.put("name", houseOwnerName);
		ownerMap.put("type", String.valueOf(houseOwnerIdType));
		ownerMap.put("code", houseOwnerIdNo);
		// 共有人ID列表
		List<String> personList = checkPerson(ownerMap);

		String personid = "";
		// 共有人非空
		if (personList != null && personList.size() != 0) {
			personid = personList.get(0);
			if (per_id.equals(personid)) {
				flg = false;
			}
		}

		// 姓名数组
		String[] conameArr = request.getParameterValues("coname");
		// 证件类型数组
		String[] coidtypeArr = request.getParameterValues("coidtype");
		// 证件号数组
		String[] coidcodeArr = request.getParameterValues("coidcode");
		if (conameArr != null && conameArr.length > 0) {
			for (int i = 0; i < conameArr.length; i++) {
				String coname = conameArr[i];
				String coidtype = coidtypeArr[i];
				String coidcode = coidcodeArr[i];
				// 姓名非空
				if (coname != null && !"".equals(coname)) {
					// 如果person不存在则插入person表
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", coname);
					map.put("type", coidtype);
					map.put("code", coidcode);
					// 共有人ID列表
					List<String> isPersonList = checkPerson(map);
					// 共有人非空
					if (isPersonList != null && isPersonList.size() != 0) {
						personid = isPersonList.get(0);
						if (per_id.equals(personid)) {
							flg = false;
						}
					}
				}
			}
		}
		return flg;
	}
	
	/**
	 * 验真房源修改房源certStatus状态
	 * @param id 房源id
	 */
	public int updateTruthHouse(String id){
		HouHouseInfo info = new HouHouseInfo();
		info.setId(id);
		info.setCertStatus(HouseCertStatusEnum.UNCERT_ENUM.getValue());
		info.preUpdate();
		return houHouseInfoMapper.updateByPrimaryKeySelective(info);
	}
	
}
