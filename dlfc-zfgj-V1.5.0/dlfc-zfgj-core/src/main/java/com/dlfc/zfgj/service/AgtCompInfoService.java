/**
* @name: agtCompInfoService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 查询公司详情,获取公司发布房源总数，获取公司在职经纪人总数，获取公司签订合同总数，更新公司信息等
*
* @version: 1.0
* @date : 2016年2月29日 
* @author: yuanjiwei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年2月29日      yuanjiwei       1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.FileDownload;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.pdf.pdf.bean.PdfCommitBook;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;
import com.housecenter.dlfc.zfgj.common.utils.PdfGeneratorUtils;

/**
 * @name: AgtCompInfoService
 * @description:查询公司详情,获取公司发布房源总数，获取公司在职经纪人总数，获取公司签订合同总数，更新公司信息等
 * 
 * @version 1.0
 * @author yuanjiwei
 *
 */
@Service("agtCompInfoService")
public class AgtCompInfoService extends BaseService {
	/** 经纪公司信息Mapper */
	@Autowired
	private AgtCompInfoMapper agtCompInfoMapper;
	/** 附件信息Mapper */
	@Autowired
	private SysInfoAttMapper sysInfoAttMapper;
	/** 附件信息Service */
	@Autowired
	private SysInfoAttService sysInfoAttService;

	/**
	 * 查询公司详情通过公司id
	 * 
	 * @param id
	 *            公司ID
	 * @return 查询成功失败状态
	 */
	public AgtCompInfo getById(String id) {
		return agtCompInfoMapper.selectById(id);
	}

