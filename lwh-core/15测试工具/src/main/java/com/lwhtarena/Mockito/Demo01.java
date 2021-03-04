package com.lwhtarena.Mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @RunWith(MockitoJUnitRunner.class)，
 * 也可以在基类中用构造函数初始化，比如上面的例子对应的初始化代码：
 * <block>
 *     public Demo01(){
 *      MockitoAnnotations.initMocks(this);
 *     }
 * </block>
 * 也可以在测试方法中mock对象：
 * <block>
 *     List mockList = mock(List.class);
 * </block>
 *
 * @Mock+@RunWith使用比较方便，比较常用，可以应用与测试类中的各个测试方法中，如果多个测试方法中用到mockList，不需要每次都mock。
 */


@RunWith(MockitoJUnitRunner.class)
public class Demo01 {

    /**
     * 在基类中使用注解@Mock来mock一个list对象，需要在基
     * 类中添加初始化mock，可以使用在类注解
     */
    @Mock
    private List mockList;

    /**
     * 1、验证行为(verify some behaviour)
     */
    @Test
    public void verify_behaviours() {
        //using mock object
        mockList.add("one");
        mockList.clear();

        //验证行为是否发生
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    /**
     * 2、stub期望的结果
     */
    @Test
    public void when_thenReturn() {
        when(mockList.get(0)).thenReturn("first");
        when(mockList.get(1)).thenThrow(new RuntimeException());

        assertEquals("first", mockList.get(0));
        try {
            mockList.get(1);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * 3、验证调用次数
     */
    @Test
    public void verify_times(){
        mockList.add("once");

        mockList.add("twice");
        mockList.add("twice");

        mockList.add("three times");
        mockList.add("three times");
        mockList.add("three times");
        //下面的两种写法是一样的，times(1)默认使用
        verify(mockList).add("once");
        verify(mockList,times(1)).add("once");

        verify(mockList,times(2)).add("twice");
        verify(mockList,times(3)).add("three times");

        //never()表示从来没有的
        verify(mockList,never()).add("never happened");

        //atLeast,atMost意思也很明显
        verify(mockList,atLeastOnce()).add("three times");
        verify(mockList,atLeast(2)).add("three times");
        verify(mockList,atMost(5)).add("three times");
    }

    /**
     * 4、模拟void方法抛出异常
     */
    @Test(expected = RuntimeException.class)
    public void doThrow_when(){
        doThrow(new RuntimeException()).when(mockList).clear();

        //下面的语句抛出RuntimeException
        //如果不加expected的方式会抛出异常
        mockList.clear();
    }

    /**
     * 5、验证顺序
     */
    @Test
    public void verify_order() {
        //A.单个mock，其方法必须以特定顺序调用
        List singleMock = mock(List.class);

        //使用单个mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //为单个mock创建一个顺序验证程序
        InOrder inOrder1 = inOrder(singleMock);

        //以下是调用顺序的验证
        inOrder1.verify(singleMock).add("was added first");
        inOrder1.verify(singleMock).add("was added second");

        //B.多个mock必须按特定顺序调用
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");

        InOrder inOrder2 = inOrder(firstMock,secondMock);

        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }

    /**
     * 6、确保模拟对象没有互动发生
     */
    @Test
    public void verify_zero_interactions() {
        //mock- 只有mockOne发生交互
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        mockOne.add("one");

        //普通验证
        verify(mockOne).add("one");
        //验证从未在模拟上调用该方法
        verify(mockOne, never()).add("two");
        //验证其他模拟没有交互
        verifyZeroInteractions(mockTwo, mockThree);
    }

    /**
     * 7、查找冗余调用
     */
    @Test
    public void verify_no_more_interactions(){
        List mockList = mock(List.class);

        mockList.add("one");
        mockList.add("two");

        verify(mockList).add("one");

        //以下验证将失败
        verifyNoMoreInteractions(mockList);
    }

    /**
     * 8、连续调用
     */
    @Test public void test_consecutive_calls() {
        List mockList = mock(List.class);

        when(mockList.get(0))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        try {
            //第一次调用会抛出异常，为了保证正常运行进行捕获
            mockList.get(0);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        //后面的调用输出结果都为 "foo"
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(0));
    }

    /**
     * 9、通过回调生成期望值(callbacks)
     * 我们建议只使用thenReturn（）或thenThrow（）进行stub，这应足以测试/测试任何
     * 干净简单的代码。 但是，如果确实需要使用通用的Answer接口stub，这里有一个示例：
     *
     *
     * doReturn()、doThrow()、doAnswer()、doNothing()、doCallRealMethod()方法
     *
     * 对于任何方法，都可以使用doThrow（），doAnswer（），doNothing（），doReturn（）和doCallRealMethod（）来代替使用when（）的相应调用。
     *
     * 但是一下几种场景需要使用do的系列方法：
     *
     * - stub void方法
     * - stub methods on spy objects (see below)
     * - 不止一次地stub相同的方法，以在测试中改变模拟的行为。
     * 比如对void方法stub一个异常，使用doThrow();
     * <block>
     *   doThrow(new RuntimeException()).when(mockedList).clear();
     *
     *   //下面的调用就会抛出一个RuntimeException
     *   mockedList.clear();
     * </block>
     */
    @Test
    public void test_callbacks(){
        List mockList = mock(List.class);

        when(mockList.get(anyInt())).thenAnswer(
                new Answer<Object>() {
                    @Override
                    public Object answer(InvocationOnMock in) throws Throwable {
                        Object[] args = in.getArguments();
                        Object mock = in.getMock();
                        return "called with arguments: " + Arrays.toString(args);
                    }
                }
        );

        assertEquals("called with arguments: [0]",mockList.get(0));
        assertEquals("called with arguments: [1]",mockList.get(1));
        assertEquals("called with arguments: [2]",mockList.get(2));
    }

    /**
     * 10、Spy真实对象
     * 对真实对象进行spy，和部分mock的概念有些类似。
     * 有时候使用when().thenReturn 的方式对spy进行mock是可以的。
     */
    @Test
    public void test_spy_on_real_objects() {
//        List list = new LinkedList();
//        List spy = spy(list);
//
//        when(spy.size()).thenReturn(100);
//
//        spy.add("one");
//        spy.add("two");
//
//        System.out.println(spy.size());
//        System.out.println(spy.get(0));
//
//        verify(spy).add("one");
//        verify(spy).add("two");

        /**
         * 但是，有些情况下使用上面的方式是不行的，因此，在使用spy时还是
         * 尽可能的考虑使用doReturn | Answer | Throw 等系列方法：
         * **/

        List list = new LinkedList();
        List spy = spy(list);

        doReturn(100).when(spy).size();
        doReturn("foo").when(spy).get(0);

        System.out.println(spy.size());
        System.out.println(spy.get(0));
    }

}
