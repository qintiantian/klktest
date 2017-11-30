package klktest.concurrency;

public class ModuleA {

	void a(){
		System.out.println("A "+ThreadShare.getThreadData());
	}
}
