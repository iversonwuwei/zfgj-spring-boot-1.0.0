/**
 * @name: HouLeaseInfoService.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 出租信息增删改查操作
 *
 * @version: 1.0
 * @date : 2015年12月2日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年12月2日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.OrderUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.HouLeaseInfoExample;
import com.dlfc.zfgj.entity.HouLeaseInfoExample.Criteria;
import com.dlfc.zfgj.entity.HouLeaseRoomInfo;
import com.dlfc.zfgj.entity.HouLeaseRoomInfoList;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.mapper.HouLeaseInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.AuditStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseAuditStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseReleaseStatusEnum;
import com.housecenter.dlfc.zfgj.common.enums.HouseSourceTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.enums.RentalModeEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;
import com.housecenter.dlfc.zfgj.common.utils.Const;
import com.housecenter.dlfc.zfgj.common.utils.ImageUtils;

/**
 * @name: HouLeaseInfoService
 * @description: 出租信息增删改查操作
 * @version 1.0
 * @author fancy
 *
 */
@Service("houLeaseInfoService")
public class HouLeaseInfoService extends BaseService{
	/** 出租信息持久层对象 */
	@Autowired
	private HouLeaseInfoMapper houLeaseInfoMapper;
	/** 图片信息Service */
	@Autowired
	private SysInfoAttService sysInfoAttService;
	/** 出租信息房间Service */
	@Autowired
	private HouLeaseRoomInfoService houLeaseRoomInfoService;
	/** 房源信息Service */
	@Autowired
	private HouHouseInfoService houHouseInfoService;

	/** 房源图片存放目录 */
	private static final String LEA_PIC_DIR = "lea";

	/**
	 * 查询出租信息对象列表
	 * @param hid 房源ID
	 * @return 出租信息对象列表
	 */
	public List<HouLeaseInfo> selectLeaseInInfo(String hid) {
		HouLeaseInfoExample example = new HouLeaseInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(hid);
		criteria.andDeleteFlgEqualTo((short) 0);
		return houLeaseInfoMapper.selectByExample(example);
	}

	/**
	 * 插入出租信息
	 * @param leaseInfo 出租信息对象
	 */
	public void insert(HouLeaseInfo leaseInfo) {
		leaseInfo.setIsNewRecord(true);
		leaseInfo.preInsert();
		houLeaseInfoMapper.insertSelective(leaseInfo);
	}

	/**
	 * 更新出租信息
	 * @param leaseInfo 出租信息对象
	 */
	public void update(HouLeaseInfo leaseInfo) {
		leaseInfo.preUpdate();
		HouLeaseInfoExample example = new HouLeaseInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(leaseInfo.getHid());
		criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		houLeaseInfoMapper.updateByExampleSelective(leaseInfo, example);
	}

	/**
	 * 添加出租信息-整租
	 * @param leaseInfo 出租信息对象
	 * @param mainimgId_path 图片地址首图
	 * @param layoutimgId_path 图片地址
	 * @param mulImage_path 图片地址数组
	 * @param roomInfos 房间对象列表
	 * @param list 房间图片列表
	 * @throws ApplicationException 添加出租信息失败
	 */
	public void saveAll(HouLeaseInfo leaseInfo, String mainimgId_path, String layoutimgId_path, String[] mulImage_path,
			HouLeaseRoomInfoList roomInfos, List<String[]> list) throws ApplicationException {
		String id = leaseInfo.getUUID();
		leaseInfo.setId(id);
		// 房源图片
		if (mulImage_path != null && mulImage_path.length > 0) {
			String[] mulImage_path_arr = mulImage_path;
			//循环获取图片地址
			for (int i = 0; i < mulImage_path_arr.length; i++) {
				String mulImage_path_temp = mulImage_path_arr[i];
				//房屋图片是空字符串
				if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
				}
				//图片临时目录
				String mulImage_path_real = "";
				try {
					mulImage_path_real = ImageUtils.generateLeaseImage(
							PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp, 1);
				} catch (IOException e) {
					logger.error("HouLeaseInfoService saveAll", e);
					throw new ApplicationException("添加出租信息失败！");
				}
				String path = mulImage_path_temp.substring(0, mulImage_path_temp.indexOf(File.separator))
						+ File.separator + mulImage_path_real;

				path = path.replaceAll("\\" + File.separator, "/");
				SysInfoAtt newSysInfoAtt = new SysInfoAtt();
				String layoutimgId = newSysInfoAtt.getUUID();
				newSysInfoAtt.setId(layoutimgId);
				if (mulImage_path_temp.equals(mainimgId_path)) {
					newSysInfoAtt.setSort(12);
				}
				newSysInfoAtt.setFileType(InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue());// 附件类型
				newSysInfoAtt.setFilePath(LEA_PIC_DIR + "/" + path);
				newSysInfoAtt.setLid(id);
				sysInfoAttService.insert(newSysInfoAtt);
			}
			
		}
		//如果没有选择租金分期
		if (leaseInfo.getStages() == null) {
			leaseInfo.setStages("00");
		}
		//设置保存信息字段
		leaseInfo.setNo(leaseInfo.getNoBuildingNo() + "," + leaseInfo.getNoUnit() + "," + leaseInfo.getNoRoom());
		leaseInfo.setFloor(leaseInfo.getFloorNo() + "," + leaseInfo.getFloorNum());
		leaseInfo.setApartmentLayout(
				leaseInfo.getAparmentRoom() + "," + leaseInfo.getAparmentHall() + "," + leaseInfo.getAparmentToilet());
		leaseInfo.setDistrict(leaseInfo.getDistrictArea() + "," + leaseInfo.getDistrictDist());
		// leaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
		leaseInfo.setLno(OrderUtils.getBusinessNO(Const.BUSINESS_TYPE_LEASE));
		leaseInfo.setSourceType(HouseSourceTypeEnum.AGENT_ENUM.getValue());
		leaseInfo.setRentType(leaseInfo.getRentDeposit() + "," + leaseInfo.getRentPayment());

