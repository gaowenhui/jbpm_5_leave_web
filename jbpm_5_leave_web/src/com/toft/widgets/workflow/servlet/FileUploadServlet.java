package com.toft.widgets.workflow.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.toft.widgets.workflow.assistance.WorkflowAss;
import com.toft.widgets.workflow.vo.FlowDesignerContext;
import com.toft.widgets.workflow.vo.Workflow;

public class FileUploadServlet extends HttpServlet {
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
	 * 取的文件路径，并写入数据库
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List items = upload.parseRequest(request);
			Iterator i = items.iterator();
			String fileName = "";
			String s = "";
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				fileName = fi.getName();
				if (fileName != null) {
					BufferedReader breader = new BufferedReader(
							new InputStreamReader(fi.getInputStream(), "UTF-8"));
					StringBuffer buf = new StringBuffer();
					while (breader.ready())
						buf.append((char) breader.read());
					breader.close();
					s = buf.toString();
				}
			}
			if (!"".equals(s)) {
				FlowDesignerContext context = new FlowDesignerContext();
				context.setAgency(request.getParameter("agency"));
				context.setDepartment(request.getParameter("department"));
				context.setConParam(request.getParameter("conParam"));
				Workflow workflow = updateDB(s, context);
				
				System.out.println(workflow.getId()+"  "+workflow.getName());
				HttpSession session = request.getSession();
				session.setAttribute("WORKFLOW_ID", String.valueOf(workflow.getId()));
				session.setAttribute("WORKFLOW_NAME", String.valueOf(workflow.getName()));
			} else
				throw new IOException("上传文件异常");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} 
	}

	private Workflow updateDB(String xml, FlowDesignerContext context)
			throws Exception {
		WorkflowAss ass = new WorkflowAss();
		Map map = new HashMap();
		map.put("design", xml);
		map.put("context", context);
		return ass.importWorkflow(map);
	}
}
