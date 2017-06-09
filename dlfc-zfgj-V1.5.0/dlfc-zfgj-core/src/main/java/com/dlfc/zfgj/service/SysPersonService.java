
/**
* @name: SysPersonService.java 
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysPersonExample;
import com.dlfc.zfgj.entity.SysPersonExample.Criteria;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.github.pagehelper.PageHelper;
import com.housecenter.dlfc.auth.AuthFacet;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

import tk.mybatis.mapper.util.StringUtil;

/**
 * SysPersonService
 * @description: 用户信息
 * @version 1.0
 * @author yuanjw
 */
@Service("personService")
public class SysPersonService {
	/**用户信息持久层对象*/
	@Autowired
	protected SysPersonMapper mapper;

	/**
	 * 获取单条数据
	 * 
	 * @param id 用户信息ID
	 * @return 用户信息对象
	 */
	public SysPerson get(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询列表数据
	 * 
	 * @param example 查询条件对象
	 * @return 用户信息对象列表
	 */
	public List<SysPerson> selectByExample(SysPersonExample example) {
		return mapper.selectByExample(example);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param page 分页对象
	 * @param entity 用户信息对象
	 * @return 页面对象<用户信息对象>
	 */
	public BasePage<SysPerson> selectPage(BasePage<SysPerson> page, SysPerson entity) {
		entity.setPage(page);
		return page;
	}

	/**
	 * 查询分页数据
	 * 
	 * @param example 查询条件对象
	 * @param page 分页对象
	 * @param rows 行数
	 * @return 用户信息对象列表
	 */
	public List<SysPerson> selectPageByExample(SysPersonExample example, int page, int rows) {
		// 分页查询
		PageHelper.startPage(page, rows);
		return selectByExample(example);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity 用户信息对象
	 */
	@Transactional(readOnly = false)
	public void save(SysPerson entity) {
		if (entity.getIsNewRecord()) {
			entity.preInsert();
			mapper.insert(entity);
		} else {
			entity.preUpdate();
			mapper.updateByPrimaryKey(entity);
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param key 用户信息ID
	 * @return 操作成功失败状态
	 */
	@Transactional(readOnly = false)
	public int deleteByKey(String key) {
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 修改数据
	 * 
	 * @param entity 用户信息对象
	 * @return 操作成功失败状态
	 */
	@Transactional(readOnly = false)
	public int updateAll(SysPerson entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	/**
	 * 修改数据
	 * 
	 * @param entity 用户信息对象
	 * @return 操作成功失败状态
	 */
	@Transactional(readOnly = false)
	public int updateNotNull(SysPerson entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 保存个人信息
	 * 
	 * @param person 用户信息对象
	 * @return 操作成功失败状态
	 */
	public int insertUser(SysPerson person) {
		Integer id = (Integer) mapper.insertSelective(person);
		return id;
	}

	/**
	 * 查询用户信息ID
	 * 
	 * @param name 姓名
	 * @param idNo 身份证号
	 * @return 用户信息ID 
	 * @throws Exception
	 */
	public String selectId(String name, String idNo) {
		SysPersonExample example = new SysPersonExample();
		Criteria cri = example.createCriteria();
		cri.andNameEqualTo(name).andIdNoEqualTo(idNo).andDeleteFlgEqualTo((short)0);
		return mapper.selectByExample(example).get(0).getId();
	}
	
	/**
	 * 判断证件号在person表是否使用过
	 * 
	 * @param idNo 身份证号
	 * @return true:已经被使用，false:未被使用
	 */
	public boolean isIdNoBinded(String idNo) {
		if ((int) mapper.isIdNoBinded(idNo) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据身份证号和姓名查用户信息的ID
	 * 
	 * @param name 姓名
	 * @param idNo 身份证号
	 * @return 用户信息ID
	 */
	public String selectIdByCard(String name,String idNo){
		SysPersonExample example = new SysPersonExample();
		Criteria cri = example.createCriteria();
		cri.andNameEqualTo(name).andIdNoEqualTo(idNo);
		List<SysPerson> sp = mapper.selectByExample(example);
		if(sp != null && sp.size() != 0){
			return sp.get(0).getId();
		}else{
			return null;
		}
		
	}
	
	/**
	 * 实名认证
	 * 
	 * @param name 姓名
	 * @param idType 证件类型
	 * @param idNo 证件号
	 * @return 用户信息存在返回用户信息对象ID，认证失败返回null
	 */
	public String checkIdentity(String name, int idType, String idNo) {
		String perId = "";
		SysPersonExample example = new SysPersonExample();
		com.dlfc.zfgj.entity.SysPersonExample.Criteria cri = example.createCriteria();
		cri.andNameEqualTo(name);
		cri.andIdTypeEqualTo(idType);
		cri.andIdNoEqualTo(idNo);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		//查询用户信息对象列表
		List<SysPerson> personList = mapper.selectByExample(example);
		if (personList != null && personList.size() != 0) {
			//获取用户信息对象ID
			perId = personList.get(0).getId();
		}
		if (StringUtil.isEmpty(perId)) {
			// 调用公安接口验证
			boolean result =AuthFacet.authID(name.trim(), idNo.trim());
			if(!result)
			{
				return null;
			}
		}
		return perId;
	}
}
