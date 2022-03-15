package com.hsmap.yuezhihui.pay.wechatpay;

import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PayOrder {
    public final static Logger LOGGER = LoggerFactory.getLogger(PayOrder.class);

    //公众账号id
    private static String appid = "gh_ee56abd387b2";
    //商户号
    private static String mch_id = "1328099001";
    //随机字符串
    private static String nonce_str = "asdfzxcv1697";
    //签名
    private static String sign = "";
    //商品描述
    private static String body = "杭州一日游";
    //附加数据
    private static String attach = "兽哥是";
    //商户订单号
    private static String out_trade_no = "20160331125987";
    //总金额
    private static Integer total_fee = 100;
    //终端ip
    private static String spbill_create_ip = "123.12.12.123";
    //通知地址
    private static String notify_url = "www.baidu.com";
    //交易类型
    private static String trade_type = "JSAPI";


    public static String placeOrder(String openid, Integer money) throws Exception {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        //获取prepayid
        Map<String, String> map = new HashMap<>();
        long timestamp = System.currentTimeMillis() / 1000;
        map.put("appid", WechatCodeConfig._APPID_GZH);
        map.put("mch_id", WechatCodeConfig._PAY_MCH_ID);
        map.put("nonce_str", EncryptUtils.getRandomChar(30));
        map.put("body", body);
        map.put("out_trade_no", CommonUtil.getOrderCode("CS_"));
        map.put("total_fee", String.valueOf(money));
        map.put("spbill_create_ip", spbill_create_ip);
        map.put("notify_url", notify_url);
        map.put("trade_type", "JSAPI");
        map.put("openid", openid);
        String paySign = WeiXinAlgorithm.getPayCustomSign(map, WechatCodeConfig._PAY_KEY);
        map.put("sign", paySign);
        String responseStr = HttpUtils.postDataXml(url, null, map);
        System.out.println(responseStr);
        //String prepayid=    getPrepayid(xml);
        //log.info("prepareid*****************************="+prepayid);
        return responseStr;
    }

//		public static String queryOrder() throws Exception{
//			String url = "https://api.mch.weixin.qq.com/pay/orderquery";
//			Map<String ,String > map=new HashMap<String,String>();
//			 map.put("appid", WechatCodeConfig._APPID_GZH );
//		     map.put("_PAY_MCH_ID", WechatCodeConfig._PAY_MCH_ID);
//		     map.put("out_trade_no", "20160331125987");
//		     map.put("nonce_str", "ahnsdksahndkj14");
//		     String paySign=getPayCustomSign(map,WechatCodeConfig._PAY_KEY);
//	        map.put("sign",paySign);
//	        String xml=    ArrayToXml(map);
//	        String responseStr = HttpRequestUtil.sendPost(url,xml);
//	        System.out.println(responseStr);
//	        return null;
//		}

    public static void main(String[] args) {

				/*String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx45f9d12a8d9cfb53]]></appid><_PAY_MCH_ID><![CDATA[1328099001]]></_PAY_MCH_ID><nonce_str><![CDATA[gKuXntYISfLHX6iW]]></nonce_str><sign><![CDATA[16F37BB9B6373C8D932C11BFDB8C9B5A]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2016040117191132b83eda230988282174]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
				try {
					Map<String, String> resultMap = doXMLParse(xml);
					System.out.println(resultMap.get("prepay_id"));
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
    }


    public static String sign(String content, String key) {
        String signStr = content + "&_PAY_KEY=" + key;
        return EncryptUtils.getMd5(signStr).toUpperCase();
    }



}
