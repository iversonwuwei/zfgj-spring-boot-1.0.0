
/**
* @name: SysMailSnService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
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

import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.zfgj.entity.SysMailSn;
import com.dlfc.zfgj.entity.SysMailSnExample;
import com.dlfc.zfgj.entity.SysMailSnExample.Criteria;
import com.dlfc.zfgj.mapper.SysMailSnMapper;

/**
 * @name: SysMailSnService
 * @description: 邮件Service
 * @version 1.0
 * @author QiuFei
 *
 */
@Service("sysMailSnService")
public class SysMailSnService {
	/** 邮件mapper */
	@Autowired
	private SysMailSnMapper sysMailSnMapper;

	/**
	 * 保存邮件
	 * 
	 * @param sys 邮箱对象
	 * @return 插入成功失败状态
	 */
	public int insert(SysMailSn sys) throws Exception {
		return sysMailSnMapper.insertSelective(sys);
	}

	/**
	 * 查询邮件列表
	 * 
	 * @param sys 邮箱对象
	 * @return 邮箱列表
	 */
	public List<SysMailSn> findInfo(User user, String s) throws Exception {
		SysMailSnExample mailExample = new SysMailSnExample();
		Criteria crit = mailExample.createCriteria();
		crit.andUserIdEqualTo(user.getId());
		crit.andSnEqualTo(s);
		crit.andDeleteFlgEqualTo((short) 0);

		return sysMailSnMapper.selectByExample(mailExample);
	}

	/**
	 * 根据id更新数据
	 * 
	 * @param sys 邮箱对象
	 * @return 修改成功失败状态
	 */
	public int updateById(SysMailSn sys) throws Exception {
		return sysMailSnMapper.updateByPrimaryKeySelective(sys);
	}

	/**
	 * 查找邮件数量
	 * 
	 * @param sn 邮箱对象
	 * @return 邮件数量
	 */
	public Integer find15(SysMailSn sn) throws Exception {
		return (Integer) sysMailSnMapper.find15(sn);
	}

}
