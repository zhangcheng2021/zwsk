package com.hsmap.yuezhihui.test.order;

import com.hsmap.yuezhihui.http.HttpUtils;
import com.hsmap.yuezhihui.test.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderTest extends BaseTest {

    public static void main(String[] args) {
        OrderTest orderTest= new OrderTest();
        ;


//        cardTest.testDelete();
//        cardTest.testEditNumber();

//        for(int i=0;i<10;i++)
        orderTest.testCreate();
//        cardTest.testCount();
//        orderTest.testList();

    }

    public String testList(){
        String url = baseUrl+"/order/list";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("status","-1");
        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);
        return result;
    }

    public String testCreate(){
        String url = baseUrl+"/order/create";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("times",getTimes());
        paramMap.put("orderPrice","99.99");
        paramMap.put("usedIntegral","0");
        paramMap.put("usedGold","0");
        paramMap.put("goodsNumber","20");
        paramMap.put("couponId","0");
        paramMap.put("payType","2");
        paramMap.put("couponPrice","10");
        paramMap.put("actualPrice","89.99");
        paramMap.put("addressId","22");
        paramMap.put("goods",getGoodsArray());
        paramMap.put("reamrk","test");

        paramMap.put("times",getTimes());

        String result = HttpUtils.postDataJson(url,getHeader(paramMap),paramMap);
        LOGGER.info("result:"+result);

        return result;
    }

    private static List<GoodsDetail> getGoodsArray(){
        List<GoodsDetail> list = new ArrayList<>();
        list.add(new GoodsDetail(2,1));
        list.add(new GoodsDetail(1,1));
        return list;

    }


}

class  GoodsDetail{

    int goodsId;
    int goodsNumber;

    GoodsDetail(int id,int count){
        this.goodsId= id;
        this.goodsNumber = count;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
}
