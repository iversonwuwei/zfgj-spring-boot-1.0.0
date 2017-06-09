package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import com.dlfc.admin.modules.sys.entity.Menu;

import java.io.Serializable;
import java.util.Date;

public class SysMenu extends MyDataEntity<SysMenu> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.parent_id
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.parent_ids
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String parentIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.name
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.sort
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private Long sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.href
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String href;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.target
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String target;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.icon
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.is_show
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String isShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.permission
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.create_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.create_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.update_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.update_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.remarks
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.del_flag
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private String delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.parent_id
     *
     * @return the value of sys_menu.parent_id
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.parent_id
     *
     * @param parentId the value for sys_menu.parent_id
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.parent_ids
     *
     * @return the value of sys_menu.parent_ids
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.parent_ids
     *
     * @param parentIds the value for sys_menu.parent_ids
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.name
     *
     * @return the value of sys_menu.name
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.name
     *
     * @param name the value for sys_menu.name
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.sort
     *
     * @return the value of sys_menu.sort
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public Long getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.sort
     *
     * @param sort the value for sys_menu.sort
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.href
     *
     * @return the value of sys_menu.href
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getHref() {
        return href;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.href
     *
     * @param href the value for sys_menu.href
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.target
     *
     * @return the value of sys_menu.target
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getTarget() {
        return target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.target
     *
     * @param target the value for sys_menu.target
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.icon
     *
     * @return the value of sys_menu.icon
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.icon
     *
     * @param icon the value for sys_menu.icon
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.is_show
     *
     * @return the value of sys_menu.is_show
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.is_show
     *
     * @param isShow the value for sys_menu.is_show
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.permission
     *
     * @return the value of sys_menu.permission
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.permission
     *
     * @param permission the value for sys_menu.permission
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.create_by
     *
     * @return the value of sys_menu.create_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.create_by
     *
     * @param createBy the value for sys_menu.create_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.create_date
     *
     * @return the value of sys_menu.create_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.create_date
     *
     * @param createDate the value for sys_menu.create_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.update_by
     *
     * @return the value of sys_menu.update_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.update_by
     *
     * @param updateBy the value for sys_menu.update_by
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.update_date
     *
     * @return the value of sys_menu.update_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.update_date
     *
     * @param updateDate the value for sys_menu.update_date
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.remarks
     *
     * @return the value of sys_menu.remarks
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.remarks
     *
     * @param remarks the value for sys_menu.remarks
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.del_flag
     *
     * @return the value of sys_menu.del_flag
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.del_flag
     *
     * @param delFlag the value for sys_menu.del_flag
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
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
        sb.append(", href=").append(href);
        sb.append(", target=").append(target);
        sb.append(", icon=").append(icon);
        sb.append(", isShow=").append(isShow);
        sb.append(", permission=").append(permission);
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
    
    private String userId;//用户ID
    
    private Menu parent;// 父级菜单
    
    private String isButton;//是否是按钮
    
	public String getIsButton() {
		return isButton;
	}

	public void setIsButton(String isButton) {
		this.isButton = isButton;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}