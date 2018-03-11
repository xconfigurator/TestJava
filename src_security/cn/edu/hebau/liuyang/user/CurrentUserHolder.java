package cn.edu.hebau.liuyang.user;

/**
 * 类似session的效果
 * 使用ThreadLocal实现
 * @author liuyang
 *
 */
public class CurrentUserHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<>();
	
	public static String get() {
		return holder.get() == null ? "unknown" : holder.get();
	}
	
	public static void set(String user) {
		holder.set(user);
	}
	
	/**
	 * 调用处
	 * CurrentUserHolder.set("liuyang");
	 * CurrentUserHolder.get()
	 */
}
