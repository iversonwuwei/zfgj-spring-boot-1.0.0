package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.ConOtherCostsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface ConOtherCostsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int countByExample(ConOtherCostsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int deleteByExample(ConOtherCostsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int insert(ConOtherCosts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int insertSelective(ConOtherCosts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	List<ConOtherCosts> selectByExample(ConOtherCostsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	ConOtherCosts selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ConOtherCosts record, @Param("example") ConOtherCostsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int updateByExample(@Param("record") ConOtherCosts record, @Param("example") ConOtherCostsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int updateByPrimaryKeySelective(ConOtherCosts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table con_other_costs
	 * @mbggenerated  Wed Jun 15 14:45:44 CST 2016
	 */
	int updateByPrimaryKey(ConOtherCosts record);
}