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
		$(function(){
			initForm();
			initCategoryComboTree();
			$("#_name").focus();
		});
		function initForm(){
			$("#frm").form({
                url: "<s:url action="save" namespace="/uac/operation" />",
                onSubmit: function () {
                	var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "操作保存成功！", function(){clearForm();$("#_name").focus();})){
                    	
                    	top.refreshContent();
                    }
                    /* var result = $.parseJSON(data);
                    if (result && result.isSuccess) {
                        $.messager.alert(WINDOW_CAPTION, '指标类型保存成功', 'info');
                        clearForm();
                        top.refreshContent();
                    } else if (result) {
                        $.messager.alert(WINDOW_CAPTION, result.resultDescription, 'error');
                    } else {
                        $.messager.alert(WINDOW_CAPTION, DEFAULT_ERROR, 'error');
                    } */
                }
            });	 
			
			
			$("#_name").validatebox({
				required:true,
				validType:'length[0, 100]',
				missingMessage:'操作名称必填，请输入！',
				invalidMessage:'操作名称长度不超过100个汉字！',
				deltaX:-200
			});
			/* $("#_name").validatebox({
				validType: "remote['<s:url action="validatename" namespace="/uac/operation" />?id=" + $('#_id').val() + "', 'entity.name']",
				invalidMessage:'操作名称已存在，请修改名称！',
				deltaX:-200
			}); */
			
			$("#_description").validatebox({
				validType:'length[0, 250]',
				invalidMessage:'备注说明长度不超过250个汉字！',
				deltaX:-200
			});
		}
		
		function clearForm(){
			var tree = $("#sltCategory").combotree("tree");
			var node = tree.tree("getSelected");
			
			var className = $("#actionClassName").val();
			$("#frm").form("clear");
			if(node != null)
				tree.tree("select", node.target);
				$("#sltCategory").combotree("setValue", node.id);
				$("#categoryId").val(node.id);
				//$("#sltCategory").combotree("select", node.target);
				$("#actionClassName").val(className);
		}
		
		function initCategoryCombobox(){
			var categoryId = "<s:property value="entity.categoryId" />";
			$("#sltCategory").combobox("select", categoryId);
		}
		
		var treeLoaded = false;
		function initCategoryComboTree() {
			$("#sltCategory").combotree({
				url : "<s:url action="listjson" namespace="/uac/operationcategory"/>",
				loadFilter : function(rows) {
					return convert(rows, "操作");
				},
				onLoadError : function(arguments) {
					$.messager.alert("操作", arguments);
				},
				onLoadSuccess : function(node, data) {
					var selectedId = $('#categoryId').val();
					setTreeNode("sltCategory", selectedId);
					treeLoaded = true;
				},
				editable : false,
				width : 350,
				panelHeight : 180,
				onClick : function(node) {
					$("#categoryId").val(node.id);
				}
			});
			hasInit = true;
		}
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">操作名称<label class="required">*</label>：</td>
					<td class="tdContent"><s:textfield id="_name" name="entity.name" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">操作类别：</td>
					<td class="tdContent">
						<select id="sltCategory"></select>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">actionName：</td>
					<td class="tdContent"><s:textfield name="entity.actionName" cssClass="formtext"/></td>
				</tr>
				<tr>
					<td class="tdHeader">actionClassName：</td>
					<td class="tdContent"><s:textfield id="actionClassName" name="entity.actionClassName" cssClass="formtext"/></td>
				</tr>
				<tr>
					<td class="tdHeader">Url：</td>
					<td class="tdContent"><s:textfield name="entity.urlPath" cssClass="formtext"/></td>
				</tr>
				<tr>
					<td class="tdHeader">序号：</td>
					<td class="tdContent"><s:textfield id="_displayOrder" name="entity.displayOrder" cssClass="formtext"/></td>
				</tr>
				<tr>
					<td class="tdHeader">备注：</td>
					<td class="tdContent"><s:textarea id="_description" name="entity.description" cssClass="formtextarea"/></td>
				</tr>
			</table>
			<s:hidden name="entity.id" />
			<s:hidden id="categoryId" name="entity.categoryId"/>
			<s:hidden id="_id" name="id" />
	</div>
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<input type="button" value="取消" onclick="top.closeDialog()" />
	</div>
	</s:form>
</body>
</html>