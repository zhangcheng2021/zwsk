package com.hsmap.yuezhihui.test.users;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class UsersTest extends BaseTest {

    public static void main(String[] args) {
//        String token = "m5C4d4y8_5948986";
//        int userId = 1;
        UsersTest usersTest = new UsersTest();
        usersTest.testSmsCode("15010339151", 3);
        ;
//        usersTest.testRegister("15010339151","1234","无心恋战");
//        usersTest.testLogin("15010339151","abcd1234");
//        usersTest.testInfo(userId,token);
//        usersTest.testcancel(userId,token);
        usersTest.testCodeLogin("15010339151", "1234");
    }

    public String testSmsCode(String mobile, int type) {
        String url = baseUrl + "/sms/getSmsCode";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mobile", mobile);
        paramMap.put("type", type + "");
        paramMap.put("times", getTimes());

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), _key));

        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testRegister(String mobile, String smsCode, String nickName) {
        String url = baseUrl + "/register/register";

        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("mobile", mobile);
        paramMap.put("smsCode", smsCode);
        paramMap.put("nickName", nickName);
        paramMap.put("passwd", EncryptUtils.getMd5("abcd1234"));
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), _key));
        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;

    }


    public String testLogin(String mobile, String passwd) {
        String url = baseUrl + "/login/index";

        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("loginName", mobile);
        paramMap.put("passwd", EncryptUtils.getMd5(passwd));
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), _key));
        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testCodeLogin(String mobile, String code) {
        String url = baseUrl + "/login/codeLogin";

        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("loginName", mobile);
        paramMap.put("code", code);
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), _key));
        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testInfo(int userId, String token) {

        String url = baseUrl + "/users/info";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), token));
        headerMap.put("userId", userId + "");

        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }


    public String testLoginOut(int userId, String token) {
        String url = baseUrl + "/login/out";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), token));
        headerMap.put("userId", userId + "");

        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testcancel(int userId, String token) {
        String url = baseUrl + "/users/cancel";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("sign", getSign(JSON.toJSONString(paramMap), token));
        headerMap.put("userId", userId + "");

        String result = HttpUtils.postDataJson(url, headerMap, paramMap);
        LOGGER.info("result:" + result);
        return result;
    }


}
