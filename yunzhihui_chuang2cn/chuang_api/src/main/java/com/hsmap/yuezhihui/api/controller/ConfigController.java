package com.hsmap.yuezhihui.api.controller;


import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.entity.core.CoreNoticeConfig;
import com.hsmap.yuezhihui.service.sys.ISysDictService;
import com.hsmap.yuezhihui.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController extends ApiBaseController {


    private final String _MODEL_NAME = "/config/";

    @Autowired
    ISysDictService dictService;

    private final String _APP_IS_REVIEW = "app_is_review";
    private final String _APP_NOTICE_REDIS = "_RED_APP_NOTICE_1";
    private final String _APP_IS_REVIEW_MSG = "app_is_review_msg";

    private final String _APP_NOTICT_KEY = "_APP_NOTICT_KEY";
    @Autowired
    private RedisUtil redisUtil;

    /***
     * 系统配置
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/isstart")
    public String isstart() {
        Map<String, String> map = new HashMap<>();
        String json = (String) redisUtil.get(_APP_NOTICT_KEY);
//        String json = dictService.getValueByCode(_APP_NOTICT_KEY);
        CoreNoticeConfig config = null;
        if (json != null && json.trim().length() > 0) {
            config = JSON.parseObject(json, CoreNoticeConfig.class);
            map.put("isStatus", config.getStartFlag().toString());
            map.put("msg", config.getNoticeMsg());
        } else {
            map.put("isStatus", "-1");
            map.put("msg", "未设置评审须知");
        }
        return setResult(map);
    }

    @RequestMapping(value = _MODEL_NAME + "/notice")
    public String notice() {
        Map<String, String> map = new HashMap<>();
        String json = (String) redisUtil.get(_APP_NOTICT_KEY);
//        String json = dictService.getValueByCode(_APP_NOTICT_KEY);
        CoreNoticeConfig config = null;
        if (json != null && json.trim().length() > 0) {
            config = JSON.parseObject(json, CoreNoticeConfig.class);
            map.put("notice", config.getNoticeContent());
            map.put("isStatus", config.getStartFlag().toString());
        } else {
            map.put("isStatus", "-1");
            map.put("notice", "未设置评审须知");
        }
        return setResult(map);
    }

    @RequestMapping(value = _MODEL_NAME + "/notice_config")
    public String noticeConfig() {
        String json = (String) redisUtil.get(_APP_NOTICT_KEY);
        CoreNoticeConfig config = null;
        if (json != null && json.trim().length() > 0) {
            config = JSON.parseObject(json, CoreNoticeConfig.class);
        } else {
            config = new CoreNoticeConfig();
            config.setStartFlag(-1);
            config.setNoticeMsg("未设置评审须知");
            config.setStartDt("1977-01-01 00:00");
            config.setEndDt("1977-01-01 00:00");
            config.setNoticeContent("");
        }
        return setResult(config);
    }
}
