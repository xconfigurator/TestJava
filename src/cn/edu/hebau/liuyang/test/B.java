package cn.edu.hebau.liuyang.test;

import cn.edu.hebau.liuyang.test2.A;

public class B extends A {
	public void printInfo() {
		System.out.println("I'am B.");
	}
	
	public B() {
		System.out.println("B");
	}
	
	/*
	@Override
	protected void finalize() throws Throwable {
		//super.finalize();
		System.out.println("~B");
	}
	*/
}
