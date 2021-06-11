package edu.net.courseware.cfg;

import java.util.Properties;

public interface CfgWriter {
	/**
	 * 创建
	 * @param fileName
	 * @param file
	 */
	void create(String fileName, Properties file);
	
	/**
	 * 修改
	 * @param fileName
	 * @param file
	 */
	void modify(String fileName, Properties file);
	/**
	 * 删除
	 * @param fileName
	 */
	void delete(String fileName);
	/**
	 * 读取
	 * @param fileName
	 * @return
	 */
	Properties load(String fileName);
}