		// 房源审核通过，出租信息上架，未通过，下架
		HouHouseInfo houHouseInfo = houHouseInfoService.findById(leaseInfo.getHid());
		Integer auditStatus = houHouseInfo.getAuditStatus();
		// 审核状态非空，并且等2（审核通过）
		if (auditStatus != null && auditStatus.intValue() == HouseAuditStatusEnum.PASS_ENUM.getValue()) {
			leaseInfo.setReleaseStatus(HouseReleaseStatusEnum.YES_ENUM.getValue());
		} else {
			leaseInfo.setReleaseStatus(HouseReleaseStatusEnum.NO_ENUM.getValue());
		}
		leaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());
		// Integer releaseStatus = leaseInfo.getReleaseStatus();
		// if(releaseStatus.intValue() ==
		// HouseReleaseStatusEnum.YES_ENUM.getValue()){//发布
		// leaseInfo.setReleaseTime(DateUtils.getDBDateTime());//发布时间
		// }

		Integer leaseMode = leaseInfo.getLeaseMode();
		// 分租
		if (leaseMode.intValue() == RentalModeEnum.PART_RENT_ENUM.getValue()) {
			Double rent = 0.0;
			// 添加房间信息
			List<HouLeaseRoomInfo> houLeaseRoomInfos = roomInfos.getRoomInfos();
			for (int i = 0; i < houLeaseRoomInfos.size(); i++) {
				HouLeaseRoomInfo info = houLeaseRoomInfos.get(i);
				String rent_tmp = String.valueOf(info.getRent());
				Double rentTmp = Double.valueOf(rent_tmp);
				// 租金
				if (rent == 0.0) {
					rent = rentTmp;
				} else {
					if (rent > rentTmp) {
						rent = rentTmp;
					}
				}
				String roomId = info.getUUID();
				info.setId(roomId);
				info.setLeaseid(id);
				info.setRentType(info.getRentDeposit() + "," + info.getRentPayment());
				// 可租
				info.setStatus(YesNoEnum.NO_ENUM.getValue());
				houLeaseRoomInfoService.insertRoom(info);

				String[] room_path = list.get(i);
				// 房间图片
				if (room_path != null && room_path.length > 0) {
					String[] mulImage_path_arr = room_path;

					for (int j = 0; j < mulImage_path_arr.length; j++) {
						String mulImage_path_temp = mulImage_path_arr[j];
						//房屋图片是空字符串
						if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
							throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
						}
						String mulImage_path_real = "";
						try {
							mulImage_path_real = ImageUtils.generateLeaseImage(
									PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp, 1);
						} catch (IOException e) {
							logger.error("HouLeaseInfoService saveAll", e);
							throw new ApplicationException("添加出租信息失败！");
						}
						String path = mulImage_path_temp.substring(0, mulImage_path_temp.indexOf(File.separator))
								+ File.separator + mulImage_path_real;

						path = path.replaceAll("\\" + File.separator, "/");
						SysInfoAtt newSysInfoAtt = new SysInfoAtt();
						String layoutimgId = newSysInfoAtt.getUUID();
						newSysInfoAtt.setId(layoutimgId);
						newSysInfoAtt.setFileType(InfoAttFileTypeEnum.ROOM_PIC_ENUM.getValue());// 附件类型
						newSysInfoAtt.setFilePath(LEA_PIC_DIR + "/" + path);
						newSysInfoAtt.setLid(roomId);
						sysInfoAttService.insert(newSysInfoAtt);
					}
				}
			}
			leaseInfo.setRent(BigDecimal.valueOf(rent));
		}

		String districtName = getDistrictName(leaseInfo);
		leaseInfo.setDistrictName(districtName);
		// 刷新时间
		leaseInfo.setFreshTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		// 发布时间
		leaseInfo.setReleaseTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		this.insert(leaseInfo);
	}

	/**
	 * 编辑出租信息
	 * @param leaseInfo 出租信息对象
	 * @param mainimgId_path 图片地址首图
	 * @param layoutimgId_path 图片地址
	 * @param mulImage_path 图片地址数组
	 * @param roomInfos 房间对象列表
	 * @param list 房间图片列表
	 * @throws ApplicationException 保存出租信息失败
	 */
	public void updateLease(HouLeaseInfo leaseInfo, String mainimgId_path, String layoutimgId_path,
			String[] mulImage_path, HouLeaseRoomInfoList roomInfos, List<String[]> room_list) throws ApplicationException {
		String id = leaseInfo.getId();

		// 删除原房源图片
		sysInfoAttService.delete(id, 11, new ArrayList<String>());
		// 房源图片
		if (mulImage_path != null && mulImage_path.length > 0) {
			String[] mulImage_path_arr = mulImage_path;
			// 循环保存图片
			for (int i = 0; i < mulImage_path_arr.length; i++) {
				String mulImage_path_temp = mulImage_path_arr[i];
				//房屋图片是空字符串
				if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
					throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
				}
				String path = "";
				if (!mulImage_path_temp.contains(".png") && !mulImage_path_temp.contains(".jpg")
						&& !mulImage_path_temp.contains(".jpeg") && !mulImage_path_temp.contains(".bmp")) {
					path = mulImage_path_temp;
				} else {
					String mulImage_path_real = "";
					try {
						mulImage_path_real = ImageUtils.generateLeaseImage(
								PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp, 1);
					} catch (IOException e) {
						logger.error("HouLeaseInfoService updateLease", e);
						throw new ApplicationException("保存出租信息失败");
					}
					path = mulImage_path_temp.substring(0, mulImage_path_temp.indexOf(File.separator)) + File.separator
							+ mulImage_path_real;
					path = path.replaceAll("\\" + File.separator, "/");

					path = LEA_PIC_DIR + "/" + path;
				}

				SysInfoAtt newSysInfoAtt = new SysInfoAtt();
				String layoutimgId = newSysInfoAtt.getUUID();
				newSysInfoAtt.setId(layoutimgId);
				if (mulImage_path_temp.equals(mainimgId_path)) {
					newSysInfoAtt.setSort(12);
				}
				newSysInfoAtt.setFileType(InfoAttFileTypeEnum.HOUSE_PIC_ENUM.getValue());// 附件类型
				newSysInfoAtt.setFilePath(path);
				newSysInfoAtt.setLid(id);

				sysInfoAttService.insert(newSysInfoAtt);
			}
		}

		leaseInfo.setNo(leaseInfo.getNoBuildingNo() + "," + leaseInfo.getNoUnit() + "," + leaseInfo.getNoRoom());
		leaseInfo.setFloor(leaseInfo.getFloorNo() + "," + leaseInfo.getFloorNum());
		leaseInfo.setApartmentLayout(
				leaseInfo.getAparmentRoom() + "," + leaseInfo.getAparmentHall() + "," + leaseInfo.getAparmentToilet());
		leaseInfo.setDistrict(leaseInfo.getDistrictArea() + "," + leaseInfo.getDistrictDist());
		leaseInfo.setRentType(leaseInfo.getRentDeposit() + "," + leaseInfo.getRentPayment());
		leaseInfo.setId(null);
		leaseInfo.setAuditStatus(AuditStatusEnum.UNAUDITED_ENUM.getValue());// 重新编辑审核不通过
		// Integer releaseStatus = leaseInfo.getReleaseStatus();
		// if(releaseStatus.intValue() ==
		// HouseReleaseStatusEnum.YES_ENUM.getValue()){//发布
		// leaseInfo.setReleaseTime(DateUtils.getDBDateTime());//发布时间
		// }

		Integer leaseMode = leaseInfo.getLeaseMode();

		// 删除房间
		houLeaseRoomInfoService.deleteRoom(id);
		// 删除原房间图片
		sysInfoAttService.delete(id, 12, new ArrayList<String>());

		if (leaseMode.intValue() == RentalModeEnum.PART_RENT_ENUM.getValue()) {// 分租
			// 房间租金
			Double rent = 0.0;
			// 添加房间信息
			List<HouLeaseRoomInfo> houLeaseRoomInfos = roomInfos.getRoomInfos();

			for (int i = 0; i < houLeaseRoomInfos.size(); i++) {
				HouLeaseRoomInfo info = houLeaseRoomInfos.get(i);
				String rent_tmp = String.valueOf(info.getRent());
				Double rentTmp = Double.valueOf(rent_tmp);
				if (rent == 0.0) {
					rent = rentTmp;
				} else {
					if (rent > rentTmp) {
						rent = rentTmp;
					}
				}
				String roomId = info.getUUID();
				info.setId(roomId);
				info.setLeaseid(id);
				info.setRentType(info.getRentDeposit() + "," + info.getRentPayment());
				info.setStatus(YesNoEnum.NO_ENUM.getValue());
				houLeaseRoomInfoService.insertRoom(info);

				String[] room_path = room_list.get(i);
				// 房间图片
				if (room_path != null && room_path.length > 0) {
					String[] mulImage_path_arr = room_path;

					for (int j = 0; j < mulImage_path_arr.length; j++) {
						String mulImage_path_temp = mulImage_path_arr[j];
						//房屋图片是空字符串
						if(com.dlfc.admin.common.utils.StringUtils.isBlank(mulImage_path_temp)){
							throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0008"));
						}
						String path = "";
						if (!mulImage_path_temp.contains(".png") && !mulImage_path_temp.contains(".jpg")
								&& !mulImage_path_temp.contains(".jpeg") && !mulImage_path_temp.contains(".bmp")) {
							path = mulImage_path_temp;
						} else {
							String mulImage_path_real = "";
							try {
								mulImage_path_real = ImageUtils.generateLeaseImage(
										PropertyUtils.getSysVal("upload.file.temporary.directory") + mulImage_path_temp, 1);
							} catch (IOException e) {
								logger.error("HouLeaseInfoService updateLease", e);
								throw new ApplicationException("保存出租信息失败");
							}
							path = mulImage_path_temp.substring(0, mulImage_path_temp.indexOf(File.separator))
									+ File.separator + mulImage_path_real;

							path = path.replaceAll("\\" + File.separator, "/");

							path = LEA_PIC_DIR + "/" + path;
						}

						SysInfoAtt newSysInfoAtt = new SysInfoAtt();
						String layoutimgId = newSysInfoAtt.getUUID();
						newSysInfoAtt.setId(layoutimgId);
						newSysInfoAtt.setFileType(InfoAttFileTypeEnum.ROOM_PIC_ENUM.getValue());// 附件类型
						newSysInfoAtt.setFilePath(path);
						newSysInfoAtt.setLid(roomId);

						sysInfoAttService.insert(newSysInfoAtt);
					}
				}
			}
			leaseInfo.setRent(BigDecimal.valueOf(rent));
		}

		// 刷新时间
		leaseInfo.setFreshTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		String districtName = getDistrictName(leaseInfo);
		leaseInfo.setDistrictName(districtName);
		this.update(leaseInfo);
	}

	/**
	 * 改变出租信息状态
	 * @param id 出租信息ID
	 * @param status 出租信息状态
	 */
	public void changeLeaseStatus(String id, String status) {
		HouLeaseInfo record = new HouLeaseInfo();
		record.setId(id);
		//上架刷新时间
		if("1".equals(status)){
		record.setFreshTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		}
		record.setReleaseStatus(Integer.parseInt(status));
		record.preUpdate();
		houLeaseInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 刷新出租信息
	 * @param id 出租信息ID
	 */
	public void refreshLease(String id) {
		HouLeaseInfo record = new HouLeaseInfo();
		record.setId(id);
		record.setFreshTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		record.preUpdate();
		houLeaseInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据区域，商圈ID获取区域，商圈名称
	 * @param record 出租信息对象
	 * @return 商圈名称
	 */
	public String getDistrictName(HouLeaseInfo record) {
		return houLeaseInfoMapper.getDistrictName(record);
	}
}
