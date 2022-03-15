package com.hsmap.yuezhihui.service.sys;

import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysOperateLog;
import com.hsmap.yuezhihui.entity.sys.SysOperateLogExample;

import javax.servlet.http.HttpServletRequest;

/**
 * 操作日志
 */
public interface ISysOperateLogService  extends IBaseService<SysOperateLog, Integer, SysOperateLogExample> {
//    void saveLog(HttpServletRequest request, SysOperateLog log);

    void saveLog(HttpServletRequest request, String content, int logType);
}
