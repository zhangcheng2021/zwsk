package com.hsmap.yuezhihui.service.sys.impl;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.sys.SysRole;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.entity.sys.SysUserInfoExample;
import com.hsmap.yuezhihui.http.HttpCommon;
import com.hsmap.yuezhihui.mapper.sys.SysUserInfoMapper;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import com.hsmap.yuezhihui.utils.HttpRequestUtils;
import com.hsmap.yuezhihui.utils.redis.RedisUtil;
import com.hsmap.yuezhihui.vo.account.LoginInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysUserInfoServiceImpl extends BaseServiceImpl<SysUserInfo, Integer, SysUserInfoExample> implements ISysUserInfoService {

    @Autowired
    SysUserInfoMapper mapper;

    @Autowired
    SysConfig sysConfig;
    @Autowired
    ISysRoleService roleService;
    @Autowired
    RedisUtil redisUtil;


    private final String _SESSION_WECHAT_ACCESS_TOKEN = "_SESSION_WECHAT_ACCESS_TOKEN";//微信token

    private final String _LOGIN_ERROR_COUNT = "_LOGIN_ERROR_COUNT_";
    /***
     * 在线登录用户
     */
    private final String _LOGIN_SYS_USER_ONLINE = "_LOGIN_SYS_USER_ONLINE_";


    @Override
    public BaseMapper<SysUserInfo, Integer, SysUserInfoExample> getMapper() {
        return mapper;
    }

    @Override
    public SysUserInfo findByLoginName(String loginName) {
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<SysUserInfo> userList = find(example);
        if (userList == null || userList.size() < 1) {
            LOGGER.info("SysUserInfoServiceImpl->findByLoginName,loginName:{},返回为空", loginName);
            return null;
        }
        return userList.get(0);
    }

    @Override
    public PageInfo<SysUserInfo> search(SysUserInfo sysUserInfo, Pageable pageable) {
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();
        if (sysUserInfo.getStatus() == null) {//状态
            criteria.andStatusGreaterThanOrEqualTo(0);
        }else{
            criteria.andStatusEqualTo(sysUserInfo.getStatus());
        }
        if (!CommonUtil.isEmpty(sysUserInfo.getLoginName())) {
            criteria.andLoginNameLike("%" + sysUserInfo.getLoginName() + "%");
        }


        example.setPageSize(pageable.getPageSize());
        example.setPageNumber(pageable.getPageNumber());
        example.setOrderByClause(" id desc");
        return findByPage(example, pageable);
    }

    List<Integer> getUserIdsByList(List<SysUserInfo> list) {
        List<Integer> ids = new ArrayList<>();
        for (SysUserInfo user : list) {
            ids.add(user.getId());
        }
        return ids;
    }

    public List<SysUserInfo> findByUserIds(List<Integer> ids) {
        List<SysUserInfo> list = null;
        if (ids.isEmpty()) {
            return list;
        }
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        example.setOrderByClause(" id desc");
        list = find(example);
        return list;

    }

    @Override
    public List<SysUserInfo> findbyAll(int status) {
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();
        if (status > -1) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(" id desc");
        return find(example);
    }

    @Override
    public List<SysUserInfo> findByAgency() {
        List<SysRole> roleList = roleService.findByType(roleService.getAgencyTypeValue());
        List<Integer> roleIdList = roleService.getIdList(roleList);
        List<SysUserInfo> userInfoList = findByRoleList(roleIdList);
        return userInfoList;
    }


    public String getNewToken() {

        return EncryptUtils.getRandomChar(6) + "_" + EncryptUtils.getRandomNumber(5);
    }

    @Override
    public String getAccessToken() {
        String accessToken = (String) getRedis(_SESSION_WECHAT_ACCESS_TOKEN);
        LOGGER.info("accessToken:{}", accessToken);
        if (CommonUtil.isEmpty(accessToken)) {
            LOGGER.info("appid:{}", sysConfig.getWechatAppid());
            LOGGER.info("Secret:{}", sysConfig.getWechatSecret());
            //accessToken = WechatUtils.getAccessToken(sysConfig.getWechatAppid(),sysConfig.getWechatSecret());
            LOGGER.info("accessToken:{}", accessToken);
            if (!CommonUtil.isEmpty(accessToken)) {
                setRedis(_SESSION_WECHAT_ACCESS_TOKEN, accessToken, (long) 7200);
            }
            LOGGER.info("AccessToken is error");
            return accessToken;
        }
        return accessToken;
    }

    @Override
    public String getShortUrl(String longUrl) {

//        return WechatUtils.getShorturl(getAccessToken(),longUrl);
//        return longUrl;
        return longUrl;
    }

    @Override
    public List<SysUserInfo> findByRoleList(List<Integer> roleIdList) {
        if (roleIdList == null || roleIdList.isEmpty()) {
            return null;
        }
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIn(roleIdList);
        criteria.andStatusEqualTo(0);
        example.setOrderByClause(" id desc");
        return find(example);
    }

    @Override
    public String encryptPasswd(String passwd, String token) {
        return EncryptUtils.getMd5(passwd, token);

    }

    public int getLoginErrorCount(int userId) {
        String key = _LOGIN_ERROR_COUNT + userId;
        Object _obj = getRedis(key);
        int count = 0;
        if (_obj != null) {
            count = (int) _obj;
            setRedis(key, count + 1);
        }
        return count;
    }

    @Override
    public void clearLoginErrorCount(int userId, int count) {
        String key = _LOGIN_ERROR_COUNT + userId;
        setRedis(key, count);
    }

    @Override
    public LoginInfoVO isLogin(int userId) {

        String key = _LOGIN_SYS_USER_ONLINE + userId;

        boolean b  = redisUtil.hasKey(_REDIS_KEY_PREFIX+key);
        if(!b){
            return null;
        }
        String json = (String) getRedis(key);
        LOGGER.info("isLogin json:-------------------------->" + json);
        if (CommonUtil.isEmpty(json)) {
            return null;
        }
        //LoginInfoVO loginInfoVO = getObjectByRedis(_PAY_KEY,LoginInfoVO.class);

        return JSON.parseObject(json, LoginInfoVO.class);
    }

    @Override
    public boolean userOnline(LoginInfoVO loginInfoVO) {
        String key = _LOGIN_SYS_USER_ONLINE + loginInfoVO.getUserId();
        boolean b = setJsonToRedis(key, loginInfoVO, (long) (120*60));
        return b;
    }

    @Override
    public boolean userOnline(int userId, String loginName, HttpServletRequest request) {
        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setUserId(userId);
        loginInfoVO.setLoginName(loginName);
        String ip = HttpRequestUtils.getIpAddr(request);
        String osAndBrower = request.getHeader("user-agent");
        LOGGER.info("osAndBrower:{}",osAndBrower);
        String osName = HttpCommon.whatOS(osAndBrower);
        LOGGER.info("osName:{}",osName);
        String brower = HttpCommon.whatBrower(osAndBrower);

        loginInfoVO.setIp(ip);
        loginInfoVO.setBrowser(brower);
        loginInfoVO.setOsName(osName);
        loginInfoVO.setRemark(osAndBrower);
        loginInfoVO.setLoginTime(new Date());
        boolean b = userOnline(loginInfoVO);
        return b;
    }

    @Override
    public boolean userOffline(int userId) {
        String key = _LOGIN_SYS_USER_ONLINE + userId;
        removeRedis(key);
        return true;
    }

    @Override
    public List<LoginInfoVO> onlineList() {
        String key = _REDIS_KEY_PREFIX+_LOGIN_SYS_USER_ONLINE + "*";


        Set<String> keys = redisUtil.getAllByKey(key);
        List<LoginInfoVO> voList = new ArrayList<>(
        );
        for(String k:keys){
            String json = (String) redisUtil.get(k);
            LoginInfoVO vo =  JSON.parseObject(json,LoginInfoVO.class);
            voList.add(vo);
        }
        return voList;
    }
}
