package com.lwhtarena.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liwh
 * @Title: TestList
 * @Package com.lwhtarena.array
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 16:02
 */
public class TestList {

    /**
     * ArrayList
     * LinkedList类是双向列表,列表中的每个节点都包含了对前一个和后一个元素的引用.
     *
     * 二者都实现list接口，ArrayList底层是数组，LinkedList是元素列表的形式存储它的数据（相比较，插入、添加、删除速度更快）
     *
     */
    @Test
    public void test01(){
        List list = new ArrayList();
        list.add("您好");
        list.add("您好啊");
        list.add("您好啊，世界！");
        System.out.println(list.size());


        System.out.println("");
        System.out.println("====================================================");

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("您好");
        list2.add("您好啊");
        list2.add("您好啊，世界！");
        System.out.println(list.size());
        System.out.println("链表的第一个元素是 : " + list2.getFirst());
        System.out.println("链表最后一个元素是 : " + list2.getLast());

    }
}
