package com.hsmap.yuezhihui.test.users;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class AddressTest extends BaseTest {

    public static void main(String[] args) {
        AddressTest test=new AddressTest();
//        test.testList();
        test.testAdd("浙江省","杭州市","江干区","东恒大厦806","tang","13777443718","100086",0);
//        test.testEdit(22,"浙江省","杭州市","江干区","东恒大厦806","唐小宝1","13777443718","100086",1);
//        test.testDelete(22);
//        test.testFindById(23);

    }

    public  String  testList(){
        String url =baseUrl+"/address/list";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testAdd(String province,String city,String county,String address,String linkName,String mobile,String postcode,int isDefault){
        String url =baseUrl+"/address/add";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("province",province);
        paramMap.put("city",city);
        paramMap.put("county",county);
        paramMap.put("address",address);
        paramMap.put("linkName",linkName);
        paramMap.put("mobile",mobile);
        paramMap.put("postcode",postcode);
        paramMap.put("isDefault",isDefault+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testEdit(int id,String province,String city,String county,String address,String linkName,String mobile,String postcode,int isDefault){
        String url =baseUrl+"/address/edit";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");

        paramMap.put("province",province);
        paramMap.put("city",city);
        paramMap.put("county",county);
        paramMap.put("address",address);
        paramMap.put("linkName",linkName);
        paramMap.put("mobile",mobile);
        paramMap.put("postcode",postcode);
        paramMap.put("isDefault",isDefault+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testDelete(int id){
        String url =baseUrl+"/address/delete";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }


    public String testFindById(int id){
        String url =baseUrl+"/address/findById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

}
