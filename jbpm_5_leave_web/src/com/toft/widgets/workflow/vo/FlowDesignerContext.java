/**
 * 
 */
package com.toft.widgets.workflow.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 17, 2010
 * 
 */
public class FlowDesignerContext {

	private String conParam = "";
	private String user = "";
	private String agency = "";
	private String department = "";
	private String webApp = "";

	public String getWebApp() {
		return webApp;
	}

	public void setWebApp(String webApp) {
		this.webApp = webApp;
	}

	public String getConParam() {
		return conParam;
	}

	public void setConParam(String conParam) {
		this.conParam = conParam;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Map toMap() {
		Map map = new HashMap();
		map.put("conParam", conParam);
		map.put("user", user);
		map.put("agency", agency);
		map.put("department", department);
		map.put("webApp", webApp);
		return map;
	}
}
