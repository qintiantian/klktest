package klktest.proxy;

import java.lang.reflect.Method;

public class AnotationTest {

	public static void main(String[] args) {
		Class<UserServiceImplDerive> clazz = UserServiceImplDerive.class;
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods) {
			Transaction a = method.getAnnotation(Transaction.class);
			System.out.println(method.getName());
			if(a != null)
				System.out.println(a.name());
		}
	}

}
