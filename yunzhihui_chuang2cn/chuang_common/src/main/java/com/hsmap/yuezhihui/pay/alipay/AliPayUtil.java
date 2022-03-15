package com.hsmap.yuezhihui.pay.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hsmap.yuezhihui.common.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tangjx
 * @ClassName: AliPayUtil
 * @Description: 支付宝支付公共方法
 */
public class AliPayUtil {
    public final static Logger LOGGER = LoggerFactory.getLogger(AliPayUtil.class);

    /**
     * @ Author      : lijiangzhou
     * @ Date        : 2019/3/18  15:10
     * @ Description : resMap 需要返回给外部的上层参数
     */
    public static JSONObject sendAlipayReqeust(String orderNo, String fullName, BigDecimal realAmount, Map<String,Object> resMap){

        AlipayClient alipayClient = new DefaultAlipayClient(Code.URL, Code.APP_ID, Code.APP_PRIVATE_KEY,
                Code.FORMAT, Code.CHARSET, Code.ALIPAY_PUBLIC_KEY, Code.SIGN_TYPE);

        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(fullName);
        model.setSubject(fullName);
        model.setOutTradeNo(orderNo);
        model.setTimeoutExpress("1d");
        model.setTotalAmount(realAmount.setScale(2, BigDecimal.ROUND_DOWN).toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        alipayRequest.setBizModel(model);
        alipayRequest.setNotifyUrl(Code.NOTIFY_URL); // 异步回调地址
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alipayRequest);
            LOGGER.info("response.getBody:{}",response.getBody());// 就是orderString
            Map<String, Object> map = new HashMap<>();
            map.put("result", response.getBody());
            //返回外参
            if(resMap!=null && !resMap.isEmpty()){
                for(String key:resMap.keySet()){
                    map.put(key, resMap.get(key));
                }
            }
            return BaseResult.result(0,"成功",map);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            LOGGER.error("支付宝下单异常:{}",e.getErrCode());
            Map<String, Object> map = new HashMap<>();
            map.put("errCode",e.getErrCode());
            map.put("errMsg",e.getErrMsg());
            map.put("message",e.getMessage());
            return BaseResult.result(-1,"支付宝下单异常",map);
        }
    }


}
