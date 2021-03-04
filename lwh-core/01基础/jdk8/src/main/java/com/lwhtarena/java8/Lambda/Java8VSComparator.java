package com.lwhtarena.java8.Lambda;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * <h2>简述：Java8 中Arrays.sort()及Collections.sort()中Lambda表达式及增强版Comparator的使用</h2>
 * <ol></ol>
 * <h2>功能描述：Java8 中Arrays.sort()及Collections.sort()中Lambda表达式及增强版Comparator的使用</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSComparator {

    @Test
    public void test001(){
        List<User> userList = Arrays.asList(new User("Jack",11),new User("Jack",10));

        /**1、Java8之前，使用匿名内部类的基本排序**/
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getAge().compareTo(user2.getAge());
            }
        });

        /**2. Java8,使用Lambda表达式的基本排序**/
        Collections.sort(userList,
                (User user1, User user2) ->user1.getAge().compareTo(user2.getAge()));

        //userList.sort((User user1, User user2) -> user1.getAge().compareTo(user2.getAge()));

        /**3. Java8,Lambda表达式可以简化，省略定义类型User**/
        userList.sort((user1, user2) -> user1.getAge().compareTo(user2.getAge()));

        /**4. Java8,Lambda表达式，多条件排序**/
        userList.sort((user1, user2) -> {
            if (user1.getName().equals(user2.getName())) {
                return user1.getAge() - user2.getAge();
            } else {
                return user1.getName().compareTo(user2.getName());
            }
        });

        /**5. Java8,多条件组合排序**/
        userList.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));

        /**6. Java8,提取Comparator进行排序**/
        Collections.sort(userList, Comparator.comparing(User::getName));

        /**7. Java8,自定义静态的比较方法来排序(静态方法必须写在被比较的类(这里是User类)中)**/
        userList.sort(User::compareByAgeThenName);

        /**8. Java8,反转排序**/
        Comparator<User> comparator = (user1, user2) -> user1.getName().compareTo(user2.getName());
        userList.sort(comparator);//先按name排序
        userList.sort(comparator.reversed());//反转排序
        Assert.assertEquals(userList.get(0),new User("Jack",10));
    }

    @Test
    public void test002(){
        /**
         * Arrays.sort()使用
         */
        //被排序的字符串数组
        String[] months = {"January","February","March","April","May","June","July","August","September","October","December"};
        //按字符串长度排序
        //1.
        Arrays.sort(months, (a, b) -> Integer.signum(a.length() - b.length()));
        //2.
        Arrays.sort(months, Comparator.comparingInt(String::length));
        //3.
        Arrays.sort(months, (a, b) -> a.length() - b.length());
        //4.
        Arrays.sort(months,
                (String a, String b) -> { return Integer.signum(a.length() - b.length()); }
        );

        System.out.println(Arrays.toString(months));
    }

    /**
     * 对A对象的集合进行排序
     */
    @Test
    public void test003(){
        List<String> lists = new ArrayList<String>();
        lists.add("5");
        lists.add("2");
        lists.add("9");

        /**lists中的对象String本身含有compareTo方法，所以可以
         * 直接调用sort方法，按自然顺序排序，即升序排序**/
        Collections.sort(lists);

        /**对A对象的集合进行排序**/
        List list =new ArrayList();
        A aa = new A();
        aa.setName("aa");
        aa.setOrder(1);
        A bb = new A();
        bb.setName("bb");
        bb.setOrder(2);
        list.add(bb);
        list.add(aa);

        //list中的对象A要实现Comparable接口
        Collections.sort(list);


        /**对B对象的集合排序**/
        List listB =new ArrayList();
        B ab = new B();
        ab.setName("ab");
        ab.setOrder("1");
        B ba = new B();
        ba.setName("ba");
        ba.setOrder("2");

        listB.add(ba);
        listB.add(ab);

        //listB中的对象如果没有实现Comparable接口，根据Collections.sort重载方法实现
        Collections.sort(listB, new Comparator<B>(){
            @Override
            public int compare(B b1, B b2) {
                return b1.getOrder().compareTo(b2.getOrder());
            }
        });
        System.out.println(list);
        System.out.println(listB);

        /**以上就是在java8之前的简单排序，是不是有些繁琐，后面来看下与之对应的排序在java8中如何实现，其实很简单只有几行代码**/

        /**在java8中可以使用Lanbda表达式**/
        Collections.sort(lists, (String a, String b)->{
            return b.compareTo(a);
        });

        /**或者**/
        Collections.sort(lists, (String a, String b) -> b.compareTo(a));

        /**或者**/
        Collections.sort(lists, (a, b) -> b.compareTo(a));
        System.out.println(lists.toString());


    }

    /**java8中集合操作**/
    @Test
    public void test004(){

        /**获取年龄为30的集合**/
        List<User> userList = Arrays.asList(new User("nihao", 20), new User("haoni", 30));
        userList = userList.stream().filter(user -> user.getAge() == 30).collect(Collectors.toList());
        System.out.println(userList.get(0).getAge());
    }

    /**对集合去重，分组操作**/
    @Test
    public void test005(){

        /**对集合去重，分组操作**/
        List<User> userList = Arrays.asList(new User("nihao", 20), new User("haoni", 30), new User("haobuhao", 30));
//        List<Integer> integerList = userList.stream().map(User::getAge).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());//降序
//        userList.forEach(user -> System.out.println(user.toString()));
//        integerList.forEach(i -> System.out.println(i));

        // 按年龄分组
        Map<Integer, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(userMap.get(20).size());
        System.out.println(userMap.get(30).size());

        Map<String, User> userNameMap = userList.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        System.out.println(userNameMap.get("nihao").getName());
        System.out.println(userNameMap.get("haoni").getName());
        System.out.println(userNameMap.get("haobuhao").getName());
    }

    /**map 循环**/
    @Test
    public void test006(){
        List<User> userList = Arrays.asList(new User("nihao", 20), new User("haoni", 30), new User("haobuhao", 30));
        Map<String,User> userMap =userList.stream()
                .collect(Collectors.toMap(User::getName,Function.identity()));
        userMap.forEach((name,user) ->{
            System.out.println(name + "===" + user.getAge());
        });
    }

    /**排序**/
    @Test
    public void test007(){
        //排序
        List<User> userList = Arrays.asList(
                new User("nihao", 20),
                new User("haoni", 30),
                new User("haobuhao", 30));

        System.out.println("===============按照年龄排序(升序)================");
        /**按照年龄排序**/
        userList = userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());//按照年龄排序
        userList.forEach(user -> {
            System.out.println(user.getAge() + ":"+ user);
        });

        System.out.println("===============按年龄倒叙排(降序)================");

        /**按年龄倒叙排**/
        userList.sort((user1, user2) -> user2.getAge().compareTo(user1.getAge()));//按年龄倒叙排
        userList.forEach(user -> {
            System.out.println(user.getAge() +":"+ user);
        });

        /**或者**/
        userList.sort(Comparator.comparing(User::getAge).reversed());
        userList.forEach(System.out::println);


        System.out.println("===============利用Collections工具来排序,按名字排序================");

        /**利用Collections工具来排序**/
        Collections.sort(userList, Comparator.comparing(User::getName));// 利用Collections工具来排序
        userList.forEach(user -> {
            System.out.println(user.getName() + ":"+ user);
        });

        System.out.println("===============利用Collections工具来排序,先按年龄排序再按名字排序================");
        /**如果想要写一些复杂逻辑**/
        Collections.sort(userList, (a, b) -> {
            if (a.getAge().equals( b.getAge())) {
                return a.getName().compareTo(b.getName());
            } else {
                return a.getAge() - b.getAge();
            }
        });
        userList.forEach(user -> {
            System.out.println(user.toString());
        });
    }

    /**
     * 多条件的排序两种写法 先根据年龄排序后姓名排序
     * **/
    @Test
    public void test008(){
        /**多条件的排序两种写法 先根据年龄排序后姓名排序**/

        //1 编写复杂的lambda表达式
        List<User> userList = Arrays.asList(
                new User("nihao", 20),
                new User("haoni", 30),
                new User("haobuhao", 30));

        userList.sort((user1, user2) -> {
            if (user1.getAge().equals(user2.getAge())) {
                return user1.getName().compareTo(user2.getName());
            } else {
                return user1.getAge() - user2.getAge();
            }
        });
        System.out.println("list1 =======");
        userList.forEach(user -> {
            System.out.println(user.toString());
        });

        //2 通过Comparator新的组合支持来实现。把多个Comparator链在一起（chain together）去建造更复杂的比较逻辑：
        List<User> user2List = Arrays.asList(
                new User("nihao", 20),
                new User("haoni", 30),
                new User("haobuhao", 30));
        user2List.sort(Comparator.comparing(User::getAge).thenComparing(User::getName)); //thenComparing实现链式调用
        System.out.println("list2 =======");
        userList.forEach(user -> {
            System.out.println(user.toString());
        });
    }
}

class User implements Serializable{
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**自定义排序**/
    public int compareByAgeThenName(User u){
        return this.name.compareTo(u.getName());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


class A implements Comparable<A>{
    private String name;
    private Integer order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "name is "+name+" order is "+order;
    }

    @Override
    public int compareTo(A a) {
        return this.order.compareTo(a.getOrder());
    }
}

class B{
    String name;
    String order;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "name is "+name+" order is "+order;
    }
}

