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

import org.dom4j.Element;

import com.toft.widgets.workflow.utils.PropertiesUtils;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class Workflow {

	private String id = "";
	private String name = "";
	private String activityName = "";
	private int width;
	private int height;
	private Object[] nodes;
	private Object[] transitions;
	private String processDefine = "";
	private String processType = "";
	private String pdStatus = "";

	// 用于事件处理
	private Object[] events;

	private Object[] wfParams;

	public String getProcessDefine() {
		return processDefine;
	}

	public void setProcessDefine(String processDefine) {
		this.processDefine = processDefine;
	}

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
	 * @return the nodes
	 */
	public Object[] getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(Object[] nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the transitions
	 */
	public Object[] getTransitions() {
		return transitions;
	}

	/**
	 * @param transitions
	 *            the transitions to set
	 */
	public void setTransitions(Object[] transitions) {
		this.transitions = transitions;
	}

	public void toModelXML(Element element) throws Exception {
		element.addAttribute("id", this.id);
		element.addAttribute("name", this.name);
		element.addAttribute("activityName", this.activityName);
		element.addAttribute("width", String.valueOf(this.width));
		element.addAttribute("height", String.valueOf(this.height));
		// 加入xmlns属性 2010-1-7
		if (this.nodes != null)
			for (int i = 0; i < this.nodes.length; i++) {
				Node node = (Node) this.nodes[i];
				node.toModelXML(element);
			}

	}

	public void toFlowXML(Element element) throws Exception {
		// 与前台绑定，取得值为后台赋值 this均为前台值
		// 第一个element元素
		element.addAttribute("activityName", this.activityName);
		element.addAttribute("processtype", this.processType);
		/**
		 * ***************************暂时注释掉ON节点--start**************************
		 * ********
		 */
		if (this.wfParams != null) {
			String str_wfParams = "";
			for (int i = 0; i < this.wfParams.length; i++) {
				Map wfParams = (Map) this.wfParams[i];
				str_wfParams += "name:" + wfParams.get("name") + "-" + "code:"
						+ wfParams.get("code") + "-" + "variable:"
						+ wfParams.get("variable") + ";";
			}
			element.addAttribute("str_wfParams", str_wfParams);
		}
		/**
		 * ******************************暂时注释掉ON节点--end*************************
		 * ***************
		 */
		// 如果前台node节点不为空，则将第一个element传到node类里进行封装
		if (this.nodes != null)
			for (int i = 0; i < this.nodes.length; i++) {
				Node node = (Node) this.nodes[i];
				node.toFlowXML(element);
		}
		//鹿鹏：修改业务逻辑，去除事件处理(老的2011.04.19前)--2011.04.19再次改造“多事件”
		if (this.events != null && this.events.length > 0) {
			for (int i = 0; i < this.events.length; i++) {
				EventFW eventfw = (EventFW) this.events[i];
				eventfw.toEventXML(element);
			}
		}
		/*去掉“如果没有配置事件追加默认class”的代码段
		 * else{
			//鹿鹏：当事件选项卡没有开启的时候，赋给流程默认的事件处理元素
			Properties property = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
			boolean enableOrDisable = "disable".equals(property.get("property.eventPropTab"));
			if(enableOrDisable){
				this.events = new Object[1];
				EventFW eventFWTemp = new EventFW();
				eventFWTemp.setEventType1("end");
				eventFWTemp.setEventClassImpl("com.toft.bpm.approval.handler.ToftDefaultActionHandler");
				eventFWTemp.toEventXML(element);
			}
		}*/
		/*默认事件是否追击，通过Properties文件的property.event.defaultUse进行判断
		Properties property = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
		boolean enableOrDisable = "enable".equals(property.get("property.event.defaultUse"));
		if(enableOrDisable){
			this.events = new Object[1];
			EventFW eventFWTemp = new EventFW();
			eventFWTemp.setEventType1("end");
			eventFWTemp.setEventClassImpl("com.toft.bpm.approval.handler.ToftEndActionHandler");
			eventFWTemp.toEventXML(element);
		}**/
	}

	// Jason start
	public Workflow toWorkflow(Element defineElement) throws Exception {
		this.id = defineElement.attributeValue("id");
		this.name = defineElement.attributeValue("name");
		this.activityName = defineElement.attributeValue("activityName");
		this.processType = defineElement.attributeValue("processtype");

		/**
		 * *********************************流程参数******************************** *
		 */
		if (defineElement.attributeValue("str_wfParams") != null
				&& !defineElement.attributeValue("str_wfParams").equals("")) {
			List wfParamsList_tempObject = new ArrayList();
			Map temp_map = null;
			String temp_wfParams = defineElement.attributeValue("str_wfParams");
			String[] tempObject = temp_wfParams.split(";");
			for (int i = 0; i < tempObject.length; i++) {
				String[] temp = tempObject[i].split("-");
				temp_map = new HashMap();
				for (int j = 0; j < temp.length; j++) {
					String[] p_temp = temp[j].split(":");
					temp_map.put(p_temp[0], p_temp[1]);
				}
				wfParamsList_tempObject.add(temp_map);
			}
			this.wfParams = wfParamsList_tempObject.toArray();
		}
		/**
		 * ********************************************************************* *
		 */
		List defineNodes = defineElement.elements();
		List nodes = new ArrayList();
		List events = new ArrayList();// 用于事件
		for (Iterator it = defineNodes.iterator(); it.hasNext();) {
			Element defineNode = (Element) it.next();
			if (!defineNode.getName().equals("on")) {
				Node node = new Node();
				node.toNode(defineNode);
				nodes.add(node);
			} else {
				EventFW eventfw = new EventFW();
				eventfw.toEvent(defineNode);
				events.add(eventfw);
			}
		}
		this.nodes = nodes.toArray();
		// 如果事件集合不为空，则放入所有的事件
		if (events.size() != 0) {
			this.events = events.toArray();
		}

		List transitions = new ArrayList();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			for (int i = 0; i < node.getGoOut().length; i++) {
				Transition transition = (Transition) node.getGoOut()[i];
				transitions.add(transition);
				for (int j = 0; j < this.nodes.length; j++) {
					Node endNode = (Node) this.nodes[j];
					if (null != endNode.getName()
							&& endNode.getName().equals(
									transition.getEndNode().getName())) {
						transition.setEndNode(endNode);
						List ins = new ArrayList();
						if (endNode.getGoIn() != null) {
							for (int k = 0; k < endNode.getGoIn().length; k++)
								ins.add(endNode.getGoIn()[k]);
						}
						ins.add(transition);
						endNode.setGoIn(ins.toArray());
						break;
					}
				}
			}
		}
		this.transitions = transitions.toArray();
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

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public Object[] getEvents() {
		return events;
	}

	public void setEvents(Object[] events) {
		this.events = events;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public Object[] getWfParams() {
		return wfParams;
	}

	public void setWfParams(Object[] wfParams) {
		this.wfParams = wfParams;
	}

	public String getPdStatus() {
		return pdStatus;
	}

	public void setPdStatus(String pdStatus) {
		this.pdStatus = pdStatus;
	}

}
