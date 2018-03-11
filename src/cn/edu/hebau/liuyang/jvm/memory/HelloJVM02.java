package cn.edu.hebau.liuyang.jvm.memory;

/**
 * 新生代调整
 * @author liuyang
 *
 */
public class HelloJVM02 {

	public static void main(String[] args) {
		// 1. eden 2 = from 1 + to 1
		// -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
		
		// 2. 
		// -Xms20m -Xmx20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
		
		// 3.
		// -XX:NewRatio=老年代/新生代
		// -Xms20m -Xmx20m -XX:NewRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
		
		byte[] b = null;
		for (int i = 0; i < 10; i++) {
			b = new byte[1 * 1024 * 1024];
		}
	}

}
