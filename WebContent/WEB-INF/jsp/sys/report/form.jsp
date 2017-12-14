<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	
		var saveUrl = "<s:url action="save" namespace="/sys/report" />";
		var serverValidateName = '<s:url action="validatename" namespace="/sys/report" />';
		
		var saveSucInfo = "报料保存成功！";
		var nameRequire = true;
		var nameMaxLength = 100;
		var nameServerValidate = false;
		var descriptionLengthValidate = true;
		var descriptionMaxLength = 250;
		
		var saveClearForm=false;
		var refreshContent=true;
		
		function delFile(id){
			top.$.messager.confirm(WINDOW_CAPTION, "确定要删除吗？", function(rst){
				if(rst){
					$.ajax({
						url:'<s:url action="delFile" namespace="/sys/report"/>?fileId=' + id + "&id="+$("#_id").val(),
						type:'post',
						timeout:TIMEOUT,
						success:function(data){
							
							if(dealJsonResult(data, "删除成功！")){
		                       	location.reload();
		                    }
						},
		                error: function () {
		                    $.messager.alert(WINDOW_CAPTION, '网络原因导致删除失败，请重试或联系管理员！', 'error');
		                }
								
					});
				}
			})
		}
		
		function showUpload(){
			var id = $("#_id").val();
			if(id!=''){
				top.openDialog2("<s:url action="showuploadfile" namespace="/kfbase/objectfile"/>?entity.objectId=<s:property value="entity.id"/>", "上传附件", 500, 200);	
			}else{
				top.$.messager.alert(WINDOW_CAPTION, "请先保存报料后再上传！", 'error');
			}
			
		}
		
		function setFileLoaded(){
			location.reload();
		}
		
		
		function acceptReport(){
			top.$.messager.confirm(WINDOW_CAPTION, "确认要审核报料吗？", function(rst){
				if(rst){
					$.ajax({
						url:'<s:url action="accept" namespace="/sys/report"/>?id=' + $("#_id").val(),
						type:'post',
						timeout:TIMEOUT,
						success:function(data){
							if(dealJsonResult(data, "审核成功！")){
	                        	location.reload();
	                        	top.refreshContent();
	                        }
						},
	                    error: function () {
	                        $.messager.alert(WINDOW_CAPTION, '网络原因导致审核失败，请重试或联系管理员！', 'error');
	                    }
								
					})
				}
			});
		}
		
		
		function showReply(replyId,replyContent){
			if(replyId){
				$("#replyId").val(replyId);
				$("#content").val(replyContent);
			}else{
				$("#replyId").val("");
				$("#content").val(replyContent); 
			}
			openDialog();			
		}
		
		
		function openDialog(title, width, height, isMaxed) {
            var options = {};
            if (title) {
                options.title = title;
            }
            if (width) {
                options.width = width;
            }
            if (height) {
                options.height = height;
            }
            if(isMaxed)
            	options.maximized = true;
            else
            	options.maximized = false;
            $("#dlg").dialog(options);
            $("#dlg").dialog("open", options);
        }
		
		function closeDialog(){
			$("#dlg").dialog("close");
		}
		
		function edit(){
			location.href='<s:url action="edit" namespace="/sys/report"/>?id=<s:property value="entity.id"/>';
		}
	</script>
	<script src="<s:url value="/content/scripts/common/form.js"/>"></script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">报料人：</td>
					<td class="tdContent"><s:textfield id="_name" name="entity.reportUserName" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">报料时间：</td>
					<td class="tdContent"><s:textfield name="entity.reportTime" cssClass="easyui-datetimebox" /></td>
				</tr>
				<tr>
					<td class="tdHeader">内容：</td>
					<td class="tdContent"><s:textarea name="entity.content" cssClass="formtextarea"/></td>
				</tr>
				<tr>
					<td class="tdHeader">地区：</td>
					<td class="tdContent">
						<s:select name="entity.area.id" list="areas" headerKey="" headerValue="---请选择---" listKey="id" listValue="name" cssClass="easyui-combobox" style="width:200px"></s:select>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">内容类型：</td>
					<td class="tdContent">
						<s:select name="entity.contentType.id" list="contentTypes" headerKey="" headerValue="---请选择---" listKey="id" listValue="name" cssClass="easyui-combobox" style="width:200px"></s:select>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">栏目：</td>
					<td class="tdContent">
						<s:select name="entity.category.id" list="categories" headerKey="" headerValue="---请选择---" listKey="id" listValue="name" cssClass="easyui-combobox" style="width:200px"></s:select>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">事件类型：</td>
					<td class="tdContent">
						<s:select name="entity.eventType.id" list="eventTypes" headerKey="" headerValue="---请选择---" listKey="id" listValue="name" cssClass="easyui-combobox" style="width:200px"></s:select>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">隐私：</td>
					<td class="tdContent">
						<s:select name="entity.isPublic" list="#{'true':'公开','false':'私密' }" headerKey="" headerValue="---请选择---" cssClass="easyui-combobox" style="width:200px"></s:select>
					</td>
				</tr>
				
			</table>
			
			<div class="reportContent">
			<div class="rptcon">
				<div style="padding:20px">
			<s:if test="images.size > 0">
				图片附件： 
				<div>
					<s:iterator value="images">
						<div class="fleft">
						<a href="<s:url action="image" namespace="/sys/report"/>?id=<s:property value="id" />" target="_blank">
						<img alt="" width="100" height="100" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />">
						</a>
						<br/>
						<a href="#" onclick="delFile('<s:property value="id"/>');return false;">删除</a>
						</div>
					</s:iterator>	
				</div>
				<div class="clear"></div>
			</s:if>
			<s:if test="videos.size > 0">
				<br/>视频附件： 
				<div>
					<s:iterator value="videos">
						<div class="fleft">
						<a href="<s:url action="video" namespace="/sys/report"/>?id=<s:property value="id" />" target="_blank">
						<video width="100" height="100" type="video/mp4">
							<source src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />"/>
							
						</video>
						</a>
						<br/>
						<a href="#" onclick="delFile('<s:property value="id"/>');return false;">删除</a>
						</div>
					</s:iterator>	
				</div>
				<div class="clear"></div>
			</s:if>
			<s:if test="audios.size > 0">
				<br/>音频附件： 
				<div>
					<s:iterator value="audios">
						<audio controls="controls" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />" width="100" height="100">
						</audio>
						<a href="#" onclick="delFile('<s:property value="id"/>');return false;">删除</a>
						<br>
					</s:iterator>
				</div>
			</s:if>
			</div>
			</div>
		</div>
			<s:hidden name="entity.id" />
			<s:hidden id="_id" name="id" />
	</div>
	
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<s:if test="entity!=null && entity.id!=null && entity.id!=''">
			<input type="button" value="上传新附件" onclick="showUpload();"/>
		</s:if>
		<input type="button" value="取消" onclick="top.closeDialog();" />
		<s:if test="entity.reportStatus.toString()=='Submit'">
			<input type="button" value="审核" style="width:80px;height:25px" onclick="acceptReport();" /> 
		</s:if>
		<s:if test="entity.reportStatus.toString()=='Accept'">
			<input type="button" value="回复" style="width:80px;height:25px" onclick="showReply();" /> 
		</s:if>
		<s:if test="entity.reportStatus.toString()=='Dealed'">
			<a  href="#" onclick="showReply();return false;">回复(<s:property value="entity.replyTime"/>)</a></span>
			<input type="button" value="回复" style="width:80px;height:25px" onclick="showReply();" />
		</s:if>
	</div>
	</s:form>
	
	<div id="dlg" class="easyui-dialog" closed="true" modal="true" title="回复" style="width: 480px; height: 300px; overflow: visible">
		<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">回复内容：</td>
					<td class="tdContent">
						<s:textarea name="replyContent" id="content" cssClass="formtextarea"/>
					</td>
				</tr>
			</table>
			<div class="buttonbox">
				<input type="button" value="回复" onclick="replyReport();">
				<input type="button" value="取消" onclick="closeDialog();">
			</div>
			<input type="hidden" id="replyId">
		</div>    
    </div>
</body>
</html>