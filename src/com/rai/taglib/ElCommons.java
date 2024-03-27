package com.rai.taglib;

import com.alibaba.fastjson.JSON;

public class ElCommons {
    public static String toJsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
}
