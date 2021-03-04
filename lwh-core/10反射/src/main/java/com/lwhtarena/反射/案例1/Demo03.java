import com.lwhtarena.反射.案例1.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 通过反射api动态的操作：构造器、方法、属性
 * @author lwh
 *
 */
@SuppressWarnings("all")
public class Demo03 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		String path ="反射.案例1.bean.User";
		try {
			Class<User> clazz =(Class<User>) Class.forName(path);

			//通过反射api调用构造方法，构造对象
			User use =clazz.newInstance();//其实是调用了User的无参构造方法
			System.out.println(use);

			Constructor<User> c =clazz.getDeclaredConstructor(int.class,int.class,String.class);
			User use2 =c.newInstance(1001,18,"李文浩-liwenhao");
			System.out.println(use2.getUname());

			System.out.println("========通过反射api调用普通的方法==========");
			User u3 =clazz.newInstance();
			Method mothod =clazz.getDeclaredMethod("setUname",String.class);
			mothod.invoke(u3, "今天测试...");
			System.out.println("动态调用普通方法： "+u3.getUname());

			User u4 =clazz.newInstance();
			Field f =clazz.getDeclaredField("uname");
			f.setAccessible(true);//这个属性不需要做完全检查了，可以直接访问  -- 调用私有方法
			f.set(u4, "lwhtarena");//通过反射直接写属性
			System.out.println(u4.getUname());
			System.out.println(f.get(u4));//通过反射直接读取属性的值

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
