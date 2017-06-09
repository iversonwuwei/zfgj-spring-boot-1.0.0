package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;
import java.util.Date;

public class SysOffice extends MyDataEntity<SysOffice> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.parent_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.parent_ids
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String parentIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.name
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.sort
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private Long sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.area_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.type
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.grade
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String grade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.address
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.zip_code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String zipCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.master
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String master;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.phone
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.fax
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.email
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.USEABLE
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String useable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.PRIMARY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String primaryPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.DEPUTY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String deputyPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.create_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.create_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.update_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.update_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.remarks
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office.del_flag
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private String delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.parent_id
     *
     * @return the value of sys_office.parent_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.parent_id
     *
     * @param parentId the value for sys_office.parent_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.parent_ids
     *
     * @return the value of sys_office.parent_ids
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.parent_ids
     *
     * @param parentIds the value for sys_office.parent_ids
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.name
     *
     * @return the value of sys_office.name
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.name
     *
     * @param name the value for sys_office.name
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.sort
     *
     * @return the value of sys_office.sort
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public Long getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.sort
     *
     * @param sort the value for sys_office.sort
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.area_id
     *
     * @return the value of sys_office.area_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.area_id
     *
     * @param areaId the value for sys_office.area_id
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.code
     *
     * @return the value of sys_office.code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.code
     *
     * @param code the value for sys_office.code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.type
     *
     * @return the value of sys_office.type
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.type
     *
     * @param type the value for sys_office.type
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.grade
     *
     * @return the value of sys_office.grade
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.grade
     *
     * @param grade the value for sys_office.grade
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.address
     *
     * @return the value of sys_office.address
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.address
     *
     * @param address the value for sys_office.address
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.zip_code
     *
     * @return the value of sys_office.zip_code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.zip_code
     *
     * @param zipCode the value for sys_office.zip_code
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.master
     *
     * @return the value of sys_office.master
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getMaster() {
        return master;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.master
     *
     * @param master the value for sys_office.master
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.phone
     *
     * @return the value of sys_office.phone
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.phone
     *
     * @param phone the value for sys_office.phone
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.fax
     *
     * @return the value of sys_office.fax
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.fax
     *
     * @param fax the value for sys_office.fax
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.email
     *
     * @return the value of sys_office.email
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.email
     *
     * @param email the value for sys_office.email
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.USEABLE
     *
     * @return the value of sys_office.USEABLE
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getUseable() {
        return useable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.USEABLE
     *
     * @param useable the value for sys_office.USEABLE
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.PRIMARY_PERSON
     *
     * @return the value of sys_office.PRIMARY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getPrimaryPerson() {
        return primaryPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.PRIMARY_PERSON
     *
     * @param primaryPerson the value for sys_office.PRIMARY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson == null ? null : primaryPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.DEPUTY_PERSON
     *
     * @return the value of sys_office.DEPUTY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getDeputyPerson() {
        return deputyPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.DEPUTY_PERSON
     *
     * @param deputyPerson the value for sys_office.DEPUTY_PERSON
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setDeputyPerson(String deputyPerson) {
        this.deputyPerson = deputyPerson == null ? null : deputyPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.create_by
     *
     * @return the value of sys_office.create_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.create_by
     *
     * @param createBy the value for sys_office.create_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.create_date
     *
     * @return the value of sys_office.create_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.create_date
     *
     * @param createDate the value for sys_office.create_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.update_by
     *
     * @return the value of sys_office.update_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.update_by
     *
     * @param updateBy the value for sys_office.update_by
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.update_date
     *
     * @return the value of sys_office.update_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.update_date
     *
     * @param updateDate the value for sys_office.update_date
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.remarks
     *
     * @return the value of sys_office.remarks
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.remarks
     *
     * @param remarks the value for sys_office.remarks
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office.del_flag
     *
     * @return the value of sys_office.del_flag
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office.del_flag
     *
     * @param delFlag the value for sys_office.del_flag
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", areaId=").append(areaId);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", grade=").append(grade);
        sb.append(", address=").append(address);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", master=").append(master);
        sb.append(", phone=").append(phone);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", useable=").append(useable);
        sb.append(", primaryPerson=").append(primaryPerson);
        sb.append(", deputyPerson=").append(deputyPerson);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}