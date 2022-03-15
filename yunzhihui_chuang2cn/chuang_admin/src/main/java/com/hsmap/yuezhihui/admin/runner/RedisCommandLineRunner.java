package com.hsmap.yuezhihui.admin.runner;

import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import com.hsmap.yuezhihui.service.review.IReviewWeightService;
import com.hsmap.yuezhihui.service.sms.ISysSmsBlacklistService;
import com.hsmap.yuezhihui.service.sms.ISysSmsTemplateService;
import com.hsmap.yuezhihui.service.sys.ISysDictService;
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


    @Autowired
    ISysDictService dictService;
    @Autowired
    ICoreSpecTypeService coreSpecTypeService;
    @Autowired
    IReviewWeightService reviewWeightService;

//    @Autowired
//    IGoodsTypeService typeService;
//
//    @Autowired
//    IGoodsBrandService brandService;

    @Override
    public void run(String... args) throws Exception {

//        loadSmsBlacklistByType(1);
//        loadSmsBlacklistByType(2);
        //loadBannerList();
//        loadSmsTemplateData();

        loadSysDictAll();
        loadCoreData();
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
        String str= (type ==1?"黑":"白")+"名单";
        LOGGER.info("正在加载短信"+str+"到redis中--------->");
        sysSmsBlacklistService.syncType2Redis(type);
        LOGGER.info("短信"+str+"模板加载完成---------->_<---------");
    }


    public void loadSysDictAll(){
        LOGGER.info("正在加载系统数据字典到redis中--------->");
        dictService.refreshRedis();

        LOGGER.info("系统数据字典到redis加载完成。。。。");
    }

    public void loadCoreData(){
        LOGGER.info("正在加载Core的数据到redis中--------->");
//        typeService.pushTypeListToRedis();
//        brandService.pushBrandListToRedis();
        reviewWeightService.pushAllToRedis();
        coreSpecTypeService.pushToRedis();

        LOGGER.info("Core的数据到redis加载完成。。。。");
    }
    public void loadGoodsType() {
        LOGGER.info("正在加载商品类型到redis中--------->");
//        typeService.pushTypeListToRedis();
//        brandService.pushBrandListToRedis();
        LOGGER.info("商品类型到redis加载完成。。。。");
    }


}
