package cn.edu.hebau.liuyang.concurrency;

public class T001 implements Runnable {

	private int count = 0;
	
	public synchronized void run() {// Critical Region解决
	//public void run() {// 演示Critical Region
		count --;
		System.out.println(Thread.currentThread().getName() + "count = " + count);
	}
	
	public static void main(String[] args) {
		T001 t = new T001();
		/**
		 * 一份存储：t
		 * 5个实例
		 */
		for(int i = 0; i < 5; i++) {
			new Thread(t, "THREAD " + i).start();
		}
	}

}
