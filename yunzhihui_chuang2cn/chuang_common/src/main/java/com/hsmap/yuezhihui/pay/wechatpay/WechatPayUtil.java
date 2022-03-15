package com.hsmap.yuezhihui.pay.wechatpay;

import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.common.BaseResult;
import com.hsmap.yuezhihui.common.CommonUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author lijiangzhou
 * @Date 2019/3/15 14:36
 * @ClassName: WechatPayUtil
 * @Description: 微信支付公共方法
 */
public class WechatPayUtil {

	/**
	 * @ Author : lijiangzhou @ Date : 2019/3/18 15:10 @ Description : resMap
	 * 需要返回给外部的上层参数
	 */
	public static JSONObject sendWechatPayReqeuest(String orderNo, String openid, String fullName,
												   BigDecimal realAmount, String tradeType, Map<String, Object> resMap, String appid, String mch_id, String mch_key) throws Exception {
		String responseOrderStr = WeiXinPayOrder.placeOrder(openid,
				String.valueOf(realAmount.multiply(new BigDecimal(100)).intValue()), fullName,
				orderNo + "_" + getRandomStringWeixin(6), tradeType,appid,mch_id,mch_key);
		Map<String, String> wechatMap = CommonUtil.doXMLParse(responseOrderStr);
		System.out.println("微信返回wechatMap:"+wechatMap);
		wechatMap.put("orderNo", orderNo);
		wechatMap.put("signType", "MD5");
		Map<String, String> payMap = new HashMap<String, String>();
		if ("APP".equals(tradeType)) {
//			payMap.put("appid", WechatCodeConfig._APPID_APP);
			payMap.put("appid", appid);
			payMap.put("noncestr", WeiXinAlgorithm.getRandomString(30));
			payMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
			payMap.put("partnerid", mch_id);
			payMap.put("prepayid", wechatMap.get("prepay_id"));
			payMap.put("package", "Sign=WXPay");
		} else if ("JSAPI".equals(tradeType)) {
//			payMap.put("appId", WechatCodeConfig._APPID_GZH);
			payMap.put("appId", appid);
			payMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
			payMap.put("nonceStr", WeiXinAlgorithm.getRandomString(30));
			payMap.put("signType", "MD5");
			payMap.put("package", "prepay_id=" + wechatMap.get("prepay_id"));
		}

		String paySign = WeiXinAlgorithm.getPayCustomSign(payMap, mch_key);
		payMap.put("package_id", "prepay_id=" + wechatMap.get("prepay_id"));
		payMap.put("packageid", wechatMap.get("prepay_id"));

		payMap.put("mch_id", mch_id);

		payMap.put("paySign", paySign);
		payMap.put("body", wechatMap.get("body"));
		// 订单号
		payMap.put("out_trade_no", wechatMap.get("orderNo"));
		// 订单金额
		payMap.put("total_fee", wechatMap.get("total_fee"));
		Map<String, Object> map = new HashMap<>();
		map.put("wechatMap", payMap);
		// 返回外参
		if (resMap != null && !resMap.isEmpty()) {
			for (String key : resMap.keySet()) {
				map.put(key, resMap.get(key));
			}
		}
//		resMap.clear();
//		br.setMap(map);
//		br.setSuccess(true);
//
//		System.out.println("ssss:"+br.toString());
//		return br;
		return BaseResult.result(0,"成功",map);
	}

	private static String getRandomStringWeixin(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(26);// [0,62)
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

}
