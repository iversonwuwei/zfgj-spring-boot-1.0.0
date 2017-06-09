package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConDepositDistAgr;
import com.dlfc.zfgj.entity.ConDepositDistAgrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ConDepositDistAgrMapper extends MyMapper<ConDepositDistAgr>{
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int countByExample(ConDepositDistAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int deleteByExample(ConDepositDistAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int insert(ConDepositDistAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int insertSelective(ConDepositDistAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	List<ConDepositDistAgr> selectByExample(ConDepositDistAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	ConDepositDistAgr selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ConDepositDistAgr record,
                                 @Param("example") ConDepositDistAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int updateByExample(@Param("record") ConDepositDistAgr record, @Param("example") ConDepositDistAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int updateByPrimaryKeySelective(ConDepositDistAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_deposit_dist_agr
	 * @mbggenerated  Thu Dec 24 09:10:12 CST 2015
	 */
	int updateByPrimaryKey(ConDepositDistAgr record);

//	ConDepositDistAgr selectByIdAndStatus(String id);
}