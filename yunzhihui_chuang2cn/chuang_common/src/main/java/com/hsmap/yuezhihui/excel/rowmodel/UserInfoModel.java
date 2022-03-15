package com.hsmap.yuezhihui.excel.rowmodel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class UserInfoModel extends BaseRowModel {

    @ExcelProperty(index = 0,value = "姓名")
    private String name;

    @ExcelProperty(index = 1,value = "手机号码")
    private String mobile;

    @ExcelProperty(index = 2,value = "登录次数")
    private Double loginCount;

    @ExcelProperty(index = 3,value = "注册时间",format = "yyyy/MM/dd")
    private Date createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Double loginCount) {
        this.loginCount = loginCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
