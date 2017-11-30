package klktest.myweb.enums;

public enum OperationResult {

	SUCCESS("成功", 1),
	FAILED("失败", 0);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private OperationResult(String name, int value) {
		this.name = name;
		this.value = value;
	}
	private String name;
	private int value;
	
	@Override
	public String toString(){
		return name;
	}
}
