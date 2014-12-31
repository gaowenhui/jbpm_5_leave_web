/**
 * 
 */
package com.toft.widgets.workflow.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.toft.widgets.workflow.utils.CommonValue;
import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.utils.PropertiesUtils;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * @description
 * 
 *              海洋于 2011年9月27日进行修改，增加泳道设定及泳道读取属性
 * 
 */
public class Task {
	private String id = "";
	private String name = "";
	private String activityName = "";
	private String organization = "";
	private String department = "";
	private String role = "";
	private String person = "";
	private List hasten = new ArrayList();// add by lsy 催办

	// 泳道设定及获取
	private String swimlineSetter = "";

	public String getSwimlineSetter() {
		return swimlineSetter;
	}

	public void setSwimlineSetter(String swimlineSetter) {
		this.swimlineSetter = swimlineSetter;
	}

	public String getSwimlineGetter() {
		return swimlineGetter;
	}

	public void setSwimlineGetter(String swimlineGetter) {
		this.swimlineGetter = swimlineGetter;
	}

	private String swimlineGetter = "";

	private AssignRule assignRule = new AssignRule();// add by lsy 活动规则
	private DynamicTaskRule dynamicTaskRule = new DynamicTaskRule();// add by
	// lsy 动态规则
	private HastenTemplate hastenTemplate = new HastenTemplate();// add by
	// lsy 催办模板
	// --暂时不开放此功能

	private UrlAction urlAction = new UrlAction();// 存储当前节点URL的action值
	private List eventParamList = new ArrayList();// 存储对应当前节点URL的参数集合
	// =====暂无set get方法

