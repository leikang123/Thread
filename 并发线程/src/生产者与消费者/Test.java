package 生产者与消费者;



// 测试方法
public class Test {
	public static void main(String[] args) {
		// 创建库存类对象
		CarStock carStock = new CarStock();
		// 创建生产者和消费者线程对象使用同一个对象
		CarProducter p = new CarProducter(carStock);
		CarConsumer c  = new CarConsumer(carStock);
		// 创建线程
		Thread p1 = new Thread(p);
		Thread p2= new Thread(p);
		Thread c1 = new Thread(c);
		Thread c2 = new Thread(c);
		p1.start();
		p2.start();
		c1.start();
		c2.start();
		
	}

}
