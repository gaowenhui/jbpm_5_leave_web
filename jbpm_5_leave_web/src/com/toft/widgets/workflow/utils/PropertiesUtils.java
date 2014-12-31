package com.toft.widgets.workflow.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 操作属性文件辅助类
 * 
 * @author 邹庆林
 * @version 1.0
 */
public class PropertiesUtils {

	private static PropertiesUtils propertyUtil = null;

	private Properties properties = new Properties();

	private InputStream inputStream = null;

	private OutputStream outputStream = null;

	private PropertiesUtils() {
	}

	public static PropertiesUtils getPropertyUtil() {

		if (null == propertyUtil) {
			propertyUtil = new PropertiesUtils();
		}
		return propertyUtil;

	}

	// 根据key获取value
	public Properties getProperty(String propertyFileName) {
		FileInputStream inputStream = null;
		try {
			ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
			URL url = classLoader.getResource(propertyFileName);
			properties.load(url.openStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
					inputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	//根据key获取value
	public String getProperty(String propertyFileName, String key) {

		inputStream = Thread.currentThread().getClass().getResourceAsStream(
				propertyFileName);
		//		inputStream = Thread.currentThread().getClass().getClassLoader().getResourceAsStream(propertyFileName);

		//		URL url = Thread.currentThread().getClass().getResource(propertyFileName);

		//		URL url = this.getClass().getResource(propertyFileName);

		String value = "";

		try {
			//			inputStream = url.openStream();
			properties = new Properties();
			properties.load(inputStream);
			value = properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
					inputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return value;
	}

	//设置属性文件的key和value
	public Object setProperty(String propertyFileName, String key, String value) {

		URL url = Thread.currentThread().getClass().getResource(
				propertyFileName);
		String path = url.getPath();

		InputStream inputStream = Thread.currentThread().getClass()
				.getResourceAsStream(propertyFileName);
		Properties properties = new Properties();

		Object object = null;

		try {
			properties.load(inputStream);
			object = properties.setProperty(key, value);

			OutputStream outputStream = new FileOutputStream(path);
			properties.store(outputStream, "");

			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
					inputStream = null;
				}
				if (null != outputStream) {
					outputStream.close();
					outputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return object;
	}

	public static void main(String[] args) {
		PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
	}
}
