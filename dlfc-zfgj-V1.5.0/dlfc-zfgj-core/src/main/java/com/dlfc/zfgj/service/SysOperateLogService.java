
/**
* @name: OperateLogService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年9月21日 
* @author: Sun.Zhi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年9月21日       Sun.Zhi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysOperateLog;
import com.dlfc.zfgj.mapper.SysOperateLogMapper;

/**
 * @name: OperateLogService
 * @description: 操作日志Service
 * @version 1.0
 * @author Sun.Zhi
 */
@Service("sysOperateLogService")
public class SysOperateLogService {
	/**操作日志mapper*/
	@Autowired
	private SysOperateLogMapper sysOperateLogMapper;

	/**
	 * 新插入log
	 * 
	 * @param log log对象
	 */
	public void save(SysOperateLog log) {
		sysOperateLogMapper.insert(log);
	}

	/**
	 * 更新log
	 * 
	 * @param record log对象
	 */
	public void update(SysOperateLog record) {
		sysOperateLogMapper.updateByPrimaryKeySelective(record);
	}
}
