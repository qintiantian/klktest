package klktest.proxy;

public interface UserService {

	@Transaction(name = "getname")
	public String getName(String id);
	
	public int getAge(String id);
	
}
