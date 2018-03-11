package cn.edu.hebau.liuyang.spring.essentials;

public class HelloThreadLocal {

	public static void main(String[] args) {
		// ThreadLocal存放现成局部变量的容器
		// 存放在ThreadLocal中的局部变量是线程安全的
		final ThreadLocal<String> ac = new ThreadLocal<>();
		ac.set("hello, threadlocal");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("thread1 :" + ac.get());
				ac.set("heihei");
				System.out.println("thread1 :" + ac.get());
				ac.set("xxxxxx");
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("thread2 :" + ac.get());
				ac.set("houhou");
				System.out.println("thread2 :" + ac.get());
				ac.set("yyyyyy");
			}
		}).start();
		
		
		System.out.println(ac.get());
	}

}
