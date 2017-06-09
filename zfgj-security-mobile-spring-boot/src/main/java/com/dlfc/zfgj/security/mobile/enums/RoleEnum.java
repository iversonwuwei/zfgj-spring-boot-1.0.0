package com.dlfc.zfgj.security.mobile.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by K on 2017/3/29.
 */
public class RoleEnum extends ValuedEnum {

    /**
     * 平民
     */
    private static final int NO_ONE = 0;

    /**
     * 个人
     */
    private static final int PERSONAL = 1;

    /**
     * 部门
     */
    private static final int DEPARTMENTAL = 2;

    /**
     * 管理
     */
    private static final int ASSIGNMENT = 3;

    private RoleEnum(String name, int value) {
        super(name, value);
    }

    /**
     * 平民
     */
    public static final RoleEnum NO_ONE_ENUM = new RoleEnum("平民", NO_ONE);
    /**
     * 个人
     */
    public static final RoleEnum PERSONAL_ENUM = new RoleEnum("个人", PERSONAL);
    /**
     * 部门
     */
    public static final RoleEnum DEPARTMENTAL_ENUM = new RoleEnum("部门", DEPARTMENTAL);
    /**
     * 管理
     */
    public static final RoleEnum ASSIGNMENT_ENUM = new RoleEnum("管理", ASSIGNMENT);

    public static YesNoEnum getEnum(int temp) {
        return (YesNoEnum) getEnum(YesNoEnum.class, temp);
    }

    @SuppressWarnings("rawtypes")
    public static Map getEnumMap() {
        return getEnumMap(YesNoEnum.class);
    }

    @SuppressWarnings("rawtypes")
    public static List getEnumList() {
        return getEnumList(YesNoEnum.class);
    }

    @SuppressWarnings("rawtypes")
    public static Iterator iterator() {
        return iterator(YesNoEnum.class);
    }

    public static String getName(int value) {
        return getName(String.valueOf(value));
    }

    @SuppressWarnings("rawtypes")
    public static String getName(String value) {
        List list = getEnumList();
        for (int i = 0; i < list.size(); i++) {
            ValuedEnum type = (ValuedEnum) list.get(i);
            String val = String.valueOf(type.getValue());
            if (val.equals(value)) {
                return type.getName();
            }
        }

        return null;
    }
}
