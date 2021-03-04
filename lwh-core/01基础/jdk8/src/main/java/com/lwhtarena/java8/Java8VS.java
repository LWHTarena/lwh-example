package com.lwhtarena.java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * <h2>简述：Java 8 Lambda 表达式</h2>
 * <ol>Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。</ol>
 * <ol>Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。</ol>
 * <ol>使用 Lambda 表达式可以使代码变的更加简洁紧凑。</ol>
 *
 * <h3>功能描述：使用 Lambda 表达式需要注意以下两点：</h3>
 * <ol>Lambda 表达式主要用来定义行内执行的方法类型接口例如，一个简单方法接口。在上面例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义了sayMessage的执行。</ol>
 * <ol>Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力</ol>
 *
 * <h3>功能描述：变量作用域</h3>
 * <ol>lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。</ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VS {
    /**
     * 以下是lambda表达式的重要特征:
     *
     * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
     * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
     * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
     * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
     */

    /**使用 java 7 排序**/
    private void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    /**使用 java 8 排序**/
    private void sortUsingJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    static int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    /**排序比较**/
    @Test
    public void test001(){
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        /**=== 使用 Java 7 语法: ===**/
        Java8VS tester = new Java8VS();
        System.out.println("使用 Java 7 语法: ");

        tester.sortUsingJava7(names1);
        System.out.println(names1);

        /**=== 使用 Java 8 语法: ===**/
        System.out.println("使用 Java 8 语法: ");
        tester.sortUsingJava8(names2);
        System.out.println(names2);
    }

    @Test
    public void test002(){

        /**1、类型声明**/
        MathOperation addition =(int a,int b) -> a+b;

        /**2、不用类声明**/
        MathOperation subtraction =(a,b) -> a-b;

        /**3、大括号中的返回语句**/
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        /**4、没有大括号及返回语句**/
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + Java8VS.operate(10, 5, addition));
        System.out.println("10 - 5 = " + Java8VS.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + Java8VS.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + Java8VS.operate(10, 5, division));

        /** 5、不用括号 **/
        GreetingService greetingService1 =message -> System.out.println("您好！"+ message);

        /** 6、使用括号 **/
        GreetingService greetingService2 =(message) -> System.out.println("您好！"+ message);

        greetingService1.sayMessage("张三");
        greetingService2.sayMessage("李四");


    }


    final static String salutation = "Hello! ";

    public interface Converter<T1,T2>{
        void convert(int i);
    }

    @Test
    public void test003(){

        /**
         * lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不
         * 能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
         */
        GreetingService greetingService =message -> System.out.println(salutation+message);
        greetingService.sayMessage("王五");

        /**也可以直接在 lambda 表达式中访问外层的局部变量：**/
        final  int num =1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3

        /**lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）**/
        int count =2;
//        count = 5; //报错信息：Local variable num defined in an enclosing scope must be final or effectively final
        Converter<Integer, String> s1 = (param) -> System.out.println(String.valueOf(param + count));
        s1.convert(2);

        /**在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。**/
//        String first = "";
        Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());  //编译会出错

    }

}
