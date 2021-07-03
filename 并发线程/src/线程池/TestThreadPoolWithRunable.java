package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 线程池的创建
public class TestThreadPoolWithRunable {
    public static void main(String[] args) {
    	// 创建一个线程池，用于存放线程的
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 3; i++) {
            //向线程池中提交3个任务（任务：打印自己的线程名）
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名: " + Thread.currentThread().getName());
                }
            });
        }
        pool.shutdown();
    }
}
