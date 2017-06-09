/**
* @name: HouCoOwnerService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 房源共有人查询添加修改操作
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.HouCoOwner;
import com.dlfc.zfgj.entity.HouCoOwnerLog;
import com.dlfc.zfgj.mapper.HouCoOwnerMapper;

/**
 * @name: HouCoOwnerService
 * @description: 房源共有人查询添加修改操作
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("houCoOwnerService")
public class HouCoOwnerService {
	/** 房源共有人mapper */
	@Autowired
	private HouCoOwnerMapper coOwnerMapper;

	/**
	 * 添加房源-共有人
	 * 
	 * @param coOwner
	 *            房源共有人对象
	 */
	public void saveCoOwner(HouCoOwner coOwner) {
		coOwner.preInsert();
		coOwnerMapper.insertSelective(coOwner);
	}

	/**
	 * 编辑房源-修改共有人信息-删除共有人
	 * 
	 * @param coOwner
	 *            房源共有人对象
	 */
	public void deleteCoOwner(HouCoOwner coOwner) {
		coOwner.preUpdate();
		coOwner.setDeleteFlg((short) 1);
		coOwnerMapper.updateByPrimaryKeySelective(coOwner);
	}

	/**
	 * 编辑房源-保存共有人到记录表
	 * 
	 * @param houseLogId
	 *            房源日志ID
	 * @param coId
	 *            房源ID
	 * @param event
	 *            操作事件
	 * @param desc
	 *            描述
	 */
	public void saveCoOwnerLog(String houseLogId, String coId, Integer event, String desc) {
		HouCoOwnerLog coOwnerLog = new HouCoOwnerLog();
		coOwnerLog.preInsert();
		coOwnerLog.setHouInfoLogId(houseLogId);
		coOwnerLog.setHid(coId);
		coOwnerLog.setOptEvent(event);
		coOwnerLog.setDcrp(desc);
		coOwnerMapper.saveCoOwnerLog(coOwnerLog);
	}

	/**
	 * 查找共有人
	 * 
	 * @param coOwner
	 *            房源共有人
	 * @return 房源共有人对象列表
	 * @throws Exception
	 */
	public List<HouCoOwner> findCoOwner(HouCoOwner coOwner) {
		return this.coOwnerMapper.findCoOwner(coOwner);
	}

}
