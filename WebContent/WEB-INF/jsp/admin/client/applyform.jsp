<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false" title="">
		<div id="mediatorapplylist_tabs" class="easyui-tabs" >
		<!-- 基本信息选项卡BEGIN -->
			<div title="审核信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="applyform" class="easyui-form" method="post">
				<table class="formtable">
				<tr>
				<input type="hidden" id="id" readonly data-fname="id" name="entity.id" />
				<input type="hidden" id="applyClientId" readonly data-fname="applyClientId" name="entity.applyClientId" />
				<tr>
					<td class="tdHeader" style="width: 150px">申请人:</td>
					<td class="tdContent">
					<input id="applyClient" readonly data-fname="applyClient.identifyName" class="easyui-validatebox" name="entity.applyClient" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">申请时间:</td>
					<td class="tdContent">
					<input id="applyTime" readonly data-fname="applyTime" class="easyui-validatebox" name="entity.applyTime" />
				</tr>
				 <tr>
					<td class="tdHeader" style="width: 150px">申请理由:</td>
					<td class="tdContent">
						<textarea rows="3" 
										readonly
							          id="applyReason" 
							          class="easyui-validatebox"
							          missingMessage="必填，请输入！"
							          data-fname="applyReason"
							          name="entity.applyReason"
								      cssClass="formtext">
							    </textarea>	
				</tr>
			
				<tr>
					<td class="tdHeader" style="width: 150px">申请状态:</td>
					<td class="tdContent">
					<input readonly id="applyState"  data-fname="applyState" class="easyui-validatebox" name="entity.applyState"/>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">审核状态:</td>
					<td class="tdContent">
					<input id="auditState"  data-fname="auditState" class="easyui-combobox"/>
					<span style="color:red;font-weight:bold;margin-left: 80px;">请选择!</span>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">分配到调解机构:</td>
					<td class="tdContent">
					<input id="mediationAgencyId" data-fname="mediationAgencyId" class="easyui-combobox" />
					<span style="color:red;font-weight:bold;margin-left: 80px;">请选择分配机构!</span>
				</tr>
			</table>
			</form>
			</div>
			<!-- 基本信息选项卡END -->
		
	  </div>
   </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/client/applyform.js?v=2017052404"></script>
</body>
</html>