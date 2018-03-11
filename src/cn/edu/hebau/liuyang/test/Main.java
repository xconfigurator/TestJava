package cn.edu.hebau.liuyang.test;

import cn.edu.hebau.liuyang.test2.A;

public class Main {

	public static void main(String[] args) {
		A a = new B();
		a.printInfo();//I'am B.
		a = null;
		System.gc();
		
		/*
		A a1 = new A();
		a1.a = 2;
		*/
	}

}
