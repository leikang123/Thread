package edu.net.courseware.test.config;

import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;

import edu.net.courseware.cfg.CfgCenter;
import edu.net.courseware.cfg.CfgReader;
import edu.net.courseware.cfg.CfgReader.ChangeEvent;
import edu.net.courseware.cfg.CfgWriter;
import edu.net.courseware.zkclient.MyZkSerializer;

public class CfgTest {
	
	public static void main(String[] args) {
		ZkClient client = new ZkClient("localhost:2181");
		client.setZkSerializer(new MyZkSerializer());
		CfgWriter writer = new CfgCenter("/disCfg", client);
		
		String fileName = "mall-app.properties";
		Properties file = new Properties();
		file.put("cpu", "4core");
		file.put("memory", "6G");
		file.put("hardDisk", "128G");
		System.out.println("删除文件");
		writer.delete(fileName);
		
		System.out.println("创建文件");
		writer.create(fileName, file);
		
		
		
		new Thread(()->{
			while(true) {
				Properties file1 = writer.load(fileName);
				System.out.println("运维人员读取文件"+file1);
				
				file1.remove("hardDisk");
				file1.put("ssd", "256G");
				file1.put("memory", "16G");
				System.out.println("修改配置文件");
				writer.modify(fileName, file1);
				
				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		
		CfgReader reader = new CfgCenter("/disCfg", client);
		Properties readeFile = reader.load(fileName);
		System.out.println("加载到的内容："+readeFile);
			new Thread(()->{
			reader.watch(fileName, new ChangeEvent() {
				@Override
				public void dataChange(Properties properties) {
					System.out.println("读取到配置发生变化："+properties);
				}
			});
		}).start();
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
