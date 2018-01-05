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
	<div id="clientlist_layout" class="easyui-layout" data-options="fit:true,border:false,plain:true">
	<div data-options="region:'center',border:false" title="">
		<div id="newsform_tabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			
			<!-- 卡片1 -->
			<div title="广告图设置" style="overflow-y:auto;overflow-x:hidden;padding:10px">	
				<form id="uploadresform" class="easyui-form" method="post">
			 <table class="formtable">
				<input id="id" hidden name="entity.id" data-fname="id" style="width:300px">				
				<input id="url" hidden name="entity.url" data-fname="url" style="width:300px">
				<input id="newId" hidden name="entity.newId" data-fname="newId" style="width:300px">
				<input hidden id="link" class="easyui-validatebox" data-fname="link" name="entity.link" />
				<tr>
					<td class="tdHeader" style="width: 150px">轮播标题<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="title" class="easyui-validatebox" data-fname="title" name="entity.title" style="width:100%"/>
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width:150px">
						<input type="button" id="btn_uploadres" value="上传资源"/>
					</td>
					<td class="tdContent">
						 <div id="divres">
			    	    	<img id="resource" width="250" height="200" src="#"/>
			    	    		<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!尺寸建议不小于1080*200,大小不超过2MB</span>
			    	   	</div>
					</td>
				</tr>
				
			</table>
		</form>
		</div>
		
		<div title="图片链接设置" style="overflow-y:auto;overflow-x:hidden;padding:10px">	
			<!-- 新闻列表 -->
			<span color='red'>对新闻进行双击进行添加链接指向:</span>
			<div data-options="region:'north'" style="height:400px"  title="">
				<table id="newslist"></table>
			</div>
			
			<!-- 链接列表 -->
			<span style="">当前指向链接:</span>
			<div data-options="region:'sorth'" style="height:300px" title="">
				<table id="myNewslist"></table>
			</div>
		</div>
		
		</div>
	</div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/news/MomentsResources/uploadmalResources.js"></script></body>
</html>