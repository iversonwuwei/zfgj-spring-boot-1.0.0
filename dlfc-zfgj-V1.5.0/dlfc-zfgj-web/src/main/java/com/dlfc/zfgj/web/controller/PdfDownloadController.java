/**
* @name: PdfDownloadController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月20日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月20日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.FileDownload;
import com.dlfc.admin.common.utils.NumberToCN;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.pdf.pdf.bean.PdfCancelContract;
import com.dlfc.pdf.pdf.bean.PdfConContract;
import com.dlfc.pdf.pdf.bean.PdfDepositContract;
import com.dlfc.pdf.utils.ResourceLoader;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.HouCoOwner;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.mapper.HouCoOwnerMapper;
import com.dlfc.zfgj.service.ConContractService;
import com.dlfc.zfgj.service.SysCodeService;
import com.dlfc.zfgj.service.SysInfoAttService;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;
import com.housecenter.dlfc.zfgj.common.utils.PdfGeneratorUtils;

/**
 * @name: PdfDownloadController
 * @description: Pdf下载
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/dl")
public class PdfDownloadController extends BaseController {
	/**静态变量合同*/
	private static final String CONTRACT = "合同";
	/**静态变量解约协议*/
	private static final String CANCEL_CONTRACT = "解约协议";
	/**静态变量押金分配*/
	private static final String DEPOSIT_CONTRACT = "押金分配";

	/**三方合同service*/
	@Autowired
	private ConContractService conService;
	/**图片service*/
	@Autowired
	private SysInfoAttService sysInfoAttService;
	/**系统codeservice*/
	@Autowired
	private SysCodeService sysCodeService;
	/**房源共有人*/
	@Autowired
	private HouCoOwnerMapper ownerDao;

	/**
	 * pdf下载
	 * @param id 合同ID
	 * @param cType 合同类型 6:合同 7:解约协议 8:押金分配
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 */
	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public String pdfDownload(String id, int cType, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		String lid = id;
		// 获取合同
		ConContract con = conService.getById(id);
		// 服务地址
		String serverPath = "https://" + request.getServerName();
		// 判断类型
		switch (cType) {
		case 6:
			// 合同
			List<SysInfoAtt> sysInfoAttCon = sysInfoAttService.findList(id,
					InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue());
			HouCoOwner example = new HouCoOwner();
			example.setHid(con.getHid());
			// 共有人列表
			List<HouCoOwner> ownerList = ownerDao.findCoOwner(example);
			con.setOwnerList(ownerList);
			con.setSettlementCycleList(
					// 组装支付列表
					conService.dateList(con.getStartTime(), con.getEndTime(), con.getSettlementCycle(),
							con.getMonthlyRent().doubleValue(), con.getLeaseTermYear(), con.getLeaseTermMonth()));
			// 判断是否含有pdf文件
			try {
				checkPdf(lid, sysInfoAttCon, con, InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue(), serverPath, response);
			} catch (ApplicationException e) {
				addError(redirectAttributes,e.getMessage());
				return "redirect:" + adminPath + "/contp/step5?cid="+lid;
			}
			break;
		}
		return "redirect:" + adminPath + "/contp/step5?cid="+lid;
	}

	/**
	 * 判断附件表是否存有pdf
	 * 
	 * @param lid 合同ID
	 * @param sysInfoAtts 图片列表
	 * @param con 三方合同
	 * @param type 协议类型
	 * @param serverPath 服务器地址
	 * @param response 服务端返还给客户机的一个响应内容对象
	 */
	private void checkPdf(String lid, List<SysInfoAtt> sysInfoAtts, ConContract con, int type, String serverPath,
			HttpServletResponse response) throws ApplicationException{
		// 生成pdf路径
		String outputFile = null;
		// 模板路径
		String template = "";
		// 图片映射地址
		String destDir = PropertyUtils.getSysVal("pdf.file.real.directory");
		// 合同附件不存在
		if (sysInfoAtts == null || sysInfoAtts.size() == 0) {
			SysInfoAtt newSysInfoAtt = new SysInfoAtt();

			// 服务器存储合同文件的路径
			switch (type) {
			case 6:
				// 判断是否是租金分期
				if ("01".equals(con.getStages())) {
					template = "templates/pdfTemplates/threeContractTemStages1.0.0.html";
				} else {
					template = "templates/pdfTemplates/threeContractTem1.0.0.html";
				}
				// 填充三方合同数据
				PdfConContract pcc = fillContractData(con, serverPath);
				outputFile = PdfGeneratorUtils.generatePdf(destDir, outputFile, pcc, con.getId(), template);
				if(outputFile == null){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0106"));
				}
				newSysInfoAtt.setFileRealName(CONTRACT);
				break;
			case 7:
				template = "templates/pdfTemplates/pdfCancelContract.html";
				PdfCancelContract pCancel = new PdfCancelContract();
				pCancel.setImagePath(ResourceLoader.getPath("templates/pdfTemplates/images"));
				outputFile = PdfGeneratorUtils.generatePdf(destDir, outputFile, pCancel, CANCEL_CONTRACT, template);
				newSysInfoAtt.setFileRealName(CANCEL_CONTRACT);
				break;
			case 8:
				template = "templates/pdfTemplates/pdfDepositContract.html";
				PdfDepositContract pdc = new PdfDepositContract();
				pdc.setNo(con.getNo());
				pdc.setLessorName(con.getLessorName());
				pdc.setLessorIdNo(con.getLessorIdNo());
				pdc.setLessorIdType(con.getLessorIdType());

				pdc.setImagePath(ResourceLoader.getPath("templates/pdfTemplates/images"));
				outputFile = PdfGeneratorUtils.generatePdf(destDir, outputFile, pdc, DEPOSIT_CONTRACT, template);
				newSysInfoAtt.setFileRealName(DEPOSIT_CONTRACT);
				break;
			}

			// pdf插入到数据库
			newSysInfoAtt.setFileType(type);
			newSysInfoAtt.setFileName(con.getNo() + ".pdf");
			newSysInfoAtt.setFilePath(outputFile);
			newSysInfoAtt.setLid(lid);
			sysInfoAttService.insertPdf(newSysInfoAtt);
		} else {
			outputFile = sysInfoAtts.get(0).getFilePath();
		}

		// outputFile是指欲下载的文件的路径。
		outputFile = destDir + "/" + outputFile;

		FileDownload.download(outputFile, response);
	}

	/**
	 * 填充三方合同数据
	 * 
	 * @param con 三方合同
	 * @param serverPath 服务地址
	 * @return pdf合同实体
	 */
	private PdfConContract fillContractData(ConContract con, String serverPath) {
		PdfConContract pcc = new PdfConContract();
		//设置pdf合同属性值
		pcc.setNo(con.getNo());
		pcc.setLessorName(con.getLessorName());
		pcc.setLessorIdType(con.getLessorIdTypeName());
		pcc.setLessorIdNo(con.getLessorIdNo());
		if (("").equals(con.getLessorAddress())) {
			pcc.setLessorAddress(null);
		} else {
			pcc.setLessorAddress(con.getLessorAddress());
		}
		pcc.setLessorMobile(con.getLessorMobile());

		if (("").equals(con.getLessorEmail())) {
			pcc.setLessorEmail(null);
		} else {
			pcc.setLessorEmail(con.getLessorEmail());
		}

		if (("").equals(con.getLessorBackupName())) {
			pcc.setLessorBakName(null);
		} else {
			pcc.setLessorBakName(con.getLessorBackupName());
		}

		if (("").equals(con.getLessorBackupTel())) {
			pcc.setLessorBakMobile(null);
		} else {
			pcc.setLessorBakMobile(con.getLessorBackupTel());
		}

		pcc.setLesseeName(con.getLesseeName());
		pcc.setLesseeIdType(con.getLesseeIdTypeName());
		pcc.setLesseeIdNo(con.getLesseeIdNo());
		if (("").equals(con.getLesseeAddress())) {
			pcc.setLesseeAddress(null);
		} else {
			pcc.setLesseeAddress(con.getLesseeAddress());
		}
		pcc.setLesseeMobile(con.getLesseeMobile());
		if (("").equals(con.getLesseeEmail())) {
			pcc.setLesseeEmail(null);
		} else {
			pcc.setLesseeEmail(con.getLesseeEmail());
		}
		if (("").equals(con.getLesseeBackupName())) {
			pcc.setLesseeBakName(null);
		} else {
			pcc.setLesseeBakName(con.getLesseeBackupName());
		}
		if (("").equals(con.getLesseeBackupTel())) {
			pcc.setLesseeBakMobile(null);
		} else {
			pcc.setLesseeBakMobile(con.getLesseeBackupTel());
		}

		pcc.setCompanyName(con.getAgtEmpInfo().getAgtCompInfo().getFullName());
		pcc.setCompanyAddress(con.getAgtEmpInfo().getAgtCompInfo().getAddress());
		pcc.setCompanyTel(con.getAgtEmpInfo().getAgtCompInfo().getTel());

		pcc.setManageName(con.getAgtEmpInfo().getSysPerson().getName());
		pcc.setManageIdNo(con.getAgtEmpInfo().getSysPerson().getIdNo());
		pcc.setManageMobile(con.getAgtEmpInfo().getPhone());

		pcc.setHouseAddr(con.getHouseAddr());
		pcc.setHouseType(con.getHouHouseInfo().getHouStructureName());
		pcc.setProperType(con.getPropertyIdTypeName());
		pcc.setHousePropertyIdNo(con.getHouHouseInfo().getNo());
		pcc.setHouseBuildingArea(con.getBuildingArea());
		pcc.setLeaseArea(con.getLeaseArea());
		pcc.setLeasePurpose(con.getRentalPurposeName());

		pcc.setHouCoOwnerList(con.getOwnerList());
		pcc.setConHouseUserList(con.getHouUserList());

		pcc.setLeaseTermYear(con.getLeaseTermYear());
		pcc.setLeaseTermMonth(con.getLeaseTermMonth());
		pcc.setYear(DateUtils.dateToStr(con.getStartTime(), "yyyy"));
		pcc.setMonth(DateUtils.dateToStr(con.getStartTime(), "MM"));
		pcc.setDay(DateUtils.dateToStr(con.getStartTime(), "dd"));
		pcc.setEndYear(DateUtils.dateToStr(con.getEndTime(), "yyyy"));
		pcc.setEndMonth(DateUtils.dateToStr(con.getEndTime(), "MM"));
		pcc.setEndDay(DateUtils.dateToStr(con.getEndTime(), "dd"));
		pcc.setMonthlyRent(con.getMonthlyRent());
		pcc.setMonthlyRentCN(NumberToCN.number2CNMontrayUnit(new BigDecimal(con.getMonthlyRent())));
		pcc.setSettlementCycle(con.getSettlementCycleName());
		pcc.setOwnerBear(con.getOwnerBear());

		if (con.getOwnerBearOther() != null && (!con.getOwnerBearOther().isEmpty())) {
			if (con.getOwnerBear() != null && (!con.getOwnerBear().isEmpty())) {
				pcc.setOwnerBear(pcc.getOwnerBear() + Const.SEPARATOR + Const.OWNER_BEAR_OTHER_FLAG);
			} else {
				pcc.setOwnerBear(String.valueOf(Const.OWNER_BEAR_OTHER_FLAG));
			}
		}

		pcc.setOwnerBearOther(con.getOwnerBearOther());
		pcc.setLesseeBear(leaseBear(con.getOwnerBears()));
		pcc.setDepositAmt(con.getDepositAmt());
		pcc.setDepositAmtCN(NumberToCN.number2CNMontrayUnit(con.getDepositAmt()));
		pcc.setAgencyFee(con.getAgencyFee());
		pcc.setAgencyFeeCN(NumberToCN.number2CNMontrayUnit(con.getAgencyFee()));
		pcc.setLessorIds(getName(con.getLessorIds(), sysCodeService.getCodeByType("lessor_origi_license")));
		pcc.setLessorIdsOther(con.getLessorIdsOther());
		pcc.setLesseeIds(getName(con.getLesseeIds(), sysCodeService.getCodeByType("lessee_origi_license")));
		pcc.setLesseeIdsOther(con.getLesseeIdsOther());
		pcc.setSettlementCycleList(con.getSettlementCycleList());
		pcc.setImagePath(ResourceLoader.getPath("templates/pdfTemplates/images"));
		pcc.setAdditionalTerms(con.getAdditionalTerms());
		if (con.getAgtEmpInfo().getAgtCompInfo().getEchapterEnabled() != null
				&& Integer.valueOf(con.getAgtEmpInfo().getAgtCompInfo().getEchapterEnabled()) == 1) {
			pcc.setImageUrl(serverPath + con.getAgtEmpInfo().getAgtCompInfo().getEchapterPath());
		}
		return pcc;
	}

	/**
	 * 获取承租方承担费用
	 * 
	 * @param lessorBear 出租房承担费用
	 * @return 承租方承担费用
	 */
	private String leaseBear(String[] lessorBear) {
		// 获取承租方承担费用
		String leaseeBear = "";
		List<SysCode> bears = sysCodeService.getCodeByType("lessor_bear");
		if (bears != null) {
			for (SysCode code : bears) {
				boolean flg = true;
				if (lessorBear != null) {
					for (String bear : lessorBear) {
						if (code.getCode().equals(bear)) {
							flg = false;
							break;
						}
					}
				}
				if (flg) {
					leaseeBear = leaseeBear + Const.SEPARATOR + code.getCode();
				}
			}
		}
		if (leaseeBear.startsWith(Const.SEPARATOR)) {
			leaseeBear = leaseeBear.substring(1);
		}

		return leaseeBear;
	}

	/**
	 * 根据code值获取标签名称
	 * 
	 * @param codeList
	 * @param feature code字符串
	 * @return 标签名字
	 */
	private String getName(String feature, List<SysCode> codeList) {
		// 标签名字
		String featureName = "";
		if (StringUtils.isNotEmpty(feature)) {
			// 获取code字符数组
			String[] featureArry = feature.split(Const.SEPARATOR);
			for (String f : featureArry) {
				for (SysCode code : codeList) {
					if (StringUtils.isNotEmpty(f) && f.equals(code.getCode())) {
						featureName = featureName + " " + code.getName();
					}
				}
			}
		}
		return featureName;
	}
}