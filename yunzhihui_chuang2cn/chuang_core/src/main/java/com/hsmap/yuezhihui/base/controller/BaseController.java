package com.hsmap.yuezhihui.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.common.BaseResult;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.utils.HttpRequestUtils;
import com.hsmap.yuezhihui.base.page.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class BaseController {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final static Integer _PAGE_SIZE = 10;


    public ModelAndView createView(String tempName) {
        ModelAndView view = new ModelAndView(tempName);
        return view;
    }



    public String getUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid;

    }


    public ModelAndView forwardView(String path) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" + path);
        return mv;
    }

    public ModelAndView redirectView(String path) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + path);
        return mv;
    }

    public String setResultError() {
        return getResult(-1, "数据处理错误", null).toJSONString();
    }

    public String setResultError(String msg) {
        return getResult(-1, msg, null).toJSONString();
    }

    public String setResultError(int code, String msg) {
        return getResult(code, msg, null).toJSONString();
    }

    public String setResultError(int code, String msg, Object data) {
        return getResult(code, msg, data).toJSONString();
    }

    public String setResult() {
        return getResult(0, "成功", null).toJSONString();
    }

    public String setResult(int code, String msg, Object data) {
        return getResult(code, msg, data).toJSONString();
    }

    public String setResult(Object data) {
        return getResult(0, "成功", data).toJSONString();
    }

    public String setResult(int code, String msg) {
        return getResult(code, msg, null).toJSONString();
    }


    /***
     * 分页构建器
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Pageable getPageable(int pageNumber, int pageSize) {
        Pageable pageable = new Pageable(pageNumber, pageSize);
        return pageable;
    }

    public Pageable getPageable(int pageNumber) {
        Pageable pageable = new Pageable(pageNumber, _PAGE_SIZE);
        return pageable;
    }


    /***
     * 返回参数json格式
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public JSONObject getResult(int code, String msg, Object data) {
        return BaseResult.result(code,msg,data);
    }

    /***
     * 获取session
     * @param httpSession
     * @param attribute
     * @return
     */
    public Object getSession(HttpSession httpSession, String attribute) {
        return HttpRequestUtils.getSession(httpSession,attribute);
    }

    /***
     * 设置session
     * @param httpsession
     * @param name
     * @param o
     * @return
     */
    public boolean setSession(HttpSession httpsession, String name, Object o) {
           return HttpRequestUtils.setSession(httpsession,name,o);

    }

    /**
     * 删除session
     *
     * @param httpSession
     * @param name
     * @return
     */
    public boolean removeSession(HttpSession httpSession, String name) {
        return HttpRequestUtils.removeSession(httpSession,name);
    }

    /**
     *
     * 获取IP
     *
     * @param request
     * @return
     */
    public  String getIpAddr(HttpServletRequest request) {

        String ipAddress = HttpRequestUtils.getIpAddr(request);
        return ipAddress;
    }

    /**
     * 创建用户token
     * @return
     */
    public String createUserToken() {
        String token = EncryptUtils.getRandomChar(8) + "_" + EncryptUtils.getRandomNumber(7);
        return token;
    }


}
