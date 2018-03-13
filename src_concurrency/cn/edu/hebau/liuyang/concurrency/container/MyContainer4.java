package cn.edu.hebau.liuyang.concurrency.container;

import java.util.LinkedList;

/**
 * 面试题<br>
 * 需求：<br>
 * 编写一个固定容量的同步容器，拥有put和get方法，以及getCount方法。<br>
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用。<br>
 * 
 * 使用wait和notify/notifyAll来实现。<br>
 * 
 * @author liuyang
 *
 */
public class MyContainer4<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;// 最多10个元素
	private int count = 0;

	public synchronized void put(T t) {

		// 1. 满则等待
		while (list.size() == MAX) {// 注意是while而不是if。生产者-消费者情景下肯定是while。
			try {
				this.wait();// 参考《Effective Java》： wait一般都是和while配合，而不是和if。
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 2. put
		list.add(t);
		++count;

		// 3. 通知
		this.notifyAll();// 通知消费者线程进行消费
	}

	public synchronized T get() {
		T result = null;

		// 1. 空则等待
		while (list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 2. get
		result = list.removeFirst();
		--count;

		// 3. 通知
		this.notifyAll();// 通知生产者进行生产

		// 4. 返回
		return result;
	}

	public static void main(String[] args) {
		MyContainer4<String> c = new MyContainer4<>();

		// 启动Consumer * 10
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {// 每个消费者拿5个
					System.out.println(Thread.currentThread().getName() + " 正在消费 -> " + c.get());
				}
			}, "C" + i).start();
		}

		// 启动Producer * 2
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {// 每个生产者生产25个
					c.put(Thread.currentThread().getName() + " 生产的 " + j);
				}
			}, "P" + i).start();
		}
	}

}
