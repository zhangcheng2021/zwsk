package com.hsmap.yuezhihui.test.article;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class ArticleTest extends BaseTest {

    public static void main(String[] args) {
        String token = "c2k5c2N5_2653290";
        int userId = 1;

        ArticleTest test  = new ArticleTest();
//        test.testNoticeById(userId,token);
        test.testIcon();
    }


    public String testBanner(){
        String url = baseUrl+"/article/banner";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("type","0");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testIcon(){
        String url = baseUrl+"/article/icon";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("type","0");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testActivity(int userId,String token ){
        String url = baseUrl+"/article/activity";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("type","0");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }
    public String testAdvertadvert(int userId,String token ){
        String url = baseUrl+"/article/advert";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("spaceId","1");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }
    public String testNotice(int userId,String token ){
        String url = baseUrl+"/article/notice";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("spaceId","1");


        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }
    public String testNoticeById(int userId,String token ){
        String url = baseUrl+"/article/noticeById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id","10");

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }
}
