package cn.edu.hebau.liuyang.jvm.classloader;

/**
 * 演示线程上下文类加载器<br>
 * 用于破坏双亲委派加载机制，灵活指定自己想要加载的类。
 * @author liuyang
 *
 */
public class Loader05ThreadContext {

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader loader = Loader05ThreadContext.class.getClassLoader();
		System.out.println(loader);
		
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader2);

		// 指定线程上下文类加载器
		// Thread.currentThread().setContextClassLoader()
		// Thread.currentThread().getContextClassLoader()
		//Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("f:/"));// 指定为自定义的ClassLoader
		System.out.println(Thread.currentThread().getContextClassLoader());
		
		// 使用前面指定的线程上下文类加载器来加载一些东西
		Class<Loader05ThreadContext> c = (Class<Loader05ThreadContext>) Thread.currentThread().getContextClassLoader().loadClass("cn.edu.hebau.liuyang.jvm.classloader.Loader05ThreadContext");
		System.out.println(c);
		
		// 看一下c的类加载器是谁
		System.out.println(c.getClassLoader());// 注意，如果在自定义的FileSystemClassLoader中也使用了父类委派机制，则有可能打印出来的还是sun.misc.Launcher$AppClassLoader@73d16e93
	}

}
