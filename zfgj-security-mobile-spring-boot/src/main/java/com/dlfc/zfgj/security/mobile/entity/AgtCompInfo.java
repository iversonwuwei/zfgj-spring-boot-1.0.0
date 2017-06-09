package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AgtCompInfo extends MyDataEntity<AgtCompInfo> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.PINDEX
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.COM_NO
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String comNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.OFFICE_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String officeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.BUSINESS_LICENSE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String businessLicense;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.ORGANIZATION_CODE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String organizationCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.ECHAPTER_PATH
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String echapterPath;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.ECHAPTER_ENABLED
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String echapterEnabled;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.IMG1_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String img1Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.IMG2_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String img2Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.IMG3_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String img3Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.FULL_NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String fullName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.S_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String sSpelling;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.A_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String aSpelling;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.ADDRESS
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.LEGAL_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String legalPerson;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.LP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String lpMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.LP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String lpIdcard;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.CONTACT_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String contactPerson;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.CP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String cpMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.CP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String cpIdcard;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.ADMIN_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String adminId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.TEL
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String tel;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.LICENSE_DATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private Date licenseDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.LOCK_FLAG
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private Integer lockFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.COMMISSION_RATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private BigDecimal commissionRate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.GRADE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private Integer grade;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.POPULAR
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private Integer popular;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_comp_info.INTRODUCTION
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private String introduction;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.PINDEX
	 * @return  the value of agt_comp_info.PINDEX
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.PINDEX
	 * @param pindex  the value for agt_comp_info.PINDEX
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.COM_NO
	 * @return  the value of agt_comp_info.COM_NO
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getComNo() {
		return comNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.COM_NO
	 * @param comNo  the value for agt_comp_info.COM_NO
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setComNo(String comNo) {
		this.comNo = comNo == null ? null : comNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.OFFICE_ID
	 * @return  the value of agt_comp_info.OFFICE_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getOfficeId() {
		return officeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.OFFICE_ID
	 * @param officeId  the value for agt_comp_info.OFFICE_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId == null ? null : officeId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.BUSINESS_LICENSE
	 * @return  the value of agt_comp_info.BUSINESS_LICENSE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getBusinessLicense() {
		return businessLicense;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.BUSINESS_LICENSE
	 * @param businessLicense  the value for agt_comp_info.BUSINESS_LICENSE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense == null ? null : businessLicense.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.ORGANIZATION_CODE
	 * @return  the value of agt_comp_info.ORGANIZATION_CODE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getOrganizationCode() {
		return organizationCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.ORGANIZATION_CODE
	 * @param organizationCode  the value for agt_comp_info.ORGANIZATION_CODE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode == null ? null : organizationCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.ECHAPTER_PATH
	 * @return  the value of agt_comp_info.ECHAPTER_PATH
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getEchapterPath() {
		return echapterPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.ECHAPTER_PATH
	 * @param echapterPath  the value for agt_comp_info.ECHAPTER_PATH
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setEchapterPath(String echapterPath) {
		this.echapterPath = echapterPath == null ? null : echapterPath.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.ECHAPTER_ENABLED
	 * @return  the value of agt_comp_info.ECHAPTER_ENABLED
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getEchapterEnabled() {
		return echapterEnabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.ECHAPTER_ENABLED
	 * @param echapterEnabled  the value for agt_comp_info.ECHAPTER_ENABLED
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setEchapterEnabled(String echapterEnabled) {
		this.echapterEnabled = echapterEnabled == null ? null : echapterEnabled.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.IMG1_ID
	 * @return  the value of agt_comp_info.IMG1_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getImg1Id() {
		return img1Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.IMG1_ID
	 * @param img1Id  the value for agt_comp_info.IMG1_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setImg1Id(String img1Id) {
		this.img1Id = img1Id == null ? null : img1Id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.IMG2_ID
	 * @return  the value of agt_comp_info.IMG2_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getImg2Id() {
		return img2Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.IMG2_ID
	 * @param img2Id  the value for agt_comp_info.IMG2_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setImg2Id(String img2Id) {
		this.img2Id = img2Id == null ? null : img2Id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.IMG3_ID
	 * @return  the value of agt_comp_info.IMG3_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getImg3Id() {
		return img3Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.IMG3_ID
	 * @param img3Id  the value for agt_comp_info.IMG3_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setImg3Id(String img3Id) {
		this.img3Id = img3Id == null ? null : img3Id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.NAME
	 * @return  the value of agt_comp_info.NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.NAME
	 * @param name  the value for agt_comp_info.NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.FULL_NAME
	 * @return  the value of agt_comp_info.FULL_NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.FULL_NAME
	 * @param fullName  the value for agt_comp_info.FULL_NAME
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName == null ? null : fullName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.S_SPELLING
	 * @return  the value of agt_comp_info.S_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getsSpelling() {
		return sSpelling;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.S_SPELLING
	 * @param sSpelling  the value for agt_comp_info.S_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setsSpelling(String sSpelling) {
		this.sSpelling = sSpelling == null ? null : sSpelling.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.A_SPELLING
	 * @return  the value of agt_comp_info.A_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getaSpelling() {
		return aSpelling;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.A_SPELLING
	 * @param aSpelling  the value for agt_comp_info.A_SPELLING
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setaSpelling(String aSpelling) {
		this.aSpelling = aSpelling == null ? null : aSpelling.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.ADDRESS
	 * @return  the value of agt_comp_info.ADDRESS
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.ADDRESS
	 * @param address  the value for agt_comp_info.ADDRESS
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.LEGAL_PERSON
	 * @return  the value of agt_comp_info.LEGAL_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.LEGAL_PERSON
	 * @param legalPerson  the value for agt_comp_info.LEGAL_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.LP_MOBILE
	 * @return  the value of agt_comp_info.LP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getLpMobile() {
		return lpMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.LP_MOBILE
	 * @param lpMobile  the value for agt_comp_info.LP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setLpMobile(String lpMobile) {
		this.lpMobile = lpMobile == null ? null : lpMobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.LP_IDCARD
	 * @return  the value of agt_comp_info.LP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getLpIdcard() {
		return lpIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.LP_IDCARD
	 * @param lpIdcard  the value for agt_comp_info.LP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setLpIdcard(String lpIdcard) {
		this.lpIdcard = lpIdcard == null ? null : lpIdcard.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.CONTACT_PERSON
	 * @return  the value of agt_comp_info.CONTACT_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.CONTACT_PERSON
	 * @param contactPerson  the value for agt_comp_info.CONTACT_PERSON
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson == null ? null : contactPerson.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.CP_MOBILE
	 * @return  the value of agt_comp_info.CP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getCpMobile() {
		return cpMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.CP_MOBILE
	 * @param cpMobile  the value for agt_comp_info.CP_MOBILE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setCpMobile(String cpMobile) {
		this.cpMobile = cpMobile == null ? null : cpMobile.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.CP_IDCARD
	 * @return  the value of agt_comp_info.CP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getCpIdcard() {
		return cpIdcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.CP_IDCARD
	 * @param cpIdcard  the value for agt_comp_info.CP_IDCARD
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setCpIdcard(String cpIdcard) {
		this.cpIdcard = cpIdcard == null ? null : cpIdcard.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.ADMIN_ID
	 * @return  the value of agt_comp_info.ADMIN_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.ADMIN_ID
	 * @param adminId  the value for agt_comp_info.ADMIN_ID
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId == null ? null : adminId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.TEL
	 * @return  the value of agt_comp_info.TEL
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.TEL
	 * @param tel  the value for agt_comp_info.TEL
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.LICENSE_DATE
	 * @return  the value of agt_comp_info.LICENSE_DATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public Date getLicenseDate() {
		return licenseDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.LICENSE_DATE
	 * @param licenseDate  the value for agt_comp_info.LICENSE_DATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.LOCK_FLAG
	 * @return  the value of agt_comp_info.LOCK_FLAG
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public Integer getLockFlag() {
		return lockFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.LOCK_FLAG
	 * @param lockFlag  the value for agt_comp_info.LOCK_FLAG
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setLockFlag(Integer lockFlag) {
		this.lockFlag = lockFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.COMMISSION_RATE
	 * @return  the value of agt_comp_info.COMMISSION_RATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.COMMISSION_RATE
	 * @param commissionRate  the value for agt_comp_info.COMMISSION_RATE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.GRADE
	 * @return  the value of agt_comp_info.GRADE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.GRADE
	 * @param grade  the value for agt_comp_info.GRADE
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.POPULAR
	 * @return  the value of agt_comp_info.POPULAR
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public Integer getPopular() {
		return popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.POPULAR
	 * @param popular  the value for agt_comp_info.POPULAR
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_comp_info.INTRODUCTION
	 * @return  the value of agt_comp_info.INTRODUCTION
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_comp_info.INTRODUCTION
	 * @param introduction  the value for agt_comp_info.INTRODUCTION
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_comp_info
	 * @mbggenerated  Mon Jul 25 15:40:42 CST 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", comNo=").append(comNo);
		sb.append(", officeId=").append(officeId);
		sb.append(", businessLicense=").append(businessLicense);
		sb.append(", organizationCode=").append(organizationCode);
		sb.append(", echapterPath=").append(echapterPath);
		sb.append(", echapterEnabled=").append(echapterEnabled);
		sb.append(", img1Id=").append(img1Id);
		sb.append(", img2Id=").append(img2Id);
		sb.append(", img3Id=").append(img3Id);
		sb.append(", name=").append(name);
		sb.append(", fullName=").append(fullName);
		sb.append(", sSpelling=").append(sSpelling);
		sb.append(", aSpelling=").append(aSpelling);
		sb.append(", address=").append(address);
		sb.append(", legalPerson=").append(legalPerson);
		sb.append(", lpMobile=").append(lpMobile);
		sb.append(", lpIdcard=").append(lpIdcard);
		sb.append(", contactPerson=").append(contactPerson);
		sb.append(", cpMobile=").append(cpMobile);
		sb.append(", cpIdcard=").append(cpIdcard);
		sb.append(", adminId=").append(adminId);
		sb.append(", tel=").append(tel);
		sb.append(", licenseDate=").append(licenseDate);
		sb.append(", lockFlag=").append(lockFlag);
		sb.append(", commissionRate=").append(commissionRate);
		sb.append(", grade=").append(grade);
		sb.append(", popular=").append(popular);
		sb.append(", introduction=").append(introduction);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	private String filePath;//存放logo地址

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}