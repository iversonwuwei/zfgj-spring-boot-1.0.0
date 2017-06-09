/**
* @name: HouLeaseInfoController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 出租信息增删改查等操作
*
* @version: 1.0
* @date : 2015年12月2日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年12月2日       fancy        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.submission.annotation.AvoidDuplicateSubmission;
import com.dlfc.admin.common.submission.token.TokenHelper;
import com.dlfc.admin.common.utils.JedisUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.HouLeaseRoomInfo;
import com.dlfc.zfgj.entity.HouLeaseRoomInfoList;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysTradeAreas;
import com.dlfc.zfgj.entity.SysTransportStation;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.HouHouseInfoService;
import com.dlfc.zfgj.service.HouLeaseInfoService;
import com.dlfc.zfgj.service.HouLeaseRoomInfoService;
import com.dlfc.zfgj.service.SysAreaAreasService;
import com.dlfc.zfgj.service.SysCodeService;
import com.dlfc.zfgj.service.SysInfoAttService;
import com.dlfc.zfgj.service.SysTradeAreasService;
import com.dlfc.zfgj.service.SysTransportStationService;

/**
 * @name: HouLeaseInfoController
 * @description: 出租信息增删改查等操作
 * @version 1.0
 * @author fancy
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/lease")
public class HouLeaseInfoController extends BaseController {
	/** 出租信息页面根目录 */
	private static final String JSP_DIR = "dlfc/houseinfo";
	/** 系统codeservice */
	@Autowired
	private SysCodeService sysCodeService;
	/** 区域信息service */
	@Autowired
	private SysAreaAreasService sysAreaAreasService;
	/** 出租信息service */
	@Autowired
	private HouLeaseInfoService houLeaseInfoService;
	/** 商圈信息service */
	@Autowired
	private SysTradeAreasService sysTradeAreasService;
	/** 图片信息service */
	@Autowired
	private SysInfoAttService sysInfoAttService;
	/** 出租信息房间service */
	@Autowired
	private HouLeaseRoomInfoService houLeaseRoomInfoService;
	/** 地铁线路站点service */
	@Autowired
	private SysTransportStationService sysTransportStationService;
	/** 房源信息service */
	@Autowired
	private HouHouseInfoService houHouseInfoService;
	/** 经纪人信息service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;

	/**
	 * 跳转到添加出租信息页面
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RequestMapping(value = "/toadd", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String toAddLease(Model model, RedirectAttributes redirectAttributes) {
		// 公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		// 设置公共属性
		setCommonAttr();

		String hid = getRequest().getParameter("id");
		HouLeaseInfo leaseInfo = new HouLeaseInfo();
		leaseInfo.setHid(hid);
		leaseInfo.setReleaseStatus(0);
		leaseInfo.setHouseType(1);
		leaseInfo.setLeaseMode(1);
		leaseInfo.setDecor("1");
		leaseInfo.setDecorTime("1");

		HouLeaseRoomInfoList roomInfos = new HouLeaseRoomInfoList();
		model.addAttribute("leaseInfo", leaseInfo);
		model.addAttribute("roomInfos", roomInfos);
		// 查询房源
		HouHouseInfo houHouseInfo = houHouseInfoService.findById(hid);
		if (houHouseInfo != null) {
			// 建筑面积
			model.addAttribute("buildingArea", houHouseInfo.getBuildingArea());
			model.addAttribute("houseAddr", houHouseInfo.getHouseAddr());
		}
		// 如果存在有效合同，查询出合同截至时间 现在房源和三方合同的关联取消了故注释掉代码
		// List<ConContractTp> list = conService.getEndTime(hid);
		// if(list.size() > 0 ){
		// ConContractTp contract = list.get(0);
		// Date endTime = contract.getEndTime();
		//
		// model.addAttribute("endTime", endTime);
		// }

		return JSP_DIR + "/addlease";
	}

	/**
	 * 查询该房源是否有出租信息
	 * 
	 * @param hid
	 *            房源ID
	 * @return 1:有出租信息 0:无出租信息
	 * @author HAN.JIAQI
	 */
	@RequestMapping(value = { "cklUnique" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult checkLeaseInfoUnique(String hid) {
		// 房源ID为空
		if (StringUtils.isBlank(hid)) {
			return ajaxFail();
		}
		// 标记是否有出租信息
		String flag = "0";
		// 获取出租信息列表
		List<HouLeaseInfo> houLeaseInfo = houLeaseInfoService.selectLeaseInInfo(hid);

		// 如果存在出租信息标记为1
		if (houLeaseInfo.size() > 0) {
			flag = "1";
		} else {
			flag = "0";
		}
		return ajaxSuccess(flag);
	}

	/**
	 * 添加出租信息保存
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param leaseInfo
	 *            出租信息对象
	 * @param roomInfos
	 *            房间对象
 	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public String saveAll(Model model, HouLeaseInfo leaseInfo, HouLeaseRoomInfoList roomInfos,RedirectAttributes redirectAttributes) {
		// 参数校验
		if (StringUtils.isBlank(leaseInfo.getHid())) {
			return "redirect:" + adminPath + "/house/list?repage";
		}
		List<HouLeaseInfo> houLeaseInfo = houLeaseInfoService.selectLeaseInInfo(leaseInfo.getHid());

		// 如果当前房源存在出租信息,返回到房源列表页
		if (houLeaseInfo.size() != 0) {
			return "redirect:" + adminPath + "/house/list?repage";
		}
		// 图片地址
		String mainimgId_path = (String) getRequestParameter("mainimgId_path");
		// 图片地址
		String layoutimgId_path = (String) getRequestParameter("layoutimgId_path");
		// 图片地址数组
		String[] mulImage_path = getRequest().getParameterValues("pics");
		// 描述
		String description = (String) getRequestParameter("description");
		// description不为空，替换字符
		if (StringUtils.isNotEmpty(description)) {
			description = description.replaceAll("\r\n", "");
		} 
		leaseInfo.setDescription(description);

		try {
			// 参数校验
			addCheck(leaseInfo);
			List<String[]> list = new ArrayList<String[]>();
			Integer leaseMode = leaseInfo.getLeaseMode();
			if (leaseMode.intValue() == 2) {
				int roomSize = roomInfos.getRoomInfos().size();
				// 获取符合条件的房间
				roomInfos = roomCheck(roomInfos);
				int size = 0;
				for (int i = 0; i < roomSize; i++) {
					String[] room_path = getRequest().getParameterValues("pics" + i);
					if (room_path != null && room_path.length > 0) {
						list.add(size, room_path);
						size += 1;
					}
				}
			}
			// 保存出租信息
			houLeaseInfoService.saveAll(leaseInfo, mainimgId_path, layoutimgId_path, mulImage_path, roomInfos, list);
		} catch (ApplicationException ex) {
			setCommonAttr();
			addError(model, ex.getMessage());
			model.addAttribute("mainimgId_path", mainimgId_path);
			model.addAttribute("layoutimgId_path", layoutimgId_path);
			model.addAttribute("leaseInfo", leaseInfo);
			TokenHelper.getInstance().setToken(this.getRequest());
			return JSP_DIR + "/addlease";
		}
		redirectAttributes.addFlashAttribute("sucessMsg", "添加出租信息成功！");
		return "redirect:" + adminPath + "/house/list?repage";
	}

	/**
	 * 跳转到添加编辑出租信息页面
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = "/toedit", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String toEditLease(Model model, RedirectAttributes redirectAttributes) {
		// 判断公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		// 设置公共属性
		setCommonAttr();
		// 房源ID
		String hid = getRequest().getParameter("id");
		// 出租信息列表
		List<HouLeaseInfo> leaseInfoList = houLeaseInfoService.selectLeaseInInfo(hid);
		// 未超找到出租信息
		if (leaseInfoList.size() == 0) {
			addError(redirectAttributes, "无出租信息");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		HouLeaseInfo leaseInfo = leaseInfoList.get(0);
		leaseInfo.setHid(hid);
		model.addAttribute("leaseInfo", leaseInfo);
		// 获取出租信息图片对象列表
		List<SysInfoAtt> atts = sysInfoAttService.findList(leaseInfo.getId(), 9, 10);

		for (int i = 0; i < atts.size(); i++) {
			SysInfoAtt att = atts.get(i);
			Integer fileType = att.getFileType();
			String filePath = att.getFilePath();
			// 首图
			if (fileType.intValue() == 9) {
				model.addAttribute("home_page", filePath);
			} else {
				model.addAttribute("apartment_layout", filePath);
			}
		}

		String leaseId = leaseInfo.getId();
		// 图片列表
		List<SysInfoAtt> sysInfoAtt_house = sysInfoAttService.findList(leaseInfo.getId(), 11);
		for (int i = 0; i < sysInfoAtt_house.size(); i++) {
			SysInfoAtt att = sysInfoAtt_house.get(i);
			if (att.getSort() != null && att.getSort() == 12) {
				model.addAttribute("mainimgId_path", att.getFilePath());
			}
		}
		model.addAttribute("sysInfoAtt_house", sysInfoAtt_house);
		model.addAttribute("sysInfoAtt_house_size", sysInfoAtt_house.size());

		// 房间列表
		List<HouLeaseRoomInfo> roomInfos = houLeaseRoomInfoService.selectRoom(leaseId);

		for (int i = 0; i < roomInfos.size(); i++) {
			HouLeaseRoomInfo roomInfo = roomInfos.get(i);
			// 房间图片
			List<SysInfoAtt> sysInfoAtt_room = sysInfoAttService.findList(roomInfo.getId(), 12);
			roomInfo.setAtts(sysInfoAtt_room);
		}

		// 查询房源
		HouHouseInfo houHouseInfo = houHouseInfoService.findById(hid);
		if (houHouseInfo != null) {
			// 建筑面积
			model.addAttribute("buildingArea", houHouseInfo.getBuildingArea());
			model.addAttribute("houseAddr", houHouseInfo.getHouseAddr());
		}

		model.addAttribute("roomInfos", roomInfos);
		model.addAttribute("roomInfos_size", roomInfos.size());

		// 如果存在有效合同，查询出合同截至时间 现在房源和三方合同的关联取消了故注释掉代码
		// List<ConContractTp> list = conService.getEndTime(hid);
		// if(list.size() > 0 ){
		// ConContractTp contract = list.get(0);
		// Date endTime = contract.getEndTime();
		//
		// model.addAttribute("endTime", endTime);
		// }

		return JSP_DIR + "/editlease";
	}

	/**
	 * 编辑出租信息
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param leaseInfo
	 *            出租信息对象
	 * @param roomInfos
	 *            房间信息对象列表
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editLease(Model model, HouLeaseInfo leaseInfo, HouLeaseRoomInfoList roomInfos,RedirectAttributes redirectAttributes) {
		// 图片地址
		String mainimgId_path = (String) getRequestParameter("mainimgId_path");
		// 图片地址
		String layoutimgId_path = (String) getRequestParameter("layoutimgId_path");
		// 图片地址数组
		String[] mulImage_path = getRequest().getParameterValues("pics");
		// 描述
		String description = (String) getRequestParameter("description");
		// 描述非空
		if (description != null && !"".equals(description)) {
			description = description.replaceAll("\r\n", "");
		} 
		leaseInfo.setDescription(description);

		try {
			// 参数校验
			addCheck(leaseInfo);
			List<String[]> room_list = new ArrayList<String[]>();
			Integer leaseMode = leaseInfo.getLeaseMode();
			if (leaseMode.intValue() == 2) {
				int roomSize = roomInfos.getRoomInfos().size();
				roomInfos = roomCheck(roomInfos);
				int size = 0;
				for (int i = 0; i < roomSize; i++) {
					String[] room_path = getRequest().getParameterValues("pics" + i);
					if (room_path != null && room_path.length > 0) {
						room_list.add(size, room_path);
						size += 1;
					}
				}
			}
			houLeaseInfoService.updateLease(leaseInfo, mainimgId_path, layoutimgId_path, mulImage_path, roomInfos,
					room_list);
			// 修改出租信息，清除对应缓存。
			if (leaseInfo != null && StringUtils.isNotBlank(leaseInfo.getId())) {
				JedisUtils.del("www.housecenter.cn/lease/info?id=" + leaseInfo.getId());
			}
		} catch (ApplicationException ex) {
			setCommonAttr();
			addError(model, ex.getMessage());
			model.addAttribute("mainimgId_path", mainimgId_path);
			model.addAttribute("layoutimgId_path", layoutimgId_path);
			model.addAttribute("leaseInfo", leaseInfo);
			return JSP_DIR + "/editlease";
		}
		addMessage(redirectAttributes, "修改出租信息成功！");
		return "redirect:" + adminPath + "/house/list?repage";
	}

	/**
	 * 获取商圈
	 * 
	 * @return ajaxSuccess:商圈信息
	 */
	@RequestMapping(value = { "getTrade", "" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getTrade() {
		// 区域
		String area = (String) getRequestParameter("area");
		// 商圈
		String dist = (String) getRequestParameter("dist");
		// 商圈列表
		List<SysTradeAreas> trade_areas = sysTradeAreasService.getTrade(area);
		// 返回信息
		String str = "";
		if (trade_areas.size() > 0) {
			// 组装选中
			for (int i = 0; i < trade_areas.size(); i++) {
				String trade = trade_areas.get(i).getTradeId();
				str += "<option value='" + trade + "' ";
				if (dist != null && trade.equals(dist)) {
					str += "selected";
				}

				str += " >" + trade_areas.get(i).getName() + "</option>";
			}
		} else {
			str = "<option value='0'>" + "暂无数据" + "</option>";
		}

		return ajaxSuccess(str);
	}

	/**
	 * 获取符合条件的房间
	 * 
	 * @param roomInfos
	 *            房间列表
	 * @return 房间列表
	 */
	private HouLeaseRoomInfoList roomCheck(HouLeaseRoomInfoList roomInfos) {
		// 获取房间列表
		List<HouLeaseRoomInfo> houLeaseRoomInfos = roomInfos.getRoomInfos();
		// 循环房间信息删除不符合条件的房间
		for (int i = 0; i < houLeaseRoomInfos.size(); i++) {
			HouLeaseRoomInfo info = houLeaseRoomInfos.get(i);

			String room = info.getRoom();
			BigDecimal rent = info.getRent();
			String area = info.getArea();
			// 移除不符合条件的房间
			if (room == null || rent == null || area == null || "".equals(room) || "".equals(area)) {
				houLeaseRoomInfos.remove(i);
				i--;
			} else {// 保存信息
				Object description = (info.getDescription());
				if (description != null && !"".equals(description)) {
					description = ((String) description).replaceAll("\r\n", "");
					info.setDescription(description);
				}
			}
		}

		return roomInfos;
	}

	/**
	 * 检测必填项
	 * 
	 * @param leaseInfo
	 *            出租信息对象
	 * @throws ApplicationException
	 *             出租信息对象校验错误
	 */
	public void addCheck(HouLeaseInfo leaseInfo) throws ApplicationException {
		// 判断公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0016"));
		}
		// Integer houseType = leaseInfo.getHouseType();
		// 出租类型
		Integer leaseMode = leaseInfo.getLeaseMode();
		// 区域
		String districtArea = leaseInfo.getDistrictArea();
		// 商圈
		String districtDist = leaseInfo.getDistrictDist();
		// String noBuildingNo = leaseInfo.getNoBuildingNo();
		// String noUnit = leaseInfo.getNoUnit();
		// String noRoom = leaseInfo.getNoRoom();
		// 楼层总数
		String floorNum = leaseInfo.getFloorNum();
		// 创建时间
		String buildTime = leaseInfo.getBuildTime();
		// 房间数
		String aparmentRoom = leaseInfo.getAparmentRoom();
		// 客厅数
		String aparmentHall = leaseInfo.getAparmentHall();
		// 卫生间数量
		String aparmentToilet = leaseInfo.getAparmentToilet();
		// 朝向
		String orientation = leaseInfo.getOrientation();
		// 装修
		String decor = leaseInfo.getDecor();
		// 装修时间
		String decorTime = leaseInfo.getDecorTime();

		Pattern pattern = Pattern.compile("[0-9]{1,10}");
		// if
		// (!pattern.matcher(noBuildingNo).matches()||!pattern.matcher(noUnit).matches()||!pattern.matcher(noRoom).matches())
		// {
		// throw new ApplicationException("楼号必须为纯数字");
		// }
		// 参数校验
		if (StringUtils.isNotEmpty(floorNum) && !pattern.matcher(floorNum).matches()) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0032"));
		}
		if (!pattern.matcher(aparmentRoom).matches() || !pattern.matcher(aparmentHall).matches()
				|| !pattern.matcher(aparmentToilet).matches()) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0033"));
		}

		// if(houseType==null||"".equals(houseType)){
		// throw new ApplicationException("物业类型必填");
		// }
		if (leaseMode == null || (leaseMode != null && leaseMode.intValue() == 0)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0034"));
		}
		if (StringUtils.isEmpty(districtArea) || StringUtils.isEmpty(districtDist)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0035"));
		}
		if (StringUtils.isEmpty(buildTime)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0036"));
		}
		if (StringUtils.isEmpty(orientation)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0037"));
		}
		if (StringUtils.isEmpty(decor)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0038"));
		}
		if (StringUtils.isEmpty(decorTime)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0038"));
		}
	}

	/**
	 * 设置公共属性
	 * 
	 */
	public void setCommonAttr() {
		// 房屋类型
		List<SysCode> house_type = sysCodeService.getCodeByType("house_type");
		// 房屋租金
		List<SysCode> rent_type = sysCodeService.getCodeByType("rent_type");
		// 建筑时间
		List<SysCode> build_time = sysCodeService.getCodeByType("build_time");
		// 房屋朝向
		List<SysCode> hou_orientation = sysCodeService.getCodeByType("hou_orientation");
		// 装修
		List<SysCode> hou_decor = sysCodeService.getCodeByType("hou_decor");
		// 装修时间
		List<SysCode> decor_time = sysCodeService.getCodeByType("decor_time");
		// 房屋设施
		List<SysCode> house_facilities = sysCodeService.getCodeByType("house_facilities");
		// 配套家店
		List<SysCode> accessory_shop = sysCodeService.getCodeByType("accessory_shop");
		// 房源特色
		List<SysCode> house_features = sysCodeService.getCodeByType("house_features");
		// List<SysCode> hou_surroundings =
		// 周边环境
		// sysCodeService.getCodeByType("hou_surroundings");
		// 居住要求
		List<SysCode> rental_require = sysCodeService.getCodeByType("rental_require");
		// 房间特色
		List<SysCode> room_features = sysCodeService.getCodeByType("room_features");
		// 区域
		List<SysAreaAreas> district_list = sysAreaAreasService.getAreaName(PropertyUtils.getSysVal("system.city.code"));
		// 商圈
		List<SysTradeAreas> trade_areas = sysTradeAreasService.getTrade("210202");
		// 房主承担费用
		List<SysCode> lessor_bear = sysCodeService.getCodeByType("lessor_bear");
		// 房屋租金押X
		List<SysCode> rent_deposit = sysCodeService.getCodeByType("rent_deposit");
		// 房屋租金付X
		List<SysCode> rent_payment = sysCodeService.getCodeByType("rent_payment");

		getRequest().setAttribute("house_type", house_type);
		getRequest().setAttribute("rent_type", rent_type);
		getRequest().setAttribute("build_time", build_time);
		getRequest().setAttribute("hou_orientation", hou_orientation);
		getRequest().setAttribute("hou_decor", hou_decor);
		getRequest().setAttribute("decor_time", decor_time);
		getRequest().setAttribute("house_facilities", house_facilities);
		getRequest().setAttribute("accessory_shop", accessory_shop);
		getRequest().setAttribute("house_features", house_features);
		getRequest().setAttribute("lessor_bear", lessor_bear);
		// getRequest().setAttribute("hou_surroundings", hou_surroundings);
		getRequest().setAttribute("rental_require", rental_require);
		getRequest().setAttribute("room_features", room_features);
		getRequest().setAttribute("district_list", district_list);
		getRequest().setAttribute("trade_areas", trade_areas);
		getRequest().setAttribute("rent_deposit", rent_deposit);
		getRequest().setAttribute("rent_payment", rent_payment);
	}

	/**
	 * 根据商圈获取地铁
	 * 
	 * @return ajaxSuccess:地铁信息
	 */
	@RequestMapping(value = { "sta" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getStation() {
		// 线路
		String tradeId = (String) getRequestParameter("trade_id");
		// 站点
		String station = (String) getRequestParameter("station");
		// 站点列表
		List<SysTransportStation> trade_areas = sysTransportStationService.selectStation(tradeId);
		// 返回信息
		String str = "";
		if (trade_areas.size() > 0) {
			str = "<option></option>";
			// 添加选中
			for (int i = 0; i < trade_areas.size(); i++) {
				str += "<option value='" + trade_areas.get(i).getId() + "' ";
				if (station != null && station.equals(trade_areas.get(i).getId())) {
					str += "selected";
				}

				str += "   >" + trade_areas.get(i).getName() + "</option>";
			}
		} else {
			str = "<option>" + "暂无数据" + "</option>";
		}

		return ajaxSuccess(str);
	}
}
