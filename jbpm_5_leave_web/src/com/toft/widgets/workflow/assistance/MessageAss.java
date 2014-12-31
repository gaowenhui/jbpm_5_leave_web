/**
 * 
 */
package com.toft.widgets.workflow.assistance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.toft.widgets.sys.workflow.message.MessageService;
import com.toft.widgets.workflow.vo.FlowDesignerContext;

/**
 * @author cswang mail to : <cswang@isoftstone.com>
 * @create May 7, 2010
 * 
 */
public class MessageAss extends BaseAss {

	/**
	 * @throws ToftException
	 */

	private MessageService mess = MessageService.getInstance();

	public static MessageAss ass;

	public static MessageAss getInstance() {
		if (ass == null)
			ass = new MessageAss();
		return ass;
	}

	public List getInitArray(Object[] arrTemp) {
		List temp = new ArrayList();
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		Map params = null;
		try {
			conn = getConnection(context.getConParam());
			params = context.toMap();
			params.put("connection", conn);
			temp = mess.getSupportMessageSenders(params);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return temp;
	}

	public List getMessagePatternList(Object[] arrTemp) {
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		List temp = new ArrayList();
		Connection conn = null;
		Map params = null;
		try {
			conn = getConnection(context.getConParam());
			params = context.toMap();
			params.put("connection", conn);
			temp = mess.getSupportMessageSenders(params);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return temp;
	}

	public List getMessageTemplateList(Object[] arrTemp) {
		List temp = new ArrayList();
		FlowDesignerContext context = (FlowDesignerContext) arrTemp[0];
		Connection conn = null;
		Map params = null;
		try {
			conn = getConnection(context.getConParam());
			params = context.toMap();
			params.put("connection", conn);
			temp = mess.getAllMessageTemplate(params);
		} catch (Exception e) {
			closeConnection(conn);
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return temp;
	}

}
