package com.dlfc.zfgj.enums;

/**
 * Created by K on 2017/6/5.
 */
public enum PasswordLevelEnum {

    LEVEL_INIT("初始值", "0"),
    LEVEL_LOW("低", "1"),
    LEVEL_MID("中", "2"),
    LEVEL_HIGH("高", "3");

    private String name;

    private String value;

    PasswordLevelEnum(String name, String value) {
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
