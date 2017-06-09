/**
* @name: CmsGuestBookService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 意见反馈
*
* @version: 1.0
* @date : 2016年8月11日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月11日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.EmailSendTool;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.CmsGuestBook;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.mapper.CmsGuestBookMapper;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;

/**
 * @name: CmsGuestBookService
 * @description: 意见反馈
 * @version 1.0
 * @author yuanjw
 *
 */

@Service("cmsGuestBookService")
public class CmsGuestBookService {
	/** 意见反馈mapper */
	private static CmsGuestBookMapper cmsGuestBookMapper = SpringContextHolder.getBean("cmsGuestBookMapper");
	/** 图片信息service */
	@Autowired
	private SysInfoAttService sysInfoAttService;
	/** 短信service */
	@Autowired
	private SMSService sMSService;

	/**
	 * 保存意见反馈
	 * 
	 * @param cmsGuestBook
	 *            意见反馈对象
	 * @param image
	 *            图片地址数组
	 * @throws ApplicationException
	 *             上传文件失败
	 */
	public void save(CmsGuestBook cmsGuestBook, String[] image) throws ApplicationException {
		// 新建
		if (cmsGuestBook.getIsNewRecord()) {
			cmsGuestBook.preInsert();
			cmsGuestBook.setCreateDate(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
			// 保存意见反馈基本信息
			cmsGuestBookMapper.insertSelective(cmsGuestBook);
			// 保存图片
			if (image != null) {
				// 循环保存图片
				for (int i = 0; i < image.length; i++) {
					SysInfoAtt infoAtt = new SysInfoAtt();
					String mulImage_path_temp = image[i];
					String path = "";
					// 检验图片路径
					if (!mulImage_path_temp.equals("") && !mulImage_path_temp.contains("undefined")) {
						// 临时文件存放路径
						String srcFileName = PropertyUtils.getSysVal("upload.file.temporary.directory") + "/"
								+ mulImage_path_temp;
						File rootDirFile = new File(srcFileName);
						if (!rootDirFile.exists()) {
							rootDirFile.mkdirs();
						}
						// 文件存放目标路径
						String descFileName = PropertyUtils.getSysVal("upload.file.real.directory") + "/opr/"
								+ mulImage_path_temp;
						rootDirFile = new File(descFileName);
						if (!rootDirFile.exists()) {
							rootDirFile.mkdirs();
						}
						// 复制文件从临时目录到目标目录
						boolean flag = FileUtils.copyFileCover(srcFileName, descFileName, true);
						if (flag == false) {
							throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
						}
						path = "opr/" + mulImage_path_temp.replaceAll("\\" + File.separator, "/");
					}else{
						//上传图片为空字符串
						throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
					}
					infoAtt.setFilePath(path);
					infoAtt.setLid(cmsGuestBook.getId());
					infoAtt.setFileType(InfoAttFileTypeEnum.FEEDBACK_PIC_ENUM.getValue());

					// 保存sysInfoAtt
					if (image[i] != null && !"".equals(image[i])) {
						sysInfoAttService.insertPdf(infoAtt);
					}
				}
			}
		} else {// 修改
			cmsGuestBook.preUpdate();
			cmsGuestBook.setCreateDate(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
			cmsGuestBookMapper.updateByPrimaryKeySelective(cmsGuestBook);
		}
	}
	
	
	/**
	 * 发送信息
	 * 
	 * @param cmsGuestBook
	 *            意见反馈对象
	 * @throws ApplicationException 邮件发送失败
	 */
	public void sendMessage(CmsGuestBook cmsGuestBook) throws ApplicationException {
		// 电话空则发邮件
		if (StringUtils.isBlank(cmsGuestBook.getPhone())) {
			String email = cmsGuestBook.getEmail();
			EmailSendTool sendEmail = new EmailSendTool(Const.MAIL_SEND_SMTP, Const.MAIL_SEND_USER,
					Const.MAIL_SEND_PASS, email, "邮箱验证",
					"尊敬的用户您好：\n您的反馈意见已成功提交，我们会尽快安排相关工作人员进行处理，并及时邮件通知你问题的进展情况，感谢您对大连市房屋租赁公共服务平台的支持与关注！\n"
							+ "如有疑问请致电大连市房屋租赁公共服务平台客服电话：0411-82951309，0411-82580693\n祝：工作顺利！",
					Const.MAIL_SEND_NAME, "", "");
			try {
				sendEmail.send();
			} catch (UnsupportedEncodingException e) {
				Logger.getLogger(CmsGuestBookService.class).error("CmsGuestBookService sendMessage 不支持的编码格式",e);
				throw new ApplicationException("邮件发送失败");
			} catch (MessagingException e) {
				Logger.getLogger(CmsGuestBookService.class).error("CmsGuestBookService sendMessage 邮件通讯异常",e);
				throw new ApplicationException("邮件发送失败");
			}
		} else {// 邮件空则发信息
			sMSService.sendSMSByTemplate(cmsGuestBook.getPhone(), "S0010", null);
		}
	}
}
