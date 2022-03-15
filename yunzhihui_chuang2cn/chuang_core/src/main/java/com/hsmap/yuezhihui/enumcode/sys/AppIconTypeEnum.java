package com.hsmap.yuezhihui.enumcode.sys;


import java.util.HashMap;
import java.util.Map;

public enum AppIconTypeEnum {


    OPERATE_TYPE_0(0,"返佣链接"),
    OPERATE_TYPE_1(1,"生活服务");

    private int type;
    private String name;

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
        AppIconTypeEnum[] operateTypeEnums = AppIconTypeEnum.values();
        for (AppIconTypeEnum typeEnum : operateTypeEnums) {
            map.put(typeEnum.type, typeEnum.name);
        }
    }
    AppIconTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String getName(int type) {
        for (AppIconTypeEnum c : AppIconTypeEnum.values()) {
            if (c.getType() == type) {
                return c.name;
            }
        }
        return "";
    }
}
