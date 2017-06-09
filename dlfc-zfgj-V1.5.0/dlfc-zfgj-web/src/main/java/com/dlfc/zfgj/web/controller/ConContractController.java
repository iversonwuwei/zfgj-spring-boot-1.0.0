/**
* @name: ConContractController.java 
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
package com.dlfc.zfgj.web.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.AppRuntimeException;
import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.submission.annotation.AvoidDuplicateSubmission;
import com.dlfc.admin.common.submission.token.TokenHelper;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.MD5;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConHouseItems;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrDelInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.ConContractService;
import com.dlfc.zfgj.service.SysBankService;
import com.dlfc.zfgj.service.SysCodeService;
import com.dlfc.zfgj.service.SysInfoAttService;
import com.dlfc.zfgj.service.SysPersonService;
import com.dlfc.zfgj.service.UserDelInfoService;
import com.dlfc.zfgj.service.UsrUserService;
import com.housecenter.dlfc.zfgj.common.enums.ConOptEventEnum;
import com.housecenter.dlfc.zfgj.common.enums.ConStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.RentalModeEnum;
import com.housecenter.dlfc.zfgj.common.enums.RentalPurposeEnum;
import com.housecenter.dlfc.zfgj.common.enums.SettlementCycleEnum;
import com.housecenter.dlfc.zfgj.common.persistence.Page;
import com.housecenter.dlfc.zfgj.common.utils.Const;
import com.housecenter.dlfc.zfgj.common.utils.FileUpload;

import tk.mybatis.mapper.util.StringUtil;

/**
 * @name: 创建合同Controller
 * @description:经纪人信息获取，初始化新建合同，初始化合同修改step1，新建合同step1，新建合同step2，新建合同step3，新建合同step4，初始化续约合同，续约，初始化step5，
 *				点击下载按钮，移动附件并将合同状态设置为"审核中"，实名认证，获取银行卡信息，获取可租面积和是否可以整租，确认下载时，检验可租面积，图片上传，根据ID获取合同详细信息，
 *				获取房主、房客承担费用项，设置时间控件显示的时间范围，移交合同，合同取消状态，合同删除，新建合同第5步，检验方法，押金监管协议详情
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/con")
public class ConContractController extends BaseController {
	/** 静态变量：网页地址*/
	private static final String JSP_DIR = "dlfc/contract";
	private static final String STEP1 = "/step1";
	private static final String STEP2 = "/step2";
	private static final String STEP3 = "/step3";
	private static final String STEP4 = "/step4";
	private static final String STEP5 = "/step5";
	private static final String RENEWAL = "/renewal";
	/**合同逻辑service*/
	@Autowired
	private ConContractService conService;
	/**  排序service*/
	@Autowired
	private SysCodeService codeService;
	@Autowired
	/**  银行service*/
	private SysBankService bankService;
	/**  附件信息service*/
	@Autowired
	private SysInfoAttService infoAttService;
	/**  用户信息service*/
	@Autowired
	private UsrUserService userService;
	/**  经纪人信息service*/
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;
	/**  个人信息service*/
	@Autowired
	private SysPersonService sysPersonService;
	/**  用户删除的信息service*/
	@Autowired
	private UserDelInfoService userDelInfoService;

	/**
	 * 合同列表
	 * 
	 * @param request
	 * @param response
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:view")
	@RequestMapping(value = "/clist", method = RequestMethod.GET)
	public String conList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String certifyStatus = (String) getRequestParameter("st");
		String tSort = (String) getRequestParameter("tSort");
		String sSort = (String) getRequestParameter("sSort");
		String eSort = (String) getRequestParameter("eSort");
		String enname = (String) getRequestParameter("enname");
		String searchInfo = (String) getRequestParameter("searchInfo");
		String sort = "";
		// 按照时间排序
		if ("asc".equals(tSort)) {
			sort = ",c.CREATE_TIME";
			// 按照时间倒叙排序
		} else if ("desc".equals(tSort)) {
			sort = sort + ",c.CREATE_TIME DESC";
		}
		// 按起始时间排序
		if ("asc".equals(sSort)) {
			sort = sort + ",c.START_TIME";

		} else if ("desc".equals(sSort)) {
			sort = sort + ",c.START_TIME DESC";
		}
		// 按结束时间排序
		if ("asc".equals(eSort)) {
			sort = sort + ",c.END_TIME";
		} else if ("desc".equals(eSort)) {
			sort = sort + ",c.END_TIME DESC";
		}
		if (!"".equals(sort)) {
			sort = sort.substring(1);
		}
		User user = UserUtils.getUser();
		ConContract con = new ConContract();
		con.setOrderBy(sort);
		con.setUid(user.getId());
		if (StringUtils.isNoneBlank(certifyStatus)) {
			con.setStatus(Short.parseShort(certifyStatus));
		}
		// 数据权限的配置
		String scope = agtEmpInfoService.getEmpIdStr("emp:cont:view");
		con.setScope(scope);
		Short st = con.getStatus();
		// 查询条件
		con.setSearchInfo(searchInfo);
		BasePage<ConContract> page = conService.selectPage(new Page<ConContract>(request, response), con);
		List<Role> myRoles = UserUtils.getUser().getRoleList();
		String root = "";
		String comp_role = "";
		if (myRoles != null && myRoles.size() > 0) {
			for (int i = 0; i < myRoles.size(); i++) {
				if ("manager".equals(myRoles.get(i).getEnname())) {
					root = myRoles.get(i).getEnname();
				}
				// 管理员权限
				if ("assignment".equals(myRoles.get(i).getRoleType()) || "comp-role".equals(myRoles.get(i).getRoleType())) {
					comp_role = myRoles.get(i).getRoleType();
					setRequestAttribute("compRole", comp_role);
					setRequestAttribute("root", root);
					setRequestAttribute("enname", enname);
					setRequestAttribute("page", page);
					setRequestAttribute("st", st);
					setRequestAttribute("tSort", tSort);
					setRequestAttribute("sSort", sSort);
					setRequestAttribute("eSort", eSort);
					return "dlfc/contract/mlist";
				}
			}
		} // 个人权限
		if ("".equals(root)) {
			setRequestAttribute("root", root);
			setRequestAttribute("page", page);
			setRequestAttribute("st", st);
			setRequestAttribute("tSort", tSort);
			setRequestAttribute("sSort", sSort);
			setRequestAttribute("eSort", eSort);
			return "dlfc/contract/clist";
		}
		if (enname == null) {
			enname = "";
		} // 经理权限
		if (enname.equals(root)) {
			setRequestAttribute("compRole", comp_role);
			setRequestAttribute("root", root);
			setRequestAttribute("enname", enname);
			setRequestAttribute("page", page);
			setRequestAttribute("st", st);
			setRequestAttribute("tSort", tSort);
			setRequestAttribute("sSort", sSort);
			setRequestAttribute("eSort", eSort);
			return "dlfc/contract/mlist";
		} else {
			// 经理查看自己的合同
			con.setScope("");
			con.setBeid(UserUtils.getUser().getEmpId());
			page = conService.selectPage(new Page<ConContract>(request, response), con);
			setRequestAttribute("root", root);
			setRequestAttribute("enname", enname);
			setRequestAttribute("page", page);
			setRequestAttribute("st", st);
			setRequestAttribute("tSort", tSort);
			setRequestAttribute("sSort", sSort);
			setRequestAttribute("eSort", eSort);
			return "dlfc/contract/clist";
		}
	}

	/**
	 * 初始化新建合同
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String initAdd(Model model, RedirectAttributes redirectAttributes) throws Exception {
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		//判断经纪人
		if (lockFlg) {
			addError(redirectAttributes, PropertyUtils.getErrorMsg("SYS-0016"));
			return "redirect:" + super.adminPath + "/con/clist";
		}
		ConContract con = new ConContract();
		model.addAttribute("con", con);
		/*List<UsrUserBankcard> list = new ArrayList<UsrUserBankcard>();
		list.add(new UsrUserBankcard());
		list.add(new UsrUserBankcard());
		con.setUserBankcardList(list);*/
		model.addAttribute("perIdTypes", codeService.getCodeByType(Const.CODE_PER_ID_TYPE));
		model.addAttribute("banks", bankService.getList());
		String html = conService.selectAllpaySupportBankList();
		model.addAttribute("allpaySupportBankList", html);

		return JSP_DIR + STEP1;
	}

	/**
	 * 初始化合同修改step1
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RequestMapping(value = STEP1, method = RequestMethod.GET)
	public String addStep1(Model model, String cid) throws Exception {
		checkCid(cid);
		model.addAttribute("perIdTypes", codeService.getCodeByType(Const.CODE_PER_ID_TYPE));
		model.addAttribute("banks", bankService.getList());
		getContract(model, cid);
		/*List<UsrUserBankcard> list = new ArrayList<UsrUserBankcard>();
		List<UsrUserBankcard> lessorBankcards = userService.getUserByPerId(con.getLessorPid()).getUserBankcardList();
		if (lessorBankcards != null && lessorBankcards.size() > 0) {
			String num = lessorBankcards.get(0).getBankNum();
			lessorBankcards.get(0).setBankNum(num.substring(0, 3) + "******" + num.substring(num.length() - 3, num.length()));
			list.add(lessorBankcards.get(0));
		} else {
			list.add(new UsrUserBankcard());
		}
		List<UsrUserBankcard> lesseeBankcards = userService.getUserByPerId(con.getLesseePid()).getUserBankcardList();
		if (lesseeBankcards != null && lesseeBankcards.size() > 0) {
			String num = lesseeBankcards.get(0).getBankNum();
			lesseeBankcards.get(0).setBankNum(num.substring(0, 3) + "******" + num.substring(num.length() - 3, num.length()));
			list.add(lesseeBankcards.get(0));
		} else {
			list.add(new UsrUserBankcard());
		}
		con.setUserBankcardList(list);*/
		String html = conService.selectAllpaySupportBankList();
		model.addAttribute("allpaySupportBankList", html);
		return JSP_DIR + STEP1;
	}

	/**
	 * 新建合同step1
	 * 
	 * @param model
	 * @param con
	 * @param submitType
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequestMapping(value = STEP1, method = RequestMethod.POST)
	public String addStep1(Model model, ConContract con) throws Exception {
		try {
			checkStep1(con);
			User user = UserUtils.getUser();
			String empId = user.getEmpId();
			//是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入
			if (con.getIsNewRecord()) {
				con.setEid(empId);
			}
			con.setBeid(empId);
			conService.saveLessor(con);
			conService.saveLessee(con);
			conService.saveElse(con);

		} catch (ApplicationException ex) {
			logger.error("ConContractController addStep1", ex);
			addError(model, ex.getMessage());
			TokenHelper.getInstance().setToken(this.getRequest());
			model.addAttribute("perIdTypes", codeService.getCodeByType(Const.CODE_PER_ID_TYPE));
			model.addAttribute("banks", bankService.getList());
			model.addAttribute("con", con);
			return JSP_DIR + STEP1;
		} catch (Exception ex) {
			logger.error("ConContractController addStep1", ex);
			throw ex;
		}
		return getReturnUrl(con.getId(), STEP2);
	}

	/**
	 * 初始化合同step2
	 * @param model
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP2, method = RequestMethod.GET)
	public String addStep2(Model model, String cid) throws Exception {
		checkCid(cid);
		setCalender(model);
		ConContract con = getContract(model, cid);

		// 获取人员信息表ID
		String pId = sysPersonService.selectId(con.getLessorName(), con.getLessorIdNo());

		if (con.getHouUserList() != null && con.getHouUserList().size() != 0) {
			model.addAttribute("actualPersonNum", con.getHouUserList().size());
		} else {
			model.addAttribute("actualPersonNum", "1");
		}
		User user = UserUtils.getUser();
		List<HouHouseInfo> houInfoList = conService.getCanRentHouList(user.getEmpId(), con.getHid(), pId);
		for(HouHouseInfo s :houInfoList){
			String lno = s.getLno();
			if(lno != null){
				String[] str=null;
				str = lno.split(",", -1);
				StringBuffer address = new StringBuffer();
				if (str != null && str.length == 3) {
					String lh = str[0];
					String dy = str[1];
					String ss = str[2];
					if (StringUtils.isNotEmpty(lh)) {
						address.append(lh).append("号楼");
					} else {
						address.append(" 无/号楼");
					}
					if (StringUtils.isNotEmpty(dy)) {
						address.append(" ").append(dy).append("单元");
					} else {
						address.append(" 无/单元");
					}
					if (StringUtils.isNotEmpty(ss)) {
						address.append(" ").append(ss).append("室");
					} else {
						address.append(" 无/室");
					}
					String ste = s.getEstate();
					if(com.dlfc.admin.common.utils.StringUtils.isNotBlank(ste)){
						s.setEstate(s.getEstate()+address.toString());
					}
				}
			}
		}
		model.addAttribute("houseInfos", houInfoList);
		model.addAttribute("perIdTypes", codeService.getCodeByType(Const.CODE_PER_ID_TYPE));
		return JSP_DIR + STEP2;
	}

	/**
	 * 新建合同step2
	 * 
	 * @param model
	 * @param con
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP2, method = RequestMethod.POST)
	public String addStep2(Model model, ConContract con) throws Exception {
		try {
			// checkDataPermission(con.getId());
			checkStep2(con);
			conService.save(con, ConContractService.TYPE_STEP2);
		} catch (ApplicationException ex) {
			logger.error("ConContractController addStep2", ex);
			addError(model, ex.getMessage());
			setCalender(model);
			model.addAttribute("con", con);
			// 获取人员信息表ID
			String pId = sysPersonService.selectId(con.getLessorName(), con.getLessorIdNo());
			if (con.getHouUserList() != null && con.getHouUserList().size() != 0) {
				model.addAttribute("actualPersonNum", con.getHouUserList().size());
			} else {
				model.addAttribute("actualPersonNum", "1");
			}
			User user = UserUtils.getUser();
			List<HouHouseInfo> houInfoList = conService.getCanRentHouList(user.getEmpId(), con.getHid(), pId);
			model.addAttribute("houseInfos", houInfoList);
			model.addAttribute("perIdTypes", codeService.getCodeByType(Const.CODE_PER_ID_TYPE));
			return JSP_DIR + STEP2;
		} catch (Exception ex) {
			logger.error("ConContractController addStep2", ex);
			throw ex;
		}
		return getReturnUrl(con.getId(), STEP3);
	}

	/**
	 * 初始化合同step3
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP3, method = RequestMethod.GET)
	public String addStep3(Model model, String cid) throws Exception {
		checkCid(cid);
		getContract(model, cid);

		return JSP_DIR + STEP3;
	}

	/**
	 * 新建合同step3
	 * 
	 * @param model
	 * @param con
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP3, method = RequestMethod.POST)
	public String addStep3(Model model, ConContract con) throws Exception {
		try {
			// checkDataPermission(con.getId());
			checkStep3(con);
			conService.save(con, ConContractService.TYPE_STEP3);
		} catch (ApplicationException ex) {
			logger.error("ConContractController addStep3", ex);
			addError(model, ex.getMessage());
			model.addAttribute("con", con);
			return JSP_DIR + STEP3;
		} catch (Exception ex) {
			logger.error("ConContractController addStep3", ex);
			throw ex;
		}
		return getReturnUrl(con.getId(), STEP4);
	}

	/**
	 * 初始化合同step4
	 * 
	 * @param model
	 * @param cid：合同id
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP4, method = RequestMethod.GET)
	public String addStep4(Model model, String cid) throws Exception {
		checkCid(cid);
		getContract(model, cid);
		List<SysCode> bears = codeService.getCodeByType("lessor_bear");
		model.addAttribute("sysCodeList", bears);
		return JSP_DIR + STEP4;
	}

	/**
	 * 新建合同step4
	 * 
	 * @param model
	 * @param con：合同
	 * @param houItemFlg：房屋条款
	 * @param houOtherItemFlg 其他条款
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP4, method = RequestMethod.POST)
	public String addStep4(Model model, ConContract con, Integer houItemFlg, Integer houOtherItemFlg) throws Exception {
		try {
			if (houItemFlg == null) {
				con.setHouItemsList(new ArrayList<ConHouseItems>());
			}
			if (houOtherItemFlg == null) {
				con.setConOtherCostsList(new ArrayList<ConOtherCosts>());
			} else {
				checkStep4(con);
			}
			conService.save(con, ConContractService.TYPE_STEP4);
		} catch (ApplicationException ex) {
			logger.error("ConContractController addStep4", ex);
			addError(model, ex.getMessage());
			model.addAttribute("con", con);
			return JSP_DIR + STEP4;
		} catch (Exception ex) {
			logger.error("ConContractController addStep4", ex);
			throw ex;
		}

		return getReturnUrl(con.getId(), STEP5);
	}

	/**
	 * 初始化续约合同
	 * 
	 * @param model
	 * @param cid 合同id
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = RENEWAL, method = RequestMethod.GET)
	public String initRenew(Model model, String cid) throws Exception {
		checkCid(cid);
		ConContract conDB = getContract(model, cid);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(conDB.getEndTime());
		endCal.add(Calendar.DAY_OF_MONTH, 1);
		int totalMonth = conDB.getLeaseTermYear() * 12 + conDB.getLeaseTermMonth();
		conDB.setStartTime(endCal.getTime());
		Calendar newEndCal = (Calendar) endCal.clone();
		newEndCal.add(Calendar.MONTH, totalMonth);
		newEndCal.add(Calendar.DAY_OF_MONTH, -1);
		conDB.setEndTime(newEndCal.getTime());
		model.addAttribute("con", conDB);
		getBears(model, conDB);
		model.addAttribute("isRenewal", true);
		return JSP_DIR + STEP5;
	}

	/**
	 * 续约
	 * 
	 * @param model
	 * @param con 合同
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = RENEWAL, method = RequestMethod.POST)
	public String renew(Model model, ConContract con) throws Exception {
		try {
			User user = UserUtils.getUser();
			String empId = user.getEmpId();
			if (con.getIsNewRecord()) {
				con.setEid(empId);
				con.setBeid(empId);
			} else {
				con.setBeid(empId);
			}
			conService.saveForRenewal(con);
		} catch (ApplicationException ex) {
			logger.error("ConContractController renew", ex);
			addError(model, ex.getMessage());
			getContract(model, con.getId());
			return JSP_DIR + STEP5;
		} catch (Exception ex) {
			logger.error("ConContractController renew", ex);
			throw ex;
		}
		String cid = con.getId();
		return getReturnUrl(cid, STEP5);
	}

	/**
	 * 初始化step5
	 * 
	 * @param model
	 * @param cid 合同id
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:view")
	@RequestMapping(value = STEP5, method = RequestMethod.GET)
	public String addStep5(Model model, String cid) throws Exception {
		checkCid(cid);
		ConContract con = getContract(model, cid);
		getBears(model, con);
		List<SysInfoAtt> infoAttList = infoAttService.findList(cid, InfoAttFileTypeEnum.ONE_SIDE_ENUM.getValue(),
				InfoAttFileTypeEnum.OTHER_SIDE_ENUM.getValue(),InfoAttFileTypeEnum.SECURITY_BOOK_ENUM.getValue(),
				InfoAttFileTypeEnum.STAGES_LESSOR_ENUM.getValue(),InfoAttFileTypeEnum.STAGES_LESSEE_ENUM.getValue());
		con.setInfoAttList(infoAttList);
		// 程序读取加密（防盗链配置的图片方式）
		String time = DateUtils.getDate("HH:mm");
		model.addAttribute("accessKey", MD5.GetMD5Code("dlfc") + time);
		return JSP_DIR + STEP5;
	}

	/**
	 * 点击下载按钮
	 * 
	 * @param model
	 * @param con 合同
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = STEP5, method = RequestMethod.POST)
	public String addStep5(Model model, ConContract con, RedirectAttributes redirAttr) throws Exception {
		try {
			con.setStatus((short) ConStatusEnum.WAIT_UPLOAD_ENUM.getValue());
			conService.save(con, ConContractService.TYPE_STEP5);
		} catch (ApplicationException ex) {
			logger.error("ConContractController addStep5", ex);
			addError(model, ex.getMessage());
			getContract(model, con.getId());
			return JSP_DIR + STEP5;
		} catch (Exception ex) {
			logger.error("ConContractController addStep5", ex);
			throw ex;
		}
		redirAttr.addFlashAttribute("downloadFlag", true);
		return getReturnUrl(con.getId(), STEP5);
	}

	/**
	 * 移动附件并将合同状态设置为"审核中"
	 * 
	 * @param con 合同
	 * @return 网页地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:add")
	@RequestMapping(value = "uploCon", method = RequestMethod.POST)
	public String uploadContract(Model model, ConContract con) throws Exception {
		try {
			conService.saveConAtt(con);
		} catch (ApplicationException ex) {
			logger.error("ConContractController uploadContract", ex);
			addError(model, ex.getMessage());
			getContract(model, con.getId());
			return JSP_DIR + STEP5;
		} catch (Exception ex) {
			logger.error("ConContractController uploadContract", ex);
			throw ex;
		}
		// 程序读取加密（防盗链配置的图片方式）
		String time = DateUtils.getDate("HH:mm");
		model.addAttribute("accessKey", MD5.GetMD5Code("dlfc") + time);
		return getReturnUrl(con.getId(), STEP5);
	}

	/**
	 * 实名认证
	 * 
	 * @param name 姓名
	 * @param idType id类型
	 * @param idNo ID号
	 * @return 提示信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/ckid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult checkIdentity(String name, int idType, String idNo) throws Exception {
		String perId = conService.checkIdentity(name, idType, idNo, true);
		if (StringUtil.isNotEmpty(perId)) {
			// 年龄检测是否满18周岁
			return conService.checkAge(perId);
		} else {
			return ajaxFail(PropertyUtils.getErrorMsg("SYS-0009"));
		}
	}

	/**
	 * 获取身份验证信息
	 * 
	 * @param name 姓名
	 * @param idType id类型
	 * @param idNo 身份证号
	 * @return 提示信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/gbc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getBankCard(String name, int idType, String idNo) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		// 查询身份验证 
		String perId = conService.checkIdentity(name, 1, idNo, true);
		if (StringUtil.isNotEmpty(perId)) {
			UsrUser user = userService.getUserByPerId(perId);
			if (user != null) {
				resultMap.put("mobile", user.getMobile());
			} else {
				resultMap.put("mobile", "");
			}
			return ajaxSuccess("", resultMap);
		} else {
			return ajaxFail(PropertyUtils.getErrorMsg("SYS-0003"));
		}
	}

	/**
	 * 获取可租面积和是否可以整租
	 * 
	 * @param startTime 开始时间
	 * @param endTime 截止时间
	 * @param hid 房源ID
	 * @param cid：合同id
	 * @return 提示信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/gcra", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getCanRentArea(String startTime, String endTime, String hid, String cid) throws Exception {
		Map<String, String> resultMap = conService.getCanRentalArea(startTime, endTime, hid, cid);
		return ajaxSuccess("", resultMap);
	}

	/**
	 * 
	 * 确认下载时，检验可租面积
	 * @param startTime：开始时间
	 * @param endTime：截止时间
	 * @param hid：房源id
	 * @param cid:合同id
	 * @param leaseArea：租赁面积
	 * @param version： 版本
	 * @return 提示信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/gcrad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getCanRentAreaDown(String startTime, String endTime, String hid, String cid, BigDecimal leaseArea, String version) throws Exception {
		try {
			checkStep5(startTime, endTime, hid, cid);
		} catch (ApplicationException e) {
			return ajaxFail(e.getMessage());
		}
		// toLocaleDateString 谷歌浏览器转换时间格式yyyy/MM/dd
		if (startTime.contains("/")) {
			startTime = startTime.replaceAll("/", "-");
			endTime = endTime.replaceAll("/", "-");
		} else if (startTime.contains("年")) {
			// 谷歌浏览器转换时间格式yyyy年MM月dd日
			startTime = startTime.replace("年", "-").replace("月", "-").replace("日", "-");
			endTime = endTime.replace("年", "-").replace("月", "-").replace("日", "-");
		}

		Map<String, String> resultMap = conService.getCanRentalArea(startTime, endTime, hid, cid);
		if (leaseArea.intValue() > Integer.parseInt(resultMap.get("overplusArea"))) {
			ConContract con = new ConContract();
			con.setId(cid);
			con.setStatus((short) ConStatusEnum.CANCEL_ENUM.getValue());
			con.setVersion(Integer.parseInt(version));
			try {
				conService.updateCon(con, ConOptEventEnum.CANCEL_ENUM.getValue(), "取消合同", ConContractService.TYPE_STEP5);
			} catch (ApplicationException ex) {
				return ajaxFail(ex.getMessage());
			}

			return ajaxFail("该房源已被出租，当前合同被取消");
		} else {
			return ajaxSuccess();
		}
	}

	/**
	 * 图片上传
	 * @param file 
	 * @param request
	 * @param response
	 * @return 提示信息
	 */
	@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		boolean success = false;
		String message = FileUpload.checkFile(file);
		String fileName = "";
		String filePath = "";
		String fileRealName = file.getOriginalFilename();
		if (StringUtils.isEmpty(message)) {
			try {
				String extension = getExtension(fileRealName.toLowerCase());
				String destDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
				String parentDirName = "contract";
				File destFile = new File(destDir + parentDirName);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				fileName = UuidUtils.get32UUID() + extension;
				File f = new File(destFile.getAbsoluteFile() + "/" + fileName);
				file.transferTo(f);
				filePath = parentDirName + "/" + fileName;
				success = true;
			} catch (Exception ex) {
				logger.error("ConContractController upload", ex);
				message = "图片上传失败，请稍后重新上传";
			}
		}
		if (success) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("filePath", filePath);
			map.put("fileName", fileName);
			map.put("fileRealName", fileRealName);
			return ajaxSuccess("", map);
		} else {
			return ajaxFail(message);
		}
	}

	/**
	 * 根据ID获取合同详细信息
	 * 
	 * @param model
	 * @param cid 合同id
	 * @return ConContract con
	 * @throws Exception
	 */
	public ConContract getContract(Model model, String cid) throws Exception {
		ConContract con = conService.getById(cid);
		if (con.getLeasePurpose() == null) {
			con.setLeasePurpose((short) RentalPurposeEnum.CIVIL_USE_ENUM.getValue());
		}
		if (con.getRentalMode() == null) {
			con.setRentalMode((short) RentalModeEnum.WHOLE_RENT_ENUM.getValue());
		}
		if (con.getSettlementCycle() == null) {
			con.setSettlementCycle(SettlementCycleEnum.MONTHLY_ENUM.getValue());
		}
		if (con.getLeaseTermYear() == null) {
			con.setLeaseTermYear(1);
		}

		model.addAttribute("con", con);
		return con;
	}

	/**
	 * 获取房主、房客承担费用项
	 * 
	 * @param model
	 * @param con 合同
	 */
	private void getBears(Model model, ConContract con) throws Exception {
		String[] _lesseeBear = con.getOwnerBears();
		List<SysCode> lesseeBear = new ArrayList<SysCode>();// 承租方负担费用
		List<SysCode> leaseBear = new ArrayList<SysCode>();// 出租方承担费用
		List<SysCode> bears = codeService.getCodeByType("lessor_bear");
		if (bears != null) {
			for (SysCode code : bears) {
				boolean flg = true;
				if (_lesseeBear != null) {
					for (String bear : _lesseeBear) {
						if (code.getCode().equals(bear)) {
							lesseeBear.add(code);
							flg = false;
							break;
						}
					}
				}
				if (flg) {
					leaseBear.add(code);
				}
			}
		}
		List<SysCode> lessor_origi = new ArrayList<SysCode>();
		List<SysCode> lessee_origi = new ArrayList<SysCode>();
		List<SysCode> lessor = codeService.getCodeByType("lessor_origi_license");
		List<SysCode> lessee = codeService.getCodeByType("lessee_origi_license");
		String[] LessorCosts = null;
		String LessorIds = con.getLessorIds();
		if (LessorIds != null) {
			LessorCosts = LessorIds.split(",");
			for (int i = 0; i < LessorCosts.length; i++) {
				for (SysCode code : lessor) {
					if (LessorCosts[i].equals(code.getCode())) {
						lessor_origi.add(code);
					}
				}
			}
		}
		String[] LesseeCosts = null;
		String LesseeIds = con.getLesseeIds();
		if (LesseeIds != null) {
			LesseeCosts = LesseeIds.split(",");
			for (int i = 0; i < LesseeCosts.length; i++) {
				for (SysCode code : lessee) {
					if (LesseeCosts[i].equals(code.getCode())) {
						lessee_origi.add(code);
					}
				}
			}
		}

		model.addAttribute("LessorCosts", lessor_origi);
		model.addAttribute("LesseeCosts", lessee_origi);
		model.addAttribute("leaseBear", lesseeBear);
		model.addAttribute("lesseeBear", leaseBear);
	}

	/**
	 * 设置时间控件显示的时间范围
	 * 
	 * @param model
	 * @throws Exception
	 */
	private void setCalender(Model model) throws Exception {
		Calendar beginCal = DateUtils.getNowCal();
		beginCal.add(Calendar.DAY_OF_MONTH, -30);
		model.addAttribute("beginCal", DateUtils.dateToStr(beginCal.getTime(), DateUtils.DATE_Y_M_D_PATERN));
	}

	private String getReturnUrl(String cid, String url) throws Exception {
		return "redirect:" + super.adminPath + "/con" + url + "?cid=" + cid;
	}

	private void checkStep1(ConContract con) throws Exception {
		if (StringUtils.isBlank(con.getLessorName())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0040"));
		}
		if (con.getLessorName().length() > 100) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0041"));
		}
		if (StringUtils.isBlank(con.getLessorIdNo())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0042"));
		}
		if (con.getLessorIdNo().length() > 50) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0043"));
		}
		if (StringUtils.isBlank(con.getLessorMobile())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0044"));
		}
		/*UsrUserBankcard lessorUserBankcard = con.getUserBankcardList().get(0);
		if (StringUtils.isBlank(lessorUserBankcard.getBankNum())) {
			throw new ApplicationException(PropertyUtil.getErrorMsg("SYS-0045"));
		}*/
		if (StringUtils.isBlank(con.getLesseeName())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0046"));
		}
		if (con.getLesseeName().length() > 100) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0047"));
		}
		if (StringUtils.isBlank(con.getLesseeIdNo())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0048"));
		}
		if (con.getLesseeIdNo().length() > 50) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0049"));
		}
		if (StringUtils.isBlank(con.getLesseeMobile())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0050"));
		}
		if (con.getLessorIdNo().equals(con.getLesseeIdNo())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0051"));
		}
		/*UsrUserBankcard lesseeUserBankcard = con.getUserBankcardList().get(1);
		if (StringUtils.isBlank(lesseeUserBankcard.getBankNum())) {
			throw new ApplicationException(PropertyUtil.getErrorMsg("SYS-0052"));
		}*/
	}

	private void checkStep2(ConContract con) throws Exception {
		// 判断如果是租金分期是否在平台已创建用户
		if ("01".equals(con.getStages())) {
			ConContract conDB = conService.getById(con.getId());
			UsrUser lessor = userService.getUserByPerId(conDB.getLessorPid());
			if (lessor == null) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0108"));
			}
			UsrUser lessee = userService.getUserByPerId(conDB.getLesseePid());
			if (lessee == null) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0109"));
			}
		}
		if (StringUtils.isEmpty(con.getHid())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0053"));
		}
		if (con.getStartTime() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0054"));
		} else if (con.getEndTime() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0055"));
		} else if (con.getLeaseTermYear() == null || con.getLeaseTermMonth() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0056"));
		} else if (con.getLeaseTermYear() == 0 && con.getLeaseTermMonth() == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0057"));
		} else if (con.getRentalMode() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0058"));
		} else if (con.getLeasePurpose() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0059"));
		} else if (con.getRentalMode() == RentalModeEnum.PART_RENT_ENUM.getValue() && StringUtils.isBlank(con.getLeaseDomain())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0060"));
		} else if (con.getRentalMode() == RentalModeEnum.PART_RENT_ENUM.getValue() && con.getLeaseDomain().length() > 50) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0061"));
		} else if (con.getRentalMode() == RentalModeEnum.PART_RENT_ENUM.getValue() && con.getLeaseArea() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0062"));
		} else if (con.getRentalMode() == RentalModeEnum.PART_RENT_ENUM.getValue() && con.getLeaseArea().doubleValue() < 4) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0063"));
		} else {
			checkHouseUsers(con.getHouUserList());
		}
	}

	private void checkStep3(ConContract con) throws Exception {
		Integer monthlyRent = con.getMonthlyRent();
		if (monthlyRent == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0064"));
		}
		if (monthlyRent == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0065"));
		}
		if (monthlyRent > 1000000) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0066"));
		}
		Integer cycle = con.getSettlementCycle();
		if (cycle == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0067"));
		}
		if (cycle != SettlementCycleEnum.MONTHLY_ENUM.getValue() && cycle != SettlementCycleEnum.QUARTER_ENUM.getValue() && cycle != SettlementCycleEnum.HALF_YEAR_ENUM.getValue() && cycle != SettlementCycleEnum.YEAR_ENUM.getValue()) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0068"));
		}
		Integer payTimeDay = con.getPayTimeDay();
		if (payTimeDay == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0069"));
		}
		if (payTimeDay == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0070"));
		}
		if (payTimeDay > 31) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0071"));
		}
		BigDecimal agencyFee = con.getAgencyFee();
		if (agencyFee == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0072"));
		}
		if (agencyFee.doubleValue() == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0073"));
		}
		if (agencyFee.doubleValue() > 1000000) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0074"));
		}
		BigDecimal depositAmt = con.getDepositAmt();
		if (depositAmt == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0075"));
		}
		if (depositAmt.doubleValue() < 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0076"));
		}
		String OwnerBearOther = con.getOwnerBearOther();
		if (OwnerBearOther != null && OwnerBearOther.length() > 100) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0077"));
		}
	}

	private void checkHouseUsers(List<ConHouseUser> houseUsers) throws Exception {
		if (houseUsers == null || houseUsers.size() == 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0078"));
		} else {
			for (int i = 0; i < houseUsers.size(); i++) {
				checkHouseUser(houseUsers.get(i), i + 1);
			}
		}
	}

	private void checkHouseUser(ConHouseUser houseUser, int num) throws Exception {
		Object[] arguments = { num };
		if (StringUtils.isBlank(houseUser.getName())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0079", arguments));
		} else if (houseUser.getName().length() > 30) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0080", arguments));
		} else if (StringUtils.isBlank(houseUser.getMobile())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0081", arguments));
		} else if (!houseUser.getMobile().matches(Const.REGULAR_MOBILE)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0082", arguments));
		} else if (houseUser.getIdType() == null) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0083", arguments));
		} else if (StringUtils.isBlank(houseUser.getIdNo())) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0084", arguments));
		} else if (houseUser.getIdNo().length() > 50) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0085", arguments));
		} else if (!houseUser.getIdNo().matches(Const.REGULAR_ID_NO)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0086", arguments));
		}
	}

	private void checkStep4(ConContract con) throws Exception {
		for (ConOtherCosts costs : con.getConOtherCostsList()) {
			if (costs.getItem() != null && !costs.getItem().isEmpty()) {
				if (costs.getItem().length() > 50) {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0087"));
				}
			}
			if (costs.getUnit() != null && !costs.getUnit().isEmpty()) {
				if (costs.getUnit().length() > 50) {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0088"));
				}
			}
			if (costs.getPrice() != null) {
				if (costs.getPrice().doubleValue() < 0 || costs.getPrice().doubleValue() > 999999) {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0089"));
				}
			}
			if (costs.getStime() != null) {
				if (DateUtils.strToDate(DateUtils.formatDate(costs.getStime()), DateUtils.DATE_Y_M_D_PATERN) == null) {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0090"));
				}
			}
			if (costs.getScount() != null && !costs.getScount().isEmpty()) {
				if (costs.getScount().length() > 8) {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0091"));
				}
			}
		}
	}

	private String getExtension(String fileName) {
		String extension = "";
		int index = fileName.indexOf(".");
		if (index > 0) {
			extension = fileName.substring(index);
		}
		return extension;
	}

	private void checkCid(String cid) throws Exception {
		if (StringUtils.isBlank(cid)) {
			throw new AppRuntimeException(PropertyUtils.getErrorMsg("SYS-0005"));
		}
	}

	/**
	 * 移交合同
	 * 
	 * @param request
	 * @return 重定向
	 * @throws Exception
	 */
	@RequestMapping(value = "tran", method = RequestMethod.POST)
	public String tranfer(HttpServletRequest request) throws Exception {
		String checkHid = (String) getRequestParameter("transfer");
		String empId = (String) getRequestParameter("emp_id");

		String[] hisArr = checkHid.split("_");
		String id = "";
		for (int i = 0; i < hisArr.length; i++) {
			id = hisArr[i];
			conService.saveTransfer(id, empId);
		}

		return "redirect:" + super.adminPath + "/con/clist";
	}

	/**
	 * 合同取消状态
	 * @param redirAttr
	 * @return 重定向
	 * @throws Exception
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String delCon(RedirectAttributes redirAttr) throws Exception {
		String id = (String) getRequestParameter("cid");
		try {
			checkCid(id);
			conService.deleteCon(id);
		} catch (ApplicationException ex) {
			logger.error("ConContractController delCon", ex);
			redirAttr.addFlashAttribute("error", ex.getMessage());
		} catch (Exception ex) {
			logger.error("ConContractController delCon", ex);
			throw ex;
		}
		return "redirect:" + super.adminPath + "/con/clist";

	}

	/**
	 * 合同删除
	 * @param model
	 * @param cid 合同id
	 * @return 重定向
	 * @throws Exception
	 */
	@RequestMapping(value = "/dell", method = RequestMethod.POST)
	public String contdel(Model model, String cid) throws Exception {
		User user = UserUtils.getUser();
		ConContract conDB = conService.getById(cid);
		short oldstatus = conDB.getStatus();
		UsrDelInfo usrDelInfo = new UsrDelInfo();
		usrDelInfo.setDelId(cid);
		usrDelInfo.setUepId(user.getId());
		usrDelInfo.setId(usrDelInfo.getUuid());

		userDelInfoService.insert(usrDelInfo);
		conService.saveLog(cid, oldstatus, ConOptEventEnum.CANCEL_ENUM.getValue(), "删除合同");

		return "redirect:" + super.adminPath + "/con/clist";
	}

	/**
	 * 新建合同第5步，检验方法
	 * 
	 * @param startTime 起始时间
	 * @param endTime 结束时间
	 * @param hid 房源id
	 * @param cid 合同id
	 * @throws Exception
	 */
	private void checkStep5(String startTime, String endTime, String hid, String cid) throws Exception {
		if (StringUtils.isBlank(startTime)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0093"));
		} else if (StringUtils.isBlank(endTime)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0093"));
		} else if (StringUtils.isBlank(hid)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0093"));
		} else if (StringUtils.isBlank(cid)) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0093"));
		}
	}

	/**
	 * 押金监管协议详情
	 * 
	 * @return 网站地址
	 * @throws Exception
	 */
	@RequestMapping(value = "/dinfo")
	public String depositInfo() throws Exception {
		return "dlfc/contract/dInfo";
	}
	
	/**
	 * 保险条款详情
	 * 
	 * @return 地址
	 * @throws Exception
	 */
	@RequestMapping(value = "/insurance")
	public String insuranceInfo() throws Exception {
		return "dlfc/contract/insurance";
	}

	/**
	 * 房客租金分期协议条款
	 * 
	 * @return 返回注册条款的页面
	 */
	@RequestMapping(value = "eesc", method = RequestMethod.GET)
	public String LesseeStagesclause() {

		return "dlfc/contract/lesseeclause";
	}
	
	/**
	 * 房主租金分期协议条款
	 * 
	 * @return 返回注册条款的页面
	 */
	@RequestMapping(value = "orsc", method = RequestMethod.GET)
	public String LessorStagesclause() {

		return "dlfc/contract/lessorclause";
	}
	
	/**
	 * 其他方式
	 * 
	 * @return 返回其他方式的页面
	 */
	@RequestMapping(value = "ew", method = RequestMethod.GET)
	public String ElseWay() {

		return "dlfc/contract/elseway";
	}
}
