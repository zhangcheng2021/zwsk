package com.hsmap.yuezhihui.service.sms;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysSmsSendLog;
import com.hsmap.yuezhihui.entity.sys.SysSmsSendLogExample;

/**
 * 短信发送记录service接口
 */
public interface ISysSmsSendService extends IBaseService<SysSmsSendLog,Integer, SysSmsSendLogExample> {


    boolean sendSmsToAliyun(String mobile,String code);
    /***
     * 发送短信-有模板
     * @param templateCode 模板code
     * @param mobile 手机号码
     * @param str 参数
     */
    boolean sendMsgToBaiFen(String templateCode, String mobile, String... str);

    /***
     * 发送Liasmart短信
     * @param mobile
     * @param msg
     * @return
     */
    boolean sendMsgToLiasmart(String mobile, String msg);

    /**
     * 发送短信-无模板
     * @param mobile
     * @param msg
     */
    boolean sendMsgToBaiFen(String mobile, String msg);

    boolean verifyIp(String ip);

    PageInfo<SysSmsSendLog> search(SysSmsSendLog smsSendLog, Pageable pageable);

    String getSmsCode();

}
