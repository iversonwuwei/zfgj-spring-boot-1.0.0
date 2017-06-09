/**
* @name: AgtCertLinkService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 根据id更新数据,判断资格证号唯一,根据id取得信息,判断是否资格认证,判断是否正在认证中,根据uid取得信息
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

import com.dlfc.admin.common.service.BaseService;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.AgtCertLinkExample;
import com.dlfc.zfgj.entity.AgtCertLinkExample.Criteria;
import com.dlfc.zfgj.mapper.AgtCertLinkMapper;

/**
 * @name: AgtCertLinkService
 * @description:根据id更新数据,判断资格证号唯一,根据id取得信息,判断是否资格认证,判断是否正在认证中,根据uid取得信息
 * @version 1.0
 * @author QiuFei
 *
 */
@Service("agtCertLinkService")
public class AgtCertLinkService extends BaseService {

	/** 经纪人认证Mapper */
	@Autowired
	private AgtCertLinkMapper agtCertLinkMapper;

	/**
	 * 插入数据
	 * 
	 * @param acl
	 *            经纪人认证对象
	 * @return 插入成功失败状态
	 */
	public int insert(AgtCertLink acl) {
		return agtCertLinkMapper.insertSelective(acl);
	}

	/**
	 * 根据id更新数据
	 * 
	 * @param acl
	 *            经纪人认证对象
	 * @return 更新成功失败状态
	 */
	public int updateById(AgtCertLink acl) {
		return agtCertLinkMapper.updateByPrimaryKeySelective(acl);
	}

	/**
	 * 判断资格证号唯一
	 * 
	 * @param certCardNo 认证卡号
	 * @return true:已绑定，false:未绑定
	 */
	public boolean isAgtCardBinded(String certCardNo) {
		// 判断资格证是否已绑定 true：已绑定
		if ((int) agtCertLinkMapper.isAgtCardBinded(certCardNo) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据id取得信息
	 * 
	 * @param id
	 *            经纪人认证ID
	 * @return 查询成功失败状态
	 */
	public AgtCertLink findById(String id) {
		return agtCertLinkMapper.selectByPrimaryKey(id);
	}

	/**
	 * 判断是否资格认证
	 * 
	 * @param uid
	 *            资格证号ID
	 * @return true:已认证，false:未认证
	 */
	public boolean isAgtCert(String uid) {
		// 资格认证存在校验 true：已认证
		if ((int) agtCertLinkMapper.isAgtCert(uid) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否正在认证中
	 * 
	 * @param uid
	 *            资格证号ID
	 * @return true:认证中，false:未认证中
	 */
	public boolean isAgtCertStatus(String uid) {
		// 经纪人认证状态判断 true：在认证中
		if ((int) agtCertLinkMapper.isAgtCertStatus(uid) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据用户ID取得认证信息
	 * 
	 * @param uid
	 *            用户ID
	 * @return AgtCertLink 经纪人认证对象
	 */
	public AgtCertLink findByUid(String uid) {
		AgtCertLinkExample agtExample = new AgtCertLinkExample();
		Criteria crit = agtExample.createCriteria();
		crit.andUserIdEqualTo(uid);
		crit.andDeleteFlgEqualTo((short) 0);
		List<AgtCertLink> list = agtCertLinkMapper.selectByExample(agtExample);
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
