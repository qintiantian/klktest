package klktest.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if(method.isAnnotationPresent(Transaction.class)){
			System.out.println("proxy before target invoke --");
			Object ret = proxy.invokeSuper(obj, args);
			Transaction act = method.getAnnotation(Transaction.class);
			System.out.println(act.name());
			System.out.println("proxy after target invoke --");
			return ret;
		}
		return proxy.invokeSuper(obj, args);
	}

	

}
