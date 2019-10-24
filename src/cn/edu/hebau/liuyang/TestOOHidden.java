package cn.edu.hebau.liuyang;

public class TestOOHidden {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		A c = new B();	
	}

}

class A {
	void func() {
		System.out.println("A");
	}
}

class B extends A {
	void func(String str) {
		System.out.println("B:" + str);
	}
}
