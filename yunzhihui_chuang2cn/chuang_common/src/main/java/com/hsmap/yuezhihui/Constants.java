package com.hsmap.yuezhihui;

/***
 * 静态配置类
 */
public class Constants {

    /***
     * 异常返回默认返回值
     */
    public final static int _EXCEPTION_ERROR = -1;


    public final static String _SESSION_USER_NAME ="SYS_USER_INFO_SESSION";//用户session
    public final static String _SESSION_ROLE_MENU ="SYS_ROLE_MENU_SESSION";//用户菜单session

    public final static String _PREVENT_KEY = "48566B80BC7A7947";//非登录用户key

    public final static long _SESSION_VALID_TIME = 3*60*60;//session有效时间


    //***** redis ************/
    public final static String _REDIS_BANNER_LIST = "_REDIS_BANNER_LIST_";//banner
    public final static String _REDIS_REBATE_CONFIG_LIST = "_REDIS_REBATE_CONFIG_LIST";
    public final static String _REDIS_BRAND_LIST = "_REDIS_BRAND_LIST";
    public final static String _REDIS_GOODS_TYPE_LIST = "_REDIS_GOODS_TYPE_LIST";






    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String FIALL = "FIALL";
    /**********************对象和个体****************************/
    public static final String SESSION_USER = "loginedAgent"; // 用户对象
    public static final String SESSION_LOGINID = "sessionLoginID"; // 登录ID
    public static final String SESSION_USERID = "sessionUserID"; // 当前用户对象ID编号

    public static final String SESSION_USERNAME = "sessionUserName"; // 当前用户对象ID编号
    public static final Integer PAGE = 10; // 默认分页数
    public static final String SESSION_URL = "sessionUrl"; // 被记录的url
    public static final String SESSION_SECURITY_CODE = "sessionVerifyCode"; // 登录页验证码
    // 时间 缓存时间
    public static final int TIMEOUT = 1800;// 秒
    public static final String ON_LOGIN = "/logout.htm";
    public static final String LOGIN_OUT = "/toLogout";


    public static final String  _DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String  _DATE_FORMAT = "yyyy-MM-dd";
    public static final int  _GROUP_PRODUCT_COUNT = 30;//每组最大人数



}
