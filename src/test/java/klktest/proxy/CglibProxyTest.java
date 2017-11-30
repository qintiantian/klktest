package klktest.proxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxyTest {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(proxy);
		enhancer.setSuperclass(UserServiceImplDerive.class);
		
		UserService o =  (UserService) enhancer.create();
		o.getAge("1");
		o.getName("1");

	}

}
