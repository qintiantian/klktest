package klktest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		InvocationHandler handler = new MyInvocationHandler(userService);
		UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), 
				userService.getClass().getInterfaces(), handler);
		userServiceProxy.getName("1");
		userService.getAge("1");
	}

}
