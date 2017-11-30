package klktest.concurrency;

public class ModuleB {
	
	void b(){
		System.out.println("B "+ThreadShare.getThreadData());
	}
}
