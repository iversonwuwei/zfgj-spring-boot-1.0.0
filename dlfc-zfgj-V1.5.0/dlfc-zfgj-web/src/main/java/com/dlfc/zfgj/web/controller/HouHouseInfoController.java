/**
* @name: HouHouseInfoController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 房源增删改查，数据校验等
*
* @version: 1.0
* @date : 2016年4月28日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年4月28日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.submission.annotation.AvoidDuplicateSubmission;
import com.dlfc.admin.common.submission.token.TokenHelper;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.MD5;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.HouCoOwner;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouHouseInfoAtt;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.HouHouseInfoAttService;
import com.dlfc.zfgj.service.HouHouseInfoService;
import com.dlfc.zfgj.service.HouLeaseInfoService;
import com.dlfc.zfgj.service.HouLeaseRoomInfoService;
import com.dlfc.zfgj.service.SysAreaAreasService;
import com.dlfc.zfgj.service.SysCodeService;
import com.dlfc.zfgj.service.SysPersonService;
import com.dlfc.zfgj.service.UsrHouCollectionService;
import com.housecenter.dlfc.zfgj.common.enums.HouseReleaseStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.PropertyIdTypeEnum;
import com.housecenter.dlfc.zfgj.common.persistence.Page;

import tk.mybatis.mapper.util.StringUtil;

/**
 * @name: HouHouseInfoController
 * @description: 房源增删改查，数据校验等
 * @version 1.0
 * @author yuanjw
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/house")
public class HouHouseInfoController extends BaseController {
	/** 房源页面根目录 */
	private static final String JSP_DIR = "dlfc/houseinfo";
	/** 建筑面积最大值 */
	private static final int BUILDING_AREA_MAX = 99999999;
	/** 建筑面积最小值 */
	private static final int BUILDING_AREA_MIN = 7;
	/** 静态变量，房源session的key */
	private static final String KEY_HOUSEINFO_LIST_CON_SESSION = "KEY_HOUSEINFO_LIST_CON_SESSION";

	/** 房源信息service */
	@Autowired
	private HouHouseInfoService houseInfoService;
	/** 房源图片service */
	@Autowired
	private HouHouseInfoAttService houHouseInfoAttService;
	/** 系统code表service */
	@Autowired
	private SysCodeService sysCodeService;
	/** 城市区域service */
	@Autowired
	private SysAreaAreasService sysAreaAreasService;
	/** 经纪人service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;
	/** 出租信息service */
	@Autowired
	private HouLeaseInfoService houLeaseInfoService;
	/** 出租信息房间service */
	@Autowired
	private HouLeaseRoomInfoService houLeaseRoomInfoService;
	/** 用户收藏出租信息service */
	@Autowired
	private UsrHouCollectionService UsrHouCollectionService;
	/** 用户基础信息service */
	@Autowired
	private SysPersonService sysPersonService;

	/**
	 * 房源列表展示
	 * @param houseInfo 房源du
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:view")
	@RequestMapping(value = { "list", "" })
	public String list(HouHouseInfo houseInfo, HttpServletRequest request, HttpServletResponse response) {
		// 从其他页面返回本页面时，从session里取出本页面的查询参数
		if (isRepage()) {
			houseInfo = (HouHouseInfo) getSessionAttribute(KEY_HOUSEINFO_LIST_CON_SESSION);
		}

		// 产权证件ID
		String propertyId = (String) getRequestParameter("propertyId");
		// 出租状态
		String releaseStatus = (String) getRequestParameter("releaseStatus");
		// 合同签约状态
		String signStatus = (String) getRequestParameter("signStatus");
		// 创建时间
		String butimesort = (String) getRequestParameter("bs");
		// 全部房源 or 关注房源
		String status = (String) getRequestParameter("st");
		// 个人房源 or 部门房源
		String ms = (String) getRequestParameter("ms");
		// 房源编号
		String searchInfo = (String) getRequestParameter("searchInfo");
		// 房源信息对象为空
		if (houseInfo == null) {
			houseInfo = new HouHouseInfo();
		}
		// 当前页
		String currentPage = (String) getRequestParameter("currentPage");

		/**
		 * 签约状态需要显示； 1、如果为整租合同，在合同到期前都显示“签满”状态，系统判断合同到期时间，如为到期30天内则可签订新合同（含续约），
		 * 如超过30天则不能签订新合同； 2、分租情况根据目前实际出租方式暂无法判定“签满”状态。（另分租要求总出租面积不能大于房源总面积的70%）
		 */

		// status非空
		if (StringUtils.isNoneBlank(status)) {
			houseInfo.setActiveStatus(Integer.parseInt(status));
		}
		houseInfo.setCurrentPage(currentPage);
		// 排序
		String sort = "";
		if ("asc".equalsIgnoreCase(butimesort)) {
			sort += ",A.CREATE_TIME";
		} else if ("desc".equalsIgnoreCase(butimesort)) {
			sort += ",A.CREATE_TIME DESC";
		}
		// 截取字符串
		if (!"".equals(sort)) {
			sort = sort.substring(1);
		}
		// 判断产权证件ID是否为空
		if (propertyId != null && !"".equals(propertyId)) {
			houseInfo.setPropertyIdType(Integer.parseInt(propertyId));
		}
		houseInfo.setReleaseStatus(releaseStatus);
		houseInfo.setSignStatus(signStatus);
		houseInfo.setOrderBy(sort);
		// 获取角色列表
		List<Role> myRoles = UserUtils.getUser().getRoleList();
		String enname = "";
		String roleType = "";
		// 判断登录用户是否是经理
		if (myRoles != null && myRoles.size() > 0) {
			for (int i = 0; i < myRoles.size(); i++) {
				if ("comp-role".equals(myRoles.get(i).getRoleType())
						|| "assignment".equals(myRoles.get(i).getRoleType())) {
					roleType = myRoles.get(i).getRoleType();
				}
				if ("manager".equals(myRoles.get(i).getEnname())) {
					enname = myRoles.get(i).getEnname();
				}
			}
		}
		setRequestAttribute("enname", enname);
		setRequestAttribute("roleType", roleType);

		// 部门房源，并且是经理才能看所有房源
		if (("manager".equals(enname) && StringUtils.isNoneBlank(ms) && "1".equals(ms)) || "comp-role".equals(roleType)
				|| "assignment".equals(roleType)) {
			// 查看权限
			houseInfo.setRoleType("1");
			String eid = agtEmpInfoService.getEmpIdStr("emp:house:view");
			houseInfo.setBeid(eid);
		} else {
			houseInfo.setBeid("'" + UserUtils.getUser().getEmpId() + "'");
		}

		// houseInfo.setUid(UserUtils.getUser().getId());

		houseInfo.setSearchInfo(searchInfo);
		// 房源信息页面对象
		BasePage<HouHouseInfo> page = houseInfoService.selectPage(new Page<HouHouseInfo>(request, response), houseInfo);

		setRequestAttribute("page", page);
		setRequestAttribute("st", status);
		setRequestAttribute("ms", ms);
		setRequestAttribute("searchInfo", searchInfo);
		setRequestAttribute("bs", butimesort);

		setRequestAttribute("property_id", propertyId);
		setRequestAttribute("release_status", releaseStatus);
		setRequestAttribute("sign_status", signStatus);

		// 产权状态
		List<SysCode> property_list = sysCodeService.getCodeByType("property_id_type");
		getRequest().setAttribute("property_list", property_list);

		// 将查询参数存入session，以便当从其他页面返回时获得这些参数
		setSessionAttribute(KEY_HOUSEINFO_LIST_CON_SESSION, houseInfo);

		// 根据permission+role获取当前登录经纪人scope
		String scope = agtEmpInfoService.getScope("emp:house:view");
		setRequestAttribute("scope", scope);

		// 返回房源
		String ret = "/houselist";
		// 判断角色类型 comp-role， assignment是管理角色
		if ("comp-role".equals(roleType) || "assignment".equals(roleType)) {
			ret = "/managerlist";
			setRequestAttribute("comp_flg", "manager");
			// ms等于“1”代表部门权限
		} else if (StringUtils.isNoneBlank(ms) && "1".equals(ms)) {
			ret = "/managerlist";
		}
		// 获取是否锁定
		if (!"comp-role".equals(roleType) && !"assignment".equals(roleType)) {
			// 公司是否锁定
			boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
			setRequestAttribute("lockFlg", lockFlg);
		} else {
			// 公司是否锁定
			boolean lockFlg = agtEmpInfoService.isCompLockedBySysUser(UserUtils.getUser().getId());
			setRequestAttribute("lockFlg", lockFlg);
		}

		return JSP_DIR + ret;
	}

	/**
	 * 想租
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求到房源列表
	 */
	@RequestMapping(value = "/col", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String doWant(Model model, RedirectAttributes redirectAttributes) {
		// 房源id
		String hid = getRequest().getParameter("hid");
		// 收藏类型
		String collectType = (String) getRequestParameter("collectType");
		// st = 1 代表收藏房源 ""代表全部房源
		String st = (String) getRequestParameter("st");
		// 权限 ms=1 代表部门权限
		String ms = (String) getRequestParameter("ms");

		// 获取收藏房源数量
		if (collectType == null || "".equals(collectType)) {
			int current = UsrHouCollectionService.selectCurrent(hid);
			// 收藏房源数量大于零
			if (current > 0) {
				addError(redirectAttributes, "不能重复想租！");
				return "redirect:" + adminPath + "/house/list?repage";
			}
		}
		// 收藏
		UsrHouCollectionService.doWant(hid, collectType);
		//返回参数
		String tmp = "";
		if ("1".equals(st)) {
			tmp = "&st=1";
		} else if ("1".equals(ms)) {
			tmp = "&ms=1";
		}

		return "redirect:" + adminPath + "/house/list?repage" + tmp;
	}

	/**
	 * 删除房源时对房源校验
	 * @return AjaxResult:操作成功，ajaxFail:操作失败
	 */
	@RequiresPermissions("emp:house:del")
	@RequestMapping(value = { "delc", "" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult delCheck() {
		// 返回值字符串
		String ret = "";
		// 房源id
		String checkHid = (String) getRequestParameter("hid");
		// 截取房源id
		String[] hisArr = checkHid.split("_");

		HouHouseInfo houseInfo = new HouHouseInfo();
		for (int i = 0; i < hisArr.length; i++) {
			String hid = hisArr[i].split(",")[0];
			houseInfo.setId(hid);
			// 获取房源列表
			List<HouHouseInfo> list = houseInfoService.delCheck(hid);
			if (list.size() > 0) {
				houseInfo = list.get(0);
				// 所属人ID
				String beid = houseInfo.getBeid();
				List<Role> myRoles = UserUtils.getUser().getRoleList();
				// 角色
				String roleType = "";
				// 判断登录用户是否是经理
				if (myRoles != null && myRoles.size() > 0) {
					for (int j = 0; j < myRoles.size(); j++) {
						if ("comp-role".equals(myRoles.get(j).getRoleType())
								|| "assignment".equals(myRoles.get(j).getRoleType())) {
							roleType = myRoles.get(j).getRoleType();
						}
					}
				}
				// 如果是管理员登录，不需要校验数据权限
				if (!"comp-role".equals(roleType) && !"assignment".equals(roleType)) {
					boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
					if (lockFlg) {
						return ajaxFail("公司已锁定！");
					}
					ret = checkDataPermission(beid, "emp:house:del");
				} else {
					// 公司是否锁定
					boolean lockFlg = agtEmpInfoService.isCompLockedBySysUser(UserUtils.getUser().getId());
					if (lockFlg) {
						return ajaxFail("公司已锁定！");
					}
				}
			} else {
				ret = "存在有效合同，不能删除！";
			}
		}
		// 返回值字符串为空，返回正确信息
		if ("".equals(ret)) {
			return ajaxSuccess("成功");
		}
		return ajaxFail(ret);
	}

	/**
	 * 删除房源-经纪人 经纪人 +房源代理状态：（未代理||已过期）+无有效合同 -->房源禁用
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求到房源列表
	 */
	@RequiresPermissions("emp:house:del")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String del(Model model, RedirectAttributes redirectAttributes) {
		// 房源id
		String checkHid = (String) getRequestParameter("hid");
		// 截取房源id
		String[] hisArr = checkHid.split("_");
		HouHouseInfo houseInfo = new HouHouseInfo();
		for (int i = 0; i < hisArr.length; i++) {
			String hid = hisArr[i].split(",")[0];
			houseInfo.setId(hid);
			// 检测房源是否在合同表存在
			List<HouHouseInfo> list = houseInfoService.delCheck(hid);
			if (list.size() > 0) {
				houseInfo = list.get(0);
				String eid = houseInfo.getEid();
				String ret = "";
				// 角色列表
				List<Role> myRoles = UserUtils.getUser().getRoleList();
				// 角色
				String roleType = "";
				// 判断登录用户是否是经理
				if (myRoles != null && myRoles.size() > 0) {
					for (int j = 0; j < myRoles.size(); j++) {
						if ("comp-role".equals(myRoles.get(j).getRoleType())
								|| "assignment".equals(myRoles.get(j).getRoleType())) {
							roleType = myRoles.get(j).getRoleType();
						}
					}
				}
				// 如果是管理员登录，不需要校验数据权限
				if (!"comp-role".equals(roleType) && !"assignment".equals(roleType)) {
					boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
					if (lockFlg) {
						addError(redirectAttributes, "公司已锁定！");
						return "redirect:" + adminPath + "/house/list?repage";
					}
					ret = checkDataPermission(eid, "emp:house:del");
				} else {
					boolean lockFlg = agtEmpInfoService.isCompLockedBySysUser(UserUtils.getUser().getId());
					if (lockFlg) {
						addError(redirectAttributes, "公司已锁定！");
						return "redirect:" + adminPath + "/house/list?repage";
					}
				}
				// 非当前经纪人发起的房源
				if (!"".equals(ret)) {
					addError(redirectAttributes, ret);
					return "redirect:" + adminPath + "/house/list?repage";
				}
			} else {
				addError(redirectAttributes, "已存在有效合同，不能删除！");
				return "redirect:" + adminPath + "/house/list?repage";
			}
		}
		houseInfoService.deleteHouse(hisArr);
		return "redirect:" + adminPath + "/house/list?repage";
	}

	// /**
	// * 移交房源-功能暂时不要
	// *
	// * @param model 数据绑定接口,实现类为ExtendedModelMap
	// * @return
	// * @throws Exception
	// */
	// @RequiresPermissions("emp:house:transfer")
	// @RequestMapping(value = "/tran", method = RequestMethod.POST, produces =
	// "application/json;charset=UTF-8")
	// public String transferHouse(Model model, RedirectAttributes
	// redirectAttributes) throws Exception {
	// String checkHid = (String) getRequestParameter("transfer");
	// String empId = (String) getRequestParameter("emp_id");
	//
	// String[] hisArr = checkHid.split("_");
	//
	// for (int i = 0; i < hisArr.length; i++) {
	// String hid = hisArr[i];
	// agtEmpHouseLinkService.updateTransferHouse(hid,empId);
	// }
	// return "redirect:" + adminPath + "/house/list?repage";
	// }

	/**
	 * 跳转到添加房源页面
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 添加房源页面
	 */
	@RequiresPermissions("emp:house:add")
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RequestMapping(value = "/toadd", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String toadd(Model model, RedirectAttributes redirectAttributes) {
		// 委托人人员ID
		String per_id = (String) getRequestParameter("per_id");
		// 委托信息ID
		String ent_id = (String) getRequestParameter("ent_id");

		setCommonAttr();
		//查询公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}

		HouHouseInfo houseinfo = new HouHouseInfo();
		model.addAttribute("houseinfo", houseinfo);

		model.addAttribute("per_id", per_id);
		model.addAttribute("ent_id", ent_id);
		return JSP_DIR + "/houseadd";
	}

	/**
	 * 检测证件编号是否存在如果选其他，检验地址
	 * @return ajaxSuccess:证件编号不存在，ajaxFail:证件编号存在
	 */
	@RequiresPermissions("emp:house:add")
	@RequestMapping(value = { "cid", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult checkIdNo() {
		// 房源地址
		String houseAddr = (String) getRequestParameter("houseAddr");
		// 产权证件编号
		String propertyIdNo = (String) getRequestParameter("propertyIdNo");
		// 产权证件类型
		String propertyIdType = (String) getRequestParameter("propertyIdType");

		HouHouseInfo houseInfo = new HouHouseInfo();
		houseInfo.setPropertyIdNo(propertyIdNo);
		houseInfo.setPropertyIdType(Integer.parseInt(propertyIdType));
		houseInfo.setHouseAddr(houseAddr);
		// 检测是否存在房源
		String count = "";
		// 选择了其他校验地址
		if (PropertyIdTypeEnum.OTHER_ENUM.getValue() == Integer.parseInt(propertyIdType)) {
			// 检测房源地址是否存在
			count = houseInfoService.selectCheckAddr(houseInfo);
		} else {
			//编号不为空则验证,为空则不用验证
			if(StringUtils.isNotBlank(propertyIdNo)){
				// 检测证件编号是否存在
				count = houseInfoService.selectCheckIdNo(houseInfo);
			}
		}
		// count为空，不存在房源，返回成功。
		if (StringUtils.isEmpty(count)) {
			return ajaxSuccess("成功");
		}
		return ajaxFail("失败");
	}

	/**
	 * 添加房源- 插入房源等表
	 * @param request 接收客户端向服务器发出请求
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param houseInfo
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:add")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequestMapping(value = "/add")
	public String addHouse(HttpServletRequest request, Model model, HouHouseInfo houseInfo) {
		//房源id
		String houseId = "";
		// 获取参数，组装返回参数
		setCommonAttr();
		model.addAttribute("houseinfo", houseInfo);

		// 图片地址数组
		String[] mulImage_path = request.getParameterValues("pics");
		// 产权类型
		String propertyIdType = request.getParameter("propertyIdType");
		int propertyId = StringUtils.isNotEmpty(propertyIdType) ? Integer.parseInt(propertyIdType) : 0;
		// 产权类型不是其他
		if (propertyId != PropertyIdTypeEnum.OTHER_ENUM.getValue()
				&& (mulImage_path == null || mulImage_path.length == 0)) {
			addError(model, "请上传附件！");
			TokenHelper.getInstance().setToken(this.getRequest());
			return JSP_DIR + "/houseadd";
		}

		try {
			// 添加/编辑房源检测共同信息
			addCheck(houseInfo, request);
		} catch (ApplicationException ex) {
			model.addAttribute("atts", mulImage_path);
			addError(model, ex.getMessage());
			TokenHelper.getInstance().setToken(this.getRequest());
			return JSP_DIR + "/houseadd";
		} 
		try {
			// 保存信息并返回房源ID
			houseId = houseInfoService.insertHouse(request, houseInfo, mulImage_path);
		} catch (ApplicationException e) {
			setRequestAttribute("msg", "添加房源失败！");
			return JSP_DIR + "/addresult";
		}
		return "redirect:" + adminPath + "/house/adres?hid=" + houseId;
	}

	/**
	 * 房源详情
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:view")
	@RequestMapping(value = "/see", method = { RequestMethod.POST, RequestMethod.GET })
	public String see(Model model, RedirectAttributes redirectAttributes) {
		// 房源ID
		String hid = (String) getRequestParameter("hid");
		HouHouseInfo houseInfo = new HouHouseInfo();
		houseInfo.setId(hid);
		// 获取房源列表
		List<HouHouseInfo> houseInfoList = houseInfoService.selectHouseInfo(houseInfo);
		//未查找到房源
		if (houseInfoList.size() == 0) {
			addError(model, "无房源信息");
			return JSP_DIR + "/houselist";
		} else {
			houseInfo = houseInfoList.get(0);
			// 获取房源图片对象列表
			List<HouHouseInfoAtt> atts = houHouseInfoAttService.getAtts(hid);
			model.addAttribute("atts", atts);
			model.addAttribute("houseInfo", houseInfo);
		}

		setCommonAttr();
		String retStr = "";
		retStr = JSP_DIR + "/houseinfo";
		return retStr;
	}

	/**
	 * 跳到编辑房源页面 审核未通过
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @param hid 房源ID
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = "/edit",method = { RequestMethod.POST, RequestMethod.GET })
	public String edit(Model model, RedirectAttributes redirectAttributes, String hid) {
		//公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		HouHouseInfo houseInfo = new HouHouseInfo();
		houseInfo.setId(hid);
		// 房源列表
		List<HouHouseInfo> houseInfoList = houseInfoService.selectHouseInfo(houseInfo);
		if (houseInfoList.size() == 0) {
			addError(model, "无房源信息");
			return JSP_DIR + "/houselist";
		} else {
			// 获取房源图片对象列表
			List<HouHouseInfoAtt> atts = houHouseInfoAttService.getAtts(hid);
			if (houseInfoList.get(0).getOwners() != null && !houseInfoList.get(0).getOwners().isEmpty()
					&& houseInfoList.get(0).getOwners().get(0) != null) {
				houseInfoList.get(0).setHouseOwnerIdNo(houseInfoList.get(0).getOwners().get(0).getIdNo());
				houseInfoList.get(0).setIdType(houseInfoList.get(0).getOwners().get(0).getIdType());
				houseInfoList.get(0).setHouseOwnerName(houseInfoList.get(0).getOwners().get(0).getName());
			}
			model.addAttribute("atts", atts);
			model.addAttribute("houseinfo", houseInfoList.get(0));
		}
		// 设置公用属性
		setCommonAttr();

		houseInfo = houseInfoList.get(0);
		String beid = houseInfo.getBeid();
		Integer auditStatus = houseInfo.getAuditStatus();
		Integer certFlag = houseInfo.getCertFlag();

		Integer propertyIdType = houseInfo.getPropertyIdType();
		setRequestAttribute("propertyIdType", propertyIdType);
		// 返回值字符串
		String retStr = "";
		// 返回提示信息
		String ret = checkDataPermission(beid, "emp:house:edit");

		if ("".equals(ret)) {
			// 程序读取加密（防盗链配置的图片方式）
			String time = DateUtils.getDate("HH:mm");
			model.addAttribute("accessKey", MD5.GetMD5Code("dlfc") + time);
			// 审核未通过,认证未通过，可以修改所有信息
			if (auditStatus != null && auditStatus == 3 && (certFlag == null || certFlag == 0)) {
				retStr = JSP_DIR + "/edithouse";
			} else if (auditStatus != null && auditStatus == 3 && certFlag == 1) {// 审核未通过,认证通过，只能修改图片
				retStr = JSP_DIR + "/editfile";
			} else {// 未审核||审核通过
				retStr = JSP_DIR + "/houseinfo";
			}
		} else {
			addError(redirectAttributes, ret);
			return "redirect:" + adminPath + "/house/list?repage";
		}

		return retStr;
	}

	/**
	 * 编辑房源保存-可以编辑所有 1,审核未通过 2,认证未通过
	 * @param request 接收客户端向服务器发出请求
	 * @param redirectAttributes 对象重定向传参
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param houseInfo 房源实体
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model,
			HouHouseInfo houseInfo) {
		// 设置公用属性
		setCommonAttr();
		// 公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		if (houseInfoService.checkVersion(houseInfo)) {
			addError(redirectAttributes, "房源信息已被修改");
			return "redirect:" + adminPath + "/house/list?repage";
		}

		// 修改房源的房源主键
		String houseId = houseInfo.getId();
		houseInfo.setId(houseId);
		// 获取房源对象列表
		List<HouHouseInfo> houseInfoList = houseInfoService.selectHouseInfo(houseInfo);
		if (houseInfoList.size() == 0) {
			addError(model, "无房源信息");
			return JSP_DIR + "/houselist";
		} else {
			model.addAttribute("houseinfo", houseInfoList.get(0));
		}

		HouHouseInfo houseInfo2 = houseInfoList.get(0);
		String beid = houseInfo2.getBeid();
		// 返回信息
		String ret = checkDataPermission(beid, "emp:house:edit");

		if (!"".equals(ret)) {
			addError(redirectAttributes, ret);
			return "redirect:" + adminPath + "/house/list?repage";
		}

		// 图片数组
		String[] mulImage_path = request.getParameterValues("pics");
		// 产权类型
		String propertyIdType = request.getParameter("propertyIdType");
		int propertyId = StringUtils.isNotEmpty(propertyIdType) ? Integer.parseInt(propertyIdType) : 0;
		// 产权类型不是其他
		if (propertyId != PropertyIdTypeEnum.OTHER_ENUM.getValue()
				&& (mulImage_path == null || mulImage_path.length == 0)) {
			addError(model, "请上传附件！");
			return JSP_DIR + "/edithouse";
		}

		try {
			addCheck(houseInfo, request);
			//更新数据
		} catch (ApplicationException ex) {
			addError(model, ex.getMessage());
			return JSP_DIR + "/edithouse";
		}
		try {
			houseInfoService.updateHouse(request, houseInfo, mulImage_path);
		} catch (ApplicationException e) {
			setRequestAttribute("msg", "编辑房源失败！");
			return JSP_DIR + "/addresult";
		}

		return "redirect:" + adminPath + "/house/list?repage";
	}

	/**
	 * 编辑房源保存-只编辑附件 1,审核未通过 2,认证通过
	 * @param redirectAttributes 对象重定向传参
	 * @param request 接收客户端向服务器发出请求
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param houseInfo 房源实体
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = "/upd")
	public String updateHouseFile(RedirectAttributes redirectAttributes, HttpServletRequest request, Model model,
			HouHouseInfo houseInfo) {
		// 设置公用属性
		setCommonAttr();
		// 公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			addError(redirectAttributes, "公司已锁定！");
			return "redirect:" + adminPath + "/house/list?repage";
		}
		if (houseInfoService.checkVersion(houseInfo)) {
			addError(redirectAttributes, "房源信息已被修改");
			return "redirect:" + adminPath + "/house/list?repage";
		}

		// 修改房源的房源主键
		String houseId = houseInfo.getId();
		houseInfo.setId(houseId);
		// 房源列表
		List<HouHouseInfo> houseInfoList = houseInfoService.selectHouseInfo(houseInfo);
		// 未查找到房源信息
		if (houseInfoList.size() == 0) {
			addError(model, "无房源信息");
			return JSP_DIR + "/houselist";
		} else {
			model.addAttribute("houseinfo", houseInfoList.get(0));
		}

		HouHouseInfo houseInfo2 = houseInfoList.get(0);
		String beid = houseInfo2.getBeid();
		// 返回信息
		String ret = checkDataPermission(beid, "emp:house:edit");

		if (!"".equals(ret)) {
			addError(redirectAttributes, ret);
			return "redirect:" + adminPath + "/house/list?repage";
		}

		//图片数组
		String[] mulImage_path = request.getParameterValues("pics");

		if (mulImage_path.length == 0) {
			addError(model, "请上传附件！");
			return JSP_DIR + "/houseadd";
		}

		try {
			houseInfoService.updateHouseFile(houseInfo, mulImage_path);
		} catch (ApplicationException e) {
//			addError(redirectAttributes,"编辑房源失败");
			setRequestAttribute("msg", "编辑房源失败！");
			return JSP_DIR + "/addresult";
		}
		return "redirect:" + adminPath + "/house/list?repage";
	}

	/**
	 * 添加房源成功跳转成功页面
	 * @return 请求地址
	 */
	@RequestMapping(value = "/adres")
	public String adres() {
		// 房源id
		String hid = (String) getRequestParameter("hid");
		setRequestAttribute("repage", "repage");
		setRequestAttribute("hid", hid);
		return JSP_DIR + "/addresult";
	}

	/**
	 * 添加/编辑房源检测共同信息
	 * @param houseInfo
	 * @param request 接收客户端向服务器发出请求
	 * @throws ApplicationException 公司已锁定，房源地址非空，建筑面积范围和非空，
	 */
	public void addCheck(HouHouseInfo houseInfo, HttpServletRequest request) throws ApplicationException {
		// 公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		if (lockFlg) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0016"));
		}
		// 房源地址
		String houseAddr = houseInfo.getHouseAddr();
		// 建筑面积
		BigDecimal buildingarea = houseInfo.getBuildingArea();

		//房源地址非空校验
		if (StringUtils.isBlank(houseAddr)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0024"));
		}
		// 产权信息空验证
		if (houseInfo.getPropertyIdType() == null) {
			throw new ApplicationException("请填写产权信息！");
		}
		//房源面积非空校验
		if (buildingarea == null || buildingarea.doubleValue() == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0025"));
		} else {// 建筑面积不能大于最大面积并且不能小于最小面积
			if (buildingarea.doubleValue() > BUILDING_AREA_MAX || buildingarea.doubleValue() < BUILDING_AREA_MIN) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0026"));
			}
		}
		// 房主身份证号
		String houseOwnerIdNo = houseInfo.getHouseOwnerIdNo();
		// 房主姓名
		String HouseOwnerName = houseInfo.getHouseOwnerName(); 
		boolean OwnerIdNoFlg = false;
		// 如果产权类型为“产权”、“使用权”、“不动产登记”时
		if ((PropertyIdTypeEnum.USUS_ENUM.getValue() == houseInfo.getPropertyIdType().intValue()) 
				|| (PropertyIdTypeEnum.PROPERTY_ENUM.getValue() == houseInfo.getPropertyIdType().intValue())
				|| (PropertyIdTypeEnum.RORE_ENUM.getValue() == houseInfo.getPropertyIdType().intValue())) {
			// 房主信息有任意一个为空时
			if (StringUtils.isBlank(houseOwnerIdNo) || StringUtils.isBlank(HouseOwnerName)) {
				throw new ApplicationException("请填写房主信息");
			} else {
				OwnerIdNoFlg = true;
			}
		} 
		// 如果产权类型为“其他”时
		else if (PropertyIdTypeEnum.OTHER_ENUM.getValue() == houseInfo.getPropertyIdType().intValue()) {
			// 如果填写房主身份证但未填写姓名时
			if (StringUtils.isNotBlank(houseOwnerIdNo) && StringUtils.isBlank(HouseOwnerName)) {
				throw new ApplicationException("请填写完整的房主信息");
			} else if (StringUtils.isNotBlank(houseOwnerIdNo) && StringUtils.isNotBlank(HouseOwnerName)) {
				OwnerIdNoFlg = true;
			}
		} else {
			throw new ApplicationException("产权类型错误");
		}
		
		// 共有人姓名 证件类型 证件编号
		String[] conameArr = request.getParameterValues("coname");
		// 共有人证件类型
		String[] coidtypeArr = request.getParameterValues("coidtype");
		// 共有人证件编号
		String[] coidcodeArr = request.getParameterValues("coidcode");

		List<String> compareNameList = new ArrayList<String>();
		List<String> compareNoList = new ArrayList<String>();
		// 判断共有人姓名是否相同
		Boolean noBoo = false;
		// 判断共有人证件号是否相同
		Boolean houNoBoo = false;

		String result = "";
		// 房主已添加的情况下验证共有人是否填写
		if (OwnerIdNoFlg && conameArr != null && conameArr.length > 0 && coidcodeArr != null && coidcodeArr.length > 0
				&& coidcodeArr.length == coidcodeArr.length) {
			List<HouCoOwner> l = new ArrayList<HouCoOwner>();
			for (int i = 0; i < conameArr.length; i++) {
				if (compareNoList.contains(coidcodeArr[i])) {
					noBoo = true;
				}

				if (coidcodeArr[i].equals((houseOwnerIdNo))) {
					houNoBoo = true;
				}

				String coname = conameArr[i];
				if (coname == null || "".equals(coname.trim())) {
					continue;
				}

				HouCoOwner co = new HouCoOwner();
				compareNameList.add(conameArr[i]);
				compareNoList.add(coidcodeArr[i]);
				co.setName(conameArr[i]);

				if (coidtypeArr.length < i) {

				} else {
					co.setIdType(Integer.parseInt(coidtypeArr[i]));
				}
				if (coidcodeArr.length < i) {

				} else {
					co.setIdNo(coidcodeArr[i]);
				}

				l.add(co);
				houseInfo.setOwners(l);

				// 实名认证
				String result_tmp = sysPersonService.checkIdentity(conameArr[i], Integer.parseInt(coidtypeArr[i]),
						coidcodeArr[i]);
				if (result_tmp == null) {
					result = result_tmp;
				}
			}
			setRequestAttribute("houseinfo", houseInfo);
		}
		// 共有人存在提示错误信息
		if (noBoo) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0027"));
		}
		// 共有人证件号重复提示错误信息
		if (houNoBoo) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0028"));
		}

		String per_id = request.getParameter("per_id");
		setRequestAttribute("per_id", per_id);

		if (result == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0030"));
		}

		// 实名认证-房主
		if (OwnerIdNoFlg) {
			String result_tmp = sysPersonService.checkIdentity(HouseOwnerName, 1, houseOwnerIdNo);
			if (result_tmp == null) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0031"));
			}
		}
	}

	/**
	 * 设置公用属性
	 */
	public void setCommonAttr() {
		// 证件
		List<SysCode> id_list = sysCodeService.getCodeByType("per_id_type");
		// 房源类型
		List<SysCode> property_list = sysCodeService.getCodeByType("property_id_type");
		// 房源结构
		List<SysCode> structure_list = sysCodeService.getCodeByType("house_structure");
		// 区域列表
		List<SysAreaAreas> district_list = sysAreaAreasService.getAreaName(PropertyUtils.getSysVal("system.city.code"));

		getRequest().setAttribute("id_list", id_list);
		getRequest().setAttribute("property_list", property_list);
		getRequest().setAttribute("district_list", district_list);
		getRequest().setAttribute("structure_list", structure_list);
	}

	/**
	 * 校验数据权限
	 * @param beid
	 * @param permission 许可信息
	 * @return 错误提示信息
	 */
	private String checkDataPermission(String beid, String permission) {
		String retStr = "";
		if (StringUtil.isNotEmpty(beid)) {
			// 获取操作权限范围
			List<String> empIdList = agtEmpInfoService.getEmpIds(permission);
			if (!empIdList.contains(beid)) {
				retStr = "无操作权限！";
			}
		}

		return retStr;
	}

	/**
	 * 改变房间状态
	 * @return AjaxResult:操作成功
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = { "crs", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult changeRoomStatus() {
		// 出租信息ID
		String id = (String) getRequestParameter("id");
		// 房间ID
		String status = (String) getRequestParameter("status");
		// 修改房间状态
		houLeaseRoomInfoService.changeRoomStatus(id, status);

		String leaseid = houLeaseRoomInfoService.selectLeaseid(id);
		int count = houLeaseRoomInfoService.selectRoomCount(leaseid);

		// 如果房间都已经出租，设置出租信息下架
		if (count == 0) {
			houLeaseInfoService.changeLeaseStatus(leaseid, String.valueOf(HouseReleaseStatusEnum.NO_ENUM.getValue()));
		}
		return ajaxSuccess("成功", count);
	}

	/**
	 * 改变出租信息状态
	 * @return AjaxResult:操作成功
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = { "cls", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult changeLeaseStatus() {
		// 出租信息ID
		String id = (String) getRequestParameter("id");
		// 出租信息状态
		String status = (String) getRequestParameter("status");

		String[] leaseArr = id.split("-");
		// ${houseInfo.lid}——${houseInfo.leaseMode}
		String leaseid = leaseArr[0];
		String leaseMode = leaseArr[1];

		int count = houLeaseRoomInfoService.selectRoomCount(leaseid);

		Integer leaseInt = Integer.parseInt(leaseMode);
		// 如果房间都已经出租，不能设置出租信息上架 1-整租,2分租
		if (leaseInt.intValue() == 1 || (count != 0 && leaseInt.intValue() == 2)) {
			houLeaseInfoService.changeLeaseStatus(leaseid, status);
		}

		return ajaxSuccess("成功");
	}

	/**
	 * 刷新出租信息
	 * @return AjaxResult
	 */
	@RequiresPermissions("emp:house:edit")
	@RequestMapping(value = { "refl", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult refreshLease() {
		String id = (String) getRequestParameter("lid");

		houLeaseInfoService.refreshLease(id);
		return ajaxSuccess("成功");
	}

	/**
	 * 获取服务器时间
	 * @return 服务器时间
	 */
	@RequestMapping(value = "/serverDate", method = RequestMethod.GET)
	@ResponseBody
	public String getServerDate() {
		return DateUtils.getDate();
	}
	
	/**
	 * 验真房源
	 * @param id 房源id
	 * @return 房源列表页面
	 */
	@RequestMapping(value = "/truth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult truthHouse(String id) {
		int flg = houseInfoService.updateTruthHouse(id);
		if(flg == 1){
			return ajaxSuccess("申请成功");
		}
		return ajaxSuccess("申请失败");
	}
}
