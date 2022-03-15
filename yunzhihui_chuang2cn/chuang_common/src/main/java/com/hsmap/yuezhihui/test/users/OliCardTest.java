package com.hsmap.yuezhihui.test.users;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class OliCardTest extends BaseTest {
    public static void main(String[] args) {

        OliCardTest oliCardTest = new OliCardTest();
//        oliCardTest.testList();
//        oliCardTest.testAdd(0,"1100110033111321","13777443718");
        oliCardTest.testEdit(1,1,"1100110033111321","13777443718");
//        oliCardTest.testDelete(1);
//        oliCardTest.testFindById(1);
    }


    public String testList() {
        String url = baseUrl + "/oilCard/list";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());
        String result = HttpUtils.postDataJson(url, getHeader(paramMap), paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testAdd(int type, String cardNum, String mobile) {
        String url = baseUrl + "/oilCard/add";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());
        paramMap.put("type", type+"");
        paramMap.put("cardNum", cardNum);
        paramMap.put("mobile", mobile);
        String result = HttpUtils.postDataJson(url, getHeader(paramMap), paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testEdit(int id, int type, String cardNum, String mobile) {
        String url = baseUrl + "/oilCard/edit";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());
        paramMap.put("id", id+"");
        paramMap.put("type", type+"");
        paramMap.put("cardNum", cardNum);
        paramMap.put("mobile", mobile);
        String result = HttpUtils.postDataJson(url, getHeader(paramMap), paramMap);
        LOGGER.info("result:" + result);
        return result;
    }

    public String testDelete(int id) {
        String url = baseUrl + "/oilCard/delete";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id", id+"");
        String result = HttpUtils.postDataJson(url, getHeader(paramMap), paramMap);
        LOGGER.info("result:" + result);
        return result;
    }


    public String testFindById(int id) {
        String url = baseUrl + "/oilCard/findById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times", getTimes());
        paramMap.put("id", id+"");
        String result = HttpUtils.postDataJson(url, getHeader(paramMap), paramMap);
        LOGGER.info("result:" + result);
        return result;
    }


}
