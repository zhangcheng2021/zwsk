package com.hsmap.yuezhihui.enumcode;

public enum OperateLogTypeEnum {

    TYPE_URL(1,"URL_访问日志"),
    TYPE_ASSET(2,"ASSET_资金");


    private int type;
    private String level;


    OperateLogTypeEnum(int type, String level) {
        this.type = type;
        this.level = level;
    }

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static String getLevel(int type) {
        for (OperateLogTypeEnum c : OperateLogTypeEnum.values()) {
            if (c.getType() == type) {
                return c.level;
            }
        }
        return "";
    }
}
