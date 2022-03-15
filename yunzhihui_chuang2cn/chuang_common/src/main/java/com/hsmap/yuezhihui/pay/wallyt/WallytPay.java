package com.hsmap.yuezhihui.pay.wallyt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 信贷科技支付接口 betloan
 */
public class WallytPay {
    public final static Logger LOGGER = LoggerFactory.getLogger(WallytPay.class);

    private static final String _BASE_URL = "https://finance-api-uat.wepayez.com";
    private static final String _PARTNER_ID = "100580000018";//商户号 PartnerName： 越智汇·创管家
    private String _CALLBACK_URL = "";//返回URL
    private String _NOTIFY_URL = "";//通知URL
    //    private static final String _NOTIFY_URL= "http://test.test.test";//通知URL
    private static final String _PUBLIC_SIGN_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnKU75TI5pIpJjPvLzV6cq57UdJfnwSYINZBUh96l2FKxgOfej6EvBL9NlTmmOdUov2cm2PBbj3n0me5Cu17tau8glQbimOH7v/yBbHLaDRQdDK9her9XmzVsKFcdqehGA1PsO6rEqeiX0QHkkVlI+cGjvYyRbryvsd+JBJqnpWjOcX342D3Z6S8MAF4Rbd4agoa7Z/xvryDDqMZLNJHCNqZDIDYx6uGEMLm0+tGVWuQIFmsX10U83FAg3+Q8BzRVGHCXxXLvh0Wxf/kR39IGjOX8ADVQCM0KDLDbtwgEeye3Na6FwgEI/ke+tCfwmWjTiHvU4Mf17xSkKPk+919MzwIDAQAB";
    private static final String _PUBLIC_ENCRYPTION_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbF2gvAfaKYB+Kom3CLeijBgbYXeoRiDnPNeIQmwcCneqZcx8T2UAh5EAOcZbQp1tM6wq7iTMUHQmQbwvFPRq2y7i7GqjWQ45fKxrXQoLgXYHWnMLRQTk3rPfw7jj52EhP9H6IrdJv05hTrML4nRe7BsVu/GOQzjYBERtfY7dwsXlcs1uikjypBNPIDVNfWunmvaokbm4lmIoBqiSf7iw1MaR477Q5sXP9xkpZdGoJPfVurmCBa3J1iMmoUUVaHCUXD3K9IsFWsWjH/LORqKFbhTPrsqxQNqeQDW6jFjO3JmO5gMbGBuq1tC6yskF27/mDmfyle/n2fJtahLvUzJjQIDAQAB";
    private static final String _PRIVATE_SIGN_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpRsPfbhGj842dIWz8fR0i9G/gipFPe+SnKYIdWTVTrMyo2MQQ3ra2Sp5SN4zq8QPnGSaXw5cTFauJ2QtVFaoNxO8FnCQONo9okzJpY49LVM2POa9UjA8qR/HTDPQwqOiXsY3IhVUQJ9rSXK/WZBYnq3Ln+msRtOMo5/GgcxLNTUqEfOnSdBDelKnHXMSkGA8qBRTRm77zOlV4lp9lR4FTHMgtSft12bSxLIbOfaDJsV3tysJgon8xPsq6Si60l/MR/zpWVchWiQaODWZaRvdGFKInxcf7K5iXf/AFLklPTRGbQI2PKjDnw4L+txDJQJJCrHTHcGXY46dcgDpFULGTAgMBAAECggEAGODtqMjkjtQc3BstiW5Z+ekzCC1Vbfkngen31Zhm3gVnjulM563T7LbKzgqcC2ugxzZLFcMJUSS+MWRU5gxpEdz/xDNOmUgri5MWtaaMSOYN0B8KRcLP98bbI8mjmgM5LBJMZEw0VEuHPaOhtEDBgNMR8EY9tjx/pmNwf8Rp3wJfz9F79qkcfAFTV7rYhfueM+6zPzHwydrG4lE3FovMzfpU0NCrMmUMgf32gEuBznvQIP3v42v0SyojISp/WZU6lyB3QMxkSnP+9r9Cs7dGO4D7xg3sWM7sjWni4uME/fVDKJdPgiUGY33N+XDjz93vsB5a2JVFEpGnCCXPhnqbAQKBgQDaBGM+JhN5aQdqHAb6t+SBbh3G6/owVBjJYKep4arUQAo24OJ8PAG7Yp9AUuvzpHM8oIiQptYLKyg8ZoNwPMqB7TYn7sxsBbhIcHiEQNQs+h5NRXLJNeQOLSwF4HSSAHO3tvTSqsMs4dUF6DqC3f3epUxkz29YE7xFbjeaPzkIZwKBgQDGxIfsGEF3ARd41lVbVfp1gboJfOHFKk4tw/GvkSSqsDmdwnpQXfu5VRPahAVJzADuW1fbyhGQU4ZxHCG7zttdQPu4N8/RE8jWfpXoZidBt8ii8WAdNavBy4npzdFSEp3JXTatdUq48/WtzIX7nPfendo+IvOCFH8naUO+UKrB9QKBgHRg8zkxyiN9VV/nJjKgB3Kv/WU6gotZ2QFmfxnIRRCYPtucoEoBHkuREcGcgifFGV67syrEZG4B07CqaG95hABnSAl5B9aCFyVUJIE0ug5oVdSZxfRyuBJ5Oo7IinGFnD/tgjMSxK0owDYvCUzig6peQrFtqdoybsYBV90W8rLxAoGAKC4SS+ebVDMkS4kIOC5pMtfGg6R6/yBOLXIuqQ7uGpMFEp79msFk4VIRcVq+tuxGQ+izGfr/Ee3Qf4zwRMbMEU3RlKuzltApCTMNmq0Lwn0kUcv5Ye43ijVa66hvZ+fbfZkaNHZBZKHsR/XQH8zbTmUaePUXxHPgQFdHGnG0EwECgYEAiuUoD02l8A7n290h3jLZECZpgakGu25E0q/teKv3VyKQohv4fAdga8/hxyF1JieF/kdOkmlbEAzroPvyYh9OqKjs2W7A68PgdlzQCkGGMUY3jDvNbivGUTbwTa60CNyA1rByuZHRBN1DS9PAbA6WxPhPDLMTwF2M+naX66UhH8U=";

