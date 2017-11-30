package klktest.myweb.pub;

public interface IncrementIdGenerator {

	public long genId(String table);
	
	public long[] genIds(String table, int delta);
	
}
