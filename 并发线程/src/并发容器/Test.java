package 并发容器;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class Test {
	public static void main(String[] args) {
		BlockingQueue<MyJob> pq = new PriorityBlockingQueue<MyJob>();
		pq.add(new MyJob(3));
		pq.add(new MyJob(2));
		pq.add(new MyJob(1));
		System.out.println("队列"+pq);
		System.out.println("取出队列中的一个元素"+pq.poll().getId());
		System.out.println("队列"+pq);
		
	}

}
