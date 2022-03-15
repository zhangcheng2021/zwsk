package com.hsmap.yuezhihui.test.goods;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class TypeTest  extends BaseTest {

    public static void main(String[] args) {
        TypeTest typeTest= new TypeTest();
        typeTest.testList();


    }

    public String testList(){
        String url = baseUrl+"/type/list";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());


        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }
}
