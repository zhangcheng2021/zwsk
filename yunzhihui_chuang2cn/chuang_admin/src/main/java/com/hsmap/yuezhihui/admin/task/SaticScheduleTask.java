package com.hsmap.yuezhihui.admin.task;

import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/***
 *  后台定时任务
 *
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask extends BaseTask{
    @Autowired
    ISysUserInfoService userInfoService;

//    @Scheduled(cron = "0 0 1 * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void userVipExamine() {
//        LOGGER.info("检查用户是否到期开始...");
//        int count = orderUserVipService.examineVip();
//        LOGGER.info("检查用户是否到期结束，共到期{}用户...",count);
//        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//
//    }
}

