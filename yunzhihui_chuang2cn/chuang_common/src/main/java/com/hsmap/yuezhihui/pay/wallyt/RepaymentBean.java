package com.hsmap.yuezhihui.pay.wallyt;

/**
 * 通知状态bean
 *
 */
public class RepaymentBean {
    String originalMsgId;
    String partnerId;
    int trxAmount;
    String trxDate;
    String trxStatus;
    String currency;
    String authorizationCode;
    String repayChannel;

    public String getOriginalMsgId() {
        return originalMsgId;
    }

    public void setOriginalMsgId(String originalMsgId) {
        this.originalMsgId = originalMsgId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public int getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(int trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getRepayChannel() {
        return repayChannel;
    }

    public void setRepayChannel(String repayChannel) {
        this.repayChannel = repayChannel;
    }
//
//    public String getAttach() {
//        return attach;
//    }
//
//    public void setAttach(String attach) {
//        this.attach = attach;
//    }
}
