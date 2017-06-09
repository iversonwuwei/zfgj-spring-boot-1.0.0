package com.dlfc.zfgj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoExample;
import com.dlfc.zfgj.entity.AgtEmpInfoList;

@MyBatisDao
public interface AgtEmpInfoMapper extends MyMapper<AgtEmpInfo> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int countByExample(AgtEmpInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int deleteByExample(AgtEmpInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int insert(AgtEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int insertSelective(AgtEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	List<AgtEmpInfo> selectByExample(AgtEmpInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	AgtEmpInfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int updateByExampleSelective(@Param("record") AgtEmpInfo record, @Param("example") AgtEmpInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int updateByExample(@Param("record") AgtEmpInfo record, @Param("example") AgtEmpInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int updateByPrimaryKeySelective(AgtEmpInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	int updateByPrimaryKey(AgtEmpInfo record);

	/**
	 * 查询全部信息
	 * @param example
	 * @return
	 */
	List<AgtEmpInfo> selectByExampleAll(AgtEmpInfoExample example);
	
	/**
	 * 查询信息
	 * @param selectMap
	 * @return
	 */
	List<AgtEmpInfoList> selectEmpInfoList(AgtEmpInfoList empInfo);
	
	List<AgtEmpInfoList> selectTransferList(AgtEmpInfoList empInfo);
	
	int isInCom(String uid);
	
	List<AgtEmpInfo> selectEmpIdList(AgtEmpInfo empInfo);
	
	List<AgtEmpInfoList> selectEmpINfoByOffice(AgtEmpInfoList empInfo);
	
	/**
	 * 是否有正在处理中的合同
	 * @param eId 经纪人ID
	 * @return
	 */
	int hasDoingCon(String eId);
	
	int actHouse(String eid);
	
	int conHouse(String eid);
	
	int wHouse(String eid);
	
	int unAuHouse(String eid);
	
	int cus(String eid);
	
	int contract(AgtEmpInfo aei);
	
	int eContract(AgtEmpInfo aei);
	
	int selectCountByOffice(String compId);
	
	String selectCompIdByOfficeId(String officeId);
	
	int isCompLockedByUserId(String userId);
	
	int isCompLockedBySysUser(String userId);
	
	AgtEmpInfo getPhone(String id);
	
	int isPhoneBinded(String phone);
	
	void updatePhone(AgtEmpInfo aei);
	
	String getEid(String userId);
	
	AgtEmpInfo findAllById (String eid);
}