package cn.edu.hebau.liuyang.concurrency.threadlocal;

public class Person {
	private String name;
	
	Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
