package com.hsmap.yuezhihui.sms.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hsmap.yuezhihui.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSms {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    private String accessId = "LTAI4Fmp9AQECaCMaTaVKd92";
    private String accessSecret = "x8IduqZ80r2kT69VJMYaKUlqvAIQ5z";
    private String signName = "智维课堂商城";

    public SendSms(String accessKey, String secret, String signName) {
        if (!CommonUtil.isEmpty(accessKey)) {
            this.accessId = accessKey;
        }

        if (!CommonUtil.isEmpty(secret)) {
            this.accessSecret = secret;
        }

        if (!CommonUtil.isEmpty(signName)) {
            this.signName = signName;
        }
    }

    //AccessKey ID
    //	Access Key Secret
    public static void main(String[] args) {
        SendSms sendSms = new SendSms(null, null, null);
        sendSms.sendSecurityCode("SMS_187120075","13777443718", "1234");
    }


    public void sendSecurityCode(String templateCode,String mobile, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("LiasmartSendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{code:'" + code + "'}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            LOGGER.info ("aliyun sms result:{}",response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
