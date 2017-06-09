package com.dlfc.zfgj.entity;

import com.dlfc.zfgj.common.data.DataEntity;

import java.io.Serializable;

public class SysInfoAtt extends DataEntity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.PINDEX
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.FILE_TYPE
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private Integer fileType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.FILE_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.FILE_PATH
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private String filePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.LID
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private String lid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_info_att.FILE_REAL_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private String fileRealName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_info_att
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.PINDEX
     *
     * @return the value of sys_info_att.PINDEX
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.PINDEX
     *
     * @param pindex the value for sys_info_att.PINDEX
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.FILE_TYPE
     *
     * @return the value of sys_info_att.FILE_TYPE
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.FILE_TYPE
     *
     * @param fileType the value for sys_info_att.FILE_TYPE
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.FILE_NAME
     *
     * @return the value of sys_info_att.FILE_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.FILE_NAME
     *
     * @param fileName the value for sys_info_att.FILE_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.FILE_PATH
     *
     * @return the value of sys_info_att.FILE_PATH
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.FILE_PATH
     *
     * @param filePath the value for sys_info_att.FILE_PATH
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.LID
     *
     * @return the value of sys_info_att.LID
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public String getLid() {
        return lid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.LID
     *
     * @param lid the value for sys_info_att.LID
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_info_att.FILE_REAL_NAME
     *
     * @return the value of sys_info_att.FILE_REAL_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public String getFileRealName() {
        return fileRealName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_info_att.FILE_REAL_NAME
     *
     * @param fileRealName the value for sys_info_att.FILE_REAL_NAME
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
     */
    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName == null ? null : fileRealName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_info_att
     *
     * @mbggenerated Tue Dec 15 11:33:31 CST 2015
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
        sb.append(", lid=").append(lid);
        sb.append(", fileRealName=").append(fileRealName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}