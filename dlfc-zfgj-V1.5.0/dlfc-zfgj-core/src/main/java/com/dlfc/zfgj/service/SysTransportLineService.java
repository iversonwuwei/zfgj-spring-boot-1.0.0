/**
 * @name: SysTransportLineService.java 
 *
 * @Copyright: (c) 2016 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2016年4月23日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2016年4月23日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysTransportLine;
import com.dlfc.zfgj.mapper.SysTransportLineMapper;

/**
 * @name: SysTransportLineService
 * @description: 交通线路表Service
 * @version 1.0
 * @author fancy
 *
 */
@Service("sysTransportLineService")
public class SysTransportLineService {
	/**地铁线路持久层对象*/
	@Autowired
	private SysTransportLineMapper sysTransportLineMapper;

	/**
	 * 通过ID获取地铁
	 * 
	 * @param id 地铁线ID
	 * @return 地铁线路对象
	 */
	public SysTransportLine selectByPrimaryKey(String id) {
		return sysTransportLineMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据商圈ID获取地铁
	 * 
	 * @param tradeId 商圈ID
	 * @return 地铁线路对象列表
	 */
	public List<SysTransportLine> selectStation(String tradeId) {
		return sysTransportLineMapper.selectStation(tradeId);
	}
}
