package com.lwhtarena.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liwh
 * @Title: DeferredResultHolder
 * @Package com.lwhtarena.web.async
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:34
 */
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }

}
