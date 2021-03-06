package com.dlfc.zfgj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConContractLog;
import com.dlfc.zfgj.entity.ConContractLogExample;
@MyBatisDao
public interface ConContractLogMapper extends MyMapper<ConContractLog> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int countByExample(ConContractLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int deleteByExample(ConContractLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int insert(ConContractLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int insertSelective(ConContractLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	List<ConContractLog> selectByExample(ConContractLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	ConContractLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ConContractLog record,
			@Param("example") ConContractLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int updateByExample(@Param("record") ConContractLog record, @Param("example") ConContractLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int updateByPrimaryKeySelective(ConContractLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract_log
	 * @mbggenerated  Thu May 12 13:38:54 CST 2016
	 */
	int updateByPrimaryKey(ConContractLog record);

	/**
	 * 
	 * 
	 */
	
	void save(ConContractLog conLog);
}