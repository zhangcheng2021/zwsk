package com.hsmap.yuezhihui.admin.interceptor;

import com.hsmap.yuezhihui.base.controller.BaseController;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.service.sys.ISysOperateLogService;
import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.enumcode.OperateLogTypeEnum;
import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import com.hsmap.yuezhihui.vo.account.LoginInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISysOperateLogService operateLogService;

    @Autowired
    private ISysUserInfoService sysUserInfoService;

    @Autowired
    private SysConfig config;

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
        LOGGER.info("LoginInterceptor noInterceptorPath:" + noInterceptorPath);
        if (CommonUtil.isEmpty(noInterceptorPath)) {
            LOGGER.warn("登录接口没有设置不验证URL，请注意检查");
            return true;
        }

        if (path.matches(noInterceptorPath)) {
            //不需要的拦截直接过
            //ip得到地址
            LOGGER.info(">>>>>====>>" + "有权限，继续访问");
            //保存访问信息
            return true;
        } else {
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
            LOGGER.info("=====>>>" + "需要进行权限判定。。。");
            BaseController baseController = new BaseController();
            SysUserInfo _SESSION_USER_INFO = (SysUserInfo) request.getSession().getAttribute(Constants._SESSION_USER_NAME);
            if (_SESSION_USER_INFO == null) {
                baseController.forwardView("/login/index");
                LOGGER.info("无操作权限");
                response.sendRedirect(request.getContextPath() + "/login/index");
                return false;
            }
            //是否有session
            LoginInfoVO loginInfoVO = sysUserInfoService.isLogin(_SESSION_USER_INFO.getId());
            if (loginInfoVO == null) {
                request.removeAttribute(Constants._SESSION_USER_NAME);
                baseController.forwardView("/login/index");
                LOGGER.info("无操作权限");
                response.sendRedirect(request.getContextPath() + "/login/index");
                return false;
            }
            SysUserInfo userInfo = sysUserInfoService.findById(loginInfoVO.getUserId());
            //request.setAttribute("userInfo", userInfo);
            config.setCurrentUserGrantGroupIds(userInfo.getChannel());//数据域
            sysUserInfoService.userOnline(_SESSION_USER_INFO.getId(), _SESSION_USER_INFO.getLoginName(), request);
            operateLogService.saveLog(request, "url:" + path, OperateLogTypeEnum.TYPE_URL.getType());
            LOGGER.info("授权链接通过，继续访问 ");

            return true;
        }
    }


}