    public WallytPay(String callbackUrl, String notifyUrl) {
        _CALLBACK_URL = callbackUrl;
        _NOTIFY_URL = notifyUrl;

    }

    public static void main(String[] args) {
//        String codeStr = "iJ8co5kp4mJrAvVUkfp1QOzTNawAYO9A+n15Rj6FKDQXMARCqsthfMkqETq+Ng2YpTLXG+Jet1Z5xOlO+Wtl49gAY8APWHXbJvyKJotKTJeAIpuFNfDu29uaZnPtMyTf3XwPADtQ115/KYx+Gp5n6zHK+uLjEeQFRM+Sw5FQwwkTxPD6UDpj4NFirWViYc0lXGgBY+g+AQrpXn0Gfx/+6UPWsiKrWKss6NfZHpOatqc7w58pk8C7rbvzmYShZey6cw4AymD9ZxxDK0qZjijxTKnePsecTY7fa2TxEIRPdMWuhrr4Hdpx4GIqeh49vCDFlSu6P7YFOwP6Naet8P+urg==";
//
        WallytPay wallytPay = new WallytPay("http://152.32.143.55:8082/api/callback/wallytCallback", "http://152.32.143.55:8082/api/callback/wallytNotifyUrl");
//        ResponseBean bean = wallytPay.fullCharge("L_20210401223655_d4xq3L101", "1234567@qq.com", "40", wallytPay.getAuthorizationCode(codeStr));

//        ResponseBean bean =  wallytPay.h5Retrieving(CommonUtil.getOrderCode("PAY_"),"15010339151@139.com","1.00");
//        LOGGER.info("bean:{}",JSONObject.toJSONString(bean));
//        WallytPay wallytPay
        RepaymentBean bean  = wallytPay.query("PAY_20210413103144_X7i9kyS5");
        LOGGER.info("bean:{}",JSONObject.toJSONString(bean));

    }

    /**
     * H5 还款地址获取
     */
    public ResponseBean h5Retrieving(String msgId, String email, String trxAmount) {
        String url = _BASE_URL + "/v2/repay/link/retrieving";
        String h5json = geth5RetrievingJson(msgId, email, trxAmount).toJSONString();
        ResponseBean responseBean = payPostByJson(url, h5json);
        return responseBean;
    }

    /**
     * 授权全部金额卡还款
     */
    public ResponseBean fullCharge(String msgId, String email, String trxAmount, String authorizationCode) {
        String url = _BASE_URL + "/v2/repay/card/fullCharge";
        String jsonStr = getFullChargeJson(msgId, email, trxAmount, authorizationCode).toJSONString();
        ResponseBean responseBean = payPostByJson(url, jsonStr);
        return responseBean;
    }


