package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.AgtExamineHouInfo;
import com.dlfc.zfgj.entity.AgtExamineHouInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface AgtExamineHouInfoMapper extends MyMapper<AgtExamineHouInfo>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int countByExample(AgtExamineHouInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int deleteByExample(AgtExamineHouInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int insert(AgtExamineHouInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int insertSelective(AgtExamineHouInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    List<AgtExamineHouInfo> selectByExample(AgtExamineHouInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    AgtExamineHouInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int updateByExampleSelective(@Param("record") AgtExamineHouInfo record, @Param("example") AgtExamineHouInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int updateByExample(@Param("record") AgtExamineHouInfo record, @Param("example") AgtExamineHouInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int updateByPrimaryKeySelective(AgtExamineHouInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_examine_hou_info
     *
     * @mbggenerated Fri Mar 25 14:50:10 CST 2016
     */
    int updateByPrimaryKey(AgtExamineHouInfo record);
    
    List<AgtExamineHouInfo> selectExamineList(AgtExamineHouInfo record);
    
    int selectExamineeCount(String id);
}