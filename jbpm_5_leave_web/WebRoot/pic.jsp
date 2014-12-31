<%@page import="org.jbpm.api.*,java.io.*"%>
<%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
			
	ExecutionService executionService = processEngine.getExecutionService();
			
	String id = request.getParameter("id");
	ProcessInstance processInstance = executionService.findProcessInstanceById(id);
			
	String processDefinitionId = processInstance.getProcessDefinitionId();
	ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
	
	//此处不知道为什么还要特别的通过repositoryService和processDefinition.getDeploymentId()来显示此图片
	InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),"leave2.png");
	System.out.println("inputStream: "+inputStream);
	//InputStream inputStream =new FileInputStream(new File("D:/program/java/servers/apache-tomcat-5.5.23/webapps/jbpm2/WEB-INF/classes/leave.png"));
	byte[] b = new byte[1024];
	int len = -1; 
	ServletOutputStream sos=response.getOutputStream();
	while ((len = inputStream.read(b, 0, 1024)) != -1) {
		sos.write(b, 0, len);
	}
	
	sos.close();
%>