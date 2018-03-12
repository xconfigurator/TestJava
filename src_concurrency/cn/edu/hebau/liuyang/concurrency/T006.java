package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 遇到异常的情况<br>
 * 如果出现异常，默认情况下锁会被释放。<br>
 * 故并发处理过程中，异常情况需要格外注意处理，否则有可能发生不一致的情况<br>
 * 场景：Web App中，多个Servle线程同时访问一个资源的情况下。<br>
 * 如果第一个线程抛出异常，其他线程就会进入临界区(Critical Region), 从而有可能造成数据不一致。<br>
 * 
 * @author liuyang
 *
 */
public class T006 {
	int count = 0;

	synchronized void m() {
		System.out.println(Thread.currentThread().getName() + " start");
		while (true) {
			count++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (count == 5) {
				int i = 1 / 0;// 此处模拟发生异常，锁将被释放，要想不是放，可以在这里进行catch，然后让循环继续。
			}
		}
		// System.out.println(Thread.currentThread().getName() + " end");
	}

	public static void main(String[] args) {
		T006 t = new T006();
		new Thread(t::m, "t1").start();
		// 如果抛出异常后锁不被释放，则后面两个线程永远不会获得处理机
		// 至于t1抛出异常后是t2获得处理机还是t3获得处理机要看当时的调度。实测两次都是t3活得处理机。
		new Thread(t::m, "t2").start();
		new Thread(t::m, "t3").start();
	}

}
