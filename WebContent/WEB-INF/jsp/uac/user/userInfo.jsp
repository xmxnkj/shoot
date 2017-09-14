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
	<script src="<s:url value="/content/scripts/Uace/actorSelectBox.js"/>"></script>
	<script>
		$(function(){
			initForm();
		});
		function initForm(){
			$("#frm").form({
                url: "<s:url action="saveUserInfo" namespace="/uac/user" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "用户资料保存成功！", function(){})){
                    	clearForm();
                    	top.refreshContent();
                    }
                }
            });	 
			
			
			
			
			
			
			$("#address").validatebox({
				required:false,
				validType:'length[0, 100]',
				missingMessage:'',
				invalidMessage:'地址长度不超过100个汉字！',
				deltaX:-200
			});
			
			$("#idCard").validatebox({
				required:false,
				validType:'length[0, 18]',
				missingMessage:'',
				invalidMessage:'身份证长度不超过18个字符！',
				deltaX:-200
			});
		}
		
		function uploadFile(){
			var url = "<s:url action="showuploadfile" namespace="/uac/user"/>";
			top.openDialog2(url, "上传头像", 500, 200);
		}
		
		function setFileLoaded(fileId, fileName, objectId, objectName, objectType){
			$("#userPic").prop("src", '<s:url action="download" namespace="/kfbase/objectfile"/>?id='+fileId);
			top.setUserPic(fileId);
		}
		
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv" style="text-align: center">
			<table style="width:700px;" class="infotbl">
				<tr>
					<td class="tdHeader">头&nbsp;&nbsp;&nbsp;&nbsp;像：</td>
					<td class="tdContent" colspan="3">
						<s:if test="loginUser.picId==null || loginUser.picId==''">
							<a href="#" onclick="uploadFile();"><img id="userPic" style="width:140px;height:180px;max-height:180px;margin:0px" src="../../web/images/defaulthead.jpg"/></a>
						</s:if><s:else>
							<a href="#" onclick="uploadFile();"><img id="userPic" style="width:140px;height:180px;max-height:180px;margin:0px" src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="loginUser.picId" />"/></a>
						</s:else>
						<div>(点击图片设置头像)</div>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
					<td class="tdContent" ><s:property value="loginUser.name"/></td>
					<td class="tdHeader">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
					<td class="tdContent"><s:property value="loginUser.Department.name"/>
				</tr>
				<tr>
					<td class="tdHeader">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
					<td class="tdContent">
						<s:property value="loginUser.position.name"/>
					</td>
					<td class="tdHeader">联系电话：</td>
					<td class="tdContent">
						<s:textfield name="currentUser.contact" style="width:300px" id="address"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td>
					<td class="tdContent">
						<s:property value="loginUser.positionGrade.name"/>
					</td>
					<td class="tdHeader">居&nbsp;&nbsp;住&nbsp;&nbsp;地：</td>
					<td class="tdContent">
						<s:textfield name="currentUser.address" style="width:300px" id="address"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">出生日期：</td>
					<td class="tdContent">
						<s:textfield name="currentUser.bornDate" cssClass="easyui-datebox" />
					</td>
					<td class="tdHeader">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</td>
					<td class="tdContent">
						<s:textfield name="currentUser.idCard" style="width:300px" id="idCard"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td class="tdContent">
						<s:radio name="currentUser.gender" list="#{'false':'男','true':'女' }"></s:radio>
					</td>
					<td class="tdHeader">公&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司：</td>
					<td class="tdContent">
						来宾市烟草专卖局（公司）
					</td>
				</tr>
				
			</table>
	</div>
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<input type="reset" value="取消" />
	</div>
	
	</s:form>
</body>
</html>