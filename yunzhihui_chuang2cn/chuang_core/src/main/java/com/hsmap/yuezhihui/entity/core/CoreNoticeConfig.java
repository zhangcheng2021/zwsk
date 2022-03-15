package com.hsmap.yuezhihui.entity.core;

/**
 *  评审须知配置
 */
public class CoreNoticeConfig {
    /**
     * 批次Id
     */
    private Integer batchId;
    /**
     * 标识  1:开启 0:未开启 -1:未配置
     */
    private Integer startFlag;
    private String startDt;
    private String endDt;
    /**
     * 开启提示
     */
    private String noticeMsg;
    /**
     * 评审须知
     */
    private String noticeContent;


    public Integer getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Integer startFlag) {
        this.startFlag = startFlag;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getNoticeMsg() {
        return noticeMsg;
    }

    public void setNoticeMsg(String noticeMsg) {
        this.noticeMsg = noticeMsg;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "CoreNoticeConfig{" +
                "batchId=" + batchId +
                ", startFlag=" + startFlag +
                ", startDt='" + startDt + '\'' +
                ", endDt='" + endDt + '\'' +
                ", noticeMsg='" + noticeMsg + '\'' +
                '}';
    }
}
