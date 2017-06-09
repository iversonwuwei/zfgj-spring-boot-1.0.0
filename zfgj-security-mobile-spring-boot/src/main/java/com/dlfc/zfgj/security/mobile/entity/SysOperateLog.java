package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;

public class SysOperateLog extends MyDataEntity<SysOperateLog> implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.PINDEX
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private Integer pindex;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.USER_NAME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String userName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.USER_ID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String userId;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.OPERATE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String operate;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.DESCRIPTION
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String description;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.URL
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String url;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.OPERATE_TIME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private Date operateTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.BROWER_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String browerType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.MOBLIE_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String moblieType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.SUC_FLG
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private Short sucFlg;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.MOBILE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String mobile;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.IP
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String ip;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.ID_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private Short idType;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.ID_NO
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String idNo;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_operate_log.SESSIONID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private String sessionid;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table sys_operate_log
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.PINDEX
     * @return  the value of sys_operate_log.PINDEX
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.PINDEX
     * @param pindex  the value for sys_operate_log.PINDEX
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.USER_NAME
     * @return  the value of sys_operate_log.USER_NAME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.USER_NAME
     * @param userName  the value for sys_operate_log.USER_NAME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.USER_ID
     * @return  the value of sys_operate_log.USER_ID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.USER_ID
     * @param userId  the value for sys_operate_log.USER_ID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.OPERATE
     * @return  the value of sys_operate_log.OPERATE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getOperate() {
        return operate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.OPERATE
     * @param operate  the value for sys_operate_log.OPERATE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.DESCRIPTION
     * @return  the value of sys_operate_log.DESCRIPTION
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.DESCRIPTION
     * @param description  the value for sys_operate_log.DESCRIPTION
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.URL
     * @return  the value of sys_operate_log.URL
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.URL
     * @param url  the value for sys_operate_log.URL
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.OPERATE_TIME
     * @return  the value of sys_operate_log.OPERATE_TIME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.OPERATE_TIME
     * @param operateTime  the value for sys_operate_log.OPERATE_TIME
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.BROWER_TYPE
     * @return  the value of sys_operate_log.BROWER_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getBrowerType() {
        return browerType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.BROWER_TYPE
     * @param browerType  the value for sys_operate_log.BROWER_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setBrowerType(String browerType) {
        this.browerType = browerType == null ? null : browerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.MOBLIE_TYPE
     * @return  the value of sys_operate_log.MOBLIE_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getMoblieType() {
        return moblieType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.MOBLIE_TYPE
     * @param moblieType  the value for sys_operate_log.MOBLIE_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setMoblieType(String moblieType) {
        this.moblieType = moblieType == null ? null : moblieType.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.SUC_FLG
     * @return  the value of sys_operate_log.SUC_FLG
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public Short getSucFlg() {
        return sucFlg;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.SUC_FLG
     * @param sucFlg  the value for sys_operate_log.SUC_FLG
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setSucFlg(Short sucFlg) {
        this.sucFlg = sucFlg;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.MOBILE
     * @return  the value of sys_operate_log.MOBILE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.MOBILE
     * @param mobile  the value for sys_operate_log.MOBILE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.IP
     * @return  the value of sys_operate_log.IP
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.IP
     * @param ip  the value for sys_operate_log.IP
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.ID_TYPE
     * @return  the value of sys_operate_log.ID_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public Short getIdType() {
        return idType;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.ID_TYPE
     * @param idType  the value for sys_operate_log.ID_TYPE
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setIdType(Short idType) {
        this.idType = idType;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.ID_NO
     * @return  the value of sys_operate_log.ID_NO
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.ID_NO
     * @param idNo  the value for sys_operate_log.ID_NO
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_operate_log.SESSIONID
     * @return  the value of sys_operate_log.SESSIONID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_operate_log.SESSIONID
     * @param sessionid  the value for sys_operate_log.SESSIONID
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_operate_log
     * @mbggenerated  Mon Dec 14 18:49:07 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", userName=").append(userName);
        sb.append(", userId=").append(userId);
        sb.append(", operate=").append(operate);
        sb.append(", description=").append(description);
        sb.append(", url=").append(url);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", browerType=").append(browerType);
        sb.append(", moblieType=").append(moblieType);
        sb.append(", sucFlg=").append(sucFlg);
        sb.append(", mobile=").append(mobile);
        sb.append(", ip=").append(ip);
        sb.append(", idType=").append(idType);
        sb.append(", idNo=").append(idNo);
        sb.append(", sessionid=").append(sessionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}