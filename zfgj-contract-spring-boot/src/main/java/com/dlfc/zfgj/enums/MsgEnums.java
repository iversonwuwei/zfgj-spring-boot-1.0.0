package com.dlfc.zfgj.enums;

/**
 * Created by wanglijun on 2017/3/31.
 */
public enum MsgEnums {
    REGESIT("1"), FINDPWDCHECKPHONE("2"), FINDPWDCHECKBANKPHONE("3"), SETAPPLYPWD("4"), FINDAPPLYPWD("5"), REPLACEPHONETOOLD("6"), REPLACEPHONETONEW("7"),
    BINDBANKPHONE("8") {

        @Override
        public boolean isRest() {

            return true;
        }
    };

    private String value;

    private MsgEnums(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }

    public boolean isRest() {

        return false;
    }
}
