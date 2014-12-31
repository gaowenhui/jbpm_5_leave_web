package com.toft.widgets.workflow.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileReadUtil {
	private String currentRecord = "";
	private BufferedReader fileStream;
	private String path;

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return returnStr：String 返回所读文件内容
	 * @throws FileNotFoundException
	 */
	public String ReadFile(String filePath) throws FileNotFoundException {
		path = filePath;
		fileStream = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), Charset.forName("utf-8")));
		String line = null;
		String returnStr = null;
		try {
			while ((line = fileStream.readLine()) != null) {
				currentRecord = currentRecord + line;
			}
		} catch (IOException e) {
			System.out.println("读取数据错误.");
		}
		if (currentRecord == null) {
			returnStr = "没有任何记录";
		} else {
			returnStr = currentRecord;
		}
		return returnStr;
	}
}
