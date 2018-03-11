package cn.edu.hebau.liuyang;

public class HelloMath {

	public static void main(String[] args) {
		Double p1 = -0.4082;
		Double p2 = 0.8165;
		Double p3 = 0.4082;
		Double sum = Math.pow(p1, 2) + Math.pow(p2, 2) + Math.pow(p3, 2);
		Double result = Math.sqrt(sum);
		System.out.println(result);
	}

}
