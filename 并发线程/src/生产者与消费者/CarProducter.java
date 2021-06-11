package 生产者与消费者;
// 生产车的线程
public class CarProducter implements Runnable{
	 public CarStock carStock;
	 
	public CarProducter(CarStock carStock) {
		this.carStock=carStock;
	}
	public void run() {
		while(true) {
			try {
				carStock.productCar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
