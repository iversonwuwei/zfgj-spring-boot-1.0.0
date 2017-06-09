/**
* @name: AllinpayService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 支付操作，实名认证等
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.mapper.SysPayChannelMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housecenter.dlfc.auth.AuthFacet;

/**
 * @name: AllinpayService
 * @description: 支付操作，实名认证等
 * @version 1.0
 * @author Jack.Da
 */

@Service(value = "allinpayService")
public class AllinpayService {
	/** 日志对象 */
	private final static Logger logger = LoggerFactory.getLogger(AllinpayService.class);

	/** 支付渠道Mapper */
	@Autowired
	private SysPayChannelMapper payChannelMapper;

	/**
	 * 组装验证报文
	 * @param bankCode 银行代码
	 * @param accountType 账号类型
	 * @param accountNo 账号
	 * @param accountName 账号名
	 * @param idType 开户证件类型
	 * @param id 证件号
	 * @param tel 手机号/小灵通
	 * @return true:验证成功，false:验证失败
	 */
	public boolean checkBankInfo(String bankCode, String accountType, String accountNo, String accountName,
			String idType, String id, String tel) {
		return AuthFacet.authBC4Key(accountNo, accountName, id, tel);
	}
	
	/**
	 * 访问阿里https接口判断银行类型是否绕过证书标志
	 * @param key 属性文件key
	 * @return true:使用证书，false:绕过证书
	 */
	private String getAllpayConf(String key) {
		return PropertyUtils.getSysVal(key);
	}

	/**
	 * 实名认证
	 * @param id 身份证号
	 * @param name 姓名
	 * @return 验证失败返回null，验证成功返回true
	 */
	public boolean checkId(String id, String name) {
		return AuthFacet.authID(name, id);
	}

	/**
	 * 阿里通过银行卡号获取银行基础代码
	 * @param cardNo 银行卡号
	 * @return 验证失败返回null，验证成功返回基础代码code
	 */
	public Map<String, String> getBankByAlipay(String cardNo) {
		Map<String, String> map = null;
		// 验证开关
		String onOff = payChannelMapper.findSvalue();
		if ("off".equalsIgnoreCase(onOff)) {// 验证开关关闭
			map = new HashMap<String, String>();
			map.put("bank", "BOC");
			map.put("cardType", "DC");
			return map;
		} else {// 验证开关打开
			String conf = getAllpayConf("aliba.vertify.banktype.cer.flag");
			boolean flag = new Boolean(conf);
			String result = "";
			String url = Global.getConfig("alipayUrl");
			String params = "_input_charset=utf-8&cardBinCheck=true&cardNo=" + cardNo;
			if (flag) {
				HttpService httpService = new HttpService();
				result = httpService.sendGet(url, params);
			} else {
				String urlString = url + "?" + params;
				result = SysX509TrustService.exeGetMethod(urlString);
			}
			if (result.contains("\"validated\":true")) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode;
				try {
					jsonNode = mapper.readTree(result);
				} catch (JsonProcessingException e) {
					logger.error("AllinpayService getBankByAlipay", e);
					return null;
				} catch (IOException e) {
					logger.error("AllinpayService getBankByAlipay", e);
					return null;
				}
				
				String code = jsonNode.get("bank").textValue();
				String type = jsonNode.get("cardType").textValue();
				map = new HashMap<String, String>();
				map.put("bank", code);
				map.put("cardType", type);
			}
			return map;
		}
	}

}
