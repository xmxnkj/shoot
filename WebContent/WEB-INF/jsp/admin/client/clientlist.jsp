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
<title>会员列表</title>
</head>
<body>
<div id="clientlist_layout" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="">
		<div id="clientlist_tabs" class="easyui-tabs" data-options="fit:true">
			<!-- 选项卡01 -->
			<div title="普通用户" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<div id="clientlist_toolbar" >
					<div>
						<span>注册时间段:</span>
					    <input id="resgitTimeStart01" name="resgitTimeStart01" class="easyui-datebox" data-options="width:100"/>-
					    <input id="resgitTimeEnd01" name="resgitTimeEnd01" class="easyui-datebox" data-options="width:100"/>
					    <span>用户姓名:</span>
					    <input id="nickName01" name="nickName" data-options="width:150"/>
					    <span>电话:</span>
					    <input id="tel01" name="tel01" data-options="width:150"/>
					    <span>认证状态:</span>
					    <select id='CertificateState' class='easyui-combobox' name='CertificateState'>
    						<option value="WaitAudit">待审核</option>
    						<option value="Pass">已通过</option>
    						<option value="NotPass">未通过</option>
    						<option value="">全部</option>
						</select>
						
					    <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
					   <!--  <a id="btnAdd" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加会员</a>	    -->
					  	<a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>会员详情</a>
						<!-- <a href="javascript:kill('dgclientlist');"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>删除</a> -->
						<a href="javascript:exportExcel();"  plain="true" class="easyui-linkbutton" iconcls='icon-print'>导出</a>
						
					</div>
				</div> 
				<table id="dgclientlist" ></table>
			</div>
			<!-- 选项卡02 -->
			<div title="员用户" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<div id="mediatorlist_toolbar" >
					<div>
						<span>注册时间段:</span>
					    <input id="resgitTimeStart02" name="resgitTimeStart02" class="easyui-datebox" data-options="width:100"/>-
					    <input id="resgitTimeEnd02" name="resgitTimeEnd02" class="easyui-datebox" data-options="width:100"/>
					    <span>用户姓名:</span>
					    <input id="nickName02" name="nickName" data-options="width:150"/>
					    <span>电话:</span>
					    <input id="tel02" name="tel02" data-options="width:150"/>
					    <a id="btnSearchMediator" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
					    <a id="btnAddMediator" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加会员</a>
					  	<a id="btnEditMediator"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>会员详情</a>
						<!-- <a href="javascript:kill('dgmediatorlist');"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>删除</a> -->
					</div>
				</div> 
				<table id="dgmediatorlist" ></table>
			</div>
		</div>
	</div>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/client/list.js?v=2017042412"></script>	
</body>
</html>