package com.lwhtarena.集合;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lwh
 * @folder com.lwhtarena
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class List集合 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("DDDDD");
        list.add("EEEEE");
        list.add("FFFFF");

/*	  //List的第一种遍历方式:因为list有顺序，利用size()和get()方法获取
	    for (int i = 0; i < list.size(); i++) {
	      System.out.println(list.get(i));
	    }
	    //List的第二种遍历方式：利用Iterator
	    Iterator<String> it = list.iterator();
	    while (it.hasNext()) {
	      System.out.println(it.next());
	    }
	    //List的第三种遍历方式:利用foreach
	    for (String s2 : list) {
	      System.out.println(s2);
	    }*/

        int i = 0;
        for(Iterator it = list.iterator(); it.hasNext(); i++){
            System.out.println(it.next());

        }

    }
}
