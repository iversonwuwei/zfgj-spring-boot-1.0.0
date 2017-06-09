package com.housecenter.dlfc.zfgj.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;


/**
 * 上传文件
 * 创建人：sun.zhi 创建时间：2014年12月23日
 * @version
 */
public class FileUpload {

	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	/**
	 * 检测上传的附件大小，文件名是否标准
	 * @param file
	 * @return
	 */
	public static String checkFile(@RequestParam("file") MultipartFile file){
		String filerealname = file.getOriginalFilename();
		long maxSize = StringUtils.toLong(PropertyUtils.getSysVal("upload.file.max.size"));
		
		if(file.getSize() > maxSize){
			return "上传文件大小不得超过"+maxSize/1024/1024+"M";
		}
		String photoExt=filerealname.substring(filerealname.lastIndexOf(".")).toLowerCase();//获得文件后缀名
		
		String sizeAllowed[] = PropertyUtils.getSysVal("upload.file.ext.allowed").split(",");
		int j = 0;
		
		StringBuffer buffer = new StringBuffer();
		String fileName = "";
		for(int i = 0;i < sizeAllowed.length;i++){
			buffer.append(sizeAllowed[i].replace(".", "/"));
			if(sizeAllowed[i].equals(photoExt)){
				j++;
				break;
			}
			if(i==sizeAllowed.length-1){
				fileName = buffer.toString().substring(1, buffer.toString().length());
			}
		}
		if(j != 1){
			return "请上传后缀名为"+fileName+"的图片";
		}
		
		return "";
	}
}
