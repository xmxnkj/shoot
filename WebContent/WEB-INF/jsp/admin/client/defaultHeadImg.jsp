<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false" title="">
		<div id="clientform_tabs" class="easyui-tabs" >
		<!-- 基本信息选项卡BEGIN -->
			<div title="用户默认头像" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="addform" class="easyui-form" method="post">
				<table class="formtable">
				<tr>
						<td class="tdHeader" style="width: 150px">头像<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFile1" name="imgFile1" src="#" width="250" height="200"/>
	 						<!-- <input type="button" id="btn_uploadres" value="上传资源"/> -->
	 						<a href="javascript(0);" id="btn_uploadres1" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!建议尺寸100*100,图片大小不超过500K</span>
	 					</td>
					</tr>
			</table>
			</form>
			</div>
			<!-- 基本信息选项卡END -->
			<div title="身份证默认图片" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="addform" class="easyui-form" method="post">
				<table class="formtable">
				<tr>
						<td class="tdHeader" style="width: 150px">头像<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFile2" name="imgFile2" src="#" width="250" height="200"/>
	 						<!-- <input type="button" id="btn_uploadres" value="上传资源"/> -->
	 						<a href="javascript(0);" id="btn_uploadres2" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!建议尺寸140*80,图片大小不超过500K</span>
	 					</td>
					</tr>
			</table>
			</form>
			</div>
			
			<div title="调解机构默认图片" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="addform" class="easyui-form" method="post">
				<table class="formtable">
				<tr>
						<td class="tdHeader" style="width: 150px">头像<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFile3" name="imgFile3" src="#" width="250" height="200"/>
	 						<!-- <input type="button" id="btn_uploadres" value="上传资源"/> -->
	 						<a href="javascript(0);" id="btn_uploadres3" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!建议尺寸140*80,图片大小不超过500K</span>
	 					</td>
					</tr>
			</table>
			</form>
			</div>
		
	  </div>
   </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/client/defaultHeadImg.js?v=20170212001"></script>
</body>
</html>