package com.lwhtarena.java8.Stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：对Stream的使用就是实现一个filter-map-reduce过程，产生一个最终结果，或者导致一个副作用（side effect）。</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Test003 {

    /**
     * 流的构造与转换
     */
    @Test
    public  void test001(){
        /*提供最常见的几种构造Stream的例子*/
        /**1. Individual values**/
        Stream stream = Stream.of("a", "b", "c");

        /**2. Arrays**/
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);

        /**3. Collections**/
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        /**
         * 需要注意的是，对于基本数值型，目前有三种对应的包装类型Stream：IntStream、LongStream、DoubleStream。
         * 当然我们也可以用 Stream<Integer>、Stream<Long>和Stream<Double>，但是boxing/unboxing会很耗时，所以
         * 特别为这三种基本数值型提供了对应的Stream。
         * **/

        /**流也可以转换为其它数据结构**/
        /**1. Array**/
        String[] strArray1 = (String[]) stream.toArray(String[]::new);

        /**2. Collection**/
        List<String> list1 = (List<String>) stream.collect(Collectors.toList());
        List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = (Set) stream.collect(Collectors.toSet());
        Stack stack1 = (Stack) stream.collect(Collectors.toCollection(Stack::new));

        /*3. String*/
        String str = stream.collect(Collectors.joining()).toString();
    }

    /**
     * Intermediate 操作
     *
     * 　map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
     *
     * Terminal 操作
     *
     * 　forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     *
     * Short-circuiting 操作
     *
     * 　anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */
    @Test
    public void test002(){

        /**1、 map/flatMap
         * map，它的作用就是把inputStream的每个元素映射成outputStream的另外一个元素
         *
         * map生成的是个1:1映射，每个输入元素都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要flatMap。
         * **/
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());


        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());


        /**
         * 2、filter
         *
         * filter对原始Stream进行某项测试，通过测试的元素被留下来生成一个新Stream。
         * **/
        //留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);

        /**
         * 3、forEach
         *
         * forEach方法接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式。
         * **/
        // 对一个人员集合遍历，找出男性并打印姓名。
        List<Person> roster =Arrays.asList(
                new Person("李文浩",Person.Sex.MALE.name()),
                new Person("周琪",Person.Sex.FEMALE.name()),
                new Person("郑智",Person.Sex.MALE.name()));
        roster.stream()
                .filter(p -> p.getGender().equals(Person.Sex.MALE.name()))
                .forEach(p -> System.out.println(p.getName()));

        roster.forEach(System.out::println);

    }

    @Test
    public void test003(){

        /**4、peek 对每个元素执行操作并返回一个新的 Stream*
         *
         * forEach 不能修改自己包含的本地变量值，也不能用break/return之类的关键字提前结束循环。
         * */
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());


        /**5、findFirst
         * 这里比较重点的是它的返回值类型Optional:这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，或者不包含,使用它的目的是尽可能避免NullPointerException。
         * **/

        /**
         * 6、reduce
         * 这个方法的主要作用是把Stream元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator），
         * 和前面Stream的第一个、第二个、第n个元素组合。从这个意义上说，字符串拼接、数值的 sum、min、max、average
         * 都是特殊的reduce。例如Stream的sum就相当于：
         *   Integer sum = integers.reduce(0, (a, b) -> a+b);
         * 或
         *   Integer sum = integers.reduce(0, Integer::sum);
         *
         * 也有没有起始值的情况，这时会把 Stream 的前面两个元素组合起来，返回的是 Optional。
         * **/
        // reduce 的用例
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);

        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);

        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);

        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

    }
}

class Person {

    enum Sex{
        MALE,FEMALE;
    }

    String name;
    String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
