/**
 * 
 */
package com.toft.widgets.workflow.vo;

import org.dom4j.Element;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jul 2, 2009
 * 
 */
public class Variable {

	private String id = "";
	private String name = "";
	private String activityName = "";
	private String read = "";
	private String write = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}

	public void toXML(Element element) throws Exception {
		Element variable = element.addElement("flow-variable");
		variable.addAttribute("id", this.id);
		variable.addAttribute("name", this.name);
		variable.addAttribute("activityName", this.activityName);
	}

	public Variable toVariable(Element element) throws Exception {
		this.id = element.attributeValue("id");
		this.name = element.attributeValue("name");
		this.activityName = element.attributeValue("activityName");
		return this;
	}
}
