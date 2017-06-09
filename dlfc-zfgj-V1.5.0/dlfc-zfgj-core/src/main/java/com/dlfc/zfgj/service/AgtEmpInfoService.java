/**
* @name: AgtEmpInfoService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 查询经纪人信息，获取经纪人权限，查询公司是否锁定管理员用户等
*
* @version: 1.0
* @date : 2016年2月29日 
* @author: yuanjiwei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年2月29日      yuanjiwei       1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.entity.AgtEmpInfoList;
import com.dlfc.zfgj.entity.SysPermissionScope;
import com.dlfc.zfgj.entity.SysPermissionScopeExample;
import com.dlfc.zfgj.entity.SysPermissionScopeExample.Criteria;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.SysPermissionScopeMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;

/**
 * 
 * @name: AgtEmpInfoService
 * @description: 查询经纪人信息，获取经纪人权限，查询公司是否锁定管理员用户等
 * 
 * @version 1.0
 * @author fanyc
 *
 */
@Service("agtEmpInfoService")
public class AgtEmpInfoService {
	/** 经纪人信息Mapper */
	@Autowired
	private AgtEmpInfoMapper agtEmpInfoMapper;
	/** 经纪人权限范围Mapper */
	@Autowired
	private SysPermissionScopeMapper sysPermissionScopeMapper;
	/** 经纪人角色类型 */
	private static final String EMP_ROLE = "emp-role";
	/** 管理员角色类型 */
	private static final String COMP_ROLE = "comp-role";
	/** 超级管理员角色类型 */
	private static final String ASSIGNMENT = "assignment";

	/**
	 * 获取当前用户可查看的经纪人范围内的所有经纪人ID
	 * 
	 * @param permission
	 *            许可信息
	 * @return 经纪人ID列表
	 */
	public List<String> getEmpIds(String permission) {
		// 获取权限范围
		String scope = getScope(permission);
		List<String> retList = new ArrayList<String>();

		User user = UserUtils.getUser();
		String officeId = user.getOffice().getId();
		String companyId = user.getCompany().getId();
		String empId = user.getEmpId();

		AgtEmpInfo agtEmpInfo = new AgtEmpInfo();

		if ("0".equals(scope)) {// 无权限
			retList.add("");
		} else if ("2".equals(scope)) {// 整个company
			agtEmpInfo.setCompanyId(companyId);
			List<AgtEmpInfo> empIdList = agtEmpInfoMapper.selectEmpIdList(agtEmpInfo);

			for (AgtEmpInfo emp : empIdList) {
				String id = emp.getId();
				retList.add(id);
			}
		} else if ("4".equals(scope)) {// 整个office
			agtEmpInfo.setOfficeId(officeId);
			List<AgtEmpInfo> empIdList = agtEmpInfoMapper.selectEmpIdList(agtEmpInfo);

			for (AgtEmpInfo emp : empIdList) {
				String id = emp.getId();
				retList.add(id);
			}
		} else if ("8".equals(scope)) {// 经纪人自己
			retList.add(empId);
		} else {// 无权限

		}

		return retList;
	}

	/**
	 * 获取当前用户可查看的经纪人范围内的所有经纪人ID
	 * 
	 * @param permission
	 *            许可信息
	 * @return 经纪人ID字符串
	 */
	public String getEmpIdStr(String permission) {
		String scope = getScope(permission);

		StringBuffer buffer = new StringBuffer();
		buffer.append("''");

		User user = UserUtils.getUser();
		String officeId = user.getOffice().getId();
		String companyId = user.getCompany().getId();
		String empId = user.getEmpId();

		AgtEmpInfo agtEmpInfo = new AgtEmpInfo();

		if ("0".equals(scope)) {// 无权限

		} else if ("2".equals(scope)) {// 整个company
			String comId = agtEmpInfoMapper.selectCompIdByOfficeId(companyId);
			agtEmpInfo.setCompanyId(comId);
			List<AgtEmpInfo> empIdList = agtEmpInfoMapper.selectEmpIdList(agtEmpInfo);

			for (AgtEmpInfo emp : empIdList) {
				String id = emp.getId();
				buffer.append(",").append("'").append(id).append("'");
			}
		} else if ("4".equals(scope)) {// 整个office
			agtEmpInfo.setOfficeId(officeId);
			List<AgtEmpInfo> empIdList = agtEmpInfoMapper.selectEmpIdList(agtEmpInfo);

			for (AgtEmpInfo emp : empIdList) {
				String id = emp.getId();
				buffer.append(",").append("'").append(id).append("'");
			}
		} else if ("8".equals(scope)) {// 经纪人自己
			buffer.append(",").append("'").append(empId).append("'");
		} else {// 无权限

		}

		return buffer.toString();
	}

