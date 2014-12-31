package com.toft.widgets.workflow.monitorFW.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * @author yxy
 * @create Jun 16, 2009 处理Clob与String的工具类，相互转化
 */
public class StringUtil {

	/**
	 * String转化为Document类型
	 * 
	 * @param str
	 * @return
	 */
	public static Document stringToDoc(String str) {
		Document document = null;
		if (str != null && !"".equals(str)) {
			try {
				document = DocumentHelper.parseText(str.trim());
				return document;
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return document;
	}
}
