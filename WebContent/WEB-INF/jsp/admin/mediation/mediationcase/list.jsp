<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("path",path,pageContext.REQUEST_SCOPE); 
	pageContext.setAttribute("basePath",basePath,pageContext.REQUEST_SCOPE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="mediationcaselist_layout" class="easyui-layout" data-options="fit:true">
	<div id="mediationcaselist_toolbar" >
		<div>
		    <%-- <span>会员昵称:</span>
		    <input id="nickName" name="nickName" data-options="width:150"/> --%>
		    <!-- <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a> -->
		  	<a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>详情</a>
<%-- 		  	<span>年:</span>
		  	<input id="year" name="beginyear" class="easyui-datebox" data-options="width:100"/> 
		  	<span>月:</span>
		  	<input id="month" name="beginmonth" class="easyui-datebox" data-options="width:100"/>至-
		  	<span>年:</span>
		  	<input id="year" name="endyear" class="easyui-datebox" data-options="width:100"/> 
		  	<span>月:</span> --%>
		  	 <input id="beginDate" name="beginDate" class="easyui-datebox" data-options="width:100"/>至-
		  	 <input id="endDate" name="endyDate" class="easyui-datebox" data-options="width:100"/> 
		    <a id="btnExportExce" href="javascript:void(0);" plain="true" class="easyui-linkbutton">导出ecxel</a>
		</div>
	</div> 
	<div id="search" class="search_bar">
		<form id="search_form" onsubmit="return false;">
			<div class="search-row">
				<span class="search-item"> 
					时间:<input id="beginDate" name="beginDate" class="easyui-datetimebox" data-options="width:100"/>至-
		  			    <input id="endDate" name="endyDate" class="easyui-datetimebox" data-options="width:100"/> 
				</span>
				<span class="search-item"> 
					调委会:
					<input id="mediationAgency" class="easyui-combobox" data-fname="mediationAgency" name="mediationAgency" />
					
				</span>
				<span class="search-item"> 
					员：<input id="mediatorClient" name="mediatorClient" class="easyui-combobox" data-fname="mediatorClient" />
				</span>
				<span class="search-item">
					状态：
				<input id="caseStatus" class="easyui-combobox" data-fname="caseStatus" name="caseStatus" />
					
				</span>
				<input class="search_btn" type="button" onclick="search()" value="搜索" />
			</div>
		</form>
	</div>
	<table id="dgmediationcaselist" ></table>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/mediationcase/list.js?v=2017031011"></script>
</body>
</html>