package cn.edu.hebau.liuyang.jvm.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * -XX:PretenureSizeThreshold
 * @author liuyang
 *
 */
public class HelloJVM06PretenureSizeThreshold {

	public static void main(String[] args) {
		// 仔细观察两个测试用例，并不是什么情况下都需要同时指定-XX:-UseTLAB才能看出结果。
		testPretenureObjSmall();// 对象小，可以被放进TLAB，则此时就不会再被放入老年代的堆中
		//testPretenureObjLarge();// 对象大，直接被放在堆上。
	}
	
	public static void testPretenureObjSmall() {
		// 目的：对象大于1000就直接放入老年代
		// -Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
		// 结果发现老年代竟然是使用了0%。
		// 出现这种情况是因为：虚拟机对于体积不大的对象会优先把数据分配到TLAB区域中，因此就失去了在老年代分配的机会。
		// -Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB
		Map<Integer, byte[]> m = new HashMap<Integer, byte[]>();
		for (int i = 0; i < 5 * 1024; i++) {
			byte[] b = new byte[1024];// 1024 > 1000
			m.put(i, b);
		}
	}
	
	public static void testPretenureObjLarge() {
		// -Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
		Map<Integer, byte[]> m = new HashMap<Integer, byte[]>();
		for (int i = 0; i < 5 ; i++) {
			byte[] b = new byte[1024 * 1024];
			m.put(i, b);
		}
	}

}