	/**
	 * 查询公司信息通过公司id
	 * 
	 * @param id 公司ID
	 * @return 查询成功失败状态
	 */
	public AgtCompInfo getCompInfoById(String id) {
		return agtCompInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 获取公司发布房源总数
	 * 
	 * @param id 公司ID
	 * @return 获取成功失败状态
	 */
	public int getHouseTotal(String id) {
		return agtCompInfoMapper.getHouseTotal(id);
	}

	/**
	 * 获取公司在职经纪人总数
	 * 
	 * @param id 公司ID
	 * @return 成功失败状态
	 */
	public int getAgentTotal(String id) {
		return agtCompInfoMapper.getAgentTotal(id);
	}

	/**
	 * 获取公司签订合同总数
	 * 
	 * @param id 公司ID
	 * @return 成功失败状态
	 */
	public int getContractTotal(String uid) {
		return agtCompInfoMapper.getContractTotal(uid);
	}

	/**
	 * 更新公司信息
	 * 
	 * @param id 公司ID
	 * @return 成功失败状态
	 */
	public void update(AgtCompInfo agtCompInfo) {
		agtCompInfoMapper.updateByPrimaryKeySelective(agtCompInfo);
	}

	/**
	 * 保存公司logo
	 * 
	 * @param agtCompInfo 经纪公司信息对象
	 * @param image 公司Logo图片
	 * @throws ApplicationException 保存公司logo失败！
	 */
	public void saveLogo(AgtCompInfo agtCompInfo, String image) throws ApplicationException {

		// 上传图片并获取存储路径
		String path;
		try {
			path = upload(image);
		} catch (ApplicationException e) {
			throw new ApplicationException("保存公司logo失败！");
		}

		SysInfoAtt sysInfo = new SysInfoAtt();

		// 公司logo存在，删除logo
		if (StringUtils.isNotBlank(agtCompInfo.getImg1Id())) {
			sysInfoAttMapper.deleteByPrimaryKey(agtCompInfo.getImg1Id());
		}

		sysInfo.preInsert();
		sysInfo.setFilePath(path);
		// 保存logo
		sysInfoAttMapper.insertSelective(sysInfo);

		// 保存图片ID
		agtCompInfo.setImg1Id(sysInfo.getId());
		agtCompInfoMapper.updateByPrimaryKeySelective(agtCompInfo);
	}

	/**
	 * 上传电子章
	 * 
	 * @param agtCompInfo 公司信息对象
	 * @param image 公司Logo图片
	 * @throws ApplicationException 保存公司logo失败！
	 */
	public void saveChapter(AgtCompInfo agtCompInfo, String image) throws ApplicationException {

		// 上传图片并获取存储路径
		String path = "";
		try {
			path = upload(image);
		} catch (ApplicationException e) {
			throw new ApplicationException("上传电子章失败！");
		}
		agtCompInfo.setEchapterPath(path);
		// 设置为未启用状态
		agtCompInfo.setEchapterEnabled(String.valueOf(YesNoEnum.NO_ENUM.getValue()));
		agtCompInfoMapper.updateByPrimaryKeySelective(agtCompInfo);
		sysInfoAttService.delete(agtCompInfo.getId(), InfoAttFileTypeEnum.DW_COMMITBOOK_PDF_ENUM.getValue(), null);

	}

	/**
	 * 上传文件或图片
	 * 
	 * @param image 公司Logo图片
	 * @return 图片存储路径
	 * @author HAN.JIAQI 2016/07/25
	 * @throws ApplicationException 上传附件失败
	 */
	public String upload(String image) throws ApplicationException {
		String mulImage_path_temp = image;
		String path = "";
		// 临时文件地址
		String srcFileName = PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp;
		// 目标地址
		String descFileName = PropertyUtils.getSysVal("upload.file.real.opr.directory") + mulImage_path_temp;
		File rootDirFile = new File(descFileName);
		// 上级文件夹不存在创建新文件夹
		if (!rootDirFile.getParentFile().exists()) {
			rootDirFile.getParentFile().mkdirs();
		}
		// 复制文件是否成功
		boolean flag = FileUtils.copyFileCover(srcFileName, descFileName, true);
		if (flag == false) {
			throw new ApplicationException("上传附件失败");
		}
		path = (PropertyUtils.getSysVal("upload.file.opr") + mulImage_path_temp).replaceAll("\\" + File.separator, "/");

		return path;
	}

	/**
	 * 下载承诺书
	 * 
	 * @param id:公司id
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @throws ApplicationException 下载承诺证书失败
	 */
	public void downPleBk(String id, HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
		// 服务器存储承诺书文件的路径
		String destDir = PropertyUtils.getSysVal("upload.file.real.opr.directory");
		AgtCompInfo agtCompInfo = getCompInfoById(id);
		String outputFile = "";
		String imageUrl = "https://"+ request.getServerName() + agtCompInfo.getEchapterPath();
		// 填充数据
		PdfCommitBook pcb = new PdfCommitBook();
		pcb.setCompanyName(agtCompInfo.getFullName());
		pcb.setImageUrl(imageUrl);

		List<SysInfoAtt> sysInfoAttCon = sysInfoAttService.findList(id,
				InfoAttFileTypeEnum.DW_COMMITBOOK_PDF_ENUM.getValue());

		if (sysInfoAttCon == null || sysInfoAttCon.size() == 0) {
			// 承诺书模板
			String template = "templates/pdfTemplates/commitBook1.0.0.html";
			// 当天存储文件的路径 (规则：时间)
			outputFile = PdfGeneratorUtils.generatePdf(destDir, outputFile, pcb, id, template);
			if (outputFile == null) {
				throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0107"));
			}
			// 承诺书pdf存储到附件表
			SysInfoAtt newSysInfoAtt = new SysInfoAtt();
			newSysInfoAtt.setFileType(InfoAttFileTypeEnum.DW_COMMITBOOK_PDF_ENUM.getValue());// 附件类型
			newSysInfoAtt.setFileName(id + ".pdf");
			newSysInfoAtt.setFilePath(outputFile);
			newSysInfoAtt.setLid(id);
			newSysInfoAtt.setFileRealName("承诺书");
			sysInfoAttService.insertPdf(newSysInfoAtt);
		} else {
			outputFile = sysInfoAttCon.get(0).getFilePath();
		}

		outputFile = destDir + outputFile;
		try {
			FileDownload.download(outputFile, response);
		} catch (ApplicationException e) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0107"));
		}
	}
}
