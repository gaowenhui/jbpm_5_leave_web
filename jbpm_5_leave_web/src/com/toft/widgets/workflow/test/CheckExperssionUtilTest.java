package com.toft.widgets.workflow.test;

import com.toft.widgets.workflow.utils.CheckExperssionUtil;
/**
 * 测试CheckExperssionUtil
 * @author admin
 *
 */
public class CheckExperssionUtilTest {
	public static void main(String[] args){
		String TrueExperssion1 = "4>3?(3>2?(2>1?:\"1\"):\"2\"):\"3\"";
		String FlaseExperssion1 = "4>3?(3>2?:\"1\":\"2\"";
		String FlaseExperssion2 = "4>3?1:2";
		String TrueExperssion2 = "a>b?\"1\":\"2\"";
		String TrueExperssion3 = "abc==阿什顿飞";
//		System.out.println(CheckExperssionUtil.checkExperssion(TrueExperssion1));
//		System.out.println(CheckExperssionUtil.checkExperssion(TrueExperssion2));
//		System.out.println(CheckExperssionUtil.checkExperssion(FlaseExperssion1));
//		System.out.println(CheckExperssionUtil.checkExperssion(FlaseExperssion2));
		System.out.println(CheckExperssionUtil.testLogic(TrueExperssion3));
	}
}