	private List carbonCopyList = new ArrayList();// 用于存放抄送消息
	// 超时处理
	public TimeOutAction timeoutAction = new TimeOutAction();// 超时处理类
	public String timeout = "";// 默认的处理时间
	public List timeoutParam = new ArrayList();// 超时类的参数

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
	 * 用DOM4J的方式生成XML文件
	 * 
	 * @param element
	 * @param acName
	 */
	public void toXML(Element element, String acName) throws Exception {

		// 给task增加两个属性 2011年9月27日---海洋
		element.addAttribute("swimlineSet", getSwimlineSetter());
		element.addAttribute("swimlineGet", getSwimlineGetter());

		// modify by lsy :去掉一层task节点
		/** *************************************************************** */
		Element assignment = element.addElement("assignment-handler");
		// add by lsy :不同节点匹配不同的处理类 2009-12-14
		if (ConstValue.COUNTERSIGNTYPE.equals(acName)) {
			assignment.addAttribute("class", CommonValue.getInstance().getCounterSignNodeClassName());
		} else if (ConstValue.TASKDYNAMICNODETYPE.equals(acName)) {
			assignment.addAttribute("class", CommonValue.getInstance().getDyntaskClassName());
		} else if (ConstValue.COMMITNODETYPE.equals(acName)) {
			assignment.addAttribute("class", "com.toft.widgets.workflow.handler.SubmitTaskActivity");
		} else {
			assignment.addAttribute("class", CommonValue.getInstance().getTaskClassName());
		}
		/**
		 * ***************************URL属性：会签节点、任务节点、动态节点**********************
		 * *********************
		 */
		if (this.urlAction == null)
			this.urlAction = new UrlAction();
		// url
		// --------------------------------------url start
		Properties property = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
		boolean enableOrDisable = "enable".equals(property.get("property.urlPropTab"));
		String urlActionCode = urlAction.getUrlActionCode();
		String urlActionName = urlAction.getUrlActionName();
		if (enableOrDisable && StringUtils.isNotEmpty(urlActionCode)) {
			Element fieldURL = assignment.addElement("field");
			fieldURL.addAttribute("name", "url");
			Element urllist = fieldURL.addElement("string");
			urllist.addAttribute("value", StringUtils.isNotEmpty(urlActionCode) ? urlActionCode : "null");
			// urlName -2010-2-26
			Element fieldURLName = assignment.addElement("field");
			fieldURLName.addAttribute("name", "urlName");
			Element urNamellist = fieldURLName.addElement("string");
			urNamellist.addAttribute("value", StringUtils.isNotEmpty(urlActionName) ? urlActionName : "null");
		}
		// --------------------------------------url end

		if (!ConstValue.COMMITNODETYPE.equals(acName)) {
			/**
			 * **************************任务节点的超时处理******************************
			 * ******
			 */
			if (!(this.timeout == null || "".equals(this.timeout))
					&& !(this.timeoutAction == null || "".equals(this.timeoutAction) || this.timeoutAction.getTimeoutActionCode() == null || ""
							.equals(this.timeoutAction.getTimeoutActionCode()))) {
				Element fieldTimeOut = element.addElement("on");
				fieldTimeOut.addAttribute("event", "timeout");
				Element fieldDueDate = fieldTimeOut.addElement("timer");
				fieldDueDate.addAttribute("duedate", this.timeout);
				Element fieldEventListener = fieldTimeOut.addElement("event-listener");
				fieldEventListener.addAttribute("class", this.timeoutAction.getTimeoutActionCode());
				// 保存超时类的名称--20100330
				Element fieldEventName = fieldEventListener.addElement("field");
				fieldEventName.addAttribute("name", "timeoutName");
				Element fieldEventNameValue = fieldEventName.addElement("string");
				fieldEventNameValue.addAttribute("value", this.timeoutAction.getTimeoutActionName());
				// 解析参数
				Element fieldParamName = fieldEventListener.addElement("field");
				fieldParamName.addAttribute("name", "timeoutParam");
				Element list2 = fieldParamName.addElement("list");
				list2.addAttribute("class", "java.util.ArrayList");
				if (this.timeoutParam != null) {
					Iterator iter = this.timeoutParam.iterator();
					while (iter.hasNext()) {
						Map obj = (Map) iter.next();
						if (obj.get("value") == null || "".equals(obj.get("value"))) {
						} else {
							Element fieldParamValue = list2.addElement("string");
							fieldParamValue.addAttribute("value", obj.get("name").toString() + "=" + obj.get("value").toString());
						}
					}
				}
			}
			/**
			 * *****************会签节点区别于任务节点的属性
			 * 2009-12-13-start-待修改***********************
			 */
			// 如果会签节点节点的话参与者规则填充到counterSignatureRule，否则为任务节点参与者规则填充到
			if (ConstValue.COUNTERSIGNTYPE.equals(acName)) {
				Element fieldCountSignature = assignment.addElement("field");
				fieldCountSignature.addAttribute("name", "counterSignatureRule");
				Element string = fieldCountSignature.addElement("string");
				string.addAttribute("value", (String) this.assignRule.getAssignRuleCode());
				// Name-2010-2-23
				Element fieldCountSignatureName = assignment.addElement("field");
				fieldCountSignatureName.addAttribute("name", "counterSignatureRuleName");
				Element stringName = fieldCountSignatureName.addElement("string");
				stringName.addAttribute("value", (String) this.assignRule.getAssignRuleName());
				// className
				String className = (String) this.assignRule.getAssignRuleClassName();
				if (StringUtils.isNotEmpty(className)) {
					Element fieldClassName = assignment.addElement("field");
					fieldClassName.addAttribute("name", "assignRuleClassName");
					Element stringClassName = fieldClassName.addElement("string");
					stringClassName.addAttribute("value", className);
				}
			} else {
				// field--assignRule
				if (this.assignRule == null)
					assignRule = new AssignRule();
				Element field = assignment.addElement("field");
				field.addAttribute("name", "assignRule");
				Element string = field.addElement("string");
				string.addAttribute("value", (String) this.assignRule.getAssignRuleCode());
				// Name-2010-2-23
				Element fieldName = assignment.addElement("field");
				fieldName.addAttribute("name", "assignRuleName");
				Element stringName = fieldName.addElement("string");
				stringName.addAttribute("value", (String) this.assignRule.getAssignRuleName());
				// className
				String className = (String) this.assignRule.getAssignRuleClassName();
				if (StringUtils.isNotEmpty(className)) {
					Element fieldClassName = assignment.addElement("field");
					fieldClassName.addAttribute("name", "assignRuleClassName");
					Element stringClassName = fieldClassName.addElement("string");
					stringClassName.addAttribute("value", className);
				}
			}

			if (this.hasten != null && this.hasten.size() > 0) {
				// field--hasten
				Element field2 = assignment.addElement("field");
				field2.addAttribute("name", "hasten");
				Element list2 = field2.addElement("list");
				list2.addAttribute("class", "java.util.ArrayList");

				Iterator itr = this.hasten.iterator();
				// 催办方式以list方式拼入XML中
				while (itr.hasNext()) {
					Object nextObj = itr.next();
					Element string2 = list2.addElement("string");
					string2.addAttribute("value", nextObj.toString());
				}

				if (this.hastenTemplate != null) {
					String hastenTemplateCode = this.hastenTemplate.getHastenTemplateCode();
					String hastenTemplateName = this.hastenTemplate.getHastenTemplateName();
					if (StringUtils.isNotEmpty(hastenTemplateCode)) {
						Element field3 = assignment.addElement("field");
						field3.addAttribute("name", "hastenTemplate");
						Element string3 = field3.addElement("string");
						string3.addAttribute("value", hastenTemplateCode);

						Element field3Name = assignment.addElement("field");
						field3Name.addAttribute("name", "hastenTemplateName");
						Element string3Name = field3Name.addElement("string");
						string3Name.addAttribute("value", hastenTemplateName);
					}
				}
			}

			if (this.carbonCopyList != null && this.carbonCopyList.size() > 0) {
				Element field4 = assignment.addElement("field");
				field4.addAttribute("name", "carbonCopy");
				Element fieldList = field4.addElement("list");
				fieldList.addAttribute("class", "java.util.ArrayList");
				Iterator iterMap = this.carbonCopyList.iterator();
				while (iterMap.hasNext()) {
					Map objMap = (Map) iterMap.next();
					Element fieldMap = fieldList.addElement("map");
					fieldMap.addAttribute("class", "java.util.HashMap");
					// pattern
					Element fieldEntryPattern = fieldMap.addElement("entry");

					Element fieldKeyPattern = fieldEntryPattern.addElement("key");
					Element fieldKeyPatternValue = fieldKeyPattern.addElement("string");
					fieldKeyPatternValue.addAttribute("value", "carbonCopyWay");
					Element fieldValuePattern = fieldEntryPattern.addElement("value");
					Element fieldVPValue = fieldValuePattern.addElement("string");
					fieldVPValue.addAttribute("value", objMap.get("carbonCopyPatternImpl").toString().replaceAll(",", "\\$"));
					// rule
					Element fieldEntryRule = fieldMap.addElement("entry");
					Element fieldKeyRule = fieldEntryRule.addElement("key");
					Element fieldKeyRuleValue = fieldKeyRule.addElement("string");
					fieldKeyRuleValue.addAttribute("value", "receiverCode");
					Element fieldValueRule = fieldEntryRule.addElement("value");
					Element fieldVRValue = fieldValueRule.addElement("string");
					fieldVRValue.addAttribute("value", objMap.get("carbonCopyRule").toString());
					// ruleName -- 2010-3-5
					Element fieldEntryRuleName = fieldMap.addElement("entry");
					Element fieldKeyRuleName = fieldEntryRuleName.addElement("key");
					Element fieldKeyRuleNameValue = fieldKeyRuleName.addElement("string");
					fieldKeyRuleNameValue.addAttribute("value", "receiverCodeName");
					Element fieldValueRuleName = fieldEntryRuleName.addElement("value");
					Element fieldVRNameValue = fieldValueRuleName.addElement("string");
					fieldVRNameValue.addAttribute("value", objMap.get("carbonCopyRuleName").toString());
					// template
					Element fieldEntryTemplate = fieldMap.addElement("entry");
					Element fieldKeyTemplate = fieldEntryTemplate.addElement("key");
					Element fieldKeyTemplateValue = fieldKeyTemplate.addElement("string");
					fieldKeyTemplateValue.addAttribute("value", "hastenTemplate");
					Element fieldValueTemplate = fieldEntryTemplate.addElement("value");
					Element fieldVTValue = fieldValueTemplate.addElement("string");
					fieldVTValue.addAttribute("value", objMap.get("carbonCopyTemplate").toString());
					// templateName 3-5
					Element fieldEntryTemplateName = fieldMap.addElement("entry");
					Element fieldKeyTemplateName = fieldEntryTemplateName.addElement("key");
					Element fieldKeyTemplateNameValue = fieldKeyTemplateName.addElement("string");
					fieldKeyTemplateNameValue.addAttribute("value", "hastenTemplateName");
					Element fieldValueTemplateName = fieldEntryTemplateName.addElement("value");
					Element fieldVTValueName = fieldValueTemplateName.addElement("string");
					fieldVTValueName.addAttribute("value", objMap.get("carbonCopyTemplateName").toString());
					// name--仅用于前台的显示
					Element fieldEntryName = fieldMap.addElement("entry");
					Element fieldKeyName = fieldEntryName.addElement("key");
					Element fieldKeyNameValue = fieldKeyName.addElement("string");
					fieldKeyNameValue.addAttribute("value", "hastenMessage");
					Element fieldValueName = fieldEntryName.addElement("value");
					Element fieldVNValue = fieldValueName.addElement("string");
					fieldVNValue.addAttribute("value", objMap.get("carbonCopyPattern").toString());
				}
			}
		}
	}

