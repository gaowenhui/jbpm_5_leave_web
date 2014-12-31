/**
 * 
 */
package com.toft.widgets.workflow.vo;

import java.io.Serializable;

/**
 * @author lllianga
 * @date 2011-12-13下午04:38:28
 */
public class BehaviorManagement implements Serializable{

	private static final long serialVersionUID = 5014359789726841011L;
   
	private String id;
	private String code;
	private String name;
	private String classpath;
	private String evType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getEvType() {
		return evType;
	}
	public void setEvType(String evType) {
		this.evType = evType;
	}
	
}
