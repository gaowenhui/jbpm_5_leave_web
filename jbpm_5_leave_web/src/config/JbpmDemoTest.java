
/*
 * 软件著作权: 软通动力信息技术（集团）有限公司
 * 系统名称: 
 * 功能描述: 
 * 创建者: CodeMaker
 * 版本历史: V1.0
 * 创建日期: 2013-03-25
 */

package config;

public class JbpmDemoTest { 

    /**
     * Java属性:ID
     * 表的字段:text:ID
     */
    private java.lang.String id;

    /**
     * Java属性:NAME
     * 表的字段:text:业务名称
     */
    private java.lang.String name;

    /**
     * Java属性:CODE
     * 表的字段:text:业务编码
     */
    private java.lang.String code;

    /**
     * Java属性:processid
     * 表的字段:text:流程ID
     */
    private java.lang.String processid;

    /**
     * Java属性:NOTE
     * 表的字段:text:备注
     */
    private java.lang.String note;

    /**
     * Java属性:isstatus
     * 表的字段:text:状态 
     */
    private java.lang.String isstatus;

    /**
     * Java属性:processDefinitionCode
     * 表的字段:text:流程模版code
     */
    private java.lang.String processDefinitionCode;

    
    public java.lang.String getIsstatus() {
		return isstatus;
	}

	public void setIsstatus(java.lang.String isstatus) {
		this.isstatus = isstatus;
	}

	public java.lang.String getProcessDefinitionCode() {
		return processDefinitionCode;
	}

	public void setProcessDefinitionCode(java.lang.String processDefinitionCode) {
		this.processDefinitionCode = processDefinitionCode;
	}

	/**
     * @return id
     */
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }
    /**
     * @return name
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    /**
     * @return code
     */
    public java.lang.String getCode() {
        return this.code;
    }

    /**
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    } 

	public java.lang.String getProcessid() {
		return processid;
	}

	public void setProcessid(java.lang.String processid) {
		this.processid = processid;
	}

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}
    
    
}
