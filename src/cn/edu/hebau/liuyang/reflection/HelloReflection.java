package cn.edu.hebau.liuyang.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.edu.hebau.liuyang.annotation.Student;

public class HelloReflection {

	private static final String CLASS = "cn.edu.hebau.liuyang.annotation.Student";
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<Student> clazz = (Class<Student>) Class.forName(CLASS);
		
		// 调用类的无参构造方法实例化
		Student s1 = clazz.newInstance();// 需要Student类具有无参构造方法
		System.out.println(s1);
		
		// 调用类的有参构造方法实例化
		Constructor<Student> c1 = clazz.getDeclaredConstructor(int.class, String.class, int.class);
		Student s2 = c1.newInstance(1001, "liuyang", 35);
		System.out.println(s2);
		
		// 调用普通方法
		// s3.setStudentName("xconfigutrator");
		Student s3 = clazz.newInstance();
		Method m3 = clazz.getDeclaredMethod("setStudentName", String.class);
		m3.invoke(s3, "xconfigurator");
		System.out.println(s3);
		
		// 调用Field, 给私有Field设置值
		// 注意
		// 1. 如果调用非共有字段的时候需要使用getDeclaredField方法
		// 2. 使用f4.setAccessible(true);
		Student s4 = clazz.newInstance();
		Field f4 = clazz.getDeclaredField("studentName");
		f4.setAccessible(true);// 因为是私有字段，如果不设置这一句，下面的设置值操作就会抛异常。
		f4.set(s4, "crazyliuyang");			// 通过反射set私有字段
		System.out.println(f4.get(s4));		// 通过反射get私有字段
		System.out.println(s4);
		
	}

}
