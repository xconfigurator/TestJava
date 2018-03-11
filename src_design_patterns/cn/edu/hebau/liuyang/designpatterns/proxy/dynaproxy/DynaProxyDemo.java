package cn.edu.hebau.liuyang.designpatterns.proxy.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 对不同的接口、不同的方法切入相同的逻辑
 * 相当于@Aspect
 * @author liuyang
 *
 */
public class DynaProxyDemo implements InvocationHandler {
	
	// 用Object接收被代理对象
	private Object target;
	
	public DynaProxyDemo(Object target) {
		this.target = target;
	}
	
	/**
	 * @param proxy	被代理对象
	 * @param method 被代理方法
	 * @param args	参数
	 * 
	 * @return 方法返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		
		try {
			// @Before
			System.out.println("#@Before");
			
			result = method.invoke(target, args);
			
			// @AfterReturning
			System.out.println("#@AfterReturning");
			
		} catch (Throwable e) {
			// @AfterThrowing
			System.out.println("#@AfterThrowing");
			throw e;
		} finally {
			// @After
			System.out.println("#@After");
		}
		
		return result;
	}

}
