package cn.edu.hebau.liuyang.concurrency;

/**
 * 同步方法和非同步方法是否可以同时调用？ 在执行m1的时候m2能否被执行 答：可以！同步方法运行的过程中非同步方法是可以运行的。非同步方法不需要申请锁。
 * 
 * @author liuyang
 *
 */
public class T002 {

	public synchronized void m1() {// 相当于synchronized(this) {}
		System.out.println(Thread.currentThread().getName() + " m1 start ...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}

	public void m2() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2");
	}

	public static void main(String[] args) {
		T002 t = new T002();

		// 以下两组效果等价, t2 m2会在t1运行期间打印出来。
		// 写法1 JDK 8+
//		new Thread(() -> t.m1(), "t1").start();
//		new Thread(() -> t.m2(), "t2").start();

		// 写法2 JDK 8+
//		new Thread(t::m1, "t1").start();
//		new Thread(t::m2, "t2").start();

		// 写法3 JDK 7及以前
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}, "t1").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}, "t2").start();
	}

}
