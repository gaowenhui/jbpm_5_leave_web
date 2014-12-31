package com.toft.widgets.workflow.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class EventFW {
	private String eventType1 = "";
	private String eventType1Name="";
	private String eventClassImpl = "";
	private String eventClassImplName = "";
	private String eventTimerSet="";
	
	private List eventParamList = new ArrayList();

	public String getEventClassImpl() {
		return eventClassImpl;
	}

	public void setEventClassImpl(String eventClassImpl) {
		this.eventClassImpl = eventClassImpl;
	}

	public List getEventParamList() {
		return eventParamList;
	}

	public void setEventParamList(List eventParamList) {
		this.eventParamList = eventParamList;
	}

	// 封装成对象
	public void toEvent(Element element) {
		// 因为超时和事件共用ON节点，所以要先判断event类型是否为timeout，如果不是超时类型则解析，否则不予处理
		// 2011.04.21 鹿鹏修改，上句描述修改：去除超时选项卡配置，统一在事件处理“超时事件”，故不用判断if
		//if (!"timeout".equals(element.attributeValue("event"))&& element.attributeValue("event") != null) {
			this.eventType1 = element.attributeValue("event");
			this.eventType1Name = element.attributeValue("name");
			
			Element elementTimer = element.element("timer");
			if(elementTimer!=null){
				this.eventTimerSet = elementTimer.attributeValue("duedate");
			}
			
			this.eventClassImpl = element.element("event-listener")
					.attributeValue("class");
			// 事情类名称的反显2010-3-2
			this.eventClassImplName = "eventClassImplName".equals(element
					.element("event-listener").element("field").attributeValue(
							"name")) ? element.element("event-listener")
					.element("field").element("string").attributeValue("value")
					: "";
			Iterator fieldElement = element.element("event-listener")
					.elementIterator("field");
			while (fieldElement.hasNext()) {
				Element elementItem = (Element) fieldElement.next();
				if (elementItem.element("list") != null
						&& elementItem.element("list").element("string") != null) {
					Iterator iterEvent = elementItem.element("list")
							.elementIterator("string");
					while (iterEvent.hasNext()) {
						Map mapObj = new HashMap();
						Element elementEventParam = (Element) iterEvent.next();
						String value = elementEventParam
								.attributeValue("value");
						String arrValue[] = value.split("=");
						mapObj.put("name", arrValue[0]);
						mapObj.put("value", arrValue[1]);
						this.eventParamList.add(mapObj);

					}
				}

			}
		//}

	}
	/**
	 * 连接线 的事件对象封装
	 * @param element
	 */
	public void toTransitionEvent(Element element) {
		
			this.eventType1 = "";
			this.eventType1Name = "";
			this.eventClassImpl = element.attributeValue("class");
						
			this.eventClassImplName = "eventClassImplName".equals(element.element("field").attributeValue("name")) 
			? element.element("field").element("string").attributeValue("value")
			: "";
			Iterator fieldElement = element.elementIterator("field");
			while (fieldElement.hasNext()) {
				Element elementItem = (Element) fieldElement.next();
				if (elementItem.element("list") != null
						&& elementItem.element("list").element("string") != null) {
					Iterator iterEvent = elementItem.element("list")
							.elementIterator("string");
					while (iterEvent.hasNext()) {
						Map mapObj = new HashMap();
						Element elementEventParam = (Element) iterEvent.next();
						String value = elementEventParam
								.attributeValue("value");
						String arrValue[] = value.split("=");
						mapObj.put("name", arrValue[0]);
						mapObj.put("value", arrValue[1]);
						this.eventParamList.add(mapObj);
					}
				}
			}
	}

	// 拼写XML
	public void toEventXML(Element element) {
		if (!((this.eventType1 == null || "".equals(this.eventType1)))
				&& !((this.eventClassImpl == null || ""
						.equals(this.eventClassImpl)))) {
			Element elementEventOn = element.addElement("on");
			elementEventOn.addAttribute("event", this.eventType1);
			elementEventOn.addAttribute("name", this.eventType1Name);
			
			//追加超时的事件配置，2011.04.21 鹿鹏			
			if(this.eventTimerSet!=null && this.eventTimerSet.length()>0){
				Element elementTimer = elementEventOn.addElement("timer");
				elementTimer.addAttribute("duedate",this.eventTimerSet);
			}
			Element elementEventClass = elementEventOn
					.addElement("event-listener");
			elementEventClass.addAttribute("class", this.eventClassImpl);
			// 记录名称 2010-3-2
			Element elementEventClassName = elementEventClass
					.addElement("field");
			elementEventClassName.addAttribute("name", "eventClassImplName");
			Element elementEventClassNameValue = elementEventClassName
					.addElement("string");
			elementEventClassNameValue.addAttribute("value",
					this.eventClassImplName);

			Element elementEventParam = elementEventClass.addElement("field");
			elementEventParam.addAttribute("name", "eventParam");
			Element elementParamList = elementEventParam.addElement("list");
			elementParamList.addAttribute("class", "java.util.ArrayList");
			if (this.eventParamList != null) {

				Iterator iter = this.eventParamList.iterator();
				while (iter.hasNext()) {
					Map obj = (Map) iter.next();
					if (obj.get("value") == null || "".equals(obj.get("value"))) {
					} else {
						Element fieldParamValue = elementParamList
								.addElement("string");
						fieldParamValue.addAttribute("value", obj.get("name")
								.toString()
								+ "=" + obj.get("value").toString());
					}
				}
			}
		}

	}
	
	/**
	 * 连接线的事件，没有<on... 标签
	 * @param element
	 */
	public void toTransitionEventXML(Element element) {
		if (!(this.eventClassImpl == null || "".equals(this.eventClassImpl))) {
			Element elementEventClass = element
					.addElement("event-listener");
			elementEventClass.addAttribute("class", this.eventClassImpl);
			// 记录名称 2010-3-2
			Element elementEventClassName = elementEventClass
					.addElement("field");
			elementEventClassName.addAttribute("name", "eventClassImplName");
			Element elementEventClassNameValue = elementEventClassName
					.addElement("string");
			elementEventClassNameValue.addAttribute("value",
					this.eventClassImplName);

			Element elementEventParam = elementEventClass.addElement("field");
			elementEventParam.addAttribute("name", "eventParam");
			Element elementParamList = elementEventParam.addElement("list");
			elementParamList.addAttribute("class", "java.util.ArrayList");
			if (this.eventParamList != null) {

				Iterator iter = this.eventParamList.iterator();
				while (iter.hasNext()) {
					Map obj = (Map) iter.next();
					if (obj.get("value") == null || "".equals(obj.get("value"))) {
					} else {
						Element fieldParamValue = elementParamList
								.addElement("string");
						fieldParamValue.addAttribute("value", obj.get("name")
								.toString()
								+ "=" + obj.get("value").toString());
					}
				}
			}
		}
	}

	public String getEventType1() {
		return eventType1;
	}

	public void setEventType1(String eventType1) {
		this.eventType1 = eventType1;
	}

	public String getEventClassImplName() {
		return eventClassImplName;
	}

	public void setEventClassImplName(String eventClassImplName) {
		this.eventClassImplName = eventClassImplName;
	}

	public String getEventType1Name() {
		return eventType1Name;
	}

	public void setEventType1Name(String eventType1Name) {
		this.eventType1Name = eventType1Name;
	}

	public String getEventTimerSet() {
		return eventTimerSet;
	}

	public void setEventTimerSet(String eventTimerSet) {
		this.eventTimerSet = eventTimerSet;
	}
}
