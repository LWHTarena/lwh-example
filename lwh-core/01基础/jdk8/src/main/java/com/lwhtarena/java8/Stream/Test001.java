package com.lwhtarena.java8.Stream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * <h2>简述：Stream是Java8中，操作集合的一个重要特性。</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Test001 {

    /**
     * 从iteration到Stream操作
     * 当你操作一个集合的时候，你通常的做法是迭代每一个元素，然后处理你想要的事情。
     *
     * 从表面上，stream看起来和集合很像，你可以自由的操作它。但是有以下几点不同：
     *
     * 1、stream不存储集合元素
     * 2、stream操作不修改源数据，他们是返回一个新的streams来承载结果
     * 3、stream操作都会尽可能的进行延迟加载。这意味着当需要使用结果的时候它才会才运行。
     *
     * 当你使用stream的时候，需要关注一下3个阶段：
     *
     * 1、创建一个stream
     * 2、指定的中间操作将初始stream转化为其他stream
     * 3、最终的操作会产生一个结果，在调用最终操作前都会延迟执行的。在这之后，stream不会再被使用。
     */
    @Test
    public void test001() throws IOException {
        /**当你操作一个集合的时候，你通常的做法是迭代每一个元素，然后处理你想要的事情。**/
        String contents =new String(Files.readAllBytes(Paths.get("S:\\lwh-githubs\\basecore_java\\01 环境基础\\src\\com\\lwhtarena\\java8\\Stream\\alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+")); // 截取words

        /**之前的写法**/
        int count =0;
        for(String s: words){
            if(s.length() >6 ){
                count ++;
            }
        }
        System.out.println("count: "+ count);
        System.out.println("================= 分界线 ====================");


        /**上面一段代码有什么问题吗？除了并行处理不是很好以为我想是没有。在Java8中，相同的操作是这么处理的：**/
        long num =words.parallelStream().filter(s -> s.length()>6).count();
        System.out.println("num: "+ num);

        /**
         * so cool！从代码中，我们就能非常容易的看出它要表达的意思，filter是针对words的过滤。
         * 有人会问，这样的操作的确让人很是兴奋，但是刚才说到的并行处理它能解决吗？ 答案是不可以，但是Java8给我们提供了非常好的API，并行处理如下：
         * **/
        long numbers = words.parallelStream().filter(w -> w.length() > 6).count();
        System.out.println("numbers: "+ numbers);
    }

    /**
     * Stream创建
     * **/
    @Test
    public void test002() throws IOException {
        String content =new String(Files.readAllBytes(Paths.get("S:\\lwh-githubs\\basecore_java\\01 环境基础\\src\\com\\lwhtarena\\java8\\Stream\\alice.txt")), StandardCharsets.UTF_8);

        /**1、java8中，你就可以将集合的操作都利用stream来处理，如果你有一个数组，使用静态的Stream.of方法来实现
         *
         * of方法定义：
         *   1) public static<T> Stream<T> of(T t);
         *   2) public static<T> Stream<T> of(T... values);
         * **/
        // 分隔后返回String[]
        Stream<String> words = Stream.of(content.split("[\\P{L}]+"));

        /**2、想要创建一个空的stream，可以使用Stream.empty方法**/
        Stream<String> silence = Stream.empty();
    }
}
