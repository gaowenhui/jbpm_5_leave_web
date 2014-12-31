package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.toft.widgets.workflow.db.DBAssistance;
import com.toft.widgets.workflow.db.IDBAssistance;
import com.toft.widgets.workflow.utils.PropertiesUtils;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 18, 2009
 * 
 */
public class BaseAss {

	protected IDBAssistance db = null;

	public BaseAss() {
		String classPath = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties").getProperty("connection.provider");
		if (classPath != null && !classPath.equals(""))
			try {
				db = (IDBAssistance) Class.forName(classPath).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			db = new DBAssistance();
	}

	public Connection getConnection(String connParam) {
		return db.getConnection(connParam);
	}

	public Connection getConnection() {
		return db.getConnection();
	}

	public void closePrepareStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement close error!");
				e.printStackTrace();
			}
		}
	}

	public void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("Statement close error!");
				e.printStackTrace();
			}
		}
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollBackConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeResultSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet close error!");
				e.printStackTrace();
			}
	}
}