package cn.edu.hebau.liuyang.designpatterns.proxy.dynaproxy;

import java.lang.reflect.Proxy;

import cn.edu.hebau.liuyang.designpatterns.proxy.MyInterface;
import cn.edu.hebau.liuyang.designpatterns.proxy.MyInterfaceImpl1;
import cn.edu.hebau.liuyang.designpatterns.proxy.MyInterfaceImpl2;

public class Main {

	public static void main(String[] args) {
		// 通过设定系统变量，查看动态生成的字节码
		// 运行后在Navigator视图下，找com.sun.proxy文件夹下的对应类
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		
		
		// 1. 对实现MyInterface的两个不同的类织入DynaProxyDemo中的切面逻辑
		System.out.println("######################################");
		MyInterface m1 = (MyInterface) Proxy.newProxyInstance(
				Main.class.getClassLoader()
				, new Class[] {MyInterface.class}
				, new DynaProxyDemo(new MyInterfaceImpl1())
				);
		m1.saySomething();
		
		System.out.println("######################################");
		MyInterface m2 = (MyInterface) Proxy.newProxyInstance(
				Main.class.getClassLoader()
				, new Class[] {MyInterface.class}
				, new DynaProxyDemo(new MyInterfaceImpl2())
				);
		m2.saySomething();
		
		
		// 2. 对m1织入DynaProxyTimer切面逻辑——程序运行记时间
		MyInterface m11 = (MyInterface) Proxy.newProxyInstance(
				Main.class.getClassLoader()
				, new Class[] {MyInterface.class}
				, new DynaProxyTimer(new MyInterfaceImpl1())
				);
		m11.saySomething();// 享受计时服务		
	}

}
