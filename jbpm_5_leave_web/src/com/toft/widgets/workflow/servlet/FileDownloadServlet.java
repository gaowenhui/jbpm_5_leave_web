/**
 * 鹿鹏添加 流程导出调用servlet
 */
package com.toft.widgets.workflow.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileDownloadServlet extends HttpServlet {
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

	
	public void init() throws ServletException {
		System.out.println("-------------------FileDownloadServlet.init()------------------------");
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
			
			String filename = request.getParameter("fileName");
			String dir = request.getParameter("dir");
			
	        if (filename == null) {
	            throw new ServletException("Parameter 'filename' must be supplied");
	        }
	        String path = getClass().getClassLoader().getResource("").getPath()
			.split("WEB-INF")[0]+ dir +File.separatorChar + filename;
	        path = path.replaceAll("%20", " "); // 对应com/toft/widgets/workflow/utils/CommonValue.java所做的修改
	
	        File file = new File(path);
	        
	        try {
	        	//设置响应的类型及头信息
	            response.reset();
	            response.setContentType("application/octet-stream; charset=UTF-8");
	            filename = URLEncoder.encode(filename, "UTF-8");
	            response.addHeader("Content-Disposition","attachment; filename=" + filename);

	            //将文件的内容输出到指定的文件中
	            java.io.FileInputStream fis = new java.io.FileInputStream(file);
	            OutputStream output = response.getOutputStream();
	            byte[] buffer = new byte[1000];
	            while(fis.read(buffer) != -1){
	               output.write(buffer);
	               output.flush();
	               buffer = new byte[1000];
	            }          
	            output.close();
	            fis.close();
	        } catch (Exception e){	        	
	        	e.printStackTrace();
	        	
	        }
	       
	       
	}

	
}
