package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouEntrustCompLink;
import com.dlfc.zfgj.entity.HouEntrustCompLinkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface HouEntrustCompLinkMapper extends MyMapper<HouEntrustCompLink> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int countByExample(HouEntrustCompLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int deleteByExample(HouEntrustCompLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int insert(HouEntrustCompLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int insertSelective(HouEntrustCompLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    List<HouEntrustCompLink> selectByExample(HouEntrustCompLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    HouEntrustCompLink selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int updateByExampleSelective(@Param("record") HouEntrustCompLink record, @Param("example") HouEntrustCompLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int updateByExample(@Param("record") HouEntrustCompLink record, @Param("example") HouEntrustCompLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int updateByPrimaryKeySelective(HouEntrustCompLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    int updateByPrimaryKey(HouEntrustCompLink record);
}