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
		<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">	
			<form id="editform" class="easyui-form" method="post">
					<table class="formtable">
						 <input hidden id="id" name="entity.id" data-fname="id" style="width:300px">
						 <input hidden id="managerClientId"  class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="managerClientId" name="entity.managerClientId" style="width:300px;"/>
						 <input hidden id="mediationResourceId" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="mediationResourceId" name="entity.mediationResourceId" style="width:300px;"/>
						<tr>
							<td class="tdHeader" style="width: 150px">机构类型<label class="required">*</label>:</td>
							<td class="tdContent">
							   <input id="agencyType" class="easyui-combobox" data-fname="agencyType" name="entity.agencyType" />
							</td>
							<td class="tdHeader" style="width: 150px">机构名称<label class="required">*</label>:</td>
							<td class="tdContent">
							   <input id="agencyName" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="agencyName" name="entity.agencyName" />
							</td>
						</tr>

						<tr>
							<td class="tdHeader" style="width: 150px">电话<label class="required">*</label>:</td>
							<td class="tdContent">
								<input id="tel" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="tel" name="entity.tel" style="width:300px;"/>
							</td>
							<td class="tdHeader" style="width: 150px">所属街道<label class="required">*</label>:</td>
							<td class="tdContent">
								<input id="belongsTo" class="easyui-combobox" required="true" missingMessage="必填，请输入！" data-fname="belongsTo" name="entity.belongsTo" style="width:300px;"/>
							</td>
						</tr>

						<tr>
							<td class="tdHeader" style="width: 150px">机构主体分类<label class="required">*</label>:</td>
							<td class="tdContent">
								<input id="agencyClassify" class="easyui-combobox" required="true" missingMessage="必填，请输入！" data-fname="agencyClassify" name="entity.agencyClassify" style="width:300px;"/>
							</td>
						</tr>
						
						<tr>
							<td class="tdHeader" style="width: 150px">地址<label class="required">*</label>:</td>
							<td class="tdContent">
								<input id="address" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="address" name="entity.address" style="width:300px;"/>
								<div id="allmap"></div>
								<a id="serchByAddress" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
							</td>
						</tr>
						<div id="container" 
						    	style="position: absolute;
						        margin-top:60px; 
						        width: 850px; 
						        height: 300px; 
						        top: 260px;
						        margin-left: 10px;
						        border: 1px solid gray;
						        overflow:hidden;">
						</div>
						
						<tr>
							<td class="tdHeader" style="width: 150px">经度(高德)<label class="required">*</label>:</td>
							<td class="tdContent">
							<input id="lon" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="lon" name="entity.lon" style="width:300px;"/>
							</td>
							<td class="tdHeader" style="width: 150px">纬度(高德)<label class="required">*</label>:</td>
							<td class="tdContent">
							<input id="lat" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="lat" name="entity.lat" style="width:300px;"/>
							</td>
						</tr>

						<tr>
							<td class="tdHeader" style="width: 150px">经度(百度)<label class="required">*</label>:</td>
							<td class="tdContent">
							<input id="lonBaiDu" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="lonBaiDu" name="entity.lonBaiDu" style="width:300px;"/>
							</td>
							<td class="tdHeader" style="width: 150px">纬度(百度)<label class="required">*</label>:</td>
							<td class="tdContent">
							<input id="latBaiDu" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="latBaiDu" name="entity.latBaiDu" style="width:300px;"/>
							</td>
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
							</td>
						</tr>
					</table>
					</form>
		</div>
		
		<div title="缩略图" style="overflow-y:auto;overflow-x:hidden;padding:10px">	
			<table class="formtable">
				<tr>
					<td class="tdHeader" style="width:150px">
						<input type="button" id="btn_uploadres" value="上传资源"/>
					</td>
					<td class="tdContent">
						 <div id="divres">
			    	    	<img id="resource" width="250" height="200" src="#"/>
			    	   	 </div>
			    	   	 <span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!尺寸建议为1280*768,大小不超过2MB</span>
					</td>
				</tr>
				
			</table>	
		</div>
		
		</div>
	</div>
</div>

<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/mediationagency/editform.js?v=2017030711"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=qWmCfqDAdPBzy3YoHxGnimKT&s=1"></script>
</body>
</html>