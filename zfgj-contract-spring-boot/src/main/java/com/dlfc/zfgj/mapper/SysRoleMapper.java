package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysRole;
import com.dlfc.zfgj.entity.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SysRoleMapper extends MyMapper<SysRole> {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int countByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int deleteByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int insert(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int insertSelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	List<SysRole> selectByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	SysRole selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByExampleSelective(@Param("record") SysRole record,
                                 @Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByExample(@Param("record") SysRole record,
                        @Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	int updateByPrimaryKey(SysRole record);

//	/**
//     * 获得权限列表，以后重构     * @param role
//     * @return
//     */
//    List<Role> findList(Role role);
//
//    /**
//     * 获得权限列表，以后重构     * @param role
//     * @return
//     */
//    List<Role> findMgrList(Role role);
}