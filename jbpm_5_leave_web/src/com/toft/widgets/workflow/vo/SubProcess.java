package com.toft.widgets.workflow.vo;

public class SubProcess {
	private String subProcessName = "";
	private String subProcessCode = "";
	private String outcome="";

	public String getSubProcessName() {
		return subProcessName;
	}

	public void setSubProcessName(String subProcessName) {
		this.subProcessName = subProcessName;
	}

	public String getSubProcessCode() {
		return subProcessCode;
	}

	public void setSubProcessCode(String subProcessCode) {
		this.subProcessCode = subProcessCode;
	}

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
	
}
