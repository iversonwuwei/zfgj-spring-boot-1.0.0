package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConDepositLog;
import com.dlfc.zfgj.entity.ConDepositLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface ConDepositLogMapper extends MyMapper<ConDepositLog> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int countByExample(ConDepositLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int deleteByExample(ConDepositLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int insert(ConDepositLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int insertSelective(ConDepositLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	List<ConDepositLog> selectByExample(ConDepositLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	ConDepositLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ConDepositLog record, @Param("example") ConDepositLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int updateByExample(@Param("record") ConDepositLog record, @Param("example") ConDepositLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int updateByPrimaryKeySelective(ConDepositLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_log
	 * @mbggenerated  Tue Nov 10 13:05:31 CST 2015
	 */
	int updateByPrimaryKey(ConDepositLog record);
}