/**
* @name: DepositReturnService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月18日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月18日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConDepositDistAgr;
import com.dlfc.zfgj.entity.ConDepositDistLog;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysInfoAttExample;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.mapper.ConDepositDistAgrMapper;
import com.dlfc.zfgj.mapper.ConDepositDistLogMapper;
import com.dlfc.zfgj.mapper.SysInfoAttMapper;
import com.housecenter.dlfc.zfgj.common.enums.DepositDistributeEnum;
import com.housecenter.dlfc.zfgj.common.enums.DepositDistributeStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: DepositReturnService
 * @description: 押金返还
 * @version 1.0
 * @author hanjiaqi
 *
 */
@Service("depositReturnService")
public class DepositReturnService {
	/** 房客同意*/
	private final static int DEPOSIT_DISTRIBUTE_AGREE = 3;
	/** 房客拒绝*/
	private final static int DEPOSIT_DISTRIBUTE_REFUSE = 6;

	/** 合同信息mapper*/
	@Autowired
	private ConContractMapper conMapper;
	/** 押金分配协议mapper*/
	@Autowired
	private ConDepositDistAgrMapper conDepositDistAgrMapper;
	/** 押金分配协议记录mapper*/
	@Autowired
	private ConDepositDistLogMapper conDepositDistLogMapper;
	/** 附件信息mapper*/
	@Autowired
	private SysInfoAttMapper infoAttDao;

	/**
	 * 通过ID查找合同
	 * @param id 合同ID
	 * @return 合同对象
	 */
	public ConContract findByCid(String id) {
		return conMapper.findById(id);
	}

	/**
	 * 插入押金分配记录
	 * @param conDepositDistAgr 押金分配对象
	 * @return 插入成功失败状态
	 */
	public int insertSelective(ConDepositDistAgr conDepositDistAgr) {
		conDepositDistAgr.preInsert();
		int flag = conDepositDistAgrMapper.insertSelective(conDepositDistAgr);
		// 插入成功写入LOG
		if (flag > 0) {
			saveConDepositDistLog(conDepositDistAgr, DepositDistributeEnum.DEPOSIT_DISTRIBUTE_CREATE_ENUM.getValue(), 0,
					conDepositDistAgr.getStatus());
		}
		return flag;
	}

	/**
	 * 更新押金分配
	 * @param conDepositDistAgr
	 * @param nowOperate 押金分配操作
	 * @param beforeStatus 之前的状态
	 * @return 更新成功失败状态
	 */
	public int updateDeposit(ConDepositDistAgr conDepositDistAgr, int nowOperate, int beforeStatus) {
		conDepositDistAgr.preUpdate();
		int flag = conDepositDistAgrMapper.updateByPrimaryKeySelective(conDepositDistAgr);
		// 更新成功写入LOG
		if (flag > 0) {
			saveConDepositDistLog(conDepositDistAgr, nowOperate, beforeStatus, conDepositDistAgr.getStatus());
		}
		return flag;
	}

	/**
	 * 押金分配记录
	 * @param conDepositDistAgr 押金分配对象
	 * @param optEvent 押金分配操作事件
	 * @param StatusBefore 协议之前状态
	 * @param StatusCurr 协议之后状态
	 * @return 保存成功失败状态
	 */
	public int saveConDepositDistLog(ConDepositDistAgr conDepositDistAgr, int optEvent, int StatusBefore,
			int StatusCurr) {
		ConDepositDistLog conDepositDistLog = new ConDepositDistLog();
		conDepositDistLog.preInsert();
		conDepositDistLog.setDepositDistNo(conDepositDistAgr.getNo());
		conDepositDistLog.setDepositDistId(conDepositDistAgr.getId());
		conDepositDistLog.setDescription(conDepositDistAgr.getDescription());
		conDepositDistLog.setOperator(UserUtils.getUser().getId());
		conDepositDistLog.setOptEvent(optEvent);
		switch (StatusCurr) {
		case 0:
			conDepositDistLog.setDcrp("创建押金分配合同");
			break;
		case 1:
			conDepositDistLog.setDcrp("提交押金分配申请");
			break;
		case 2:
			conDepositDistLog.setDcrp("拒绝押金分配");
			break;
		case 3:
			conDepositDistLog.setDcrp("同意押金返还");
			break;
		case 4:
			conDepositDistLog.setDcrp("押金返还已完成");
			break;
		case 5:
			conDepositDistLog.setDcrp("押金分配申请审核中");
			break;
		case 6:
			conDepositDistLog.setDcrp("押金分配申请审核不通过");
			break;
		}
		conDepositDistLog.setOptTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		conDepositDistLog.setDepositDistStatusBefore((short) StatusBefore);
		conDepositDistLog.setDepositDistStatusCurr((short) StatusCurr);
		conDepositDistLog.setChargeReason(conDepositDistAgr.getChargeReason());
		conDepositDistLog.setDeductAmt(conDepositDistAgr.getDeductAmt());
		return conDepositDistLogMapper.insertSelective(conDepositDistLog);
	}

