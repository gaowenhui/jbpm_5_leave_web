package com.toft.widgets.workflow.implement;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toft.widgets.workflow.db.SQLProvider;
import com.toft.widgets.workflow.interfaces.IprocessDefine;
import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.utils.StringUtil;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

public class ProcessDefine implements IprocessDefine {

	public String changeWorkflowStatus(Connection conn, String id) {
		String msg = null;
		if (conn == null) {
			return "连接为空";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select code from " + ConstValue.JBPM4_DEPLOY + " where ID=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			String code = null;
			if (rs.next()) {
				code = rs.getString("CODE");
			} else {
				return "无此流程";
			}
			closeResultSet(rs);
			ps = conn.prepareStatement("update " + ConstValue.JBPM4_DEPLOY + " set pdstatus=? where code=?");
			ps.setString(1, "1");
			ps.setString(2, code);
			ps.execute();
			closePrepareStatement(ps);
		} catch (SQLException e) {
			closePrepareStatement(ps);
			e.printStackTrace();
		}
		return msg;
	}

	public List getWorkflowInfo(String id, Connection conn) {
		if (conn == null)
			return null;
		List workflowList = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Clob clob = null;
			ps = conn.prepareStatement("select * from " + ConstValue.JBPM4_DEPLOY + " where ID=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map workflow = new HashMap();
				workflow.put("id", rs.getString("id".toUpperCase()));
				workflow.put("name", rs.getString("name".toUpperCase()));
				workflow.put("code", rs.getString("code".toUpperCase()));
				workflow.put("pdname", rs.getString("pdname".toUpperCase()));
				workflow.put("pdstatus", rs.getString("pdstatus").toUpperCase());
				clob = rs.getClob("PROCESSDEFINE");
				String tempstr = StringUtil.clobToString(clob);
				if (!"".equals(tempstr)) {
					workflow.put("processDefine", tempstr);
				}
				workflowList.add(workflow);
			}
			closeResultSet(rs);
			closePrepareStatement(ps);
		} catch (SQLException e) {
			closeResultSet(rs);
			closePrepareStatement(ps);
			e.printStackTrace();
		}

		return workflowList;
	}

	public List getWorkflowList(Connection conn, FlowDesignerContext context) {
		if (conn == null)
			return null;
		PreparedStatement ps = null;
		List workflowList = new ArrayList();
		String statusTemp = null;
		try {
			Clob clob = null;
			ps = conn.prepareStatement(SQLProvider.selectAllWorkflow(context));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map workflow = new HashMap();
				workflow.put("id", rs.getString("id".toUpperCase()));
				workflow.put("name", rs.getString("name".toUpperCase()));
				workflow.put("code", rs.getString("code".toUpperCase()));
				workflow.put("pdversion", rs.getString("pdversion".toUpperCase()));
				statusTemp = rs.getString("pdstatus".toUpperCase());
				if ("0".equals(statusTemp)) {
					workflow.put("pdstatus", "未发布");
				}
				if ("1".equals(statusTemp)) {
					workflow.put("pdstatus", "已发布");
				}
				if ("2".equals(statusTemp)) {
					workflow.put("pdstatus", "修改未发布");
				}
				clob = rs.getClob("PROCESSDEFINE");
				String tempstr = StringUtil.clobToString(clob);
				if (!"".equals(tempstr)) {
					workflow.put("processDefine", tempstr);
				}
				workflowList.add(workflow);
			}
			closeResultSet(rs);
			closePrepareStatement(ps);
		} catch (SQLException e) {
			closePrepareStatement(ps);
			e.printStackTrace();
		}
		return workflowList;
	}

	public boolean updateProVersion(Connection conn, String id, String pdId, int pdVersion) {
		boolean flag;
		if (conn == null) {
			return false;
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from " + ConstValue.JBPM4_DEPLOY + " where ID=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ps = conn.prepareStatement(SQLProvider.updateWorkflowByVersion());
				ps.setInt(1, pdVersion);
				ps.setString(2, pdId);
				ps.setString(3, id);
				ps.execute();
			}
			flag = true;
			closeResultSet(rs);
			closePrepareStatement(ps);
		} catch (SQLException e) {
			closePrepareStatement(ps);
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	protected void closePrepareStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement close error!");
				e.printStackTrace();
			}
		}
	}

	protected void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("Statement close error!");
				e.printStackTrace();
			}
		}
	}

	protected void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void closeResultSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet close error!");
				e.printStackTrace();
			}
	}

}
