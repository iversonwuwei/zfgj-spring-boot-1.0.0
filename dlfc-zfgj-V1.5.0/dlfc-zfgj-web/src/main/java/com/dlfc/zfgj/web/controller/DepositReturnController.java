/**
* @name: DepositReturnController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月18日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月18日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConDepositDistAgr;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.service.DepositReturnService;
import com.dlfc.zfgj.service.SysCodeService;
import com.dlfc.zfgj.service.SysInfoAttService;
import com.housecenter.dlfc.zfgj.common.enums.DepositDistributeEnum;
import com.housecenter.dlfc.zfgj.common.enums.DepositDistributeStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;
import com.housecenter.dlfc.zfgj.common.utils.FileUpload;

/**
 * @name: DepositReturnController
 * @description: 押金返还
 * @version 1.0
 * @author hanjiaqi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/deposit")
public class DepositReturnController extends BaseController {
	/** admin路径 */
	private String parentUrl = Global.getConfig("adminPath");
	/** 押金返还service */
	@Autowired
	private DepositReturnService depositReturnService;
	/** 系统codeservice */
	@Autowired
	private SysCodeService sysCodeService;
	/** 图片service */
	@Autowired
	private SysInfoAttService sysInfoAttService;

	/**
	 * 押金返还查看step1
	 * @param cid 合同ID
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return String 请求地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:deposit")
	@RequestMapping(value = "/drone", method = RequestMethod.GET)
	public String DepositReturnStepOne(String cid, Model model) throws Exception {
		// 获取合同
		ConContract conContract = depositReturnService.findByCid(cid);
		List<SysCode> depositGainReasonList = new ArrayList<SysCode>();
		// 获取deposit_gain_reason的code列表
		depositGainReasonList = sysCodeService.getCodeByType("deposit_gain_reason");
		if (conContract.getConDepositDistAgr() != null) {
			conContract.setDepositType(conContract.getConDepositDistAgr().getChargeReason());
			model.addAttribute("version", conContract.getConDepositDistAgr().getVersion());
		} else {
			conContract.setDepositType(0);
			model.addAttribute("version", -1);
		}

		model.addAttribute("depositGainReasonList", depositGainReasonList);
		model.addAttribute("cid", cid);
		model.addAttribute("conContract", conContract);
		model.addAttribute("createTime", DateUtils.formatDateTime(conContract.getCreateTime()));
		model.addAttribute("startTime", DateUtils.formatDate(conContract.getStartTime()));
		model.addAttribute("endTime", DateUtils.formatDate(conContract.getEndTime()));

		return "dlfc/contract/derestep1";
	}

	/**
	 * 押金返还记录插入
	 * @param cid 合同id
	 * @param version 版本
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param conContract 合同实体
	 * @return String 请求地址
	 */
	@RequiresPermissions("emp:cont:deposit")
	@RequestMapping(value = "/idi", method = RequestMethod.POST)
	public String InsertDepositInfor(String cid, int version, Model model, ConContract conContract) throws Exception {
		// 获取合同
		ConContract con = depositReturnService.findByCid(cid);
		try {
			checkDeposit(con, conContract.getConDepositDistAgr().getDeductAmt(),
					conContract.getConDepositDistAgr().getDescription());
			/*
			 * ConDepositDistAgr conDepositDistAgr = new ConDepositDistAgr();
			 * conDepositDistAgr.setDeductAmt(rentalDeduct);
			 * conDepositDistAgr.setDescription(reason);
			 * conDepositDistAgr.setChargeReason(Integer.valueOf(depositType));
			 */
			conContract.getConDepositDistAgr()
					.setStatus((short) DepositDistributeStatusEnum.DEPOSIT_DISTRIBUTE_STATUS_CREATE_ENUM.getValue());
			// 押金分配表中没有此记录，插入一条新记录	
			if (conContract.getConDepositDistAgr().getId() == null
						|| "".equals(conContract.getConDepositDistAgr().getId())) {
				String no = con.getNo().replace(Const.BUSINESS_NAME_PREFIX_CONTRACT,
						Const.BUSINESS_NAME_PREFIX_DEPOSIT);
				conContract.getConDepositDistAgr().setNo(no);
				conContract.getConDepositDistAgr().setCid(cid);
				depositReturnService.insertSelective(conContract.getConDepositDistAgr());
			} else {
				conContract.getConDepositDistAgr().setId(con.getConDepositDistAgr().getId());
				conContract.getConDepositDistAgr().setNo(con.getConDepositDistAgr().getNo());
				conContract.getConDepositDistAgr().setVersion(con.getConDepositDistAgr().getVersion());
				// 更新排他
				if (conContract.getConDepositDistAgr().getVersion() == version) {
					conContract.getConDepositDistAgr().setVersion(version + 1);
					depositReturnService.updateDeposit(conContract.getConDepositDistAgr(),
							DepositDistributeEnum.DEPOSIT_DISTRIBUTE_CREATE_ENUM.getValue(),
							conContract.getConDepositDistAgr().getStatus());
				} else {
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
				}
			}
		} catch (ApplicationException ex) {
			logger.error("DepositReturnController InsertDepositInfor", ex);
			addError(model, ex.getMessage());
			List<SysCode> depositGainReasonList = sysCodeService.getCodeByType("deposit_gain_reason");
			model.addAttribute("conContract", con);
			model.addAttribute("depositGainReasonList", depositGainReasonList);
			model.addAttribute("createTime", DateUtils.formatDateTime(con.getCreateTime()));
			model.addAttribute("startTime", DateUtils.formatDate(con.getStartTime()));
			model.addAttribute("endTime", DateUtils.formatDate(con.getEndTime()));
			model.addAttribute("version", version);
			model.addAttribute("cid", cid);
			return "dlfc/contract/derestep1";
		}
		return "redirect:" + parentUrl + "/deposit/drtwo?cid=" + cid + "&lastFlag=" + 0;
	}

	/**
	 * 押金返还查看step2
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param cid 合同id
	 * @param lastFlag 下载pdf的标记  1:下载 ,0:不下载
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return String 请求地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:deposit")
	@RequestMapping(value = "/drtwo", method = RequestMethod.GET)
	public String DepositReturnStepTwo(String cid, String lastFlag, Model model) throws Exception {
		//获取合同
		ConContract conContractAfter = depositReturnService.findByCid(cid);
		//获取图片列表
		List<SysInfoAtt> sysInfoAttCon = sysInfoAttService.findList(conContractAfter.getConDepositDistAgr().getId(),
				InfoAttFileTypeEnum.DEPO_DIST_AGR_ENUM.getValue());
		//判断图片是否存在
		if (sysInfoAttCon != null && sysInfoAttCon.size() != 0) {
			conContractAfter.setInfoAttList(sysInfoAttCon);
		}
		model.addAttribute("DepositTypeName", conContractAfter.getConDepositDistAgr().getDepositTypeName());
		model.addAttribute("cid", cid);
		model.addAttribute("lastFlag", lastFlag);
		model.addAttribute("conContract", conContractAfter);

		model.addAttribute("createTime", DateUtils.formatDateTime(conContractAfter.getCreateTime()));
		model.addAttribute("startTime", DateUtils.formatDate(conContractAfter.getStartTime()));
		model.addAttribute("endTime", DateUtils.formatDate(conContractAfter.getEndTime()));
		return "dlfc/contract/derestep2";
	}

	/**
	 * 押金分配协议下载
	 * @param id 合同id
	 * @param cType 合同类型 6:合同 7:解约协议 8:押金分配
	 * @return String 请求地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:deposit")
	@RequestMapping(value = "/pdl", method = RequestMethod.POST)
	public String download(String id, int version, Model model) throws Exception {
		//获取合同
		ConContract conContract = depositReturnService.findByCid(id);
		ConDepositDistAgr conDepositDistAgr = new ConDepositDistAgr();
		conDepositDistAgr.setDeductAmt(conContract.getConDepositDistAgr().getDeductAmt());
		conDepositDistAgr.setDescription(conContract.getConDepositDistAgr().getDescription());
		conDepositDistAgr.setChargeReason(conContract.getConDepositDistAgr().getChargeReason());
		conDepositDistAgr.setStatus((short) DepositDistributeStatusEnum.DEPOSIT_DISTRIBUTE_STATUS_SUBMIT_ENUM.getValue());
		conDepositDistAgr.setId(conContract.getConDepositDistAgr().getId());
		conDepositDistAgr.setNo(conContract.getConDepositDistAgr().getNo());
		// 更新排他
		if (conContract.getConDepositDistAgr().getVersion() == version) {
			conDepositDistAgr.setVersion(version + 1);
			depositReturnService.updateDeposit(conDepositDistAgr,
					DepositDistributeEnum.DEPOSIT_DISTRIBUTE_SUBMIT_ENUM.getValue(),
					conContract.getConDepositDistAgr().getStatus());
		} else {
			// 此类暂时没用所以没修改
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
		}
		return "redirect:" + parentUrl + "/deposit/drtwo?cid=" + id + "&lastFlag=" + 1;
	}

	/**
	 * 押金分配协议上传并将合同状态设置为"审核中"
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param con 合同实体
	 * @return String 请求地址
	 * @throws Exception
	 */
	@RequiresPermissions("emp:cont:deposit")
	@RequestMapping(value = "/pul", method = RequestMethod.POST)
	public String upload(Model model, ConContract con) throws Exception {
		try {
			//获取合同
			ConContract conContract = depositReturnService.findByCid(con.getId());
			depositReturnService.saveConAtt(con);
			ConDepositDistAgr conDepositDistAgr = new ConDepositDistAgr();
			conDepositDistAgr.setDeductAmt(conContract.getConDepositDistAgr().getDeductAmt());
			conDepositDistAgr.setDescription(conContract.getConDepositDistAgr().getDescription());
			conDepositDistAgr.setChargeReason(conContract.getConDepositDistAgr().getChargeReason());
			conDepositDistAgr.setStatus((short) DepositDistributeStatusEnum.DEPOSIT_DISTRIBUTE_STATUS_VERIFYING_ENUM.getValue());
			conDepositDistAgr.setId(conContract.getConDepositDistAgr().getId());
			conDepositDistAgr.setNo(conContract.getConDepositDistAgr().getNo());
			// 更新排他
			if (conContract.getConDepositDistAgr().getVersion().intValue() == con.getConDepositDistAgr().getVersion()
					.intValue()) {
				conDepositDistAgr.setVersion(con.getConDepositDistAgr().getVersion() + 1);
	
				//将押金分配协议状态置为等待审核，并插入log表中
				depositReturnService.updateDeposit(conDepositDistAgr,
						DepositDistributeEnum.DEPOSIT_DISTRIBUTE_SUBMIT_ENUM.getValue(),
						conContract.getConDepositDistAgr().getStatus());
			} else {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
			}
		} catch (ApplicationException ex) {
			logger.error("DepositReturnController upload", ex);
			addError(model, ex.getMessage());
		} catch (Exception ex) {
			logger.error("DepositReturnController upload", ex);
			throw ex;
		}
		return "redirect:" + super.adminPath + "/deposit/drtwo?cid=" + con.getId();
	}

	/**
	 * 上传图片
	 * @param file 文件
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return AjaxResult
	 */
	@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		//是否上传成功
		boolean success = false;
		//校验文件
		String message = FileUpload.checkFile(file);
		//文件名称
		String fileName = "";
		//文件地址
		String filePath = "";
		//文件真实名称
		String fileRealName = file.getOriginalFilename();
		//判断非空
		if (StringUtils.isEmpty(message)) {
			try {
				//获取文件拓展名
				String extension = getExtension(fileRealName.toLowerCase());
				//获取文件临时路径
				String destDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
				//存放目录
				String parentDirName = "contract";
				//创建文件
				File destFile = new File(destDir + parentDirName);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				//随机文件名
				fileName = UuidUtils.get32UUID() + extension;
				File f = new File(destFile.getAbsoluteFile() + "/" + fileName);
				file.transferTo(f);
				filePath = parentDirName + "/" + fileName;
				success = true;
			} catch (Exception ex) {
				logger.error("DepositReturnController upload", ex);
				message = "图片上传失败，请稍后重新上传";
			}
		}
		//判断图片是否上传成功
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
	 * 获取文件拓展名
	 * @param fileName 文件名称
	 * @return String 文件拓展名
	 */
	private String getExtension(String fileName) {
		String extension = "";
		//获取“.”的位置
		int index = fileName.indexOf(".");
		if (index > 0) {
			//从index位置开始剪切
			extension = fileName.substring(index);
		}
		return extension;
	}

	/**
	 * 
	 * @param conContract 合同实体
	 * @param rentalDeduct 履约保证金
	 * @param reason 原因
	 * @return void
	 */
	private void checkDeposit(ConContract conContract, BigDecimal rentalDeduct, String reason)
			throws ApplicationException {
		//判断保证金是否为空
		if (rentalDeduct == null) {
			rentalDeduct = BigDecimal.valueOf(0);
		}
		//校验保证金金额
		if (rentalDeduct.intValue() > conContract.getDepositAmt().intValue()) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0021"));
		}
		//校验保证金金额
		if (rentalDeduct.intValue() < 0) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0021"));
		}
		//校验保证金长度
		if (rentalDeduct.toString().length() > 10) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0022"));
		}
		//校验原因长度
		if (reason.length() > 100) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0023"));
		}
	}

}
