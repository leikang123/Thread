package Test;

public class ConcurrentTest {
	
	 private static final  long count = 100000;
	 // private static final Runnable Runnable = null;
	public static void main(String[] args) throws InterruptedException {
		// 串行方法
		serial();
		// 并发方法
		concurrent();
	}
	private static void concurrent() throws InterruptedException {
		 long start = System.currentTimeMillis();
		 Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
			int a =0;
			for(long i = 0;i < count;i++) {
				a +=5;
			}
				
			}
		});
		 t.start();
		 int b= 0;
		 for(long i =0;i < count;i++) {
			 b --;
		 }
		t.join();
		long time  = System.currentTimeMillis() - start;
		System.out.println("Concurrent:"+time+"ms,b="+b);
		 }
		
	
	private static void serial() {
		long start  = System.currentTimeMillis();
		int a =0;
		for(long i=0;i< count;i++) {
			a +=5;
		}
		int b= 0;
		for(int i=0;i< count;i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start ;
		System.out.println("serial:"+time+"ms,b="+b+",a=+a");
	}

}
