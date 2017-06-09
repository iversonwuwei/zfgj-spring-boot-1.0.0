package com.housecenter.dlfc.zfgj.common.utils;

import org.springframework.web.context.WebApplicationContext;

import com.dlfc.admin.common.utils.PropertyUtils;


/**
 * 静态变量
 * 
 * @name: Const
 * @description:
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionCaptcha";
	public static final String SESSION_LOGIN_USER = "sessionLoginUser";
	public static final String SESSION_LOGIN_USER_ID = "sessionLoginUserId"; // 登陆用户id
	public static final String SESSION_LOGIN_USER_PID = "sessionLoginUserPid"; // 登陆用户人员id
	public static final String SESSION_LOGIN_USER_NAME = "sessionLoginUserName"; // 登陆用户名
	public static final String SESSION_LOGIN_USER_IDENTITY = "sessionLoginUserIdentity"; // 登陆用户身份
	public static final String SESSION_FORGET_USER = "forgetUser";// 忘记密码用户信息
	public static final String SESSION_BACK_URL = "index";// 访问你页面的来源地址
	// public static final String SESSION_USERNAME = "USERNAME"; //用户名
	public static final String PASSWDSHA = "dlfcsha0693";// 密码加密
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login.do"; // 登录地址
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/"; // 图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径

	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(captcha)|(static)|(main)|(register*)|(forget*)|(personal/getidcode)).*"; // 不对匹配该值的访问路径拦截（正则）

	// 路径填写之后，登陆成功，系统会跳回此路径ge
	public static final String NEED_INTERCEPTOR_BACK_PATH = ".*/((mailauth)|(fpwd)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String DEFAULT_TMP_DIR_ROOT = "\\tmp";

	public static final String CURRENT_PAGE = "1";
	public static final Integer KEY_PAGE_INFO = 20;
	// 默认一页显示多少记录数
	public static final String KEY_ROW_COUNT_PER_PAGE = "20";
	// 上一页
	public static final String KEY_BUTTON_PREVIOUS_PAGE = "上一页";
	// 下一页
	public static final String KEY_BUTTON_NEXT_PAGE = "下一页";
	// 翻页
	public static final String KEY_PAGE_LABEL = "";
	// 移动
	public static final String KEY_PAGE_MOVE_LABEL = "移动";

	public static WebApplicationContext WEB_APP_CONTEXT = null;

	public static final Integer PAGE_SIZE = 20;

	public static final String MAIL_SEND_USER = "gelin@china-tcloud.com";
	public static final String MAIL_SEND_PASS = "a456789";
	public static final String MAIL_SEND_SMTP = "smtp.exmail.qq.com";
	public static final String MAIL_SEND_NAME = "大连租房管家";
	// public static final String MAIL_SEND_ =
	// "http://192.168.0.109:8080/contract/personal/mailauth";

	// 数据字典：个人证件类型
	public static final String CODE_PER_ID_TYPE = "per_id_type";
	// 电话号码正则
	public static final String REGULAR_MOBILE = "^1[34578]\\d{9}$";
	// 证件正则
	public static final String REGULAR_ID_NO = "^[0-9a-zA-Z]+$";
	public static final String IDENTITYAUTH = "/my/ia";
	public static final String DO_AUTH_PATH = ".*/((my/ia)|(my/iac)|(my/is)|(my/ic)|(my/isc)|(my/ir)|(msg/.*)|(logout)).*";

	/**
	 * 经纪人在职状态
	 */
	public static final int AGT_STATUS_ONJOB = 0;

	/**
	 * 经纪人离职状态
	 */
	public static final int AGT_STATUS_QUIT = 1;

	/**
	 * 删除标志 删除
	 */
	public static final short DEL_DELETE = 1;

	/**
	 * 删除标志 未删除
	 */
	public static final short DEL_NORMAL = 0;

	/**
	 * 消息状态已读
	 */
	public static final short MESSAGE_STATUS_READ = 1;

	/**
	 * 消息状态未读
	 */
	public static final short MESSAGE_STATUS_UNREAD = 0;

	/**
	 * 消息为群发
	 */
	public static final short MESSAGE_MASS_YES = 1;

	/**
	 * 消息为非群发
	 */
	public static final short MESSAGE_MASS_NO = 0;

	/**
	 * 数据版本号
	 */
	public static final int VERSION_NEW = 0;

	/**
	 * 空字符
	 */
	public static final String STRING_EMPTY = "";

	/**
	 * 消息类型 1：系统消息
	 */
	public static final short MESSAGE_TYPE_SYSTEM = 1;

	/**
	 * 消息类型 2：业务动态
	 */
	public static final short MESSAGE_TYPE_BUSINESS = 2;

	/**
	 * 系统UUID
	 */
	public static final String SYSTEM_UUID = PropertyUtils.getSysVal("system.uuid");
	/**
	 * 房屋使用率
	 */
	public static final double AREA_RATIO = 0.7;

	/**
	 * 人员信息表
	 */
	public static final String LOCK_TABLE_NAME_SYS_PERSON = "0";

	/**
	 * 合同信息表
	 */
	public static final String LOCK_TABLE_NAME_CON_CONTRACT = "1";

	/**
	 * 房源信息表
	 */
	public static final String LOCK_TABLE_NAME_HOU_HOUSE_INFO = "2";

	/**
	 * 房源出租信息表
	 */
	public static final String LOCK_TABLE_NAME_HOU_LEASE_INFO = "3";

	/**
	 * 合同解约协议信息表
	 */
	public static final String LOCK_TABLE_NAME_CON_DISS_AGR = "4";

	/**
	 * 押金分配协议信息表
	 */
	public static final String LOCK_TABLE_NAME_CON_DEPOSIT_DIST_AGR = "5";

	/**
	 * 证件认证 待审核 0
	 */
	public static final String LOCK_SYS_PERSON_NEED_APPROVE = "0";

	/**
	 * 合同 待审核 0
	 */
	public static final String LOCK_CON_CONTRACT_NEED_APPROVE = "9";

	/**
	 * 房源 待审核 0
	 */
	public static final String LOCK_HOU_HOUSE_INFO_NEED_APPROVE = "1";

	/**
	 * 出租信息 待审核 0
	 */
	public static final String LOCK_HOU_LEASE_INFO_NEED_APPROVE = "0";

	/**
	 * 解约协议 待审核 0
	 */
	public static final String LOCK_CON_DISS_AGR_NEED_APPROVE = "5";

	/**
	 * 押金分配协议 待审核 0
	 */
	public static final String LOCK_CON_DEPOSIT_DIST_AGR_NEED_APPROVE = "5";

	/**
	 * 合同 商务编号类型
	 */
	public static final int BUSINESS_TYPE_CONTRACT = 1;

	/**
	 * 房源 商务编号类型
	 */
	public static final int BUSINESS_TYPE_HOUSE = 2;

	/**
	 * 出租信息 商务编号类型
	 */
	public static final int BUSINESS_TYPE_LEASE = 3;
	/**
	 * 合同 商务编号前缀
	 */
	public static final String BUSINESS_NAME_PREFIX_CONTRACT = "C";
	/**
	 * 房源 商务编号前缀
	 */
	public static final String BUSINESS_NAME_PREFIX_HOUSE = "H";
	/**
	 * 出租信息 商务编号前缀
	 */
	public static final String BUSINESS_NAME_PREFIX_LEASE = "L";
	/**
	 * 解约协议 商务编号前缀
	 */
	public static final String BUSINESS_NAME_PREFIX_DISS = "T";
	/**
	 * 押金分配 商务编号前缀
	 */
	public static final String BUSINESS_NAME_PREFIX_DEPOSIT = "D";

	public static final String UNDERLINE = "_";
	
	public static final String SEPARATOR = ",";
	
	/**
	 * 房主承担其它费用的编号
	 */
	public static final int OWNER_BEAR_OTHER_FLAG = 13;
	
	/**
	 * 大连房产网客服电话
	 */
	public static final String DLFC_PHONE = "4000-623-799";
}
