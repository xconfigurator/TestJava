package cn.edu.hebau.liuyang.jvm.memory;

/**
 * 栈配置 -Xss
 * @author liuyang
 *
 */
public class HelloJVM04StackOverflow {
	
	private static int count = 0;
	
	public static void main(String[] args) {
		// -Xss1m -XX:+PrintCommandLineFlags
		// -Xss2m -XX:+PrintCommandLineFlags
		
		try {
			recursion();
		} catch (Throwable t) {
			System.out.println("调用最大深度：" + count);
			t.printStackTrace();
		}
	}
	
	public static void recursion() {
		count ++;
		recursion();
	}
}
