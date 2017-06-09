package com.dlfc.zfgj.enums;

/**
 * Created by wanglijun on 2017/3/31.
 */
public enum TemplateNoEnum {
    REGESIT_NO("Z0001"), FINDPWDCHECKPHONE_NO("Z0005"), FINDPWDCHECKBANKPHONE_NO("Z0007"), SETAPPLYPWD_NO("Z0008"), FINDAPPLYPWD_NO("Z0009"),
    REPLACEPHONETOOLD_NO("Z0010"), REPLACEPHONETONEW_NO("Z0011"), BINDBANKPHONE_NO("Z0014"), FEEDBACK("S0010"), CONDISSCREATE("H0010"), CONDISSAGREE("H0011"),
    CONDISSREFUSED("H0012"), OWNERSREFUSEDCONTRACT("H0005"), LESSEECONFIRMCONTRACT("H0007"), LESSEEREFUSEDCONTRACT("H0008"), CONTRACTAPPLY("H0004"),
    LESSORCONFIRMCONTRACT("H0006"), BROADBAND("S0013"), CREATEDEPOSIT("S0001"), CONFIRMDEPOSIT("S0002"), REFUSEDDEPOSIT("S0003") {

        @Override
        public boolean isRest() {

            return true;
        }
    };

    private String value;

    private TemplateNoEnum(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }

    public boolean isRest() {

        return false;
    }
}
