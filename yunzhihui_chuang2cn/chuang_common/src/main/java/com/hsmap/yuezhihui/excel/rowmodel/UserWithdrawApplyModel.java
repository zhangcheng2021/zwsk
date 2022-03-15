package com.hsmap.yuezhihui.excel.rowmodel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserWithdrawApplyModel  extends BaseRowModel {

    @ExcelProperty(index = 0,value = "用户id")
    private String userId;
    @ExcelProperty(index = 1,value = "用户名")
    private String userName;
    @ExcelProperty(index = 2,value = "用户类型")
    private String userSource;
    @ExcelProperty(index = 3, value = "用户余额")
    private BigDecimal availableBalance;
    @ExcelProperty(index = 4, value = "提现金额")
    private BigDecimal applyAmount;
    @ExcelProperty(index = 5, value = "手续费比例")
    private String chargeRate;
    @ExcelProperty(index = 6, value = "手续费")
    private BigDecimal chargeAmount;
    @ExcelProperty(index = 7, value = "实际打款金额")
    private BigDecimal amount;
    @ExcelProperty(index = 8, value = "帐号")
    private String bankCard;
    @ExcelProperty(index = 9, value = "银行")
    private String bankName;
    @ExcelProperty(index = 10, value = "姓名")
    private String bankUserName;
    @ExcelProperty(index = 11, value = "手机号")
    private String mobile;
    @ExcelProperty(index = 12, value = "IP地址")
    private String ip;
//    @ExcelProperty(index = 8, value = "收款方式")
//    private String payType;
//    @ExcelProperty(index = 9, value = "收款详情")
//    private String payContent;
    @ExcelProperty(index = 13, value = "注册时间", format = "yyyy-MM-dd HH:mm")
    private Date regDate;
    @ExcelProperty(index = 14, value = "申请时间", format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @ExcelProperty(index = 15, value = "提现时间", format = "yyyy-MM-dd HH:mm")
    private Date finishTime;
    @ExcelProperty(index = 17, value = "状态")
    private String status;
    @ExcelProperty(index = 16, value = "后台备注")
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(String chargeRate) {
        this.chargeRate = chargeRate;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
