package com.hsmap.yuezhihui.sms.imfs;

import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/***
 * 巴西短信
 */
public class SkylinelabsSms {
     static final Logger LOGGER = LoggerFactory.getLogger(SkylinelabsSms.class);

    private static String _ACCOUNT = "cs_cum3sw";
    private static String _PASSWD = "CQVkLuHn";
    private static String _base_url = "http://sms.skylinelabs.cc:20003/";

    /**
     * 查询余额
     */
    public static void getBalance(){
        String url  = "getbalanceV2?account=%s&sign=%s&datetime=%s";

        url = getUrl(url);
        LOGGER.info("url:{}",url);
        String result = HttpUtils.getData(url,null);
        LOGGER.info("getBalance result:{}",result);

    }

    /**
     * 短信发送
     * @param numbers 手机号码
     * @param msg 内容
     * @return
     */
    public static String sendsms(String senderid,String numbers,String msg){
        String url = "sendsmsV2?account=%s&sign=%s&datetime=%s";
        url = getUrl(url);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("senderid",senderid);
        paramMap.put("numbers",numbers);
        paramMap.put("content",msg);
        String result = HttpUtils.postDataJson(url,paramMap);
        LOGGER.info("sendsms result:{}",result);
        return result;
    }


    public static String getreport(String ids){
        String url = "getreportV2?account=%s&sign=%s&datetime=%s&ids=";
        url = getUrl(url)+ids;
        String result = HttpUtils.getData(url,null);
        LOGGER.info("getBalance result:{}",result);
        return result;
    }

    public static String batchsendsms(){
        return "";
    }

    public static String getsentrcd(){
        return "";
    }

    public static void main(String[] args) {
        //getBalance();
        String ids = CommonUtil.getDateStringByPattern("yyMMddHHmmss")+"_"+ EncryptUtils.getRandomNumber(6);
        LOGGER.info("ids:{}",ids);
        sendsms(ids,"5511999091890","test is code:19282");


    }
    private static String getUrl(String url){
        String times = getTime();
        String sign  = sign(times);
        url = _base_url+String.format(url, _ACCOUNT,sign,times);

        return url ;

    }

    /***
     * 当前时间
     * @return
     */
    private static String getTime(){
        return CommonUtil.getDateStringByPattern("yyyyMMddHHmmss");
    }
    private static String sign(String times){
        String str = String.format("%s%s%s", _ACCOUNT, _PASSWD,times);
        LOGGER.info("str:{}",str);
        String sign = EncryptUtils.getMd5(str);
        LOGGER.info("sign:{}",sign);
        return sign;
    }





}
