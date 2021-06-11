package com.lk.zk;

import java.nio.file.WatchEvent;

/**
 * zk服务器之间的一种监听
 *接受会话之间的健康情况，监控数据的变化，断开连接，重新连接等，会发送通知。
 *
 * @author mac1094
 *
 */
//定义一个监控接口
public interface Watcher {
	
	void process(WatchEvent event);

}
