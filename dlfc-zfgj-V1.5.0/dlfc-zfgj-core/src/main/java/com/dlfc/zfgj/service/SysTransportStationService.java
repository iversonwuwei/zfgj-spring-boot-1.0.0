/**
 * @name: SysTransportStationService.java 
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

import com.dlfc.zfgj.entity.SysTransportStation;
import com.dlfc.zfgj.mapper.SysTransportStationMapper;

/**
 * @name: SysTransportStationService
 * @description: 交通线路站表Service
 * @version 1.0
 * @author fancy
 *
 */
@Service("sysTransportStationService")
public class SysTransportStationService {
	/**交通线路站点持久层对象*/
	@Autowired
	private SysTransportStationMapper sysTransportStationMapper;

	/**
	 * 根据ID获取交通线路站点对象
	 * 
	 * @param id 交通线路站点ID
	 * @return 交通线路站点对象
	 */
	public SysTransportStation select(String id) {
		return sysTransportStationMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据商圈ID 获取地铁
	 * 
	 * @param tradeId 商圈ID
	 * @return 交通线路站点对象列表
	 */
	public List<SysTransportStation> selectStation(String tradeId) {
		return sysTransportStationMapper.selectStation(tradeId);
	}
}