	/**
	 * 保存合同图片
	 * @param con 合同对象
	 * @throws ApplicationException 上传文件失败
	 */
	public void saveConAtt(ConContract con) throws ApplicationException {
		// 押金分配协议id
		String id = con.getConDepositDistAgr().getId();

		// 删除已有的押金分配协议附件
		SysInfoAttExample example = new SysInfoAttExample();
		com.dlfc.zfgj.entity.SysInfoAttExample.Criteria cri = example.createCriteria();
		cri.andLidEqualTo(id);
		cri.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		cri.andFileTypeEqualTo(InfoAttFileTypeEnum.DEPO_DIST_AGR_ENUM.getValue());
		SysInfoAtt infoAtt = new SysInfoAtt();
		infoAtt.setVersion(1);
		infoAtt.preUpdate();
		infoAtt.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		infoAttDao.updateByExampleSelective(infoAtt, example);
		
		//添加押金分配协议图片
		SysInfoAtt oneSide = con.getInfoAttList().get(0);
		oneSide.setLid(id);
		oneSide.setFileType(InfoAttFileTypeEnum.DEPO_DIST_AGR_ENUM.getValue());
		oneSide.preInsert();
		infoAttDao.insertSelective(oneSide);

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
	 * 查寻审核数据
	 * @param id
	 * @return 押金分配对象
	 */
	public ConDepositDistAgr selectOneAudit(String id) {
		return conDepositDistAgrMapper.selectByIdAndStatus(id);
	}

	/**
	 * 更新特殊审核flag
	 * @param conDepositDistAgr 押金分配对象
	 */
	public void updateSpeFlag(ConDepositDistAgr conDepositDistAgr) {
		conDepositDistAgrMapper.updateByPrimaryKeySelective(conDepositDistAgr);
	}

	/**
	 * 更新审核状态
	 * @param conDepositDistAgr 押金分配对象
	 * @return 1:成功，0:失败
	 * @throws ApplicationException 数据已被更新
	 */
	public int updateDepositAudit(ConDepositDistAgr conDepositDistAgr) throws ApplicationException {
		// 检验版本
		checkVersion(conDepositDistAgr.getId(), conDepositDistAgr.getVersion());
		int optEvent = 0;
		int beforeStatus = conDepositDistAgr.getStatus();
		if (conDepositDistAgr.getRejectReason() == null || conDepositDistAgr.getRejectReason().length() == 0) {
			// 更新通过
			conDepositDistAgr.setStatus((short) DepositDistributeStatusEnum.DEPOSIT_DISTRIBUTE_STATUS_AGREE_ENUM.getValue());
			conDepositDistAgr.setVersion(conDepositDistAgr.getVersion() + 1);
		} else {
			// 更新拒绝
			conDepositDistAgr.setStatus((short) DepositDistributeStatusEnum.DEPOSIT_DISTRIBUTE_STATUS_VERIFYREFUSE_ENUM.getValue());
			conDepositDistAgr.setVersion(conDepositDistAgr.getVersion() + 1);
		}
		// 更新成功
		if (conDepositDistAgrMapper.updateByPrimaryKeySelective(conDepositDistAgr) != 0) {
			ConDepositDistAgr deposit = conDepositDistAgrMapper.selectByPrimaryKey(conDepositDistAgr.getId());

			if (deposit.getStatus() == DEPOSIT_DISTRIBUTE_AGREE) {
				optEvent = DepositDistributeEnum.DEPOSIT_DISTRIBUTE_AGREE_ENUM.getValue();
			} else if (deposit.getStatus() == DEPOSIT_DISTRIBUTE_REFUSE) {
				optEvent = DepositDistributeEnum.DEPOSIT_DISTRIBUTE_REFUSE_ENUM.getValue();
			}
			saveConDepositDistLog(deposit, optEvent, beforeStatus, deposit.getStatus());
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 检验版本
	 * @param id 押金分配对象ID
	 * @param oldVersion 版本号
	 * @throws ApplicationException 数据已被更新
	 */
	public void checkVersion(String id, Integer oldVersion) throws ApplicationException {
		ConDepositDistAgr conDepositDistAgr = null;
		if (oldVersion != null) {
			conDepositDistAgr = conDepositDistAgrMapper.selectByPrimaryKey(id);
			if (oldVersion.intValue() != conDepositDistAgr.getVersion()) {
				conDepositDistAgr = null;
			}
		}
		if (conDepositDistAgr == null) {
			// 数据已被更新，请刷新页面再进行操作！
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0004"));
		}
	}

	/**
	 * 移动文件
	 * @param srcFilePath 源文件地址
	 * @param destFilePath 目标地址
	 * @throws ApplicationException 上传文件失败
	 */
	private void copyFile(String srcFilePath, String destFilePath) throws ApplicationException {
		boolean result = FileUtils.copyFileCover(srcFilePath, destFilePath, true);
		if (!result) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
		}
	}
}
