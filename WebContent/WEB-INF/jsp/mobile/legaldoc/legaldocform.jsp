<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10); 
String path = request.getContextPath();
System.out.print(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path",path,PageContext.REQUEST_SCOPE); 
pageContext.setAttribute("basePath",basePath,PageContext.REQUEST_SCOPE);

String legaldocid = request.getAttribute("legaldocid").toString();
System.out.print(legaldocid);
String error = request.getAttribute("error").toString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/lib/weui.min.css">
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/clean-up.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/style.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/Regulatory-documents.css">
<title>法规文档</title>

</head>
<body>
<script src="<s:property value="#attr.basePath" />pluginresource/jqweui/lib/jquery-2.1.4.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jqweui/js/jquery-weui.min.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jsviews/jsrender.min.js"></script>
<script src="<s:property value="#attr.basePath" />mobileresource/js/legaldoc.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/plugins/jquery.plugin.apiajax.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/plugins/jquery.plugin.utils.js"></script>
<div>
	<input type="hidden" id="legaldocid" value="<%=legaldocid %>"/>
	<input type="hidden" id="error" value="<%=error %>"/>
	<input type="hidden" id="homeBasePath" value="<%=basePath %>"/>
	<input type="hidden" id="homePath" value="<%=path %>"/>
</div>
<div id="title"></div>
<!-- 动态模板 循环列表 BENGIN-->
<script id="infoTmpl" type="text/x-jsrender">
	<div id="wraper">
        <div class="legaldoc">
        	<div class="title">
            	<p>{{:title}}</p>
            </div>
			<div class="data-body">
				{{:content}}
	        </div>
            <div class="title-time">
            	<p>{{:publishTime}}{{:publishUnit}}</p>
            </div>
        </div>
    </div>
</script>
<script id="contentTmpl" type="text/x-jsrender">
	<div id="wraper">
        <div class="legaldoc">
            <div class="data">
	            	<div class="data-body">
						<p>{{:content}}</p>
	            	</div>			
            </div>
        </div>
    </div>
</script>
</body>
</html>