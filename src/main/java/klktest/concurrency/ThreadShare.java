package klktest.concurrency;

public class ThreadShare {

	private static ThreadLocal<ShareData> map = new ThreadLocal<ShareData>(){
		protected ShareData initialValue() {
			return new ShareData();
		};
	};
	
	public ThreadShare() {
	}
	
	public static ShareData getThreadData() {
		return map.get();
	}
}
class ShareData {

	private String name;
	private int age;
	public ShareData(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public ShareData() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void reset() {
		name = null;
		age = 0;
	}
	@Override
	public String toString() {
		return Thread.currentThread().getName()+" ShareData [name=" + name + ", age=" + age + "]";
	}
}
