package com.hsmap.yuezhihui.sms.liasmart;

import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LiasmartSendSms {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LiasmartSendSms.class);
    final static String _URL=  "http://api.liasmart.com/api/SendSMS";
    final static String _API_ID = "API6480189711";
    final static String _API_PASSWORD = "you88660";
    final static String _SMS_TYPE = "T";
    final static String _ENCODING = "U";
    final static String _SENDER_ID = "Liasmart";
    public static void send(String mobile,String msg){
        LOGGER.info("LiasmartSendSms send mobile:{},msg:{}",mobile,msg);
        Map<String,Object> map =new HashMap<>();
        map.put("api_id",_API_ID);
        map.put("api_password",_API_PASSWORD);
        map.put("sms_type",_SMS_TYPE);
        map.put("encoding",_ENCODING);
        map.put("sender_id",_SENDER_ID);
        map.put("phonenumber",mobile);
        map.put("textmessage",msg);
        String result = HttpUtils.postDataJson(_URL,map);
        LOGGER.info("result:{}",result);

    }

    public static void main(String[] args) {
        LiasmartSendSms.send("2348109288157","测试短信，验证码为1234");
    }
}
