package 线程池;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 线程池类
public class TestThreadPoolWithCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	// 创建一个线程池，规定数量的线程池
        ExecutorService pool = Executors.newFixedThreadPool(4);
        ArrayList<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 6; i++) {
            Future<Integer> result = pool.submit(() ->{
                //向线程池中提交6个任务（每个任务：求1-10之和）
                    int sum = 0;
                    for (int j = 0; j <= 10; j++) {
                        sum += j;
                    }
                    return sum;
            });
            //从Future中获取result的结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
            futureList.add(result);
        }
        for (Future<Integer> future : futureList) {
            //获取并打印各个任务的结果
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
