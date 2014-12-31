<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程图</title>
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
	<div class="btn_list">
                    <div class="btn_list_left">
                    	<button type="button" class="btn_clo button left" class="sysBtn" onclick="window.close();"><table border="0" cellpadding="0" cellspacing="0"><tr><td><img src="<bt:img src="x.gif"/>"/></td><td>关闭</td></tr></table></button>                    
                    </div>
      </div>
		<table width="100%" height="95%" align="center" border="0" cellpadding="0" cellspacing="0">
			   <tr>
                        <td class="text_input" align="center">
                            评审主题：<s:property value="beanName.businessSN"/>
                        </td>
                    </tr>
			<tr>
				<td height="100%">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						id="MonitorFW" width="100%" height="95%"
						codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
						<param name="movie" value="<%=request.getContextPath()%>/assets/bpm/MonitorFW.swf" />
						<param name="quality" value="high" />
						<param name="bgcolor" value="#ffffff" />
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="FlashVars" value="webApp=<%=basePath%>&pdId=<%=pdId%>"/>
						<embed src="<%=request.getContextPath()%>/assets/bpm/MonitorFW.swf" quality="high" bgcolor="#ffffff"
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
	</table>
	</body>
</html>