package com.lwhtarena.java8.Lambda;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * <h2>简述：Java8 lambda表达式10个示例</h2>
 * <ol>lambda -> 闭包</ol>
 * <h2>功能描述：</h2>
 * <ol>Lambda表达式 vs 匿名类</ol>
 * <blockquote><pre>
 * 既然lambda表达式即将正式取代Java代码中的匿名内部类，那么有必要对二者做一个比较分析。一个关键的不同点就是关键
 * 字 this。匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类。另一个不同
 * 点是二者的编译方式。Java编译器将lambda表达式编译成类的私有方法。使用了Java 7的 invokedynamic 字节码指令来
 * 动态绑定这个方法。
 * </pre></blockquote>
 *
 * <ol>lambda表达式有个限制，那就是只能引用 final 或 final 局部变量，这就是说不能在lambda内部修改定义在域外的变量。</ol>
 *  看起来更像不可变闭包，类似于Python。
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSExample {

    /**
     * 用lambda表达式实现Runnable
     */
    @Test
    public void test001(){

        /**Java 8之前**/
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        /**Java 8 方式**/
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

    }

    /**
     * 使用Java 8 lambda表达式进行事件处理
     */
    @Test
    public void test002(){
        // Java 8之前：
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        // Java 8方式：
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    @Test
    public void test003(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        // Java 8之后：
        List features2 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features2.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features2.forEach(System.out::println);
    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     *
     * 除了在语言层面支持函数式编程风格，Java 8也添加了一个包，叫做 java.util.function。它
     * 包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，使用 java.util.function.Predicate
     * 函数式接口以及lambda表达式，可以向API方法添加逻辑，用更少的代码支持更多的动态行为。下面是Java 8 Predicate
     * 的例子，展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
     *
     */
    @Test
    public void test004(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.length() > 4);
        filter2(languages, (str)->str.length() > 4);
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    // 更好的办法
    public static void filter2(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    /**
     * 如何在lambda表达式中加入Predicate
     *
     * 上个例子说到，java.util.function.Predicate 允许将两个或更多的 Predicate 合成一个。它提供类
     * 似于逻辑操作符AND和OR的方法，名字叫做and()、or()和xor()，用于将传入 filter() 方法的条件合并
     * 起来。例如，要得到所有以J开始，长度为四个字母的语言，可以定义两个独立的 Predicate 示例分别表示
     * 每一个条件，然后用 Predicate.and() 方法将它们合并起来，如下所示：
     */
    @Test
    public void test005(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));

        /**类似地，也可以使用 or() 和 xor() 方法。**/
    }

    /**
     * Java 8中使用lambda表达式的Map和Reduce示例
     */
    @Test
    public void test6(){
        /**
         * 本例介绍最广为人知的函数式编程概念map。它允许你将对象进行转换。例如在本例中，我们将 costBeforeTax
         * 列表的每个元素转换成为税后的值。我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的
         * 每一个元素。然后用 forEach() 将列表元素打印出来。使用流API的收集器类，可以得到所有含税的开销。有
         * toList() 这样的方法将 map 或任何其他操作的结果合并起来。由于收集器在流上做终端操作，因此之后便不
         * 能重用流了。你甚至可以用流API的 reduce() 方法将所有数字合成一个，下一个例子将会讲到。
         */
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax2.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);

        /**
         * 在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。还有一个 reduce() 函数可以将所有值合并成一个。
         * Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。另外，reduce 并不是一个新的操作，
         * 你有可能已经在使用它。SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接
         * 收多个值并返回一个值。流API定义的 reduce() 函数可以接受lambda表达式，并对所有值进行合并。IntStream这样
         * 的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法
         * 来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。在这个Java 8的Map Reduce示例里，我们首先对所有
         * 价格应用 12% 的VAT，然后用 reduce() 方法计算总和。
         */

        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax3 = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax3) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // 新方法：
        List<Integer> costBeforeTax4 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax4.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    /**
     * 通过过滤创建一个String列表
     *
     * 过滤是Java开发者在大规模集合上的一个常用操作，而现在使用lambda表达式和流API过滤大规模数据集合是惊人的简单。流提供了一个
     * filter() 方法，接受一个 Predicate 对象，即可以传入一个lambda表达式作为过滤逻辑。下面的例子是用lambda表达式过滤Java集
     * 合，将帮助理解。
     */
    @Test
    public void test007(){
        List<String> strList = Arrays.asList("nihao即","可以传入","9","一个lambda表达式","作","为过滤逻辑。","下面的例");

        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);

        /**备注： 另外，关于 filter() 方法有个常见误解。在现实生活中，做过滤的时候，通常会丢弃部分，但使用filter()方法则是获得一个新的列表，且其每个元素符合过滤原则。**/
    }

    /**
     * 对列表的每个元素应用函数
     */
    @Test
    public void test008(){
        /**
         * 我们通常需要对列表的每个元素使用某个函数，例如逐一乘以某个数、除以某个数或者
         * 做其它操作。这些操作都很适合用 map() 方法，可以将转换逻辑以lambda表达式的形
         * 式放在 map() 方法里，就可以对集合的各个元素进行转换了，如下所示。
         */

        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

    }

    /**
     * 复制不同的值，创建一个子列表
     */
    @Test
    public void test009(){
        /**本例展示了如何利用流的 distinct() 方法来对集合进行去重。**/
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     */
    @Test
    public void test010(){
        /**
         * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。可以
         * 返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistics，描述流中元素的各种
         * 摘要数据。在本例中，我们用这个方法来计算列表的最大值和最小值。它也有 getSum() 和 getAverage() 方法来获得
         * 列表的所有元素的总和及平均值。
         */
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