	/**
	 * 反向读取XML
	 * 
	 * @param element
	 * @param elementParent
	 * @return Task
	 */
	public Task toTask(Element element, Element elementParent) throws Exception {
		this.id = element.attributeValue("id");
		this.name = element.attributeValue("name");
		/**
		 * **************************解析timeout**********************************
		 * 
		 * 2011.04.21 鹿鹏：去除超时处理，将此作为事件进行处理解析
		 * 
		 * Iterator iterTimeOutEvent = elementParent.elementIterator("on");
		 * while (iterTimeOutEvent.hasNext()) { Element elementTimeOutOn =
		 * (Element) iterTimeOutEvent.next();// (Element) //
		 * elementParent.element("on") if
		 * ("timeout".equals(elementTimeOutOn.attributeValue("event"))) {
		 * Element elementTimeOutTimer = (Element) elementTimeOutOn
		 * .element("timer"); // 当timer属性为空时则表示该事件不是超时事件不予处理 //
		 * if(elementTimeOutTimer != null){ this.timeout =
		 * elementTimeOutTimer.attributeValue("duedate"); Element
		 * elementTimeOutActionEL = (Element) elementTimeOutOn
		 * .element("event-listener"); if (elementTimeOutActionEL != null) {
		 * this.timeoutAction .setTimeoutActionCode(elementTimeOutActionEL
		 * .attributeValue("class")); /**
		 * ******************************20100330-修改超时的名称保存-start***
		 * ********************************** /* Iterator iterTimeOutField =
		 * elementTimeOutActionEL .elementIterator("field"); while
		 * (iterTimeOutField.hasNext()) { Element iterField = (Element)
		 * iterTimeOutField.next(); if ("timeoutName".equals(iterField
		 * .attributeValue("name"))) {
		 * this.timeoutAction.setTimeoutActionName(iterField
		 * .element("string").attributeValue("value")); } /**
		 * *******************************end*******************
		 * ***************** /* else { Iterator iterTimeOut =
		 * iterField.element("list") .elementIterator("string"); while
		 * (iterTimeOut.hasNext()) { Map mapObj = new HashMap(); Element
		 * elementTimeOutParam = (Element) iterTimeOut .next(); String value =
		 * elementTimeOutParam .attributeValue("value"); // 当value有值时做处理，否则不做处理
		 * if (value != null) { String arrValue[] = value.split("=");
		 * mapObj.put("name", arrValue[0]); mapObj.put("value", arrValue[1]);
		 * this.timeoutParam.add(mapObj); } } } } } } }
		 */
		// }
		// 鹿鹏 ：提取配置文件的 url配置项，判断是否开启，如果未开启则不进行反解析
		Properties property = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
		boolean enableOrDisable = "enable".equals(property.get("property.urlPropTab"));

		/** **************************************************************** */
		List nodes = element.elements("field");
		// modify by lsy :分解 规则
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			Element assignment = (Element) it.next();
			if ("assignRule".equals(assignment.attributeValue("name"))) {
				this.assignRule.setAssignRuleCode(assignment.element("string").attributeValue("value"));
			}
			// assignRuleName-2010-2-23
			else if ("assignRuleName".equals(assignment.attributeValue("name"))) {
				this.assignRule.setAssignRuleName(assignment.element("string").attributeValue("value"));
			}
			else if("assignRuleClassName".equals(assignment.attributeValue("name"))){
				this.assignRule.setAssignRuleClassName(assignment.element("string").attributeValue("value"));
			}
			// 会签节点的参与者规则
			else if ("counterSignatureRuleName".equals(assignment.attributeValue("name"))) {
				this.assignRule.setAssignRuleName(assignment.element("string").attributeValue("value"));
			} else if ("counterSignatureRule".equals(assignment.attributeValue("name"))) {
				this.assignRule.setAssignRuleCode(assignment.element("string").attributeValue("value"));
			}
			// 催办方式以list方式解析出来
			else if ("hasten".equals(assignment.attributeValue("name"))) {
				Element elementHastenList = (Element) assignment.element("list");

				Iterator iterTimeOut = elementHastenList.elementIterator("string");
				while (iterTimeOut.hasNext()) {
					Element elementTimeOutParam = (Element) iterTimeOut.next();
					String value = elementTimeOutParam.attributeValue("value");
					if (value != null) {
						// mapObj.put("value",value);
						this.hasten.add(value);
					} else {
						this.hasten = null;
					}
				}
			}
			// add by lsy :url 鹿鹏 追加 && enableOrDisable 判断是否开启配置项
			else if ("url".equals(assignment.attributeValue("name")) && enableOrDisable) {
				this.urlAction.setUrlActionCode(!"".equals(assignment.element("string").attributeValue("value")) ? assignment.element("string")
						.attributeValue("value") : " ");
			}
			// add by lsy : urlName-2010-2-26 鹿鹏 追加 && enableOrDisable 判断是否开启配置项
			else if ("urlName".equals(assignment.attributeValue("name")) && enableOrDisable) {
				this.urlAction.setUrlActionName(!"".equals(assignment.element("string").attributeValue("value")) ? assignment.element("string")
						.attributeValue("value") : " ");
			}

			// 消息模板
			else if ("hastenTemplate".equals(assignment.attributeValue("name"))) {
				this.hastenTemplate.setHastenTemplateCode(!"null".equals(assignment.element("string").attributeValue("value")) ? assignment.element(
						"string").attributeValue("value") : " ");
			} else if ("hastenTemplateName".equals(assignment.attributeValue("name"))) {
				this.hastenTemplate.setHastenTemplateName(!"null".equals(assignment.element("string").attributeValue("value")) ? assignment.element(
						"string").attributeValue("value") : " ");
			}
			// 抄送解析
			else if ("carbonCopy".equals(assignment.attributeValue("name"))) {
				Element elementCarbonCopyList = assignment.element("list");
				Iterator iterCarbonCopy = elementCarbonCopyList.elementIterator("map");
				while (iterCarbonCopy.hasNext()) {
					Map mapCarbonCopy = new HashMap();
					Element elementMap = (Element) iterCarbonCopy.next();
					Iterator iterEntry = elementMap.elementIterator("entry");
					while (iterEntry.hasNext()) {
						Element elementEntryKey = (Element) iterEntry.next();
						if ("carbonCopyWay".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyPatternImpl", elementEntryKey.element("value").element("string").attributeValue("value")
									.replaceAll("\\$,", ","));
						if ("receiverCode".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyRule", elementEntryKey.element("value").element("string").attributeValue("value"));
						// 3-5 ruleName
						if ("receiverCodeName".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyRuleName", elementEntryKey.element("value").element("string").attributeValue("value"));

						if ("hastenTemplate".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyTemplate", elementEntryKey.element("value").element("string").attributeValue("value"));

						// 3-5 carbonCopyTemplateName
						if ("hastenTemplateName".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyTemplateName", elementEntryKey.element("value").element("string").attributeValue("value"));

						if ("hastenMessage".equals(elementEntryKey.element("key").element("string").attributeValue("value")))
							mapCarbonCopy.put("carbonCopyPattern", elementEntryKey.element("value").element("string").attributeValue("value"));
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

	public HastenTemplate getHastenTemplate() {
		return hastenTemplate;
	}

	public void setHastenTemplate(HastenTemplate hastenTemplate) {
		this.hastenTemplate = hastenTemplate;
	}

	public List getHasten() {
		return hasten;
	}

	public void setHasten(List hasten) {
		this.hasten = hasten;
	}

	public TimeOutAction getTimeoutAction() {
		return timeoutAction;
	}

	public void setTimeoutAction(TimeOutAction timeoutAction) {
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

	public AssignRule getAssignRule() {
		return assignRule;
	}

	public void setAssignRule(AssignRule assignRule) {
		this.assignRule = assignRule;
	}

	public DynamicTaskRule getDynamicTaskRule() {
		return dynamicTaskRule;
	}

	public void setDynamicTaskRule(DynamicTaskRule dynamicTaskRule) {
		this.dynamicTaskRule = dynamicTaskRule;
	}

	public UrlAction getUrlAction() {
		return urlAction;
	}

	public void setUrlAction(UrlAction urlAction) {
		this.urlAction = urlAction;
	}

}
