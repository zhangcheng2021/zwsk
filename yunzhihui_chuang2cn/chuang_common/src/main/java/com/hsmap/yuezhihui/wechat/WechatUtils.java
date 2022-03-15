package com.hsmap.yuezhihui.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.wechat.resultBean.JscodeSessionBean;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具类
 */

public class WechatUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
//
      String _APPID_ = "wxcc69e6d1489f0b61";
      String _SECRET_ ="7fe1e19fffe49c91b84a0f67837c7673";

    public WechatUtils(String appid,String secret){
        if(!CommonUtil.isEmpty(appid)) {
            _APPID_ = appid;
        }
        if(!CommonUtil.isEmpty(secret)) {
            _SECRET_ = secret;
        }
    }




    /***
     * 获取Access_Token
     * @return
     */
    public String getAccessToken() {
        String _url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String url = String.format(_url,_APPID_,_SECRET_);
        LOGGER.info("url:{}",url);
        String json = HttpUtils.getData(url,null);
        LOGGER.info("result json:{}",json);
        JSONObject jsonObject=JSON.parseObject(json);
        String accessToken =  jsonObject.getString("access_token");
        LOGGER.info("accessToken:{}",accessToken);
        if (CommonUtil.isEmpty(accessToken)) {
            return "";
        }
        return accessToken;
    }


    /***
     * 登录凭证校验
     * @param jsCode
     * @return
     */
    public JscodeSessionBean code2Session(String jsCode){


        String _url =" https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        String url = String.format(_url.trim(),_APPID_,_SECRET_,jsCode);
        LOGGER.info("code2Session,url:{},jscode:{}",url,jsCode);
        String json = HttpUtils.getData(url,null);
        LOGGER.info("result json:{}",json);

        JSONObject jsonObject=JSON.parseObject(json);

        JscodeSessionBean jscodeSessionBean = new JscodeSessionBean();
//        ;
        jscodeSessionBean.setTime(new Date().getTime());
        if(jsonObject.get("openid")!=null){
            LOGGER.info("请求成功");
            String openid  =jsonObject.getString("openid");
            String sessionKey  = jsonObject.getString("session_key");
            String unionid  = jsonObject.getString("unionid");
            jscodeSessionBean.setOpenid(openid);
            jscodeSessionBean.setSessionKey(sessionKey);
            jscodeSessionBean.setUnionid(unionid);
            LOGGER.info("openid:{},session_key:{},unionid:{}",openid,sessionKey,unionid);
        }else{
            int code  = jsonObject.getInteger("errcode");
            String msg = jsonObject.getString("errmsg");
            jscodeSessionBean.setCode(code);
            jscodeSessionBean.setErrmsg(msg);
            LOGGER.info("请求失败,code:{},errmsg:{}",code,msg);
        }

        return jscodeSessionBean;
    }

    public String getShorturl(String accessToken,String longUrl) {
        String _url ="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=%s";
        String url = String.format(_url,accessToken).trim();
        LOGGER.info("url:{}",url);

        Map<String,Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("action","long2short");
        map.put("long_url",longUrl);
        String postResult = HttpUtils.postDataJson(url, map);
        LOGGER.info("postResult:{}",postResult);
        JSONObject jsonObject=JSON.parseObject(postResult);
        int errcode = jsonObject.getInteger("errcode");
        if (errcode != 0) {
            return "";
        }
        String short_url = jsonObject.getString("short_url");
        LOGGER.info("short_url:{}",short_url);
        return short_url;

    }



    public static void main(String[] args) {

        WechatUtils wechatUtils =new WechatUtils(null,null);
        JscodeSessionBean bean = wechatUtils.code2Session("033qzo2d2W0X0I0laN4d2K6f2d2qzo2M");
        LOGGER.info("json:{}",JSON.toJSONString(bean));
//       String accessToken =  WechatUtils.getAccessToken(_APPID_,_SECRET_);

        //String accessToken = "31_Hv3a9UH8L-veXbksqNnbYbiRqci5U6pDyZQCRBHJOR8p0hkb460clNIEljyvnVE3WZfAyqCDBxZ4jM4sG_dq2AuDnTfF-qQTf1mvlOdwE_34uZ9kFkNl5xsbLTglpF3v5UKpVQ2Lyqlt7aEnQKPgAJAWQV";
//       LOGGER.info("accessToken:{}",accessToken);
       //String short_url = getShorturl(accessToken,"http://a113.21.cn/getuserid/pre/B1F40733D44A148C7C0A14C0BF284FF5E8D112D5CDD65054D9642B34BB9278F9B8BB88BE78C15DD2FBBFD29129E2595D");
//       LOGGER.info("url:{}",short_url);

    }
}
