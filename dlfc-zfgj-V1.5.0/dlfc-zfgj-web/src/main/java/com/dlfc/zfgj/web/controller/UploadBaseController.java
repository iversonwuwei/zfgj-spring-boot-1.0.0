/**
* @name: UploadBaseController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 上传文件base
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;

/**
 * @name: UploadBaseController
 * @description: 上传文件base
 * @version 1.0
 * @author yuanjw
 */

public class UploadBaseController {
	/** 日志对象 */
	private final static Logger logger = LoggerFactory.getLogger(UploadBaseController.class);
	// 允许文件格式
	private String allowSuffix = PropertyUtils.getSysVal("upload.file.ext.allowed");
	// 允许文件大小
	private long allowSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
	// 文件名称
	private String fileName;
	// 文件名称数组
	private String[] fileNames;
	// 错误信息
	private String errorInfo;

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * 功能：重新命名文件
	 * @return 规定格式的日期字符串
	 */
	private String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}

	/**
	 * 多文件上传
	 * @param files 文件数组
	 * @param destDir 真实目录
	 * @param request 接收客户端向服务器发出请求
	 */
	public void uploads(MultipartFile[] files, String destDir, HttpServletRequest request) {
		// 站点跟路径
		String path = request.getContextPath();
		//网站全路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
			try {
				fileNames = new String[files.length];
				int index = 0;
				for (MultipartFile file : files) {
					//获取源文件后缀名
					String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					int length = getAllowSuffix().indexOf(suffix);
					if (length == -1) {
						errorInfo = "请上传允许格式的文件";
						return;
					}
					if (file.getSize() > getAllowSize()) {
						errorInfo = "您上传的文件大小已经超出范围";
						return;
					}
					String realPath = request.getSession().getServletContext().getRealPath("/");
					File destFile = new File(realPath + destDir);
					if (!destFile.exists()) {
						destFile.mkdirs();
					}
					String fileNameNew = getFileNameNew() + "." + suffix;//
					File f = new File(destFile.getAbsoluteFile() + "\\" + fileNameNew);
					file.transferTo(f);
					f.createNewFile();
					fileNames[index++] = basePath + destDir + fileNameNew;
				}
			} catch (IllegalStateException e) {
				logger.error("UploadBaseController uploads", e);
			} catch (IOException e) {
				logger.error("UploadBaseController uploads", e);
			}
	}

	/**
	 * 功能：文件上传
	 * @param files 文件数组
	 * @param destDir 真实目录
	 * @param request 接收客户端向服务器发出请求
	 */
	public void upload(MultipartFile file, String destDir, HttpServletRequest request) {
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				errorInfo = "请上传允许格式的文件";
				return;
			}
			if (file.getSize() > getAllowSize()) {
				errorInfo = "您上传的文件大小已经超出范围";
				return;
			}

			String realPath = request.getSession().getServletContext().getRealPath("/");
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			try {
				file.transferTo(f);
				f.createNewFile();
			} catch (IllegalStateException e) {
				logger.error("UploadBaseController upload", e);
			} catch (IOException e) {
				logger.error("UploadBaseController upload", e);
			}
			fileName = fileNameNew;

	}
}