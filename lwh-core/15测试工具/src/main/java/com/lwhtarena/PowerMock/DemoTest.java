package com.lwhtarena.PowerMock;

import com.lwhtarena.PowerMock.被测试.Demo;
import com.lwhtarena.PowerMock.被测试.UserService;
import com.lwhtarena.PowerMock.被测试.Utils;
import org.junit.Test;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

/**
 * PowerMock简单实现原理
 * 1.当某个测试方法被注解@PrepareForTest标注以后，在运行测试用例时，会创建一个新的org.powermock.core.classloader.MockClassLoader实例，然后加载该测试用例使用到的类（系统类除外）。
 * 2.PowerMock会根据你的mock要求，去修改写在注解@PrepareForTest里的class文件（当前测试类会自动加入注解中），以满足特殊的mock需求。例如：去除final方法的final标识，在静态方法的最前面加入自己的虚拟实现等。
 * 3.如果需要mock的是系统类的final方法和静态方法，PowerMock不会直接修改系统类的class文件，而是修改调用系统类的class文件，以满足mock需求。
 */
public class DemoTest {

    @Spy //需要手动new
    @InjectMocks
    private Demo demo = new Demo();

    @Mock  // 非@Spy修饰的对象，可以不手动new，由mockito框架实例化
    private UserService userService;

    /**
     * 测试
     * @see Demo#dealUser()
     */
    @Test
    public void testDealUser() throws Exception {
        // correct
        PowerMockito.when(userService.getUserName(ArgumentMatchers.anyInt())).thenReturn("zero007");

        // error
        // PowerMockito.doReturn("zero007").when(userService.getUserName(ArgumentMatchers.anyInt()));

        // correct
        PowerMockito.doReturn("zero007").when(userService).getUserName(ArgumentMatchers.anyInt());

        // when 里面参数  如果方法返回值为void
        // error
        // PowerMockito.doNothing().when(userService.handle(ArgumentMatchers.anyString()));
        // correct
        PowerMockito.doNothing().when(userService).handle(ArgumentMatchers.anyString());
        // correct
        PowerMockito.doNothing().when(userService, "handle", ArgumentMatchers.anyString());


        // mock私有方法，demo 必须是mock或者spy的实例
        // 必须先录制预期行为
        PowerMockito.doNothing().when(demo, "say", ArgumentMatchers.anyString());

        demo.dealUser();
    }

    /**
     * @see Demo#say(String)
     */
    @Test
    public void testSay() throws Exception {

        // throw org.mockito.exceptions.misusing.UnfinishedStubbingException:
        //        PowerMockito.doReturn("zero").when(demo).sys(ArgumentMatchers.anyString());

        // throw org.mockito.exceptions.misusing.UnfinishedStubbingException:
        //        PowerMockito.doReturn("zero").when(demo.sys(ArgumentMatchers.anyString()));

        // correct
//        Mockito.doReturn("zero").when(demo).sys(ArgumentMatchers.anyString());
        // error   Mockito.doReturn("zero").when(demo.sys(ArgumentMatchers.anyString()));


        Mockito.when(demo.sys(ArgumentMatchers.anyString())).thenReturn("zero");
//        ---sys() :
        System.out.println(demo.sys("007"));

        Whitebox.invokeMethod(demo, "say", "Hello World!");//测试private方法
    }

    /**
     * @see Demo#work()
     */
    @Test
    public void testWork() throws Exception {
    }

    /**
     * @see Demo#getString()
     */
    @Test
    public void tetsGetString() throws Exception {
        //mock静态方法
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.getObject(ArgumentMatchers.any(Demo.class), ArgumentMatchers.anyString())).thenCallRealMethod();
        PowerMockito.doNothing().when(Utils.class, "deal");
        // PowerMockito.when(Utils.xxx(xx).thenReturn(xxx));
        Whitebox.setInternalState(Utils.class, "URL_STRING", "www.123.com");

        String result = demo.getString();
        System.out.println(result);
    }
}
