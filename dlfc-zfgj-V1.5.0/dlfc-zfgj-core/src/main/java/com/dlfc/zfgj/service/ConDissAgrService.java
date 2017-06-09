/**
* @name: ConDissAgrService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 解约协议
*
* @version: 1.0
* @date : 2016年8月11日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月11日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConDissAgr;
import com.dlfc.zfgj.entity.ConDissLog;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.mapper.ConDissAgrMapper;
import com.dlfc.zfgj.mapper.ConDissLogMapper;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.housecenter.dlfc.zfgj.common.enums.DissAgrLogEventEnum;
import com.housecenter.dlfc.zfgj.common.enums.DissAgrStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: ConDissAgrService
 * @description: 解约协议
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("ConDissAgrService")
public class ConDissAgrService {
	/** 解约协议mapper */
	@Autowired
	private ConDissAgrMapper dissAgrMapper;
	/** 图片信息mapper */
	@Autowired
	private SysInfoAttMapper infoAttDao;
	/** 解约协议记录mapper */
	@Autowired
	private ConDissLogMapper conDissLogMapper;

	/**
	 * 保存解约协议
	 * 
	 * @param dissAgr
	 *            解约协议对象
	 */
	public void save(ConDissAgr dissAgr) {
		dissAgr.preInsert();
		dissAgrMapper.insertSelective(dissAgr);
		this.saveDissAgrLog(dissAgr, "创建解约协议", DissAgrLogEventEnum.CREATE_ENUM.getValue());
	}

	/**
	 * 更新解约协议
	 * 
	 * @param conDissAgr
	 *            解约协议对象
	 * @param dcrp
	 *            描述
	 * @param optEvent
	 *            操作事件
	 */
	public void update(ConDissAgr conDissAgr, String dcrp, int optEvent) {
		dissAgrMapper.updateByPrimaryKeySelective(conDissAgr);
		this.saveDissAgrLog(conDissAgr, dcrp, optEvent);
	}

	/**
	 * 通过合同ID查询解约协议
	 * 
	 * @param cid
	 *            合同ID
	 * @return 解约协议日志对象列表
	 */
	public List<ConDissLog> findByCidDesc(String cid) {
		return conDissLogMapper.findByCidDesc(cid);

	}

	/**
	 * 根据id查出一条信息
	 * 
	 * @param id
	 *            解约协议ID
	 * @return 解约协议对象
	 */
	public ConDissAgr findById(String id) {
		return dissAgrMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入附件表
	 * 
	 * @param contract
	 *            合同对象
	 * @param dissagr
	 *            解约协议对象
	 * @throws ApplicationException 上传文件失败
	 */
	public void saveDisAtt(ConContract contract, ConDissAgr dissagr) throws ApplicationException {
		dissagr.setStatus((short) DissAgrStatusEnum.APPROVE_ENUM.getValue());
		dissagr.preUpdate();
		update(dissagr, "上传合同并通过", DissAgrLogEventEnum.ENTER_ENUM.getValue());
		String did = contract.getConDissAgr().getId();

		// 删除已有的合同附件
		SysInfoAttExample example = new SysInfoAttExample();
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri = example.createCriteria();
		cri.andLidEqualTo(did);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri.andFileTypeEqualTo(InfoAttFileTypeEnum.DISS_AGR_ENUM.getValue());
		SysInfoAtt infoAtt = new SysInfoAtt();
		infoAtt.setVersion(1);
		infoAtt.preUpdate();
		infoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		infoAttDao.updateByExampleSelective(infoAtt, example);

		// 添加解约合同图片
		SysInfoAtt oneSide = contract.getInfoAttList().get(0);
		oneSide.setLid(did);
		oneSide.setFileType(InfoAttFileTypeEnum.DISS_AGR_ENUM.getValue());
		oneSide.preInsert();
		infoAttDao.insertSelective(oneSide);
		// 将合同状态置为等待审核
		// con.setStatus((short)ConStatusEnum.APPROVING_ENUM.getValue());
		// modify(con, ConOptEventEnum.SUBMIT_ENUM.getValue(), "上传图片，提交合同等待审批");

		// 移动图片
		String tempDir = PropertyUtils.getSysVal("upload.file.temporary.directory");
		String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
		String oneSideFilePath = realDir + oneSide.getFilePath();
		File file = new File(oneSideFilePath);
		if (!file.exists()) {
			copyFile(tempDir + oneSide.getFilePath(), oneSideFilePath);
		}
		file = null;
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFilePath
	 *            源文件地址
	 * @param destFilePath
	 *            目标地址
	 * @throws ApplicationException
	 *             上传文件失败
	 */
	private void copyFile(String srcFilePath, String destFilePath) throws ApplicationException {
		boolean result = FileUtils.copyFileCover(srcFilePath, destFilePath, true);
		if (!result) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
		}
	}

	/**
	 * 查寻审核数据
	 * 
	 * @param id
	 *            解约协议ID
	 * @return 解约协议对象
	 */
	public ConDissAgr selectOneAudit(String id) {
		return dissAgrMapper.selectByIdAndStatus(id);
	}

	/**
	 * 更新特殊审核flag
	 * 
	 * @param conDissAgr
	 *            解约协议对象
	 */
	public void updateSpeFlag(ConDissAgr conDissAgr) {
		dissAgrMapper.updateByPrimaryKeySelective(conDissAgr);
	}

	/**
	 * 更新审核状态
	 * 
	 * @param conDissAgr
	 *            解约协议对象
	 * @return 更新成功失败状态
	 * @throws ApplicationException 数据已被更新
	 */
	public int updateConDissAgrAudit(ConDissAgr conDissAgr) throws ApplicationException {
		String dcrp = "";
		int optEvent = 0;
		// short beforeStatus = conDissAgr.getStatus();
		checkVersion(conDissAgr.getId(), conDissAgr.getVersion());

		if (conDissAgr.getRejectReason() == null || conDissAgr.getRejectReason().length() == 0) {
			// 更新通过
			conDissAgr.setStatus((short) DissAgrStatusEnum.ENTER_ENUM.getValue());
			conDissAgr.setVersion(conDissAgr.getVersion() + 1);
		} else {
			// 更新拒绝
			conDissAgr.setStatus((short) DissAgrStatusEnum.DISAGREE_ENUM.getValue());
			conDissAgr.setVersion(conDissAgr.getVersion() + 1);
		}

		if (dissAgrMapper.updateByPrimaryKeySelective(conDissAgr) != 0) {
			ConDissAgr newConDissAgr = dissAgrMapper.selectByPrimaryKey(conDissAgr.getId());

			if (conDissAgr.getStatus() == 3) {
				dcrp = "审核通过";
				optEvent = DissAgrLogEventEnum.ENTER_ENUM.getValue();
			} else if (conDissAgr.getStatus() == 6) {
				dcrp = "审核未通过";
				optEvent = DissAgrLogEventEnum.REJECT_ENUM.getValue();
			}
			saveDissAgrLog(newConDissAgr, dcrp, optEvent);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 检验版本
	 * 
	 * @param id
	 *            解约协议ID
	 * @param oldVersion
	 *            版本号
	 * @throws ApplicationException 数据已被更新
	 */
	public void checkVersion(String id, Integer oldVersion) throws ApplicationException {
		ConDissAgr conDissAgr = null;
		if (oldVersion != null) {
			conDissAgr = dissAgrMapper.selectByPrimaryKey(id);
			if (oldVersion.intValue() != conDissAgr.getVersion()) {
				conDissAgr = null;
			}
		}
		if (conDissAgr == null) {
			// 数据已被更新，请刷新页面再进行操作！
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
		}
		/* return person; */
	}

	/**
	 * 插入解约协议LOG表
	 * 
	 * @param conDissAgr
	 *            解约协议对象
	 * @param dcrp
	 *            描述
	 * @param optEvent
	 *            操作事件
	 * @return 保存成功失败状态
	 */
	public int saveDissAgrLog(ConDissAgr conDissAgr, String dcrp, int optEvent) {
		ConDissLog conDissLog = new ConDissLog();
		conDissLog.setNo(conDissAgr.getNo());
		conDissLog.setDissId(conDissAgr.getId());
		conDissLog.setCid(conDissAgr.getCid());
		/*
		 * conDissLog.setConDissStatusBefore(before);
		 * conDissLog.setConDissStatusCurr(conDissAgr.getStatus());
		 */
		// conDissLog.setCreateTime(DateUtils.getDBDateTime());
		conDissLog.setCreateUserType(conDissAgr.getCreateUserType());
		conDissLog.setDcrp(dcrp);
		conDissLog.setOperator(UserUtils.getUser().getId());
		conDissLog.setOptEvent(optEvent);
		conDissLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		conDissLog.setRejectReason(conDissAgr.getRejectReason());
		conDissLog.setDescription(conDissAgr.getDescription());
		conDissLog.setEffectiveTime(conDissAgr.getEffectiveTime());
		conDissLog.setPaymentAmt(conDissAgr.getPaymentAmt());
		conDissLog.setPaymentDepositAmt(conDissAgr.getPaymentDepositAmt());
		conDissLog.setPaymentFrom(conDissAgr.getPaymentFrom());
		conDissLog.setPaymentTo(conDissAgr.getPaymentTo());
		conDissLog.setReason(conDissAgr.getReason());
		conDissLog.setRejectType(conDissAgr.getRejectType());
		conDissLog.setRetrunRentAmt(conDissAgr.getRetrunRentAmt());
		conDissLog.setStatus((int) conDissAgr.getStatus());
		conDissLog.setVersion(conDissAgr.getVersion());
		conDissLog.preInsert();
		return conDissLogMapper.insertSelective(conDissLog);
	}
}
