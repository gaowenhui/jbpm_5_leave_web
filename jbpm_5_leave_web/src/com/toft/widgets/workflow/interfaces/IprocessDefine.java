package com.toft.widgets.workflow.interfaces;

import java.sql.Connection;
import java.util.List;

import com.toft.widgets.workflow.vo.FlowDesignerContext;

public interface IprocessDefine {
	/**
	 * 查询返回所有流程
	 * 
	 * @return List
	 */
	public List getWorkflowList(Connection conn, FlowDesignerContext context);

	/**
	 * 提供外部接口，根据传进来的ID值，返回流程流程信息
	 * 
	 * @author lsy
	 * @param id
	 * @return List
	 */
	public List getWorkflowInfo(String id, Connection conn);

	/**
	 * 提供外部接口，根据传进来的ID值判断，如果参数为0(已创建)的时候将状态更改为1(已发布)
	 * 
	 * @author lsy
	 * @param id
	 * @return String
	 */
	public String changeWorkflowStatus(Connection conn, String id);

	/**
	 * 提供外部接口，根据传进来的ID值更新版本号
	 * 
	 * @param conn
	 * @param id
	 * @param pdId
	 * @param pdVersion
	 * @return
	 */
	public boolean updateProVersion(Connection conn, String id, String pdId,
			int pdVersion);
}
