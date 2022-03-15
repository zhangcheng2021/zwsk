package com.hsmap.yuezhihui.pay.axshare;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.common.ParameterSignUtils;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AxsharePay {
    public final static Logger LOGGER = LoggerFactory.getLogger(AxsharePay.class);

    private static final String _BASE_URL = "https://sandbox.transfersmile.com";
    private static final String _MERCHANT_ID = "16141549383658990";//帐号
    private static final String _MERCHANT_KEY = "Pagsmile_sk_f82660374dfd5926ed52eeae273f5c6c9f0e1af54a450d725abb77dad53c2aba";//key
    private static final String _SOURCE_CURRENCY = "BRL";
    private static final String _ARRIVAL_CURRENCY = "BRL";
    private static final String _FEE_BEAR = "merchant";
    private static final String _NOTIFY_URL = "";

    public static void main(String[] args) {
        PayBean payBean = new PayBean();
        payBean.setAmount(0.1);
        payBean.setName("Test User Name");
        payBean.setPhone("551234567890");
        payBean.setCPF("50284414727");
//        payBean.set
        payBean.setPixType("CPF");
        payBean.setPixKey("12345678901");
        payBean.setCustomCode(CommonUtil.getOrderCode("cpf_"));
        pixPayout(payBean);
    }
    public static String pixPayout(PayBean payBean){
        String url = "/api/v1/pix/payout";
        url = _BASE_URL +url;
        Map<String,Object> paramMap =getParamMap(payBean);
        paramMap = ParameterSignUtils.sortMap(paramMap);
        LOGGER.info("json:{}", JSON.toJSONString(paramMap));
        String result = HttpUtils.postDataJson(url,getHeaderMap(paramMap),paramMap);
        return result;

    }

    private static Map<String,Object> getParamMap(PayBean payBean){
        Map<String,Object> map = new HashMap<>();
        map.put("amount",payBean.getAmount());
        map.put("source_currency",_SOURCE_CURRENCY);
        map.put("arrival_currency",_ARRIVAL_CURRENCY);
        map.put("fee_bear",_FEE_BEAR);
        map.put("name",payBean.getName());
        map.put("phone",payBean.getPhone());
        map.put("document_id",payBean.getCPF());
        map.put("pix_type",payBean.getPixType());
        map.put("pix_key",payBean.getPixKey());
        map.put("custom_code",payBean.getCustomCode());
        map.put("notify_url",_NOTIFY_URL);


//        "amount": 10,
//                "source_currency": "USD",
//                "arrival_currency": "BRL",
//                "fee_bear": "merchant",
//                "phone": "551234567890",
//                "name": "Tom Cat",
//                "email": "email@email.com",
//                "document_id": "12345678901",
//                "pix_type": "CPF",
//                "pix_key": "12345678901",
//                "custom_code": "xxx_custom_code",
//                "notify_url": "https://example.com"
        return map;
//        return map;
    }








    private  static Map<String,String> getHeaderMap(Map<String,Object> paramMap){
        Map<String,String> map = new HashMap<>();
        map.put("Content-Type","application/json");
        map.put("merchantId",_MERCHANT_ID);
        map.put("Authorization",getAuthorization(paramMap));
        return map;
    }

    private static String getAuthorization(Map<String,Object> paramMap){
        StringBuffer queryStr = new StringBuffer();
        int index = 0;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            //System.out.println(entry.getKey() + ":" + entry.getValue());
            queryStr.append(entry.getKey());
            queryStr.append("=");
            queryStr.append(entry.getValue());
            if(index++ != paramMap.size()) {
                queryStr.append("&");
            }
        }
        LOGGER.info("queryStr:{}",queryStr);
        queryStr.append(_MERCHANT_KEY);
        LOGGER.info("queryStr:{}",queryStr);
        String sign = EncryptUtils.getMd5(queryStr.toString());
        LOGGER.info("sign:{}",sign);
        return sign;
    }
}
