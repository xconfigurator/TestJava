package cn.edu.hebau.liuyang.jvm.memory;

/**
 * 堆参数和基本调试参数
 * @author liuyang
 *
 */
public class HelloJVM01 {

	public static void main(String[] args) {
		// 测试JVM参数
		// 1. -XX:+PrintGC -Xms5m -Xmx20m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
		// 2. -XX:+PrintGC -Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
		
		// 系统初始化
		showJVMInformation();
		
		// 申请1MB空间
		byte[] b1 = new byte[1 * 1024 * 1024];
		System.out.println("f申请了1MB空间");
		showJVMInformation();
		
		// 申请4MB空间
		byte[] b2 = new byte[4 * 1024 * 1024];
		System.out.println("f申请了4MB空间");
		showJVMInformation();
		
		int a = 0x00000000fa0a0000;
		int b = 0x00000000fa801000;
		System.out.println("结果为：" + (b - a) / 1024);
	}
	
	public static void showJVMInformation() {
		// 查看信息
		System.out.println("#JVM INFO##########################################");
		System.out.println("#max memory:\t" + Runtime.getRuntime().maxMemory());
		System.out.println("#free memory:\t" + Runtime.getRuntime().freeMemory());
		System.out.println("#total memory:\t" + Runtime.getRuntime().totalMemory());
	}

}
