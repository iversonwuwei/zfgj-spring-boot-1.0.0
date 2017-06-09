package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class HouHouseInfoAtt extends MyDataEntity<HouHouseInfoAtt> implements Serializable {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.PINDEX
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.FILE_TYPE
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private Integer fileType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.FILE_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private String fileName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.FILE_PATH
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private String filePath;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.HID
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private String hid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_info_att.FILE_REAL_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private String fileRealName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.PINDEX
	 * @return  the value of hou_house_info_att.PINDEX
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.PINDEX
	 * @param pindex  the value for hou_house_info_att.PINDEX
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.FILE_TYPE
	 * @return  the value of hou_house_info_att.FILE_TYPE
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public Integer getFileType() {
		return fileType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.FILE_TYPE
	 * @param fileType  the value for hou_house_info_att.FILE_TYPE
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.FILE_NAME
	 * @return  the value of hou_house_info_att.FILE_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.FILE_NAME
	 * @param fileName  the value for hou_house_info_att.FILE_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.FILE_PATH
	 * @return  the value of hou_house_info_att.FILE_PATH
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.FILE_PATH
	 * @param filePath  the value for hou_house_info_att.FILE_PATH
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath == null ? null : filePath.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.HID
	 * @return  the value of hou_house_info_att.HID
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public String getHid() {
		return hid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.HID
	 * @param hid  the value for hou_house_info_att.HID
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setHid(String hid) {
		this.hid = hid == null ? null : hid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_info_att.FILE_REAL_NAME
	 * @return  the value of hou_house_info_att.FILE_REAL_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public String getFileRealName() {
		return fileRealName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_info_att.FILE_REAL_NAME
	 * @param fileRealName  the value for hou_house_info_att.FILE_REAL_NAME
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName == null ? null : fileRealName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", fileType=").append(fileType);
		sb.append(", fileName=").append(fileName);
		sb.append(", filePath=").append(filePath);
		sb.append(", hid=").append(hid);
		sb.append(", fileRealName=").append(fileRealName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	private String newhid;

	public String getNewhid() {
		return newhid;
	}

	public void setNewhid(String newhid) {
		this.newhid = newhid;
	}
    
}