package com.hsmap.yuezhihui.admin.controller;

import com.hsmap.yuezhihui.base.controller.BaseController;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;
import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.common.CommonUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AdminBaseController extends BaseController {


    final String _SESSION_SMS_VERIFY_IMG = "_SESSION_SMS_VERIFY_IMG";
    final String _SESSION_LOGIN_VERIFY_IMG = "_SESSION_LOGIN_VERIFY_IMG";
    final String _SESSION_REG_VERIFY_IMG = "_SESSION_REG_VERIFY_IMG";

    public ModelAndView getErrorPage(String msg) {
        String _error_templates = "/error/error_msg";
        ModelAndView mv = createView(_error_templates);
        mv.addObject("__MESSAGE", msg);
        return mv;
    }

    public ModelAndView getErrorPage2(String msg) {
        String _error_templates = "error/error_msg";
        ModelAndView mv = createView(_error_templates);
        mv.addObject("__MESSAGE", msg);
        return mv;
    }

    public ModelAndView getLoadPage(String url, String msg) {
        String _load_templates = "/error/loading";
        ModelAndView mv = createView(_load_templates);
        if (CommonUtil.isEmpty(msg)) {
            msg = "页面跳转中，请稍侯...";
        }
        mv.addObject("__MESSAGE", msg);
        if (CommonUtil.isEmpty(url)) {
            url = "";
        }
        mv.addObject("__URL", url);
        return mv;
    }

    /***
     * 获取登录用户session
     * @param httpSession
     * @return
     */
    public SysUserInfo getSysUserInfoSession(HttpSession httpSession) {
        SysUserInfo userInfo = (SysUserInfo) getSession(httpSession, Constants._SESSION_USER_NAME);
        return userInfo;
    }


    /***
     * 设置登录用户session
     * @param httpSession
     * @return
     */
    public void setSysUserInfoSession(HttpSession httpSession, SysUserInfo sysUserInfo) {
        setSession(httpSession, Constants._SESSION_USER_NAME, sysUserInfo);
    }

    /***
     * 删除用户session
     * @param httpSession
     */
    public void removeSysUserInfoSession(HttpSession httpSession) {
        removeSession(httpSession, Constants._SESSION_USER_NAME);

    }

    /***
     * 获取登录用户的菜单session
     * @param httpSession
     * @return
     */
    public List<SysMenuInfoVO> getRoleMenuSession(HttpSession httpSession) {
        List<SysMenuInfoVO> volist = (List<SysMenuInfoVO>) getSession(httpSession, Constants._SESSION_ROLE_MENU);
        return volist;
    }


    /***
     * 设置登录用户的菜单session
     * @param httpSession
     * @return
     */
    public void setRoleMenuSession(HttpSession httpSession, List<SysMenuInfoVO> list) {
        setSession(httpSession, Constants._SESSION_ROLE_MENU, list);
    }

    /***
     * 删除登录用户的菜单session
     * @param httpSession
     */
    public void removeRoleMenuSession(HttpSession httpSession) {
        removeSession(httpSession, Constants._SESSION_ROLE_MENU);

    }

    public int getSessionUserId(HttpSession httpSession) {

        SysUserInfo sessionUserInfo = getSysUserInfoSession(httpSession);
        if (sessionUserInfo != null) {
            return sessionUserInfo.getId();
        }


        return 0;
    }


    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     * @return
     */
    protected void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(percentEncodedFileName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(percentEncodedFileName);

        response.setHeader("Content-disposition", contentDispositionValue.toString());
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    private String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }

}
