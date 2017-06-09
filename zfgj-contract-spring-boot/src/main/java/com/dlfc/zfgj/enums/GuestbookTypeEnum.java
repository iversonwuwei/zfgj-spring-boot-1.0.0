package com.dlfc.zfgj.enums;

/**
 * Created by K on 2017/5/5.
 */
public enum GuestbookTypeEnum {
    ZFGJ("租房管家", "2"),
    APP("APP", "5"),
    ZFW("租房网", "4");

    private String key;

    private String value;

    private GuestbookTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
