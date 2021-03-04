package com.lwhtarena.集合;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author lwh
 * @folder com.lwhtarena
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Map集合 {
    public static void main(String[] args) {

        Map<String,String> map = new TreeMap<String, String>();
        map.put("Jerry", "10000");
        map.put("shellway", "20000");
        map.put("Kizi", "30000");
        //Map的第一种遍历方式：先获得key,再获得值value
        Set<String> sett = map.keySet();
        for (String s : sett) {
            System.out.println(s+":"+map.get(s));
        }
        //Map的第二种遍历方式：获得键值对
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

    }
}
