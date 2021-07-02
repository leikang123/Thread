package com.lk.zk;

import java.nio.file.WatchEvent;


// 事物的监控
public class Master implements Watcher{
	Zookeeper zk;
	String hostPort;
	
	

	public Master(String hostPort) {
		super();
		this.hostPort = hostPort;
	}

 public void startZK() {
	 // 实例化对象zookeeper,参数
	 Zookeeper zk = new Zookeeper(hostPort, 15000,this);
 }
 
	@Override
	public void process(WatchEvent event) {
		System.out.println(event);
			
	}
public static void main(String args[]) throws InterruptedException {
	Master m = new Master(args[1]);
	m.startZK();
	Thread.sleep(6000);
}
}
