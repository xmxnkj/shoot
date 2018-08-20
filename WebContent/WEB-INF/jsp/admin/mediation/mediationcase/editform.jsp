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
		<div id="editform_tabs" class="easyui-tabs" >
			<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="editform" class="easyui-form" method="post">
					<table class="formtable">
						 <input id="id" hidden name="entity.id" data-fname="id" style="width:300px">
						<tr>
						<td class="tdHeader" style="width: 150px">类型<label class="required">*</label>:</td>
							<td class="tdContent">
							   <input readonly id="caseType" data-fname="caseType" name="entity.caseType" />
							 </td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">申述点<label class="required">*</label>:</td>
							<td class="tdContent">
							<textarea  readonly
									  rows="5" 
							          id="caseExplain" 
							          data-fname="caseExplain"
							          name="caseExplain"
								      cssClass="formtext">
							    </textarea>	
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">发生地<label class="required">*</label>:</td>
							<td class="tdContent">
							<input readonly id="address" data-fname="address" name="entity.address" style="width:300px;"/>
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">申诉人<label class="required">*</label>:</td>
							<td class="tdContent">
							<input readonly id="applyClientName" data-fname="applyClientName" name="entity.applyClientName" style="width:300px;"/>
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">被申诉人<label class="required">*</label>:</td>
							<td class="tdContent">
							<input readonly id="complainant" style="width:300px;"/>
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">正文<label class="required">*</label>:</td>
							<td class="tdContent">
								<textarea  readonly
									  rows="3" 
							          id="refuseReason" 
							          data-fname="refuseReason"
							          name="entity.refuseReason"
								      cssClass="formtext">
							    </textarea>	
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">状态<label class="required">*</label>:</td>
							<td class="tdContent">
							<input readonly id="caseState" data-fname="caseState" name="entity.caseState" style="width:300px;"/>
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">导出卷宗<label class="required">*</label>:</td>
							<td class="tdContent">
								<a href="javascript:getMediationCaseFile()" class="easyui-linkbutton" plain="true" iconCls="icon-print">导出</a>
							</td>
						</tr>
					</table>
					</form>
			</div>
			
			<div title="人民调查记录" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<table id="dgmediationrecordlist" ></table>
			</div>
			
			<div title="人民记录" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<table id="dgmediationresourcelist" ></table>
			</div>
			
			<div title="协议书" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="protocolform" class="easyui-form" method="post">
					<table class="formtable">
						<tr>
							<td class="tdHeader" style="width: 150px">标题<label class="required">*</label>:</td>
							<td class="tdContent" colspan="4">
								<input readonly id="title"  data-fname="title" name="entity.title" style="width:600px;"/>
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">正文<label class="required">*</label>:</td>
							<td class="tdContent" colspan="4">
								<textarea rows="12" 
									  readonly
							          id="content" 
							          class="easyui-textbox"
							          data-fname="content"
							          name="entity.content"
								      cssClass="formtext">
							    </textarea>	
							</td>
						</tr>
						<tr>
							<td class="tdHeader" style="width: 150px">甲方<label class="required">*</label>:</td>
							<td class="tdContent">
								<input readonly id="applyClient" data-fname="applyClient.identifyName" name="entity.applyClient"/>
							</td>
							<td class="tdHeader" style="width: 150px">是否同意<label class="required">*</label>:</td>
							<td class="tdContent">
								<input readonly id="partAState"  data-fname="partAState" name="entity.partAState"/>
							 </td>
						</tr>
					</table>
				</form>
				<form id="partbform" class="easyui-form" method="post">
					<table class="formtable">
						<tr>
							<td class="tdHeader" colspan="4"><center>
								乙方信息
							</center></td>
						</tr>
						<tr >
							<td class="tdHeader">
								<center>姓名</center>
							</td>
							<td class="tdHeader">
								<center>是否同意</center>
							</td>
						</tr>
							<tr>
								<td class="tdContent">
									<center>
										<div id="tempclient"></div>
									</center>
								</td>
								<td class="tdContent">
									<center>
									<div id="tempclient2"></div>
									</center>
								</td>
							</tr>
					</table>
				</form>
			</div>
			
			<div title="聊天记录" style="overflow-y:auto;overflow-x:hidden;padding:10px">
					<table id="chatList" ></table>
			</div>
			
		</div>
	</div>
</div>
<script id="tempclientTmpl" type="text/x-jsrender">
	<div id="wraper">
        <div class="tempclient">
            <div class="data">
				 
	            	<div class="data-body">
						<p>{{:identifyName}}</p>
	            	</div>
				
            </div>
        </div>
    </div>
</script>
<script id="tempclientTmpl2" type="text/x-jsrender">
	<div id="wraper">
        <div class="tempclient">
            <div class="data">
				 
	            	<div class="data-body">
						<p>{{:sign}}</p>
	            	</div>
				
            </div>
        </div>
    </div>
</script>
<script src="<s:property value="#attr.basePath" />pluginresource/jsviews/jsrender.min.js"></script>
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/mediationcase/editform.js?v=2017041808"></script>
</body>
</html>