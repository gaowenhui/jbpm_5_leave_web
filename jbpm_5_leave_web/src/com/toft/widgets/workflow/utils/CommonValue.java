/**
 * 
 */
package com.toft.widgets.workflow.utils;

import java.io.File;

/**
 * CommonValue变量声明并提供getter、setter
 * 
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 16, 2009
 * 
 */
public class CommonValue {

	private String eventClassName = "com.toft.bpm.approval.handler.ToftDefaultActionHandler";
	private String actorClassName = "com.toft.bpm.approval.handler.ToftDefaultActorHandler";
	// add by lsy用于拼装普通任务task节点时使用
	private String taskClassName = "com.toft.widgets.workflow.handler.AssignTask";
	// add by lsy 用于拼装会签节点 2009-12-14
	private String counterSignNodeClassName = "com.toft.widgets.workflow.handler.CounterSignatureTask";
	// add by lsy 用于拼装动态节点时使用 2009-12-14
	private String dyntaskClassName = "com.toft.widgets.workflow.handler.AssignDynamicTask";

	public String getExportFilesPath() {
		String path = getClass().getClassLoader().getResource("").getPath()
				.split("/WEB-INF")[0]
				+ File.separatorChar + "exports";
		path = path.replaceAll("%20", " "); //通知客户，建立目录时 不应带空格//怀疑是否参数传反
		return path;
	}

	public CommonValue() {
	}

	/**
	 * @return the eventClassName
	 */
	public String getEventClassName() {
		return this.eventClassName;
	}

	public String getActorClassName() {
		return this.actorClassName;
	}

	/**
	 * @param eventClassName
	 *            the eventClassName to set
	 */
	public void setEventClassName(String eventClassName) {
		this.eventClassName = eventClassName;
	}

	private static CommonValue value;

	/**
	 * 当value为空生成新实例value 不为空返回value值
	 * 
	 * @return value：CommonValue
	 */
	public static CommonValue getInstance() {
		if (value == null)
			value = new CommonValue();
		return value;
	}

	public String getFlowFilesPath() {
		return getClass().getClassLoader().getResource("").getPath().split(
				"WEB-INF")[0]
				+ File.separatorChar + "flows" + File.separatorChar;
	}

	public static CommonValue getValue() {
		return value;
	}

	public static void setValue(CommonValue value) {
		CommonValue.value = value;
	}

	public String getTaskClassName() {
		return taskClassName;
	}

	public String getCounterSignNodeClassName() {
		return counterSignNodeClassName;
	}

	public String getDyntaskClassName() {
		return dyntaskClassName;
	}
}
