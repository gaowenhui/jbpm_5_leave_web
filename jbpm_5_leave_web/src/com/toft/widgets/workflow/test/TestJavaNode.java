package com.toft.widgets.workflow.test;

public class TestJavaNode {
	public int uuid;
	public String temp;
	private String name;
	private String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public long add(long a,long b){
		return a+b;
	}
	
	public String apend(String source,String a){
		return source+a;
	}
	
}
