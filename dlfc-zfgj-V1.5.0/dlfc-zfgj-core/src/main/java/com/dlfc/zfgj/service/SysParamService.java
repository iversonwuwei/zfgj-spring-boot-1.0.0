package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysParamExample.Criteria;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysParamExample;
import com.dlfc.zfgj.mapper.SysParamMapper;

/**
 * @name: SysParamService
 * @description: 
 * 
 * @version 1.0
 * @author yuanjiwei
 *
 */
@Service("sysParamService")
public class SysParamService {
	
	@Autowired
	private SysParamMapper paramMapper;

	/**
	 * 通过scope查询参数
	 * 通过类型获取名称-sys_param
	 */
	public List<SysParam> getParamByScope(String scope) throws Exception {

		SysParamExample sysParamExample = new SysParamExample();
		Criteria criteria = sysParamExample.createCriteria();
		criteria.andScopeEqualTo(scope);
		return paramMapper.selectByExample(sysParamExample);
	}
	
	
	
	
	
	

	public void save(SysParam sysParam) {
		if (sysParam.getIsNewRecord()){
			sysParam.preInsert();
			paramMapper.insert(sysParam);
		}else{
			sysParam.preUpdate();
			paramMapper.updateByPrimaryKeySelective(sysParam);
		}
	}

	public void delete(SysParam sysParam) {
		paramMapper.deleteByPrimaryKey(sysParam.getId());
	}
	
	/**
	 * 判断开关是否打开
	 * 
	 * @param sysParam 存放scope和skey参数的实体
	 * @return on返回true，off返回false
	 */
	public boolean isOn(SysParam sysParam) {
		String isOn = paramMapper.findSvalue(sysParam);
		if ("off".equalsIgnoreCase(isOn)) {
			return false;
		} else {
			return true;
		}
	}
}
