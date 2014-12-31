/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.toft.widgets.workflow.utils.ConstValue;
import com.toft.widgets.workflow.utils.PropertiesUtils;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 7, 2010
 * 
 */
public class ParamtersAss extends BaseAss {

	private static ParamtersAss ass;

	public static ParamtersAss getInstance() {
		if (ass == null)
			ass = new ParamtersAss();
		return ass;
	}

	public List getParamsList(Object[] arrTemp) {
		List temp = new ArrayList();
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection(context.getConParam());
			String sql = "select * from "+ConstValue.JBPM4_GLOBAL_PARAM;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map param = new HashMap();
				param.put("name", rs.getString("NAME"));
				param.put("code", rs.getString("CODE"));
				param.put("variable", rs.getString("VARIABLE_TYPE"));
				temp.add(param);
			}
		} catch (Exception e) {
			closeConnection(conn);
			closePrepareStatement(ps);
			closeResultSet(rs);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return temp;
	}

	public Map getConfig() {
		Map temp = new HashMap();
		Properties prop = PropertiesUtils.getPropertyUtil().getProperty("workflow.properties");
		Enumeration keys = prop.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			temp.put(key, prop.get(key));
		}
		return temp;
	}
}
