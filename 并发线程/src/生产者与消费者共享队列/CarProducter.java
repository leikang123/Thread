package 生产者与消费者共享队列;

public class CarProducter implements Runnable {
    //共享缓存区
    private CarStock carPool;
    //多线程的执行状态，用于控制线程的启停
    private volatile boolean isRunning = true;

    public CarProducter(CarStock carPool) {
        this.carPool = carPool;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
				carPool.productCar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

    //停止当前线程
    public void stop() {
        this.isRunning = false;
    }
}
