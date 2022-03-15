package com.hsmap.yuezhihui.test.goods;

import com.hsmap.yuezhihui.test.BaseTest;
import com.hsmap.yuezhihui.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class InfoTest extends BaseTest {

    public static void main(String[] args) {

        InfoTest infoTest = new InfoTest();
        infoTest.testList();
//        infoTest.testFindById(2);

    }

    public String testList(){
        String url =baseUrl+"/goods/list";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("typeId",32+"");
        paramMap.put("pageNumber",1+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }


    public String testFindById(int id){
        String url =baseUrl+"/goods/findById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }


}
