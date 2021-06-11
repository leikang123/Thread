package 生产者与消费者;



// 定义一个汽车类
public class CarStock {
	// 汽车库存属性
	public int cars;
	
// 写一个生产者去生产车的方法
	public synchronized void productCar() throws InterruptedException {
		 // 如果汽车小于20的话，必须生产
		if(cars<20) {
			System.out.println("生产车..."+cars);
			Thread.currentThread().sleep(100);
			cars++;
			// 通知正在监听carStock并且处于阻塞状态的线程。
			notifyAll();
		}else {
			wait();
		}
		
	}
	// 写一个消费者的方法
	public synchronized void consumeCar() throws InterruptedException {
		if(cars>0) {
			System.out.println("销售车..."+cars);
			Thread.currentThread().sleep(100);
			cars--;
			// 唤醒生产方法去生产车
			notifyAll();
			// 等待生产者生产车
			wait();
		}else {
			// 等待生产者生产车
		   wait();
		}
	}
}
