package cn.edu.hebau.liuyang.jvm.classloader;

/**
 * 演示类加载时机<br>
 * 加载->验证->准备(设置static值 )->解析->初始化
 * @author liuyang
 *
 */
public class Loader01 {
	
	static {
		System.out.println("静态初始化 Loader01");
	}

	public static void main(String[] args) {
		/*
		System.out.println("main: 注意观察我的输出位置！");
		A01 a = new A01();
		System.out.println("A01.width = " + A01.width);// 300
		A01 a2 = new A01();						// 类加载和初始化只进行一次，故不会再次输出A01和A01Super静态块中的内容。
		*/
		System.out.println(A01.super_width);
	}

}

class A01 extends A01Super{
	/*
	 * 静态域和静态代码块会在“初始化”阶段，通过执行<clinit>()方法进行初始化的。
	 */
	
	public static int width = 100;				// 静态域
	
	static {									// 静态代码块
		System.out.println("静态初始化 A01");
		width = 300;
	}
	
	public A01() {
		System.out.println("A01构造方法执行");
	}
}

class A01Super {
	
	public static int super_width = 100;
	
	static {
		System.out.println("静态初始化 A01Super");
	}
}
