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
			<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="editform" class="easyui-form" method="post">
				<table class="formtable">
				
				<input id="id" type="hidden" name="entity.id" data-fname="id" style="width:300px">				
				<tr>
				
				<td class="tdHeader" style="width: 150px">排序<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="orderdisplay" class="easyui-validatebox" data-fname="orderdisplay" name="entity.orderdisplay" style="width:100%"/>
					 </td>
				</tr>
				<td class="tdHeader" style="width: 150px">新闻标题<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="articletitle" class="easyui-validatebox" data-fname="articletitle" name="entity.articletitle" style="width:100%"/>
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">新闻单位<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="newsUnit" data-fname="newsUnit" class="easyui-validatebox" name="entity.newsUnit" style="width:100%" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">作者<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="authorName" data-fname="authorName" class="easyui-validatebox" name="entity.authorName" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">点赞数:</td>
					<td class="tdContent">
					<input id="likenums" readonly data-fname="likenums" class="easyui-validatebox" name="entity.likenums" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">评论数:</td>
					<td class="tdContent">
					<input id="commentnums" readonly data-fname="commentnums" class="easyui-validatebox" name="entity.commentnums" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">发布时间:</td>
					<td class="tdContent">
					<input id="articledatetime" data-fname="articledatetime" type="text" class="easyui-datetimebox" name="entity.articledatetime" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">新闻简介<label class="required">*</label>:</td>
					<td class="tdContent">
					<textarea id="simpleDesc" rows="5" class="easyui-validatebox" required="true" missingMessage="必填，请输入！"
							          data-fname="simpleDesc"
							          name="entity.simpleDesc"
								      cssClass="formtext">
					</textarea>
					</td>
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">内容<label class="required">*</label>：</td>
					<td class="tdContent">
 						<textarea name="entity.newsContent" data-fname="newsContent" id="newsContent"></textarea>
 					</td>
				</tr>

				</table>
				</form>
			</div>
			
			<div title="主图信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="mainImgeditform" class="easyui-form" method="post">
				<table class="formtable">
					<tr>
						<td class="tdHeader" style="width: 150px">主图<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFile" name="imgFile" src="#" width="250" height="200"/>
	 						<a href="javascript(0);" id="btn_uploadres" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!尺寸不超过1280*768,且大小不超过2MB</span>
	 					</td>
					</tr>
					
					<%-- <tr>
						<td class="tdHeader" style="width: 150px">多图<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFiles" name="imgFiles" src="#" width="250" height="200"/>
	 						<input type="file" name="imgs" id="imgs" multiple/>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!</span>
	 					</td>
					</tr> --%>
					
				</table>
				</form>
			</div>
				
			<div title="新闻评论" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<div id="newsCommentslist_toolbar" >
					<div>
					    <a id="btnRemoveComment" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>移除</a>
					</div>
				</div> 
				<table id="newsCommentslist" ></table>
			</div>

		</div>
		</div>
	</div>

	<link rel="stylesheet" href="<s:property value="#attr.basePath" />/content/scripts/kindEditor/themes/default/default.css">
 	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/kindeditor-min.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/lang/zh_CN.js"></script>
	<script src="<s:property value="#attr.basePath" />adminresource/js/news/editform.js?v=2017031004"></script>
</body>
</html>