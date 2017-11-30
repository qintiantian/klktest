package klktest.proxy;

public class UserServiceImpl implements UserService {

	public String getName(String id) {
		System.out.println("目标对象执行--getName--");
		return "Tom";
	}

	@Transaction(name="getAgeAnnotation")
	public int getAge(String id) {
		System.out.println("目标对象执行--getAge--");
		return 10;
	}

}
