package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConDissLog;
import com.dlfc.zfgj.entity.ConDissLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ConDissLogMapper extends MyMapper<ConDissLog> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int countByExample(ConDissLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int deleteByExample(ConDissLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int insert(ConDissLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int insertSelective(ConDissLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	List<ConDissLog> selectByExample(ConDissLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	ConDissLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ConDissLog record, @Param("example") ConDissLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int updateByExample(@Param("record") ConDissLog record, @Param("example") ConDissLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int updateByPrimaryKeySelective(ConDissLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_log
	 * @mbggenerated  Fri Dec 18 16:46:36 CST 2015
	 */
	int updateByPrimaryKey(ConDissLog record);
	
//	List<ConDissLog> findByDidDesc(String id);
//
//	int insertLog(ConDissLog record);
}