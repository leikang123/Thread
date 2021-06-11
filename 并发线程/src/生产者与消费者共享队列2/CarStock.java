package 生产者与消费者共享队列2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarStock {
	int cars;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
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
	public  void productCar()  {
		lock.lock();
		CarData cardata = new CarData();
		// 向队列中增加一个对象
		boolean sucess = false;
		try {
			sucess = this.queue.offer(cardata,2,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sucess) {
			int id = ++count;
			cardata.setId(id);
			System.out.println("生产CarData,编号:"+id+",库存:"+queue.size());
			try {
				Thread.currentThread().sleep((int) (1000*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			condition.signalAll();
		}else {
			System.out.println("生产CarData失败。。。");
			System.out.println("生产CarData失败。。。");
		}if(queue.size()<100) {
			System.out.println("库存挤满了，等待消费。。。。");
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				// 释放锁
				lock.unlock();
			}
		}
		
		
	}
// 消费车的方法
    public  void resumeCar() {
    	// 加锁
    	lock.lock();
        try {
            // 从CarData队列中，拿走一个CarData对象
            CarData carData = this.queue.poll(2, TimeUnit.SECONDS);
            if (carData != null) {
                Thread.sleep((int) (1000 * Math.random()));
               condition.signalAll();
                System.out.println("消费CarData，编号：" + carData.getId() + "，库存: " + queue.size());
            } else {
                System.out.println("消费CarData失败....");
            }
            if (queue.size() > 0) {

            } else {
                System.out.println("库存为空,等待生产...");
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
        lock.unlock();
    }
    }
}
