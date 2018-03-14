package cn.edu.hebau.liuyang.concurrency.threadlocal;

public class PersonThreadLocal {

	private ThreadLocal<String> name = new ThreadLocal<>();
	
	PersonThreadLocal(String name) {
		setName(name);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);;
	}

}
