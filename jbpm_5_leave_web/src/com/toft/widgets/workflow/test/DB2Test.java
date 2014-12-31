package com.toft.widgets.workflow.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB2Test {
	private static String driver = "com.ibm.db2.jcc.DB2Driver";
	private static String URL = "jdbc:db2://10.24.16.70:50000/sdcwf";
	private static String username = "administrator";
	private static String password = "isoft@3";

	/*
	 <datasource id="1" databaseType="DB2" usedDataSource="false" jndi="">
	  <connection port="50000" password="lnnaoqamppvzpppz" dbName="sdcwf"
	  userName="administrator" serverName="10.24.16.70" databaseType="DB2" />
	  </datasource>
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			System.out.println("驱动接口程序连接成功!!!");
		} catch (Exception e) {
			System.out.println("驱动接口程序连接失败!!!" + driver);
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(URL, username, password);
			if (!con.isClosed()) {
				System.out.println("连接DB成功!!!");
			}
		} catch (Exception ex) {
			System.out.println("连接DB失败!!!" + driver);
			ex.printStackTrace();
		}

		try {

			pstmt = con.prepareStatement("select t.* from jbpm4_deploy t");//administrator
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 表中只有id和username两个字段
				System.out.println(rs.getString("NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
