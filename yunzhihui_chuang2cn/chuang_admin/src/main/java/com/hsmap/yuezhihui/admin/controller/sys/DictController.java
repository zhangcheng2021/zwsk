package com.hsmap.yuezhihui.admin.controller.sys;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.entity.core.CoreNoticeConfig;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import com.hsmap.yuezhihui.service.sys.ISysDictService;
import com.hsmap.yuezhihui.utils.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class DictController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "sys/dict";
    @Autowired
    ISysDictService dictService;
    @Autowired
    IReviewBatchService batchService;
    @Autowired
    private RedisUtil redisUtil;

    private final String _APP_IS_REVIEW = "app_is_review";
    private final String _APP_IS_REVIEW_MSG = "app_is_review_msg";
    private final String _APP_NOTICE_REDIS = "_RED_APP_NOTICE_1";

    private final String _APP_NOTICT_KEY = "_APP_NOTICT_KEY";

    @RequestMapping(value = _MODEL_NAME + "/noticeInput")
    public ModelAndView taskTimeInput() {

        ModelAndView mv = null;
        try {

            List<ReviewBatch> batchList = batchService.findByStatus(1);
            ReviewBatch batch = null;
            if (batchList != null && batchList.size() == 1) {
                batch = batchList.get(0);
                mv = createView(_MODEL_NAME + "/notice");
            } else if (batchList != null && batchList.size() > 1) {
                mv = createView(_MODEL_NAME + "/error");
                mv.addObject("error", "不支持同时配置多个批次");
                return mv;
            } else {
                mv = createView(_MODEL_NAME + "/error");
                mv.addObject("error", "当前没有进行中的批次");
                return mv;
            }

//            String isStart = dictService.getValueByCode(_APP_IS_REVIEW);
//            String isStartMsg = dictService.getValueByCode(_APP_IS_REVIEW_MSG);

            //String notice = dictService.getValueByCode(_TASK_END_TIME_CODE);
            String json = (String) redisUtil.get(_APP_NOTICT_KEY);
            CoreNoticeConfig config = null;
            if (StringUtils.isNotBlank(json)) {
                config = JSON.parseObject(json, CoreNoticeConfig.class);
                config.setNoticeContent(batch.getContent());
                mv.addObject("dateRange", config.getStartDt() + " - " + config.getEndDt());
            } else {
                config = new CoreNoticeConfig();
                config.setBatchId(batch.getId());
                config.setStartFlag(0);
                mv.addObject("dateRange", "");
            }
            mv.addObject("config", config);

//            LOGGER.info("isStart:{},notice:{}", isStart, notice);
            mv.addObject("_PARENT_MENU_NAME", "评审管理");
            mv.addObject("_MENU_NAME", "评审设置");
//            mv.addObject("isStart", isStart);
//            mv.addObject("isStartMsg", isStartMsg);
            mv.addObject("batchName", batch.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME + "/notice")
    public String notice(CoreNoticeConfig config,
                         @RequestParam("dateRange") String dateRange) {
        LOGGER.info("notice attrs {} , dateRange:{}", config.toString(), dateRange);
        LOGGER.info("notice content length {}", config.getNoticeContent().length());
        String[] dds = dateRange.split(" - ");
        config.setStartDt(dds[0].trim());
        config.setEndDt(dds[1].trim());
        String json = JSON.toJSONString(config);
        redisUtil.set(_APP_NOTICT_KEY, json);
//        dictService.saveByCode(_APP_IS_REVIEW, isStart);
//        dictService.saveByCode(_APP_IS_REVIEW_MSG, isStartMsg);

        if (config.getBatchId() > 0) {
            ReviewBatch batch = batchService.findById(config.getBatchId());
            batch.setContent(config.getNoticeContent());
            batchService.update(batch);
        }
//        dictService.setRedis(_APP_NOTICE_REDIS, notice);
        return setResult();
    }

}
