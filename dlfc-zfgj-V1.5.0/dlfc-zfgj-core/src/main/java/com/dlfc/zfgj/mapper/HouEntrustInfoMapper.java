package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouEntrustInfo;
import com.dlfc.zfgj.entity.HouEntrustInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouEntrustInfoMapper extends MyMapper<HouEntrustInfo>{
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int countByExample(HouEntrustInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int deleteByExample(HouEntrustInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int insert(HouEntrustInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int insertSelective(HouEntrustInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	List<HouEntrustInfo> selectByExample(HouEntrustInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	HouEntrustInfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int updateByExampleSelective(@Param("record") HouEntrustInfo record,
			@Param("example") HouEntrustInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int updateByExample(@Param("record") HouEntrustInfo record,
			@Param("example") HouEntrustInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int updateByPrimaryKeySelective(HouEntrustInfo record);
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_entrust_info
	 * @mbggenerated  Fri May 06 13:12:57 CST 2016
	 */
	int updateByPrimaryKey(HouEntrustInfo record);
	
	/**
	 * 取消锁定房源
	 */
	int updateByLock(String id);

	List<HouEntrustInfo> selectEntrustInfoList(HouEntrustInfo example);
    
    int selectEntrustInfoObjectCount(String id);
    
    /**
     * 是否匹配复姓列表
     * @param surname 姓
     * @return 0:此姓不是复姓/1：此姓是复姓
     */
    int selectSurnamelist(String surname);
}