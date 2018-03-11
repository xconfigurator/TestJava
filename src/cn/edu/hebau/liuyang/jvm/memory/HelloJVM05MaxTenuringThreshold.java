package cn.edu.hebau.liuyang.jvm.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * -XX:MaxTenuringThreshold
 * @author liuyang
 *
 */
public class HelloJVM05MaxTenuringThreshold {

	public static void main(String[] args) throws InterruptedException {
		// 观察是不是15次gc后新生代内存就会有明显变化（15次后新生代对象将被放入老年代）
		// -Xms1024m -Xmx1024m -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails
		// -Xms1024m -Xmx1024m -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails -XX:+PrintHeapAtGC
		Map<Integer, byte[]> m = new HashMap<Integer, byte[]>();
		for (int i = 0; i < 5; i++) {
			byte[] b = new byte[1024 * 1024];
			m.put(i, b);
		}
		
		// 下面这段代码是为了让程序别停下来，同时挤压eden, 并给gc足够的时间
		for (int i = 0; i < 300 * 20; i ++) {
			byte[] b = new byte[1024 * 1024];
		}
		
		//Thread.sleep(1000 * 10);
	}

}
