package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;
import java.util.Date;

public class CmsAdMedia extends MyDataEntity implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.PINDEX
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.AD_ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String adId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.TITLE
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.SORT
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Integer sort;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.LINK
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String link;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.description
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.NOFOLLOW
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Integer nofollow;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.CREATE_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String createUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.CREATE_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Short createUserIdentity;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.CREATE_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.MODIFY_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private String modifyUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.MODIFY_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Short modifyUserIdentity;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.MODIFY_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Date modifyTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.DELETE_FLG
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Short deleteFlg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cms_ad_media.VERSION
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cms_ad_media
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.ID
	 * @return  the value of cms_ad_media.ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.ID
	 * @param id  the value for cms_ad_media.ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.PINDEX
	 * @return  the value of cms_ad_media.PINDEX
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.PINDEX
	 * @param pindex  the value for cms_ad_media.PINDEX
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.AD_ID
	 * @return  the value of cms_ad_media.AD_ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getAdId() {
		return adId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.AD_ID
	 * @param adId  the value for cms_ad_media.AD_ID
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setAdId(String adId) {
		this.adId = adId == null ? null : adId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.TITLE
	 * @return  the value of cms_ad_media.TITLE
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.TITLE
	 * @param title  the value for cms_ad_media.TITLE
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.SORT
	 * @return  the value of cms_ad_media.SORT
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.SORT
	 * @param sort  the value for cms_ad_media.SORT
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.LINK
	 * @return  the value of cms_ad_media.LINK
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getLink() {
		return link;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.LINK
	 * @param link  the value for cms_ad_media.LINK
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setLink(String link) {
		this.link = link == null ? null : link.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.description
	 * @return  the value of cms_ad_media.description
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.description
	 * @param description  the value for cms_ad_media.description
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.NOFOLLOW
	 * @return  the value of cms_ad_media.NOFOLLOW
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Integer getNofollow() {
		return nofollow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.NOFOLLOW
	 * @param nofollow  the value for cms_ad_media.NOFOLLOW
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setNofollow(Integer nofollow) {
		this.nofollow = nofollow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.CREATE_USER
	 * @return  the value of cms_ad_media.CREATE_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.CREATE_USER
	 * @param createUser  the value for cms_ad_media.CREATE_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.CREATE_USER_IDENTITY
	 * @return  the value of cms_ad_media.CREATE_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Short getCreateUserIdentity() {
		return createUserIdentity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.CREATE_USER_IDENTITY
	 * @param createUserIdentity  the value for cms_ad_media.CREATE_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setCreateUserIdentity(Short createUserIdentity) {
		this.createUserIdentity = createUserIdentity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.CREATE_TIME
	 * @return  the value of cms_ad_media.CREATE_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.CREATE_TIME
	 * @param createTime  the value for cms_ad_media.CREATE_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.MODIFY_USER
	 * @return  the value of cms_ad_media.MODIFY_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public String getModifyUser() {
		return modifyUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.MODIFY_USER
	 * @param modifyUser  the value for cms_ad_media.MODIFY_USER
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser == null ? null : modifyUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.MODIFY_USER_IDENTITY
	 * @return  the value of cms_ad_media.MODIFY_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Short getModifyUserIdentity() {
		return modifyUserIdentity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.MODIFY_USER_IDENTITY
	 * @param modifyUserIdentity  the value for cms_ad_media.MODIFY_USER_IDENTITY
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setModifyUserIdentity(Short modifyUserIdentity) {
		this.modifyUserIdentity = modifyUserIdentity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.MODIFY_TIME
	 * @return  the value of cms_ad_media.MODIFY_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.MODIFY_TIME
	 * @param modifyTime  the value for cms_ad_media.MODIFY_TIME
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.DELETE_FLG
	 * @return  the value of cms_ad_media.DELETE_FLG
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Short getDeleteFlg() {
		return deleteFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.DELETE_FLG
	 * @param deleteFlg  the value for cms_ad_media.DELETE_FLG
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setDeleteFlg(Short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cms_ad_media.VERSION
	 * @return  the value of cms_ad_media.VERSION
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cms_ad_media.VERSION
	 * @param version  the value for cms_ad_media.VERSION
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_media
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", pindex=").append(pindex);
		sb.append(", adId=").append(adId);
		sb.append(", title=").append(title);
		sb.append(", sort=").append(sort);
		sb.append(", link=").append(link);
		sb.append(", description=").append(description);
		sb.append(", nofollow=").append(nofollow);
		sb.append(", createUser=").append(createUser);
		sb.append(", createUserIdentity=").append(createUserIdentity);
		sb.append(", createTime=").append(createTime);
		sb.append(", modifyUser=").append(modifyUser);
		sb.append(", modifyUserIdentity=").append(modifyUserIdentity);
		sb.append(", modifyTime=").append(modifyTime);
		sb.append(", deleteFlg=").append(deleteFlg);
		sb.append(", version=").append(version);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}