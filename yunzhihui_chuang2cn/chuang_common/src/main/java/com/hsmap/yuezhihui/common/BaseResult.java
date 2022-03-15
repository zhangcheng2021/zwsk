package com.hsmap.yuezhihui.common;

import com.alibaba.fastjson.JSONObject;

public class BaseResult {

    public static JSONObject result(int code, String msg, Object data) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (data != null) {
            json.put("data", data);
        } else {
            json.put("data", "");
        }
        return json;
    }


}
