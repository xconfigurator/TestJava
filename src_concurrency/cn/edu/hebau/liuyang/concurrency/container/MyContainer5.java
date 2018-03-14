package cn.edu.hebau.liuyang.concurrency.container;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题<br>
 * 需求：<br>
 * 编写一个固定容量的同步容器，拥有put和get方法，以及getCount方法。<br>
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用。<br>
 * 
 * 使用Lock和Condition实现。<br>
 * 对比：Condition的方式可以更加精确地指定哪些线程等待，哪些线程被唤醒<br>
 * 
 * @author liuyang
 *
 */
public class MyContainer5<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;// 最多10个元素
	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		lock.lock();
		try {
			// 1. 满则等待
			while (list.size() == MAX) {// 用while而不是if
				producer.await();// 注意是await而不是wait，wait是Object中的方法，必须和synchronized配合使用。
			}

			// 2. put
			list.add(t);
			++count;

			// 3. 通知消费者
			consumer.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public T get() {
		T result = null;
		lock.lock();
		try {
			// 1. 空则等待
			while (list.isEmpty()) {// 用while而不是if
				consumer.await();
			}

			// 2. get
			result = list.removeFirst();
			--count;

			// 3. 通知生产者
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		// 4. 返回
		return result;
	}

	public static void main(String[] args) {
		MyContainer5<String> c = new MyContainer5<>();

		// 后面测测试用例和MyContainer4相同
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
