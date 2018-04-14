package cn.edu.hebau.liuyang.lambda;

interface IMessage {
	int doSomeCalc(int x, int y);
}

public class HelloLambda {

	public static void main(String[] args) {
		func((s1, s2) -> s1 + s2, 10, 20);// 写法1： 推荐

		func((s1, s2) -> {
			s1 += 10;
			s2 -= 10;
			return s1 - s2;
		}, 10, 20);// 写法2：return与代码块配合使用
	}

	public static void func(IMessage msg, int a, int b) {
		System.out.println(msg.doSomeCalc(a, b));
	}
}