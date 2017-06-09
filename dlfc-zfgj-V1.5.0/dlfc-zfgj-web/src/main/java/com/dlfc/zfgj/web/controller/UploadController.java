/**
* @name: UploadController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 上传文件
*
* @version: 1.0
* @date : 2016年8月12日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月12日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dlfc.admin.common.exception.AppRuntimeException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.ImageUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;

/**
 * @name: UploadController
 * @description: 上传文件
 * @version 1.0
 * @author qiufei
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "${adminPath}/upload")
public class UploadController extends BaseController {
	// 允许文件格式
	private String allowSuffix = PropertyUtils.getSysVal("upload.file.ext.allowed");
	// 允许文件大小
	private long allowSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
	// 文件名字
	private String fileName;
	// 文件地址
	private String filePath;
	// 错误信息
	private String errorInfo;
	// 文件临时根目录
	private String destDir = PropertyUtils.getSysVal("upload.file.temporary.directory");

	/**
	 * 上传图片
	 * @param file 上传的文件
	 * @param idx 0:其它图片 1:上传公司电子章图片
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return AjaxResult:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping("/uploadHeadPic")
	@ResponseBody
	public AjaxResult submit(@RequestParam("file") MultipartFile file, Integer idx, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//文件名称
		String fileRealName = file.getOriginalFilename();
			//文件后缀名
			String suffix = fileRealName.substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();

			// 判断后缀名
			if (idx != null && idx == 1) {
				if (!suffix.equals(".png")) {
					throw new AppRuntimeException("请上传允许格式的文件");
				}
			} else {
				int length = allowSuffix.indexOf(suffix);
				if (length == -1) {
					throw new AppRuntimeException("请上传允许格式的文件");
				}
			}

			// 判断文件大小
			if (file.getSize() > allowSize) {
				throw new AppRuntimeException("您上传的文件大小已经超出范围");
			}

			String date = DateUtils.getSysDateStr("yyyy-MM-dd");
			File destFile = new File(destDir + date);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}

			try {
				if (idx != null && idx == 1) {
					fileName = UuidUtils.get32UUID() + suffix;
					File f = new File(destFile.getAbsoluteFile() + "/" + fileName);
					file.transferTo(f);
					filePath = date + "/" + fileName;
				} else {
					fileName = UuidUtils.get32UUID() + ".jpg";
					StringBuilder builder = new StringBuilder(request.getSession().getServletContext().getRealPath("tmp"));
					File f = new File(builder.toString());
					if (!f.exists()) {
						f.mkdirs();
					}
					builder.append(File.separator).append(UuidUtils.get32UUID()).append(suffix);
					f = new File(builder.toString());
					// 本地临时路径
					file.transferTo(f);
					//压缩图片
					boolean upFile = ImageUtils.imgCompress(f.getPath(),
							destFile.getAbsoluteFile() + File.separator + fileName);
					if (upFile) {
						filePath = date + File.separator + fileName;
					}
				}
			} catch (IllegalStateException e) {
				logger.error("UploadController submit", e);
				errorInfo = "上传失败";
			} catch (IOException e) {
				logger.error("UploadController submit", e);
				errorInfo = "上传失败";
			}

		if (!StringUtils.isBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("filename", fileName);
		map.put("filepath", filePath);
		map.put("filerealname", fileRealName);

		return ajaxSuccess("上传成功", map);
	}

}