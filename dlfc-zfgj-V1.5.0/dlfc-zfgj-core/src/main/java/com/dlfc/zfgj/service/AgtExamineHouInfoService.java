/**
 * @name: AgtExamineHouInfoService.java 
 *
 * @Copyright: (c) 2016 DLFC. All rights reserved. 
 *
 * @description: 查询经纪人验房预约记录，经纪人预约验房列表，验房预约，删除经纪人验房预约，修改备注，查验房预约信息
 *
 * @version: 1.0
 * @date : 2016年2月20日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2016年2月20日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtExamineHouInfo;
import com.dlfc.zfgj.entity.AgtExamineHouInfoExample;
import com.dlfc.zfgj.entity.AgtExamineHouInfoExample.Criteria;
import com.dlfc.zfgj.entity.HouEntrustInfo;
import com.dlfc.zfgj.mapper.AgtExamineHouInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.EntrustStateEnum;
import com.housecenter.dlfc.zfgj.common.enums.MsgTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: AgtExamineHouInfoService
 * @description: 查询经纪人验房预约记录，经纪人预约验房列表，验房预约，删除经纪人验房预约，修改备注，查验房预约信息
 * 
 * @version 1.0
 * @author fancy
 *
 */
@Service("agtExamineHouInfoService")
public class AgtExamineHouInfoService extends BaseService {
	/** 经纪人验房预约Mapper */
	@Autowired
	private AgtExamineHouInfoMapper agtExamineHouInfoMapper;
	/** 消息Service */
	@Autowired
	private MessageService messageService;
	/** 房源委托service */
	@Autowired
	private HouEntrustInfoService houEntrustInfoService;
	/** 短信Service */
	@Autowired
	private SMSService smsService;
	/** 消息推送Service */
	@Autowired
	private MessagePushService messagePushService;
	/** 房源消息 */
	private static final short MSG_TYPE_HOU = (short) MsgTypeEnum.HOU_ENUM.getValue();
	/** 验房预约标题 */
	private static String MSG_HOUSE_RESERVATION_CHECK_TITLE = PropertyUtils
			.getMessage("msg.house.reservation.check.title");

	/**
	 * 查询经纪人验房预约记录
	 * 
	 * @param page
	 *            经纪人验房预约信息页
	 * @param entity
	 *            经纪人验房信息对象
	 * @return 经纪人验房内容
	 */
	public BasePage<AgtExamineHouInfo> selectPage(BasePage<AgtExamineHouInfo> page, AgtExamineHouInfo entity) {
		entity.setPage(page);
		// 验房预约对象列表
		List<AgtExamineHouInfo> list = agtExamineHouInfoMapper.selectExamineList(entity);
		page.setList(list);
		return page;
	}

	/**
	 * 删除经纪人验房预约
	 * 
	 * @param id
	 *            验房预约对象ID
	 */
	public void deleteExamine(String id) {
		AgtExamineHouInfo record = new AgtExamineHouInfo();
		record.setId(id);
		record.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		// 更新验房预约对象
		agtExamineHouInfoMapper.updateByPrimaryKeySelective(record);
		// 获取验房预约对象
		record = agtExamineHouInfoMapper.selectByPrimaryKey(id);

		HouEntrustInfo houEntrustInfo = new HouEntrustInfo();
		houEntrustInfo.setId(record.getEntrustId());
		houEntrustInfo.setStatus(EntrustStateEnum.COMMISSIONED_ENUM.getValue());
		houEntrustInfo.setLockEid("");
		// 更新委托状态
		houEntrustInfoService.updateByPrimaryKeySelective(houEntrustInfo);
	}

	/**
	 * 验房预约
	 * 
	 * @param record
	 *            验房预约对象
	 */
	public void insertExamineHou(AgtExamineHouInfo record) {
		String phone = houEntrustInfoService.selectEntInfo(record.getEntrustId()).getPhone();
		record.preInsert();
		record.setDeleteFlg((short) YesNoEnum.NO_ENUM.getValue());
		agtExamineHouInfoMapper.insertSelective(record);
		HouEntrustInfo houEntrustInfo = new HouEntrustInfo();
		houEntrustInfo.setId(record.getEntrustId());
		houEntrustInfo.setStatus(EntrustStateEnum.RESERVATION_ENUM.getValue());
		// 更新委托状态
		houEntrustInfoService.updateByPrimaryKeySelective(houEntrustInfo);

		// 经纪人{0}先生已创建验房预约，预约时间为{1}。如有疑问，请致电经纪人：{2}。
		Object[] arguments = { UserUtils.getUser().getName(), record.getExamineTime(),
				UserUtils.getUser().getMobile() };
		String content = PropertyUtils.getMessage("msg.house.reservation.check.content", arguments);
		String receiver = houEntrustInfoService.getUid(record.getEntrustId());
		messageService.sendMessage(UserUtils.getUser().getId(), UserUtils.getUser().getName(), receiver,
				MSG_HOUSE_RESERVATION_CHECK_TITLE, content, MSG_TYPE_HOU);
		// 经纪人${name}先生/女士已发起验房预约，时间为${time}。如有疑问，请致电经纪人：${tel}
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", UserUtils.getUser().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(record.getExamineTime());
		map.put("time", time);
		map.put("tel", UserUtils.getUser().getMobile());
		smsService.sendSMSByTemplate(phone, "F0006", map);
		// 推送消息
		messagePushService.pushSingleAccount(receiver, MSG_HOUSE_RESERVATION_CHECK_TITLE, content, MSG_TYPE_HOU);

	}

	/**
	 * 修改备注
	 * 
	 * @param id
	 *            验房预约对象ID
	 * @param comment
	 *            备注
	 */
	public void updateComment(String id, String comment) {
		AgtExamineHouInfo record = new AgtExamineHouInfo();
		record.setId(id);
		record.setComment(comment);
		record.preUpdate();
		// 修改备注
		agtExamineHouInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 查询验房预约信息
	 * 
	 * @param entrustId 委托信息ID
	 * @return 经纪人验房预约对象列表
	 */
	public List<AgtExamineHouInfo> selectExaminesInfo(String entrustId) {
		AgtExamineHouInfoExample example = new AgtExamineHouInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		criteria.andEntrustIdEqualTo(entrustId);
		// 获取验房预约信息对象列表
		return agtExamineHouInfoMapper.selectByExample(example);
	}
}
