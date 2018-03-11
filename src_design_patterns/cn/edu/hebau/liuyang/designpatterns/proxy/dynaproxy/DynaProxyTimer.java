package cn.edu.hebau.liuyang.designpatterns.proxy.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Aspect 记录方法运行时间
 * @author liuyang
 *
 */
public class DynaProxyTimer implements InvocationHandler {
	
	private Object target;
	
	public DynaProxyTimer(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		long begin = 0;
		long end = 0;
		
		try {
			// @Before
			begin = System.currentTimeMillis();
		
			// 原逻辑
			result = method.invoke(target, args);
			
			// @AfterReturning
			end = System.currentTimeMillis();
			System.out.println("#程序运行了：" + (end - begin) + "ms");
		} catch (Throwable e) {
			throw e;
		} 
		
		return result;
	}

}
