/**
* @name: SysBankService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 查询银行信息
*
* @version: 1.0
* @date : 2015年11月5日 
* @author: QiuFei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月5日       QiuFei        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysBank;
import com.dlfc.zfgj.entity.SysBankExample;
import com.dlfc.zfgj.entity.SysBankExample.Criteria;
import com.dlfc.zfgj.mapper.SysBankMapper;

/**
 * @name: SysBankService
 * @description: 查询银行信息
 * @version 1.0
 * @author QiuFei
 *
 */
@Service
public class SysBankService {
	/**银行信息mapper*/
	@Autowired
	private SysBankMapper bankMapper;

	/**
	 * 获取银行信息列表
	 * @return List<SysBank> 银行对象列表
	 */
	public List<SysBank> getList() {
		SysBankExample bankExample = new SysBankExample();
		Criteria criteria = bankExample.createCriteria();
		criteria.andDeleteFlgEqualTo((short)0);
		return bankMapper.selectByExample(bankExample);
	}
}
