
/**
* @name: SysInfoAttService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月5日 
* @author: QiuFei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月5日       QiuFei        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.entity.SysInfoAttExample.Criteria;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;

/**
 * @name: SysInfoAttService 图片
 * @description: 图片操作
 * @version 1.0
 * @author QiuFei
 *
 */
@Service("sysInfoAttService")
public class SysInfoAttService {
	/** 附件mapper */
	@Autowired
	private SysInfoAttMapper sysInfoAttMapper;

	/**
	 * 插入图片
	 * 
	 * @param sia 图片对象
	 * @return 插入成功失败状态
	 */
	public int insert(SysInfoAtt sia) {
		sia.setIsNewRecord(true);
		sia.preInsert();
		return sysInfoAttMapper.insertSelective(sia);
	}

	/**
	 * 根据id更新数据
	 * 
	 * @param sia 图片对象
	 * @return 更新成功失败状态
	 */
	public int updateById(SysInfoAtt sia) {
		return sysInfoAttMapper.updateByPrimaryKeySelective(sia);
	}

	/**
	 * 查找附件记录
	 * 
	 * @param lid 图片外键ID
	 * @param fileTypes 图片类型
	 * @return 图片列表
	 */
	public List<SysInfoAtt> findList(String lid, Integer... fileTypes) {
		SysInfoAttExample example = new SysInfoAttExample();
		if (fileTypes != null) {
			for (Integer fileType : fileTypes) {
				Criteria cri = example.or();
				cri.andLidEqualTo(lid);
				cri.andDeleteFlgEqualTo((short) 0);
				cri.andFileTypeEqualTo(fileType);
			}
		} else {
			Criteria cri = example.createCriteria();
			cri.andLidEqualTo(lid);
			cri.andDeleteFlgEqualTo((short) 0);
		}
		example.setOrderByClause("PINDEX");
		return sysInfoAttMapper.selectByExample(example);
	}

	/**
	 * 插入一条附件记录
	 * 
	 * @param record 图片对象
	 * @return 插入成功失败状态
	 */
	public int insertPdf(SysInfoAtt record) {
		record.preInsert();
		return sysInfoAttMapper.insertSelective(record);
	}

	/**
	 * 根据lid，附件类型删除附件,剔除values
	 * 
	 * @param lid 图片外键ID
	 * @param fileType 图片类型
	 * @param values 图片地址
	 */
	public void delete(String lid, Integer fileType, List<String> values) {
		SysInfoAtt att = new SysInfoAtt();
		att.preUpdate();
		att.setDeleteFlg((short) 1);

		SysInfoAttExample example = new SysInfoAttExample();
		Criteria criteria = example.createCriteria();
		criteria.andLidEqualTo(lid);
		criteria.andDeleteFlgEqualTo((short) 0);
		criteria.andFileTypeEqualTo(fileType);

		if (values != null && values.size() > 0) {
			criteria.andFilePathNotIn(values);
		}

		sysInfoAttMapper.updateByExampleSelective(att, example);
	}

	/**
	 * 根据lid查询
	 * 
	 * @param lid 图片外键ID
	 * @param fileType 图片类型
	 * @return 图片对象
	 */
	public SysInfoAtt selectByLidAndType(String lid, int type) {
		SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
		Criteria crit = sysInfoAttExample.createCriteria();
		crit.andLidEqualTo(lid);
		crit.andFileTypeEqualTo(type);
		crit.andDeleteFlgEqualTo((short) 0);
		List<SysInfoAtt> list = sysInfoAttMapper.selectByExample(sysInfoAttExample);
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 更新头像
	 * 
	 * @param sysInfoAtt 图片对象
	 */
	public void updateAvarar(SysInfoAtt sysInfoAtt) {
		User userInfo = UserUtils.getUser();
		SysInfoAtt siaOld = new SysInfoAtt();
		siaOld = this.selectByLidAndType(userInfo.getId(), InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
		if (siaOld == null) {// 没有修改过头像
			sysInfoAtt.preInsert();
			sysInfoAtt.setId(sysInfoAtt.getUUID());
			sysInfoAtt.setLid(userInfo.getId());
			sysInfoAtt.setDeleteFlg((short) 0);
			sysInfoAttMapper.insertSelective(sysInfoAtt);
		} else {
			siaOld.preUpdate();
			siaOld.setFilePath(sysInfoAtt.getFilePath());
			sysInfoAttMapper.updateByPrimaryKeySelective(siaOld);
		}
	}

	/**
	 * 添加或修改经纪人二维码
	 * @param sysInfoAtt 附件对象
	 */
	public void updateErWei(SysInfoAtt sysInfoAtt){
		
		SysInfoAtt siaOld = new SysInfoAtt();
		//查询是否已有二维码
		siaOld = this.selectByLidAndType(sysInfoAtt.getLid(),InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue());
		//没有修改过二维码
		if(siaOld == null){
			sysInfoAtt.preInsert();
			sysInfoAtt.setId(sysInfoAtt.getUUID());
			sysInfoAtt.setDeleteFlg((short)0);
			sysInfoAttMapper.insertSelective(sysInfoAtt);
		}else{
			siaOld.preUpdate();
			siaOld.setFilePath(sysInfoAtt.getFilePath());
			sysInfoAttMapper.updateByPrimaryKeySelective(siaOld);
		}
	}
	
	/**
	 * 根据lid查询list根据pindex排序
	 * 
	 * @param lid 图片外键ID
	 * @param fileType 图片类型
	 * @return 图片列表
	 */
	public List<SysInfoAtt> selectListByLidTypeDesc(String lid, int type) {
		SysInfoAttExample sysInfoAttExample = new SysInfoAttExample();
		Criteria crit = sysInfoAttExample.createCriteria();
		crit.andLidEqualTo(lid);
		crit.andFileTypeEqualTo(type);
		crit.andDeleteFlgEqualTo((short) 0);
		sysInfoAttExample.setOrderByClause("PINDEX desc");
		List<SysInfoAtt> list = sysInfoAttMapper.selectByExample(sysInfoAttExample);
		return list;
	}

	/**
	 * 根据ID查询图片对象
	 * 
	 * @param id 图片ID
	 * @return SysInfoAtt 图片对象
	 */
	public SysInfoAtt selectById(String id) {
		return sysInfoAttMapper.selectByPrimaryKey(id);
	}
}
