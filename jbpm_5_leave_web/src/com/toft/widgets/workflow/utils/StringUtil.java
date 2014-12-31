package com.toft.widgets.workflow.utils;

import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.toft.core2.ToftException;

/**
 * @author yxy
 * @create Jun 16, 2009 处理Clob与String的工具类，相互转化
 */
public class StringUtil {
	/**
	 * clob转化String类型
	 * 
	 * @param clob
	 * @return
	 */
	public static String clobToString(Clob clob) {
		String s = "";
		if (clob != null) {
			Reader reader = null;
			boolean end = false;
			try {
				reader = clob.getCharacterStream();
				while (!end) {
					char[] ac = new char[1024];
					if (reader.read(ac) == -1) {
						end = true;
					}
					s += new String(ac);
					ac = null;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return s.trim();
	}

	/**
	 * String转化Clob类型
	 * 
	 * @param clob
	 * @return
	 */
	// public static CLOB stringToClob(Connection conn, String str) {
	// CLOB clob = null;
	// if (conn != null && str != null && str.length() > 0) {
	// try {
	// clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);
	// clob.open(CLOB.MODE_READWRITE);
	// Writer tempClobWriter = clob.getCharacterOutputStream();
	// tempClobWriter.write(str);
	// tempClobWriter.flush();
	// tempClobWriter.close();
	// clob.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return clob;
	// }
	public static void stringToClob(Connection conn, String str, String id) {
		try {
			int size = str.length() / 3500 + 1;
			String[] temp = new String[size];
			for (int i = 0; i < size; i++) {
				if (i == size - 1)
					temp[i] = str.substring(0);
				else {
					temp[i] = str.substring(0, 3500);
					str = str.substring(3500);
				}
			}
			String sql = "";
			PreparedStatement ps = null;

			for (int i = 0; i < size; i++) {
				temp[i] = temp[i].replaceAll("'", "''");
				if (i == 0)
					sql = "update "+ConstValue.JBPM4_DEPLOY+" set PROCESSDEFINE = '" + temp[i] + "' where ID=" + id;
				else
					sql = "update "+ConstValue.JBPM4_DEPLOY+" set PROCESSDEFINE = PROCESSDEFINE||'" + temp[i] + "' where ID=" + id;
				ps = conn.prepareStatement(sql);
				ps.execute();
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * String转化为Document类型
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Document stringToDoc(String str) throws Exception {
		Document document = null;
		if (str != null && !"".equals(str)) {
			try {
				document = DocumentHelper.parseText(str.trim());
				return document;
			} catch (DocumentException e) {
				e.printStackTrace();
				throw new Exception("*.jpdl.xml文件无法解析，或xml文件损毁，请检查*.jpdl.xml文件信息");
			}
		}
		return document;
	}

	/**
	 * 将字符串组装成URL的格式 如/toftcore/deomtest.do?a=250&b=380
	 * 
	 * @param urlStr
	 *            请求的URL
	 * @param up
	 *            流程定义中配置的参数，这个是可选项
	 * @return 带有参数的URL
	 * @deprecated
	 */
	public static String getURLDup(String urlStr, List up) {

		if (null != up && up.size() > 0) { // 有参数

			String tempUrl = "";
			String param = "";

			String str1 = "?";
			String str2 = "&";

			for (Iterator iter = up.iterator(); iter.hasNext();) {
				String s = (String) iter.next();
				param += s;
				param += str2;
			}

			param = param.substring(0, param.lastIndexOf("&"));
			tempUrl = urlStr + str1 + param;
			return tempUrl;
		}

		return urlStr;
	}

	/**
	 * 将字符串组装成URL的格式 如/toftcore/deomtest.do?a=250&b=380
	 * 
	 * @param urlStr
	 *            请求的URL
	 * @param recordId
	 *            业务单据标识
	 * @param up
	 *            流程定义中配置的参数，这个是可选项
	 * @return 带有参数的URL
	 */
	public static String getURL(String urlStr, String recordId, List up) {

		String tempUrl = "";
		String param = "";

		String str1 = "?";
		String str2 = "&";

		if (null != up && up.size() > 0) { // 有参数

			for (Iterator iter = up.iterator(); iter.hasNext();) {
				String s = (String) iter.next();
				param += s;
				param += str2;
			}

			if (!"".equals(param)) {
				param = param.substring(0, param.lastIndexOf("&"));
			}

			tempUrl = urlStr + str1 + param; // 添加可选参数（流程定义配置的参数）

			tempUrl += (str2 + ConstantUtil.BUSINESS_RECORE_ID + "=" + recordId);

			return tempUrl;
		}

		// 包含业务单据标识的URL
		urlStr += (str1 + ConstantUtil.BUSINESS_RECORE_ID + "=" + recordId);

		return urlStr;
	}

	/**
	 * 将字符串组装成URL的格式 如/toftcore/deomtest.do?a=250&b=380
	 * 
	 * @param urlStr
	 *            请求的URL
	 * @param urlParams
	 *            流程定义中配置的参数和 运行时所需要的参数(启动流程时放入流程变量中，如业务单据标识等)
	 * @return 带有参数的URL
	 */
	public static String getURL(String urlStr, List urlParams) {

		String tempUrl = "";
		String param = "";

		String str1 = "?";
		String str2 = "&";

		if (null != urlParams && urlParams.size() > 0) { // 有参数

			for (Iterator iter = urlParams.iterator(); iter.hasNext();) {
				String s = (String) iter.next();
				param += s;
				param += str2;
			}

			if (!"".equals(param)) {
				param = param.substring(0, param.lastIndexOf("&"));
			}
            if(urlStr.indexOf(str1)!=-1){
            	str1=str2;
            }
			tempUrl = urlStr + str1 + param; // 添加可选参数（流程定义配置的参数）

			return tempUrl;
		}

		return urlStr;
	}

	/**
	 * 获取URL内的单据ID
	 * 
	 * @param url
	 *            URL
	 * @param businessRecordId
	 *            业务单据ID
	 * @return 业务单据ID
	 */
	public static String getUrlInfoId(String url) {

		if (url != null && !"".equals(url)) {
			if (url.indexOf("businessRecordId=") != -1) {
				if (url.indexOf("&") == -1) {
					return url.split("businessRecordId=")[1] == null ? "" : url.split("businessRecordId=")[1].toString();
				} else {
					return url.split("businessRecordId=")[1].split("&")[0] == null ? "" : url.split("businessRecordId=")[1].split("&")[0].toString();

				}
			}
			return "";
		}
		return "";

	}

	/**
	 * 获取URL内的单据金额
	 * 
	 * @param url
	 * 
	 * @param money
	 * 
	 * @return 业务单据金额
	 */
	public static String getUrlInfoMoney(String url) {
		if (url != null && !"".equals(url)) {
			if (url.indexOf("money=") == -1) {
				return "";
			}

			return url.split("money=")[1].split("&")[0] == null ? "" : url.split("money=")[1].split("&")[0].toString();

		}
		return "";
	}

	/**
	 * 获取格式化的单据金额
	 * 
	 * @param url
	 * 
	 * @param money
	 * 
	 * @return 业务单据金额
	 */
	public static String getDecimalFormat(String money) {
		if (!money.equals("") && money != null) {
			DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
			String outStr = null;
			double d;
			try {
				d = Double.parseDouble(money);
				outStr = fmt.format(d);
			} catch (Exception e) {
			}
			return "￥" + outStr;
		}
		return "￥0.00";
	}

	/**
	 * 格式化的单据金额恢复到格式化前
	 * 
	 * @param url
	 * 
	 * @param money
	 * 
	 * @return 业务单据金额
	 */
	public static String getUnFormat(String money) {
		if (!money.equals("") && money != null) {
			String outStr = "";
			if (money.indexOf("￥") != -1) {
				if (money.indexOf(",") != -1) {
					String temp[] = money.split("￥")[1].split(",");
					for (int i = 0; i < temp.length; i++) {
						outStr += temp[i];
					}
					return outStr;
				} else {

					return money.split("￥")[1];
				}

			} else {
				return money;
			}
		}

		return "";
	}

	/**
	 * 转换时间显示的格式
	 * 
	 * @param time
	 * 
	 * @param 引入格式
	 *            （"YYYY-MM-DD HH-MM-SS"）
	 * @return （"YYYY-MM-DD"）
	 * @throws ToftException
	 */
	public static String getTimeInfo(String time) throws ToftException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		if (time == null && "".equals(time)) {
			return "";
		}
		Date date = null;
		try {
			date = (Date) sdf.parse(time);
		} catch (ParseException e) {
			throw ToftException.ThrowToftException("Date format exception!" + e);
		}

		return sdf1.format(date);
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return true:数字 false:非数字
	 */
	public static boolean isNumeric(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}
}
