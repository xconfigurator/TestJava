package cn.edu.hebau.liuyang.test2;

public class A {

	protected int a = 999;
	
	public void printInfo() {
		System.out.println("I'am A.");
	}
	
	public A() {
		System.out.println("A");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("~A");
	}
	
}
