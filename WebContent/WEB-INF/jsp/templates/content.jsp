<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10); 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path",path,PageContext.REQUEST_SCOPE); 
pageContext.setAttribute("basePath",basePath,PageContext.REQUEST_SCOPE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9;IE=8;IE=EDGE" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
<meta name ="apple-mobile-web-app-capable" content="yes"/>
<meta content="telephone=no" name="format-detection"/>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>Mobile 3D</title>
<script type="text/javascript">
var $homebasepath = "<%=basePath%>";
var $homepath = "<%=path%>";
</script>    
	<script src="<s:url value="/pluginresource/jeasyui-extensions/jquery-1.11.1.min.js"/>"></script>
    <script src="<s:url value="/content/scripts/jquery-easyui/jquery.easyui.min.js"/>"></script>
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/icon.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/green1.css"/>" rel="stylesheet" />
    <script src="<s:url value="/content/scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"/>"></script>
    <link href="<s:url value="/content/styles/menu.css"/>" rel="stylesheet" type="text/css" />
    <link href="<s:url value="/content/styles/index.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/styles/form.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/styles/Site.css"/>" rel="stylesheet" />
    <link rel="stylesheet" href="<s:url value="/mobileresource/css/style.css"/>">
    <link rel="stylesheet" href="<s:url value="/mobileresource/css/Pagestyle.css"/>"> 
    <link rel="stylesheet" href="<s:url value="/mobileresource/css/new.css"/>"> 
    <!--
    <link href="<s:url value="/web/css/jquery.rating.css"/>" rel="stylesheet" />
    <link href="<s:url value="/web/css/common.css"/>" rel="stylesheet" />
    -->
    
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jquery.jdirk.min.js"/>"></script>
    <link href="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.min.css"/>" rel="stylesheet" />
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.js"/>"></script>
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.all.min.js"/>"></script>
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.form.js"/>"></script>    
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.utils.js"/>"></script>
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.apiajax.js"/>"></script>
    
   
    <script src="<s:url value="/content/scripts/common/setting.js"/>"></script>
    <script src="<s:url value="/content/scripts/common/utils.js"/>"></script>
    <script src="<s:url value="/content/scripts/common/tree.js"/>"></script>
    
    <script src="<s:url value="/pluginresource/ajaxupload/ajaxupload.3.6.js"/>"></script>
    
    <link href="<s:url value="/content/styles/buyTicketCss/phone_style.css"/>" rel="stylesheet"/>
	<link href="<s:url value="/content/styles/buyTicketCss/datepicker.css"/>" rel="stylesheet" type="text/css" />
	
    <style type="text/css">
    	body{
    		overflow: hidden;
    	}
    	html{
    		overflow:auto;
    	}
    </style>
    
    
</head>
<body>
		<decorator:body />
</body>
</html>