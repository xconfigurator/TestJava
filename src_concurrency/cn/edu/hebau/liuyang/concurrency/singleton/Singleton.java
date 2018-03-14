package cn.edu.hebau.liuyang.concurrency.singleton;

import java.util.Arrays;

/**
 * 单例——线程安全版本(这是一个不用加锁还可以实现懒加载的方法)
 * 线程安全单例方法，参考：
 * http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * 
 * @author liuyang
 *
 */
public class Singleton {

	// 2. 创建唯一实例。
	// private static Singleton03 instance;
	private static class Inner {
		private static Singleton instance = new Singleton();
	}

	// 1. 构造方法私有化，不允许外部直接使用new创建对象。
	private Singleton() {
	}

	// 3. 提供静态访问器。
	public static Singleton getInstance() {
		return Inner.instance;
	}

	// 测试1
//	public static void main(String[] args) {
//		Singleton s1 = Singleton.getInstance();
//		Singleton s2 = Singleton.getInstance();
//		System.out.println(s1 == s2);
//	}
	
	// 测试2
	public static void main(String[] args) {
		Thread[] threads = new Thread[200];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				Singleton.getInstance();
			}) ;
		}
		
		Arrays.asList(threads).forEach(o -> o.start());
	}

}
