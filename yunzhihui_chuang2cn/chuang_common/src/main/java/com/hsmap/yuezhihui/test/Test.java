package com.hsmap.yuezhihui.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class Test extends BaseTest {
    private final String _BASE_URL = "http://127.0.0.1:8082/api";

    public static void main(String[] args) {

//        Test test =new Test();
//        String result = test.payTestCallback();
//        System.out.println(result);

//        String str  ="\"request\":[\"{\\\"originalMsgId\\\":\\\"1614154607652\\\",\\\"partnerId\\\":\\\"300599999999\\\",\\\"trxAmount\\\":5000,\\\"trxStatus\\\":\\\"SUCCESS\\\",\\\"trxDate\\\":\\\"2021-0 1-05T08:17:43.000Z\\\",\\\"currency\\\":\\\"NGN\\\",\\\"repayChannel\\\":\\\"card\\\",\\\"attach\\\":\\\"attach of this repay order\\\",\\\"authorizationCode\\\":\\\"NUaJri+axD+DZa7fp/EePklqo9DKb7cqrYKQaECX4ewqtBbDkmuL16YYBgOFh6sEX474oezfl6wEeF2q2W4hJ+1sRTfSUzCMJDB QPGqY1pmsV/S71HVg67wfBHnAS4jZ5ErBLyWFPDv7yQOO/Ekb3ThcQtuwgmH3EjYVOc4wmuEc1tagAE2FyanVMVTexc3qJLxGbj4VnymmvQDhyFKyMXwgMIB FYJXOQwpS7hGglFNWIgEX8myQx3xhHuaYwFUrMM7YJCc61fF8wCgYOpDiXU36J9A4fFCyIrIHr4ovovEcGsm64xA8kyxoE6WFjVUFIp5AQ0VAS0x05fUK3X222w= =\\\"}\"],\"signature\":[\"DW+LZ0xCBpm54goito77j6RKVaOqoGHxCFaWkSYjHyKBlVSsroR1skwAiwYQoUvG/THvymYGuFURnTJW3jwXvMi6c/FAABMouz9FTZa1evt Ht/HmvutvLdF+VBCSDzJ+thpkS7/azmm6RYRdTZavzPxjZFHHFQBbLVSEgEk9jwfp70tapEIXVOWb6GyPf9rZg4FFfo0ma4gRkVMWNYIKpz6XXjHaJeVLYg0MBmVA 1Rm0V1FEdjauS01EDeRXrN4Iiu1NSFoyEsM+e4zg8tf61aK9hLLEtlaFceZrJVkgT5WqICawzr7ICFiQWyaV0ncSolncB0FzoZpEokRbQ/ODUw==\"]}";
//        JSONObject json = JSONObject.parseObject(str);
//        JSONArray array = json.getJSONArray("request");
    }

//    public void testGetSmsCode(String mobile ){
//        HttpUtils.sendform()
//
//
//    }

    public String payTestCallback(){
        String url = "http://127.0.0.1:8082/api/callback/wallytCallback";
        JSONObject requestJson= new JSONObject();
        JSONArray array = new JSONArray();
        Map<String,Object> map = new HashMap<>();
        map.put("originalMsgId","L_20210301020138_jGxY2Auu");
        map.put("partnerId","100530000128");
        map.put("trxAmount","500");
        map.put("trxDate", CommonUtil.getDateStringByPattern("yyyyMMddHHmmss"));
        map.put("trxStatus","SUCCESS");
        map.put("authorizationCode","L_20210301020138_jGxY2Auu");
        map.put("repayChannel","qr");
        map.put("attach","");
        array.add(map);

        requestJson.put("request",array);
        String result = HttpUtils.postDataJson(url,null,requestJson.toJSONString());
        return result;
    }

    public  String testYou() {


//        String url ="http://m.you018.com/product/getPorductList.dos";
        String url = "http://m.youjiaman8.com/index/index.dos";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobilephone", "13777443718");
        paramMap.put("sign", getSign("13777443718"));
        paramMap.put("token", "dc0537b3-ca1c-4d41-b7de-6225bd7feca2");
        paramMap.put("channel", 1);
        paramMap.put("version", "1.0.9");
//        paramMap.put("uid",654);
//        paramMap.put("uid",654);
//        paramMap.put("uid",654);

        String result = HttpUtils.sendform(url, paramMap);
        return result;
    }

    private String getSign(String str){
        String sign = EncryptUtils.getMd5(EncryptUtils.getMd5(str), "F055ECEB2640BD1BD7F0B8D25C4CC9AD");
        System.out.println("sign:"+sign);
        return sign;
    }
}
