package com.hsmap.yuezhihui.pay;

import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/***
 * BufPay 个人收款
 * 网址：https://bufpay.com/
 * 帐号：ruituoedu@163.com
 * 密码：abcd1234
 *
 */
public class BufpayUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(BufpayUtils.class);
    //
    static  String _APP_ID="";
    static String _APP_SECRET = "";

    public BufpayUtils(String appid,String secret){
        _APP_ID = appid;
        _APP_SECRET = secret;
    }

    public BufpayUtils(){

    }

    public static void main(String[] args) {
        String name = "测试商品-0001";

        //BufpayUtils.pay(name,"wechat","1", CommonUtil.getRandomByCount(18),"1","Http://test.test.test","Http://test.test.test");
//        BufpayUtils.testCallBack();

    }
    /***
     * 发起付款接口
     * @param name 必填, 订单销售商品名称
     * @param payType 必填, 支付方式（可选alipay/wechat）
     * @param price 必填, 订单价格
     * @param orderId 必填, 订单号
     * @param orderUid 必填, 订单购买用户（可以为空字符串，但是建议设置为购买用户编号，便于后续对账）
     * @param returnUrl 必填, 支付成功后回调地址
     * @param notifyUrl 必填, 支付成功后前台跳转地址（可以为空字符串）
     */
    public  String pay(String name,String payType,String price,String orderId,String orderUid,String returnUrl,String notifyUrl) {
        String url  = "https://bufpay.com/api/pay/"+_APP_ID;
        String signStr  = name + payType + price + orderId + orderUid + notifyUrl + returnUrl + "" ;
        String sign = getSign(signStr);
        LOGGER.info("signStr;{},sign:{}",signStr,sign);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        paramMap.put("pay_type",payType);
        paramMap.put("price",price);
        paramMap.put("order_id",orderId);
        paramMap.put("order_uid",orderUid);
        paramMap.put("notify_url",notifyUrl);
        paramMap.put("return_url",returnUrl);
        paramMap.put("sign",sign);
        String result = HttpUtils.sendform(url,paramMap);
        LOGGER.info("reslut:{}",result);
        return result;
    }

    public  String getSign(String str) {
        return  EncryptUtils.getMd5(str+_APP_SECRET);
    }

    public  String testCallBack() {

        String url  = "http://127.0.0.1:8888/wap/callback/bufpay";
        String aoid = _APP_ID;
        String order_id = "20200309212204_n5n6e5_734336";
        String order_uid ="9c27a8c35958834a7ae52f13f6f15e92";
        String price ="4.0";
        String pay_price="4.01";
        String signStr  = aoid + order_id + order_uid + price + pay_price + "" ;
        String sign = getSign(signStr);
        LOGGER.info("signStr;{},sign:{}",signStr,sign);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("aoid",aoid);
        paramMap.put("order_id",order_id);
        paramMap.put("order_uid",order_uid);
        paramMap.put("price",price);
        paramMap.put("pay_price",pay_price);
        paramMap.put("sign",sign);
        String result = HttpUtils.sendform(url,paramMap);
        LOGGER.info("reslut:{}",result);
        return result;

    }

}
