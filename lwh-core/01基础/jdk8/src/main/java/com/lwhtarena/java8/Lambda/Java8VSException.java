package com.lwhtarena.java8.Lambda;

import org.junit.jupiter.api.Test;
import pl.touk.throwing.ThrowingConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * <h2>简述：Java 8 lambda表达式中的异常处理</h2>
 * <ol></ol>
 * <h2>功能描述：在Java 8 中，当写lambda表达式并处理异常时代码变得冗余不堪，下面主要介绍lambda表达式中一些异常处理方式。</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSException {

    /**
     * 处理 Unchecked 异常
     */
    @Test
    public void test001(){
        /**当i为0时会引发ArithmeticException异常。**/
        List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 20);
        integers.forEach(i -> System.out.println(50 / i));

        /**通常的做法是通过try…catch来处理异常，防止系统崩溃。**/
        List<Integer> integers2 = Arrays.asList(3, 9, 7, 0, 10, 20,0);
        integers2.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.err.println(
                        "Arithmetic Exception occured : " + e.getMessage());
            }
        });

        /**使用try…catch后代码不再像以前一样简洁。我们可以通过写一个包装方法来进行处理。**/
        List<Integer> integers3 = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers3.forEach(lambdaWrapper(i -> System.out.println(50 / i)));

        /**
         * 通过编写包装方法lambdaWrapper并接收一个lambda表达式，在该方法内会进行异常处
         * 理。因此对于同类问题就可以避免反复写try…catch。
         *
         * 再次改进增加一个异常类型， 可以抛出我们希望抛出的异常。
         * 类似的可以根据实际需求写出各种类型的函数式接口包装类，如Function, BiFunction, BiConsumer等。
         */
        List<Integer> integers4 = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers4.forEach(
                consumerWrapper(
                        i -> System.out.println(50 / i),
                        ArithmeticException.class));
    }


    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println(
                        "Arithmetic Exception occured : " + e.getMessage());
            }
        };
    }

    static <T, E extends Exception> Consumer<T> consumerWrapper(Consumer<T> consumer, Class<E> clazz) {

        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = clazz.cast(ex);
                    System.err.println(
                            "Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw ex;
                }
            }
        };
    }

    /**
     * 处理 Checked 异常
     */
    public void test002(){
        /**
         * 这个例子中writeToFile会抛出IOException。IOException是一个Checked异常，
         * 所以必须处理。因此要么抛出要么捕获。
         */
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
//        integers.forEach(i -> writeToFile(i)); /**提示需要抛出异常**/


    }
    static void writeToFile(Integer integer) throws IOException {
        // logic to write to file which throws IOException
    }

    /**
     *  从Lambda表达式中抛出Checked异常
     */
    @Test
    public void test003(){
        /**throws抛出异常仍然会提示错误unhandled IOException。**/
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(throwingConsumerWrapper(i -> writeToFile(i)));

    }

    /**包装类，这样在foreach中方法不但可以抛出异常同时又能保持简洁性。**/
    static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {

        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    /**
     * 在Lambda表达式中处理Checked异常
     *
     * 类似的，可以根据实际需求编写ThowingFunction, ThrowingBiFunction, ThrowingBiConsumer。
     */
    @Test
    public void test004(){
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);

        /**接着对throwingConsumerWrapper进行改造，使其能接收一个异常类型参数。**/
        integers.forEach(handlingConsumerWrapper(
                i -> writeToFile(i), IOException.class));
    }

    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {

        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

}

