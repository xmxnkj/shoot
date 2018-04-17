/**
 * 
 */
function initForm(){
	$.easyuiSubmitForm({
		 formid:'editform',
		 url: $homebasepath+"admin/basisset/saveSysParameterTable.shtml",
		 suctext:'保存成功',
 		 succallbackfun:function(result,resultdata){
 			$('#id').val(resultdata.id);
 			$.easyui.parent.refreshData();
		 },
		 faicallbackfun:function(result,message){
			
		 }
	});
}

/**
 * 表单提交
 */
function formsubmit(){
	  $('#editform').submit();
}

/**
 * 表单加载编辑数据
 * @param row
 */
function formLoadEditData(rowdata){
	$('#editform').form("jsonLoad",rowdata);
}


