package edu.net.courseware.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkClientWatchDemo {

	public static void main(String[] args) {
		// 创建一个zk客户端
		ZkClient client = new ZkClient("localhost:2181");
		client.setZkSerializer(new MyZkSerializer());
		if(! client.exists("/net")) {
			client.createPersistent("/net", true);
		}
		
		/*
		 * new Thread(()->{ while(true) { String path =
		 * client.createPersistentSequential("/net", "");
		 * System.out.println("创建了/net下的顺序节点："+path); try { Thread.sleep(5000L); } catch
		 * (InterruptedException e) { e.printStackTrace(); } } }).start();
		 * 
		 * new Thread(()->{ while(true) { String data = client.readData("/net");
		 * System.out.println("读取到了/net节点数据内容："+data); try { Thread.sleep(5000L); }
		 * catch (InterruptedException e) { e.printStackTrace(); } } }).start();
		 */
		
		client.subscribeDataChanges("/net", new IZkDataListener() {

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("----收到节点被删除了-------------"+dataPath);
			}

			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println(dataPath+"----收到节点数据变化：" + data + "-------------");
			}
		});
		
		client.subscribeChildChanges("/net", new IZkChildListener() {
			
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println(parentPath+"----子节点数据变化：-------------"+currentChilds);
				
			}
		});

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
