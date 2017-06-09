
/**
 * @name: LeaseInfoSysSource.java
 * @Copyright: (c) 2017 DLFC. All rights reserved.
 * @description:
 * @version: 1.0
 * @date : 2017年5月10日
 * @author: liuyundong
 * @Modification History:<br>
 * Date          Author         Version        Discription
 * 2017年5月10日       liuyundong        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.enums;

/**
 * @author liuyundong
 * @version 1.0
 * @name: LeaseInfoSysSource
 * @description: 经纪人在职状态枚举
 */
public enum AgtStatusEnum {

    ACTIVE("在职", "0"),
    INACTIVE("不在职", "1"),
    APPROVING("审核中", "2"),
    REFUSED("拒绝", "3");

    private String name;

    private String value;

    AgtStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
