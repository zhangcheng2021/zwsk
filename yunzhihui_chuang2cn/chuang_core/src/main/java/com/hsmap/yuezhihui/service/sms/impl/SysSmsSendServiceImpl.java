package com.hsmap.yuezhihui.service.sms.impl;


import com.hsmap.yuezhihui.service.sys.ISysDictService;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.sys.SysSmsSendLog;
import com.hsmap.yuezhihui.entity.sys.SysSmsSendLogExample;
import com.hsmap.yuezhihui.mapper.sys.SysSmsSendLogMapper;
import com.hsmap.yuezhihui.sms.aliyun.SendSms;
import com.hsmap.yuezhihui.sms.liasmart.LiasmartSendSms;
import com.hsmap.yuezhihui.utils.redis.RedisUtil;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sms.ISysSmsBlacklistService;
import com.hsmap.yuezhihui.service.sms.ISysSmsSendService;
import com.hsmap.yuezhihui.service.sms.ISysSmsTemplateService;
import com.hsmap.yuezhihui.sms.baifen.SmsSendUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysSmsSendServiceImpl extends BaseServiceImpl<SysSmsSendLog,Integer, SysSmsSendLogExample> implements ISysSmsSendService {


    final String _SMS_SEND_LOG_60 = "sms_send_log_60_";
    final String _SMS_SEND_LOG_3600 = "sms_send_log_3600_";
    final String _SMS_SEND_IP_ ="_SMS_SEND_IP_";

    final int _SMS_1_HOUR_COUNT = 10;
    final int _SMS_SEND_IP_COUNT = 10;
    final long _SMS_SEND_IP_TIME = 60*10;//IP周期


    @Autowired
    SysSmsSendLogMapper mapper;
    @Autowired
    SysConfig sysConfig;
    @Autowired
    ISysDictService dictService;
    @Autowired
    RedisUtil redisUtil;
    //com.hsmap.yuezhihui.common.redis.RedisUtil



    @Autowired
    ISysSmsBlacklistService sysSmsBlacklistService;

    @Autowired
    ISysSmsTemplateService sysSmsTemplateService;
    @Override
    public BaseMapper<SysSmsSendLog, Integer, SysSmsSendLogExample> getMapper() {
        return mapper;
    }


    @Override
    public boolean sendSmsToAliyun(String mobile, String code) {
        String accessId = sysConfig.getAliyunSmsKey();
        String accessSecret = sysConfig.getAliyunSmsSecret();
        String signName = sysConfig.getAliyunSmsSignName();
        String templateCode = sysConfig.getAliyunSmsSecurityTemplateCode();

        SysSmsSendLog sysSmsSendLog = getSendLog(mobile,code);
        if(sysSmsSendLog.getSendStatus() == 0){
            SendSms sendSms  =new SendSms(accessId,accessSecret,signName);
            sendSms.sendSecurityCode(templateCode,mobile,code);
            save(sysSmsSendLog);
            setSendLog60ByRedis(mobile);
            setSendLog1HourByRedis(mobile);
            return true;
        }
        save(sysSmsSendLog);

        return false;
    }


    @Override
    public boolean sendMsgToBaiFen(String templateCode, String mobile, String... str) {
        String msg = sysSmsTemplateService.getSmsContent(templateCode,str);
        if (CommonUtil.isEmpty(msg)) {
            LOGGER.info("短信内容为空");
            return false;
        }
        boolean b =  sendMsgToBaiFen(mobile,msg);
        return b;
    }

    @Override
    public boolean sendMsgToBaiFen(String mobile, String msg) {
        msg = sysSmsTemplateService.getMsgAddSignature(msg);
        boolean b = sendBanFenSms(mobile,msg);
        return b;

    }
    @Override
    public boolean sendMsgToLiasmart(String mobile, String msg) {
        boolean b = sendLiasmartSms(mobile,msg);
        return b;

    }

    @Override
    public boolean verifyIp(String ip) {
        LOGGER.info("verifyIP,ip:{}",ip);
        if (CommonUtil.isEmpty(ip)) {
            return false;
        }
        String key = _SMS_SEND_IP_+ip;
        int ipSendCcount = redisUtil.get(key) ==null?0:((int)redisUtil.get(key));
        LOGGER.info("ipSendCcount:{}",ipSendCcount);
        if(ipSendCcount>=_SMS_SEND_IP_COUNT){
            LOGGER.info("ip:{},{}分钟内发送短信词数超限",ip,_SMS_SEND_IP_TIME);
            return false;
        }
        setRedis(key,ipSendCcount+1,_SMS_SEND_IP_TIME);
        return true;
    }

    private  boolean sendLiasmartSms(String mobile,String msg){
        LOGGER.info("send mobile:{},msg:{}",mobile,msg);

        SysSmsSendLog smsSendLog = getSendLog(mobile,msg);
        if(smsSendLog.getSendStatus() == 0){
            LiasmartSendSms.send(mobile,msg);
            setSendLog60ByRedis(mobile);
            setSendLog1HourByRedis(mobile);
            return true;
        }
        save(smsSendLog);
        return false;

    }
    /***
     * 百分通联接口
     * @param mobile
     * @param msg
     * @return
     */
    private boolean sendBanFenSms(String mobile,String msg){
        SmsSendUtils smsSendUtils = new SmsSendUtils(sysConfig.getSmsUserName(),sysConfig.getSmsPasswd());
        LOGGER.info("send mobile:{},msg:{}",mobile,msg);
        LOGGER.info("nnnnnn:{}",sysConfig.getSmsPasswd());

        SysSmsSendLog smsSendLog = getSendLog(mobile,msg);
        if(smsSendLog.getSendStatus() == 0){
            smsSendUtils.sendMsg(mobile,msg);
            setSendLog60ByRedis(mobile);
            setSendLog1HourByRedis(mobile);
            return true;
        }
        save(smsSendLog);
        return false;
    }

    private SysSmsSendLog getSendLog(String mobile,String msg){
        //入库{}
        SysSmsSendLog sendLog = new SysSmsSendLog();
        sendLog.setMobile(mobile);
        sendLog.setMsgNumber((int) Math.ceil(((double) msg.length()/(double)70)));
        sendLog.setWordSize(msg.length());
        sendLog.setSendTime(new Date());
        sendLog.setMsg(msg);
        //是否白名单
        boolean b = sysSmsBlacklistService.mobileIsBlackByTypeRedis(mobile,2);
        if (b) {//白名单用户直接发送

            sendLog.setRemark("白名单用户");
            sendLog.setSendStatus(0);
            return sendLog;
        }
        //是否黑名单
        boolean black_1 = sysSmsBlacklistService.mobileIsBlackByTypeRedis(mobile,1);
        if (black_1) {
            //黑名单
            LOGGER.info("短信发送失败，手机号码:{}为黑名单用户",mobile);
            sendLog.setRemark("黑名单用户");
            sendLog.setSendStatus(-1);
            return  sendLog;
        }


        //1分钟1条
        if (isSendLog60ByRedis(mobile)) {
            LOGGER.info("短信发送失败，手机号码:{}，一分钟内有发送记录",mobile);
            sendLog.setRemark("一分钟内有发送记录");
            sendLog.setSendStatus(-1);
            return  sendLog;
        }
        //1小时发送10条  60*60
        int count =getSendCountByRedis(_SMS_SEND_LOG_3600+mobile);
        LOGGER.info("send count:{}",count);
        if (count >= _SMS_1_HOUR_COUNT) {
            LOGGER.info("短信发送失败，手机号码:{}，1小时内发送条数为:{},超过最大限制",mobile,count);
            sendLog.setRemark("1小时内发送条数为:"+_SMS_1_HOUR_COUNT+",已经发送:"+count);
            sendLog.setSendStatus(-1);
            return sendLog;
        }
        //发送
        //smsSendUtils.sendMsg(mobile,msg);
//        LiasmartSendSms.send(mobile,msg);
        //发送记录
        sendLog.setRemark("发送成功");
        sendLog.setSendStatus(0);
        return sendLog;
    }

    /***
     * 查看是否是1分钟内的记录
     * @param mobile
     * @return
     */
    private boolean isSendLog60ByRedis(String mobile) {
        Object obj = getRedis(_SMS_SEND_LOG_60+mobile);
        if (obj == null) {
            return false;
        }
        int count = (int )obj;
        if (count >= 1) {
            return true;

        }
        return false;
    }

    /***
     * 1小时内发送条数
     * @param key
     * @return
     */
    private int getSendCountByRedis(String key) {
        Object obj = getRedis(key);

        if (obj == null) {
            return 0 ;
        }
        return (int)obj;
    }

    /***
     * 设置发送记录
     * @param mobile
     */
    private void setSendLog60ByRedis(String mobile) {

        setRedis(_SMS_SEND_LOG_60+mobile,1, (long) 60);
    }

    /**
     * 设置1小时发送条数
     * @param mobile
     */
    private void setSendLog1HourByRedis(String mobile) {
        //setRedis(_SMS_SEND_LOG_60+mobile,mobile, (long) 60);
        String key = _SMS_SEND_LOG_3600+mobile;
        int count =getSendCountByRedis(key);
        LOGGER.info("setSendLog1HourByRedis,count:{}",count);
        setRedis(key,(count+1),(long)3600);
        LOGGER.info("_PAY_KEY:{},count:{}",key,count+1);
    }


    @Override
    public PageInfo<SysSmsSendLog> search(SysSmsSendLog smsSendLog, Pageable pageable) {
        SysSmsSendLogExample example = new SysSmsSendLogExample();
        SysSmsSendLogExample.Criteria criteria = example.createCriteria();
        if (!CommonUtil.isEmpty(smsSendLog.getMobile())) {
            criteria.andMobileEqualTo(smsSendLog.getMobile());
        }
        example.setOrderByClause(" send_time desc");
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());


        return findByPage(example,pageable);
    }

    @Override
    public String getSmsCode() {

//        return "0000";
        return CommonUtil.getRandomByCount(4);
    }
}
