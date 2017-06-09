/**
* @name: MessageService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 消息，站内信等
*
* @version: 1.0
* @date : 2015年11月11日 
* @author: daiym 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月11日       daiym        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysMessage;
import com.dlfc.zfgj.entity.SysMessageExample;
import com.dlfc.zfgj.entity.SysMessageExample.Criteria;
import com.dlfc.zfgj.mapper.SysMessageMapper;
import com.housecenter.dlfc.zfgj.common.utils.Const;

import net.sf.json.JSONArray;

/**
 * @name: MessageService 
 * @description: 消息，站内信等
 * @version 1.0
 * @author daiym
 *
 */
@Service("MessageService")
public class MessageService {
	/**系统参数mapper*/
	@Autowired
	private SysMessageMapper sysMessageMapper;

	/**
	 * 角色类型判断
	 * @return true:管理角色，false:经纪人角色
	 */
	private boolean roleType() {
		// 获取角色列表
		List<Role> roleList = UserUtils.getUser().getRoleList();
		for (Role role : roleList) {
			if ("assignment".equals(role.getRoleType()) || "comp-role".equals(role.getRoleType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取得未读消息数量
	 * @return 未读消息数量
	 */
	public int StatusCount() {
		SysMessageExample example = new SysMessageExample();
		Criteria cri = example.createCriteria();
		//获取经纪人ID
		String eid = UserUtils.getUser().getEmpId();
		//经纪人ID不为空，接收人ID为经纪人ID
		if (eid != null) {
			cri.andReceiverUidEqualTo(eid);
		} else if (roleType()) {//是管理员，接收人ID为当前登录用户的ID
			cri.andReceiverUidEqualTo(UserUtils.getUser().getId());
		} else {
			return 0;
		}
		cri.andStatusEqualTo((short) 0);
		cri.andDeleteFlgEqualTo((short) 0);
		int res = sysMessageMapper.countByExample(example);
		return res;
	}

	/**
	 * 查询生效消息的总数
	 * @return 生效消息总数
	 */
	public int Count() {
		SysMessageExample example = new SysMessageExample();
		Criteria cri = example.createCriteria();
		//获取经纪人ID
		String eid = UserUtils.getUser().getEmpId();
		//经纪人ID不为空，接收人ID为经纪人ID
		if (eid != null) {
			cri.andReceiverUidEqualTo(eid);
		} else if (roleType()) {//是管理员，接收人ID为当前登录用户的ID
			cri.andReceiverUidEqualTo(UserUtils.getUser().getId());
		} else {
			return 0;
		}
		cri.andDeleteFlgEqualTo((short) 0);
		int res = sysMessageMapper.countByExample(example);
		return res;
	}

	/**
	 * 分页查询系统消息列表
	 * @param page页面对象
	 * @param msg数据对象
	 * @return 消息页面对象
	 */
	public BasePage<SysMessage> find(BasePage<SysMessage> page, SysMessage msg) {
		SysMessageExample example = new SysMessageExample();
		Criteria cri = example.createCriteria();
		//获取经纪人ID
		String eid = UserUtils.getUser().getEmpId();
		//经纪人ID不为空，接收人ID为经纪人ID
		if (eid != null) {
			cri.andReceiverUidEqualTo(eid);
		} else if (roleType()) {//是管理员，接收人ID为当前登录用户的ID
			cri.andReceiverUidEqualTo(UserUtils.getUser().getId());
		} else {
			return page;
		}
		if (msg.getStatus() != null && msg.getStatus() != 9) {
			cri.andStatusEqualTo(msg.getStatus());
		}
		cri.andDeleteFlgEqualTo((short) 0);
		example.setOrderByClause(" SEND_TIME DESC ");
		example.setPage(page);
		List<SysMessage> msgList = sysMessageMapper.selectByExampleList(example);
		page.setList(msgList);
		return page;
	}

	/**
	 * 取得最后一条未读信息的标题和ID
	 * @return 字符串
	 */
	public String firstTitle() {
		SysMessageExample example = new SysMessageExample();
		Criteria cri = example.createCriteria();
		//获取经纪人ID
		String eid = UserUtils.getUser().getEmpId();
		//经纪人ID不为空，接收人ID为经纪人ID
		if (eid != null) {
			cri.andReceiverUidEqualTo(eid);
		} else if (roleType()) {//是管理员，接收人ID为当前登录用户的ID
			cri.andReceiverUidEqualTo(UserUtils.getUser().getId());
		} else {
			return "";
		}
		cri.andStatusEqualTo((short) 0);
		cri.andDeleteFlgEqualTo((short) 0);
		example.setOrderByClause(" SEND_TIME DESC ");

		List<SysMessage> resList = sysMessageMapper.selectByExample(example);
		// 返回字符串
		String res = "";

		if (resList != null && resList.size() > 0) {
			SysMessage msg = resList.get(0);
			Map<String, String> resMap = new HashMap<String, String>();
			resMap.put("title", msg.getTitle());
			resMap.put("id", msg.getId());
			res = JSONArray.fromObject(resMap).toString();
		}

		return res;
	}

	/**
	 * 根据用户ID取得消息
	 * @param userId 用户ID
	 * @return 消息列表
	 */
	public List<SysMessage> list(String userId) {
		SysMessageExample example = new SysMessageExample();
		Criteria cri = example.createCriteria();
		cri.andReceiverUidEqualTo(userId);
		example.setOrderByClause(" SEND_TIME DESC ");
		return sysMessageMapper.selectByExample(example);
	}

	/**
	 * 取得详情
	 * @return 消息对象
	 */
	public SysMessage detail(String id) {
		return sysMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据主键更新已读状态
	 * @param id 消息ID
	 */
	public void read(String id) {
		SysMessage record = new SysMessage();
		record.setId(id);
		record.setStatus((short) 1);
		sysMessageMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 批量删除消息
	 */
	public void delete(String id) {
		SysMessage record = new SysMessage();
		record.setId(id);
		record.setDeleteFlg((short) 1);
		sysMessageMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 发送个人站内信
	 * @param sender 发送人ID
	 * @param senderName 发送人姓名
	 * @param receiver 接收人ID
	 * @param title 站内信标题
	 * @param content 站内信内容
	 * @param type 消息类型 1：系统消息2：业务动态
	 * @return 操作状态
	 */
	public int sendMessage(String sender, String senderName, String receiver, String title, String content,
			short type) {

		SysMessage message = new SysMessage();

		message.setId(UuidUtils.get32UUID());
		message.setSenderUid(sender);
		message.setSenderName(senderName);
		message.setSendTime(new Date());
		message.setReceiverUid(receiver);
		message.setTitle(title);
		message.setContent(content);
		message.setStatus(Const.MESSAGE_STATUS_UNREAD);
		message.setType(type);
		message.setMassFlg(Const.MESSAGE_MASS_NO);
		message.setDeleteFlg(Const.DEL_NORMAL);
		message.preInsert();

		return sysMessageMapper.insertSelective(message);
	}

	/**
	 * 系统消息发送
	 * @param receiver 接收人ID
	 * @param content 内容
	 * @return 操作状态
	 */
	public int sendMessage(String receiver, String content) {

		SysMessage message = new SysMessage();

		message.setId(UuidUtils.get32UUID());
		message.setSenderUid(Const.SYSTEM_UUID);
		message.setSenderName(PropertyUtils.getSysVal("system.name"));
		message.setSendTime(new Date());
		message.setReceiverUid(receiver);
		message.setTitle(Const.STRING_EMPTY);
		message.setContent(content);
		message.setStatus(Const.MESSAGE_STATUS_UNREAD);
		message.setType(Const.MESSAGE_TYPE_SYSTEM);
		message.setMassFlg(Const.MESSAGE_MASS_NO);
		message.setDeleteFlg(Const.DEL_NORMAL);
		message.preInsert();

		return sysMessageMapper.insertSelective(message);
	}

	/**
	 * 发送群发站内信
	 * @param sender 发送人ID
	 * @param senderName 发送人姓名
	 * @param title 发送标题
	 * @param content 发送正文
	 * @param type 消息类型 1：系统消息2：业务动态
	 * @return 操作状态
	 */
	public int sendMessageMass(String sender, String senderName, String title, String content, short type) {

		SysMessage message = new SysMessage();

		message.setId(UuidUtils.get32UUID());
		message.setSenderUid(sender);
		message.setSenderName(senderName);
		message.setSendTime(new Date());
		message.setTitle(title);
		message.setContent(content);
		message.setType(type);
		message.setMassFlg(Const.MESSAGE_MASS_YES);
		message.setDeleteFlg(Const.DEL_NORMAL);
		message.preInsert();

		return sysMessageMapper.insertSelective(message);
	}

	/**
	 * 根据主键更新已读状态
	 * @param sysMessage 消息对象
	 */
	public void updateStatus(SysMessage sysMessage) {
		sysMessageMapper.updateByPrimaryKeySelective(sysMessage);
	}

	/**
	 * 查找上一条消息
	 * @param sysMessage 消息对象
	 */
	public SysMessage selectUpMsg(SysMessage sysMessage) {
		return sysMessageMapper.selectUpMsg(sysMessage);
	}

	/**
	 * 查找下一条消息
	 * @param sysMessage 消息对象
	 */
	public SysMessage selectDownMsg(SysMessage sysMessage) {
		return sysMessageMapper.selectDownMsg(sysMessage);
	}
}
