/**
 * 
 */
package com.toft.widgets.workflow.monitorFW.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.sql.CLOB;

import org.dom4j.Element;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 15, 2009
 * 
 */
public class Workflow {

	private String id;
	private String name;
	private String activityName;
	private int width;
	private int height;
	private Object[] nodes;
	private Object[] transitions;
	private String processDefine;
	private CLOB clob;
	// add by lsy : add key version
	private String key;
	private String version;
	// 用于事件处理
	private Object[] events;
	// 用于变量的存储
	private Object[] variables;

	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public CLOB getClob() {
		return clob;
	}

	public void setClob(CLOB clob) {
		this.clob = clob;
	}

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

	public Workflow toWorkflow(Element defineElement) throws Exception {
		this.id = defineElement.attributeValue("id");
		this.name = defineElement.attributeValue("name");
		this.activityName = defineElement.attributeValue("activityName");

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

	// add by lsy
	public String getKey() {
		return key;
	}

	// add by lsy
	public void setKey(String key) {
		this.key = key;
	}

	// add by lsy
	public String getVersion() {
		return version;
	}

	// add by lsy
	public void setVersion(String version) {
		this.version = version;
	}

	public Object[] getEvents() {
		return events;
	}

	public void setEvents(Object[] events) {
		this.events = events;
	}

	public Object[] getVariables() {
		return variables;
	}

	public void setVariables(Object[] variables) {
		this.variables = variables;
	}

}
