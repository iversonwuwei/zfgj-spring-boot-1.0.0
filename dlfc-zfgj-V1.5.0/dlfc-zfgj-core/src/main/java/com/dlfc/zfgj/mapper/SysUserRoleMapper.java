package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysUserRole;
import com.dlfc.zfgj.entity.SysUserRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysUserRoleMapper extends MyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int countByExample(SysUserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int deleteByExample(SysUserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int deleteByPrimaryKey(@Param("userId") String userId,
			@Param("roleId") String roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int insert(SysUserRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int insertSelective(SysUserRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	List<SysUserRole> selectByExample(SysUserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int updateByExampleSelective(@Param("record") SysUserRole record,
			@Param("example") SysUserRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user_role
	 * @mbggenerated  Mon Nov 09 16:01:06 CST 2015
	 */
	int updateByExample(@Param("record") SysUserRole record,
			@Param("example") SysUserRoleExample example);
	
	int deleteByUserId(String userId);
}