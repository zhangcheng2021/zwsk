package com.hsmap.yuezhihui.test;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    final public Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    final public String baseUrl =  "http://m.jiayouka001.com/";
    final public String baseUrl =  "http://127.0.0.1:8888/api";
    final public String _key = "RPOBACSACYAAIMSROIE9HY4WSPGDMYGH";

    final public int userId= 1;
    final public String token = "N8F4Y5M6_0574330";
    public  String getSign(String str,String token){
        String sign = EncryptUtils.getMd5(EncryptUtils.getMd5(str), token);
        return sign;
    }

    public Map<String,String> getHeader(Map<String, Object> paramMap ){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        headerMap.put("userId",userId+"");
        return headerMap;
    }

    public String getJson(String  json){
        json = StringEscapeUtils.unescapeJava(json);
        String result = json.substring(json.indexOf("{"),json.lastIndexOf("}")+1);
        LOGGER.info("result:{}",result);
        return result;
    }

    /***
     * 当前时间戳
     * @return
     */
    public String getTimes(){
        Date date = new Date();
        return String.valueOf(date.getTime());
    }




}
