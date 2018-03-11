package cn.edu.hebau.liuyang.jvm.memory;

/**
 * TLAB
 * @author liuyang
 *
 */
public class HelloJVM07TLAB {

	public static void main(String[] args) {
		// TLAB分配
		// 注意这个项-XX:-DoEscapeAnalysis，否则打不出消息
		// -XX:+UseTLAB -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis
		// -XX:+UseTLAB -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis -XX:+PrintTLAB -XX:+PrintGC
		
		// 对比操作（实验结果并不稳定）
		// 1. 不设置JVM参数运行(即使用TLAB)
		// 2. 设置-XX:-UseTLAB
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 1000000000; i++) {
			alloc();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	public static void alloc() {
		byte[] b = new byte[2];
	}

}
