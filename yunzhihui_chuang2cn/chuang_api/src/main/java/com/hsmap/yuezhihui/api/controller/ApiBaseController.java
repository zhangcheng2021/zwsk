package com.hsmap.yuezhihui.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.base.controller.BaseController;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
//import com.hsmap.yuezhihui.entity.user.UserAccount;
//import com.hsmap.yuezhihui.service.users.IUserAccountService;
import org.apache.commons.lang.StringEscapeUtils;
//import IUserAccountService;

import javax.servlet.http.HttpServletRequest;

public class ApiBaseController extends BaseController {
    final public String _USERS_ACCOUNT_SESSION = "_USERS_ACCOUNT_SEESION";

    final public String _RECOMMEND_ID_SESSION = "_RECOMMEND_ID_SESSION" ;//推荐人ID session

    public final static String _SESSION_REG_VERIFY_IMG = "REG_VERIFY_IMG_SESSION";
    public final static String _SESSION_LOGIN_VERIFY_IMG = "LOGIN_VERIFY_IMG_SESSION";
    public final static String _SESSION_SMS_VERIFY_IMG = "SMS_VERIFY_IMG_SESSION";


    public final static String _SESSION_REGISTER_SMS_CODE = "_SESSION_REGISTER_SMS_CODE";//注册短信验证码session
    public final static String _SESSION_FIND_PASSWD_SMS_CODE = "_SESSION_FIND_PASSWD_SMS_CODE";//找回密码短信验证码session
    public final static String _SESSION_LOGIN_SMS_CODE = "_SESSION_LOGIN_SMS_CODE";//登录密码短信验证码session

    public final static String _SMS_REGISTER_TEMPLATE = "ST_20200209b4c5Z3Q1";//注册验证码短信模板code
    public final static String _SMS_FIND_PASSWD_TEMPLATE = "ST_20200209F3R1T4G2";//找回密码验证码短信模板code
    public final static String _SMS_LOGIN_TEMPLATE = "ST_20200217_N6U3R0M8";//登录验证码短信模板code




    public  final int ARTICLE_NOTICE_TYPE_ID = 1;

    public final String _NOUSERS_SECRET_KEY = "RPOBACSACYAAIMSROIE9HY4WSPGDMYGH";//注册加密key



//    /****
//     * 与用户不相关验证
//     * @param request
//     * @param json
//     * @return
//     */
//    public boolean noUserAuthority(HttpServletRequest request, String json, IUserAccountService accountService){
//        LOGGER.info("转义前的字符json:{}",json);
//
//        String sign = request.getHeader("sign");
//        LOGGER.info("noUserAuthority->sign:{}",json);
//        json = getJson(json);
//
//        if(CommonUtil.isEmpty(sign)){
//            return false;
//        }
//        String _sign = accountService.getSign(json,_NOUSERS_SECRET_KEY);
//        LOGGER.info("_sign:{},sign:{}",_sign,sign);
//        if(!_sign.equals(sign)){
//            LOGGER.info("sign 不匹配");
//            return false;
//        }
//        return true;
//    }


    public String getJson(String  json){
        json = StringEscapeUtils.unescapeJava(json);
        String result = json.substring(json.indexOf("{"),json.lastIndexOf("}")+1);
        LOGGER.info("result:{}",result);
        return result;
    }


    /***
     * 获取json对象
     * @param json
     * @return
     */

    public JSONObject getJsonObject(String json){
        json= getJson(json);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject;
    }
    /***
     * 获取渠道号
     * @param request
     * @return
     */
    public String getChannel(HttpServletRequest request){
        String channel = request.getHeader("channel");
        return channel;

    }
    /***
     * 与用户相关验证
     * @param request
     * @param json
     * @param accountService
     * @return
     */
//    public boolean userAuthority(HttpServletRequest request, String json, IUserAccountService accountService){
//        String sign = request.getHeader("sign");
//        String userId = request.getHeader("userId");
//
//        LOGGER.info("转义前的字符json:{}",json);
//        json = getJson(json);
//        LOGGER.info("userAuthority-sign:{},userId:{}",sign,userId);
//        if(CommonUtil.isEmpty(sign)||CommonUtil.isEmpty(userId)){
//            LOGGER.info("验证信息为空");
//            return false;
//        }
//
//       //token
//        String token = accountService.getTokenByRedis(Integer.parseInt(userId));
//        LOGGER.info("token:{}",token);
//        String _sign = accountService.getSign(json,token);
//        LOGGER.info("_sign:{},sign:{}",_sign,sign);
//        if(!_sign.equals(sign)){
//            LOGGER.info("sign 不匹配");
//            return false;
//        }
//        UserAccount  userAccount = accountService.findById(Integer.parseInt(userId));
//        if(userAccount == null){
//            LOGGER.info("账户异常请联系客服处理");
//            return false;
//        }
//
//        if(userAccount.getStatus()!=0){
//            LOGGER.info("账户异常请联系客服处理");
//            return false;
//        }
//        return true;
//
//    }



    /***
     * 从Header中获取userId
     * @param request
     * @return
     */
    public int getUserIdByHeader(HttpServletRequest request){
        String userId = request.getHeader("userId");
        if(CommonUtil.isEmpty(userId)){
            return 0;
        }
        return Integer.parseInt(userId);
    }


    /**
     * 用户加密
     * @param passwd
     * @param token
     * @return
     */
    public String getUserPasswd(String passwd,String token) {
        return EncryptUtils.getMd5(passwd, token);
    }

    /***
     * 用户明文密码加密
     * @param passwd
     * @param token
     * @return
     */
    public String getUserPasswdByPlaintext(String passwd,String token) {
        String _passwd = EncryptUtils.getMd5(passwd);
        return getUserPasswd(_passwd,token);
    }

}
