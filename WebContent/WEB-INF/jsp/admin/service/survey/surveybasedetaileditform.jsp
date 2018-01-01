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
<div class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false" title="">
	<form id="editform" class="easyui-form" method="post">
			<table class="formtable">
				问卷题目id:<input  id="mainid" data-fname="mainid" name="entity.mainid" style="width: 400px"/></br>
				id:<input  id="id" data-fname="id" name="entity.id" style="width: 400px"/>
				<td class="tdHeader" style="width: 150px">答案序号<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="seqnum" class="easyui-numberbox" required="true" missingMessage="必填，请输入！" data-fname="seqnum"  name="entity.seqnum" style="width: 250px"/>
						<span style="color:red;font-weight:bold;margin-left: 80px;">请输入正整数!</span>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">答案描述<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="2" 
								          id="surveycontent" 
								          class="easyui-validatebox"
								          required="true" 
								          missingMessage="必填，请输入！"
								          data-fname="surveycontent"
								          name="entity.surveycontent"
									      cssClass="formtext"
									      style="width: 400px">
					    </textarea>	
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width:150px">问题类型<label class="required">*</label>:</td>
					<td class="tdContent">
						<input id="surveytype" data-fname="surveytype" name="entity.surveytype" style="width: 200px"/>
					</td>
				</tr>
			</table>
	</form>
 </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/service/survey/surveybasedetaileditform.js?v=2017030203"></script>
</body>
</html>