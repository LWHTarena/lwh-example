import com.lwhtarena.反射.案例1.annotation.LWHField;
import com.lwhtarena.反射.案例1.annotation.LWHTable;
import com.lwhtarena.反射.案例1.annotation.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


/**
 * 通过反射获取注解信息
 * @author lwh
 */
@SuppressWarnings("all")
public class Demo05 {

	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		Student stu =new Student();
		Class clazz =stu.getClass();

		System.out.println("==========获得类的所有有效注解==========");
		//获得类的所有有效注解
		Annotation[] annotations =clazz.getAnnotations();
		for(Annotation an:annotations){
			System.out.println(an);
		}

		System.out.println("===========获得类的指定的注解=========");
		//获得类的指定的注解
		LWHTable t =(LWHTable) clazz.getAnnotation(LWHTable.class);
		System.out.println(t.value());

		//获得类的属性的注解
		Field f = clazz.getDeclaredField("studentName");
		LWHField sxtField = f.getAnnotation(LWHField.class);
		System.out.println(sxtField.columnName()+"--"+sxtField.type()+"--"+sxtField.length());

		//根据获得的表名、字段的信息，拼出DDL语句，然后，使用JDBC执行这个SQL，在数据库中生成相关的表


	}

}
