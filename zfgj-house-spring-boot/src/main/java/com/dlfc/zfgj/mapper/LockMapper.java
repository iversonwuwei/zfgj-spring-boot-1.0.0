package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;

import java.util.Map;

@MyBatisDao
public interface LockMapper {
	
	/**
	 * 取得认证下一条数据
	 * @param param
	 * @return 下一条数据主键
	 */
	String getId(Map<String, Object> param);
	
	/**
	 * 根据主键更新锁定信息
	 * @param param
	 * @return
	 */
	int updateByPrimaryKey(Map<String, Object> param);
	
//	/**
//	 * 查看锁定状态
//	 * @param param
//	 * @return
//	 */
//	Map<String, Object> getLockInfo(Map<String, Object> param);
//
//	/**
//	 * 取得待审核的所有条数
//	 * @return
//	 */
//	List<Map<String, String>> getAllCount();
	
}