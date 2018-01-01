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
				问卷活动id:<input  id="surveyactivityid" data-fname="surveyactivityid" name="entity.surveyactivityid" style="width: 400px"/></br>
				id:<input  id="id" data-fname="id" name="entity.id" style="width: 400px"/>
				<td class="tdHeader" style="width: 150px">问题序号<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="seqnum" class="easyui-numberbox" required="true" missingMessage="必填，请输入！" data-fname="seqnum"  name="entity.seqnum" style="width: 250px"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">问题描述<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="3" 
								          id="surveytitle" 
								          class="easyui-validatebox"
								          required="true" 
								          missingMessage="必填，请输入！"
								          data-fname="surveytitle"
								          name="entity.surveytitle"
									      cssClass="formtext"
									      style="width: 400px">
					    </textarea>	
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width:150px">创建时间<label class="required">*</label>:</td>
					<td class="tdContent">
						<input id="bulidtime" data-fname="bulidtime" name="entity.bulidtime" class="easyui-datetimebox" style="width: 250px"/>
					</td>
				</tr>
			</table>
	</form>
 </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/service/survey/surveybasemaineditform.js?v=2017030203"></script>
</body>
</html>