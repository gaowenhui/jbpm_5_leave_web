/**
 * 
 */
package com.toft.widgets.workflow.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class Transition {

	private String id = "";
	private String name = "";
	private String activityName = "";
	private String pass = "";
	private Node startNode;
	private Node endNode;
	private Object[] actions;
	private Map firtBendpoint;
	private Map secondBendpoint;
	private String isFold = "";
	private String description = "";// add by lsy 用来记录表达式
	
	private Object[] events;

	private String g; // lsy

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

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	/**
	 * @return the endNode
	 */
	public Node getEndNode() {
		return endNode;
	}

	/**
	 * @param endNode
	 *            the endNode to set
	 */
	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	/**
	 * @return the actions
	 */
	public Object[] getActions() {
		return actions;
	}

	/**
	 * @param actions
	 *            the actions to set
	 */
	public void setActions(Object[] actions) {
		this.actions = actions;
	}

	/**
	 * @return the firtBendpoint
	 */
	public Map getFirtBendpoint() {
		return firtBendpoint;
	}

	/**
	 * @param firtBendpoint
	 *            the firtBendpoint to set
	 */
	public void setFirtBendpoint(Map firtBendpoint) {
		this.firtBendpoint = firtBendpoint;
	}

	/**
	 * @return the secondBendpoint
	 */
	public Map getSecondBendpoint() {
		return secondBendpoint;
	}

	/**
	 * @param secondBendpoint
	 *            the secondBendpoint to set
	 */
	public void setSecondBendpoint(Map secondBendpoint) {
		this.secondBendpoint = secondBendpoint;
	}

	/**
	 * @return the isFold
	 */
	public String getIsFold() {
		return isFold;
	}

	/**
	 * @param isFold
	 *            the isFold to set
	 */
	public void setIsFold(String isFold) {
		this.isFold = isFold;
	}

	public void toFlowXML(Element element) throws Exception {
		// 创建根节点：流程节点
		Element transition = element.addElement("transition");
		transition.addAttribute("to", this.endNode.getName());
		transition.addAttribute("name", this.name);
		if (this.startNode.getType().equals("fork")) {
			transition.addAttribute("identityName", this.name);// this.activityName==>this.endNode.getActivityName()
		} else {
			transition.addAttribute("identityName", this.endNode
					.getActivityName());// this.activityName==>this.endNode.getActivityName()
		}
		// 加入一个属性，用于记录唯一识别name值

		transition.addAttribute("activityName", this.activityName);
		transition.addAttribute("pass", this.pass);
		transition.addAttribute("description", this.description);
		if (this.isFold.equals("1")) {
			transition.addAttribute("g", this.getG());
		}
		if(this.events != null && this.events.length > 0){
			for (int i = 0; i < this.events.length; i++) {
				EventFW eventfw = (EventFW) this.events[i];
				eventfw.toTransitionEventXML(transition);
			}
			
		}
	}

	public void toModelXML(Element element) throws Exception {
		Element transition = element.addElement("edge");
		transition.addAttribute("id", this.id);
		transition.addAttribute("name", this.name);
		Element label = transition.addElement("label");
		label.addAttribute("x", "5");
		label.addAttribute("y", "-10");
		if (this.isFold.equals("1")) {
			transition.addAttribute("g", this.getG());
		}

	}

	// not use:因为采用数据库的存储方式  2011.04.21 鹿鹏标注：暂时没有其他方法调用此方法
	public Transition toTransition(Element defineElement, Element diagramElement) {
		if (defineElement != null && diagramElement != null) {
			this.id = defineElement.attributeValue("id");
			this.name = defineElement.attributeValue("name");
			this.activityName = defineElement.attributeValue("activityName");
			this.pass = defineElement.attributeValue("pass");
			this.description = defineElement.attributeValue("description");
			Node end = new Node();
			end.setName(defineElement.attributeValue("to"));
			this.endNode = end;

			if (diagramElement.elements("bendpoint").size() > 0) {
				this.isFold = "1";
				List nodes = diagramElement.elements("bendpoint");
				List bendpoints = new ArrayList();
				for (Iterator it = nodes.iterator(); it.hasNext();) {
					Element bendpoint = (Element) it.next();
					Map point = new HashMap();
					point.put("x", bendpoint.attributeValue("w1"));
					point.put("y", bendpoint.attributeValue("h1"));
					bendpoints.add(point);
				}
				this.firtBendpoint = (Map) bendpoints.get(0);
				this.secondBendpoint = (Map) bendpoints.get(1);
			} else
				this.isFold = "0";

			return this;
		} else
			return null;

	}

	// Jason start
	// modify by lsy ：采用数据库的方式读取，改变G属性以适应JPDL4.0的要求 expr==>description
	public Transition toTransition(Element defineElement) throws Exception {
		if (defineElement != null) {
			this.id = defineElement.attributeValue("id");
			this.name = defineElement.attributeValue("name");
			this.activityName = defineElement.attributeValue("activityName");
			this.pass = defineElement.attributeValue("pass");
			this.description = defineElement.attributeValue("description");// description
			Node end = new Node();
			end.setName(defineElement.attributeValue("to"));
			this.endNode = end;

			/**
			 * modify by lsy : 由于传输的方式不同，所以取得数据后封装的方式也要相应改变 原来以节点元素的方式加入折点<bendpoint>给出x\y的值
			 * 现将折点的x\y值以数据拼装的方式给到状态节点的G属性
			 */
			if (!"".equals(defineElement.attributeValue("g"))
					&& null != defineElement.attributeValue("g")) {
				this.isFold = "1";
				String nodes[] = defineElement.attributeValue("g").split(",");
				// 将节点拼装为LIST形式
				List bendpoints = new ArrayList();
				// 第一个折点的值
				Map firtMap = new HashMap();
				firtMap.put("x", nodes[0]);
				firtMap.put("y", nodes[1]);
				bendpoints.add(firtMap);// 将第一个节点的MAP封装到LIST中
				// 第二个折点的值
				Map seconMap = new HashMap();
				seconMap.put("x", nodes[2]);
				seconMap.put("y", nodes[3]);
				bendpoints.add(seconMap);// 将第二个节点的MAP封装到LIST中

				this.firtBendpoint = (Map) bendpoints.get(0);
				this.secondBendpoint = (Map) bendpoints.get(1);
			} else
				this.isFold = "0";
			
			List events = new ArrayList();
			for (Iterator it = defineElement.elements().iterator(); it.hasNext();) {
				Element defineEventElement = (Element) it.next();
				EventFW eventfw = new EventFW();
				eventfw.toTransitionEvent(defineEventElement);
				events.add(eventfw);
				
			}
			this.events = events.toArray();
			
			return this;
		} else
			return null;

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
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	// lsy-start
	public String getG() {
		this.g = String.valueOf(this.firtBendpoint.get("x")) + ","
				+ String.valueOf(this.firtBendpoint.get("y")) + ","
				+ String.valueOf(this.secondBendpoint.get("x")) + ","
				+ String.valueOf(this.secondBendpoint.get("y"));
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	// lsy- end
	// add by lsy :线上表达式
	public String getDescription() {
		return description;
	}

	// add by lsy :线上表达式
	public void setDescription(String description) {
		this.description = description;
	}

	public Object[] getEvents() {
		return events;
	}

	public void setEvents(Object[] events) {
		this.events = events;
	}
}
