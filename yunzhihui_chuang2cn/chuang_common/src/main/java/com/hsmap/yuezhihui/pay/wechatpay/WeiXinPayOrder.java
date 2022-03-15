package com.hsmap.yuezhihui.pay.wechatpay;

import com.hsmap.yuezhihui.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 微信下单
 *
 * @author Administrator
 *
 */
public class WeiXinPayOrder {

	/**
	 * 封装下单
	 *
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public static String placeOrder(String openid, String total_fee, String body, String out_trade_no,String tradeType,String appid,String mch_id,String mch_key) throws Exception {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// 获取prepayid
		Map<String, String> map = new HashMap<>();
//		}
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("nonce_str", WeiXinAlgorithm.getRandomString(30));
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("total_fee", total_fee);
		map.put("spbill_create_ip", WeiXinAlgorithm.getIp());
		map.put("notify_url", WechatCodeConfig._NOTIFY_URL);
		map.put("trade_type", tradeType);
		if("JSAPI".equals(tradeType)){
			map.put("openid", openid);
		}
		String paySign = WeiXinAlgorithm.getPayCustomSign(map, mch_key);
		map.put("sign", paySign);
		String responseStr = HttpUtils.postDataXml(url,null,map);
		return responseStr;
	}



	public static void main(String[] args) {

	}

}
