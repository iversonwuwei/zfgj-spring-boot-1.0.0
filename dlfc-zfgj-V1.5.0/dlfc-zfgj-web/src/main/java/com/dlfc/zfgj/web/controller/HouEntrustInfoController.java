/**
 * @name: HouEntrustInfoController.java 
 *
 * @Copyright: (c) 2016 DLFC. All rights reserved. 
 *
 * @description: 房源委托、验房预约
 *
 * @version: 1.0
 * @date : 2016年3月25日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2016年3月25日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtExamineHouInfo;
import com.dlfc.zfgj.entity.HouEntrustInfo;
import com.dlfc.zfgj.entity.SysTradeAreas;
import com.dlfc.zfgj.service.AgtExamineHouInfoService;
import com.dlfc.zfgj.service.HouEntrustInfoService;
import com.dlfc.zfgj.service.SysTradeAreasService;
import com.housecenter.dlfc.zfgj.common.persistence.Page;

/**
 * @name: HouEntrustInfoController
 * @description: 房源委托、验房预约
 * @version 1.0
 * @author fancy
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/ent")
public class HouEntrustInfoController extends BaseController {
	/**页面根目录*/
	private static final String JSP_DIR = "dlfc/houseinfo";
	/**静态变量，HouEntrustInfo的key*/
	private static final String KEY_ENTRUSTINFO_LIST_CON_SESSION = "KEY_ENTRUSTINFO_LIST_CON_SESSION";
	/**房源委托service*/
	@Autowired
	private HouEntrustInfoService houEntrustInfoService;
	/**经纪人验房预约service*/
	@Autowired
	private AgtExamineHouInfoService agtExamineHouInfoService;
	/**商圈信息service*/
	@Autowired
	private SysTradeAreasService sysTradeAreasService;

	/**
	 * 房源委托列表展示 租房管家ZFGJMG-418-明确房源委托推送规则及管理员端部门业务范围逻辑
	 * 1、租房网中房主发布委托时选择的商圈为小区所在的商圈；与可选的经纪公司无关联。
	 * 2、判断委托推送的规则为：房主选择西安路商圈，A/B两家中介；则委托将推送给A/B两家公司的所有经纪人（此委托对其他经纪公司经纪人不可见），
	 * 再根据此两家中介经纪人所在门店业务范围中所选的商圈判断可抢优先级，
	 * 按照房主发布委托到达服务器的时间:"同商圈"【立即可抢】>"同区域"【延迟30秒可抢】>"其他区域"【延迟60秒可抢】；
	 * 3、租房管家管理员端部门管理设置的部门业务范围（商圈范围）只在抢委托时判断是否优先可抢时作为判断依据；如经纪人没有所属商圈，
	 * 则按照推送规则中"其他区域"【延迟60秒可抢】处理。
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		HouEntrustInfo entrustInfo = null;
		// 从其他页面返回本页面时，从session里取出本页面的查询参数
		if (isRepage()) {
			entrustInfo = (HouEntrustInfo) getSessionAttribute(KEY_ENTRUSTINFO_LIST_CON_SESSION);
		}
		// 创建时间
		String butimesort = (String) getRequestParameter("bs");
		// 全部房源 or关注房源
		String status = (String) getRequestParameter("st");
		
		// 房源委托对象为空，创建一个实体
		if (entrustInfo == null) {
			entrustInfo = new HouEntrustInfo();
		}
		//默认页面
		String currentPage = (String) getRequestParameter("currentPage");
		entrustInfo.setCurrentPage(currentPage);
		//排序
		String sort = "";
		//asc升序，desc降序
		if ("asc".equalsIgnoreCase(butimesort)) {
			sort += ",A.CREATE_TIME";
		} else if ("desc".equalsIgnoreCase(butimesort)) {
			sort += ",A.CREATE_TIME DESC";
		}
		//排序字段非空
		if (!"".equals(sort)) {
			sort = sort.substring(1);
		}
		entrustInfo.setOrderBy(sort);

		// 获取当前经纪人部门负责的区域ID
		List<SysTradeAreas> areas = sysTradeAreasService.getAreaIdByUid(UserUtils.getUser().getId());
		entrustInfo.setAreasList(areas);
		String companyId = "99999999";
		// 公司非空
		if (UserUtils.getUser().getCompany() != null) {
			companyId = UserUtils.getUser().getCompany().getId();
			entrustInfo.setCompId(companyId);
		}
		// 获取当前经纪人id
		String empId = UserUtils.getUser().getEmpId();
		entrustInfo.setLockEid(empId);
		// 获取房源委托对象
		BasePage<HouEntrustInfo> page = houEntrustInfoService.selectPage(new Page<HouEntrustInfo>(request, response),entrustInfo);
		setRequestAttribute("page", page);
		setRequestAttribute("st", status);
		setRequestAttribute("bs", butimesort);

		// 将查询参数存入session，以便当从其他页面返回时获得这些参数
		setSessionAttribute(KEY_ENTRUSTINFO_LIST_CON_SESSION, entrustInfo);
		return JSP_DIR + "/entrustlist";
	}

	/**
	 * 预约验房
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = "/exah", method = RequestMethod.POST)
	public String examineHou(RedirectAttributes redirectAttributes) {
		//验房id
		String entrustId = (String) getRequestParameter("entrustId");
		//验房日期
		String examineTime = (String) getRequestParameter("examineTime");
		//验房时间
		String examineTime2 = (String) getRequestParameter("examineTime2");
		//描述
		String comment = (String) getRequestParameter("comment");

		AgtExamineHouInfo info = new AgtExamineHouInfo();
		info.setEntrustId(entrustId);
		info.setComment(comment);
		info.setEid(UserUtils.getUser().getEmpId());

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * Date date = sdf.parse(examineTime+" "+examineTime2+":00");
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date;
		try {
			date = sdf.parse(examineTime + " " + examineTime2);
			info.setExamineTime(date);
		} catch (ParseException e) {
			logger.error("HouEntrustInfoController examineHou",e.getMessage());
		}

		// 判断委托是否已经被人抢走
		List<AgtExamineHouInfo> agtExamineHouInfos = agtExamineHouInfoService.selectExaminesInfo(entrustId);
		if (agtExamineHouInfos.size() > 0) {
			addError(redirectAttributes, "下手慢了，委托已被抢走！！");
			return "redirect:" + adminPath + "/ent/list?repag";
		}

		agtExamineHouInfoService.insertExamineHou(info);
		return "redirect:" + adminPath + "/ent/list?repag";
	}

	/**
	 * 锁定房源--租房管家 抢房源 / / 0.5小时 抢房源后未预约验房操作致抢单无效（委托信息重新在“公共房源”列表显示） 经纪人
	 * @return ajaxSuccess:操作成功，ajaxFail操作失败
	 */
	@RequestMapping(value = { "lock", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult lockHouse() {
		String id = (String) getRequestParameter("id");
		HouEntrustInfo houEntrustInfo = houEntrustInfoService.selectEntInfo(id);
		if (houEntrustInfo == null) {
			return ajaxFail("委托已取消!");
		}
		//锁定经纪人ID
		String lockEid = houEntrustInfo.getLockEid();
		// 第一次抢
		if (lockEid != null && !lockEid.equals("")) {
			String empId = UserUtils.getUser().getEmpId();
			// 上次跟这次是一个经纪人
			if (lockEid.equals(empId)) {

			} else {// 不是一个人
				Date lockTime = houEntrustInfo.getLockTime();
				Date currentTime = com.dlfc.admin.common.utils.DateUtils.getSynchTime();
				long time = currentTime.getTime() - lockTime.getTime();
				// 距上次抢的时间
				int t = (int) (time / 1000);
				// 这次与上次的间隔 必须大于30分 可抢
				if (t < 30 * 60) {
					return ajaxFail();
				}
			}
		}

		houEntrustInfoService.lockHouse(id);
		return ajaxSuccess();
	}

	/**
	 * 取消锁定房源--点取消或者关闭取消锁定房源
	 * @return ajaxSuccess:操作成功
	 */
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult lockCancel() {
		String id = (String) getRequestParameter("id");
		houEntrustInfoService.lockCancel(id);
		return ajaxSuccess();
	}
}
