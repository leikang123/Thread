package com.lk.zk;

public class Zookeeper {
	// zk建立会话属性
	// 主机名与服务器端口连接
	String connectString;
	// 等待客户端通信时间的最长时间，如果超过了这个时间，会话会断开。
	int sessionTimeout;
	// 监控客户端和服务端的会话情况，会发送通知
	Watcher watcher;
	
	public Zookeeper(String connectString,
			int sessionTimeout,
			Watcher watcher) {
	}
	
}
