package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SysInfoAttMapper extends MyMapper<SysInfoAtt> {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int countByExample(SysInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int deleteByExample(SysInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int insert(SysInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int insertSelective(SysInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	List<SysInfoAtt> selectByExample(SysInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	SysInfoAtt selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int updateByExampleSelective(@Param("record") SysInfoAtt record,
                                 @Param("example") SysInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int updateByExample(@Param("record") SysInfoAtt record,
                        @Param("example") SysInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int updateByPrimaryKeySelective(SysInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_info_att
	 * @mbggenerated  Fri Jan 13 09:37:13 CST 2017
	 */
	int updateByPrimaryKey(SysInfoAtt record);

//	SysInfoAtt selectByLid(String lid);
}