package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.AgtRequirementInfo;
import com.dlfc.zfgj.entity.AgtRequirementInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface AgtRequirementInfoMapper extends MyMapper<AgtRequirementInfo>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int countByExample(AgtRequirementInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int deleteByExample(AgtRequirementInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int insert(AgtRequirementInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int insertSelective(AgtRequirementInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    List<AgtRequirementInfo> selectByExample(AgtRequirementInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    AgtRequirementInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int updateByExampleSelective(@Param("record") AgtRequirementInfo record, @Param("example") AgtRequirementInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int updateByExample(@Param("record") AgtRequirementInfo record, @Param("example") AgtRequirementInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int updateByPrimaryKeySelective(AgtRequirementInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_requirement_info
     *
     * @mbggenerated Wed Nov 18 16:55:17 CST 2015
     */
    int updateByPrimaryKey(AgtRequirementInfo record);
}