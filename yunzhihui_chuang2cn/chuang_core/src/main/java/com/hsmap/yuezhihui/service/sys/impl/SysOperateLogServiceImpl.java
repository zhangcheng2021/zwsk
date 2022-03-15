package com.hsmap.yuezhihui.service.sys.impl;

import com.hsmap.yuezhihui.enumcode.OperateLogTypeEnum;
import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.sys.SysOperateLog;
import com.hsmap.yuezhihui.entity.sys.SysOperateLogExample;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.mapper.sys.SysOperateLogMapper;
import com.hsmap.yuezhihui.utils.HttpRequestUtils;
import com.hsmap.yuezhihui.http.HttpCommon;
import com.hsmap.yuezhihui.service.sys.ISysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Service
public class SysOperateLogServiceImpl  extends BaseServiceImpl<SysOperateLog,Integer, SysOperateLogExample> implements ISysOperateLogService {


    @Autowired
    SysOperateLogMapper mapper;
    @Override
    public BaseMapper<SysOperateLog, Integer, SysOperateLogExample> getMapper() {
        return mapper;
    }
//
//    @Override
//    public void saveLog(HttpServletRequest request, SysOperateLog log) {
//        //保存信息
//        Enumeration<String> headerNames = request.getHeaderNames();
//        String allInfo = "";
//        while (headerNames.hasMoreElements()){
//            String header = headerNames.nextElement();
//            allInfo += header + ": ";
//            allInfo += request.getHeader(header) + "\n";
//        }
//        LOGGER.info("allInfo:{}",allInfo);
//        String osAndBrower = request.getHeader("user-agent");
//        LOGGER.info("osAndBrower:{}",osAndBrower);
//        String os = HttpCommon.whatOS(osAndBrower);
//        LOGGER.info("os:{}",os);
//        String brower = HttpCommon.whatBrower(osAndBrower);
//        LOGGER.info("brower:{}",brower);
//        log.setRemark("user-agent:"+osAndBrower);
//        log.setOs(os);
//        log.setBrowser(brower);
//        save(log);
//
//    }

    @Override
    public void saveLog(HttpServletRequest request, String content, int logType) {
        Object userSession = HttpRequestUtils.getSession(request.getSession(), Constants._SESSION_USER_NAME);
        int userId = 0;
        if(userSession != null) {
            SysUserInfo userInfo = (SysUserInfo)userSession;
            userId = userInfo.getId();
        }
        SysOperateLog log = new SysOperateLog();
        String ip = HttpRequestUtils.getIpAddr(request);

        Enumeration<String> headerNames = request.getHeaderNames();
        String allInfo = "";
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            allInfo += header + ": ";
            allInfo += request.getHeader(header) + "\n";
        }
        LOGGER.info("allInfo:{}",allInfo);
        String osAndBrower = request.getHeader("user-agent");
        LOGGER.info("osAndBrower:{}",osAndBrower);
        String os = HttpCommon.whatOS(osAndBrower);
        LOGGER.info("os:{}",os);
        String brower = HttpCommon.whatBrower(osAndBrower);
        LOGGER.info("brower:{}",brower);
        log.setRemark("user-agent:"+osAndBrower);
        log.setOs(os);
        log.setBrowser(brower);
        log.setUserId(userId);
        log.setIp(ip);
        log.setOperateContent(content);
        log.setType(logType);
        log.setLevel(OperateLogTypeEnum.getLevel(logType));
        save(log);
        //logTypeEnum.
//        OperateLogTypeEnum.getValue()

    }
}
