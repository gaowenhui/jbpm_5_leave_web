/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.toft.widgets.workflow.db.SQLProvider;
import com.toft.widgets.workflow.utils.ClassUtil;
import com.toft.widgets.workflow.utils.CreateRandomId;
import com.toft.widgets.workflow.utils.PropertiesUtils;
import com.toft.widgets.workflow.vo.BehaviorManagement;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 17, 2010
 * 
 */
public class WorkflowConfigAss extends BaseAss {

	private static WorkflowConfigAss ass = null;

	public static WorkflowConfigAss getInstance() {
		if (ass == null) {
			Properties prop = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
			String className = prop.getProperty("event.config");
			if (className != null && !className.equals(""))
				try {
					ass = (WorkflowConfigAss) Class.forName(className).newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				ass = new WorkflowConfigAss();
		}
		return ass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toft.widgets.workflow.interfaces.IWorkflowConfig#getAllEventAction
	 * (java.lang.Object[])
	 */
	public List getBehaviorManagementList(Object[] arrTemp) {
		BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
		List eventAction = new ArrayList();
		String evType = behaviorManagement.getEvType();
		String name = behaviorManagement.getName();
		Connection conn = getConnection(context.getConParam());
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQLProvider.getBehaviorManagementList(evType, name));
			ps.setString(1, evType);
			if (StringUtils.isNotEmpty(name)) {
				ps.setString(2, "%" + name + "%");
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("id", rs.getString(1));
				map.put("code", rs.getString(2));
				map.put("name", rs.getString(3));
				map.put("classpath", rs.getString(4));
				map.put("evType", rs.getString(5));
				eventAction.add(map);
			}
			closeResultSet(rs);
			closePrepareStatement(ps);
			closeConnection(conn);
		} catch (SQLException e) {
			closePrepareStatement(ps);
			closeConnection(conn);
			e.printStackTrace();
		}
		return eventAction;
	}

	public String saveBehaviorManagement(Object[] arrTemp) {
		String result = isNameRepeat(arrTemp);
		if ("noRepeat".equals(result)) {
			BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
			String id = behaviorManagement.getId();
			if (StringUtils.isNotEmpty(id)) {
				result = updateBehaviorManagement(arrTemp);
			} else {
				result = insertBehaviorManagement(arrTemp);
			}
		}
		return result;
	}

	public String insertBehaviorManagement(Object[] arrTemp) {
		PreparedStatement ps = null;
		Connection conn = null;
		String result = "true";
		try {
			BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
			if ("2".equals(behaviorManagement.getEvType())) {
				result = ClassUtil.validateClassPath(behaviorManagement.getClasspath());
				if("noExist".equals(result)){
					return result;
				}else{
					result = "true";
				}
				
			}
			FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
			conn = getConnection(context.getConParam());
			String id = CreateRandomId.createId_Thirty();
			ps = conn.prepareStatement(SQLProvider.insertBehaviorManagement());
			ps.setString(1, id);
			ps.setString(2, behaviorManagement.getCode());
			ps.setString(3, behaviorManagement.getName());
			ps.setString(4, behaviorManagement.getClasspath());
			ps.setString(5, behaviorManagement.getEvType());
			ps.execute();

			if(conn!=null&&!conn.getAutoCommit()){ // 非事物控制类，需要判断事物是否自动提交，如果非，就提交事物 add by chenshuqing 20130419
				conn.commit();	
			}
			
			closePrepareStatement(ps);
			closeConnection(conn);
		} catch (Exception e) {
			result = "false";
			rollBackConnection(conn);
			closePrepareStatement(ps);
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closePrepareStatement(ps);
			closeConnection(conn);
		}
		return result;
	}

	public String updateBehaviorManagement(Object[] arrTemp) {
		PreparedStatement ps = null;
		Connection conn = null;
		String result = "true";
		try {
			BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
			if ("2".equals(behaviorManagement.getEvType())) {
				result = ClassUtil.validateClassPath(behaviorManagement.getClasspath());
				if("noExist".equals(result)){
					return result;
				}else{
					result = "true";
				}
			}
			FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
			conn = getConnection(context.getConParam());
			ps = conn.prepareStatement(SQLProvider.updateBehaviorManagement());
			ps.setString(1, behaviorManagement.getCode());
			ps.setString(2, behaviorManagement.getName());
			ps.setString(3, behaviorManagement.getClasspath());
			ps.setString(4, behaviorManagement.getEvType());
			ps.setString(5, behaviorManagement.getId());
			ps.execute();
			
			if(conn!=null&&!conn.getAutoCommit()){ // 非事物控制类，需要判断事物是否自动提交，如果非，就提交事物 add by chenshuqing 20130419
				conn.commit();	
			}
			
			closePrepareStatement(ps);
			closeConnection(conn);
		} catch (Exception e) {
			result = "false";
			rollBackConnection(conn);
			closePrepareStatement(ps);
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closePrepareStatement(ps);
			closeConnection(conn);
		}
		return result;
	}

	public String deleteBehaviorManagement(Object[] arrTemp) {
		PreparedStatement ps = null;
		Connection conn = null;
		String result = "true";
		try {
			BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
			FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
			conn = getConnection(context.getConParam());
			ps = conn.prepareStatement(SQLProvider.deleteBehaviorManagement());
			ps.setString(1, behaviorManagement.getId());
			ps.execute();
			
			if(conn!=null&&!conn.getAutoCommit()){ // 非事物控制类，需要判断事物是否自动提交，如果非，就提交事物 add by chenshuqing 20130419
				conn.commit();	
			}
			
			closePrepareStatement(ps);
			closeConnection(conn);
		} catch (Exception e) {
			result = "false";
			rollBackConnection(conn);
			closePrepareStatement(ps);
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closePrepareStatement(ps);
			closeConnection(conn);
		}
		return result;
	}

	public String isNameRepeat(Object[] arrTemp) {
		PreparedStatement ps = null;
		Connection conn = null;
		String result = "noRepeat";
		try {
			BehaviorManagement behaviorManagement = (BehaviorManagement) arrTemp[0];
			String id = behaviorManagement.getId();
			String evType = behaviorManagement.getEvType();
			String name = behaviorManagement.getName();
			FlowDesignerContext context = (FlowDesignerContext) arrTemp[1];
			conn = getConnection(context.getConParam());
			ps = conn.prepareStatement(SQLProvider.getBehaviorManagementCount(behaviorManagement));
			ps.setString(1, name);
			ps.setString(2, evType);
			if ("2".equals(evType)) {
				ps.setString(3, behaviorManagement.getClasspath());
			}
			if (StringUtils.isNotEmpty(id)) {
				ps.setString(4, id);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					result = "repeat";
				}
			}
			closeResultSet(rs);
			closePrepareStatement(ps);
			closeConnection(conn);
		} catch (Exception e) {
			rollBackConnection(conn);
			closePrepareStatement(ps);
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closePrepareStatement(ps);
			closeConnection(conn);
		}
		return result;
	}
}
