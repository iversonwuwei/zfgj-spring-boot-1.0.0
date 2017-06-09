package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConDissAgr;
import com.dlfc.zfgj.entity.ConDissAgrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ConDissAgrMapper extends MyMapper<ConDissAgr>{
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int countByExample(ConDissAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int deleteByExample(ConDissAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int insert(ConDissAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int insertSelective(ConDissAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	List<ConDissAgr> selectByExample(ConDissAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	ConDissAgr selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ConDissAgr record, @Param("example") ConDissAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int updateByExample(@Param("record") ConDissAgr record, @Param("example") ConDissAgrExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int updateByPrimaryKeySelective(ConDissAgr record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_diss_agr
	 * @mbggenerated  Thu Dec 24 09:13:03 CST 2015
	 */
	int updateByPrimaryKey(ConDissAgr record);

//	ConDissAgr selectByIdAndStatus(String id);
}