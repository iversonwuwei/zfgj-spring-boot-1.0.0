package com.dlfc.zfgj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConContractExample;

@MyBatisDao
public interface ConContractMapper extends MyMapper<ConContract> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int countByExample(ConContractExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int deleteByExample(ConContractExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int insert(ConContract record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int insertSelective(ConContract record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	List<ConContract> selectByExample(ConContractExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	ConContract selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ConContract record,
			@Param("example") ConContractExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int updateByExample(@Param("record") ConContract record,
			@Param("example") ConContractExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int updateByPrimaryKeySelective(ConContract record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_contract
	 * @mbggenerated  Tue Oct 18 10:00:34 CST 2016
	 */
	int updateByPrimaryKey(ConContract record);

	ConContract findDissById(String cid);

	List<ConContract> findByList(ConContract con);

	List<ConContract> getConInPeriod(ConContract con);

	double getAreaInPeriod(ConContract con);

	ConContract findById(String cid);

	ConContract findOne(ConContract con);

	void update(ConContract contract);
	
	List<ConContract> getEndTime(ConContract con);
}