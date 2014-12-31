/**
 * 
 */
package com.toft.widgets.workflow.db;

import java.sql.Connection;

/**
 * @author cswang  mail to : <cswang@isoftstone.com>
 * @create May 11, 2010
 * 
 */
public interface IDBAssistance {

	public Connection getConnection();
	
	public Connection getConnection(String str);
	
}
