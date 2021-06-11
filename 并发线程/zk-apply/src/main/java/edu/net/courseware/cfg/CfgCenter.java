package edu.net.courseware.cfg;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;



/**
 * 配置中心
 */
public class CfgCenter implements CfgReader, CfgWriter {
	// 父节点
	String parentPath;
	
	// ZKclient
	ZkClient client;
	
	public CfgCenter(String path, ZkClient client) {
		// 省略参数检查
		
		this.parentPath = path;
		this.client = client;
		if(! client.exists(parentPath)) {
			client.createPersistent(parentPath);
		}
	}
	
	@Override
	public void create(String fileName, Properties file) {
		// 创建配置文件节点 /
		String name = parentPath+"/"+fileName;
		// 如果存在的话，爆出异常
		if(client.exists(name)) {
			throw new RuntimeException(name+" 文件已存在");
		}
		client.createPersistent(name);
		// 创建配置项内容
		Iterator<Object> ter = file.keySet().iterator();
		while(ter.hasNext()) {
			String key = ter.next().toString();
			String value = file.get(key).toString();
			key = name +"/"+key;
			client.createPersistent(key, value);
		}
	}

	@Override
	public void modify(String fileName, Properties file) {
		// 先删除、后创建
		delete(fileName);
		create(fileName, file);
	}

	@Override
	public void delete(String fileName) {
		// 省略检查
		String path = parentPath +"/"+ fileName;
		client.deleteRecursive(path);
	}

	@Override
	public Properties load(String fileName) {
		Properties prop = new Properties();
		// 通过文件Zode，找下面的配置项
		String path = parentPath +"/"+ fileName;
		List<String> children = client.getChildren(path);
		// 循环加载配置项
		children.forEach((e)->{
			String itemPath = path +"/"+ e;
			String value = client.readData(itemPath);	// get
			System.out.println("key:"+e+", path:"+itemPath+", value:"+value);
			prop.put(e, value);
		});
		return prop;
	}

	@Override
	public void watch(String fileName, ChangeEvent event) {
		// 监听文件——子节点——配置项
		String path = parentPath +"/"+ fileName;
		
		ScheduledThreadPoolExecutor scheduled = (ScheduledThreadPoolExecutor) 
				Executors.newScheduledThreadPool(1);
		scheduled.setRemoveOnCancelPolicy(true);
		
		CopyOnWriteArrayList<ScheduledFuture<?>> list = new CopyOnWriteArrayList<ScheduledFuture<?>>();
		
		// 获取子节点，变化通知
		client.subscribeChildChanges(path, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				// 收敛动作，5秒内发生变化，只通知一次
				for(int i = 0; i < list.size(); i++) {
					ScheduledFuture<?> sumfuture = list.get(i);
					if(sumfuture != null && ! sumfuture.isCancelled() && !sumfuture.isDone()) {
						sumfuture.cancel(true);
						i--;
					}
				}
				
				// 5秒延时处理
				ScheduledFuture<?> future = scheduled.schedule(()->{
					Properties prop = load(fileName);
					event.dataChange(prop);
				}, 5, TimeUnit.SECONDS);
				
				list.add(future);
			}
		});

	}

}
