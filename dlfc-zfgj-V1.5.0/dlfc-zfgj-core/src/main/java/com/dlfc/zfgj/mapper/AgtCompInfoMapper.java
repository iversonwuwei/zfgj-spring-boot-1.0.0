package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtCompInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface AgtCompInfoMapper extends MyMapper<AgtCompInfo> {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int countByExample(AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int deleteByExample(AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int insert(AgtCompInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int insertSelective(AgtCompInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	List<AgtCompInfo> selectByExampleWithBLOBs(AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	List<AgtCompInfo> selectByExampleList(AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	AgtCompInfo selectByPrimaryKey(String id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByExampleSelective(@Param("record") AgtCompInfo record, @Param("example") AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") AgtCompInfo record, @Param("example") AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByExample(@Param("record") AgtCompInfo record, @Param("example") AgtCompInfoExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByPrimaryKeySelective(AgtCompInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(AgtCompInfo record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	int updateByPrimaryKey(AgtCompInfo record);
	List<AgtCompInfo> findList();
	//获取公司发布房源总数
	int getHouseTotal(String id);
	//获取公司在职经纪人
	int getAgentTotal(String id);
	//获取公司签订合同总数
	int getContractTotal(String id);
    
	AgtCompInfo selectById(String id);
//    int isCom(String com);
}