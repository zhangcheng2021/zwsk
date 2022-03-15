package com.hsmap.yuezhihui.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "shopconfig")
public class SysConfig {

    //系统名称
    private String sysname;

    private String sysUrl;

    private String aliyunSmsKey;
    private String aliyunSmsSecret;
    private String aliyunSmsSignName;

    private String aliyunSmsSecurityTemplateCode;

    //登录拦截器不验证URL
    private String noInterceptorPath;
    //回调URL
    private String callbackPath;

    //短信帐号
    private String smsUserName;
    //短信密码
    private String smsPasswd;
    //短信签名
    private String smsSignature;

    //又拍云空间名称
    private String upyunBucketName;
    //又拍云操作员名称
    private String upyunUserName;
    //又拍云操作员密码
    private String upyunPassword;

    //又拍云-图片域名
    private String imagesDomain;

    //微信appid
    private String wechatAppid;
    //微信Secret
    private String wechatSecret;

    private String sysDomain;

    private String imagesPath;

    //推广链接
    private String promotionUrl;

    private String payCallbackUrl;
    //NOTIFY_URL
    private String payNotifyUrl;

    //上传目录
    private String uploadDir;
    //下载目录
    private String downloadDir;
    //下载目录
    private String serverProxyUrl;

    /**
     * 当前用户被分配的可视组ID字符串，
     * LoginInterceptor拦截中赋值
     * SysUserInfo.channel
     * 1.ALL 表示所有数据域
     * 2.空 无数据域
     * 3. IDS  groupID in in(ids)
     */
    private List<Integer> grantGroupIdList;
    private boolean grantGroupIsAll;

    /**
     * 是否带组数据域查询
     *
     * @return
     */
    public boolean isRequireGroupScope() {
        return !this.grantGroupIsAll;
    }

    public List<Integer> getGrantGroupIdList() {
        return this.grantGroupIdList;
    }

    public void setCurrentUserGrantGroupIds(String grantGroupIds) {
        String str = (grantGroupIds == null ? "" : grantGroupIds.trim());
        this.grantGroupIsAll = "ALL".equals(str);
        if (this.grantGroupIsAll)
            return;
        //grantGroupIdList需要每次访问都实例化一个，防止多次授权和登录后，权限越界
        grantGroupIdList = new ArrayList<>();
        if (str.length() > 0) {
            String[] tmp = str.split(",");
            for (String s : tmp) {
                if (StringUtils.isNumeric(s)) {
                    grantGroupIdList.add(Integer.parseInt(s));
                }
            }
        }
        if (grantGroupIdList.size() == 0) {
            grantGroupIdList.add(-1);//防止in报错,-1不会报错也查询不到数据
        }
    }

    /**
     * 获取专家签名图片存储目录
     *
     * @return
     */
    public String signPath() {
        return uploadDir + File.separator + "sign" + File.separator;
    }

    /**
     * 获取专家签名图片访问路径
     *
     * @return
     */
    public String signUrl() {
        return serverProxyUrl + "/sign";
    }

    public String getServerProxyUrl() {
        return serverProxyUrl;
    }

    public void setServerProxyUrl(String serverProxyUrl) {
        this.serverProxyUrl = serverProxyUrl;
    }

    public String getTempPath() {
        return uploadDir + File.separator + "tmp";
    }

    public String getTempPdfHtml() {
        return uploadDir + File.separator + "pdfhtml";
    }

    public String getTempPdf() {
        return uploadDir + File.separator + "pdf";
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }

    public String getPayCallbackUrl() {
        return payCallbackUrl;
    }

    public void setPayCallbackUrl(String payCallbackUrl) {
        this.payCallbackUrl = payCallbackUrl;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getSysDomain() {
        return sysDomain;
    }

    public void setSysDomain(String sysDomain) {
        this.sysDomain = sysDomain;
    }

    public String getSysUrl() {
        return sysUrl;
    }

    public void setSysUrl(String sysUrl) {
        this.sysUrl = sysUrl;
    }

    public String getWechatAppid() {
        return wechatAppid;
    }

    public void setWechatAppid(String wechatAppid) {
        this.wechatAppid = wechatAppid;
    }

    public String getWechatSecret() {
        return wechatSecret;
    }

    public void setWechatSecret(String wechatSecret) {
        this.wechatSecret = wechatSecret;
    }

    public String getImagesDomain() {
        return imagesDomain;
    }

    public void setImagesDomain(String imagesDomain) {
        this.imagesDomain = imagesDomain;
    }

    public String getUpyunBucketName() {
        return upyunBucketName;
    }

    public void setUpyunBucketName(String upyunBucketName) {
        this.upyunBucketName = upyunBucketName;
    }

    public String getUpyunUserName() {
        return upyunUserName;
    }

    public void setUpyunUserName(String upyunUserName) {
        this.upyunUserName = upyunUserName;
    }

    public String getUpyunPassword() {
        return upyunPassword;
    }

    public void setUpyunPassword(String upyunPassword) {
        this.upyunPassword = upyunPassword;
    }

    public String getNoInterceptorPath() {
        return noInterceptorPath;
    }

    public String getCallbackPath() {
        return callbackPath;
    }

    public void setCallbackPath(String callbackPath) {
        this.callbackPath = callbackPath;
    }

    public void setNoInterceptorPath(String noInterceptorPath) {
        this.noInterceptorPath = noInterceptorPath;
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getSmsSignature() {
        return smsSignature;
    }

    public void setSmsSignature(String smsSignature) {
        this.smsSignature = smsSignature;
    }

    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }

    public String getSmsPasswd() {
        return smsPasswd;
    }

    public void setSmsPasswd(String smsPasswd) {
        this.smsPasswd = smsPasswd;
    }

    public String getAliyunSmsKey() {
        return aliyunSmsKey;
    }

    public void setAliyunSmsKey(String aliyunSmsKey) {
        this.aliyunSmsKey = aliyunSmsKey;
    }

    public String getAliyunSmsSecret() {
        return aliyunSmsSecret;
    }

    public void setAliyunSmsSecret(String aliyunSmsSecret) {
        this.aliyunSmsSecret = aliyunSmsSecret;
    }

    public String getAliyunSmsSignName() {
        return aliyunSmsSignName;
    }

    public void setAliyunSmsSignName(String aliyunSmsSignName) {
        this.aliyunSmsSignName = aliyunSmsSignName;
    }

    public String getAliyunSmsSecurityTemplateCode() {
        return aliyunSmsSecurityTemplateCode;
    }

    public void setAliyunSmsSecurityTemplateCode(String aliyunSmsSecurityTemplateCode) {
        this.aliyunSmsSecurityTemplateCode = aliyunSmsSecurityTemplateCode;
    }
}
