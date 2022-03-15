package com.hsmap.yuezhihui.test.order;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class CardTest extends BaseTest {

    public static void main(String[] args) {
        CardTest cardTest= new CardTest();
;
        cardTest.testAdd();
//        cardTest.testDelete();
//        cardTest.testEditNumber();
        cardTest.testList();
        cardTest.testCount();
    }
    public String testList(){
        String url = baseUrl+"/card/list";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());


        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testAdd(){
        String url = baseUrl+"/card/add";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("goodsId",1+"");
        paramMap.put("goodsNumber",2+"");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }



    public String testCount(){
        String url = baseUrl+"/card/count";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testDelete(){
        String url = baseUrl+"/card/delete";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("goodsId",2+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testEditNumber(){
        String url = baseUrl+"/card/editNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());

        paramMap.put("goodsNumber",-1+"");
        paramMap.put("goodsId",2+"");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

}
