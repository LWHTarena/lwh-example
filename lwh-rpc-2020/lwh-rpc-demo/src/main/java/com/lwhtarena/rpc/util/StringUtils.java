package com.lwhtarena.rpc.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liwh
 * @Title: StringUtils
 * @Package com.lwhtarena.rpc.util
 * @Description: 处理字符串的工具
 * @Version 1.0.0
 * @date 2020/9/19 22:23
 */
public class StringUtils {


    public static Map<String, String> string2Map(String str) {
        String[] split = str.split("&");
        Map<String, String> map = new HashMap<>(16);
        for (String s : split) {
            String[] split1 = s.split("=");
            map.put(split1[0], split1[1]);
        }
        return map;
    }

    public static String map2String(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey() + "=" + entry.getValue());
            if (iterator.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

}