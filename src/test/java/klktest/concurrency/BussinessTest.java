package klktest.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;

public class BussinessTest {

	@Before
	public void beforeTest() {
		
	}
	
	@Test
	public void bizTest() {
		final Bussiness biz = new Bussiness(10,5);
		final int cycle = 5;
		new Thread(new Runnable() {
			public void run() {
				for(int i=1; i<=cycle; i++) {
					biz.sub(i);
				}
			}
		}).start();
		
		for(int i=1; i<=cycle; i++) {
			biz.main(i);
		}
	}
	
	@Test
	public void shareDataTest() {
		final CountDownLatch latch = new CountDownLatch(2);
		new Thread(new Runnable() {
			public void run() {
				int d = new Random().nextInt(1000000000);
				crossModules(d);
				latch.countDown();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				int d = new Random().nextInt(1000000000);
				crossModules(d);
				latch.countDown();
			}
		}).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	
	private static void crossModules(int d){
		System.out.println(Thread.currentThread().getName()+" data "+d);
		ShareData sd = ThreadShare.getThreadData();
		sd.setName(""+d);
		sd.setAge(d);
		new ModuleA().a();
		new ModuleB().b();
	}
}
