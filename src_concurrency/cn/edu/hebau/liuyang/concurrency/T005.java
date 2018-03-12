package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法。<br>
 * 方式二：子类调用父类的同步方法。
 * @author liuyang
 *
 */
public class T005 {
	
	synchronized void m() {
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		/*
		//T005 t = new T005();
		T005Child t = new T005Child();
		new Thread(t::m).start();
		*/
		
		new T005Child().m();
	}

}


class T005Child extends T005 {
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}