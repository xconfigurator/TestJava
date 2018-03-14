package cn.edu.hebau.liuyang.concurrency.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal<br>
 * 每个线程单独维护自己的变量版本<br>
 * @author liuyang
 *
 */
public class Main {

	// 普通版本 名称被t2改了 写volatile名字一定会被修改
	volatile static Person p = new Person("xconfigurator");
	// 普通版本 名称被t2改了 如果不写volatile有可能名字不会被修改
	//static Person p = new Person("xconfigurator");
	// ThreadLocal版本-> 不希望线程之间相互影响！
	//volatile static PersonThreadLocal p = new PersonThreadLocal("xconfigurator");// 这种写法有迷惑性,但实际上只是在main线程中初始化了
	
	public static void main(String[] args) {
		
		// 线程1在2秒后打印
		new Thread(()->{
			System.out.println("t1 start");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(p.getName());// ThreadLocal版本拿到的是null
			System.out.println("t1 end");
		}).start();

		// 线程2在1秒后修改名字
		new Thread(()->{
			System.out.println("t2 start");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			p.setName("liuyang");
			System.out.println("t2 end");
		}).start();		
	
		
		System.out.println("main : " + p.getName());// main: xconfigurator
	}
}
