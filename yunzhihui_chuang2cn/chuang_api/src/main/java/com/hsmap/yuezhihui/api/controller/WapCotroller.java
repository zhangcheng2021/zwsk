package com.hsmap.yuezhihui.api.controller;


import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sms.ISysSmsSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class WapCotroller  extends ApiBaseController {

    private final String _MODEL_NAME = "/wap/register/";
//
//    @Autowired
//    IUserAccountService userAccountService;

    @Autowired
    ISysSmsSendService smsSendService;
//    @Autowired
//    IUserAssetService userAssetService;
//    @Autowired
//    IUserInviteService userInviteService;

    @RequestMapping(value = _MODEL_NAME + "index")
    public ModelAndView index(HttpServletRequest request, String r) {

        if (!CommonUtil.isEmpty(r)) {
            setRecommendIdSession(request.getSession(),Integer.parseInt(r));
        }
        ModelAndView mv = createView(_MODEL_NAME + "index");

        return mv;
    }

    /***
     * 发送短信
     * @param request
     * @param mobile 手机号码
     * @param verifiCode 图形验证码
     * @param type 类型 0 注册 1找回密码 。。。。
     * @param index 请求次数
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "getSmsCode")
    public String getSmsCode(HttpServletRequest request, String mobile, String verifiCode, @RequestParam(name = "type", defaultValue = "0") int type, @RequestParam(name = "index", defaultValue = "1") int index) {
//        LOGGER.info("mobile:{},verifiCode:{},type:{},index:{}", mobile, verifiCode, type, index);
//        HttpSession session = request.getSession();
//
//        String _virifiCode = (String) getSession(session, _SESSION_SMS_VERIFY_IMG);
//        LOGGER.info("_virifiCode:{}",_virifiCode);
//        if (CommonUtil.isEmpty(_virifiCode)) {
//
//            return setResultError("系统错误，请刷新页面");
//        }
//        if (!_virifiCode.equals(verifiCode)) {
//            return setResultError("图形验证码不正确");
//        }
//
//        //ip
//        String ip = getIpAddr(request);
//        // userAccountService.setRedis("")
//        if (!smsSendService.verifyIp(ip)) {
//
//            return setResultError("当前网络环境有风险");
//        }
//        String smsCode = smsSendService.getSmsCode();
//        UserAccount userAccount = userAccountService.findByloginName(mobile);
//        if (type == 0) {
//
//            if (userAccount != null) {
//                LOGGER.info("手机号码已注册,mobile:{}", mobile);
//                return setResultError("手机号码已注册");
//            }
//            boolean b= smsSendService.sendSmsToAliyun( mobile, smsCode);
//            if(!b){
//                return setResultError("短信发送失败");
//            }
//            setSession(session, _SESSION_REGISTER_SMS_CODE, smsCode);
//        } else if (type == 1) {
//            if (userAccount == null) {
//                LOGGER.info("密码找回失败，用户不存在,mobile:{}", mobile);
//                return setResultError("用户不存在");
//            }
//            boolean b=  smsSendService.sendSmsToAliyun(mobile, smsCode);
//            if(!b){
//                return setResultError("短信发送失败");
//            }
//            setSession(session, _SESSION_FIND_PASSWD_SMS_CODE, smsCode);
//        } else if (type == 3) {
//            boolean b= smsSendService.sendSmsToAliyun(mobile, smsCode);
//            if(!b){
//                return setResultError("短信发送失败");
//            }
//            setSession(session, _SESSION_LOGIN_SMS_CODE, smsCode);
//        }
//        removeSession(session, _SESSION_REG_VERIFY_IMG);
        return setResult();
    }

    /***
     *
     * @param mobile
     * @param smsCode
     * @param passwd md5
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "register")
    public String register(HttpServletRequest request, String mobile, String smsCode, String passwd,String nickName) {
        LOGGER.info("register,mobile:{},smsCode:{},passwd:{}", mobile, smsCode, passwd);
        HttpSession httpSession = request.getSession();
        if (CommonUtil.isEmpty(mobile)) {
            LOGGER.info("注册失败，手机号码为空");
            return setResultError("注册手机号码不能为空");
        }

        if (CommonUtil.isEmpty(smsCode)) {
            LOGGER.info("注册失败，短信验证码为空");
            return setResultError("短信验证码不能为空");
        }

        if (CommonUtil.isEmpty(passwd)) {
            LOGGER.info("注册失败，登录密码为空");
            return setResultError("登录密码为空");
        }
        if (CommonUtil.isEmpty(nickName)) {
            LOGGER.info("注册失败，昵称为空");
            return setResultError("昵称为空");
        }
        //验证短信验证码
        String _smsCode = (String) getSession(httpSession, _SESSION_REGISTER_SMS_CODE);
        if (CommonUtil.isEmpty(_smsCode)) {
            LOGGER.info("注册失败，短信验证码在session中不存在");
            return setResultError("请先获取短信验证码");
        }
        LOGGER.info("");
        if (!smsCode.equals(_smsCode)) {
            LOGGER.info("注册失败，短信验证码不正确");
            return setResultError("短信验证码错误");
        }

        String token =createUserToken();
        String _passwd = getUserPasswd(passwd, token);
//        UserAccount userAccount = new UserAccount();
        //是否有推荐
//        UserAccount recommentUser = null;
        int recommentId = getRecommendIdSession(httpSession);
        if (recommentId > 0) {
//            recommentUser = userAccountService.getUserByRecommentCode(recommentCode);
//            recommentUser = userAccountService.findById(recommentId);
//            String sourceCode = userAccountService.getSourceCode(recommentId);
//            int level = userAccountService.getValue(sourceCode);
//            userAccount.setSourceCode(sourceCode);
//            userAccount.setName(level);
        }
//        userAccount.setLoginName(mobile);
//        userAccount.setMobile(mobile);
//        userAccount.setPasswd(_passwd);
//        userAccount.setToken(token);
//        userAccount.setRegIp(getIpAddr(request));
//        userAccount.setSource(1);
//        userAccount.setNickName(nickName);
//        userAccount.setRecommendCode(userAccountService.getRecommendCode());
//        userAccountService.save(userAccount);
//        //资产表
//        UserAsset userAsset = new UserAsset();
//        userAsset.setUserId(userAccount.getId());
//        userAsset.setUserName(userAccount.getMobile());
//        userAssetService.save(userAsset);


        //推荐表
//        if (recommentUser != null) {
//            String ip  = getIpAddr(request);
//            userInviteService.save(recommentUser,userAccount,ip,"用户注册");
//        }
        removeSession(httpSession, _SESSION_REGISTER_SMS_CODE);

        return setResult();
    }


    public int getRecommendIdSession(HttpSession httpSession) {
        int recommentId =0;
        try {
            if(getSession(httpSession, _RECOMMEND_ID_SESSION)!=null){
                recommentId = (int)getSession(httpSession, _RECOMMEND_ID_SESSION);
            }
            return recommentId;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0 ;
        }
    }

    @RequestMapping(value = _MODEL_NAME + "findpwdInput")
    public ModelAndView findpwdInput(HttpServletRequest request) {
        ModelAndView mv = createView(_MODEL_NAME+"findpwd");

        return mv;
    }

    /***
     * 注册协议
     * @param request
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "protocol")
    public ModelAndView protocol(HttpServletRequest request) {
        ModelAndView mv = createView(_MODEL_NAME+"protocol");
        return mv;

    }
    @RequestMapping(value = _MODEL_NAME + "download")
    public ModelAndView download(HttpServletRequest request) {
        ModelAndView mv = createView(_MODEL_NAME+"download");
        return mv;

    }

    public void setRecommendIdSession(HttpSession httpSession,int recommendId) {
        setSession(httpSession,_RECOMMEND_ID_SESSION,recommendId);
    }
}
