package com.hsmap.yuezhihui.appDemoTest;

import com.hsmap.yuezhihui.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class YouTest {

    static String url = "http://m.didijiayou001.com/";

    public static void main(String[] args) {

//        YouTest.testCardDetail();
//        YouTest.testProduct();
//        YouTest.testGetOilCity();
        YouTest.testPorductList();

    }



    private static void testPorductList(){
        String _url = url +"/product/getPorductList.dos";
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("version","1.1.1");
        paramMap.put("type",9);
        paramMap.put("channel",2);
//        headerMap.put("userId",String.valueOf(userId));
//        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = HttpUtils.sendform(_url, paramMap);
        System.out.println("result:"+result);
    }

    private static void testGetOilCity(){
        String _url = url +"/member/getOilCity.dos";
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("version","1.1.1");
        paramMap.put("channel",1);
        paramMap.put("city","浙江");
        paramMap.put("type",2);
        paramMap.put("token","7817be71-dfa6-4852-bd1b-5500b8cc7da8");
        paramMap.put("uid",828);
//        headerMap.put("userId",String.valueOf(userId));
//        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = HttpUtils.sendform(_url, paramMap);
        System.out.println("result:"+result);
    }

    private static void testProduct(){
        String _url = url+"/product/getPorductList.dos";
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("version","1.1.1");
        paramMap.put("channel",1);
        paramMap.put("type",1);
        paramMap.put("token","7817be71-dfa6-4852-bd1b-5500b8cc7da8");
        paramMap.put("uid",828);
//        headerMap.put("userId",String.valueOf(userId));
//        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = HttpUtils.sendform(_url, paramMap);
        System.out.println("result:"+result);
    }
    private static void testOrders(){
        String _url = url+"/member/myOrders.dos";
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("version","1.1.1");
        paramMap.put("channel",1);
        paramMap.put("type",2);
        paramMap.put("token","7817be71-dfa6-4852-bd1b-5500b8cc7da8");
        paramMap.put("uid",828);
//        headerMap.put("userId",String.valueOf(userId));
//        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = HttpUtils.sendform(_url, paramMap);
        System.out.println("result:"+result);
    }


    private static void testCardDetail(){
        String _url = url+"member/fuelCardDetail.dos";
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("version","1.1.1");
        paramMap.put("channel",1);
        paramMap.put("fuelCardId",2017);
        paramMap.put("token","7817be71-dfa6-4852-bd1b-5500b8cc7da8");
        paramMap.put("uid",828);
//        headerMap.put("userId",String.valueOf(userId));
//        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = HttpUtils.sendform(_url, paramMap);
        System.out.println("result:"+result);
    }




}
