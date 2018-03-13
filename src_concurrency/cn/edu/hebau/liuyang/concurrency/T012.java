package cn.edu.hebau.liuyang.concurrency;

/**
 * 不要以字符串常量作为锁定对象<br>
 * 在下面例子中，m1和m2中其实锁定的是同一个对象<br>
 * 这种情况还会发生比较诡异的现象，<br>
 * 比如你用到了一个类库，在该类库中代码锁定了字符串"hello"<br>
 * 但是你读不到源代码，但你在自己的代码中也锁定了"hello",这个时候就会发生“诡异”的阻塞<br>
 * 原因就是你的程序和类库不经意间使用了同一把锁。jetty曾经就存在这么一个隐蔽的bug。
 * 
 * @author liuyang
 *
 */
public class T012 {

	String s1 = "hello";
	String s2 = "hello";

	void m1() {
		synchronized (s1) {

		}
	}

	void m2() {
		synchronized (s2) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
