package cn.edu.hebau.liuyang;

public class TestString {
	
	public static void main(String[] args) {
		
		/**
		 * 方式1：开辟一块堆内存空间，并会自动保存在对象池之中以供下次重用。
		 */
		String s11 = "hello";
		/**
		 * 方式2：会先形成一个匿名字符串对象，然后调用String构造方法形成相同内容的字符串对象并返回引用。
		 * 匿名内部对象将成为垃圾，不会入池。
		 */
		String s12 = new String("hello，world");
		
		String s21 = new String("hello, world intern").intern();// 手工强制入池，以供重用
		String s22 = "hello, world intern";// 会返回对象池中的字符串对象的引用。
		System.out.println(s21 == s22);// true
		
		System.out.println(initcap("heLLo"));
		
		// int aa = 10;
		//aa = 11;
		
		
		int sw1 = 10, sw2 = 9;
		System.out.printf("sw1 = %d , sw2 = %d\n", sw1, sw2);
		swap(sw1, sw2);
		System.out.printf("sw1 = %d , sw2 = %d\n", sw1, sw2);
		
		
	}
	
	
	// 示例，没有做相应的容错！
	public static String initcap(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static void swap (int a, int b) {
		int tmp  = a;
		a = b;
		b = tmp;
	}

}
