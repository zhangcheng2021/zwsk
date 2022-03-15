package com.hsmap.yuezhihui.sms.baifen;

import java.util.Date;

/**
 * Created by tangjixiong on 2018/4/17.
 */
public class SmsRecord {

    String telphone;
    String content;
    int sendCount;
    int status;
    Date createTime;
    String msgId;
    Date sendTime;
    String resultCode;
    String resultMsg;
    int rptStatus;
    String rptCode;
    String rptMsg;
    Date rptTime;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getRptStatus() {
        return rptStatus;
    }

    public void setRptStatus(int rptStatus) {
        this.rptStatus = rptStatus;
    }

    public String getRptCode() {
        return rptCode;
    }

    public void setRptCode(String rptCode) {
        this.rptCode = rptCode;
    }

    public String getRptMsg() {
        return rptMsg;
    }

    public void setRptMsg(String rptMsg) {
        this.rptMsg = rptMsg;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }
}
