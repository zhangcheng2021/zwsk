package com.hsmap.yuezhihui.admin.controller;

import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.vo.account.LoginInfoVO;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sys.ISysMenuInfoService;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class LoginController extends AdminBaseController {

    @Autowired
    ISysUserInfoService userInfoService;

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    ISysMenuInfoService menuInfoService;

    //模块名称
    private final String _MODEL_NAME = "login";

    private final int LOGIN_ERROR_MAX_COUNT = 5;


    @RequestMapping(value = _MODEL_NAME + "/index")
    public ModelAndView index(Model model) {
//        return "index";
        Map<String, String> map = new HashMap<>();
        map.put("name", "tang");
        ModelAndView mv = createView(_MODEL_NAME + "/index");
        return mv;
    }

    /**
     * 登录
     *
     * @param loginName
     * @param passwd    md5加密后的密码
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/login")
    public String login(HttpSession httpSession, HttpServletRequest request, String loginName, String passwd, String imgCode) {

        LOGGER.info("loginName:{},passwd:{},imgCode:{}", loginName, passwd, imgCode);
        String _SESSION_REG_IMG_CODE = (String) getSession(httpSession, _SESSION_LOGIN_VERIFY_IMG);
        LOGGER.info("_SESSION_REG_IMG_CODE:{}", _SESSION_REG_IMG_CODE);
        if (CommonUtil.isEmpty(_SESSION_REG_IMG_CODE)) {
            return setResultError("验证码不正确");
        }
        if (!_SESSION_REG_IMG_CODE.equals(imgCode)) {
            return setResultError("验证码不正确");
        }
        if (CommonUtil.isEmpty(loginName)) {
            LOGGER.info("登录名为空....");
            return setResultError("登录名不能为空");
        }
        if (CommonUtil.isEmpty(passwd)) {
            LOGGER.info("密码为空....");
            return setResultError("密码不能为空");
        }
        SysUserInfo userInfo = userInfoService.findByLoginName(loginName);
        if (userInfo == null) {
            LOGGER.info("用户名{},查询为空....", loginName);
            return setResultError("用户不存在");
        }


        //状态 0 正常 1锁定 -1 删除
        if (userInfo.getStatus() == 1) {
            LOGGER.info("用户{}已锁定，请联系管理员解锁", loginName);
            return setResultError("用户已锁定，请联系管理员解锁");
        }
        if (userInfo.getStatus() != 0) {
            LOGGER.info("用户{}状态不正确，状态为{}", loginName, userInfo.getStatus());
            return setResultError("用户状态不正确");
        }
        //
//        String _passwd = EncryptUtils.getMd5(passwd, userInfo.getToken());
        String _passwd = userInfoService.encryptPasswd(passwd, userInfo.getToken());
        LOGGER.info("密码计算完成，计算值为{}", _passwd);
        if (!_passwd.equals(userInfo.getPasswd())) {
            LOGGER.info("密码不匹配,系统密码:{},登录密码：{}", userInfo.getPasswd(), _passwd);
            int loginErrorCount = userInfoService.getLoginErrorCount(userInfo.getId());
            if (loginErrorCount > LOGIN_ERROR_MAX_COUNT) {
                userInfo.setStatus(1);
                userInfoService.update(userInfo);
                return setResultError("密码错误次数过多，帐号已经锁定");
            }
            return setResultError("登录密码错误");
        }
        //是否登录
//        LoginInfoVO loginInfoVO = userInfoService.isLogin(userInfo.getId());
//        if(loginInfoVO != null){
//            LOGGER.info("用户{}已经登录，请联系管理员处理",loginName);
//            return setResultError("用户已经登录，请联系管理员处理");
//        }

        //密码错误次数清0
        userInfoService.clearLoginErrorCount(userInfo.getId(), 0);
        LOGGER.info("登录成功，准备更新用户登录日志");
        //更新登录次数和登录时间
        userInfo.setLoastIp(getIpAddr(request));
        userInfo.setLoastTime(new Date());
        userInfo.setLoginCount(userInfo.getLoginCount() + 1);
        userInfoService.update(userInfo);
        LOGGER.info("用户登录日志更新完成，准备保存session");
        //登录成功
        setSysUserInfoSession(httpSession, userInfo);
        LOGGER.info("保存session完成，准备加载用户菜单");
        //加载到redis中
        userInfoService.userOnline(userInfo.getId(), userInfo.getLoginName(), request);
        //加载用户菜单
        setRoleMenuSession(httpSession, getMenuInfoVoListByRole(userInfo.getRole()));
        LOGGER.info("用户菜单加载完成，登录完成");

        return setResult();
    }


    /**
     * 根据用户角色查询权限列表
     *
     * @param role
     * @return
     */
    private List<SysMenuInfoVO> getMenuInfoVoListByRole(int role) {
        List<SysMenuInfoVO> menuInfoVOList = menuInfoService.findVoByRoleId(role);
        List<SysMenuInfoVO> voList = new ArrayList<>();
        //删除一级菜单为空的记录
        if (menuInfoVOList == null || menuInfoVOList.size() < 1) {
            return voList;
        }
        for (SysMenuInfoVO vo : menuInfoVOList) {
            if (vo.getChildList() != null && vo.getChildList().size() > 0) {
                voList.add(vo);
            }
        }
        return voList;

    }

    /**
     * 退出登录
     *
     * @return
     */

    @RequestMapping(value = _MODEL_NAME + "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        LOGGER.info("logout log point...");
        HttpSession httpSession = request.getSession();
        LOGGER.info("logout log point..., Session is null ={}", httpSession == null);
        if (httpSession != null) {
            SysUserInfo userInfo = getSysUserInfoSession(httpSession);
            LOGGER.info("logout log point..., userInfo is null ={}", userInfo == null);
            if (userInfo != null) {
                userInfoService.userOffline(userInfo.getId());
            }
            LOGGER.info("logout log point..., userInfo offLine ");
            removeRoleMenuSession(httpSession);
            removeSysUserInfoSession(httpSession);
            LOGGER.info("logout log point..., clear all Session");
        }
        return redirectView("/login/index");
    }

}
