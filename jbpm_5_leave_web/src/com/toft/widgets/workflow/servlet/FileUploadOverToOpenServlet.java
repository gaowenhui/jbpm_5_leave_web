package com.toft.widgets.workflow.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class FileUploadOverToOpenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 获取session信息，组装xml，传至前台swf
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String workflow_id = (String)session.getAttribute("WORKFLOW_ID");
		String workflow_name = (String)session.getAttribute("WORKFLOW_NAME");
		session.removeAttribute("WORKFLOW_ID");
		session.removeAttribute("WORKFLOW_NAME");
		
		String string =  getResultXml(workflow_id,workflow_name);
		//"<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><workflow id="+workflow_id+" name="+workflow_name+" /></data>";
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(string);
		out.flush();
		out.close();
	}
	
	private String getResultXml(String id,String name){
		Document document = DocumentHelper.createDocument();
		Element outer = document.addElement("data");
		Element workflow = outer.addElement("workflow");
		workflow.addAttribute("id", "_"+id);
		workflow.addAttribute("name", "_"+name);
		return document.asXML();
	}

	
}
