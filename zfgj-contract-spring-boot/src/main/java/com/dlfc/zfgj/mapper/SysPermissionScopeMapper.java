package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysPermissionScope;
import com.dlfc.zfgj.entity.SysPermissionScopeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SysPermissionScopeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int countByExample(SysPermissionScopeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int deleteByExample(SysPermissionScopeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int insert(SysPermissionScope record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int insertSelective(SysPermissionScope record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	List<SysPermissionScope> selectByExample(SysPermissionScopeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByExampleSelective(@Param("record") SysPermissionScope record,
                                 @Param("example") SysPermissionScopeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByExample(@Param("record") SysPermissionScope record,
                        @Param("example") SysPermissionScopeExample example);
}