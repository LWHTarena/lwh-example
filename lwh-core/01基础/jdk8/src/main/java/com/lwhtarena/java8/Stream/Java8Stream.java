package com.lwhtarena.java8.Stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * <h2>简述：java8中的流</h2>
 * <ol></ol>
 * <h2>功能描述：外部迭代与内部迭代</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8Stream {

    /**
     *    用到的for循环是来自java5的增强for循环，本质上是属于iterator迭代器的语法糖，这
     * 种使用迭代器的迭代集合的方式，称之为外部迭代，说的通俗一点，就是需要我们程序猿手动的
     * 对这个集合进行种种操作才能得到想要结果的迭代方式，叫做外部迭代。
     *    与外部迭代所对应的，则是内部迭代，内部迭代与之相反，是集合本身内部通过流进行了处理
     *  之后，程序猿们只需要直接取结果就行了，这种迭代称为内部迭代。
     */
    @Test
    public void test001(){

        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("东风","伦敦")
        );

        /**1、外部迭代**/
        int count = 0;
        for(Artist artist: allArtists){
            if(artist.isFrom("伦敦")){
                count++;
            }
        }
        System.out.println(count);

        /**2、内部迭代**/
        long count2 = allArtists.stream()//进行流操作
                .filter(artist -> artist.isFrom("伦敦")) //选出所有来自伦敦的艺术家
                .count();//统计他们的数量

        System.out.println(count2);
    }

    /**
     * 常用流的api
     *1.获取流对象
     * **/
    @Test
    public void test002(){
        /**对于集合来说,直接通过stream()方法即可获取流对象**/
        List<Person> list = new ArrayList<Person>();
        Stream<Person> stream = list.stream();

        /**对于数组来说,通过Arrays类提供的静态函数stream()获取数组的流对象**/
        String[] names = {"chaimm","peter","john"};
        Stream<String> stream2 = Arrays.stream(names);

        /**直接将几个普通的数值变成流对象**/
        Stream<String> stream3 = Stream.of("chaimm","peter","john");
    }

    /**
     * 常用流的api
     * 2.collect(toList())
     *
     * collect（Collectors.toList()）方法是将stream里的值生成一个列表，也就是将流再转化成为集合，是一个及早求值的操作。
     * <blockquote><pre>
     *       list.stream()//将集合转化成流
     *         .???()//一系列惰性求值的操作,返回值为stream
     *         .collect(toList())//及早求值，这个及早求值的方法返回值为集合,再将流转化为集合
     * </pre></blockquote>
     * **/
    @Test
    public void test003(){
        /**
         * 关于惰性求值与及早求值，这里简单说明一下，这两者最重要的区别就在于看操作有没有具体的返回值(或者说是否产生了具体的数值)
         * ，比如上文的的统计来自英国艺术家人数的代码，第二行代码的操作是首先筛选出来自英国的艺术家，这个操作并没有实际的数值产生，
         * 因此这个操作就是惰性求值，而最后的count计数方法，产生了实际的数值，因此是及早求值。惰性求值是用于描述stream流的，因此
         * 返回值是stream，而几乎所有对于流的链式操作都是进行各种惰性求值的链式操作，最后加上一个及早求值的方法返回想要的结果。
         *   你可以用建造者的设计模式去理解他，建造者模式通过一系列的操作进行设置与配置操作，最后调用一个build方法，创建出相应的
         * 对象。对于这里也是同样，调用各种惰性求值的方法，返回一个stream流，最后一步调用一个及早求值的方法，得到最终的结果。
         * 那么现在对于这个collect(toList())，使用方法就十分明了了。
         */

    }

    /**
     * 常用流的api
     * 3.筛选filter
     *    filter函数接收一个Lambda表达式作为参数，该表达式返回boolean，在执行过程中，流将元素逐一输送给filter，并筛选出执行结果为true的元素。
     *
     */
    @Test
    public void test004(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("东风","伦敦"),
                new Artist("莎士比亚","意大利"),
                new Artist("莎士","伦敦")
        );

        /**筛选出来自英国的艺术家**/
        long count = allArtists.stream()
                .filter(artist -> artist.isFrom("伦敦"))//惰性求值筛选
                .count();//及早求值统计
        System.out.println(count);

    }

    /**
     * 常用流的api
     * 4.去重distinc
     *
     */
    @Test
    public void test005(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("鲁迅","北京"),
                new Artist("东风","伦敦"),
                new Artist("莎士比亚","意大利"),
                new Artist("莎士","伦敦"),
                new Artist("大仲马","伦敦")
        );

        /**只增加了一行，便达到了筛选出所有来自英国的艺术家，并且去掉重复的名字之后的统计数量的目的**/
        long count = allArtists.stream()
                .filter(artist -> artist.isFrom("伦敦"))
                .distinct()
                .count();
        System.out.println(count);
    }

    /**
     * 常用流的api
     * 5.截取limit
     */
    @Test
    public void test006(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("鲁迅","北京"),
                new Artist("东风","伦敦"),
                new Artist("莎士比亚","意大利"),
                new Artist("莎士","伦敦"),
                new Artist("大仲马","伦敦")
        );

        /**截取流的前N个元素**/
        List<Artist> list = allArtists.stream()
                .filter(artist -> artist.isFrom("伦敦"))
                .limit(2)
                .collect(toList());
        list.forEach(System.out::println);
    }

    /**
     * 常用流的api
     * 6.跳过skip
     */
    @Test
    public void test007(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("鲁迅","北京"),
                new Artist("东风","伦敦"),
                new Artist("莎士比亚","意大利"),
                new Artist("莎士","伦敦"),
                new Artist("大仲马","伦敦")
        );
        /**跳过流的前N个元素：**/
        long count = allArtists.stream()
                .skip(2)
                .filter(artist -> artist.isFrom("伦敦"))
                .count();
        System.out.println(count);

        List<Artist> list =allArtists.stream()
                .filter(artist -> artist.isFrom("伦敦"))
                .skip(1)
                .collect(toList());
        list.forEach(System.out::println);
    }

    /**
     * 常用流的api
     * 7.映射map
     *   如果有一个函数可以将一种类型的值转换成另外一种类型,map操作就可以使用该函数，将一个流中的值转换成一个新的流。
     */
    @Test
    public void test008(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京"),
                new Artist("鲁迅","北京"),
                new Artist("东风","伦敦"),
                new Artist("莎士比亚","意大利"),
                new Artist("莎士","伦敦"),
                new Artist("大仲马","伦敦")
        );

        /**
         * 如果有一个函数可以将一种类型的值转换成另外一种类型,map操作就可以使用该函数，将一个流中的值转换成一个新的流。
         * 映射这个操作其实在大家编程的过程中都经常用到，也就是将A映射成B A->B
         *
         * 还是用艺术家的例子，现在要获得一个包含所有来自伦敦艺术家的名字的集合
         */
        List<String> artistNames = allArtists.stream()
                .filter(artist -> artist.isFrom("伦敦"))
                .map(artist -> artist.getName())//将艺术家集合映射成了包含艺术家名字的集合
                .collect(toList());
        /**请注意，这里的传递的Lambda表达式必须是Function接口的一个实例，Function接口是只包含一个参数的普通函数接口。**/

        artistNames.forEach(System.out::println);
    }

    /**
     * 常用流的api
     * 8.flatMap
     *   已经介绍过map操作，它可以用一个新的值代替stream里的值，但有时候，用户希望让map操作有点变化，生成一个新的steram对
     * 象取而代之，用户通常不希望结果是一连串的流，此时flatMap能够派上用场。
     *   通俗的一点的说法是，他可以将一条一条的小流，汇聚成一条大流，好比海纳百川的感觉。
     */
    @Test
    public void test009(){
        /**已经介绍过map操作，它可以用一个新的值代替stream里的值，但有时候，用户希望让map操作有点变化，生成一个新
         * 的steram对象取而代之，用户通常不希望结果是一连串的流，此时flatMap能够派上用场。
         *
         * 假设有一个包含多个集合的流，现在希望得到所有数字的序列，利用flatMap解决办法如下
         ***/

        List <Integer> together = Stream.of(asList(1,2),asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());

        together.forEach(n -> System.out.println(n));

        /**
         * 输出结果为1,2,3,4
         * 你看，2条小流被整合成了一条流！（这就是为什么这个类库叫做stream，流的意思，十分的形象化）
         *
         * steram流，在java8里，你可以理解成流水线，流水线的上的商品就是集合里一个个的元素，而这些对于流的各种各样的流操作，就是流水线上加工这些商品的机器。所以呢，stream流的相关特性与之也符合
         *
         * 不可逆，无论是河流，水流，还是流水线，没听过有倒流的，因此java8中的流也同样如此，你不可能在操作完第一个元素之后回头再重新操作，这在流操作里是无法完成的。
         *
         * 另一个特性就是内部迭代，这在一开始已经讲述过了。
         * 为什么到这里我才做不可逆的特性说明呢，因为我觉得flatMap很能符合流的特点，水流嘛，海纳百川，不可逆流，你看，完美符合java8的流特性。
         */

    }

    /**
     * 常用流的api
     *  9、max和min
     */
    @Test
    public void test010(){
        List<Artist> allArtists = asList(
                new Artist("文艺","北京",22),
                new Artist("鲁迅","北京",89),
                new Artist("东风","伦敦",18),
                new Artist("莎士比亚","意大利",35),
                new Artist("莎士","伦敦",56),
                new Artist("大仲马","伦敦",45)
        );
        /**
         * 例子：获得所有艺术家中，年龄最大的艺术家
         *    想一想，采用原始的外部迭代，要达到这么简单的要求是不是忽然感觉很麻烦？排个序？还是写一个交替又或者是选择比较的算法？何必
         * 这么麻烦！使用流操作采用内部迭代就好了，这不是我们程序猿应该专门写一段外部程序来解决的问题！
         *
         *    Stream上常用的操作之一是求最大值和最小值，事实上通过流操作来完成最大值最小值的方式有很多很多种，这里介绍的max和min的方法
         * 是stream类里的直接附带的方法，事实上在实际操作的时候我并不会选择这种操作方式
         *
         * 使用流操作如下:
         */
        Artist theMaxAgeArtist = allArtists.stream()
                .max(Comparator.comparing(artist -> artist.getAge())) //max()方法返回的是一个Optional对象
                .get(); //需要记住的是，通过get方法可以取得Optional对象中的值。
        System.out.println("获得所有艺术家中，年龄最大的艺术家:"+theMaxAgeArtist);

    }

    /**
     * 常用流的api
     * 10、归约reduce
     * reduce操作可以实现从一组值中生成一个值。在上述例子中用到的count，min，max方法，因为经常使用，所以被纳入了标准库里，实际上，这些方法都是由reduce操作实现的。
     *
     * reduce函数接收两个参数：
     *   + 初始值
     *   + 进行归约操作的Lambda表达式
     */
    @Test
    public void tes011(){

        /**举个例子 使用reduce进行求和
         *
         * reduce的第一个参数表示初始值为0；
         * reduce的第二个参数为需要进行的归约操作，它接收一个拥有两个参数的Lambda表达式，以上代码acc参数代表当
         * 前的数值总和,element代表下一个元素，reduce会把流中的元素两两输给Lambda表达式，最后将计算出累加之和。
         * 也就是说每次acc+element的返回值都会赋给acc
         * **/
        int count = Stream.of(1,2,3)
                .reduce(0,(acc,element) -> acc + element);
        System.out.println(count);

        /**上面的方法中我们自己定义了Lambda表达式实现求和运算，如果当前流的元素为数值类型，那么可以使用Integer提供了sum函数代替自定义的Lambda表达式，如：**/
        List<Integer> list =Arrays.asList(10,7,9,7,6,55,22,11);
        int age = list.stream().reduce(0, Integer::sum);
        System.out.println(age);
        /**
         * Integer类还提供了min、max等一系列数值操作，当流中元素为数值类型时可以直接使用。
         *
         * 注: 上面的Integer::sum如果不理解的话，这是java8中引用的方法，是一种简写语法，属于语法糖。
         * 一般格式为类名(或者是类的实例对象) :: 方法名（注意这里只是方法名，没有括号）,这里引用了Integer里的sum函数(java8里新增的)，下面是Integer里的sum函数源码
         * <pre>
         *      public static int sum(int a, int b) {
         *         return a + b;
         *     }
         * </pre>
         */
    }


    /**
     * 常用流的api
     * 11、Optional对象
     * Optional是Java8新加入的一个容器，这个容器只存1个或0个元素，它用于防止出现NullpointException，它提供如下方法：
     *
     * - isPresent()              判断容器中是否有值。
     * - ifPresent(Consume lambda)   容器若不为空则执行括号中的Lambda表达式。
     * - T get()                  获取容器中的元素，若容器为空则抛出NoSuchElement异常。
     * - T orElse(T other)          获取容器中的元素，若容器为空则返回括号中的默认值。
     *
     * 值得注意的是，Optional对象不仅可以用于新的Java 8 API,也可用于具体领域类中，和普通的类并没有什么区别，当试图避免空值相关的缺陷，如捕获的异常时，可以考虑一下是否可使用Optional对象。
     */
    public void test012(){

    }

    /**
     * 常用流的api
     * 12、peek 对每个元素执行操作并返回一个新的 Stream
     *
     * <pre>
     *     // 1. Individual values
     *     Stream stream = Stream.of("a", "b", "c");
     *
     *     // 2. Arrays
     *    String [] strArray = new String[] {"a", "b", "c"};
     *    stream = Stream.of(strArray);
     *    stream = Arrays.stream(strArray);
     *
     *    // 3. Collections
     *    List<String> list = Arrays.asList(strArray);
     *    stream = list.stream();
     * </pre>
     */
    @Test
    public void test013(){
        List<String> arr =Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        arr.forEach(System.out::println);
    }

    /**
     * 常用流的api
     * 13、Stream 有三个 match 方法，从语义上说：
     *      - allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     *      - anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     *      - noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     *
     * 它们都不是要遍历全部元素才能返回结果。例如 allMatch 只要一个元素不满足条件，就 skip 剩下的所有元素，返回 false。
     */
    @Test
    public void test014(){
        List<Person1> persons = new ArrayList();
        persons.add(new Person1(1, "name" + 1, 10));
        persons.add(new Person1(2, "name" + 2, 21));
        persons.add(new Person1(3, "name" + 3, 34));
        persons.add(new Person1(4, "name" + 4, 6));
        persons.add(new Person1(5, "name" + 5, 55));

        boolean isAllAdult = persons.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult(都是成年人)? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child(有小孩)? " + isThereAnyChild);

        boolean anyOld =persons.stream()
                .noneMatch(p ->p.getAge() >70);
        System.out.println("没有老年人? " + anyOld);

    }
}


class Artist{

    String name;
    String from;

    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    boolean isFrom(String _from){
        if(_from.equals(from)){
            return true;
        }
        return false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", age=" + age +
                '}';
    }

    public Artist(String name, String from) {
        this.name = name;
        this.from = from;
    }

    public Artist(String name, String from, int age) {
        this.name = name;
        this.from = from;
        this.age = age;
    }
}

class Person1 {

    int id;
    String name;
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person1() {
    }

    public Person1(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
