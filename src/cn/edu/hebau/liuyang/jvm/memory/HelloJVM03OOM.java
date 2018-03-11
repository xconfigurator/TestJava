package cn.edu.hebau.liuyang.jvm.memory;

import java.util.Vector;

/**
 * OOM处理示例
 * @author liuyang
 *
 */
public class HelloJVM03OOM {

	public static void main(String[] args) {
		// -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=f:/HelloJVM03OOM.dump
		// 模拟内存溢出
		Vector<Byte[]> v = new Vector();
		for (int i = 0; i < 5; i++) {
			v.add(new Byte[1 * 1024 * 1024]);
		}
	}

}
