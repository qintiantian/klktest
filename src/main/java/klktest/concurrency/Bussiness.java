package klktest.concurrency;

public class Bussiness {
	private boolean shouldSub = true;
	private int main;
	private int sub;
	
	public Bussiness(int main, int sub) {
		super();
		this.main = main;
		this.sub = sub;
	}

	public Bussiness() {
		super();
	}

	public synchronized void main(int t) {
		while(shouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=1; i<=main; i++) {
			System.out.println(Thread.currentThread().getName()+" main work "+i+" of "+t);
		}
		System.out.println();
		shouldSub = true;
		this.notify();
	}
	
	public synchronized void sub(int t) {
		while(!shouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=1; i<=sub; i++) {
			System.out.println(Thread.currentThread().getName()+" sub work "+i+" of "+t);
		}
		System.out.println();
		shouldSub = false;
		this.notify();
	}

}
