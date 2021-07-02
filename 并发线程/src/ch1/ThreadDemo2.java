package ch1;
/**
 * 并发火车票的验证
 * @author mac1094
 *
 */
public class ThreadDemo2 implements Runnable{
// 定义100张火车票
	 private int ticket =100;
	@Override
	public void run() {
		while(true) {
			// 调用售票方法并运行这个方法
			sellTickets();
		}
		
	}
	// 写一个售票方法
	// 加上synchronized关键字锁后，线程就安全了
	public  synchronized void sellTickets() {
		// 表示有票
		if(ticket>0) {
			System.out.println(Thread.currentThread().getName()+":"+ticket);
			// 当购买一张票数后，票数就减少+
			ticket--;
			
		}
	}
	
	public static void main(String[] args) {
		// 创建对象
		ThreadDemo2 demo = new ThreadDemo2();
		// 创建线程并执行
		Thread t1= new Thread(demo);
		Thread t2= new Thread(demo);
		Thread t3= new Thread(demo);
		// 设置线程的姓名
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();
		
	}
}


	
