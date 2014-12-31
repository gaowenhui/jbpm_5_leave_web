<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String conParam = "1";
	String pageCommand = request.getParameter("pageCommand");
	pageCommand = pageCommand!=null?pageCommand:"";
	String pagePID = request.getParameter("pagePID");
	pagePID = pagePID!=null?pagePID:"";
%>

<html>
<style type="text/css">
html{height:100%;margin: 0;}
body{height:100%; margin: 0;}
</style>
	<body onunload="back();" >
		<table width="100%" height="100%" align="center">
			<tr>
				<td>
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						id="FlowDesigner" width="100%" height="100%"
						codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
						<param name="movie" value="<%=request.getContextPath()%>/assets/bpm/FlowDesignerSDC.swf" />
						<param name="quality" value="high" />
						<param name="bgcolor" value="#ffffff" />
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="FlashVars" value="webApp=<%=basePath%>&conParam=<%=conParam%>&pageCommand=<%=pageCommand %>&pagePID=<%=pagePID %>">
						<embed src="<%=request.getContextPath()%>/assets/bpm/FlowDesignerSDC.swf" quality="high" bgcolor="#ffffff"
							width="100%" height="100%" name="FlowDesigner" align="middle"
							flashvars="webApp=<%=basePath%>&conParam=<%=conParam%>&pageCommand=<%=pageCommand %>&pagePID=<%=pagePID %>"
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
	<script language="javaScript">
	function back(){
    
     window.returnValue='1';
    
	}
	</script>
</html>