package com.hsmap.yuezhihui.entity.sys;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class SysSmsSendLog  extends BaseEntity implements Serializable {
    private Integer id;

    private String mobile;

    private String msg;

    private Date sendTime;

    private Integer sendStatus;

    private Date rptTime;

    private Integer rtpStatus;

    private Integer templateId;

    private String remark;

    private Integer wordSize;

    private Integer msgNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public Integer getRtpStatus() {
        return rtpStatus;
    }

    public void setRtpStatus(Integer rtpStatus) {
        this.rtpStatus = rtpStatus;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getWordSize() {
        return wordSize;
    }

    public void setWordSize(Integer wordSize) {
        this.wordSize = wordSize;
    }

    public Integer getMsgNumber() {
        return msgNumber;
    }

    public void setMsgNumber(Integer msgNumber) {
        this.msgNumber = msgNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", msg=").append(msg);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", rptTime=").append(rptTime);
        sb.append(", rtpStatus=").append(rtpStatus);
        sb.append(", templateId=").append(templateId);
        sb.append(", remark=").append(remark);
        sb.append(", wordSize=").append(wordSize);
        sb.append(", msgNumber=").append(msgNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
