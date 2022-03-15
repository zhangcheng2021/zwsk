package com.hsmap.yuezhihui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HttpRequestUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);
    /**
     * 获取IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }


    /***
     * 获取session
     * @param httpSession
     * @param attribute
     * @return
     */
    public static Object getSession(HttpSession httpSession, String attribute) {
        Object sessionAttribute = httpSession.getAttribute(attribute);
        return sessionAttribute;
    }

    /***
     * 设置session
     * @param httpsession
     * @param name
     * @param o
     * @return
     */
    public static  boolean setSession(HttpSession httpsession, String name, Object o) {
        try {
            //过期时间
            httpsession.setMaxInactiveInterval(3*60*60);
            httpsession.setAttribute(name, o);
            LOGGER.info("session name :{}", name);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 删除session
     *
     * @param httpSession
     * @param name
     * @return
     */
    public  static boolean removeSession(HttpSession httpSession, String name) {
        try {
            httpSession.removeAttribute(name);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
