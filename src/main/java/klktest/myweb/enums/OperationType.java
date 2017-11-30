package klktest.myweb.enums;

public enum OperationType {

	ADD("add", 1),
	UPDATE("update", 2),
	DELETE("delete", 3),
	QUERY("query", 4),
	BATCH_UPDATE("batch_update", 5),
	BATCH_ADD("batch_add", 6),
	BATCH_DELETE("batch_delete", 7),
	DEFAULT("default",0);
	
	private OperationType() {
	}
	
	private OperationType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	private String name;
	private int value;
	
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
	
	@Override
	public String toString(){
		return name;
	}
}
