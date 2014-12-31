/**
 * 
 */
package com.toft.widgets.workflow.monitorFW.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * @description
 */
public class Task {
	private String id;
	private String name;
	private String activityName;
	private String organization;
	private String department;
	private String role;
	private String person;
	private List hasten = new ArrayList();//add by lsy 催办
	private String assignRule;// add by lsy 活动规则
	private String dynamicTaskRule;// add by lsy 动态规则
	private String hastenTemplate;//add by lsy 催办模板 --暂时不开放此功能
	private String eventActionCurrSelectName;//存储当前节点URL的action值
	private List eventParamList = new ArrayList();//存储对应当前节点URL的参数集合 =====暂无set get方法
	private List carbonCopyList = new ArrayList();//用于存放抄送消息
	//超时处理
	public String timeoutAction;//超时处理类
	public String timeout;//默认的处理时间
	public List timeoutParam = new ArrayList();//超时类的参数

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * 反向读取XML
	 * @param element
	 * @param elementParent
	 * @return Task
	 */
	public Task toTask(Element element, Element elementParent) {
		this.id = element.attributeValue("id");
		this.name = element.attributeValue("name");
		/****************************解析timeout***************************************
		Iterator iterTimeOutEvent = elementParent.elementIterator("on");
		while (iterTimeOutEvent.hasNext()) {
			Element elementTimeOutOn = (Element) iterTimeOutEvent.next();//(Element) elementParent.element("on")
			if ("timeout".equals(elementTimeOutOn.attributeValue("event"))) {
				Element elementTimeOutTimer = (Element) elementTimeOutOn
						.element("timer");
				//当timer属性为空时则表示该事件不是超时事件不予处理
				this.timeout = elementTimeOutTimer.attributeValue("duedate");
				Element elementTimeOutActionEL = (Element) elementTimeOutOn
						.element("event-listener");
				if (elementTimeOutActionEL != null) {
					this.timeoutAction = elementTimeOutActionEL
							.attributeValue("class");
					String tempList[] = { "field", "list" };
					for (int i = 0; i < tempList.length; i++) {
						if (elementTimeOutActionEL.element("list") == null) {
							elementTimeOutActionEL = (Element) elementTimeOutActionEL
									.element(tempList[i]);
						} else {
							break;
						}
					}
					Iterator iterTimeOut = elementTimeOutActionEL.element(
							"list").elementIterator("string");
					while (iterTimeOut.hasNext()) {
						Map mapObj = new HashMap();
						Element elementTimeOutParam = (Element) iterTimeOut
								.next();
						String value = elementTimeOutParam
								.attributeValue("value");
						//当value有值时做处理，否则不做处理
						if (value != null) {
							String arrValue[] = value.split("=");
							mapObj.put("name", arrValue[0]);
							mapObj.put("value", arrValue[1]);
							this.timeoutParam.add(mapObj);
						}
					}
				}
			}
		}

		******************************************************************/
		List nodes = element.elements("field");
		//modify by lsy :分解 规则 
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			Element assignment = (Element) it.next();
			if ("assignRule".equals(assignment.attributeValue("name"))) {
				this.assignRule = assignment.element("string").attributeValue(
						"value");
			} else if ("dynamicTaskRule".equals(assignment
					.attributeValue("name"))) {
				this.dynamicTaskRule = assignment.element("string")
						.attributeValue("value");
			}
			//催办方式以list方式解析出来
			else if ("hasten".equals(assignment.attributeValue("name"))) {
				Element elementHastenList = (Element) assignment
						.element("list");

				Iterator iterTimeOut = elementHastenList
						.elementIterator("string");
				while (iterTimeOut.hasNext()) {
					Element elementTimeOutParam = (Element) iterTimeOut.next();
					String value = elementTimeOutParam.attributeValue("value");
					if (value != null) {
						this.hasten.add(value);
					} else {
						this.hasten = null;
					}
				}
			}
			//add by lsy :url
			else if ("url".equals(assignment.attributeValue("name"))) {
				this.eventActionCurrSelectName = !"null".equals(assignment
						.element("string").attributeValue("value")) ? assignment
						.element("string").attributeValue("value")
						: " ";
			}
			//add by lsy :url-param
			else if ("urlParam".equals(assignment.attributeValue("name"))) {

				Element elementURLList = (Element) assignment.element("list");
				Iterator iterURL = elementURLList.elementIterator("string");
				while (iterURL.hasNext()) {
					Map mapObj = new HashMap();
					Element elementURLParam = (Element) iterURL.next();

					String value = elementURLParam.attributeValue("value");
					String arrValue[] = value.split("=");
					mapObj.put("name", arrValue[0]);
					mapObj.put("value", arrValue[1]);
					this.eventParamList.add(mapObj);

				}
			}
			//消息模板
			else if ("hastenTemplate".equals(assignment.attributeValue("name"))) {
				this.hastenTemplate = !"null".equals(assignment.element(
						"string").attributeValue("value")) ? assignment
						.element("string").attributeValue("value") : " ";
			}
			//抄送解析
			else if ("carbonCopy".equals(assignment.attributeValue("name"))) {
				Element elementCarbonCopyList = assignment.element("list");
				Iterator iterCarbonCopy = elementCarbonCopyList
						.elementIterator("map");
				while (iterCarbonCopy.hasNext()) {
					Map mapCarbonCopy = new HashMap();
					Element elementMap = (Element) iterCarbonCopy.next();
					Iterator iterEntry = elementMap.elementIterator("entry");
					while (iterEntry.hasNext()) {
						Element elementEntryKey = (Element) iterEntry.next();
						if ("carbonCopyWay".equals(elementEntryKey.element(
								"key").element("string")
								.attributeValue("value")))
							mapCarbonCopy.put("carbonCopyPatternImpl",
									elementEntryKey.element("value").element(
											"string").attributeValue("value"));
						if ("receiverCode".equals(elementEntryKey
								.element("key").element("string")
								.attributeValue("value")))
							mapCarbonCopy.put("carbonCopyRule", elementEntryKey
									.element("value").element("string")
									.attributeValue("value"));
						if ("hastenTemplate".equals(elementEntryKey.element(
								"key").element("string")
								.attributeValue("value")))
							mapCarbonCopy.put("carbonCopyTemplate",
									elementEntryKey.element("value").element(
											"string").attributeValue("value"));
						if ("hastenMessage".equals(elementEntryKey.element(
								"key").element("string")
								.attributeValue("value")))
							mapCarbonCopy.put("carbonCopyPattern",
									elementEntryKey.element("value").element(
											"string").attributeValue("value"));
					}
					this.carbonCopyList.add(mapCarbonCopy);
				}
			}

		}
		return this;
	}

	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName
	 *            the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAssignRule() {
		return assignRule;
	}

	public void setAssignRule(String assignRule) {
		this.assignRule = assignRule;
	}

	public String getDynamicTaskRule() {
		return dynamicTaskRule;
	}

	public void setDynamicTaskRule(String dynamicTaskRule) {
		this.dynamicTaskRule = dynamicTaskRule;
	}

	public String getHastenTemplate() {
		return hastenTemplate;
	}

	public void setHastenTemplate(String hastenTemplate) {
		this.hastenTemplate = hastenTemplate;
	}

	public List getHasten() {
		return hasten;
	}

	public void setHasten(List hasten) {
		this.hasten = hasten;
	}

	public String getEventActionCurrSelectName() {
		return eventActionCurrSelectName;
	}

	public void setEventActionCurrSelectName(String eventActionCurrSelectName) {
		this.eventActionCurrSelectName = eventActionCurrSelectName;
	}

	public String getTimeoutAction() {
		return timeoutAction;
	}

	public void setTimeoutAction(String timeoutAction) {
		this.timeoutAction = timeoutAction;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public List getTimeoutParam() {
		return timeoutParam;
	}

	public void setTimeoutParam(List timeoutParam) {
		this.timeoutParam = timeoutParam;
	}

	public List getEventParamList() {
		return eventParamList;
	}

	public void setEventParamList(List eventParamList) {
		this.eventParamList = eventParamList;
	}

	public List getCarbonCopyList() {
		return carbonCopyList;
	}

	public void setCarbonCopyList(List carbonCopyList) {
		this.carbonCopyList = carbonCopyList;
	}

}
