package edu.net.courseware.cfg;

import java.util.Properties;

/**
 * 应用程序监听、读取配置
 */
public interface CfgReader {
	/**
	 * 读取
	 * @param fileName
	 * @return
	 */
	Properties load(String fileName);
	
	/**
	 * 监听配置文件
	 * @param fileName
	 * @param event
	 */
	void watch(String fileName, ChangeEvent event);
	
	/**
	 * 通知事件
	 */
	interface ChangeEvent{
		/**
		 * 告知发生变化后配置
		 * @param properties
		 */
		void dataChange(Properties properties);
	}
}
