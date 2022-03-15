package com.hsmap.yuezhihui.api.runner;

import com.hsmap.yuezhihui.service.sms.ISysSmsBlacklistService;
import com.hsmap.yuezhihui.service.sms.ISysSmsTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(99)
/***
 * 启动时加载redis的数据
 */
public class RedisCommandLineRunner implements CommandLineRunner {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ISysSmsTemplateService sysSmsTemplateService;
    @Autowired
    ISysSmsBlacklistService sysSmsBlacklistService;

//    @Auto
    @Override
    public void run(String... args) throws Exception {
        loadSmsTemplateData();
        loadSmsBlacklistByType(1);
        loadSmsBlacklistByType(2);
    }


    /***
     * 加载短信模板
     */
    public void loadSmsTemplateData() {
        LOGGER.info("正在加载短信模板到redis中");
        sysSmsTemplateService.syncAll2Redis(0);
        LOGGER.info("短信模板加载完成---------->_<---------");
    }

    public void loadSmsBlacklistByType(int type) {
        String str = (type == 1 ? "黑" : "白") + "名单";
        LOGGER.info("正在加载短信" + str + "到redis中--------->");
        sysSmsBlacklistService.syncType2Redis(type);
        LOGGER.info("短信" + str + "模板加载完成---------->_<---------");
    }





}
