package com.hsmap.yuezhihui.pay.luxpag;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.Base64Utils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/***
 * 巴西支付
 * luxpag支付
 */
public class LuxpagPayUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(LuxpagPayUtils.class);
    private final static String _APP_ID = "16141549383658990";
    private final static String _SECRET_KEY = "Pagsmile_sk_f82660374dfd5926ed52eeae273f5c6c9f0e1af54a450d725abb77dad53c2aba";
    private final static String _CHANNEL = "PIX";
    private final static String _METHOD = "Boleto";

    private final static String _ORDER_CURRENCY = "BRL";
    private final static String _TRADE_TYPE = "WEB";//响应方式
    private final static String _NOTIFY_URL = "http://www.baidu.com/";//回调地址


    private final static String _BASE_URL = "https://dev-gateway.luxpag.com/";


    public static void main(String[] args) {
        String orderNo= CommonUtil.getOrderCode("L_");
        double amount=10;
        String subject="test pay";
        String content ="test pay amount 10";
        String userId ="21234";
        PayInBean payInBean  = new PayInBean();
        payInBean.setOrderNo(orderNo);
        payInBean.setAmount(amount);
        payInBean.setSubject(subject);
        payInBean.setContent(content);
        payInBean.setUserId(userId);
        //String result  =  create(payInBean);
//        LOGGER.info("result:{}",result);
        create(payInBean);
        LOGGER.info("payIn json:{}",JSONObject.toJSONString(payInBean));
    }


    public static PayInBean create(PayInBean payInBean){
        String url = _BASE_URL + "trade/create";
        Map<String, Object> paramMap = getCreateMap(payInBean);
        String result = HttpUtils.postDataJson(url, getHeaderMap(), paramMap);
        LOGGER.info("result:{}",result);
        payInBean.setResult(result);
        analysisResult(payInBean);
        return payInBean;
    }

    private static Map<String, Object> getCreateMap(PayInBean payInBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", _APP_ID);//商户后台创建并获得
        map.put("timestamp", getTimes());//yyyy-MM-dd HH:mm:ss
        map.put("out_trade_no", payInBean.getOrderNo());//商户订单号
        map.put("channel", _CHANNEL);
//        map.put("method", _METHOD);
        map.put("order_currency", _ORDER_CURRENCY);
        map.put("order_amount", payInBean.getAmount());//订单金额
        map.put("subject", payInBean.getSubject());//订单标题
        map.put("content", payInBean.getContent());//订单内容
        map.put("notify_url", _NOTIFY_URL);//回调地址
        map.put("trade_type", _TRADE_TYPE);//回调地址
        map.put("buyer_id", payInBean.getUserId());//商户的用户ID
        return map;
    }

    private static PayInBean analysisResult(PayInBean payInBean){
        String result = payInBean.getResult();
        if(CommonUtil.isEmpty(result)){
            return  payInBean;
        }
        JSONObject json = JSON.parseObject(result);
        String code = json.getString("code");
        String msg = json.getString("msg");
        payInBean.setCode(code);
        payInBean.setMsg(msg);
        if("10000".equals(code)){
            payInBean.setStatus(1);
            String tradeNo = json.getString("trade_no");
            String webUrl = json.getString("web_url");
            payInBean.setTradeNo(tradeNo);
            payInBean.setWebUrl(webUrl);
        }else{
            payInBean.setStatus(0);

        }
        return payInBean;
    }

    public static String pay(PayInBean payInBean) {
        String url = _BASE_URL + "trade/pay";
        Map<String, Object> paramMap = getPayMap(payInBean);
        String result = HttpUtils.postDataJson(url, getHeaderMap(), paramMap);
        return result;
    }


    private static Map<String, String> getHeaderMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", getAuthorization());
        return map;
    }

    /***
     * 生成订单map
     * @return
     */
    private static Map<String, Object> getPayMap(PayInBean payInBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", _APP_ID);//商户后台创建并获得
        map.put("timestamp", getTimes());//yyyy-MM-dd HH:mm:ss
        map.put("out_trade_no", payInBean.getOrderNo());//商户订单号
        map.put("channel", _CHANNEL);
        map.put("method", _METHOD);
        map.put("order_amount", payInBean.getAmount());//订单金额
        map.put("order_currency", _ORDER_CURRENCY);
        map.put("subject", payInBean.getSubject());//订单标题
        map.put("content", payInBean.getContent());//订单内容
        map.put("notify_url", _NOTIFY_URL);//回调地址
        map.put("buyer_id", payInBean.getUserId());//商户的用户ID
        map.put("trade_type", _TRADE_TYPE);//响应方式
//        map.put("customer",getPixCustomerMap(payInBean.getName(),payInBean.getDocumentId(),payInBean.getPixType(), payInBean.pixKey));
        return map;
    }

    private static Map<String,Object> getPixCustomerMap(String name,String documentId,String pixType,String pixKey){
        Map<String,Object> map  = new HashMap<>();
        map.put("name",name);
        map.put("document_id",documentId);
        map.put("pix_type",pixType);
        map.put("pix_key",pixKey);
        Map<String,String> identifyMap = new HashMap<>();
        identifyMap.put("number","11222");
        identifyMap.put("type","1");
        map.put("identify",identifyMap);
        return map;
    }

//    private

    private static String getTimes() {
        return CommonUtil.getDateStringByPattern("yyyy-MM-dd HH:mm:ss");
    }

    private static String getAuthorization() {
        String str = String.format("%s:%s", _APP_ID, _SECRET_KEY);
        String sign = "Basic "+ Base64Utils.encoder(str);
        LOGGER.info("getAuthorization,str:{},sign:{}",str,sign);
        return sign;
    }

    private static void testPay(){

        String orderNo=CommonUtil.getOrderCode("L_");
        double amount=10;
        String subject="test pay";
        String content ="test pay amount 10";
        String userId ="21234";
        PayInBean payInBean  = new PayInBean();
        payInBean.setOrderNo(orderNo);
        payInBean.setAmount(amount);
        payInBean.setSubject(subject);
        payInBean.setContent(content);
        payInBean.setUserId(userId);

//        payInBean.setName("Test User Name");
//        payInBean.setDocumentId("50284414727");
//        payInBean.setPixType("CPF");
//        payInBean.setPixKey("12345678901");
        String s =  LuxpagPayUtils.pay(payInBean);
        LOGGER.info("s:{}",s);

    }
}
