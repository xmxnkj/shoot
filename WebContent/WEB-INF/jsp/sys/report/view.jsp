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
		
		function validateReply(){
			var content=$("#content").val();
			if(content==''){
				top.$.messager.alert(WINDOW_CAPTION, '请输入回复内容！', 'error');
				return false;
			}
			return true;
		}
		
		function replyReport(replyId){
			if(validateReply()){
				
				var data={replyContent:$("#content").val()};
				if(replyId){
					data["replyId"]=replyId;
				}
				
				$.ajax({
					url:'<s:url action="reply" namespace="/sys/report"/>?id=' + $("#_id").val(),
					data:data,
					type:'post',
					timeout:TIMEOUT,
					success:function(data){
						
						if(dealJsonResult(data, "回复成功！")){
	                       	location.reload();
	                    }
					},
	                error: function () {
	                    $.messager.alert(WINDOW_CAPTION, '网络原因导致回复失败，请重试或联系管理员！', 'error');
	                }
							
				});
			}
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
	<div class="formdiv">
		<table class="signtbl">
			<tr>
				<td class="signtit">
					地区：
				</td>
				<td>
					<div class="signTag">
						<s:iterator value="areas">
							<span <s:if test="id==entity.area.id">class="selected"</s:if> ><s:property value="name"/></span>
						</s:iterator>
					</div>
				</td>
			</tr>
			<tr>
				<td class="signtit">
					栏目：
				</td>
				<td>
					<div class="signTag">
						<s:iterator value="categories">
							<span <s:if test="id==entity.category.id">class="selected"</s:if> ><s:property value="name"/></span>
						</s:iterator>
					</div>
				</td>
			</tr>
			<tr>
				<td class="signtit">
					内容类型：
				</td>
				<td>
					<div class="signTag">
						<s:iterator value="contentTypes">
							<span <s:if test="id==entity.contentType.id">class="selected"</s:if> ><s:property value="name"/></span>
						</s:iterator>
					</div>
				</td>
			</tr>
			<tr>
				<td class="signtit">
					事件类型：
				</td>
				<td>
					<div class="signTag">
						<s:iterator value="eventTypes">
							<span <s:if test="id==entity.eventType.id">class="selected"</s:if> ><s:property value="name"/></span>
						</s:iterator>
					</div>
				</td>
			</tr>
			<tr>
				<td class="signtit">
					隐私：
				</td>
				<td>
					<div class="signTag">
						<span <s:if test="entity.isPublic">class="selected"</s:if>>公开</span>
						<span <s:if test="!entity.isPublic">class="selected"</s:if>>私密</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="signtit">
					处理流程：
				</td>
				<td>
					<div class="signTag">
						<s:if test="entity.reportStatus.toString()=='Submit'">
							<span class="statuscur"><a href="#" onclick="acceptReport();return false;">审核</a></span>--><span>回复</span>
							<input type="button" value="审核" style="width:80px;height:25px" onclick="acceptReport();" /> 
						</s:if>
						<s:if test="entity.reportStatus.toString()=='Accept'">
							<span class="statusexe">审核(<s:property value="entity.acceptTime"/>)</span>--><span class="statuscur"><a href="#" onclick="showReply();return false;">回复</a></span>
							<input type="button" value="回复" style="width:80px;height:25px" onclick="showReply();" /> 
						</s:if>
						<s:if test="entity.reportStatus.toString()=='Dealed'">
							<span class="statusexe">审核(<s:property value="entity.acceptTime"/>)</span>--><span class="statusexe"><a  href="#" onclick="showReply();return false;">回复(<s:property value="entity.replyTime"/>)</a></span>
							<input type="button" value="回复" style="width:80px;height:25px" onclick="showReply();" />
						</s:if>
					</div>
				</td>
			</tr>
			<s:if test="entity.reportStatus.toString()=='Dealed'">
				<tr>
					<td class="signtit" style="vertical-align: top">
						回复：
					</td>
					<td>
						<div class="signTag">
							<s:iterator value="replies">
								时间：<s:property value="replyDate"/>&nbsp;&nbsp;<a href="#" onclick="showReply('<s:property value="id"/>', '<s:property value="content"/>');return false;">修改</a><br/>
								内容：<s:property value="content"/><br/><br/>
								
							</s:iterator>
						</div>
					</td>
				</tr>
			</s:if>
		</table>
		<div style="margin-left:30px">
		<input type="button" value="编辑报料" style="width:80px;height:30px" onclick="edit();">
		<input type="button" value="关闭" style="width:80px;height:30px" onclick="top.closeDialog();">
		</div>
		<div class="reportContent">
			<div class="rpttit">
				报料人：<s:property value="entity.reportUserName" />&nbsp;&nbsp;&nbsp;&nbsp;
				报料时间：<s:property value="entity.reportTime"/>
			</div>
			<div class="rptcon">
				<s:property value="entity.content"/>
				<div style="padding:20px">
			<s:if test="images.size > 0">
				图片附件： 
				<div>
					<s:iterator value="images">
						<a href="<s:url action="image" namespace="/sys/report"/>?id=<s:property value="id" />" target="_blank">
						<img alt="" width="100" height="100" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />">
						</a>
					</s:iterator>	
				</div>
			</s:if>
			<s:if test="videos.size > 0">
				<br/>视频附件： 
				<div>
					<s:iterator value="videos">
						<a href="<s:url action="video" namespace="/sys/report"/>?id=<s:property value="id" />" target="_blank">
						<video width="100" height="100" type="video/mp4">
							<s:if test="objectName!=null && objectName!=''">
								<source src="<s:property value="objectName"/>"/>
							</s:if><s:else>
								<source src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />"/>
							</s:else>
						</video>
						</a>
					</s:iterator>	
				</div>
			</s:if>
			<s:if test="audios.size > 0">
				<br/>音频附件： 
				<div>
					<s:iterator value="audios">
						<s:if test="objectName!=null && objectName!=''">aa
						<audio controls="controls" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="objectName" />" width="100" height="100">
						</audio>
						</s:if><s:else>bb
						<audio controls="controls" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />" width="100" height="100">
						</audio>
						</s:else>
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