    public RepaymentBean query(String orderCode) {
        String url = _BASE_URL + "/v2/repay/status/query";
        JSONObject requestJson = new JSONObject();
        requestJson.put("msgId", CommonUtil.getOrderCode("Q_"));
        requestJson.put("reqDateTime", CommonUtil.getDateStringByPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
        requestJson.put("originalMsgId", orderCode);
        requestJson.put("partnerId", _PARTNER_ID);

        String result  =post(url,requestJson.toJSONString());
        LOGGER.info("result:{}",result);
       // String status
        JSONObject resultJson =JSONObject.parseObject(result);
        JSONObject responseJson = resultJson.getJSONObject("response");
        String code = responseJson.getString("code");
        if(!"200".equals(code)){
            return null;
        }
        //PENDING SUCCESS
        String status = responseJson.getString("trxStatus");
        if("SUCCESS".equals(status)){
            RepaymentBean bean = JSONObject.parseObject(responseJson.toJSONString(), RepaymentBean.class);
            String authorizatioCode = bean.getAuthorizationCode();
            bean.setAuthorizationCode(getAuthorizationCode(authorizatioCode));
            return bean;
        }
        return null;


    }

    /**
     * 回调方法
     *
     * @param repaymentJson
     * @return
     */
    public List<RepaymentBean> repaymentStatus(String repaymentJson) {
        JSONObject json = JSONObject.parseObject(repaymentJson);
        LOGGER.info("json:{}", json.toJSONString());
        String arrayStr = json.getString("request");
        LOGGER.info("arrayStr:{}", arrayStr);
        JSONArray array = JSONArray.parseArray(arrayStr);
        LOGGER.info("array:{}", array.toJSONString());
        List<RepaymentBean> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            String str = array.getString(i);
            RepaymentBean bean = JSONObject.parseObject(str, RepaymentBean.class);
            String code = bean.getAuthorizationCode();
            bean.setAuthorizationCode(getAuthorizationCode(code));
            list.add(bean);

        }
        // List<RepaymentBean> list = JSONArray.parseArray(arrayStr,RepaymentBean.class);

        return list;

    }

    /**
     * 解密AuthorizationCode
     *
     * @param code
     * @return
     */
    public String getAuthorizationCode(String code) {
        try {
            String authorization = SignUtils.decryptByRSA(code, _PRIVATE_SIGN_KEY);
            return authorization;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    private ResponseBean payPostByJson(String url, String jsonData) {
//        ResponseBean responseBean =null;
//        try {
        String result = post(url, jsonData);
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONObject responseJson = resultJson.getJSONObject("response");
        ResponseBean responseBean = JSONObject.parseObject(responseJson.toJSONString(), ResponseBean.class);
        responseBean.setResult(result);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
        return responseBean;
    }


    private String post(String url, String jsonData) {
        JSONObject json = new JSONObject();
        json.put("request", jsonData);
        String sign = SignUtils.signBySHA256WithRSA(jsonData, _PRIVATE_SIGN_KEY);
        json.put("signature", sign);
        LOGGER.info("h5json:{},signature:{},json:{}", jsonData, sign, json.toJSONString());
        String result = HttpUtils.postDataJson(url, null, json.toJSONString());
        LOGGER.info("result :{}", result);
        return result;

    }


    private JSONObject geth5RetrievingJson(String msgId, String email, String trxAmount) {
        JSONObject json = new JSONObject();
        json.put("msgId", msgId);
        json.put("partnerId", _PARTNER_ID);
        json.put("reqDateTime", CommonUtil.getDateStringByPattern("yyyy-MM-dd HH:mm:ss"));
        json.put("email", email);
        json.put("callbackUrl", _CALLBACK_URL);
        json.put("notifyUrl", _NOTIFY_URL);
        json.put("trxAmount", trxAmount);
        return json;
    }

    private JSONObject getFullChargeJson(String msgId, String email, String trxAmount, String authorizationCode) {
        JSONObject json = new JSONObject();
        json.put("msgId", msgId);
        json.put("partnerId", _PARTNER_ID);
        json.put("reqDateTime", CommonUtil.getDateStringByPattern("yyyy-MM-dd HH:mm:ss"));
        json.put("email", email);
        json.put("trxAmount", trxAmount);
        json.put("authorizationCode", authorizationCode);
        json.put("notifyUrl", _NOTIFY_URL);
        return json;
    }
}
