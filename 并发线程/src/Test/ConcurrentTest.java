package Test;

// 并发线程的测试demo
public class ConcurrentTest {
	
	 private static final  long count = 100000;
	 // private static final Runnable Runnable = null;
	public static void main(String[] args) throws InterruptedException {
		// 串行方法
		serial();
		// 并发方法
		concurrent();
	}
	// 并发线程静态方法
	private static void concurrent() throws InterruptedException {
		// 开始时间
		 long start = System.currentTimeMillis();
		// 线程的创建
		 Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
			int a =0;
			for(long i = 0;i < count;i++) {
				a +=5;
			}
				
			}
		});
		// 线程开始运行
		 t.start();
		 int b= 0;
		 for(long i =0;i < count;i++) {
			 b --;
		 }
		// 线程 让步
		 }
		 }
		t.join();
		long time  = System.currentTimeMillis() - start;
		System.out.println("Concurrent:"+time+"ms,b="+b);
		 }
		
	// 串行线程方法
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
