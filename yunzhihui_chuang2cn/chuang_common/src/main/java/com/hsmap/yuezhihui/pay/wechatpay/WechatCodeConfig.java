package com.hsmap.yuezhihui.pay.wechatpay;

/***
 * 微信支付配置，后续要放到配置文件中
 */
public class WechatCodeConfig {


	public static final String _REDIRECT_URI = "";
	public static final String _RESPONSE_TYPE = "code";
	public static final String _SCOPE = "snsapi_login";
	public static final String _STATE = "as01";
	public static final String _GRANT_TYPE = "authorization_code";
	//微信回调地址
	public static final String _NOTIFY_URL = "http://m.yhk188.com/index/weChatCallback.dos";
	public static final String _TRADE_TYPE = "JSAPI";

	public static final  String _APPID_GZH = "wxa7ced124ad5ded2d";
	public static final String _GZH_SECRET = "9a20e3e7863d09ab7a377624e214bfee";

	public static final String _APPID_APP = "wx1977779feb4e63a3";
	// 微信支付商户号
	public static final String _PAY_MCH_ID = "1580455091";
	public static final String _PAY_KEY = "ze2yBTWbZ0aQtfKRYSZyx0urA6yS4dCt";


}
