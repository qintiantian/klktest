package klktest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	public MyInvocationHandler() {
		super();
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.isAnnotationPresent(Transaction.class)) {
			System.out.println("----代理开启事务--------");
			Object result = method.invoke(target, args);
			System.out.println("----代理提交事务-------");
			return result;
		} else {
			Object result = method.invoke(target, args);
			return result;
		}
	}

}
