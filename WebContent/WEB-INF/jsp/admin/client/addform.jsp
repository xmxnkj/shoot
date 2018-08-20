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
		<div id="clientform_tabs" class="easyui-tabs" >
		<!-- 基本信息选项卡BEGIN -->
			<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="addform" class="easyui-form" method="post">
				<table class="formtable">
				<tr>
				<input id="headImgFile" data-fname="headImgFile" class="easyui-validatebox" name="entity.headImgFile" />
				<tr>
					<td class="tdHeader" style="width: 150px">账号<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="account" data-fname="account" class="easyui-validatebox" name="entity.account" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">密码<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="passwd" data-fname="passwd" class="easyui-validatebox" name="entity.passwd" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">姓名<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="identifyName" data-fname="identifyName" class="easyui-validatebox" name="entity.identifyName"/>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">身份证号:</td>
					<td class="tdContent">
					<input id="identify" data-fname="identify" name="entity.identify" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">手机号码<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="tel" data-fname="tel" class="easyui-validatebox" name="entity.tel" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">用户类型<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="clientType" readonly data-fname="clientType" class="easyui-validatebox" value="员" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">用户身份<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="clientState" data-fname="clientState" class="easyui-combobox" name="entity.clientState" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">分配到机构:</td>
					<td class="tdContent">
					<input id="mediationAgencyId" data-fname="mediationAgencyId" class="easyui-combobox" name="entity.mediationAgencyId" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">擅长领域<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="3" 
							          id="skill" 
							          class="easyui-validatebox"
							          required="true" 
							          missingMessage="必填，请输入！"
							          data-fname="skill"
							          name="entity.skill"
								      cssClass="formtext">
							    </textarea>	
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">简介<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="3" 
							          id="description" 
							          class="easyui-validatebox"
							          required="true" 
							          missingMessage="必填，请输入！"
							          data-fname="description"
							          name="entity.description"
								      cssClass="formtext">
							    </textarea>	
				</tr>
				<tr>
						<td class="tdHeader" style="width: 150px">头像<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="resource" name="resource" src="#" width="250" height="200"/>
	 						<!-- <input type="button" id="btn_uploadres" value="上传资源"/> -->
	 						<a href="javascript(0);" id="btn_uploadres" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!</span>
	 					</td>
					</tr>
			</table>
			</form>
			</div>
			<!-- 基本信息选项卡END -->
		
	  </div>
   </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/client/addform.js?v=20170212001"></script>
</body>
</html>