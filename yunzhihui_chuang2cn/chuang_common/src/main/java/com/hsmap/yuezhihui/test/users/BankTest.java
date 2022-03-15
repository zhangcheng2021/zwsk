package com.hsmap.yuezhihui.test.users;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class BankTest extends BaseTest {


    public static void main(String[] args) {
        BankTest bankTest  = new BankTest();
//        bankTest.testList();
//        bankTest.testAdd("招商银行","大运村运行","唐小雄","6214850104999708","13777443718","430726198800014322");
//        bankTest.testEdit(10,"招商银行1","大运村运行","唐小雄","6214850104999708","13777443718","430726198800014322");
//        bankTest.testDelete(10);
        bankTest.testFindById(11);
    }


    public  String  testList(){
        String url =baseUrl+"/bank/list";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testAdd(String bankName,String bankDeposit,String bankUserName,String bankCard,String mobile,String idcard){
        String url =baseUrl+"/bank/add";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("bankName",bankName);
        paramMap.put("bankDeposit",bankDeposit);
        paramMap.put("bankUserName",bankUserName);
        paramMap.put("bankCard",bankCard);
        paramMap.put("mobile",mobile);
        paramMap.put("idcard",idcard);
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testEdit(int id,String bankName,String bankDeposit,String bankUserName,String bankCard,String mobile,String idcard){
        String url =baseUrl+"/bank/edit";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        paramMap.put("bankName",bankName);
        paramMap.put("bankDeposit",bankDeposit);
        paramMap.put("bankUserName",bankUserName);
        paramMap.put("bankCard",bankCard);
        paramMap.put("mobile",mobile);
        paramMap.put("idcard",idcard);
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testDelete(int id){
        String url =baseUrl+"/bank/delete";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }


    public String testFindById(int id){
        String url =baseUrl+"/bank/findById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("id",id+"");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }





}
