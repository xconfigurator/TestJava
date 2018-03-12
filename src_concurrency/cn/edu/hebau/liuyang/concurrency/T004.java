package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法。<br>
 * 一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁。<br>
 * 也就是说synchronized获得的锁是可重入的。<br>
 * @author liuyang
 *
 */
public class T004 {
	
	synchronized void m1() {
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();//调用m2,而且m2申请的就是this
		System.out.println("m1 end");
	}
	
	synchronized void m2() {
		System.out.println("m2 start");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2 end");
	}

	public static void main(String[] args) {
		T004 t = new T004();
		new Thread(t::m1).start();
	}

}
