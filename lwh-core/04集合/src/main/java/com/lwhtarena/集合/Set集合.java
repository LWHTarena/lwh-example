package com.lwhtarena.集合;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lwh
 * @folder com.lwhtarena
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Set集合 {
    public static void main(String[] args) {

        Set<String> set = new HashSet<String>();
        set.add("AAA");
        set.add("BBB");
        set.add("CCC");

        //Set的第一种遍历方式:利用Iterator
        Iterator<String> it1 = set.iterator();
        for (String ss : set) {
            System.out.println(ss);
        }

        //Set的第一种遍历方式:利用foreach
        for (String sss : set) {
            System.out.println(sss);
        }

    }
}
