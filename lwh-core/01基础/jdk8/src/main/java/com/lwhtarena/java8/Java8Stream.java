package com.lwhtarena.java8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * <h2>简述：Java 8 Stream</h2>
 * <pre>
 *     Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 *     Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 *     Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 *     这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 *     元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 * </pre>
 *
 * <h2>什么是 Stream？</h2>
 * <ol>Stream（流）是一个来自数据源的元素队列并支持聚合操作</ol>
 * <h2>生成流</h2>
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 * <pre>stream() − 为集合创建串行流。</pre>
 * <pre>parallelStream() − 为集合创建并行流。</pre>
 * <pre>
 *     List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
 *     List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
 * </pre>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8Stream {

    /**Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：**/
    @Test
    public void test001(){
        Random random =new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：**/
    @Test
    public void test002(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        /*获取对应的平方数*/
        List<Integer> squaresList =numbers.stream().map(i ->i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
//        numbers = numbers.stream().map(i ->i*i).distinct().collect(Collectors.toList());
//        numbers.forEach(System.out::println);
    }

    /**filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：**/
    @Test
    public void test003(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        /**获取空字符串的数量**/
        int count = (int) strings.stream().filter(s ->s.isEmpty()).count();

        System.out.println("count:"+ count);

    }

    /**limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：**/
    @Test
    public void test004(){
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：**/
    @Test
    public void test005(){
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /**parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：**/
    @Test
    public void test006(){
        /**并行（parallel）程序**/
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        /** 获取空字符串的数量 **/
        int count = (int) strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("count:"+ count);

    }

    /**Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：**/
    @Test
    public void test007(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    /**一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果**/
    @Test
    public void test008(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }
}
