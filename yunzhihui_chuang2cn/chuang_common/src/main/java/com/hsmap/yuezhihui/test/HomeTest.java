package com.hsmap.yuezhihui.test;

import com.hsmap.yuezhihui.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class HomeTest extends  BaseTest {
    public static void main(String[] args) {
            HomeTest homeTest = new HomeTest();
            homeTest.testConfig();
    }

    public String testConfig(){
        String url =baseUrl+"/home/config";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

}
