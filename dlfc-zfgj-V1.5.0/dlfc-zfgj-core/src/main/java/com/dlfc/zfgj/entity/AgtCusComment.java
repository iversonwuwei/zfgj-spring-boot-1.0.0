package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;
import java.util.Date;

public class AgtCusComment extends MyDataEntity<AgtCusComment> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.PINDEX
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.CUSTOMER_ID
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private String customerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.EVENT
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private Integer event;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.HOUSE_NO
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private String houseNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.EVENT_TIME
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private Date eventTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cus_comment.COMMENTS
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private String comments;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.PINDEX
	 * @return  the value of agt_cus_comment.PINDEX
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.PINDEX
	 * @param pindex  the value for agt_cus_comment.PINDEX
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.CUSTOMER_ID
	 * @return  the value of agt_cus_comment.CUSTOMER_ID
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.CUSTOMER_ID
	 * @param customerId  the value for agt_cus_comment.CUSTOMER_ID
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.EVENT
	 * @return  the value of agt_cus_comment.EVENT
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public Integer getEvent() {
		return event;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.EVENT
	 * @param event  the value for agt_cus_comment.EVENT
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setEvent(Integer event) {
		this.event = event;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.HOUSE_NO
	 * @return  the value of agt_cus_comment.HOUSE_NO
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public String getHouseNo() {
		return houseNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.HOUSE_NO
	 * @param houseNo  the value for agt_cus_comment.HOUSE_NO
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo == null ? null : houseNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.EVENT_TIME
	 * @return  the value of agt_cus_comment.EVENT_TIME
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.EVENT_TIME
	 * @param eventTime  the value for agt_cus_comment.EVENT_TIME
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cus_comment.COMMENTS
	 * @return  the value of agt_cus_comment.COMMENTS
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cus_comment.COMMENTS
	 * @param comments  the value for agt_cus_comment.COMMENTS
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setComments(String comments) {
		this.comments = comments == null ? null : comments.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", customerId=").append(customerId);
		sb.append(", event=").append(event);
		sb.append(", houseNo=").append(houseNo);
		sb.append(", eventTime=").append(eventTime);
		sb.append(", comments=").append(comments);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}