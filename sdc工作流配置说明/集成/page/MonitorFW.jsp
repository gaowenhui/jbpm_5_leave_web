<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.isoftstone.com/sdc/toft3" prefix="t"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String pdId = request.getParameter("processInstanceId").toString();
%>
<html>
<link href="<%=basePath%>/ui/themes/toft/style.css" type="text/css" rel="stylesheet"></link>
<style type="text/css">
html{height:100%;margin: 0;}
body{height:100%; margin: 0;}
</style>
	<body>
		<table width="100%" height="95%" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="100%">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						id="MonitorFW" width="100%" height="95%"
						codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
						<param name="movie" value="MonitorFW.swf" />
						<param name="quality" value="high" />
						<param name="bgcolor" value="#ffffff" />
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="FlashVars" value="webApp=<%=basePath%>&pdId=<%=pdId%>">
						<embed src="MonitorFW.swf" quality="high" bgcolor="#ffffff"
							width="100%" height="95%" name="FlowDesignerSDC" align="middle"
							flashvars="webApp=<%=basePath%>&pdId=<%=pdId%>"
							play="true" loop="false" quality="high"
							allowScriptAccess="sameDomain"
							type="application/x-shockwave-flash"
							pluginspage="http://www.adobe.com/go/getflashplayer">
						</embed>
					</object>
				</td>
			</tr>
		<tr>
			<td align="center"><input type="button" id="btnExit" value="关闭" class="sysBtn" onclick="window.close();" />
			</td>
		</tr>
	</table>
	</body>
</html>