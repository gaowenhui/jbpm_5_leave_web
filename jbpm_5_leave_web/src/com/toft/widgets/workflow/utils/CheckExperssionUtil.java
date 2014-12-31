package com.toft.widgets.workflow.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * descirption：三目运算符验证
 * 
 * @author admin
 * 
 */
public class CheckExperssionUtil {
	/**
	 * 正则表达式验证三目运算符是否正确
	 * 
	 * @param str
	 * @return check1:boolean
	 */
	public static boolean checkOperator(String str) {
		Pattern pattern = Pattern.compile(".+?.+:.+");
		Matcher matcher = pattern.matcher(str);
		boolean check = matcher.matches();
		return check;
	}

	/**
	 * 正则表达式验证引号是否正确
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkQuotationMarksByRegular(String str) {
		Pattern pattern = Pattern.compile("^\".+\"");
		Matcher matcher = pattern.matcher(str);
		boolean check = matcher.matches();
		return check;
	}

	/**
	 * 验证括号是否正确
	 * 
	 * @param str
	 * @return check2:boolean
	 */
	public static boolean checkParentheses(String str) {
		String temp = str;
		Integer indexL = new Integer(0);
		Integer indexR = new Integer(0);
		List left = new ArrayList();
		List right = new ArrayList();
		while (indexL.intValue() != -1 && indexR.intValue() != -1) {
			indexL = new Integer(temp.indexOf("(", indexL.intValue()));
			indexR = new Integer(temp.indexOf(")", indexR.intValue()));
			if ((indexL.intValue() == -1) || (indexR.intValue() == -1)) {
				break;
			}
			left.add(indexL);
			right.add(indexR);
			indexL = new Integer(indexL.intValue() + 1);
			indexR = new Integer(indexL.intValue() + 1);
		}
		if (left.size() == 0 && right.size() == 0) {
			return true;
		}
		/**
		 * 把匹配合格的字符串替换成"1";
		 */
		if (left.size() == right.size() && left != null && right != null) {
			StringBuffer replacement = new StringBuffer("\"1\"");
			StringBuffer begin = null;
			StringBuffer behind = null;
			StringBuffer newStr = null;
			Integer[] leftArray = (Integer[]) left.toArray(new Integer[left
					.size()]);
			Integer[] rightArray = (Integer[]) right.toArray(new Integer[right
					.size()]);
			int leftLength = leftArray.length;
			boolean check;
			int tempL = leftArray[leftLength - 1].intValue() + 1;
			int tempR = rightArray[0].intValue();
			for (int i = 0; i < leftArray.length; i++) {
				String Experssion = str.substring(tempL, tempR);
				check = CheckExperssionUtil.checkOperator(Experssion);
				/**
				 * 处理匹配过的嵌套三目运算符
				 */
				if (check) {
					begin = new StringBuffer(str.substring(0, tempL - 1));
					behind = new StringBuffer(str.substring(tempR + 1, str
							.length()));
					newStr.append(begin);
					newStr.append(replacement);
					newStr.append(behind);
					str = newStr.toString();
				} else if (!check) {
					return false;
				}
			}
			return true;
		} else if (left.size() != right.size()) {
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 验证引号是否正确
	 * 
	 * @param str
	 * @return check3:boolean
	 */
	public static boolean checkQuotationMarks(String str) {
		String temp = str;
		Integer index = new Integer(0);
		List list = new ArrayList();
		List left = new ArrayList();
		List right = new ArrayList();
		while (index.intValue() != -1) {
			index = new Integer(temp.indexOf("\"", index.intValue()));
			if ((index.intValue() == -1)) {
				break;
			}
			list.add(index);
			index = new Integer(index.intValue() + 1);
		}
		/**
		 * 基数项放入left，偶数项放入right
		 */
		if ((list.size()) % 2 == 0 && (list.size() != 0)) {
			for (int i = 0; i < ((list.size()) / 2); i++) {
				int j = i + 1;
				left.add(list.get(i));
				right.add(list.get(j));
				i = i + 2;
				j = j + 2;
			}
		} else {
			return false;
		}
		/**
		 * 正则表达式验证;
		 */
		Integer[] leftArray = (Integer[]) left
				.toArray(new Integer[left.size()]);
		Integer[] rightArray = (Integer[]) right.toArray(new Integer[right
				.size()]);
		boolean check;
		int i = 0;
		int tempL = leftArray[i].intValue();
		int tempR = rightArray[i].intValue();
		for (; i < leftArray.length; i++) {
			String Experssion = str.substring(tempL, tempR);
			check = CheckExperssionUtil
					.checkQuotationMarksByRegular(Experssion);
			if (!check) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证表达式
	 * 
	 * @param experssion
	 * @return
	 */
	public static boolean checkExperssion(String experssion) {
		boolean cpb = CheckExperssionUtil.checkParentheses(experssion);
		boolean cqmb = CheckExperssionUtil.checkQuotationMarks(experssion);
		if (cpb && cqmb) {
			return true;
		} else if (!(cpb && cqmb)) {
			return false;
		} else {
			return false;
		}
	}

	/** *********************add by lsy with lmq ************************** */

	// 添加对三目运算符的支持
	public static boolean hasIllegalExpression(String exp) {
		if (!exp.replaceAll("\\!|:|\\?|M|S|E|O|L|N|B|C|\\(|\\)", "").equals(""))// 检查非法的字符
			return true;
		return false;
	}

	private static String[] legalExpression = { "EOE", "NOE", "EON", "NON",
			"ELE", "BLE", "ELB", "BLB", "ECE", "NCE", "ECN", "NCN", "EME",
			"EMN", "NMN", "NME", "BMB", "EMB", "BME", "SMS", "SME", "EMS",
			"E?E:E", "E?N:E", "E?N:N", "E?E:N", "E?S:S", "E?S:E", "E?:E:S",
			"E?B:B", "E?:B:E", "E?E:B", "B?E:E", "B?N:E", "B?N:N", "B?E:N",
			"B?S:S", "B?S:E", "B?:E:S", "B?B:B", "B?:B:E", "B?E:B", "!E", "!B" };// 合法的表达式，可以合并

	public static boolean testLogic(String test) {
		String exp = test.trim().replaceAll(
				"[\u4E00-\u9FA5a-zA-Z_][\u4E00-\u9FA5a-zA-Z_\\.0-9]*", "E")
				.replaceAll(// 将表达式的空格去掉,将变量替换为E
						"\\d+", "N").replaceAll("N\\.N", "N").replaceAll(
						"'E'|'N'", "S").replaceAll(// 字符串替换为S，数字为N
						"\\+|-|\\*|/", "O").replaceAll(// 将算术运算符替换为O
						">=|<=|>|<", "C").replaceAll("==|!=", "M").replaceAll(// 相等运算符M，比较预算符为C
						"\\|\\||\\&\\&", "L");// 将逻辑运算符替换为L
		String[] merge = new String[exp.length()];// 数组下标为字符串中的位置，值为匹配的句式
		boolean canMerge = testMerge(exp, merge);// 是否可合并
		exp = removeBrackets(exp);
		while (canMerge) {
			if (hasIllegalExpression(exp))// 检查非法的字符和非法的表达式
				return false;
			for (int i = 0; i < merge.length; i++) {
				if (merge[i] != null) {
					String s = (String) merge[i];
					char result = getResult(s, exp);
					exp = exp.replaceFirst(s.replaceAll("\\?|!", "\\\\$0"),
							String.valueOf(result));// 由于JDK1.4（replace(char,char)）和JDK1.5（replace(string,string)）版本不同因为使用不同的方式replaceAll(string,string)
					exp = removeBrackets(exp);
					break;
				}
			}
//			System.out.println(exp);
			merge = new String[exp.length()];// 清空
			canMerge = false;// 查找可用于合并的表达式
			canMerge = testMerge(exp, merge);
//			System.out.println(exp);
		}
//		System.out.println(exp);
		if ("B".equals(exp) || "E".equals(exp) || "".equals(exp))
			return true;
		return false;
	}

	public static String removeBrackets(String exp) {
		return exp.replaceAll("\\(E\\)", "E").replaceAll("\\(B\\)", "B")
				.replaceAll("\\(N\\)", "N");// 去掉没用的括号
	}

	public static char getResult(String s, String exp) {
		char result;
		if (s.length() > 3) {
			if (s.charAt(2) != 'E' || s.charAt(4) != 'E') {
				result = (s.charAt(2) != 'E') ? s.charAt(2) : s.charAt(4);
			} else {
				result = 'E';
			}
		} else if (s.length() == 2 && s.charAt(0) == '!')
			result = 'B';
		else
			result = s.charAt(1) == 'O' ? 'N' : 'B';// 除了算术运算返回N之外都返回B
		return result;
	}

	public static boolean testMerge(String exp, String[] merge) {
		boolean canMerge = false;// 是否可合并
		for (int i = 0; i < legalExpression.length; i++) {// 查找可用于合并的表达式
			int index = exp.indexOf(legalExpression[i]);
			if (index >= 0) {
				merge[index] = legalExpression[i];
				canMerge = true;
			}
		}
		return canMerge;
	}

	/** ************************************************* */

	public static void main(String arg0[]) {
		String s = "aaa=='阿斯顿发生大幅'";
		testLogic(s);
	}

}
