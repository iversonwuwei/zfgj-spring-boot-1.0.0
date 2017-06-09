/**
* @name: UserDelInfoService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年8月15日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月15日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.UsrDelInfo;
import com.dlfc.zfgj.mapper.UsrDelInfoMapper;

/**
 * UserDelInfoService
 * @description: 用户删除记录表
 * @version 1.0
 * @author yuanjw
 */
@Service("userDelInfoService")
public class UserDelInfoService {
	/**用户删除记录持久层对象*/
	@Autowired
	private UsrDelInfoMapper usrDelInfoMapper;

	/**
	 * 保存删除用户记录
	 * @param usrDelInfo
	 */
	public void insert(UsrDelInfo usrDelInfo) {
		usrDelInfoMapper.save(usrDelInfo);
	}

}
