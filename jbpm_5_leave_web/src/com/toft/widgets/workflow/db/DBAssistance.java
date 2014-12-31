/**
 * 
 */
package com.toft.widgets.workflow.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.toft.core2.dao.database.DbUtils;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create Jun 16, 2009
 * 
 */
public class DBAssistance implements IDBAssistance {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 单位 "jdbc:oracle:thin:@10.24.25.31:1521:orcl", "pt4",
			// "password"--oracle.jdbc.driver.OracleDriver
			// 家："jdbc:oracle:thin:@127.0.0.1:1521:orcl2", "pt4",
			// "password"--oracle.jdbc.driver.OracleDriver
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.10.21.229:1521:orcl", "gdf0303",
					"gdf0303");
		} catch (SQLException e) {
			System.out.println("get connection error!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public Connection getConnection(String str) {
		Connection conn = null;
		if (str == null || str == "") {
			str = "1";
		}
		try {
			conn = DbUtils.getConnection(str);
		} catch (Exception e) {
			System.out.println("get connection error!");
			e.printStackTrace();
		}
		return conn;
	}

}
