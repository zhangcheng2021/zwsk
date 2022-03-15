package com.hsmap.yuezhihui.service.sys;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.entity.sys.SysUserInfoExample;
import com.hsmap.yuezhihui.vo.account.LoginInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISysUserInfoService extends IBaseService<SysUserInfo, Integer, SysUserInfoExample> {


    /***
     * 按用户名查询
     * @param loginName
     * @return
     */
    SysUserInfo findByLoginName(String loginName);

    /***
     * 分页查询
     * @param sysUserInfo
     * @return
     */
    PageInfo<SysUserInfo> search(SysUserInfo sysUserInfo, Pageable pageable);


    List<SysUserInfo> findByUserIds(List<Integer> userIdList);



    List<SysUserInfo> findbyAll(int status);

    /***
     * 代理商用户
     * @return
     */
    List<SysUserInfo>  findByAgency();

    String getNewToken();


    //weixintoken
    String getAccessToken();

    String getShortUrl(String longUrl);

    List<SysUserInfo> findByRoleList(List<Integer> roleIdList);

    /***
     * 密码加密
     * @return
     */
     String encryptPasswd(String passwd,String token);


    /***
     * 查看登录错误次数
     * @param userId
     * @return
     */
    int getLoginErrorCount(int userId);

    void clearLoginErrorCount(int userId,int count);

    /**
     * 用户是否登录
     * @param userId
     * @return
     */
    LoginInfoVO  isLogin(int userId);

    /**
     * 设置为在线
     * @param loginInfoVO
     * @return
     */
    boolean userOnline(LoginInfoVO loginInfoVO);

    /**
     * 设置为在线
     * @param userId
     * @return
     */
    boolean userOnline(int userId, String loginName, HttpServletRequest request);

    /***
     * 设置离线
     * @param userId
     * @return
     */
    boolean userOffline(int userId);

    /**
     * 登录用户列表
     * @return
     */
    List<LoginInfoVO> onlineList();

}
