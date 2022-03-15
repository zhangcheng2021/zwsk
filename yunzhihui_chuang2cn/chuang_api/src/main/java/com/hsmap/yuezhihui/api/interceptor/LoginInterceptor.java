package com.hsmap.yuezhihui.api.interceptor;

import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.common.HttpServletUtils;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysConfig config;
//    @Autowired
//    private IUserInfoService userInfoService;

    /* 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        LOGGER.info("LoginInterceptor path:" + path);
        String noInterceptorPath = config.getNoInterceptorPath();
        String callbackPath = config.getCallbackPath();
        LOGGER.info("LoginInterceptor noInterceptorPath:{},callbackPath:{}" , noInterceptorPath,callbackPath);
        if (CommonUtil.isEmpty(noInterceptorPath)) {
            LOGGER.warn("登录接口没有设置不验证URL，请注意检查");
            return true;
        }

        if(path.matches(callbackPath)){
            LOGGER.info("回调地址不需要验证！");
            return true;
        } else {//需要用户验签的事情
            LOGGER.info("=====>>>需要进行权限判定。。。");
            //不需要的拦截直接过
            LOGGER.info(">>>>>====>>不需要验证用户信息，继续访问");
            String token = Constants._PREVENT_KEY;
            String headerSign = getHeaderSign(request);
            String paramSign = HttpServletUtils.getSignByParam(request, token);
            LOGGER.info("headerSign:{},paramSign:{}", headerSign, paramSign);
            if (!paramSign.equals(headerSign)) {
                LOGGER.info("验签不通过");
                HttpServletUtils.resResult(response,getResultMap(1,"验签不通过"));
                return false;
            }
            return true;
        }
    }


    private Map<String,Object> getResultMap(int code,String msg){
        Map<String,Object > resultMap = new HashMap<>();
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    private String getHeaderSign(HttpServletRequest request) {
        String sign = request.getHeader("sign");
        return sign;
    }

}
