/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.toft.widgets.workflow.taskrole.TaskRoleDao;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 7, 2010
 * 
 */
public class TaskAss extends BaseAss {

	private static TaskAss ass;

	public static TaskAss getInstance() {
		if (ass == null)
			ass = new TaskAss();
		return ass;
	}

	public List getTaskAssAllSimpleRuleList(Object[] arrTemp) {
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		List temp = new ArrayList();
		TaskRoleDao dao = TaskRoleDao.getInstance();
		try {
			conn = getConnection(context.getConParam());
			temp = dao.findAllTaskRole(conn, context.getAgency(), context.getDepartment());
			closeConnection(conn);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		}
		return temp;
	}

	public List getTaskAssAllDynList(Object[] arrTemp) {
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		List temp = new ArrayList();
		TaskRoleDao dao = TaskRoleDao.getInstance();
		try {
			conn = getConnection(context.getConParam());
			//temp = dao.findAllDynamicTaskRole(conn);
			closeConnection(conn);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		}

		return temp;
	}

	public List getCounterSignAllPostRuleList(Object[] arrTemp) {
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		List temp = new ArrayList();
		TaskRoleDao dao = TaskRoleDao.getInstance();
		try {
			conn = getConnection(context.getConParam());
			temp = dao.findAllSimpleTaskRoleForGroup(conn, context.getAgency(), context.getDepartment());
			closeConnection(conn);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}

		return temp;
	}
}
