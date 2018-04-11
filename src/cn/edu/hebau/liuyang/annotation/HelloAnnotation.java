package cn.edu.hebau.liuyang.annotation;

import java.lang.annotation.Annotation;

/**
 * 使用反射解析注解
 * @author liuyang
 *
 */
public class HelloAnnotation {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		Class clazz = Class.forName("cn.edu.hebau.liuyang.annotation.Student");
		
		// 获得类的所有注解
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation a : annotations) {
			System.out.println(a);
		}
		
		// 直接拿到指定类型的注解
		Entity entity = (Entity) clazz.getAnnotation(Entity.class);
		String tableName = entity.value();
		System.out.println(tableName);
		
		// 获取指定属性的注解
		java.lang.reflect.Field snf = clazz.getDeclaredField("studentName");
		Field sn = snf.getAnnotation(Field.class);
		String studentName = sn.columnName();
		System.out.println(studentName);
		
		// 拼SQL...
	}

}
