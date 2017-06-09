/**
 * @name: MessagePushService.java 
 *
 * @Copyright: (c) 2016 DLFC. All rights reserved. 
 *
 * @description: 向APP推送消息
 *
 * @version: 1.0
 * @date : 2016年7月6日 
 * @author: WangLituo 
 *
 * @Modification  History:<br>
 *  Date		  Author		 Version		Discription
 *  2016年7月6日	   WangLituo		1.0			 <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.maven.surefire.util.internal.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.mapper.SysParamMapper;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

/**
 * @name: MessagePushService
 * @description: 向APP推送消息
 * 
 * @version 1.0
 * @author WangLituo
 *
 */
@Service
public class MessagePushService {

	/** 日志对象 */
	protected Logger logger = LoggerFactory.getLogger(MessagePushService.class);

	/** 初始化参数Key */
	private static final String ANDROID_ACCESS_ID = "Android_Access_Id";
	/** 初始化参数Key */
	private static final String ANDROID_SECRET_KEY = "Android_Secret_Key";
	/** 初始化参数Key */
	private static final String IOS_ACCESS_ID = "IOS_Access_Id";
	/** 初始化参数Key */
	private static final String IOS_SECRET_KEY = "IOS_Secret_Key";

	/** SysParam表查询对象 */
	@Autowired
	private SysParamMapper sysParamMapper;

	/**
	 * 推送Android、IOS消息（单）
	 * 
	 * @param userId
	 *            推送用户Uid
	 * @param title
	 *            推送标题
	 * @param content
	 *            推送内容
	 * @return 0:正常 ，1:用户userId不能为空
	 */
	public int pushSingleAccount(String userId, String title, String content) {
		if (StringUtils.isBlank(userId)) {
			logger.info("userId不能为空");
			return 1;
		}
		Map<String, Object> map = sysParamMapper.selectByMsgPushParam();
		if (map != null) {
			// Android Access Id
			long androidAccessId = Long.valueOf((String) map.get(ANDROID_ACCESS_ID));
			// Android Secret Key
			String androidSecretKey = (String) map.get(ANDROID_SECRET_KEY);
			// IOS Access Id
			long iosAccessId = Long.valueOf((String) map.get(IOS_ACCESS_ID));
			// IOS Secret Key
			String iosSecretKey = (String) map.get(IOS_SECRET_KEY);
			// Android 推送
			JSONObject andRet = XingeApp.pushAccountAndroid(androidAccessId, androidSecretKey, title, content, userId);
			// IOS 推送
			JSONObject iosRet = XingeApp.pushAccountIos(iosAccessId, iosSecretKey, content, userId,
					XingeApp.IOSENV_PROD);

			if (0 != andRet.getInt("ret_code")) {
				logger.info("Android 推送失败:" + andRet);
			}
			if (0 != iosRet.getInt("ret_code")) {
				logger.info("IOS 推送失败:" + iosRet);
			}
		} else {
			logger.info("Android 推送失败:信鸽参数未获取到！");
		}
		return 0;
	}

	/**
	 * 推送Android、IOS消息（单）
	 * 
	 * @param userId
	 *            推送用户Uid
	 * @param title
	 *            推送标题
	 * @param content
	 *            推送内容
	 * @param msgType
	 *            消息类型
	 * @return 0:正常 1:用户userId不能为空
	 */
	public int pushSingleAccount(String userId, String title, String content, short msgType) {
		if (StringUtils.isBlank(userId)) {
			logger.info("userId不能为空");
			return 1;
		}
		Map<String, Object> map = sysParamMapper.selectByMsgPushParam();
		if (map != null) {
			// Android Access Id
			long androidAccessId = Long.valueOf((String) map.get(ANDROID_ACCESS_ID));
			// Android Secret Key
			String androidSecretKey = (String) map.get(ANDROID_SECRET_KEY);
			// IOS Access Id
			long iosAccessId = Long.valueOf((String) map.get(IOS_ACCESS_ID));
			// IOS Secret Key
			String iosSecretKey = (String) map.get(IOS_SECRET_KEY);

			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", msgType);
			// android
			XingeApp andXinge = new XingeApp(androidAccessId, androidSecretKey);
			JSONObject andRet = andXinge.pushSingleAccount(0, userId, this.instanceMessage(title, content, custom));
			// ios
			XingeApp iosXinge = new XingeApp(iosAccessId, iosSecretKey);
			JSONObject iosRet = iosXinge.pushSingleAccount(0, userId, this.instanceMessageIOS(content, custom),
					XingeApp.IOSENV_PROD);

			if (0 != andRet.getInt("ret_code")) {
				logger.info("Android 推送失败:" + andRet);
			}
			if (0 != iosRet.getInt("ret_code")) {
				logger.info("IOS 推送失败:" + iosRet);
			}
		} else {
			logger.info("Android 推送失败:信鸽参数未获取到！");
		}
		return 0;
	}

	/**
	 * 推送消息给所有用户
	 * 
	 * @param title
	 *            推送标题
	 * @param content
	 *            推送内容
	 * @return 0:正常 
	 */
	public int pushAll(String title, String content) {
		Map<String, Object> map = sysParamMapper.selectByMsgPushParam();
		if (map != null) {
			// Android Access Id
			long androidAccessId = Long.valueOf((String) map.get(ANDROID_ACCESS_ID));
			// Android Secret Key
			String androidSecretKey = (String) map.get(ANDROID_SECRET_KEY);
			// IOS Access Id
			long iosAccessId = Long.valueOf((String) map.get(IOS_ACCESS_ID));
			// IOS Secret Key
			String iosSecretKey = (String) map.get(IOS_SECRET_KEY);
			// Android 推送
			JSONObject andRet = XingeApp.pushAllAndroid(androidAccessId, androidSecretKey, title, content);
			// IOS 推送
			JSONObject iosRet = XingeApp.pushAllIos(iosAccessId, iosSecretKey, content, XingeApp.IOSENV_PROD);

			if (0 != andRet.getInt("ret_code")) {
				logger.info("Android 推送失败:" + andRet);
			}
			if (0 != iosRet.getInt("ret_code")) {
				logger.info("IOS 推送失败:" + iosRet);
			}
		} else {
			logger.info("Android 推送失败:信鸽参数未获取到！");
		}
		return 0;
	}

	/**
	 * 创建Message
	 * 
	 * @param title
	 *            推送标题
	 * @param content
	 *            推送内容
	 * @param custom
	 *            自定义参数
	 * @return android信息对象
	 */
	private Message instanceMessage(String title, String content, Map<String, Object> custom) {
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		message.setCustom(custom);
		return message;
	}

	/**
	 * 创建Message
	 * 
	 * @param title
	 *            推送标题
	 * @param content
	 *            推送内容
	 * @param custom
	 *            自定义参数
	 * @return ios信息对象
	 */
	private MessageIOS instanceMessageIOS(String content, Map<String, Object> custom) {
		MessageIOS message = new MessageIOS();
		message.setAlert(content);
		message.setSound("beep.wav");
		message.setCustom(custom);
		return message;
	}

}
