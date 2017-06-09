package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.AgtCompInfoLog;
import com.dlfc.zfgj.entity.AgtCompInfoLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface AgtCompInfoLogMapper extends MyMapper<AgtCompInfoLog> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int countByExample(AgtCompInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int deleteByExample(AgtCompInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int insert(AgtCompInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int insertSelective(AgtCompInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	List<AgtCompInfoLog> selectByExample(AgtCompInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	AgtCompInfoLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int updateByExampleSelective(@Param("record") AgtCompInfoLog record,
                                 @Param("example") AgtCompInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int updateByExample(@Param("record") AgtCompInfoLog record,
                        @Param("example") AgtCompInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int updateByPrimaryKeySelective(AgtCompInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info_log
	 * @mbggenerated  Wed Dec 16 18:56:44 CST 2015
	 */
	int updateByPrimaryKey(AgtCompInfoLog record);
}