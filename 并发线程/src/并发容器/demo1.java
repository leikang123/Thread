package 并发容器;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class demo1 {
	public static void main(String[] args) {
		// 并发容易使用这个会报错 java.util.ConcurrentModificationException
		// Vector<String> v = new Vector<>();
		
		// 使用并发容器copyOnWrite
		//CopyOnWriteArrayList<String> v = new CopyOnWriteArrayList<>();
		CopyOnWriteArraySet<String> v = new CopyOnWriteArraySet<>();
		v.add("小王");
		v.add("小立");
		v.add("小抗");
	Iterator<String> s = v.iterator();
	while(s.hasNext()) {
		System.out.println(s.next());
		v.add("x");
	}
	}

}
