package com.lwhtarena.java8.Lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 * <h2>简述：Predicate和Consumer接口</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSFunction {

    /**
     * Predicate和Consumer接口的test()和accept()方法都接受一个泛型参数。不同的是test()方法进行某些逻辑判断并返回一个boolean值，而accept()接受并改变某个对象的内部值。
     * @param student
     * @param predicate
     * @param consumer
     * @return
     */
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer){

        /**使用Predicate接口实现类的test()方法判断输入的Student对象是否拥有费用打折的资格**/
        if ( predicate.test(student)){
            /**使用Consumer接口的实现类更新输入的Student对象的折扣**/
            consumer.accept(student);
        }
        return student;
    }

    @Test
    public void test001(){
        Student vo = new Student("埃里克森","里皮",50d);
        Student m =updateStudentFee(vo,x ->x.grade>100, x -> x.grade =30.0);
        System.out.println(m);

        Student student1 = new Student("Ashok","Kumar", 9.5);
        student1 = updateStudentFee(student1,
                //Lambda expression for Predicate interface
                student -> student.grade > 8.5,
                //Lambda expression for Consumer inerface
                student -> student.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("Rajat","Verma", 8.0);
        student2 = updateStudentFee(student2,
                student -> student.grade >= 8,
                student -> student.feeDiscount = 20.0);

        student2.printFee();

    }


    public static Student modify(Student student,Consumer<Student> consumer){
        consumer.accept(student);
        return student;

    }
    @Test
    public void test002(){
        List<Student> list = Arrays.asList(
                new Student("沛然","李",6d),
                new Student("走","司徒",12d),
                new Student("埃里克森","里皮",50d),
                new Student("克里斯","梅西",890d));
        for(Student s:list){
            modify(s,x->x.lastName ="木子李");
//            if(s.getFirstName().equals("走")){
//                s.setLastName("测试");
//            }
        }

        list.forEach(System.out::println);
    }



    private String test() {
        return null;
    }
    /**
     * 判断结果不为空后使用
     *
     * 乍一看代码复杂度上差不多甚至是略有提升；那为什么要这么做呢？
     * 一般情况下，我们在使用某一个函数返回值时，要做的第一步就是去分析这个函数是否会返回空值；如果没有进
     * 行分析或者分析的结果出现偏差，导致函数会抛出空值而没有做检测，那么就会相应的抛出空指针异常！
     * 而有了Optional后，在我们不确定时就可以不用去做这个检测了，所有的检测Optional对象都帮忙我们完成，
     * 我们要做的就是按上述方式去处理。！
     */
    @Test
    public void tes003(){
        /**如某个函数可能会返回空值，以往的做法：**/
        String s = test();
        /**以往写法：**/
        if (null != s) {
            System.out.println(s);
        }

        /**现在的写法**/
        Optional<String> ss = Optional.ofNullable(test());
        ss.ifPresent(System.out::println);
    }



    /**
     * 变量为空时提供默认值
     * 如要判断某个变量为空时使用提供的值，然后再针对这个变量做某种运算；
     */
    @Test
    public void test004(){
        /*如要判断某个变量为空时使用提供的值，然后再针对这个变量做某种运算； */
        /**以往写法：**/
        String s =null;
        if (null == s) {
            s = "test";
        }
        System.out.println(s);

        /**现在的写法**/
        Optional<String> o = Optional.ofNullable(s);
        System.out.println(o.orElse("test"));

    }

    /**
     * 变量为空时抛出异常，否则使用
     */
    @Test
    public void test005() throws Exception {

        /**以往写法：**/
        String s =null;
        if (null == s) {
            throw new Exception("test");
        }
        System.out.println(s);

        /**现在的写法**/
        Optional<String> o = Optional.ofNullable(s);
        System.out.println(o.orElseThrow(()->new Exception("test")));
    }


}


class Student{
    String firstName;

    String lastName;

    Double grade;

    Double feeDiscount = 0.0;

    Double baseFee = 20000.0;

    public Student(String firstName, String lastName, Double grade) {

        this.firstName = firstName;

        this.lastName = lastName;

        this.grade = grade;
    }

    public void printFee(){

        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);

        System.out.println("The fee after discount: " + newFee);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", feeDiscount=" + feeDiscount +
                ", baseFee=" + baseFee +
                '}';
    }
}
