package com.lwhtarena.rpc.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liwh
 * @Title: InvokeUtils
 * @Package com.lwhtarena.rpc.util
 * @Description: 反射工具类
 * @Version 1.0.0
 * @date 2020/9/19 22:23
 */
public class InvokeUtils {


    /**
     * 返回clazz和方法的唯一标识
     */
    public static String buildInterfaceMethodIdentify(Class<?> clazz, Method method) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("interface", clazz.getName());
        map.put("method", method.getName());
        Parameter[] parameters = method.getParameters();
        if (parameters.length > 0) {
            StringBuilder param = new StringBuilder();
            for (int i = 0; i < parameters.length; i++) {
                Parameter p = parameters[i];
                param.append(p.getType().getName());
                if (i < parameters.length - 1) {
                    param.append(",");
                }
            }
            map.put("parameter", param.toString());
        }
        return StringUtils.map2String(map);
    }


}