/**
 * 
 */
package com.toft.widgets.workflow.test;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

import com.toft.widgets.workflow.db.DBAssistance;


/**
 * @author cswang  mail to : <cswang@isoftstone.com>
 * @create Jun 18, 2009
 * 
 */
public class DBTest extends TestCase {
	
	protected void setUp() throws Exception {

	}

	public void testXMLUtil() {
		DBAssistance db = new DBAssistance();
		Connection conn = db.getConnection();
		if(conn != null)
			System.out.println("get connection sucess!");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
