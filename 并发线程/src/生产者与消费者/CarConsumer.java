package 生产者与消费者;

//消费车的线程
public class CarConsumer implements Runnable{
		CarStock carStock;
		 public CarConsumer(CarStock carStock) {
			 this.carStock=carStock;
		}
		 
		

		@Override
		public void run() {
			while(true) {
				try {
					carStock.consumeCar();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
