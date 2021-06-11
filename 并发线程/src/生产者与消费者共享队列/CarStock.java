package 生产者与消费者共享队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarStock {
	 private static int count = 0;
	  private BlockingQueue<CarData> queue;
	  
	public CarStock(BlockingQueue<CarData> queue) {
		super();
		this.queue = queue;
	}
	
	  
       public CarStock() {
		super();
		
	}


	// 生产车的方法
	public synchronized void productCar() throws InterruptedException {
		CarData cardata = new CarData();
		// 向队列中增加一个对象
		boolean sucess = this.queue.offer(cardata,2,TimeUnit.SECONDS);
		if(sucess) {
			int id = ++count;
			cardata.setId(id);
			System.out.println("生产CarData,编号:"+id+",库存:"+queue.size());
			Thread.currentThread().sleep((int) (1000*Math.random()));
			notifyAll();
		}else {
			System.out.println("生产CarData失败。。。");
			System.out.println("生产CarData失败。。。");
		}if(queue.size()<100) {
			System.out.println("库存挤满了，等待消费。。。。");
			wait();
		}
	}
// 消费车的方法
    public synchronized void resumeCar() {
        try {
            // 从CarData队列中，拿走一个CarData对象
            CarData carData = this.queue.poll(2, TimeUnit.SECONDS);
            if (carData != null) {
                Thread.sleep((int) (1000 * Math.random()));
                notifyAll();
                System.out.println("消费CarData，编号：" + carData.getId() + "，库存: " + queue.size());
            } else {
                System.out.println("消费CarData失败....");
            }
            if (queue.size() > 0) {

            } else {
                System.out.println("库存为空,等待生产...");
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