	/**
	 * 根据permission+role获取当前登录经纪人scope
	 * 
	 * @param permission
	 *            许可信息
	 * @return 权限范围
	 */
	public String getScope(String permission) {
		String scope = "";
		List<Role> roleList = UserUtils.getUser().getRoleList();
		for (Role role : roleList) {
			// 经纪人
			if (EMP_ROLE.equals(role.getRoleType())) {
				SysPermissionScopeExample example = new SysPermissionScopeExample();
				Criteria criteria = example.createCriteria();
				criteria.andPermissionEqualTo(permission);
				criteria.andRoleIdEqualTo(role.getId());
				criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
				List<SysPermissionScope> list = sysPermissionScopeMapper.selectByExample(example);
				if (list.size() > 0) {
					SysPermissionScope sysPermissionScope = list.get(0);
					String scope_temp = sysPermissionScope.getScope();
					if (scope_temp != null && !"".equals(scope_temp)) {
						scope = scope_temp;
					}
					break;
				}
				// 管理员 and 超超级管理员
			} else if (COMP_ROLE.equals(role.getRoleType()) || ASSIGNMENT.equals(role.getRoleType())) {
				scope = "2";
				break;
			}
		}

		return scope;
	}

	/**
	 * 获取公司总人数
	 * 
	 * @param compId
	 *            公司ID
	 * @return 总人数
	 */
	public int selectCountByOffice(String compId) {
		return agtEmpInfoMapper.selectCountByOffice(compId);
	}

	/**
	 * 是否锁定（经纪人用户）
	 * 
	 * @param userId
	 *            经纪人用户ID
	 * @return true:锁定,false:未锁定
	 */
	public boolean isCompLockedByUserId(String userId) {
		// 经纪人数量
		int res = agtEmpInfoMapper.isCompLockedByUserId(userId);
		// 经纪人数量大于零
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否锁定（管理用户）
	 * 
	 * @param userId
	 *            管理员用户ID
	 * @return true:锁定,false:未锁定
	 */
	public boolean isCompLockedBySysUser(String userId) {
		// 管理员数量
		int res = agtEmpInfoMapper.isCompLockedBySysUser(userId);
		// 管理员数量大于零
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 按公司查询经纪人信息列表
	 * 
	 * @param agtEmpInfoList
	 *            经纪人信息对象列表
	 * @return 查询成功失败状态
	 */
	public List<AgtEmpInfoList> selectEmpINfoByOffice(AgtEmpInfoList agtEmpInfoList) {
		return agtEmpInfoMapper.selectEmpINfoByOffice(agtEmpInfoList);
	};
	
	/**
	 * 通过主键ID查询经纪人信息
	 * 
	 * @param id 经纪人ID
	 * @return 查询成功失败状态
	 */
	public List<AgtEmpInfo> getEmpInfo(String id) {
		AgtEmpInfoExample example = new AgtEmpInfoExample();
		com.dlfc.zfgj.entity.AgtEmpInfoExample.Criteria c = example.createCriteria();
		c.andDeleteFlgEqualTo((short)YesNoEnum.NO_ENUM.getValue());
		c.andUserIdEqualTo(id);
		c.andStatusEqualTo(Const.AGT_STATUS_ONJOB);
		return agtEmpInfoMapper.selectByExample(example);
	};
}
