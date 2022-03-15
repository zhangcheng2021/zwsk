package com.hsmap.yuezhihui.sms.baifen;

/**
 * 短信返回结果类
 *
 * <p>
 * create at 2015年11月17日 下午6:54:18
 * @author <a href="mailto:tangjixiong@ancun.com">tangjixiong</a>
 * @version %I%, %G%
 * @see
 */
public class SmsResult {

	private int id;
	private String msgId;
	private String status;
	private String errorCode;
	private String errorMsg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



}
