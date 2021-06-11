package ch1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile的关键字的作用
 * 可见性和重排序
 * @author mac1094
 * AtmoicInteger是可以让volatile线程安全。
 *
 */
// volatile的线程是安全的吗？
// 线程不是安全的
public class demo01 {
	/**
	 * 此方式输出结果是197556，距离200000还很远，所以线程不安全
	 */
	// public static volatile int num = 0;
	/**
	 * 改用atomic原子性进行，可让线程安全
	 * 结果是200000
	 */
	public static AtomicInteger num = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<100;i++) {
		    // 创建线程
			Thread t = new Thread();
			// 运行线程
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					for(int i=0;i<200000;i++) {
						num.incrementAndGet();//自增功能
					}
					
				}
			};
			// 启动创建的线程
			t.start();
		}
		// 一条线程每隔3秒运行一个线程
		Thread.sleep(3000);
		System.out.println(num);
	}

}
