package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03SynchronizedList {

	public static void main(String[] args) {
		List<String> list;
		list = new ArrayList<>();// 无锁
		list = Collections.synchronizedList(new ArrayList<>());// 有锁
	}

}
