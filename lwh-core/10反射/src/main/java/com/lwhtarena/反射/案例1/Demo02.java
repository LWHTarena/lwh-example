import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 应用反射的API，获取类的信息(类的名字、属性、方法、构造器等)
 * @author lwh
 *
 */
@SuppressWarnings("all")
public class Demo02 {

	/**
	 * @param args
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, NoSuchMethodException {
		String path ="反射.案例1.bean.User";
		try {
			Class clazz =Class.forName(path);

			//获取类的名字
			System.out.println("获得包名+类名:"+clazz.getName());//获得包名+类名
			System.out.println("获得类名："+clazz.getSimpleName());//获得类名

			//获得属性信息
//			Field[] fields =clazz.getFields();//只能获得public的field
//			System.out.println(fields.length);
			Field[] fields =clazz.getDeclaredFields();//返回所有的属性
			System.out.println(fields.length);

			System.out.println("===========获得属性================");

			Field f =clazz.getDeclaredField("uname");
			System.out.println(f);

			for(Field temp :fields){
				System.out.println("User类的属性："+temp);
			}

			//获取方法信息
			System.out.println("===========获得方法================");
			Method[] methods =clazz.getDeclaredMethods();
			for(Method m :methods){
				System.out.println("拥有的方法："+m);
			}

			Method m01 =clazz.getDeclaredMethod("getUname", null);
			Method m02 =clazz.getDeclaredMethod("setUname", String.class);
			System.out.println("方法："+m01);
			System.out.println("方法："+m02);

			//获得构造信息
			System.out.println("===========获得构造器================");
			Constructor[] constructors =clazz.getConstructors();
			for(Constructor c :constructors){
				System.out.println("构造器："+c);
			}

			Constructor c01 =clazz.getDeclaredConstructor(null);
			Constructor c02 =clazz.getConstructor(int.class,int.class,String.class);
			System.out.println("获得构造器:"+c01);
			System.out.println("获得构造器:"+c02);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
