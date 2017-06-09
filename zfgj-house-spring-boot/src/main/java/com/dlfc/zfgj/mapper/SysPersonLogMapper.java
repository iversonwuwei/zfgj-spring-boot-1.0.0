package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysPersonLog;
import com.dlfc.zfgj.entity.SysPersonLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SysPersonLogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int countByExample(SysPersonLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int deleteByExample(SysPersonLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int insert(SysPersonLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int insertSelective(SysPersonLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	List<SysPersonLog> selectByExample(SysPersonLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	SysPersonLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int updateByExampleSelective(@Param("record") SysPersonLog record, @Param("example") SysPersonLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int updateByExample(@Param("record") SysPersonLog record, @Param("example") SysPersonLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int updateByPrimaryKeySelective(SysPersonLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_person_log
	 * @mbggenerated  Tue Dec 22 16:42:34 CST 2015
	 */
	int updateByPrimaryKey(SysPersonLog record);
}