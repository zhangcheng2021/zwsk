package com.hsmap.yuezhihui.enumcode.app;

import java.util.HashMap;
import java.util.Map;

public enum OsTypeEnum {

    TYPE_ANDROID(1,"安卓_android"),
    TYPE_IOS(2,"苹果_ios");


    private int type;
    private String name;


    OsTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static Map<Integer, String> map = new HashMap<>();

    static {
        OsTypeEnum[] typeEnums = OsTypeEnum.values();
        for (OsTypeEnum typeEnum : typeEnums) {
            map.put(typeEnum.type, typeEnum.name);
        }
    }
    public static String getName(int type) {
        for (OsTypeEnum c : OsTypeEnum.values()) {
            if (c.getType() == type) {
                return c.name;
            }
        }
        return "";
    }
}
