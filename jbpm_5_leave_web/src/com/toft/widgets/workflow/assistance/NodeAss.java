/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 7, 2010
 * 
 */
public class NodeAss extends BaseAss {

	private static NodeAss ass;

	public static NodeAss getInstance() {
		if (ass == null)
			ass = new NodeAss();
		return ass;
	}

	public List getTaskDycRuleList(Object[] arrTemp) {
		Connection conn = null;
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		List temp = new ArrayList();
//		DynamicNodeDao dao = new DynamicNodeDao();
//		try {
//			conn = getConnection(context.getConParam());
//			temp = dao.findAllDynamicNode(conn);
//		} catch (Exception e) {
//			closeConnection(conn);
//			e.printStackTrace();
//		}
		return temp;
	}

}
