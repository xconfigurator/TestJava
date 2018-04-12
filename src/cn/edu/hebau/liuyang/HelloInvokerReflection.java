package cn.edu.hebau.liuyang;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 反射方法调用其他Java程序
 * @author liuyang
 *
 */
public class HelloInvokerReflection {
	
	private static final String CLASSURL = "file:/F:/workspaces/workspace_oxygen/TestJava/bin/";
	private static final String CLASSNAME = "cn.edu.hebau.liuyang.Hello";
	
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		URL[] urls = new URL[] {new URL(CLASSURL)};
		URLClassLoader loader = new URLClassLoader(urls);
		Class clazz = loader.loadClass(CLASSNAME);
		clazz.getMethod("main", String[].class).invoke(null, (Object) new String[] {});
	}
}
