package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，对业务读方法不加锁<br>
 * 容易产生脏读问题（Dirty Read）// liuyang: 个人认为这个不叫“脏读”<br>
 * 处理：在读方法上也加相同的锁。
 * 场景：银行
 * @author liuyang
 *
 */
public class T003Account {

	String name;
	double balance;// 余额
	
	public synchronized void set(String name, double balance) {
		this.name = name;
		
		try {
			Thread.sleep(2000);// 放大问题
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.balance = balance;
	}
	
	// public synchronized double getBalance {// 解决
	public double getBalance(String name) {// 不加锁可能会产生问题
		// 仅演示用
		return this.balance;
	}
	
	public static void main(String[] args) throws InterruptedException {
		T003Account a = new T003Account();
		new Thread(()->a.set("zhangsan", 100.0)).start();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println(a.getBalance("zhangsan"));
		TimeUnit.SECONDS.sleep(2);
		System.out.println(a.getBalance("zhangsan"));
		
	}

}
