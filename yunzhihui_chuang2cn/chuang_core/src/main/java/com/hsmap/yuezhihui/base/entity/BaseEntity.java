package com.hsmap.yuezhihui.base.entity;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseEntity {

     final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public String toJson() {
        return  JSONObject.toJSON(this).toString();
    }

//    public String getRedisPrefix() {
//        return this.getClass().getValue()+"_";
//    }


    private String startDate;

    private String endDate;
    private int isExport;

    public int getIsExport() {
        return isExport;
    }

    public void setIsExport(int isExport) {
        this.isExport = isExport;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
