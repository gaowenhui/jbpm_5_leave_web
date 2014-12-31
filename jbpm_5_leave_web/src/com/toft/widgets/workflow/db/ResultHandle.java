/**
 * 
 */
package com.toft.widgets.workflow.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.toft.widgets.workflow.vo.Agency;
import com.toft.widgets.workflow.vo.Duty;
import com.toft.widgets.workflow.vo.User;

/**
 * @author cswang  mail to : <cswang@isoftstone.com>
 * @create Jun 23, 2009
 * 
 */
public class ResultHandle {

	public Agency agencyHandle(ResultSet rs) throws SQLException {
		Agency agency = new Agency();
		agency.setId(rs.getString("id".toUpperCase()));
		agency.setName(rs.getString("name".toUpperCase()));
		agency.setCode(rs.getString("code".toUpperCase()));
		return agency;
	}
	
	public User userHandle(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id".toUpperCase()));
		user.setName(rs.getString("name".toUpperCase()));
		user.setCode(rs.getString("code".toUpperCase()));
		return user;
	}
	
	public Duty dutyHandle(ResultSet rs) throws SQLException {
		Duty duty = new Duty();
		duty.setId(rs.getString("id".toUpperCase()));
		duty.setName(rs.getString("name".toUpperCase()));
		duty.setCode(rs.getString("code".toUpperCase()));
		return duty;
	}
}
