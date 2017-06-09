/**
 * @name: LettersController.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 站内信
 *
 * @version: 1.0
 * @date : 2015年11月9日 
 * @author: QiuFei
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年12月29日       Daiyuming        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.web.controller;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysMessage;
import com.dlfc.zfgj.service.MessageService;
import com.housecenter.dlfc.zfgj.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: LettersController
 * @description: 站内信
 * 
 * @version 1.0
 * @author Daiyuming
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/msg")
public class LettersController extends BaseController {
	/** 信息service */
	@Autowired
	private MessageService msgService;

	/**
	 * 获取站内信对象
	 * 
	 * @param id
	 * @return 站内信对象
	 */
	@ModelAttribute
	public SysMessage get(@RequestParam(required = false) String id) {
		return new SysMessage();
	}

	/**
	 * 站内信列表
	 * 
	 * @param msg 站内信实体
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 系统消息对象
	 */
	@RequestMapping(value = "/list")
	public String list(SysMessage msg, HttpServletRequest request, HttpServletResponse response, Model model) {
		//当前经纪人ID
		String id = UserUtils.getUser().getEmpId();
		// 经纪人ID非空
		if (id != null) {
			msg.setReceiverUid(id);
		} else {//添加当前用户ID
			msg.setReceiverUid(UserUtils.getUser().getId());
		}
		BasePage<SysMessage> page = msgService.find(new Page<SysMessage>(request, response), msg);
		if(msg != null ){
			setSessionAttribute("mstatus",msg.getStatus());
		}
		model.addAttribute("page", page);
		model.addAttribute("msg", msg);

		return "dlfc/personal/msgNewList";
	}

	/**
	 * 站内信详情
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail() {
		// 站内信ID
		String id = (String) getRequestParameter("id");
		// 翻页状态
		String flg = (String) getRequestParameter("flg");
		
		String page = (String) getRequestParameter("page");
		// 获取站内信
		SysMessage msg = msgService.detail(id);
		msg.setCurrentPage(page);

		// 更新已读
		msgService.read(id);
		// 站内信总数
		int count = msgService.Count();
		// 站内信未读消息总数
		int statusCount = msgService.StatusCount();
		msg.setCount(count);
		msg.setStatusCount(statusCount);

		Object b  =getSessionAttribute("mstatus");
		if(b != null){
			int i =new Short((short)b).intValue();
			setRequestAttribute("mstatus", i);
		}
		setRequestAttribute("msgDetail", msg);
		setRequestAttribute("flg", flg);
		

		return "dlfc/personal/msgNewDetail";
	}

	/**
	 * 删除一条站内信
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String delete() {
		// 站内信ID
		String id = (String) getRequestParameter("id");
		msgService.delete(id);
		return "redirect:" + adminPath + "/msg/list";
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alldels", method = RequestMethod.POST)
	public String allDelete(String checkboxid) {
		// 站内信地址数组
		String[] id = checkboxid.split(",");
		for (int i = 0; i < id.length; i++) {
			msgService.delete(id[i]);
		}
		return "redirect:" + adminPath + "/msg/list";
	}

	/**
	 * 上一条站内信
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = "/up", method = RequestMethod.GET)
	public String upMsg() {
		// 站内信ID
		String id = (String) getRequestParameter("id");
		// 站内信自增长index
		String pindex = (String) getRequestParameter("pindex");
		SysMessage sysMessage = new SysMessage();
		sysMessage.setPindex(Integer.parseInt(pindex));
		sysMessage.setReceiverUid(UserUtils.getUser().getEmpId());

		//获取站内信
		SysMessage msg = msgService.selectUpMsg(sysMessage);
		if (msg == null) {
			return "redirect:" + adminPath + "/msg/detail?id=" + id + "&flg=end";
		}
		msgService.read(msg.getId());
		int count = msgService.Count();
		int statusCount = msgService.StatusCount();
		msg.setCount(count);
		msg.setStatusCount(statusCount);

		setRequestAttribute("msgDetail", msg);

		return "dlfc/personal/msgNewDetail";
	}

	/**
	 * 下一条站内信-预留
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = "/down", method = RequestMethod.GET)
	public String downMsg() {
		// 站内信自增长index
		String pindex = (String) getRequestParameter("pindex");
		// 站内信ID
		String id = (String) getRequestParameter("id");
		SysMessage sysMessage = new SysMessage();
		sysMessage.setPindex(Integer.parseInt(pindex));
		sysMessage.setReceiverUid(UserUtils.getUser().getEmpId());
		//获取站内信
		SysMessage msg = msgService.selectDownMsg(sysMessage);
		if (msg == null) {
			return "redirect:" + adminPath + "/msg/detail?id=" + id + "&flg=dend";
		}
		msgService.read(msg.getId());
		int count = msgService.Count();
		int statusCount = msgService.StatusCount();
		msg.setCount(count);
		msg.setStatusCount(statusCount);

		setRequestAttribute("msgDetail", msg);

		return "dlfc/personal/msgNewDetail";
	}
}
