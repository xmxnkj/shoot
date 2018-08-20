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
				<form id="editform" class="easyui-form" method="post">
				<table class="formtable">
				<input hidden id="id" name="entity.id" data-fname="id" style="width:300px">
				<input hidden id="headImgFile" name="entity.headImgFile" data-fname="headImgFile" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">会员昵称<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="nickName" class="easyui-validatebox" data-fname="nickName" name="entity.nickName" />
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">账号<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="account" data-fname="account" class="easyui-validatebox" name="entity.account" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">手机<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="tel" data-fname="tel" class="easyui-validatebox" name="entity.tel"/>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">姓名<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="identifyName" data-fname="identifyName" class="easyui-validatebox" name="entity.identifyName"/>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">身份证号<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="identify" data-fname="identify" class="easyui-validatebox" name="entity.identify" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">用户类型<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="clientType" readonly data-fname="clientType" class="easyui-validatebox" name="entity.clientType" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">用户身份<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="clientState" readonly data-fname="clientState" class="easyui-validatebox" name="entity.clientState" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">排序<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="showDisplay" data-fname="showDisplay" class="easyui-validatebox" name="entity.showDisplay" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">员类型<label class="required">*</label>:</td>
					<td class="tdContent">
					<select id="mediatorType"  name="entity.mediatorType" style="width:200px;">

					</select>
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
	 						<img id="imgFile" name="imgFile" src="#" width="250" height="200"/>
	 						<a href="javascript(0);" id="btn_uploadres" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!建议尺寸100*100,图片大小不超过500k</span>
	 					</td>
					</tr>
			</table>
			</form>
			</div>
		
	  </div>
   </div>
</div>
<%-- <script src="<s:property value="#attr.basePath" />adminresource/js/mall/goodsinfo/editform.js?v=20170212001"></script> --%>
<script src="<s:property value="#attr.basePath" />adminresource/js/client/editform.js"></script>
</body>
</html>