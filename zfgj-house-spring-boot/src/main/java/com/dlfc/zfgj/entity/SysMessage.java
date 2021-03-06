package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;
import java.util.Date;

public class SysMessage extends MyDataEntity<SysMessage> implements Serializable {

    private String id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.PINDEX
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private Integer pindex;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.RECEIVER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String receiverUid;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.RECEIVER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String receiverName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.TITLE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String title;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.CONTENT
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String content;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.URL
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String url;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.STATUS
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private Short status;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.TYPE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private Short type;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.MASS_FLG
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private Short massFlg;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.SENDER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String senderUid;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.SENDER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private String senderName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sys_message.SEND_TIME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private Date sendTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table sys_message
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.PINDEX
     * @return  the value of sys_message.PINDEX
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.PINDEX
     * @param pindex  the value for sys_message.PINDEX
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.RECEIVER_UID
     * @return  the value of sys_message.RECEIVER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getReceiverUid() {
        return receiverUid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.RECEIVER_UID
     * @param receiverUid  the value for sys_message.RECEIVER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid == null ? null : receiverUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.RECEIVER_NAME
     * @return  the value of sys_message.RECEIVER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.RECEIVER_NAME
     * @param receiverName  the value for sys_message.RECEIVER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.TITLE
     * @return  the value of sys_message.TITLE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.TITLE
     * @param title  the value for sys_message.TITLE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.CONTENT
     * @return  the value of sys_message.CONTENT
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.CONTENT
     * @param content  the value for sys_message.CONTENT
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.URL
     * @return  the value of sys_message.URL
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.URL
     * @param url  the value for sys_message.URL
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.STATUS
     * @return  the value of sys_message.STATUS
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.STATUS
     * @param status  the value for sys_message.STATUS
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.TYPE
     * @return  the value of sys_message.TYPE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.TYPE
     * @param type  the value for sys_message.TYPE
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.MASS_FLG
     * @return  the value of sys_message.MASS_FLG
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public Short getMassFlg() {
        return massFlg;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.MASS_FLG
     * @param massFlg  the value for sys_message.MASS_FLG
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setMassFlg(Short massFlg) {
        this.massFlg = massFlg;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.SENDER_UID
     * @return  the value of sys_message.SENDER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getSenderUid() {
        return senderUid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.SENDER_UID
     * @param senderUid  the value for sys_message.SENDER_UID
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid == null ? null : senderUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.SENDER_NAME
     * @return  the value of sys_message.SENDER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.SENDER_NAME
     * @param senderName  the value for sys_message.SENDER_NAME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sys_message.SEND_TIME
     * @return  the value of sys_message.SEND_TIME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sys_message.SEND_TIME
     * @param sendTime  the value for sys_message.SEND_TIME
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_message
     * @mbggenerated  Tue Dec 29 09:21:43 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", receiverUid=").append(receiverUid);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", url=").append(url);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", massFlg=").append(massFlg);
        sb.append(", senderUid=").append(senderUid);
        sb.append(", senderName=").append(senderName);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    private Integer count;
    
    private Integer statusCount;
    
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(Integer statusCount) {
		this.statusCount = statusCount;
	}
	
}