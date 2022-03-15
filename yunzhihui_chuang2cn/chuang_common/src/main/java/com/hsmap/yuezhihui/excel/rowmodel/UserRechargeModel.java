package com.hsmap.yuezhihui.excel.rowmodel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import java.math.BigDecimal;
import java.util.Date;

/***
 * 充值导出
 */
public class UserRechargeModel extends BaseRowModel {
    @ExcelProperty(index = 0, value = "用户Id")
    private String userId;
    @ExcelProperty(index = 1, value = "用户名")
    private String userName;
    @ExcelProperty(index = 2, value = "用户类型")
    private String userSource;


    @ExcelProperty(index = 3, value = "用户余额")
    private BigDecimal availableBalance;
    @ExcelProperty(index = 4, value = "申请金额")
    private BigDecimal amount;

    @ExcelProperty(index = 5, value = "转账人姓名")
    private String rechargeName;
    @ExcelProperty(index = 6, value = "到帐金额")
    private BigDecimal actual;
    @ExcelProperty(index = 7, value = "IP地址")
    private String ip;
    @ExcelProperty(index = 8, value = "注册时间", format = "yyyy-MM-dd HH:mm")
    private Date regDate;
    @ExcelProperty(index = 9, value = "申请时间", format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @ExcelProperty(index = 10, value = "充值时间", format = "yyyy-MM-dd HH:mm")
    private Date finishTime;
    @ExcelProperty(index = 12, value = "状态")
    private String status;
    @ExcelProperty(index = 11, value = "后台备注")

    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }



    public String getRechargeName() {
        return rechargeName;
    }

    public void setRechargeName(String rechargeName) {
        this.rechargeName = rechargeName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
