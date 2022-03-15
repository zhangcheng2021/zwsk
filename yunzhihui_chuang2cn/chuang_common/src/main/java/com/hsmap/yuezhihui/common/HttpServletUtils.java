package com.hsmap.yuezhihui.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/***
 * HttpServlet相关操作工具类
 */
public class HttpServletUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(HttpServletUtils.class);

    /***
     * 根据参数获取sign
     * @param request
     * @param token
     * @return
     */
    public static String getSignByParam(HttpServletRequest request, String token) {
        Map<String, String> parameterMap = getParameterMap(request);
        String signStr = ParameterSignUtils.getSign(parameterMap, token);
        return signStr;
    }

    public static  void resResult(HttpServletResponse response, Map<String,Object> resultMap) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(resultMap));
            writer.flush();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static  Map<String, String> getParameterMap(HttpServletRequest request) {
        Enumeration<String> pNames = request.getParameterNames();
        Map<String, String> parameterMap = new HashMap<>();
        while (pNames.hasMoreElements()) {
            String name = pNames.nextElement();
            String value = request.getParameter(name);
            parameterMap.put(name, value);
        }
        return parameterMap;
    }



    public static void main(String[] args) {
        Map<String,String> map =new HashMap<>();
        map.put("aa","1");
        map.put("bbb","");
        map.put("ccc","1");
        map.put("dd2d","2");
        map.put("2dd","2");
        map.put("dd1d","2");
        map.put("ddd","2");
        map.put("ddd","2");
        map.put("dd1d","1");
        map.put("1111","1");
        int rate = CommonUtil.totalRate(map);
        LOGGER.info("rate:{}",rate);
    }


}
